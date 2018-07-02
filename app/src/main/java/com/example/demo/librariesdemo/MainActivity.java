package com.example.demo.librariesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.librariesdemo.models.CurrentWeather;
import com.example.demo.librariesdemo.retrofit.RetrofitWrapper;
import com.example.demo.librariesdemo.retrofit.WeatherDataRepository;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CITY_NAME = "VRATSA";

    private ImageView imgCity;
    private TextView txtData;
    private Button btnGetWeather;
    private RetrofitWrapper wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrapper = RetrofitWrapper.getInstance();

        imgCity = findViewById(R.id.img_city);
        txtData = findViewById(R.id.txt_data);
        btnGetWeather = findViewById(R.id.btn_get_weather);

        btnGetWeather.setOnClickListener(this);

        String url = "http://kmeta.bg/wp-content/uploads/2014/12/24/549ac2233c591.jpg";
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.baseline_panorama_black_48)
                .error(R.drawable.baseline_panorama_black_48)
                .into(imgCity);

    }

    @Override
    public void onClick(View v) {
        wrapper.getCurrentWeather(CITY_NAME, new WeatherDataRepository.CityListener() {
            @Override
            public void onCityWeatherReady(CurrentWeather currentWeather) {
                txtData.setText(currentWeather.getName() + "\n"
                        + convertKelvinToCelsius(currentWeather.getMain().getTemp().toString()) + "°C");
            }

            @Override
            public void onCityWeatherFailed() {
                txtData.setText("Server Error");
            }
        });
    }

    private double convertKelvinToCelsius(String tempKelvin) {
        return Double.parseDouble(tempKelvin) - 273.15;
    }
}
