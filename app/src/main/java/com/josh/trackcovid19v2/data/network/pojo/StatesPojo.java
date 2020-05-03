package com.josh.trackcovid19v2.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatesPojo {
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("cases")
    @Expose
    public String cases;
    @SerializedName("todayCases")
    @Expose
    public String todayCases;
    @SerializedName("deaths")
    @Expose
    public String deaths;
    @SerializedName("todayDeaths")
    @Expose
    public String todayDeaths;
    @SerializedName("active")
    @Expose
    public String active;
    @SerializedName("tests")
    private String tests;
    @SerializedName("testsPerOneMillion")
    @Expose
    private String testsPerOneMillion;

    public String getState() {
        return state;
    }

    public void setCountry(String country) {
        this.state = state;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(String testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }
    //@SerializedName("group")
   // @Expose
    //public Group group;
    //@SerializedName("address")
    //@Expose
    //public Address address;


}
