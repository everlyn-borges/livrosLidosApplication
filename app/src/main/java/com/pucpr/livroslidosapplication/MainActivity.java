package com.pucpr.livroslidosapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLivros;
    private LivroAdapter livroAdapter;
    private static final int REQUEST_CADASTRO_LIVRO = 1;
    private ActivityResultLauncher<Intent> launcher;
    private List<Livro> listaLivros = new ArrayList<>();
    private ImageView categoriaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewLivros = findViewById(R.id.recyclerViewLivros);
        recyclerViewLivros.setLayoutManager(new LinearLayoutManager(this));

        LivroDatabase livroDatabase = new LivroDatabase(this);
        listaLivros = livroDatabase.listarLivros();

        livroAdapter = new LivroAdapter(listaLivros, this);
        recyclerViewLivros.setAdapter(livroAdapter);

        livroAdapter.setLivros(listaLivros);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewLivros);
        recyclerView.setAdapter(livroAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button meuBotao = findViewById(R.id.buttonAddBook);
        meuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroLivros.class);
                launcher.launch(intent);
            }
        });

        Button resumoBotao = findViewById(R.id.buttonMinhasLeituras);
        resumoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResumoActivity.class);
                launcher.launch(intent);
            }
        });

        categoriaImageView = findViewById(R.id.categoriaImageView);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.hasExtra("livro")) {
                                Livro livro = (Livro) data.getSerializableExtra("livro");
                                listaLivros.add(livro);
                                livroAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int chamada, int resposta, @Nullable Intent data) {
        super.onActivityResult(chamada, resposta, data);

        if (chamada ==REQUEST_CADASTRO_LIVRO && resposta ==RESULT_OK) {
            if (data != null && data.hasExtra("livro")) {
                Livro livro = (Livro) data.getSerializableExtra("livro");

                LivroDatabase livroDatabase = new LivroDatabase(this);
                livroDatabase.criarLivroNoDB(livro.getCategoria(), livro);

                livroAdapter.setLivros(listaLivros);
                livroAdapter.notifyDataSetChanged();

                listaLivros.add(livro);
                livroAdapter.notifyDataSetChanged();
                String categoria = livro.getCategoria();

                if (categoria != null && !categoria.isEmpty()) {
                    int drawableId = getDrawableIdForCategory(categoria);
                    categoriaImageView.setImageResource(drawableId);
                    }
                listaLivros.add(livro);
                livroAdapter.adicionarLivro(livro);
            }
        }
    }

    private int getDrawableIdForCategory(String categoria) {
        switch (categoria) {
            case "Profissao":
                return R.drawable.job;
            case "Saude":
                return R.drawable.health;
            case "Relacionamento":
                return R.drawable.relationship;
            case "Lazer":
                return R.drawable.leisure;
            default:
                return R.drawable._168626_books_education_learning_knowledge_icon;
        }
    }
}