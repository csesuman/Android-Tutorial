package com.example.gsontutorial;

import com.google.gson.annotations.Expose;

public class EmployeeSerializeDeserializePractice {

    @Expose
    private String firstName;
    @Expose ( deserialize = false)
    private int age;
    @Expose
    private String mail;

    private String password;

    public EmployeeSerializeDeserializePractice(String firstName, int age, String mail, String password) {
        this.firstName = firstName;
        this.age = age;
        this.mail = mail;
        this.password = password;
    }
}
