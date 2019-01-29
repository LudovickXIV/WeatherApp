package com.test.weatherapp;

import com.test.weatherapp.pojo.location.Location;

/**
 * Интерфейс загрузки данных о текущей локации
 */
public interface OnDataPassLocation {
    void onSuccess(Location location);
    void onFailure();
}
