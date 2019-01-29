
package com.test.weatherapp.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Period {

    @SerializedName("timestamp")
    @Expose
    private long timestamp;
    @SerializedName("validTime")
    @Expose
    private String validTime;
    @SerializedName("dateTimeISO")
    @Expose
    private String dateTimeISO;
    @SerializedName("maxTempC")
    @Expose
    private int maxTempC;
    @SerializedName("maxTempF")
    @Expose
    private int maxTempF;
    @SerializedName("minTempC")
    @Expose
    private int minTempC;
    @SerializedName("minTempF")
    @Expose
    private int minTempF;
    @SerializedName("avgTempC")
    @Expose
    private int avgTempC;
    @SerializedName("avgTempF")
    @Expose
    private int avgTempF;
    @SerializedName("tempC")
    @Expose
    private int tempC;
    @SerializedName("tempF")
    @Expose
    private int tempF;
    @SerializedName("pop")
    @Expose
    private int pop;
    @SerializedName("precipMM")
    @Expose
    private double precipMM;
    @SerializedName("precipIN")
    @Expose
    private double precipIN;
    @SerializedName("iceaccum")
    @Expose
    private Object iceaccum;
    @SerializedName("iceaccumMM")
    @Expose
    private Object iceaccumMM;
    @SerializedName("iceaccumIN")
    @Expose
    private Object iceaccumIN;
    @SerializedName("maxHumidity")
    @Expose
    private int maxHumidity;
    @SerializedName("minHumidity")
    @Expose
    private int minHumidity;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("uvi")
    @Expose
    private int uvi;
    @SerializedName("pressureMB")
    @Expose
    private int pressureMB;
    @SerializedName("pressureIN")
    @Expose
    private double pressureIN;
    @SerializedName("sky")
    @Expose
    private int sky;
    @SerializedName("snowCM")
    @Expose
    private double snowCM;
    @SerializedName("snowIN")
    @Expose
    private double snowIN;
    @SerializedName("feelslikeC")
    @Expose
    private int feelslikeC;
    @SerializedName("feelslikeF")
    @Expose
    private int feelslikeF;
    @SerializedName("minFeelslikeC")
    @Expose
    private int minFeelslikeC;
    @SerializedName("minFeelslikeF")
    @Expose
    private int minFeelslikeF;
    @SerializedName("maxFeelslikeC")
    @Expose
    private int maxFeelslikeC;
    @SerializedName("maxFeelslikeF")
    @Expose
    private int maxFeelslikeF;
    @SerializedName("avgFeelslikeC")
    @Expose
    private int avgFeelslikeC;
    @SerializedName("avgFeelslikeF")
    @Expose
    private int avgFeelslikeF;
    @SerializedName("dewpointC")
    @Expose
    private int dewpointC;
    @SerializedName("dewpointF")
    @Expose
    private int dewpointF;
    @SerializedName("maxDewpointC")
    @Expose
    private int maxDewpointC;
    @SerializedName("maxDewpointF")
    @Expose
    private int maxDewpointF;
    @SerializedName("minDewpointC")
    @Expose
    private int minDewpointC;
    @SerializedName("minDewpointF")
    @Expose
    private int minDewpointF;
    @SerializedName("avgDewpointC")
    @Expose
    private int avgDewpointC;
    @SerializedName("avgDewpointF")
    @Expose
    private int avgDewpointF;
    @SerializedName("windDirDEG")
    @Expose
    private int windDirDEG;
    @SerializedName("windDir")
    @Expose
    private String windDir;
    @SerializedName("windDirMaxDEG")
    @Expose
    private int windDirMaxDEG;
    @SerializedName("windDirMax")
    @Expose
    private String windDirMax;
    @SerializedName("windDirMinDEG")
    @Expose
    private int windDirMinDEG;
    @SerializedName("windDirMin")
    @Expose
    private String windDirMin;
    @SerializedName("windGustKTS")
    @Expose
    private int windGustKTS;
    @SerializedName("windGustKPH")
    @Expose
    private int windGustKPH;
    @SerializedName("windGustMPH")
    @Expose
    private int windGustMPH;
    @SerializedName("windSpeedKTS")
    @Expose
    private int windSpeedKTS;
    @SerializedName("windSpeedKPH")
    @Expose
    private int windSpeedKPH;
    @SerializedName("windSpeedMPH")
    @Expose
    private int windSpeedMPH;
    @SerializedName("windSpeedMaxKTS")
    @Expose
    private int windSpeedMaxKTS;
    @SerializedName("windSpeedMaxKPH")
    @Expose
    private int windSpeedMaxKPH;
    @SerializedName("windSpeedMaxMPH")
    @Expose
    private int windSpeedMaxMPH;
    @SerializedName("windSpeedMinKTS")
    @Expose
    private int windSpeedMinKTS;
    @SerializedName("windSpeedMinKPH")
    @Expose
    private int windSpeedMinKPH;
    @SerializedName("windSpeedMinMPH")
    @Expose
    private int windSpeedMinMPH;
    @SerializedName("windDir80mDEG")
    @Expose
    private int windDir80mDEG;
    @SerializedName("windDir80m")
    @Expose
    private String windDir80m;
    @SerializedName("windDirMax80mDEG")
    @Expose
    private int windDirMax80mDEG;
    @SerializedName("windDirMax80m")
    @Expose
    private String windDirMax80m;
    @SerializedName("windDirMin80mDEG")
    @Expose
    private int windDirMin80mDEG;
    @SerializedName("windDirMin80m")
    @Expose
    private String windDirMin80m;
    @SerializedName("windGust80mKTS")
    @Expose
    private int windGust80mKTS;
    @SerializedName("windGust80mKPH")
    @Expose
    private int windGust80mKPH;
    @SerializedName("windGust80mMPH")
    @Expose
    private int windGust80mMPH;
    @SerializedName("windSpeed80mKTS")
    @Expose
    private int windSpeed80mKTS;
    @SerializedName("windSpeed80mKPH")
    @Expose
    private int windSpeed80mKPH;
    @SerializedName("windSpeed80mMPH")
    @Expose
    private int windSpeed80mMPH;
    @SerializedName("windSpeedMax80mKTS")
    @Expose
    private int windSpeedMax80mKTS;
    @SerializedName("windSpeedMax80mKPH")
    @Expose
    private int windSpeedMax80mKPH;
    @SerializedName("windSpeedMax80mMPH")
    @Expose
    private int windSpeedMax80mMPH;
    @SerializedName("windSpeedMin80mKTS")
    @Expose
    private int windSpeedMin80mKTS;
    @SerializedName("windSpeedMin80mKPH")
    @Expose
    private int windSpeedMin80mKPH;
    @SerializedName("windSpeedMin80mMPH")
    @Expose
    private int windSpeedMin80mMPH;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("weatherCoded")
    @Expose
    private List<WeatherCoded> weatherCoded = new ArrayList<WeatherCoded>();

    @SerializedName("weatherPrimary")
    @Expose
    private String weatherPrimary;

    @SerializedName("weatherPrimaryCoded")
    @Expose
    private String weatherPrimaryCode;

    @SerializedName("cloudsCoded")
    @Expose
    private String cloudsCoded;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("isDay")
    @Expose
    private boolean isDay;

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setWeatherPrimary(String weatherPrimary) {
        this.weatherPrimary = weatherPrimary;
    }

    public void setWeatherPrimaryCode(String weatherPrimaryCode) {
        this.weatherPrimaryCode = weatherPrimaryCode;
    }

    public void setCloudsCoded(String cloudsCoded) {
        this.cloudsCoded = cloudsCoded;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public String getWeatherPrimary() {
        return weatherPrimary;
    }

    public String getWeatherPrimaryCode() {
        return weatherPrimaryCode;
    }

    public String getCloudsCoded() {
        return cloudsCoded;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isDay() {
        return isDay;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getDateTimeISO() {
        return dateTimeISO;
    }

    public void setDateTimeISO(String dateTimeISO) {
        this.dateTimeISO = dateTimeISO;
    }

    public int getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(int maxTempC) {
        this.maxTempC = maxTempC;
    }

    public int getMaxTempF() {
        return maxTempF;
    }

    public void setMaxTempF(int maxTempF) {
        this.maxTempF = maxTempF;
    }

    public int getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(int minTempC) {
        this.minTempC = minTempC;
    }

    public int getMinTempF() {
        return minTempF;
    }

    public void setMinTempF(int minTempF) {
        this.minTempF = minTempF;
    }

    public int getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(int avgTempC) {
        this.avgTempC = avgTempC;
    }

    public int getAvgTempF() {
        return avgTempF;
    }

    public void setAvgTempF(int avgTempF) {
        this.avgTempF = avgTempF;
    }

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public double getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(double precipMM) {
        this.precipMM = precipMM;
    }

    public double getPrecipIN() {
        return precipIN;
    }

    public void setPrecipIN(double precipIN) {
        this.precipIN = precipIN;
    }

    public Object getIceaccum() {
        return iceaccum;
    }

    public void setIceaccum(Object iceaccum) {
        this.iceaccum = iceaccum;
    }

    public Object getIceaccumMM() {
        return iceaccumMM;
    }

    public void setIceaccumMM(Object iceaccumMM) {
        this.iceaccumMM = iceaccumMM;
    }

    public Object getIceaccumIN() {
        return iceaccumIN;
    }

    public void setIceaccumIN(Object iceaccumIN) {
        this.iceaccumIN = iceaccumIN;
    }

    public int getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(int maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(int minHumidity) {
        this.minHumidity = minHumidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getUvi() {
        return uvi;
    }

    public void setUvi(int uvi) {
        this.uvi = uvi;
    }

    public int getPressureMB() {
        return pressureMB;
    }

    public void setPressureMB(int pressureMB) {
        this.pressureMB = pressureMB;
    }

    public double getPressureIN() {
        return pressureIN;
    }

    public void setPressureIN(double pressureIN) {
        this.pressureIN = pressureIN;
    }

    public int getSky() {
        return sky;
    }

    public void setSky(int sky) {
        this.sky = sky;
    }

    public double getSnowCM() {
        return snowCM;
    }

    public void setSnowCM(int snowCM) {
        this.snowCM = snowCM;
    }

    public double getSnowIN() {
        return snowIN;
    }

    public void setSnowIN(double snowIN) {
        this.snowIN = snowIN;
    }

    public int getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(int feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public int getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(int feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public int getMinFeelslikeC() {
        return minFeelslikeC;
    }

    public void setMinFeelslikeC(int minFeelslikeC) {
        this.minFeelslikeC = minFeelslikeC;
    }

    public int getMinFeelslikeF() {
        return minFeelslikeF;
    }

    public void setMinFeelslikeF(int minFeelslikeF) {
        this.minFeelslikeF = minFeelslikeF;
    }

    public int getMaxFeelslikeC() {
        return maxFeelslikeC;
    }

    public void setMaxFeelslikeC(int maxFeelslikeC) {
        this.maxFeelslikeC = maxFeelslikeC;
    }

    public int getMaxFeelslikeF() {
        return maxFeelslikeF;
    }

    public void setMaxFeelslikeF(int maxFeelslikeF) {
        this.maxFeelslikeF = maxFeelslikeF;
    }

    public int getAvgFeelslikeC() {
        return avgFeelslikeC;
    }

    public void setAvgFeelslikeC(int avgFeelslikeC) {
        this.avgFeelslikeC = avgFeelslikeC;
    }

    public int getAvgFeelslikeF() {
        return avgFeelslikeF;
    }

    public void setAvgFeelslikeF(int avgFeelslikeF) {
        this.avgFeelslikeF = avgFeelslikeF;
    }

    public int getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(int dewpointC) {
        this.dewpointC = dewpointC;
    }

    public int getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(int dewpointF) {
        this.dewpointF = dewpointF;
    }

    public int getMaxDewpointC() {
        return maxDewpointC;
    }

    public void setMaxDewpointC(int maxDewpointC) {
        this.maxDewpointC = maxDewpointC;
    }

    public int getMaxDewpointF() {
        return maxDewpointF;
    }

    public void setMaxDewpointF(int maxDewpointF) {
        this.maxDewpointF = maxDewpointF;
    }

    public int getMinDewpointC() {
        return minDewpointC;
    }

    public void setMinDewpointC(int minDewpointC) {
        this.minDewpointC = minDewpointC;
    }

    public int getMinDewpointF() {
        return minDewpointF;
    }

    public void setMinDewpointF(int minDewpointF) {
        this.minDewpointF = minDewpointF;
    }

    public int getAvgDewpointC() {
        return avgDewpointC;
    }

    public void setAvgDewpointC(int avgDewpointC) {
        this.avgDewpointC = avgDewpointC;
    }

    public int getAvgDewpointF() {
        return avgDewpointF;
    }

    public void setAvgDewpointF(int avgDewpointF) {
        this.avgDewpointF = avgDewpointF;
    }

    public int getWindDirDEG() {
        return windDirDEG;
    }

    public void setWindDirDEG(int windDirDEG) {
        this.windDirDEG = windDirDEG;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public int getWindDirMaxDEG() {
        return windDirMaxDEG;
    }

    public void setWindDirMaxDEG(int windDirMaxDEG) {
        this.windDirMaxDEG = windDirMaxDEG;
    }

    public String getWindDirMax() {
        return windDirMax;
    }

    public void setWindDirMax(String windDirMax) {
        this.windDirMax = windDirMax;
    }

    public int getWindDirMinDEG() {
        return windDirMinDEG;
    }

    public void setWindDirMinDEG(int windDirMinDEG) {
        this.windDirMinDEG = windDirMinDEG;
    }

    public String getWindDirMin() {
        return windDirMin;
    }

    public void setWindDirMin(String windDirMin) {
        this.windDirMin = windDirMin;
    }

    public int getWindGustKTS() {
        return windGustKTS;
    }

    public void setWindGustKTS(int windGustKTS) {
        this.windGustKTS = windGustKTS;
    }

    public int getWindGustKPH() {
        return windGustKPH;
    }

    public void setWindGustKPH(int windGustKPH) {
        this.windGustKPH = windGustKPH;
    }

    public int getWindGustMPH() {
        return windGustMPH;
    }

    public void setWindGustMPH(int windGustMPH) {
        this.windGustMPH = windGustMPH;
    }

    public int getWindSpeedKTS() {
        return windSpeedKTS;
    }

    public void setWindSpeedKTS(int windSpeedKTS) {
        this.windSpeedKTS = windSpeedKTS;
    }

    public int getWindSpeedKPH() {
        return windSpeedKPH;
    }

    public void setWindSpeedKPH(int windSpeedKPH) {
        this.windSpeedKPH = windSpeedKPH;
    }

    public int getWindSpeedMPH() {
        return windSpeedMPH;
    }

    public void setWindSpeedMPH(int windSpeedMPH) {
        this.windSpeedMPH = windSpeedMPH;
    }

    public int getWindSpeedMaxKTS() {
        return windSpeedMaxKTS;
    }

    public void setWindSpeedMaxKTS(int windSpeedMaxKTS) {
        this.windSpeedMaxKTS = windSpeedMaxKTS;
    }

    public int getWindSpeedMaxKPH() {
        return windSpeedMaxKPH;
    }

    public void setWindSpeedMaxKPH(int windSpeedMaxKPH) {
        this.windSpeedMaxKPH = windSpeedMaxKPH;
    }

    public int getWindSpeedMaxMPH() {
        return windSpeedMaxMPH;
    }

    public void setWindSpeedMaxMPH(int windSpeedMaxMPH) {
        this.windSpeedMaxMPH = windSpeedMaxMPH;
    }

    public int getWindSpeedMinKTS() {
        return windSpeedMinKTS;
    }

    public void setWindSpeedMinKTS(int windSpeedMinKTS) {
        this.windSpeedMinKTS = windSpeedMinKTS;
    }

    public int getWindSpeedMinKPH() {
        return windSpeedMinKPH;
    }

    public void setWindSpeedMinKPH(int windSpeedMinKPH) {
        this.windSpeedMinKPH = windSpeedMinKPH;
    }

    public int getWindSpeedMinMPH() {
        return windSpeedMinMPH;
    }

    public void setWindSpeedMinMPH(int windSpeedMinMPH) {
        this.windSpeedMinMPH = windSpeedMinMPH;
    }

    public int getWindDir80mDEG() {
        return windDir80mDEG;
    }

    public void setWindDir80mDEG(int windDir80mDEG) {
        this.windDir80mDEG = windDir80mDEG;
    }

    public String getWindDir80m() {
        return windDir80m;
    }

    public void setWindDir80m(String windDir80m) {
        this.windDir80m = windDir80m;
    }

    public int getWindDirMax80mDEG() {
        return windDirMax80mDEG;
    }

    public void setWindDirMax80mDEG(int windDirMax80mDEG) {
        this.windDirMax80mDEG = windDirMax80mDEG;
    }

    public String getWindDirMax80m() {
        return windDirMax80m;
    }

    public void setWindDirMax80m(String windDirMax80m) {
        this.windDirMax80m = windDirMax80m;
    }

    public int getWindDirMin80mDEG() {
        return windDirMin80mDEG;
    }

    public void setWindDirMin80mDEG(int windDirMin80mDEG) {
        this.windDirMin80mDEG = windDirMin80mDEG;
    }

    public String getWindDirMin80m() {
        return windDirMin80m;
    }

    public void setWindDirMin80m(String windDirMin80m) {
        this.windDirMin80m = windDirMin80m;
    }

    public int getWindGust80mKTS() {
        return windGust80mKTS;
    }

    public void setWindGust80mKTS(int windGust80mKTS) {
        this.windGust80mKTS = windGust80mKTS;
    }

    public int getWindGust80mKPH() {
        return windGust80mKPH;
    }

    public void setWindGust80mKPH(int windGust80mKPH) {
        this.windGust80mKPH = windGust80mKPH;
    }

    public int getWindGust80mMPH() {
        return windGust80mMPH;
    }

    public void setWindGust80mMPH(int windGust80mMPH) {
        this.windGust80mMPH = windGust80mMPH;
    }

    public int getWindSpeed80mKTS() {
        return windSpeed80mKTS;
    }

    public void setWindSpeed80mKTS(int windSpeed80mKTS) {
        this.windSpeed80mKTS = windSpeed80mKTS;
    }

    public int getWindSpeed80mKPH() {
        return windSpeed80mKPH;
    }

    public void setWindSpeed80mKPH(int windSpeed80mKPH) {
        this.windSpeed80mKPH = windSpeed80mKPH;
    }

    public int getWindSpeed80mMPH() {
        return windSpeed80mMPH;
    }

    public void setWindSpeed80mMPH(int windSpeed80mMPH) {
        this.windSpeed80mMPH = windSpeed80mMPH;
    }

    public int getWindSpeedMax80mKTS() {
        return windSpeedMax80mKTS;
    }

    public void setWindSpeedMax80mKTS(int windSpeedMax80mKTS) {
        this.windSpeedMax80mKTS = windSpeedMax80mKTS;
    }

    public int getWindSpeedMax80mKPH() {
        return windSpeedMax80mKPH;
    }

    public void setWindSpeedMax80mKPH(int windSpeedMax80mKPH) {
        this.windSpeedMax80mKPH = windSpeedMax80mKPH;
    }

    public int getWindSpeedMax80mMPH() {
        return windSpeedMax80mMPH;
    }

    public void setWindSpeedMax80mMPH(int windSpeedMax80mMPH) {
        this.windSpeedMax80mMPH = windSpeedMax80mMPH;
    }

    public int getWindSpeedMin80mKTS() {
        return windSpeedMin80mKTS;
    }

    public void setWindSpeedMin80mKTS(int windSpeedMin80mKTS) {
        this.windSpeedMin80mKTS = windSpeedMin80mKTS;
    }

    public int getWindSpeedMin80mKPH() {
        return windSpeedMin80mKPH;
    }

    public void setWindSpeedMin80mKPH(int windSpeedMin80mKPH) {
        this.windSpeedMin80mKPH = windSpeedMin80mKPH;
    }

    public int getWindSpeedMin80mMPH() {
        return windSpeedMin80mMPH;
    }

    public void setWindSpeedMin80mMPH(int windSpeedMin80mMPH) {
        this.windSpeedMin80mMPH = windSpeedMin80mMPH;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public List<WeatherCoded> getWeatherCoded() {
        return weatherCoded;
    }

    public void setWeatherCoded(List<WeatherCoded> weatherCoded) {
        this.weatherCoded = weatherCoded;
    }
}