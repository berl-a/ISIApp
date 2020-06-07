package com.example.taxiapp.Services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    public Retrofit getRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient();
        return new Retrofit.Builder()
                .baseUrl("http://isiapp-env.eba-nhbvhbzn.us-east-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }


}
