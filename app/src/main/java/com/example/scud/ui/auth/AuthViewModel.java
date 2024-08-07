package com.example.scud.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scud.model.DataModel;
import com.example.scud.repository.DataRepository;

public class AuthViewModel extends ViewModel {
    private DataRepository repository;
    private MutableLiveData<DataModel> data;

    public AuthViewModel() {
        repository = new DataRepository();
        data = new MutableLiveData<>();
        loadData();
    }

    public LiveData<DataModel> getData() {
        loadData();
        return data;
    }

    private void loadData() {
        data = repository.getData();
    }

    public LiveData<DataModel> authenticate(String login, String password) {
        return repository.authenticate(login, password);
    }
}