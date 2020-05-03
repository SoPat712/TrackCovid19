package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.YesCountries;

import java.util.List;


@Dao
public abstract class YesCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(List<YesCountries> yescountries);

    @Query("DELETE FROM yescountries")
    abstract void deleteAll();

    @Query("Select * FROM yescountries ORDER BY country ASC")
    public abstract LiveData<List<YesCountries>> getYesCountriesList();

    @Transaction
    public void updateAll(List<YesCountries> yescountries) {
        deleteAll();
        bulkInsert(yescountries);
    }
}
