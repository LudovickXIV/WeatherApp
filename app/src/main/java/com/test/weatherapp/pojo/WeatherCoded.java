
package com.test.weatherapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherCoded {

    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("wx")
    @Expose
    private String wx;
    @SerializedName("dateTimeISO")
    @Expose
    private String dateTimeISO;

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getDateTimeISO() {
        return dateTimeISO;
    }

    public void setDateTimeISO(String dateTimeISO) {
        this.dateTimeISO = dateTimeISO;
    }

}
