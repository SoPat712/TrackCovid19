package com.josh.trackcovid19v2.data.network;

import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL = "https://corona.lmao.ninja/";

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory
                .create()).build();
    }

  // For getting states data -- create similar for countries
    /*
    public static Call<List<States>>  getDataFromService() {
        Log.d(LOG_TAG, "Getting data from the server");
        List<States> foo = null;

        RestApi service = getRetrofit().create(RestApi.class);

        Call<List<States>> call = service.getStates();


        return call;
    }
*/
    public static Call<List<StatesPojo>>  getStatesDataFromService() {
        Log.d(LOG_TAG, "Getting states data from the server");

        RestApi service = getRetrofit().create(RestApi.class);

        Call<List<StatesPojo>> call = service.getStates(0);

        return call;
    }

    public static Call<List<YesStatesPojo>>  getYesStatesDataFromService() {
        Log.d(LOG_TAG, "Getting yes states data from the server");

        RestApi service = getRetrofit().create(RestApi.class);

        Call<List<YesStatesPojo>> call = service.getYesStates(1);

        return call;
    }

    // For getting countries data
    public static Call<List<CountriesPojo>>  getCountriesDataFromService() {
        Log.d(LOG_TAG, "Getting countries data from the server");
        //List<Countries> foo = null;

        RestApi service = getRetrofit().create(RestApi.class);

        Call<List<CountriesPojo>> call = service.getCountries(0);


        return call;
    }

    public static Call<List<YesCountriesPojo>>  getYesCountriesDataFromService() {
        Log.d(LOG_TAG, "Getting yesterdays countries data from the server");
        //List<Countries> foo = null;

        RestApi service = getRetrofit().create(RestApi.class);

        Call<List<YesCountriesPojo>> call = service.getYesCountries(1);


        return call;
    }

    public static List<Countries> convertToCountriesList(List<CountriesPojo> data) {
        List<Countries> countries = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {


            for (CountriesPojo ddd : data) {
                Countries country_data = new Countries();
                country_data.country = ddd.getCountry();
                country_data.cases = Integer.parseInt(ddd.getCases());
                country_data.active = Integer.parseInt(ddd.getActive());
                country_data.deaths = Integer.parseInt(ddd.getDeaths());
                country_data.todayCases = Integer.parseInt(ddd.getTodayCases());
                country_data.todayDeaths = Integer.parseInt(ddd.getTodayDeaths());
                country_data.recovered = Integer.parseInt(ddd.getRecovered());
                //country_data.recovered = 0;
                country_data.tests = Integer.parseInt(ddd.getTests());
                country_data.testsPerMillion = Integer.parseInt(ddd.getTestsPerOneMillion());
                country_data.flag = ddd.getCountryInfoPojo().getFlag();
                country_data.critical = Integer.parseInt(ddd.getCritical());
                country_data.updated = Long.parseLong(ddd.getUpdated());


                // add
                countries.add(country_data);
                }


            Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        return countries;
    }

    public static List<States> convertToStatesList(List<StatesPojo> data) {
        List<States> states = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {


            for (StatesPojo ddd : data) {
                States state_data = new States();
                state_data.state = ddd.getState();
                state_data.cases = Integer.parseInt(ddd.getCases());
                state_data.active = Integer.parseInt(ddd.getActive());
                state_data.deaths = Integer.parseInt(ddd.getDeaths());
                state_data.todayCases = Integer.parseInt(ddd.getTodayCases());
                state_data.todayDeaths = Integer.parseInt(ddd.getTodayDeaths());
                state_data.tests = Integer.parseInt(ddd.getTests());
                state_data.testsPerMillion = Integer.parseInt(ddd.getTestsPerOneMillion());



                // add
                states.add(state_data);
            }


            Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        return states;
    }

    public static List<YesStates> convertToYesStatesList(List<YesStatesPojo> data) {
        List<YesStates> yesstates = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {


            for (YesStatesPojo ddd : data) {
                YesStates state_data = new YesStates();
                state_data.state = ddd.getState();
                state_data.cases = Integer.parseInt(ddd.getCases());
                state_data.active = Integer.parseInt(ddd.getActive());
                state_data.deaths = Integer.parseInt(ddd.getDeaths());
                state_data.todayCases = Integer.parseInt(ddd.getTodayCases());
                state_data.todayDeaths = Integer.parseInt(ddd.getTodayDeaths());
                state_data.tests = Integer.parseInt(ddd.getTests());
                state_data.testsPerMillion = Integer.parseInt(ddd.getTestsPerOneMillion());



                // add
                yesstates.add(state_data);
            }


            Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        return yesstates;
    }

    ///
    public static List<States> convertToStatesList1(List<States> data) {
        List<States> states = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {

            /*
            for (States ddd : data) {
                States state_data = new States();
                state_data.state = ddd.state;
                state_data.cases = ddd.cases;
                state_data.todayCases = ddd.todayCases;
                state_data.deaths = ddd.deaths;
                state_data.todayDeaths = ddd.todayDeaths;
                state_data.active = ddd.active;

                // add
                states.add(state_data);
                }
             */

            Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        //return states;
        return data;
    }

    //private static Date getDate(String stringData) throws ParseException {
    //    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
    //    return format.parse(stringData);
    //}


    public static Call<World>  getWorldDataFromService() {
        Log.d(LOG_TAG, "Getting data from the server");

        RestApi service = getRetrofit().create(RestApi.class);

        Call<World> call = service.getWorld();

        return call;
    }
    public static Call<YesWorld>  getYesWorldDataFromService() {
        Log.d(LOG_TAG, "Getting yesterdays data from the server");

        RestApi service = getRetrofit().create(RestApi.class);

        Call<YesWorld> call = service.getYesWorld();

        return call;
    }

    public static List<YesCountries> convertToYesCountriesList(List<YesCountriesPojo> data) {
        List<YesCountries> yescountries = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {


            for (YesCountriesPojo ddd : data) {
                YesCountries country_data = new YesCountries();
                country_data.country = ddd.getCountry();
                country_data.cases = Integer.parseInt(ddd.getCases());
                country_data.active = Integer.parseInt(ddd.getActive());
                country_data.deaths = Integer.parseInt(ddd.getDeaths());
                country_data.todayCases = Integer.parseInt(ddd.getTodayCases());
                country_data.todayDeaths = Integer.parseInt(ddd.getTodayDeaths());
                country_data.recovered = Integer.parseInt(ddd.getRecovered());
                //country_data.recovered = 0;
                country_data.tests = Integer.parseInt(ddd.getTests());
                country_data.testsPerMillion = Integer.parseInt(ddd.getTestsPerOneMillion());
                country_data.flag = ddd.getCountryInfoPojo().getFlag();
                country_data.critical = Integer.parseInt(ddd.getCritical());
                country_data.updated = Long.parseLong(ddd.getUpdated());
                // add
                yescountries.add(country_data);
            }


            Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        return yescountries;
    }

    /*
    public static List<States> convertToStatesList(List<States> data) {
        List<States> states = new ArrayList<>();
        Log.d(LOG_TAG, "Converting the response.");

        try {


            for (States ddd : data) {
                States state_data = new States();
                state_data.state = ddd.state;
                state_data.cases = ddd.cases;
                state_data.todayCases = ddd.todayCases;
                state_data.deaths = ddd.deaths;
                state_data.todayDeaths = ddd.todayDeaths;
                state_data.active = ddd.active;

                // add
                states.add(state_data);
                }

            //Log.d(LOG_TAG, "Converting the response process has been success. ");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Converting the response process has been failed. ", e);
        }

        //return states;
        return data;
    }
    */
}
