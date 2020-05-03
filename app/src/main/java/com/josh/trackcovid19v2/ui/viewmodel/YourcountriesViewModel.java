package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.josh.trackcovid19v2.data.CountriesRepository;
import com.josh.trackcovid19v2.data.database.entity.Countries;
import com.josh.trackcovid19v2.data.database.entity.YesCountries;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

public class YourcountriesViewModel extends ViewModel {

    private final CountriesRepository mRepository;
    private final LiveData<List<Countries>> mCountriesData;
    private final LiveData<List<YesCountries>> mYesCountriesData;

    public YourcountriesViewModel(CountriesRepository mRepository) {
        this.mRepository = mRepository;
        this.mCountriesData = mRepository.getCountriesList();
        this.mYesCountriesData = mRepository.getYesCountriesList();

    }

    public LiveData<List<Countries>> getCountriesList() {
        return mCountriesData;
    }

    public LiveData<List<YesCountries>> getYesCountriesList() {
        return mYesCountriesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }
}
