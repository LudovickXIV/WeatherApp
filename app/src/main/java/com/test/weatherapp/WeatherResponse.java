package com.test.weatherapp;

import com.test.weatherapp.pojo.Weather;
import com.test.weatherapp.pojo.location.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherResponse {

    /**
     *
     * @param filter интервалы в погоде, 1h каждый час, 3h каждые 3часа и т.д.
     * @param limit лимит возвращаемых периодов, т.е. если нужно  7 дней "интервал в погоде" * "количество дней" = "лимит"
     * @param id клиентский id
     * @param secret  клиентский key
     * @return
     */
    @GET("forecasts/{latlon}")
    Call<Weather> getForecasts(@Path("latlon") String lanlon,
                               @Query("filter") String filter, @Query("limit") int limit,
                               @Query("client_id") String id, @Query("client_secret") String secret);

    /**
     * запрос текущей локации через сайт sypexgeo.net
     * @return
     */
    @GET("json")
    Call<Location> getLocation();
}
