package com.pucpr.livroslidosapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResumoActivity extends AppCompatActivity {

    private List<Livro> listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        Button botaoMinhasLeituras = findViewById(R.id.buttonMinhasLeituras);
        botaoMinhasLeituras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LivroDatabase livroDatabase = new LivroDatabase(this);
        listaLivros = livroDatabase.listarLivros();

       //LivroAdapter livroAdapter = new LivroAdapter(listaLivros, ResumoActivity.this);

        List<Livro> livrosLidos = new ArrayList<>();
        for (Livro livro : listaLivros) {
            if (livro.isStatusLeitura()) {
                livrosLidos.add(livro);
            }
        }

        int totalLivrosLidos = livrosLidos.size();
        TextView totalDeLivrosTextView = findViewById(R.id.totalDeLivrosEditText);
        totalDeLivrosTextView.setText(String.valueOf(totalLivrosLidos));

        int totalLivrosEmLeitura = listaLivros.size() - totalLivrosLidos;
        TextView totalDeLivrosEmLeituraEditText = findViewById(R.id.totalDeLivrosEmLeituraEditText);
        totalDeLivrosEmLeituraEditText.setText(String.valueOf(totalLivrosEmLeitura));

        int totalLivrosProfissao = 0;
        int totalLivrosRelacionamento = 0;
        int totalLivrosSaude = 0;
        int totalLivrosLazer = 0;

        for (Livro livro: listaLivros){
            if (livro.getCategoria()!= null){
                if (livro.getCategoria().equals("Profissão")) {
                    totalLivrosProfissao++;
                } else if (livro.getCategoria().equals("Saúde")) {
                    totalLivrosSaude++;
                } else if (livro.getCategoria().equals("Relacionamento")) {
                    totalLivrosRelacionamento++;
                } else if (livro.getCategoria().equals("Lazer")) {
                    totalLivrosLazer++;
                }
            }

        }

        TextView totalDeLivrosProfissaoTextView = findViewById(R.id.totalProfissaoEditText);
        totalDeLivrosProfissaoTextView.setText(String.valueOf(totalLivrosProfissao));

        TextView totalDeLivrosSaudeTextView = findViewById(R.id.totalRelacionamentoEditText);
        totalDeLivrosSaudeTextView.setText(String.valueOf(totalLivrosSaude));

        TextView totalDeLivrosRelacionamentoTextView = findViewById(R.id.totalSaudeEditText);
        totalDeLivrosRelacionamentoTextView.setText(String.valueOf(totalLivrosRelacionamento));

        TextView totalDeLivrosLazerTextView = findViewById(R.id.totalLazerEditText);
        totalDeLivrosLazerTextView.setText(String.valueOf(totalLivrosLazer));

    }

}