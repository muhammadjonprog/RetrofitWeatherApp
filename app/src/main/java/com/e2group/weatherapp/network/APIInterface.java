package com.e2group.weatherapp.network;

import com.e2group.weatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("weather?appid=82bb99af73d63f4c0ef642fed1c0f2a5&units=metric")
    Call<WeatherModel> getWeatherData(@Query("q") String name);
}
