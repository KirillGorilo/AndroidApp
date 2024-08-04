package com.example.scud.api;

import com.example.scud.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("test")
    Call<DataModel> getField1();
}
