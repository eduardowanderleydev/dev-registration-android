package br.com.ucsal.devregistration.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.ucsal.devregistration.R;

public class PDFUtils {

    private final View view;
    private final File FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private boolean isGenerated = false;

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

    public PDFUtils(View view) {
        this.view = view;
        configureViews(view);
    }

    public boolean generatePDF() {
        PdfDocument doc = new PdfDocument();

        PdfDocument.PageInfo pageDetails = new PdfDocument
                .PageInfo.Builder(500, 600, 1)
                .create();
        PdfDocument.Page page = doc.startPage(pageDetails);

        Canvas canvas = page.getCanvas();

        Paint textColor = new Paint();
        textColor.setColor(Color.BLACK);

        writeOnScreen(canvas, textColor);

        doc.finishPage(page);

        try {
            doc.writeTo(new FileOutputStream(FILE_PATH + "/exported.pdf"));
            this.isGenerated = true;
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        doc.close();
        return isGenerated;
    }

    private void writeOnScreen(Canvas canvas, Paint textColor) {
        // name
        canvas.drawText(personName.getText().toString(), 200, 100, textColor);

        // address
        canvas.drawText(personCity.getText().toString() + " - " + personState.getText().toString(), 105, 150, textColor);
        canvas.drawText(personDistrict.getText().toString() + ", " + personStreet.getText().toString(), 105, 165, textColor);

        // contact
        canvas.drawText(personEmail.getText().toString(), 105, 180, textColor);
        canvas.drawText(personPhone.getText().toString(), 105, 195, textColor);

        // goal
        canvas.drawText("Objetivo :", 105, 225, textColor);
        canvas.drawText(personGoal.getText().toString(), 105, 240, textColor);

        // education
        canvas.drawText("Educação :", 105, 270, textColor);
        canvas.drawText(personEducation.getText().toString(), 105, 285, textColor);

        // experience
        canvas.drawText("Experiência Profissional :", 105, 315, textColor);
        canvas.drawText(personExperience.getText().toString(), 105, 330, textColor);

        // skills
        canvas.drawText("Conhecimento técnico e habilidades :", 105, 360, textColor);
        canvas.drawText(personSkills.getText().toString(), 105, 375, textColor);
    }

    private void configureViews(View view) {
        personName = view.findViewById(R.id.name);
        personEmail = view.findViewById(R.id.email);
        personPhone = view.findViewById(R.id.phoneNumber);
        personGoal = view.findViewById(R.id.goal);
        personEducation = view.findViewById(R.id.education);
        personExperience = view.findViewById(R.id.experience);
        personSkills = view.findViewById(R.id.skills);
        personCity = view.findViewById(R.id.city);
        personState = view.findViewById(R.id.state);
        personDistrict = view.findViewById(R.id.adressDistrict);
        personStreet = view.findViewById(R.id.adressStreet);
    }
}
