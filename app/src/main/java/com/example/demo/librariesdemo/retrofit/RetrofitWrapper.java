package com.example.demo.librariesdemo.retrofit;

import com.example.demo.librariesdemo.models.CurrentWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper implements WeatherDataRepository {

    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String APP_ID = "3d999e294c31847d6359dc908859b157";

    private static RetrofitWrapper wrapper;
    private RetrofitService service;

    private RetrofitWrapper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);

    }

    public static RetrofitWrapper getInstance() {
        if (wrapper == null) {
            return wrapper = new RetrofitWrapper();
        } else {
            return wrapper;
        }
    }

    @Override
    public void getCurrentWeather(final String city, final CityListener cityListener) {
        Call<CurrentWeather> call = service.getCurrentWeather(city, APP_ID);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    cityListener.onCityWeatherReady(response.body());
                } else {
                    cityListener.onCityWeatherFailed();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                cityListener.onCityWeatherFailed();
            }
        });
    }
}
