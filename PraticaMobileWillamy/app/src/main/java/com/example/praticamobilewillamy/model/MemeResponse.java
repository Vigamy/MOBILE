package com.example.praticamobilewillamy.model;

import java.util.List;

public class MemeResponse {
    private boolean success;
    private List<Meme> data;

    public MemeResponse(boolean success, List<Meme> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Meme> getData() {
        return data;
    }

    public void setData(List<Meme> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MemeResponse{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}