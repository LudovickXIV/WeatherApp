package com.test.weatherapp;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Объект для класса Realm
 */
public class MyPlace extends RealmObject {


    private String id;
    private String country = "Unknown";
    private String city = "Unknown";
    private String state = "Unknown";

    private double latitude = 0.0;
    private double longitude = 0.0;

    public String getId() {
        return id;
    }

    /**
     * id генерируется UUID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
