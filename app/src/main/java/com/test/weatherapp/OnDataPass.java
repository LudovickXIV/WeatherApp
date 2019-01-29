package com.test.weatherapp;

import com.test.weatherapp.pojo.Weather;

/**
 * Интерфейс загрузки данных о погоде
 */
public interface OnDataPass {
    void onSuccess(Weather weather);
    void onFailure(int code, Weather weather);
}
