package com.test.weatherapp;

import android.app.Activity;

/**
 * для работы со списком "Сохраненные места"
 * @param <T> принимает активити
 */
public interface OnPlaceSelected <T extends Activity> {
    void onSelected(T activity, MyPlace place);
}
