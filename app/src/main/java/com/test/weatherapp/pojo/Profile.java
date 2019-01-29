
package com.test.weatherapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("tz")
    @Expose
    private String tz;

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

}
