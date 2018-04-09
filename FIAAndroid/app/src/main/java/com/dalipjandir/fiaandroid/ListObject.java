package com.dalipjandir.fiaandroid;

import android.graphics.Bitmap;

//this is just used to display the resulting flags in the results page
public class ListObject {
    private Bitmap image;
    private String country;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}