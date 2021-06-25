package com.kyh.knucommunity.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DTODataGet {
    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public String toString() {
        return "msg is " + msg;
    }
}
