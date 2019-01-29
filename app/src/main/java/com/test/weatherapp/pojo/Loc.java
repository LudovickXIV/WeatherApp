
package com.test.weatherapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loc {

    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("lat")
    @Expose
    private Double lat;

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
