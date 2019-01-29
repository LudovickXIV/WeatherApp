
package com.test.weatherapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("loc")
    @Expose
    private Loc loc;
    @SerializedName("interval")
    @Expose
    private String interval;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = null;
    @SerializedName("profile")
    @Expose
    private Profile profile;

    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc loc) {
        this.loc = loc;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
