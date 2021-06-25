package com.kyh.knucommunity.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.json.*;

public class DTODataPost {
   @SerializedName("email")
    private Object email; // String JSON 등의 Java Class -> Object

   @SerializedName("index")
    private int index;

   @SerializedName("name")
    private Object name;

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return index + " " + email.toString() + " " + name.toString();
    }
}
