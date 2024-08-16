package com.example.scud.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scud.model.DataModel;
import com.example.scud.repository.DataRepository;

public class RegistrationViewModel extends ViewModel {
    private DataRepository repository;
    private MutableLiveData<DataModel> data;

    public RegistrationViewModel() {
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

    public LiveData<DataModel> registration(String username, String email, String password) {
        return repository.registration(username, email, password);
    }
}