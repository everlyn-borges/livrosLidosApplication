package com.pucpr.livroslidosapplication;

import java.io.Serializable;
import java.util.Date;

public class Livro implements Serializable {

    private int id;
    private String categoria;
    private String nomeLivro;
    private String autoriaLivro;
    private float avaliacao;
    private boolean statusLeitura;
    private String resenha;
    private String dataInicioLeitura;
    private String dataFimLeitura;

    public Livro(String categoria, String nomeLivro, String autoriaLivro, float avaliacao, boolean statusLeitura) {
        this.categoria = categoria;
        this.nomeLivro = nomeLivro;
        this.autoriaLivro = autoriaLivro;
        this.avaliacao = avaliacao;
        this.statusLeitura = statusLeitura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutoriaLivro() {
        return autoriaLivro;
    }

    public void setAutoriaLivro(String autoriaLivro) {
        this.autoriaLivro = autoriaLivro;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public boolean isStatusLeitura() {
        return statusLeitura;
    }

    public void setStatusLeitura(boolean statusLeitura) {
        this.statusLeitura = statusLeitura;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public String getDataInicioLeitura() {
        return dataInicioLeitura;
    }

    public void setDataInicioLeitura(String dataInicioLeitura) {
        this.dataInicioLeitura = dataInicioLeitura;
    }

    public String getDataFimLeitura() {
        return dataFimLeitura;
    }

    public void setDataFimLeitura(String dataFimLeitura) {
        this.dataFimLeitura = dataFimLeitura;
    }
}
