package com.test.weatherapp;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.weatherapp.adapters.ScreenDayAdapter;
import com.test.weatherapp.pojo.Period;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedForecastFragment extends Fragment {

    @BindView(R.id.screenWeather)
    ImageView screenWeather;
    @BindView(R.id.screenTemp)
    TextView temp;
    @BindView(R.id.screenHumidity)
    TextView humidity;
    @BindView(R.id.screenWind)
    TextView wind;
    @BindView(R.id.screenCalendarDay)
    TextView calendarDay;
    @BindView(R.id.dayWeatherRecycler)
    RecyclerView recycler;
    @BindView(R.id.windDirection)
    ImageView direction;

    private List<Period> periods;

    private RecyclerView.LayoutManager manager;
    private ScreenDayAdapter adapter;

    private String[] arr_day;
    private String[] arr_month;

    /**
     * Setter для списка Period
     * @param periods
     */
    public void setList(List<Period> periods) {
        adapter = new ScreenDayAdapter(periods);
        recycler.setAdapter(adapter);

        this.periods = periods;
        adapter.notifyDataSetChanged();

        init();
    }


    /**
     * инициализация данных о погоде
     */
    private void init(){
        // Массив дней, 0 элемент пустой, специально для работы с календарём, из ресурсов
        arr_day = getResources().getStringArray(R.array.day_of_week);
        // Массив месяцев специально для работы с календарём, из ресурсов
        arr_month = getResources().getStringArray(R.array.month_arr);

        setIconWeather(screenWeather, periods.get(0));
        String tmp = periods.get(0).getMinTempC() + "°" + "/" +
                periods.get(0).getMaxTempC() + "°";
        temp.setText(tmp);

        humidity.setText(String.valueOf(periods.get(0).getHumidity()) + "%");

        wind.setText(String.valueOf(periods.get(0).getWindSpeedKPH() * 1000 / 3600) + getResources().getString(R.string.mper_second));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(periods.get(0).getTimestamp() * 1000L);


        String cd = arr_day[calendar.get(Calendar.DAY_OF_WEEK)] + ", " + calendar.get(Calendar.DAY_OF_MONTH)+ " " + arr_month[calendar.get(Calendar.MONTH)];

        calendarDay.setText(cd);

        windDeg(periods.get(0), direction);
    }

    /**
     * Метод для утсновки направления ветра
     * @param data - Period
     * @param direction - image view
     */
    private void windDeg(Period data, ImageView direction){
        double wind = data.getWindDirDEG();
        
        if (wind > 337.5 && wind < 22.5){
            direction.setImageResource(R.drawable.icon_wind_n);
        } else if (wind > 22.5 && wind < 67.5){
            direction.setImageResource(R.drawable.icon_wind_ne);
        } else if (wind > 67.5 && wind < 112.5) {
            direction.setImageResource(R.drawable.icon_wind_e);
        } else if (wind > 112.5 && wind < 157.5) {
            direction.setImageResource(R.drawable.icon_wind_se);
        } else if (wind > 157.5 && wind < 202.5) {
            direction.setImageResource(R.drawable.icon_wind_s);
        } else if (wind > 202.5 && wind < 247.5) {
            direction.setImageResource(R.drawable.icon_wind_ws);
        } else if (wind > 247.5 && wind < 292.5) {
            direction.setImageResource(R.drawable.icon_wind_w);
        } else if (wind > 292.5 && wind < 337.5) {
            direction.setImageResource(R.drawable.icon_wind_e);
        } else direction.setImageResource(R.drawable.icon_wind_e);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_block, null);
        ButterKnife.bind(this, view);

        if (adapter != null) {
            adapter = new ScreenDayAdapter(periods);
            recycler.setAdapter(adapter);
        }
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(manager);

        return view;
    }

    /**
     * метод для установки изображения для каждой погоды
     * @param image
     * @param period
     */
    private void setIconWeather(ImageView image, Period period) {
        if (period.getCloudsCoded().contains("CL") ||
                period.getCloudsCoded().contains("FW")) {
            if (period.isDay()) {
                image.setImageResource(R.drawable.ic_white_day_bright);
            } else {
                image.setImageResource(R.drawable.ic_white_night_bright);
            }
        } else {
            if (period.isDay()) {
                image.setImageResource(R.drawable.ic_white_day_cloudy);
            } else {
                image.setImageResource(R.drawable.ic_white_night_cloudy);
            }

            try {
                if (period.getWeatherCoded() != null) {
                    if (period.getWeatherCoded().get(0).getWx().contains("T")) {
                        if (period.isDay()) {
                            image.setImageResource(R.drawable.ic_white_day_thunder);
                        } else {
                            image.setImageResource(R.drawable.ic_white_night_thunder);
                        }
                    } else if (period.getWeatherCoded().get(0).getWx().contains("R") ||
                            period.getWeatherCoded().get(0).getWx().contains("S")) {
                        if (period.isDay()) {
                            image.setImageResource(R.drawable.ic_white_day_rain);
                        } else {
                            image.setImageResource(R.drawable.ic_white_night_rain);
                        }
                    } else if (period.getWeatherCoded().get(0).getWx().contains("RS") ||
                            period.getWeatherCoded().get(0).getWx().contains("SI")) {
                        if (period.isDay()) {
                            image.setImageResource(R.drawable.ic_white_day_rain);
                        } else {
                            image.setImageResource(R.drawable.ic_white_night_rain);
                        }
                    } else if (period.getWeatherCoded().get(0).getWx().contains("RW") ||
                            period.getWeatherCoded().get(0).getWx().contains("SW")) {
                        if (period.isDay()) {
                            image.setImageResource(R.drawable.ic_white_day_shower);
                        } else {
                            image.setImageResource(R.drawable.ic_white_night_shower);
                        }
                    } else {
                        if (period.isDay()) {
                            image.setImageResource(R.drawable.ic_white_day_cloudy);
                        } else {
                            image.setImageResource(R.drawable.ic_white_night_cloudy);
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                if (period.isDay()) {
                    image.setImageResource(R.drawable.ic_white_day_cloudy);
                } else {
                    image.setImageResource(R.drawable.ic_white_night_cloudy);
                }
            }
        }
    }
}
