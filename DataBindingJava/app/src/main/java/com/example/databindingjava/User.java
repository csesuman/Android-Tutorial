package com.example.databindingjava;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class User {
        private String name;
        private int age;
        private boolean active;
        private String imageUrl;

    public User(String name, int age, boolean active, String imageUrl) {
        this.name = name;
        this.age = age;
        this.active = active;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public void handleClicked() {
        Log.d(TAG, "handleClick: " + this.getName());
    }
}
