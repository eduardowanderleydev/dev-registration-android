package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;
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

    private final File FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_presentation);

        configureViews();
        bindIntentResumeInViewResume();

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
            personCity.setText(resume.getAdress().getLocalidade());
            personState.setText(resume.getAdress().getUf());
            personDistrict.setText(resume.getAdress().getBairro());
            personStreet.setText(resume.getAdress().getLogradouro());

        } else {
            throw new RuntimeException("Resume cannot be found");
        }
    }

    public void generatePDF(View view) {
        PdfDocument doc = new PdfDocument();

        PdfDocument
                .PageInfo pageDetails = new PdfDocument
                .PageInfo.Builder(500, 600, 1)
                .create();

        PdfDocument.Page page = doc.startPage(pageDetails);

        Canvas canvas = page.getCanvas();

        Paint textColor = new Paint();
        textColor.setColor(Color.BLACK);

        // name
        canvas.drawText(personName.getText().toString(), 200, 100, textColor );

        // address
        canvas.drawText(personCity.getText().toString() + " - " + personState.getText().toString(), 105, 150, textColor );
        canvas.drawText(personDistrict.getText().toString() + ", " + personStreet.getText().toString(), 105, 165, textColor );

        // contact
        canvas.drawText(personEmail.getText().toString(), 105, 180, textColor );
        canvas.drawText(personPhone.getText().toString(), 105, 195, textColor );

        // goal
        canvas.drawText("Objetivo :", 105, 225, textColor );
        canvas.drawText(personGoal.getText().toString(), 105, 240, textColor );

        // education
        canvas.drawText("Educação :", 105, 270, textColor );
        canvas.drawText(personEducation.getText().toString(), 105, 285, textColor );

        // experience
        canvas.drawText("Experiência Profissional :", 105, 315, textColor );
        canvas.drawText(personExperience.getText().toString(), 105, 330, textColor );

        // skills
        canvas.drawText("Conhecimento técnico e habilidades :", 105, 360, textColor );
        canvas.drawText(personSkills.getText().toString(), 105, 375, textColor );

        doc.finishPage(page);

        try {
            doc.writeTo(new FileOutputStream(FILE_PATH + "/exported.pdf"));
            Toast.makeText(this, "Download realizado com sucesso.", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Falha ao gerar PDF.", Toast.LENGTH_SHORT).show();
        }

        doc.close();
    }

}