package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.States;

import java.util.List;

@Dao
public abstract class StateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(List<States> states);

    @Query("DELETE FROM states")
    abstract void deleteAll();

    @Query("Select * FROM states ORDER BY state ASC")
    public abstract LiveData<List<States>> getStatesList();

    @Transaction
    public void updateAll(List<States> states) {
        deleteAll();
        bulkInsert(states);
    }
}
