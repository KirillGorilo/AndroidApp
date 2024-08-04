package com.example.scud.repository;

import android.app.Activity;
import android.widget.Toast;

import com.example.scud.MainActivity;
import com.example.scud.R;
import com.example.scud.api.ApiClient;
import com.example.scud.api.ApiService;
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

}
