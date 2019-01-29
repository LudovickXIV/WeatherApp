
package com.test.weatherapp.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("name_de")
    @Expose
    private String nameDe;
    @SerializedName("name_fr")
    @Expose
    private String nameFr;
    @SerializedName("name_it")
    @Expose
    private String nameIt;
    @SerializedName("name_es")
    @Expose
    private String nameEs;
    @SerializedName("name_pt")
    @Expose
    private String namePt;
    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("okato")
    @Expose
    private String okato;
    @SerializedName("auto")
    @Expose
    private String auto;
    @SerializedName("vk")
    @Expose
    private Integer vk;
    @SerializedName("utc")
    @Expose
    private Integer utc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getNameIt() {
        return nameIt;
    }

    public void setNameIt(String nameIt) {
        this.nameIt = nameIt;
    }

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nameEs) {
        this.nameEs = nameEs;
    }

    public String getNamePt() {
        return namePt;
    }

    public void setNamePt(String namePt) {
        this.namePt = namePt;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public Integer getVk() {
        return vk;
    }

    public void setVk(Integer vk) {
        this.vk = vk;
    }

    public Integer getUtc() {
        return utc;
    }

    public void setUtc(Integer utc) {
        this.utc = utc;
    }

}
