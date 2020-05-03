package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.josh.trackcovid19v2.data.WorldRepository;
import com.josh.trackcovid19v2.data.database.entity.World;
import com.josh.trackcovid19v2.data.database.entity.YesWorld;
import com.josh.trackcovid19v2.model.ServiceRequest;

public class YourworldViewModel extends ViewModel {

    private final WorldRepository mRepository;
    private final LiveData<World> mWorldData;
    private final LiveData<YesWorld> mYesWorldData;

    public YourworldViewModel(WorldRepository mRepository) {
        this.mRepository = mRepository;
        this.mWorldData = mRepository.getWorld();
        this.mYesWorldData = mRepository.getYesWorld();
    }

    public LiveData<World> getWorld() {
        return mWorldData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }

    public LiveData<YesWorld> getYesWorld() {
        return mYesWorldData;
    }

}
