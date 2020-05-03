package com.josh.trackcovid19v2.model;

import com.josh.trackcovid19v2.data.network.pojo.StatesPojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponse {

    @SerializedName("data")
    @Expose
    public List<StatesPojo> data = null;
}
