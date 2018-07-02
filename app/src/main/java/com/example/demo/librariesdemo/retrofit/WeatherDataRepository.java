package com.example.demo.librariesdemo.retrofit;

import com.example.demo.librariesdemo.models.CurrentWeather;

public interface WeatherDataRepository {

    void getCurrentWeather(String city, CityListener cityListener);

    interface CityListener {

        void onCityWeatherReady(CurrentWeather currentWeather);

        void onCityWeatherFailed();
    }
}
