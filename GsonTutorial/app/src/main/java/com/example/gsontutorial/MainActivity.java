package com.example.gsontutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();

//        Address address = new Address("Germany", "Berlin");
//        List<FamilyMember> family = new ArrayList<>();
//
//        family.add(new FamilyMember("wife",25));
//        family.add(new FamilyMember("Daughter",7));
//
//        Employee employee = new Employee("Suman", 30, "suman@gmail.com",address, family);
//        String json = gson.toJson(employee);

        String json = "{\n" +
                "  \"address\": {\n" +
                "    \"city\": \"Berlin\",\n" +
                "    \"country\": \"Germany\"\n" +
                "  },\n" +
                "  \"age\": 30,\n" +
                "  \"family\": [\n" +
                "    {\n" +
                "      \"age\": 25,\n" +
                "      \"role\": \"wife\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"age\": 7,\n" +
                "      \"role\": \"Daughter\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"first_name\": \"Suman\",\n" +
                "  \"mail\": \"suman@gmail.com\"\n" +
                "}";

        Employee employee = gson.fromJson(json, Employee.class);


        String familyJson = "[\n" +
                "    {\n" +
                "      \"age\": 25,\n" +
                "      \"role\": \"wife\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"age\": 7,\n" +
                "      \"role\": \"Daughter\"\n" +
                "    }\n" +
                "  ]";

        Type familyType = new TypeToken < ArrayList<FamilyMember> > (){}.getType();
        ArrayList<FamilyMember> familyMembers = gson.fromJson(familyJson, familyType );
    }
}