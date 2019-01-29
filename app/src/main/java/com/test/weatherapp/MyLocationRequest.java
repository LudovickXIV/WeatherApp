package com.test.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;

import com.test.weatherapp.pojo.location.Location;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс для получения текущих кординат
 */
public class MyLocationRequest {

    private Context context;

    /**
     * слушатель успеха/провала загрузки координат
     */
    private OnDataPassLocation listener;


    public MyLocationRequest(Context context, OnDataPassLocation listener){
        this.context = context;
        this.listener = listener;
    }

    /**
     * Сам метод для загрузки координат через сервис
     */
    public void getLocation(){
        Thread thread = new Thread(() -> {
            try {
                Retrofit builder = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(context.getResources().getString(R.string.base_url_location))
                        .build();

                WeatherResponse client = builder.create(WeatherResponse.class);
                Call<Location> call = client.getLocation();

                Response<Location> response = call.execute();
                if (response.isSuccessful()) {
                    Location location = response.body();
                    listener.onSuccess(location);
                } else {
                    listener.onFailure();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * метод для проверки подклюючения
     * @return true если есть интернет
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
