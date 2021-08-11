package com.example.dagger2;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    private String name;
    private String capital;

    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }
}
