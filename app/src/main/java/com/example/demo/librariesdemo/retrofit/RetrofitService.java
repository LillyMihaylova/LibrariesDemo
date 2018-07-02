package com.example.demo.librariesdemo.retrofit;

import com.example.demo.librariesdemo.models.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("APPID") String api);
}
