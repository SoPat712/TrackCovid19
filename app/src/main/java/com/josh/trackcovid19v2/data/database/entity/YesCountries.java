package com.josh.trackcovid19v2.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by evrencoskun on 1.12.2017.
 */
@Entity(tableName = "yescountries")
public class YesCountries {
    @PrimaryKey
    @NonNull
    public String country;
    public int cases;
    public int active;
    public int todayCases;
    public int deaths;
    public int todayDeaths;
    public int recovered;
    public int tests;
    public int testsPerMillion;
    public String flag;
    public int critical;
    public long updated;

    //@TypeConverters(CountryInfo.class)
    //public List<CountryInfo> countryinfo;
}

