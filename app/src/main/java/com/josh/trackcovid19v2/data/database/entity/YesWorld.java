package com.josh.trackcovid19v2.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by evrencoskun on 1.12.2017.
 */
@Entity(tableName = "yesworld")
public class YesWorld {
    @PrimaryKey
    @NonNull
    public int cases;
    public int deaths;
    public int recovered;
    public long updated;
    public int active;
    public int affectedCountries;

}
