package com.josh.trackcovid19v2.data.network;

import com.josh.trackcovid19v2.data.database.entity.States;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class StatesWrapper {

    @SerializedName("states")
    @Expose
    private List<States> states = null;

    public List<States> getStates() {
        return states;
    }

    public void setStates(List<States> states) {
        this.states = states;
    }

}

