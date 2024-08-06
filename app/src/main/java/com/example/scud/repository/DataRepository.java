package com.example.scud.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.scud.api.ApiClient;
import com.example.scud.api.ApiService;
import com.example.scud.model.AuthRequest;
import com.example.scud.model.DataModel;
import com.example.scud.model.UsersList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    ApiService apiService;

    public DataRepository() {
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }

    public void getData(Callback<DataModel> callback) {
        Call<DataModel> call = apiService.getField1();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public LiveData<DataModel> authenticate(String login, String password) {
        MutableLiveData<DataModel> data = new MutableLiveData<>();
        AuthRequest authRequest = new AuthRequest(login, password);
        apiService.authenticate(authRequest).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<UsersList>> getUsers() {
        MutableLiveData<List<UsersList>> data = new MutableLiveData<>();
        apiService.getUsers().enqueue(new Callback<List<UsersList>>() {
            @Override
            public void onResponse(Call<List<UsersList>> call, Response<List<UsersList>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<UsersList>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
