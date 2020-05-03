package com.josh.trackcovid19v2.ui.yourworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourworldViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourworldViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("World Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String str) {
        mText.setValue(str);
    }

}