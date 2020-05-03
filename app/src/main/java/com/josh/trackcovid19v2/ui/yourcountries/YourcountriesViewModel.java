package com.josh.trackcovid19v2.ui.yourcountries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.josh.trackcovid19v2.data.CountriesRepository;
import com.josh.trackcovid19v2.data.database.entity.Countries;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

public class YourcountriesViewModel extends ViewModel {

    private final CountriesRepository mRepository;
    private final LiveData<List<Countries>> mCountriesData;

    public YourcountriesViewModel(CountriesRepository mRepository) {
        this.mRepository = mRepository;
        this.mCountriesData = mRepository.getCountriesList();
    }

    public LiveData<List<Countries>> getCountriesList() {
        return mCountriesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
    }
}
