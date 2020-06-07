package com.example.taxiapp.Utils.taxiapp.Services;

import com.example.taxiapp.Utils.taxiapp.RequestBody.ClientData;
import com.example.taxiapp.Utils.taxiapp.RequestBody.UserCredentials;
import com.example.taxiapp.Utils.taxiapp.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/login")
    Call<ResponseLogin> login(@Body UserCredentials credential);

    @POST("auth/individualClient")
    Call<Void> register(@Body ClientData data);

}


