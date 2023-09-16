package com.example.assignment_2;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    int age;
    int mood;

    public User(String name, int age, int mood) {
        this.name = name;
        this.age = age;
        this.mood = mood;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMood() {
        return mood;
    }
}
