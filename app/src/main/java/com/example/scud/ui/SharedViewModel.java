package com.example.scud.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scud.model.DataModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> setId = new MutableLiveData<>();

    public void setSelectedData(String id) {
        setId.setValue(id);
    }

    public LiveData<String> getSelectedData() {
        return setId;
    }
}
