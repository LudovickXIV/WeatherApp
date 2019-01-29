
package com.test.weatherapp.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("request")
    @Expose
    private Integer request;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}
