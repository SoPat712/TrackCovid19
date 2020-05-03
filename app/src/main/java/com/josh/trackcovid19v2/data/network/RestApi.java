package com.josh.trackcovid19v2.data.network;

import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.data.database.entity.World;
import com.josh.trackcovid19v2.data.database.entity.YesWorld;
import com.josh.trackcovid19v2.data.network.pojo.CountriesPojo;
import com.josh.trackcovid19v2.data.network.pojo.StatesPojo;
import com.josh.trackcovid19v2.data.network.pojo.YesCountriesPojo;
import com.josh.trackcovid19v2.data.network.pojo.YesStatesPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApi {

    @Headers("Content-Type: application/json")
    @GET("v2/states")
    Call<List<States>>  getStatess();

    @GET("v2/states")
    Call<List<StatesPojo>>  getStates(@Query("yesterday") int y);

    @GET("v2/states")
    Call<List<YesStatesPojo>>  getYesStates(@Query("yesterday") int y);

    @GET("/v2/countries")
    Call<List<CountriesPojo>>  getCountries(@Query("yesterday") int y);

    @GET("/v2/countries")
    Call<List<YesCountriesPojo>>  getYesCountries(@Query("yesterday") int y);

    @GET("v2/all")
    Call<World> getWorld();

    @GET("v2/all?yesterday=true")
    Call<YesWorld> getYesWorld();

    //countries/USA
    //yesterday/USA

    //

    //Observable<List<ServiceResponse>> getStates();
}