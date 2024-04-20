package com.mcgigglepop.myprofilebox.ui.add_password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddPasswordViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddPasswordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}