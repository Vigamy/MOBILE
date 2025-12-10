package com.aula.praticamobile;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Colaborador {
    private int numCracha;
    @ServerTimestamp
    private Date dataInicio;
    @ServerTimestamp
    private Date dataFim;

    public Colaborador() {

    }
    
    public Colaborador(int numCrachaCracha, Date dataInicio, Date dataFim) {
        this.numCracha = numCrachaCracha;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getNumCracha() {
        return numCracha;
    }

    public void setNumCracha(int numCracha) {
        this.numCracha = numCracha;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
