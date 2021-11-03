package com.e2group.weatherapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e2group.weatherapp.model.Main;
import com.e2group.weatherapp.model.WeatherModel;
import com.e2group.weatherapp.network.APIClient;
import com.e2group.weatherapp.network.APIService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
  
    MutableLiveData<WeatherModel> listWeather = new MutableLiveData<>();

        public void makeApiCall(String cityName) {

            APIService apiService = APIClient.getRetrofit().create(APIService.class);
            Call<WeatherModel> call = apiService.getWeatherData(cityName);
            call.enqueue(new Callback<WeatherModel>() {
                @Override
                public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                    listWeather.postValue(response.body());

                }

                @Override
                public void onFailure(Call<WeatherModel> call, Throwable t) {

                }
            });

    }
    public LiveData<WeatherModel> getWeatherObserver(){
        return listWeather;
    }
}