package com.kyh.knucommunity.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DTOUserList {
    @SerializedName("data")
    private ArrayList<DTOUser> data;

    public ArrayList<DTOUser> getData() {
        return data;
    }
}
