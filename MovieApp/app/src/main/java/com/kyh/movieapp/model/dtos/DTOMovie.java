package com.kyh.movieapp.model.dtos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DTOMovie {
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("image")
    private String image;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("director")
    private String director;
    @SerializedName("actor")
    private String actor;
    @SerializedName("userRating")
    private double userRating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}