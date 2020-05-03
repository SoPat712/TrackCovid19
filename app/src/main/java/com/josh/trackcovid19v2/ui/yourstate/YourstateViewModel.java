package com.josh.trackcovid19v2.ui.yourstate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.josh.trackcovid19v2.data.StateRepository;
import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.model.ServiceRequest;

import java.util.List;

public class YourstateViewModel extends ViewModel {

    private final StateRepository mRepository;
    private final LiveData<List<States>> mStatesData;

    public YourstateViewModel(StateRepository mRepository) {
        this.mRepository = mRepository;
        this.mStatesData = mRepository.getStatesList();
    }

    public LiveData<List<States>> getStatesList() {
        return mStatesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
    }
}
