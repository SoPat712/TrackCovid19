package com.josh.trackcovid19v2.utility;

import android.content.Context;

import com.josh.trackcovid19v2.AppExecutors;
import com.josh.trackcovid19v2.data.CountriesRepository;
import com.josh.trackcovid19v2.data.StateRepository;
import com.josh.trackcovid19v2.data.WorldRepository;
import com.josh.trackcovid19v2.data.database.CountriesDao;
import com.josh.trackcovid19v2.data.database.CountriesDatabase;
import com.josh.trackcovid19v2.data.database.StateDao;
import com.josh.trackcovid19v2.data.database.StatesDatabase;
import com.josh.trackcovid19v2.data.database.WorldDao;
import com.josh.trackcovid19v2.data.database.WorldDatabase;
import com.josh.trackcovid19v2.data.database.YesCountriesDao;
import com.josh.trackcovid19v2.data.database.YesCountriesDatabase;
import com.josh.trackcovid19v2.data.database.YesStateDao;
import com.josh.trackcovid19v2.data.database.YesStatesDatabase;
import com.josh.trackcovid19v2.data.database.YesWorldDao;
import com.josh.trackcovid19v2.data.database.YesWorldDatabase;
import com.josh.trackcovid19v2.data.network.UserNetworkDataSource;
import com.josh.trackcovid19v2.ui.viewmodel.yourCountriesViewModelFactory;
import com.josh.trackcovid19v2.ui.viewmodel.yourStateViewModelFactory;
import com.josh.trackcovid19v2.ui.viewmodel.yourWorldViewModelFactory;
import com.josh.trackcovid19v2.ui.viewmodel.yourrealcountryViewModelFactory;

public class InjectorUtils {

    public static StateRepository getStateRepository(Context context) {
        // Get all we need
        StateDao stateDao = StatesDatabase.getInstance(context).stateDao();
        YesStateDao yesStateDao = YesStatesDatabase.getInstance(context).yesstateDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return StateRepository.getInstance(stateDao, yesStateDao,  networkDataSource, executors);

    }

    public static CountriesRepository getCountriesRepository(Context context) {
        // Get all we need
        CountriesDao countriesDao = CountriesDatabase.getInstance(context).countriesDao();
        YesCountriesDao yescountriesDao = YesCountriesDatabase.getInstance(context).yescountriesDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return CountriesRepository.getInstance(countriesDao,
                  yescountriesDao,
                networkDataSource, executors);

    }

    public static WorldRepository getWorldRepository(Context context) {
        // Get all we need
        WorldDao worldDao = WorldDatabase.getInstance(context).worldDao();
        YesWorldDao yesworldDao = YesWorldDatabase.getInstance(context).yesworldDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return WorldRepository.getInstance(worldDao, yesworldDao, networkDataSource, executors);

    }

    public static yourWorldViewModelFactory getWorldViewModelFactory(Context context){
        WorldRepository repository = getWorldRepository(context);
        return new yourWorldViewModelFactory(repository);
    }
    public static yourStateViewModelFactory getStateViewModelFactory(Context context){
        StateRepository repository = getStateRepository(context);
        return new yourStateViewModelFactory(repository);
    }
    public static yourCountriesViewModelFactory getCountriesViewModelFactory(Context context){
        CountriesRepository repository = getCountriesRepository(context);
        return new yourCountriesViewModelFactory(repository);
    }
    public static yourrealcountryViewModelFactory getRealCountryViewModelFactory(Context context){
        CountriesRepository repository = getCountriesRepository(context);
        return new yourrealcountryViewModelFactory(repository);
    }

}
