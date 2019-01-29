
package com.test.weatherapp.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("lon")
    @Expose
    private Integer lon;
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
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("area")
    @Expose
    private Integer area;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("capital_id")
    @Expose
    private Integer capitalId;
    @SerializedName("capital_ru")
    @Expose
    private String capitalRu;
    @SerializedName("capital_en")
    @Expose
    private String capitalEn;
    @SerializedName("cur_code")
    @Expose
    private String curCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("neighbours")
    @Expose
    private String neighbours;
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

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    public String getCapitalRu() {
        return capitalRu;
    }

    public void setCapitalRu(String capitalRu) {
        this.capitalRu = capitalRu;
    }

    public String getCapitalEn() {
        return capitalEn;
    }

    public void setCapitalEn(String capitalEn) {
        this.capitalEn = capitalEn;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
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
