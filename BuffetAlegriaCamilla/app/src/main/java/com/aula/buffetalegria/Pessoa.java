package com.aula.buffetalegria;

import java.io.Serializable;
import java.util.Date;

public class Pessoa implements Serializable {
    private long id = 0;
    private String nome;
    private String responsavel;
    private String fone;
    private Date dtNasc;
    private boolean restricoes;
    private String imagem;
    private float rank;

    public Pessoa() {
    }
    public Pessoa(String nome, String responsavel, String fone, Date dtNasc, boolean restricoes, String imagem, float rank) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.fone = fone;
        this.dtNasc = dtNasc;
        this.restricoes = restricoes;
        this.imagem = imagem;
        this.rank = rank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public boolean isRestricoes() {
        return restricoes;
    }

    public void setRestricoes(boolean restricoes) {
        this.restricoes = restricoes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }
}
