package com.example.myapplication;

import androidx.annotation.NonNull;

public class Agent {
    final Integer id;
    final String name,description;

    public Agent(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
