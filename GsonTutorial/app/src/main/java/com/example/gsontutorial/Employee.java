package com.example.gsontutorial;

import android.widget.Adapter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee {

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("age")
    private int mAge;

    @SerializedName("mail")
    private String mMail;

    @SerializedName("address")
    private Address address;

    @SerializedName("family")
    private List<FamilyMember> mFamily;


    public Employee(String mFirstName, int mAge, String mMail, Address address, List<FamilyMember> mFamily) {
        this.mFirstName = mFirstName;
        this.mAge = mAge;
        this.mMail = mMail;
        this.address = address;
        this.mFamily = mFamily;
    }
}
