package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.josh.trackcovid19v2.data.CountriesRepository;
import com.josh.trackcovid19v2.data.StateRepository;
import com.josh.trackcovid19v2.data.database.entity.Countries;
import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.data.database.entity.YesCountries;
import com.josh.trackcovid19v2.data.database.entity.YesStates;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

public class YourstateViewModel extends ViewModel {

    private final StateRepository mRepository;
    private final LiveData<List<States>> mStatesData;
    private final LiveData<List<YesStates>> mYesStatesData;

    public YourstateViewModel(StateRepository mRepository) {
        this.mRepository = mRepository;
        this.mStatesData = mRepository.getStatesList();
        this.mYesStatesData = mRepository.getYesStatesList();

    }

    public LiveData<List<States>> getStatesList() {
        return mStatesData;
    }

    public LiveData<List<YesStates>> getYesStatesList() {
        return mYesStatesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }
}
