package com.dalipjandir.fiaandroid;

/**
 * Created by jeromicho on 2018-04-04.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Flags {
    private String Country;
    private String pngName;
    private String Capital;
    private String wiki;
    private String Edge_Pic;


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPngName() {
        return pngName;
    }

    public void setPngName(String pngName) {
        this.pngName = pngName;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getEdge_Pic() {
        return Edge_Pic;
    }

    public void setEdge_Pic(String edge_Pic) {
        Edge_Pic = edge_Pic;
    }

    @Override
    public String toString() {
        return "Flags{" +
                "Country='" + Country + '\'' +
                ", pngName='" + pngName + '\'' +
                ", Capital='" + Capital + '\'' +
                ", wiki='" + wiki + '\'' +
                ", Edge_Pic='" + Edge_Pic + '\'' +
                '}';
    }
}
