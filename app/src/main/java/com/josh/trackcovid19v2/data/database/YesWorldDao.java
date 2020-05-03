package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.YesWorld;


@Dao
public abstract class YesWorldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(YesWorld all);

    @Query("DELETE FROM yesworld")
    abstract void deleteAll();

    @Query("Select * FROM yesworld")
    public abstract LiveData<YesWorld> getYesWorld();

    @Transaction
    public void updateAll(YesWorld yesworld) {
        deleteAll();
        bulkInsert(yesworld);
    }
}
