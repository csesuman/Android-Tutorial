package com.example.gsontutorial;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("city")
    private String mCity;

    public Address(String mCountry, String mCity) {
        this.mCountry = mCountry;
        this.mCity = mCity;
    }
}
