package br.com.ucsal.devregistration.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.db.Db;
import br.com.ucsal.devregistration.domain.Resume;
import br.com.ucsal.devregistration.ui.adapter.ResumeAdapter;

/**
 * Activity principal, responsável por listar os currículos na tela, além de configurar cliques e botões.
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton buttonAddResume;
    private ResumeAdapter adapter;

    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Currículos");

        configureDb();
        configureViews();
        configureAdapter();
        configureButtonAddResume();
        configureLongClickToRemoveResume();
        configureClickToOpenResumePreview();
    }

    /**
     * Método responsável por configurar que quando o usuário der um clique rápido em um dos currículos, será chamada uma nova activity exibindo os dados do currículo na tela.
     */
    private void configureClickToOpenResumePreview() {
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, ResumePresentationActivity.class);
            intent.putExtra("resume", adapter.getItem(i));
            startActivity(intent);
        });
    }

    private void configureDb() {
        db = Db.getInstance(this);
    }

    /**
     * Método responsável por configurar clique longo para excluir um currículo na lista.
     */
    private void configureLongClickToRemoveResume() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Tem certeza ?");
            builder.setMessage("Um currículo excluído NÃO pode ser recuperado.");
            builder.setPositiveButton("Confirmar",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Resume resumeToRemove = adapter.getItem(i);
                            if (resumeToRemove != null) {
                                removeResume(resumeToRemove);
                            }
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing because its a cancel button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        });
    }

    /**
     * Método responsável por configurar clique no botão de adicionar um currículo, chamando a activity responsável pelo registro de um novo currículo.
     */
    private void configureButtonAddResume() {
        buttonAddResume.setOnClickListener(view -> {
            Intent toGo = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(toGo);
        });
    }

    /**
     * Método responsável por remover um ítem do banco de dados e da listView.
     */
    private void removeResume(Resume resumeToRemove) {
        db.resumeDAO().delete(resumeToRemove);
        adapter.remove(resumeToRemove);
    }

    /**
     * Método responsável por configurar o adapter no listView.
     */
    private void configureAdapter() {
        adapter = new ResumeAdapter(this, db.resumeDAO().findAll());
        listView.setAdapter(adapter);
    }

    /**
     * Método responsável por fazer o bind entre os atributos definidos e os elementos da view.
     */
    private void configureViews() {
        listView = findViewById(R.id.list_resumes);
        buttonAddResume = findViewById(R.id.fab_adicionar);
    }
}