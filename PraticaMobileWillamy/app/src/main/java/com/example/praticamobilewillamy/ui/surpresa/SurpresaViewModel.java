package com.example.praticamobilewillamy.ui.surpresa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SurpresaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SurpresaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}