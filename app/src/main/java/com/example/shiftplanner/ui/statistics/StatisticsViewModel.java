package com.example.shiftplanner.ui.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StatisticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StatisticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Statistics Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}