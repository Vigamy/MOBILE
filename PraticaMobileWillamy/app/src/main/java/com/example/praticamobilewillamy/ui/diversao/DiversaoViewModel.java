package com.example.praticamobilewillamy.ui.diversao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiversaoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DiversaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}