package com.josh.trackcovid19v2.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.josh.trackcovid19v2.data.database.entity.YesCountries;

//@Database(entities = { YourEntity.class }, version = 1, exportSchema = false)
@Database(entities = {YesCountries.class}, version = 1, exportSchema = false)
public abstract class YesCountriesDatabase extends RoomDatabase {

    private static final String LOG_TAG = YesCountriesDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "yescountries";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static YesCountriesDatabase mInstance;

    public static YesCountriesDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting " + DATABASE_NAME + " database");

        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(context, YesCountriesDatabase.class, DATABASE_NAME).build();
                Log.d(LOG_TAG, DATABASE_NAME + " database has been created.");
            }
        }
        return mInstance;
    }

    // The associated DAOs for the database
    public abstract YesCountriesDao yescountriesDao();

}
