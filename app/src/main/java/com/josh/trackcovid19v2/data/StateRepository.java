package com.josh.trackcovid19v2.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.josh.trackcovid19v2.AppExecutors;
import com.josh.trackcovid19v2.data.database.StateDao;
import com.josh.trackcovid19v2.data.database.YesStateDao;
import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.data.database.entity.YesStates;
import com.josh.trackcovid19v2.data.network.UserNetworkDataSource;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

/**
 * This class responsible for handling data operations. This is the mediator between different
 * data sources (persistent model, web service, cache, etc.)
 */
public class StateRepository {
    private static final String LOG_TAG = StateRepository.class.getSimpleName();

    private StateDao mStatesDao;
    private YesStateDao mYesStatesDao;
    private UserNetworkDataSource mNetworkDataSource;

    // For Singleton instantiationz
    private static final Object LOCK = new Object();
    private static StateRepository sInstance;

    public StateRepository(StateDao stateDao,
                               YesStateDao yesstatesDao,
                               UserNetworkDataSource networkDataSource, AppExecutors
                                       executors) {
        this.mStatesDao = stateDao;
        this.mYesStatesDao = yesstatesDao;
        this.mNetworkDataSource = networkDataSource;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        mNetworkDataSource.getStatesList().observeForever(states -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "states table is updating");
                mStatesDao.updateAll(states);
            });
        });


        mNetworkDataSource.getYesStatesList().observeForever(yesstates -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "yes states table is updating");
                mYesStatesDao.updateAll(yesstates);
            });
        });


    }

    public static StateRepository getInstance(StateDao stateDao,
                                                  YesStateDao yesStateDao,
                                                  UserNetworkDataSource
                                                          networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the yes states repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new StateRepository(stateDao,
                        yesStateDao,
                        networkDataSource, executors);
                Log.d(LOG_TAG, "Made new states repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<States>> getStatesList() {
        LiveData<List<States>> foo = mStatesDao.getStatesList();
        return foo;
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchStatesData(serviceRequest);
    }

    public LiveData<List<YesStates>> getYesStatesList() {
        LiveData<List<YesStates>> fie = mYesStatesDao.getYesStatesList();
        return fie;
    }

    public void postServiceRequest1(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchYesStatesData(serviceRequest);
    }
}
