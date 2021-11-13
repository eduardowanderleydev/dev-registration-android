package br.com.ucsal.devregistration.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.ucsal.devregistration.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonAddResume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Currículos");

        configureViews();


    }

    private void configureViews() {
        recyclerView = findViewById(R.id.list_resumes);
        buttonAddResume = findViewById(R.id.fab_adicionar);
    }

}