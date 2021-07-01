package com.kyh.movieapp.model.repositories;

import android.content.Context;

import java.util.ArrayList;

public class RepositoryHome {
    public static int MAX_IMAGE_HISTORY = 5;

    private static ArrayList<String> searchHistories = new ArrayList<>();
    private static ArrayList<String> imageHistories = new ArrayList<>();
    private static Context context;

    public static ArrayList<String> getSearchHistories() {
        return searchHistories;
    }

    public static void addHistory(String searched) {
        searchHistories.add(searched);
    }

    public static ArrayList<String> getImageHistories() {
        return imageHistories;
    }

    public static void setImageHistories(ArrayList<String> imageHistories) {
        RepositoryHome.imageHistories = imageHistories;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        RepositoryHome.context = context;
    }

    public static void addImage(String image) {
        if(imageHistories.size() >= MAX_IMAGE_HISTORY) {
            imageHistories = new ArrayList<>(searchHistories.subList(1, MAX_IMAGE_HISTORY-1));
        }
        imageHistories.add(image);
    }
}
