package com.kyh.movieapp.model.repositories;

import android.content.Context;

import com.kyh.movieapp.model.dtos.DTOMovie;

import java.util.ArrayList;

public class RepositorySearchResult {
    private static Context context;
    private static ArrayList<DTOMovie> items;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RepositorySearchResult.context = context;
    }

    public static ArrayList<DTOMovie> getItems() {
        return items;
    }

    public static void setItems(ArrayList<DTOMovie> items) {
        RepositorySearchResult.items = items;
    }

    public static void addItems(ArrayList<DTOMovie> another) {
        items.addAll(another);
    }
}
