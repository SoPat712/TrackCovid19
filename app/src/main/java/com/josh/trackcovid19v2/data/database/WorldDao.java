package com.josh.trackcovid19v2.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.josh.trackcovid19v2.data.database.entity.World;


@Dao
public abstract class WorldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void bulkInsert(World all);

    @Query("DELETE FROM world")
    abstract void deleteAll();

    @Query("Select * FROM world")
    public abstract LiveData<World> getWorld();

    @Transaction
    public void updateAll(World world) {
        deleteAll();
        bulkInsert(world);
    }
}
