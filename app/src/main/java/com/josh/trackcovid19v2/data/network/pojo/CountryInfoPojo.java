package com.josh.trackcovid19v2.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfoPojo {

    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("iso2")
    @Expose
    private String iso2;
    @SerializedName("iso3")
    @Expose
    private String iso3;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("llong")
    @Expose
    private String llong;
    @SerializedName("flag")
    @Expose
    private String flag;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLlong() {
        return llong;
    }

    public void setLlong(String llong) {
        this.llong = llong;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}