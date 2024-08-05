package com.example.scud.api;

import com.example.scud.model.AuthRequest;
import com.example.scud.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @GET("test")
    Call<DataModel> getField1();

    @POST("api-token-auth/")
    Call<DataModel> authenticate(@Body AuthRequest authRequest);
}
