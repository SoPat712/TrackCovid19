package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.Countries;

import java.util.List;


@Dao
public abstract class CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(List<Countries> countries);

    @Query("DELETE FROM countries")
    abstract void deleteAll();

    @Query("Select * FROM countries ORDER BY country ASC")
    public abstract LiveData<List<Countries>> getCountriesList();

    @Transaction
    public void updateAll(List<Countries> countries) {
        deleteAll();
        bulkInsert(countries);
    }

}
