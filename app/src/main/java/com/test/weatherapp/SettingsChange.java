package com.test.weatherapp;

import android.app.Activity;

import java.io.Serializable;

/**
 * Интерфейс для установки или отмены настроек
 * @param <T>
 */
public interface SettingsChange<T extends Activity> extends Serializable {
    void onChanged(T activity);
    void onNonChanged(T activity);
}