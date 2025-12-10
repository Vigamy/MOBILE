package com.buffet;

import java.io.Serializable;

public class Crianca implements Serializable {
    private String profile_pic;
    private int id = 0;
    private String nome;
    private String responsavel;
    private String telefone;
    private String dataNascimento;
    private boolean restricao;
    private float rank;

    public Crianca(String profile_pic, int id, String nome, String responsavel, String telefone, String dataNascimento, boolean restricao, float rank) {
        this.profile_pic = profile_pic;
        this.id = id;
        this.nome = nome;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.restricao = restricao;
        this.rank = rank;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isRestricao() {
        return restricao;
    }

    public void setRestricao(boolean restricao) {
        this.restricao = restricao;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }
}
