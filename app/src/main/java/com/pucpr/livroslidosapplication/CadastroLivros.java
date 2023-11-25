package com.pucpr.livroslidosapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroLivros extends AppCompatActivity {

    private ImageButton imageButtonProfissao;
    private ImageButton imageButtonSaude;
    private ImageButton imageButtonRelacionamento;
    private ImageButton imageButtonLazer;
    private EditText nomeLivroEditText;
    private EditText autorEditText;
    private RatingBar avaliacaoRatingBar;
    private Switch leituraConcluidaSwitch;
    private Button buttonAddLivro;
    private String categoriaSelecionada;

    private LivroDatabase livroDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livros);

        livroDatabase = new LivroDatabase(this, "livros.sqlite", null, 1);

        imageButtonProfissao = findViewById(R.id.imageButtonProfissao);
        imageButtonSaude = findViewById(R.id.imageButtonSaude);
        imageButtonRelacionamento = findViewById(R.id.imageButtonRelacionamento);
        imageButtonLazer = findViewById(R.id.imageButtonLazer);
        nomeLivroEditText = findViewById(R.id.nomeLivroEditText);
        autorEditText = findViewById(R.id.resenhaEditText);
        avaliacaoRatingBar = findViewById(R.id.avaliacaoRatingBar);
        leituraConcluidaSwitch = findViewById(R.id.leituraConcluidaSwitch);
        buttonAddLivro = findViewById(R.id.buttonAddLivro);

        imageButtonProfissao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Profissão";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Profissão", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonRelacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Relacionamento";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Relacionamento", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Saúde";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Saúde", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonLazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Lazer";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Lazer", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAddLivro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomeLivro = nomeLivroEditText.getText().toString();
                String autorLivro = autorEditText.getText().toString();
                float avaliacao = avaliacaoRatingBar.getRating();
                boolean leituraConcluida = leituraConcluidaSwitch.isChecked();

                Livro livro = new Livro(categoriaSelecionada, nomeLivro, autorLivro, avaliacao, leituraConcluida);

                long id = livroDatabase.criarLivroNoDB(categoriaSelecionada,livro);

                if (id != -1) {
                Toast.makeText(CadastroLivros.this, "Livro inserido com ID: " + id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("livro", livro);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(CadastroLivros.this, "Falha ao cadastrar o livro", Toast.LENGTH_SHORT).show();
                }
            }

        });

        imageButtonProfissao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Profissão";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Profissão", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonRelacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Relacionamento";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Relacionamento", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Saúde";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Saúde", Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonLazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaSelecionada = "Lazer";
                Toast.makeText(CadastroLivros.this, "Categoria selecionada: Lazer", Toast.LENGTH_SHORT).show();
            }

        });
    }
}