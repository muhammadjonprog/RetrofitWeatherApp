package com.e2group.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.e2group.weatherapp.model.WeatherModel;
import com.e2group.weatherapp.viewmodel.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;

    private TextView  tempText, fellsLike, tvHumidity, tvPressure;
    private Spinner spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempText = findViewById(R.id.tempText);
        fellsLike = findViewById(R.id.feelsLike);
        tvHumidity = findViewById(R.id.humidityText);
        spinnerCity = findViewById(R.id.spinnerCity);
        tvPressure = findViewById(R.id.pressure);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arrCity, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerCity.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

       viewModel.getWeatherObserver().observe(this, new Observer<WeatherModel>() {
           @Override
           public void onChanged(WeatherModel weatherModel) {
               double fa = weatherModel.getMain().getTemp();
               double c = (int) fa - 273.15 ;
               int cs = (int)c;
               tempText.setText(cs+" °C");
               fellsLike.setText("Чувствителность: "+weatherModel.getMain().getFeels_like());
               tvHumidity.setText("Влажность: "+weatherModel.getMain().getHumidity());
               tvPressure.setText("Давление: "+weatherModel.getMain().getPressure());
           }
       });

       spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               viewModel.makeApiCall(spinnerCity.getSelectedItem().toString());

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });



    }


}