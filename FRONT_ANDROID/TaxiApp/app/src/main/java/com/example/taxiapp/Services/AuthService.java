package com.example.taxiapp.Services;

import com.example.taxiapp.RequestBody.ClientData;
import com.example.taxiapp.RequestBody.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/login")
    Call<Void> login(@Body UserCredentials credential);

    @POST("/individualClient")
    Call<Void> register(@Body ClientData data);

}


