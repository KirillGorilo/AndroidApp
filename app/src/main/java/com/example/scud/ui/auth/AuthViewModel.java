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
    }

    public LiveData<DataModel> getData() {
        loadData();
        return data;
    }

    private void loadData() {
        repository.getData(new retrofit2.Callback<DataModel>() {
            @Override
            public void onResponse(retrofit2.Call<DataModel> call, retrofit2.Response<DataModel> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }

            }

            @Override
            public void onFailure(retrofit2.Call<DataModel> call, Throwable t) {
                DataModel messageError = new DataModel();
            }
        });
    }

    public LiveData<DataModel> authenticate(String login, String password) {
        return repository.authenticate(login, password);
    }
}