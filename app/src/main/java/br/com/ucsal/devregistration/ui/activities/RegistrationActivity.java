package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;
import br.com.ucsal.devregistration.repository.ArrayRepository;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        configureView();
        configureButtonAddTouch();
    }

    private void configureButtonAddTouch() {
        saveButton.setOnClickListener(view -> {
            Resume resume = createResumeTroughFields();
            //TODO validar campos
            ArrayRepository.add(resume);

            Intent toGo = new Intent(RegistrationActivity.this, MainActivity.class);
            toGo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(toGo);
            finish();
        });
    }

    private Resume createResumeTroughFields() {
        Resume resume = new Resume();
        resume.setName(personName.getText().toString());
        resume.setAdress(null);
        resume.setGoal(personGoal.getText().toString());
        resume.setEducation(personEducation.getText().toString());
        resume.setKnowledgeAndSkills(personSkills.getText().toString());
        resume.setPhoneNumber(personPhone.getText().toString());
        resume.setEmail(personEmail.getText().toString());
        resume.setProfessionalExperiences(personExperience.getText().toString());
        return resume;
    }

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
}