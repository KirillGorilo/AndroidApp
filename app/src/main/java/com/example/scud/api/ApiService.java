package com.example.scud.api;

import com.example.scud.model.AuthRequest;
import com.example.scud.model.DataModel;
import com.example.scud.model.UsersList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    @POST("api/login/")
    Call<DataModel> authenticate(@Body AuthRequest authRequest);

    @POST("api/register/")
    Call<DataModel> registration(@Body AuthRequest authRequest);

    @GET("api/users")
    Call<List<UsersList>> getUsers();

    @GET("api/generate_qr/{user_id}/")
    Call<DataModel> getQrCode(@Path("user_id") int userId);
}
