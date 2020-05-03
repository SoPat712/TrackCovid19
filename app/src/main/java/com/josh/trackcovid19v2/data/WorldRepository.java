package com.josh.trackcovid19v2.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.josh.trackcovid19v2.AppExecutors;
import com.josh.trackcovid19v2.data.database.WorldDao;
import com.josh.trackcovid19v2.data.database.YesWorldDao;
import com.josh.trackcovid19v2.data.database.entity.World;
import com.josh.trackcovid19v2.data.database.entity.YesWorld;
import com.josh.trackcovid19v2.data.network.UserNetworkDataSource;
import com.josh.trackcovid19v2.model.ServiceRequest;

/**
 * This class responsible for handling data operations. This is the mediator between different
 * data sources (persistent model, web service, cache, etc.)
 */
public class WorldRepository {
    private static final String LOG_TAG = WorldRepository.class.getSimpleName();

    private WorldDao mWorldDao;
    private YesWorldDao mYesWorldDao;
    private UserNetworkDataSource mNetworkDataSource;

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static WorldRepository sInstance;

    public WorldRepository(WorldDao worldDao, YesWorldDao yesworldDao, UserNetworkDataSource networkDataSource, AppExecutors
            executors) {
        this.mWorldDao = worldDao;
        this.mYesWorldDao = yesworldDao;
        this.mNetworkDataSource = networkDataSource;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        mNetworkDataSource.getWorld().observeForever(world -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "world table is updating");
                mWorldDao.updateAll(world);
            });
        });

        mNetworkDataSource.getYesWorld().observeForever(yesworld -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "yes world table is updating");
                mYesWorldDao.updateAll(yesworld);
            });
        });
    }

    public static WorldRepository getInstance(WorldDao worldDao, YesWorldDao yesworldDao, UserNetworkDataSource
            networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new WorldRepository(worldDao, yesworldDao, networkDataSource, executors);
                Log.d(LOG_TAG, "Made new world repository");
            }
        }
        return sInstance;
    }

    public LiveData<World> getWorld() {
        LiveData<World> foo = mWorldDao.getWorld();
        return foo;
    }

    public LiveData<YesWorld> getYesWorld() {
        LiveData<YesWorld> foo = mYesWorldDao.getYesWorld();
        return foo;
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchWorldData(serviceRequest);
    }

    public void postServiceRequest1(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchYesWorldData(serviceRequest);
    }

}
