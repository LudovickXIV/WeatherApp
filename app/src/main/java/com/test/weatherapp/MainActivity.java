package com.test.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.test.weatherapp.pojo.location.Location;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.weatherapp.pojo.Period;
import com.test.weatherapp.pojo.Weather;

import java.util.Calendar;
import java.util.List;

import static com.test.weatherapp.MapsActivity.KEY_RETURN_LAT;
import static com.test.weatherapp.MapsActivity.KEY_RETURN_LONT;

public class MainActivity extends AppCompatActivity implements DayChangeListener, OnDataPassLocation {

    public static final String KEY_LAT = "123ea969";
    public static final String KEY_LONG = "df0992";
    public static final int REQUEST_CODE = 1234;


    private ActionBar actionbar;
    private Weather mWeather;
    private WeatherPresenter presenter;
    private MyLocationRequest request;
    private SelectedForecastFragment screenFragment;
    private WeekListFragment listfragment;

    private Calendar current;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (request.isNetworkConnected()) {
            if (id == android.R.id.home) {
                if (mWeather != null) {
                    Intent intent = new Intent(this, MapsActivity.class);
                    intent.putExtra(KEY_LAT, mWeather.getResponse().get(0).getLoc().getLat());
                    intent.putExtra(KEY_LONG, mWeather.getResponse().get(0).getLoc().getLong());
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.wait_err), Toast.LENGTH_LONG).show();
                }
            }
            if (id == R.id.nav_icon) {
                request.getLocation();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.dayWeatherTextColor));
        setSupportActionBar(toolbar);

        current = Calendar.getInstance();
        current.getTimeInMillis();

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(null);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_location);

        request = new MyLocationRequest(this, this);
        presenter = new WeatherPresenter(this);

        /**
         * Проверка есть ли соединение
         */
        if (request.isNetworkConnected()) {
            request.getLocation();
        } else {        // Если соединение нет восстанавливаем последние данные
            Thread thread = new Thread(() -> {
                mWeather = presenter.restoreCacheForecast();    // восстановление данных
                if (mWeather == null) {     // Если данные не восстановлены выдаём тост с ошибкой
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show());
                } else { // если все ок
                    runOnUiThread(() -> {
                        try {
                            actionbar.setTitle(mWeather.getResponse().get(0).getProfile().getTz().split("/")[1]); // ставим название города, апи поддреживает только английский
                        } catch (Exception e) {
                            actionbar.setTitle(null);
                        }
                    });

                    /**
                     * Класс Period - это данные о погоде за 1 час(период)
                     * Получаем список Period для текущего выбраного дня, по умолчанию 0 элемент(сегодня)
                     * в списке listfragment
                     */
                    List<Period> per = WeatherDataSplitter.currentDay(mWeather, current);
                    screenFragment = (SelectedForecastFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                    runOnUiThread(() -> screenFragment.setList(per));
                    /**
                     * Получаем список Period для сегодняшних и всех последующих дней
                     */
                    List<Period> week = WeatherDataSplitter.weeksDay(mWeather);
                    listfragment = (WeekListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
                    if (listfragment != null) listfragment.setDayChangeListener(MainActivity.this);
                    runOnUiThread(() -> listfragment.setList(week));
                    listfragment.setDayChangeListener(MainActivity.this);
                }
            });
            thread.start();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            double lat = data.getDoubleExtra(KEY_RETURN_LAT, 0);
            double lon = data.getDoubleExtra(KEY_RETURN_LONT, 0);
            // получаем прогноз повторно для новых координат
            presenter.getForecasts(lat, lon);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Вызывается тогда когда пользователь выберает другой день в списке
     */
    @Override
    public void onDayChanged(Activity activity, Period weather) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(weather.getTimestamp() * 1000L);
        /**
         * Класс Period - это данные о погоде за 1 час(период)
         * Получаем список Period для текущего выбраного дня, по умолчанию 0 элемент(сегодня)
         * в списке listfragment
         */
        List<Period> per = WeatherDataSplitter.currentDay(mWeather, calendar);
        runOnUiThread(() -> screenFragment.setList(per));
    }

    /**
     * Вызывается при успешной загрузке при обнаружении координат
     */
    @Override
    public void onSuccess(Location location) {
        presenter.getForecasts(location.getCity().getLat(), location.getCity().getLon());
        presenter.setListener(new OnDataPass() {
            @Override
            public void onSuccess(Weather weather) {
                mWeather = weather;
                runOnUiThread(() -> {
                    try {
                        actionbar.setTitle(mWeather.getResponse().get(0).getProfile().getTz().split("/")[1]);
                    } catch (Exception e) {
                        actionbar.setTitle(null);
                    }
                });

                /**
                 * Класс Period - это данные о погоде за 1 час(период)
                 * Получаем список Period для текущего выбраного дня, по умолчанию 0 элемент(сегодня)
                 * в списке listfragment
                 */
                List<Period> per = WeatherDataSplitter.currentDay(weather, current);
                screenFragment = (SelectedForecastFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                runOnUiThread(() -> screenFragment.setList(per));

                /**
                 * Получаем список Period для сегодняшних и всех последующих дней
                 */
                List<Period> week = WeatherDataSplitter.weeksDay(weather);
                listfragment = (WeekListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
                if (listfragment != null) listfragment.setDayChangeListener(MainActivity.this);
                runOnUiThread(() -> listfragment.setList(week));
            }

            /**
             * при ошибке загрузки погоды, пытаемся загрузить из кеша
             */
            @Override
            public void onFailure(int code, Weather weather) {
                mWeather = weather;
                runOnUiThread(() -> {
                    try {
                        actionbar.setTitle(mWeather.getResponse().get(0).getProfile().getTz().split("/")[1]);
                    } catch (Exception e) {
                        actionbar.setTitle(null);
                    }
                });

                List<Period> per = WeatherDataSplitter.currentDay(weather, current);
                screenFragment = (SelectedForecastFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                runOnUiThread(() -> screenFragment.setList(per));

                List<Period> week = WeatherDataSplitter.weeksDay(weather);
                listfragment = (WeekListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
                if (listfragment != null) listfragment.setDayChangeListener(MainActivity.this);
                runOnUiThread(() -> listfragment.setList(week));
            }
        });
    }

    /**
     * вызывается при ошибке загрузки координат
     */
    @Override
    public void onFailure() {
        Thread thread = new Thread(() -> {
            mWeather = presenter.restoreCacheForecast();    // восстановление данных
            if (mWeather == null) {     // Если данные не восстановлены выдаём тост с ошибкой
                runOnUiThread(() -> Toast.makeText(MainActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show());
            } else { // если все ок
                runOnUiThread(() -> {
                    try {
                        actionbar.setTitle(mWeather.getResponse().get(0).getProfile().getTz().split("/")[1]); // ставим название города, апи поддреживает только английский
                    } catch (Exception e) {
                        actionbar.setTitle(null);
                    }
                });

                /**
                 * Класс Period - это данные о погоде за 1 час(период)
                 * Получаем список Period для текущего выбраного дня, по умолчанию 0 элемент(сегодня)
                 * в списке listfragment
                 */
                List<Period> per = WeatherDataSplitter.currentDay(mWeather, current);
                screenFragment = (SelectedForecastFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                runOnUiThread(() -> screenFragment.setList(per));
                /**
                 * Получаем список Period для сегодняшних и всех последующих дней
                 */
                List<Period> week = WeatherDataSplitter.weeksDay(mWeather);
                listfragment = (WeekListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
                if (listfragment != null) listfragment.setDayChangeListener(MainActivity.this);
                runOnUiThread(() -> listfragment.setList(week));
                listfragment.setDayChangeListener(MainActivity.this);
            }
        });
        thread.start();
    }
}
