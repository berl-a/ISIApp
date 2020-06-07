package com.example.taxiapp.Services;

import com.example.taxiapp.model.Cost;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RideService {

    @GET("ride/getCost")
    Call<Cost> cost(@Query("distance") double distance);

}


