package com.pucpr.livroslidosapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class LivroDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "livros.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Livro";
    private static final String COL_ID = "id";
    private static final String COL_CATEGORIA = "categoria";
    private static final String COL_NOME_LIVRO = "nome_livro";
    private static final String COL_AUTORIA_LIVRO = "autoria_livro";
    private static final String COL_AVALIACAO = "avaliacao";
    private static final String COL_STATUS_LEITURA = "status_leitura";
    private static final String COL_RESENHA = "resenha";
    private static final String COL_DATA_INICIO_LEITURA = "data_inicio_leitura";
    private static final String COL_DATA_FIM_LEITURA = "data_fim_leitura";
    private Context context;

    public LivroDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public LivroDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CATEGORIA + " TEXT, " +
                COL_NOME_LIVRO + " TEXT, " +
                COL_AUTORIA_LIVRO + " TEXT, " +
                COL_AVALIACAO + " REAL, " +
                COL_STATUS_LEITURA + " INTEGER, " +
                COL_RESENHA + " TEXT, " +
                COL_DATA_INICIO_LEITURA + " TEXT, " +
                COL_DATA_FIM_LEITURA + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public long criarLivroNoDB(String categoria, Livro livro) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME_LIVRO, livro.getNomeLivro());
        values.put(COL_CATEGORIA, livro.getCategoria());
        values.put(COL_AUTORIA_LIVRO, livro.getAutoriaLivro());
        values.put(COL_AVALIACAO, livro.getAvaliacao());
        values.put(COL_STATUS_LEITURA, livro.isStatusLeitura() ? 1 : 0);
        values.put(COL_RESENHA, livro.getResenha());
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public List<Livro> listarLivros() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = database.rawQuery(query, null);

        List<Livro> listaLivros = converterCursorParaListaLivros(cursor);

        cursor.close();
        return listaLivros;
    }

    @SuppressLint("Range")
    public List<Livro> converterCursorParaListaLivros(Cursor cursor) {
        List<Livro> listaLivros = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                 int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String categoria = cursor.getString(cursor.getColumnIndex(COL_CATEGORIA));
                String nomeLivro = cursor.getString(cursor.getColumnIndex(COL_NOME_LIVRO));
                String autorLivro = cursor.getString(cursor.getColumnIndex(COL_AUTORIA_LIVRO));
                float avaliacao = cursor.getFloat(cursor.getColumnIndex(COL_AVALIACAO));
                boolean statusLeitura = cursor.getInt(cursor.getColumnIndex(COL_STATUS_LEITURA)) == 1;
                String resenha = cursor.getString(cursor.getColumnIndex(COL_RESENHA));

                Livro livro = new Livro(categoria, nomeLivro, autorLivro, avaliacao, statusLeitura);

                listaLivros.add(livro);
            } while (cursor.moveToNext());
        }

        return listaLivros;
    }

    public void excluirTodosLivros() {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(DB_TABLE, null, null);
        database.close();
    }

}
