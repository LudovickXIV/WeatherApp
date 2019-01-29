package com.test.weatherapp;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;
import com.test.weatherapp.pojo.Weather;
import com.test.weatherapp.pojo.location.Location;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Класс в котором мы делаем запрос
 */
public class WeatherPresenter {

    public static final String KEY_FORECAST_CACHE = "0452FoCaKC";
    public static final String KEY_CACHE = "CEJ78941";

    private Context context;

    private OnDataPass listener;

    public WeatherPresenter(Context context){
        this.context = context;
    }

    public void setListener(OnDataPass listener) {
        this.listener = listener;
    }

    /**
     * Запрос для получения прогноза
     * @param lan широта
     * @param lon долгота
     */
    public void getForecasts(double lan, double lon){
        String lanLon = lan + "," + lon;
        Thread thread = new Thread(() -> {
            try {
                Retrofit builder = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(context.getResources().getString(R.string.base_url))
                        .build();

                WeatherResponse client = builder.create(WeatherResponse.class);
                Call<Weather> call = client.getForecasts(lanLon, "1h", 999,
                        context.getResources().getString(R.string.aeris_client_id),
                        context.getResources().getString(R.string.aeris_client_secret));

                Response<Weather> response = call.execute();
                if (response.isSuccessful()) {
                    Weather weather = response.body();
                    cacheForecasts(weather);
                    listener.onSuccess(weather);
                } else {
                    listener.onFailure(response.code(), restoreCacheForecast());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * метод для кеширования прогноза погоды, вызывается при каждом успешном подключении
     * @param weather
     */
    private void cacheForecasts(Weather weather){
        SharedPreferences preferences = context.getSharedPreferences(KEY_FORECAST_CACHE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String forecast = gson.toJson(weather);
        editor.putString(KEY_CACHE, forecast);
        editor.apply();
    }

    /**
     * Метод для восстановления данных из кеша
     * @return
     */
    public Weather restoreCacheForecast(){
        Weather weather;
        SharedPreferences preferences = context.getSharedPreferences(KEY_FORECAST_CACHE, MODE_PRIVATE);
        Gson gson = new Gson();
        String current = preferences.getString(KEY_CACHE, null);
        weather = gson.fromJson(current, Weather.class);
        return weather;
    }
}
