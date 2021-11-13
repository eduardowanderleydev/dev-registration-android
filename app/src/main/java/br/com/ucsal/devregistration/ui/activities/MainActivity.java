package br.com.ucsal.devregistration.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Adress;
import br.com.ucsal.devregistration.domain.Resume;
import br.com.ucsal.devregistration.repository.ArrayRepository;
import br.com.ucsal.devregistration.ui.adapter.ResumeAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton buttonAddResume;
    private ResumeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Currículos");

        addSomeResumesToTest();
        configureViews();
        configureAdapter();
        configureButtonAddResume();
        configureLongClickToRemoveResume();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, ResumePresentationActivity.class);
            intent.putExtra("resume", adapter.getItem(i));
            startActivity(intent);
        });

    }

    private void configureLongClickToRemoveResume() {
        listView.setOnItemLongClickListener( (adapterView, view, i, l) -> {
            // TODO perguntar ao usuário se tem certeza que deseja remover
            Resume resumeToRemove = (Resume) adapter.getItem(i);
            if (resumeToRemove != null ){
                removeResume(resumeToRemove);
            }
            return false;
        });
    }

    private void configureButtonAddResume() {
        buttonAddResume.setOnClickListener(view -> {
            Intent toGo = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(toGo);
        });
    }

    private void removeResume(Resume resumeToRemove) {
        ArrayRepository.remove(resumeToRemove);
        adapter.remove(resumeToRemove);
    }

    private void addSomeResumesToTest() {
        Resume resumeEduardo = new Resume();
        resumeEduardo.setName("Eduardo");
        resumeEduardo.setEmail("eduardowanderleydev@gmail.com");
        resumeEduardo.setPhoneNumber("75 9 8207-4248");
        resumeEduardo.setKnowledgeAndSkills("Java, Spring, Maven, Javascript, Typescript e sla mais oq");
        resumeEduardo.setGoal("Ser milionário");
        resumeEduardo.setProfessionalExperiences("Engenheiro de software Sr - Apple");

        Resume resumeCaio = new Resume();
        resumeCaio.setName("Caio");
        resumeCaio.setGoal("desenvolvedor");

        Resume resumeGuilherme = new Resume();
        resumeGuilherme.setName("Guilherme");
        resumeGuilherme.setGoal("frontend developer");

        Resume resumeGustavo = new Resume();
        resumeGustavo.setName("Gustavo");
        resumeGustavo.setGoal("Engenheiro de Software");

        ArrayRepository.addAll(Arrays.asList(resumeEduardo, resumeCaio, resumeGuilherme, resumeGustavo));
    }

    private void configureAdapter() {
        adapter = new ResumeAdapter(this, ArrayRepository.findAll());
        listView.setAdapter(adapter);
    }

    private void configureViews() {
        listView = findViewById(R.id.list_resumes);
        buttonAddResume = findViewById(R.id.fab_adicionar);
    }

}