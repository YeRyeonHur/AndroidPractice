package com.kyh.movieapp.model.repositories;

import com.kyh.movieapp.model.dtos.DTOMovie;

public class RepositoryMovieDescription {
    private static DTOMovie item;

    public static DTOMovie getItem() {
        return item;
    }

    public static void setItem(DTOMovie item) {
        RepositoryMovieDescription.item = item;
    }
}
