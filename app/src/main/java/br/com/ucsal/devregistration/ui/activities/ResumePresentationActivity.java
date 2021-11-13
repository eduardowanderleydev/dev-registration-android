package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;

public class ResumePresentationActivity extends AppCompatActivity {

    private TextView personName;
    // TODO implementar endereco
    private TextView personEmail;
    private TextView personPhone;
    private TextView personGoal;
    private TextView personEducation;
    private TextView personExperience;
    private TextView personSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_presentation);

        configureViews();
        bindIntentResumeInViewResume();

    }

    private void configureViews() {
        personName = findViewById(R.id.name);
        personEmail = findViewById(R.id.email);
        personPhone = findViewById(R.id.phoneNumber);
        personGoal = findViewById(R.id.goal);
        personEducation = findViewById(R.id.education);
        personExperience = findViewById(R.id.experience);
        personSkills = findViewById(R.id.skills);
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
        } else {
            throw new RuntimeException("Resume cannot be found");
        }
    }
}