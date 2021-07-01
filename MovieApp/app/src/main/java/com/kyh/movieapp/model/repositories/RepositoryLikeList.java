package com.kyh.movieapp.model.repositories;

import android.content.Context;

import com.kyh.movieapp.model.dtos.DTOMovie;

import java.util.ArrayList;

public class RepositoryLikeList {
    private static ArrayList<DTOMovie> likedItems = new ArrayList<>();
    private static Context context;

    public static void addLikedItems(DTOMovie movie) {
        likedItems.add(movie);
    }

    public static ArrayList<DTOMovie> getLikedItems() {
        return likedItems;
    }

    public static void setLikedItems(ArrayList<DTOMovie> likedItems) {
        RepositoryLikeList.likedItems = likedItems;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RepositoryLikeList.context = context;
    }
}
