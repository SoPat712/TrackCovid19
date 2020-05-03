package com.josh.trackcovid19v2.data.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.josh.trackcovid19v2.AppExecutors;
import com.josh.trackcovid19v2.data.database.entity.Countries;
import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.data.database.entity.World;
import com.josh.trackcovid19v2.data.database.entity.YesCountries;
import com.josh.trackcovid19v2.data.database.entity.YesStates;
import com.josh.trackcovid19v2.data.database.entity.YesWorld;
import com.josh.trackcovid19v2.data.network.pojo.CountriesPojo;
import com.josh.trackcovid19v2.data.network.pojo.StatesPojo;
import com.josh.trackcovid19v2.data.network.pojo.YesCountriesPojo;
import com.josh.trackcovid19v2.data.network.pojo.YesStatesPojo;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserNetworkDataSource {

    private static final String LOG_TAG = UserNetworkDataSource.class.getSimpleName();

    // For Singleton instantiation
    private static UserNetworkDataSource sInstance;
    private static final Object LOCK = new Object();

    private AppExecutors mAppExecutors;
    private final MutableLiveData<List<States>> mDownloadedStatesData;
    private final MutableLiveData<List<YesStates>> mDownloadedYesStatesData;
    private final MutableLiveData<List<Countries>> mDownloadedCountriesData;
    private final MutableLiveData<List<YesCountries>> mDownloadedYesCountriesData;
    private final MutableLiveData<World> mDownloadedWorldData;
    private final MutableLiveData<YesWorld> mDownloadedYesWorldData;

    public UserNetworkDataSource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
        this.mDownloadedStatesData = new MutableLiveData<>();
        this.mDownloadedYesStatesData = new MutableLiveData<>();
        this.mDownloadedCountriesData = new MutableLiveData<>();
        this.mDownloadedYesCountriesData = new MutableLiveData<>();
        this.mDownloadedWorldData = new MutableLiveData<>();
        this.mDownloadedYesWorldData = new MutableLiveData<>();
    }

    public static UserNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new UserNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    /*
    public void fetchData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<List<States>> call = NetworkUtils.getDataFromService();
                call.enqueue(new Callback<List<States>>() {
                    @Override
                    public void onResponse(Call<List<States>> call, Response<List<States>> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        List<States> data = response.body();
                        setStatesList(NetworkUtils.convertToStatesList(data));
                    }

                    @Override
                    public void onFailure(Call<List<States>> call, Throwable t) {
                        Log.d(LOG_TAG, "fofofofofofof");
                        Log.d("Error",t.getMessage());
                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }
    */

    public void fetchStatesData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<List<StatesPojo>> call = NetworkUtils.getStatesDataFromService();
                call.enqueue(new Callback<List<StatesPojo>>() {
                    @Override
                    public void onResponse(Call<List<StatesPojo>> call, Response<List<StatesPojo>> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got states data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        List<StatesPojo> data = response.body();
                        setStatesList(NetworkUtils.convertToStatesList(data));
                    }

                    @Override
                    public void onFailure(Call<List<StatesPojo>> call, Throwable t) {
                        Log.d(LOG_TAG, "states error");
                        Log.d("Error",t.getMessage());
                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting states data process has been failed.", ex);
            }
        });
    }

    public void fetchYesStatesData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<List<YesStatesPojo>> call = NetworkUtils.getYesStatesDataFromService();
                call.enqueue(new Callback<List<YesStatesPojo>>() {
                    @Override
                    public void onResponse(Call<List<YesStatesPojo>> call, Response<List<YesStatesPojo>> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got yes states data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        List<YesStatesPojo> data = response.body();
                        setYesStatesList(NetworkUtils.convertToYesStatesList(data));
                    }

                    @Override
                    public void onFailure(Call<List<YesStatesPojo>> call, Throwable t) {
                        Log.d(LOG_TAG, "yes states error");
                        Log.d("Error",t.getMessage());
                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting states data process has been failed.", ex);
            }
        });
    }

    public void fetchCountriesData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<List<CountriesPojo>> call = NetworkUtils.getCountriesDataFromService();
                call.enqueue(new Callback<List<CountriesPojo>>() {
                    @Override
                    public void onResponse(Call<List<CountriesPojo>> call, Response<List<CountriesPojo>> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got countries data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        List<CountriesPojo> data = response.body();
                        setCountriesList(NetworkUtils.convertToCountriesList(data));
                    }

                    @Override
                    public void onFailure(Call<List<CountriesPojo>> call, Throwable t) {
                        Log.d(LOG_TAG, "countries error");
                        Log.d("Error",t.getMessage());
                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }


    public void fetchYesCountriesData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<List<YesCountriesPojo>> call = NetworkUtils.getYesCountriesDataFromService();
                call.enqueue(new Callback<List<YesCountriesPojo>>() {
                    @Override
                    public void onResponse(Call<List<YesCountriesPojo>> call, Response<List<YesCountriesPojo>> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got yesterdays countries data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        List<YesCountriesPojo> data = response.body();
                        setYesCountriesList(NetworkUtils.convertToYesCountriesList(data));
                    }

                    @Override
                    public void onFailure(Call<List<YesCountriesPojo>> call, Throwable t) {
                        Log.d(LOG_TAG, "yes countries error");
                        Log.d("Error",t.getMessage());
                    }
                });

            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }



    public void fetchWorldData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<World> call = NetworkUtils.getWorldDataFromService();
                call.enqueue(new Callback<World>() {
                    @Override
                    public void onResponse(Call<World> call, Response<World> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got world data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        World data = response.body();
                        //setWorld(NetworkUtils.convertToStatesList(data));
                        setWorld(data);
                    }

                    @Override
                    public void onFailure(Call<World> call, Throwable t) {
                        Log.d(LOG_TAG, "fofofofofofof");
                        Log.d("Error", t.getMessage());
                    }
                });
            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }

    public void fetchYesWorldData(ServiceRequest serviceRequest) {
        mAppExecutors.networkIO().execute(() -> {
            try {
                Call<YesWorld> call1 = NetworkUtils.getYesWorldDataFromService();
                call1.enqueue(new Callback<YesWorld>() {
                    @Override
                    public void onResponse(Call<YesWorld> call, Response<YesWorld> response) {
                        //Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Info", "got yesterday's world data");
                        //ArrayList<States> foo = new ArrayList<States>();
                        YesWorld data = response.body();
                        //setWorld(NetworkUtils.convertToStatesList(data));
                        setYesWorld(data);
                    }

                    @Override
                    public void onFailure(Call<YesWorld> call, Throwable t) {
                        Log.d(LOG_TAG, "fofofofofofof");
                        Log.d("Error", t.getMessage());
                    }
                });
            } catch (Exception ex) {
                Log.e(LOG_TAG, "Getting data process has been failed.", ex);
            }
        });
    }


 //   private void setStatesList(List<States> statesList){
  //      mDownloadedData.postValue(statesList);
  //  }
    private void setWorld(World world) {
        mDownloadedWorldData.postValue(world);
    }

    public LiveData<World> getWorld(){
        return mDownloadedWorldData;
    }

    private void setYesWorld(YesWorld yesworld) {
        mDownloadedYesWorldData.postValue(yesworld);
    }

    public LiveData<YesWorld> getYesWorld(){
        return mDownloadedYesWorldData;
    }

    private void setStatesList(List<States> statesList){
        mDownloadedStatesData.postValue(statesList);
    }
    public LiveData<List<States>> getStatesList(){
        return mDownloadedStatesData;
    }

    private void setYesStatesList(List<YesStates> yesstatesList){
        mDownloadedYesStatesData.postValue(yesstatesList);
    }
    public LiveData<List<YesStates>> getYesStatesList(){
        return mDownloadedYesStatesData;
    }

    private void setCountriesList(List<Countries> countriesList){
        mDownloadedCountriesData.postValue(countriesList);
    }

    public LiveData<List<Countries>> getCountriesList(){
        return mDownloadedCountriesData;
    }

    private void setYesCountriesList(List<YesCountries> yescountriesList){
        mDownloadedYesCountriesData.postValue(yescountriesList);
    }
    public LiveData<List<YesCountries>> getYesCountriesList(){
        return mDownloadedYesCountriesData;
    }


}
