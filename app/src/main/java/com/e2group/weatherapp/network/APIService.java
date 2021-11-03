package com.e2group.weatherapp.network;

import com.e2group.weatherapp.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {


    @GET("weather?&appid=2e9edc8219ad7d0e9c64aa5330938c52&")
    Call<WeatherModel> getWeatherData(@Query("q") String cityName);
}
