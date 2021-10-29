package com.e2group.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.e2group.weatherapp.model.WeatherModel;
import com.e2group.weatherapp.network.APIClient;
import com.e2group.weatherapp.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView search;
    private TextView tempText, descText, humidity;
    private EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidity = findViewById(R.id.humidityText);
        textField = findViewById(R.id.textField);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherData(textField.getText().toString().trim());
            }
        });
    }


    private void getWeatherData(String name) {

        APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);

        Call<WeatherModel> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

                tempText.setText("Temp" + " " + response.body().getMain().getTemp() + " C");
                descText.setText("Feels Like" + " " + response.body().getMain().getFeels_like());
                humidity.setText("Humidity" + " " + response.body().getMain().getHumidity());


            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }

        });
    }
}