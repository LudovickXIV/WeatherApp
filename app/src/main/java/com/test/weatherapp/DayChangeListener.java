package com.test.weatherapp;

import android.app.Activity;

import com.test.weatherapp.pojo.Period;
import com.test.weatherapp.pojo.Weather;

public interface DayChangeListener<T extends Activity> {
    void onDayChanged(T activity, Period weather);
}
