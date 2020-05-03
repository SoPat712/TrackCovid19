package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.YesStates;

import java.util.List;

@Dao
public abstract class YesStateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(List<YesStates> yesstates);

    @Query("DELETE FROM yesstates")
    abstract void deleteAll();

    @Query("Select * FROM yesstates ORDER BY state ASC")
    public abstract LiveData<List<YesStates>> getYesStatesList();

    @Transaction
    public void updateAll(List<YesStates> yesstates) {
        deleteAll();
        bulkInsert(yesstates);
    }
}
