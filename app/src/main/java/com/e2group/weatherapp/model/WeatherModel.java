package com.e2group.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("main")
    private Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
