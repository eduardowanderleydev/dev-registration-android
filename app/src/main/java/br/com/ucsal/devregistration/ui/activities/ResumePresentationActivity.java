package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;
import br.com.ucsal.devregistration.exception.ResumeNotFoundException;
import br.com.ucsal.devregistration.util.PDFUtils;
import br.com.ucsal.devregistration.util.PermissionUtils;

public class ResumePresentationActivity extends AppCompatActivity {

    private TextView personName;
    private TextView personCity;
    private TextView personState;
    private TextView personDistrict;
    private TextView personStreet;
    private TextView personEmail;
    private TextView personPhone;
    private TextView personGoal;
    private TextView personEducation;
    private TextView personExperience;
    private TextView personSkills;

    private FloatingActionButton btnExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_presentation);

        configureViews();
        bindIntentResumeInViewResume();
        setTitle("Currículo de " + personName.getText().toString());

        btnExport.setOnClickListener(view -> {
            PermissionUtils.verifyStoragePermissions(this);
            generatePDF(view);
        });

    }

    private void configureViews() {
        personName = findViewById(R.id.name);
        personEmail = findViewById(R.id.email);
        personPhone = findViewById(R.id.phoneNumber);
        personGoal = findViewById(R.id.goal);
        personEducation = findViewById(R.id.education);
        personExperience = findViewById(R.id.experience);
        personSkills = findViewById(R.id.skills);
        personCity = findViewById(R.id.city);
        personState = findViewById(R.id.state);
        personDistrict= findViewById(R.id.adressDistrict);
        personStreet= findViewById(R.id.adressStreet);
        btnExport = findViewById(R.id.btn_export);
    }

    private void bindIntentResumeInViewResume() {
        Intent intent = getIntent();
        if (intent.hasExtra("resume")){

            Resume resume = (Resume) intent.getSerializableExtra("resume");
            personName.setText(resume.getName());
            personEmail.setText(resume.getEmail());
            personPhone.setText(resume.getPhoneNumber());
            personGoal.setText(resume.getGoal());
            personEducation.setText(resume.getEducation());
            personExperience.setText(resume.getProfessionalExperiences());
            personSkills.setText(resume.getKnowledgeAndSkills());
            personCity.setText(resume.getAddress().getLocalidade());
            personState.setText(resume.getAddress().getUf());
            personDistrict.setText(resume.getAddress().getBairro());
            personStreet.setText(resume.getAddress().getLogradouro());

        } else {
            throw new ResumeNotFoundException("Resume cannot be found");
        }
    }

    public void generatePDF(View view) {
        PDFUtils pdf = new PDFUtils(view);
        boolean isGenerated = pdf.generatePDF();
        if (isGenerated) {
            Toast.makeText(this, "Download realizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Falha ao gerar PDF.", Toast.LENGTH_SHORT).show();
        }
    }

}