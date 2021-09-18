package com.example.shiftplanner.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FeedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Feed Fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }
}