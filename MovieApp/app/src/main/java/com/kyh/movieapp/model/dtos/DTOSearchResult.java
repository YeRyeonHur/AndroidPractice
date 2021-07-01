package com.kyh.movieapp.model.dtos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DTOSearchResult {
    @SerializedName("lastBuildDate")
    private String lastBuildDate;
    @SerializedName("total")
    private int total;
    @SerializedName("start")
    private int start;
    @SerializedName("display")
    private int display;
    @SerializedName("items")
    private ArrayList<DTOMovie> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public ArrayList<DTOMovie> getItems() {
        return items;
    }

    public void setItems(ArrayList<DTOMovie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public String toString() {
        String result = "";
        for(DTOMovie movie : items)
            result += items.toString() + " ";
        return result;
    }
}