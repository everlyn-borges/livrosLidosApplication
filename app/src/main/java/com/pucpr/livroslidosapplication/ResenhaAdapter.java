//package com.pucpr.livroslidosapplication;
//
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ResenhaAdapter extends RecyclerView.Adapter<ResenhaAdapter.ResenhaViewHolder> {
//    private ResenhaLivroActivity resenhaLivroActivity;
//    private List<Livro> listaLivros;
//
//    public ResenhaAdapter(List<Livro> listaLivros, ResenhaLivroActivity resenhaLivroActivity) {
//        this.listaLivros = listaLivros;
//        this.resenhaLivroActivity = resenhaLivroActivity;
//    }
//
//    public void adicionarLivro(Livro livro) {
//        listaLivros.add(livro);
//        notifyItemInserted(listaLivros.size() - 1);
//    }
//
//    @NonNull
//    @Override
//    public ResenhaAdapter.ResenhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_recyclerview, parent, false);
//        return new ResenhaAdapter.ResenhaViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ResenhaAdapter.ResenhaViewHolder holder, int position) {
//        Livro livro = listaLivros.get(position);
//
//        holder.nomeLivroTextView.setText(livro.getNomeLivro());
//        holder.autorLivroTextView.setText(livro.getAutoriaLivro());
//        holder.avaliacaoTextView.setText("Avaliação: " + livro.getAvaliacao());
//        holder.leituraConcluidaTextView.setText("Leitura concluída: " + livro.isStatusLeitura());
//
//        String categoria = livro.getCategoria();
//        if (categoria != null && !categoria.isEmpty()) {
//            int drawableId = getDrawableIdForCategory(categoria);
//            holder.categoriaImageView.setImageResource(drawableId);
//        }
//
//        holder.buttonVerResenha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(resenhaLivroActivity, ResenhaLivroActivity.class);
//                intent.putExtra("livro", livro);
//                resenhaLivroActivity.startActivity(intent);
//            }
//        });
//
//    }
//
//    private int getDrawableIdForCategory(String categoria) {
//        int drawableId = 0;
//
//        if (categoria.equals("Profissão")) {
//            drawableId = R.drawable.job;
//        } else if (categoria.equals("Saúde")) {
//            drawableId = R.drawable.health;
//        } else if (categoria.equals("Relacionamento")) {
//            drawableId = R.drawable.relationship;
//        } else if (categoria.equals("Lazer")) {
//            drawableId = R.drawable.leisure;
//        }
//
//        return drawableId;
//    }
//
//    @Override
//    public int getItemCount() {
//        return listaLivros.size();
//    }
//
//    public class ResenhaViewHolder extends RecyclerView.ViewHolder {
//        public TextView nomeLivroTextView;
//        public TextView autorLivroTextView;
//        public TextView avaliacaoTextView;
//        public TextView leituraConcluidaTextView;
//        public Button buttonVerResenha;
//        private List<Livro> livros;
//        public ImageButton categoriaImageView;
//
//        public ResenhaViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nomeLivroTextView = itemView.findViewById(R.id.nomeLivroTextView);
//            autorLivroTextView = itemView.findViewById(R.id.autorLivroTextView);
//            avaliacaoTextView = itemView.findViewById(R.id.avaliacaoTextView);
//            leituraConcluidaTextView = itemView.findViewById(R.id.leituraConcluidaTextView);
//            categoriaImageView = itemView.findViewById(R.id.categoriaImageView);
//            buttonVerResenha = itemView.findViewById(R.id.buttonVerResenha);
//        }
//    }
//}
