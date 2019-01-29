package com.test.weatherapp;

import android.util.Log;

import com.test.weatherapp.pojo.Period;
import com.test.weatherapp.pojo.Response;
import com.test.weatherapp.pojo.Weather;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Класс для нарезки данных о погоде
 */
public class WeatherDataSplitter {

    /**
     * Метод для определения погоды на сегодня
     * @param weather получаем объект погода
     * @param calendar получаем календарь по какому мы проводим сравнения
     * @return
     */
    public static List<Period> currentDay(Weather weather, Calendar calendar){
        List<Period> response = weather.getResponse().get(0).getPeriods();
        List<Period> funcResponse = new ArrayList<>();
        Calendar current = calendar;
        Calendar periodCal = Calendar.getInstance();
        for(Period period : response) {
            periodCal.setTimeInMillis(period.getTimestamp() * 1000L);
            if ((current.get(Calendar.DAY_OF_MONTH) ==
                    periodCal.get(Calendar.DAY_OF_MONTH) &&
                    (current.get(Calendar.MONTH) ==
                                    periodCal.get(Calendar.MONTH)))) {
                funcResponse.add(period);
            }
        }
        if (funcResponse.size() < 6) { // Если в списке осталось меньше периодов чем 6, догружаем ещё со следующего дня
            for (int i = funcResponse.size(); i < 6; i++) {
                funcResponse.add(response.get(i + 1));
            }
        }
        return funcResponse;
    }

    /**
     * Метод для погоды на следующие дни
     * @param weather получаем объект погода
     */
    public static List<Period> weeksDay(Weather weather) {
        List<Period> response = weather.getResponse().get(0).getPeriods();
        List<Period> funcResponse = new ArrayList<>();
        Calendar current = Calendar.getInstance();
        current.getTimeInMillis();

        funcResponse.add(response.get(0));
        Calendar periodCal = Calendar.getInstance();
        Calendar dayPeriodCal = Calendar.getInstance();



        for(Period period : response) {
            periodCal.setTimeInMillis(period.getTimestamp() * 1000L);
            if (periodCal.get(Calendar.HOUR_OF_DAY) == 12) {
                if (periodCal.get(Calendar.DAY_OF_MONTH) == current.get(Calendar.DAY_OF_MONTH))continue;
                int min = period.getMinTempC();
                int max = period.getMaxTempC();
                for (Period per : response) {
                    dayPeriodCal.setTimeInMillis(per.getTimestamp() * 1000L);
                    if ((dayPeriodCal.get(Calendar.DAY_OF_MONTH)
                            == periodCal.get(Calendar.DAY_OF_MONTH))) {

                        if (min > 0) {
                            if (min > per.getMinTempC())min = per.getMinTempC();
                        }
                        if (min < 0) {
                            if (min < per.getMinTempC()) min = per.getMinTempC();
                        }
                        if (max < 0) {
                            if (max > per.getMinTempC())max = per.getMinTempC();
                        }
                        if (max > 0) {
                            if (max < per.getMinTempC())max = per.getMinTempC();
                        }
                    }
                }
                period.setMaxTempC(max);
                period.setMinTempC(min);
                funcResponse.add(period);
            }
        }
        return funcResponse;
    }
}
