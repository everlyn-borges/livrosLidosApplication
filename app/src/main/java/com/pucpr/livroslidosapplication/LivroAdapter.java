package com.pucpr.livroslidosapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroViewHolder> {

    private MainActivity mainActivity;
    private List<Livro> listaLivros;
    private RecyclerView recyclerViewLivros;
    private Context context;

    public LivroAdapter(List<Livro> listaLivros, Context context) {
        this.listaLivros = listaLivros;
        this.context = context;
    }

    public void setLivros(List<Livro> livros) {
        this.listaLivros = livros;
        notifyDataSetChanged();
    }

    public void adicionarLivro(Livro livro) {
        listaLivros.add(livro);
        notifyItemInserted(listaLivros.size() - 1);
    }

    @NonNull
    @Override
    public LivroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);

        return new LivroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder holder, int position) {
        Livro livro = listaLivros.get(position);

        holder.nomeLivroTextView.setText("Nome do livro: " + livro.getNomeLivro());
        holder.autorLivroTextView.setText("Autoria do livro: " + livro.getAutoriaLivro());
        holder.avaliacaoTextView.setText("Avaliação: " + livro.getAvaliacao());
        holder.leituraConcluidaTextView.setText("Leitura concluída: " + livro.isStatusLeitura());

        String categoria = livro.getCategoria();
        if (categoria != null && !categoria.isEmpty()) {
            int drawableId = getDrawableIdForCategory(categoria);
            holder.categoriaImageView.setImageResource(drawableId);
        }
    }

    private int getDrawableIdForCategory(String categoria) {
        int drawableId = 0;

        if (categoria.equals("Profissão")) {
            drawableId = R.drawable.job;
        } else if (categoria.equals("Saúde")) {
            drawableId = R.drawable.health;
        } else if (categoria.equals("Relacionamento")) {
            drawableId = R.drawable.relationship;
        } else if (categoria.equals("Lazer")) {
            drawableId = R.drawable.leisure;
        }

        return drawableId;
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public class LivroViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeLivroTextView;
        public TextView autorLivroTextView;
        public TextView avaliacaoTextView;
        public TextView leituraConcluidaTextView;
        private List<Livro> livros;
        public ImageButton categoriaImageView;
        public Button buttonAdicionarLivro;

        public LivroViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeLivroTextView = itemView.findViewById(R.id.nomeLivroTextView);
            autorLivroTextView = itemView.findViewById(R.id.autorLivroTextView);
            avaliacaoTextView = itemView.findViewById(R.id.avaliacaoTextView);
            leituraConcluidaTextView = itemView.findViewById(R.id.leituraConcluidaTextView);
            categoriaImageView = itemView.findViewById(R.id.categoriaImageView);
            buttonAdicionarLivro = itemView.findViewById(R.id.buttonAddBook);
        }
    }

    public List<Livro> getAllLivros() {
        return listaLivros;
    }
}
