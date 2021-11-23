package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.db.Db;
import br.com.ucsal.devregistration.domain.Address;
import br.com.ucsal.devregistration.domain.Resume;
import br.com.ucsal.devregistration.exception.InvalidCEPException;
import br.com.ucsal.devregistration.service.HttpService;
import br.com.ucsal.devregistration.util.CEPUtils;

/**
 * Activity responsável por renderizar tela de cadastro de um novo currículo e realizar as chamadas para operação de inserção do currículo no banco de dados.
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText personName;
    private EditText personCEP;
    private EditText personEmail;
    private EditText personPhone;
    private EditText personGoal;
    private EditText personEducation;
    private EditText personExperience;
    private EditText personSkills;

    private Button saveButton;

    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        setTitle("Novo currículo");

        configureDb();
        configureView();
        configureButtonAddTouch();
    }

    /**
     * Método responsável por configurar o botão de adicionar um novo currículo no banco de dados.
     * <p>
     * Após o currículo ser adicionado com sucesso, a pilha de intents é limpa, impedindo que o usuário possa desfazer a inserção.
     */
    private void configureButtonAddTouch() {
        saveButton.setOnClickListener(view -> {

            try {
                CEPUtils.validaCEP(personCEP.getText().toString());
            } catch (InvalidCEPException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            Resume resume = createResumeTroughFields();
            if (isValidFields(resume)) {
                db.resumeDAO().insert(resume);

                Intent toGo = new Intent(RegistrationActivity.this, MainActivity.class);
                toGo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toGo);
                finish();
            } else {
                Toast.makeText(this, "Por favor, verifique os campos preenchidos", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


    /**
     * Método responsável pela validação dos campos digitados pelo usuário para cadastro de um novo currículo.
     *
     * @param resume
     * @return true se todas as informações do currículo analisado forem válidas.
     */
    private boolean isValidFields(Resume resume) {
        if (resume.getEducation() == null || resume.getEducation().isEmpty())
            return false;
        if (resume.getEmail() == null || resume.getEmail().isEmpty())
            return false;
        if (resume.getGoal() == null || resume.getGoal().isEmpty())
            return false;
        if (resume.getName() == null || resume.getName().isEmpty())
            return false;
        if (resume.getKnowledgeAndSkills() == null || resume.getKnowledgeAndSkills().isEmpty())
            return false;
        if (resume.getPhoneNumber() == null || resume.getPhoneNumber().isEmpty())
            return false;
        else
            return true;
    }

    /**
     * Método responsável por criar um novo Currículo com base nas informações que foram digitadas pelo usuário.
     *
     * @return um novo Currículo, populado com os dados digitados pelo usuário.
     */
    private Resume createResumeTroughFields() {
        Resume resume = new Resume();
        resume.setName(personName.getText().toString());
        Address address = returnAddressByCep(personCEP.getText().toString());
        resume.setAddress(address);
        resume.setGoal(personGoal.getText().toString());
        resume.setEducation(personEducation.getText().toString());
        resume.setKnowledgeAndSkills(personSkills.getText().toString());
        resume.setPhoneNumber(personPhone.getText().toString());
        resume.setEmail(personEmail.getText().toString());
        resume.setProfessionalExperiences(personExperience.getText().toString());
        return resume;
    }

    /**
     * Método responsável por fazer a chamada a classe HttpService passando o CEP informado pelo usuário como parâmetro.
     *
     * @param cep
     * @return o endereço completo com base no CEP que foi informado pelo usuário.
     */
    private Address returnAddressByCep(String cep) {
        try {
            return new HttpService(cep).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método responsável por fazer o bind entre os atributos definidos e os elementos da view.
     */
    private void configureView() {
        personName = findViewById(R.id.request_name);
        personCEP = findViewById(R.id.request_cep);
        personPhone = findViewById(R.id.request_phone);
        personEmail = findViewById(R.id.request_email);
        personGoal = findViewById(R.id.request_goal);
        personEducation = findViewById(R.id.request_education);
        personExperience = findViewById(R.id.request_professionalExperience);
        personSkills = findViewById(R.id.request_knowledge);

        saveButton = findViewById(R.id.btn_save);
    }

    private void configureDb() {
        db = Db.getInstance(this);
    }
}