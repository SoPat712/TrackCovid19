package com.josh.trackcovid19v2.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by evrencoskun on 1.12.2017.
 */
@Entity(tableName = "states")
public class States {
    @PrimaryKey
    @NonNull
    public String state;
    public int cases;
    public int todayCases;
    public int deaths;
    public int todayDeaths;
    public int active;
    public int tests;
    public int testsPerMillion;
}
