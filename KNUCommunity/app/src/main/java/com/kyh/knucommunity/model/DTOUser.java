package com.kyh.knucommunity.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DTOUser {
    @SerializedName("index")
    private int index;

    @SerializedName("name")
    private String name;

    @SerializedName("tel")
    private String tel;

    public DTOUser(int index, String name, String tel) {
        this.index = index;
        this.name = name;
        this.tel = tel;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    @NonNull
    @Override
    public String toString() {
        return index + " " + name + " " + tel;
    }
}
