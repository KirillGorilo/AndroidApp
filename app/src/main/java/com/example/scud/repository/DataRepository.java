package com.example.scud.repository;

import android.app.Activity;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.scud.MainActivity;
import com.example.scud.R;
import com.example.scud.api.ApiClient;
import com.example.scud.api.ApiService;
import com.example.scud.model.AuthRequest;
import com.example.scud.model.DataModel;
import com.example.scud.ui.account.AccountFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

}
