package com.example.myapplication;


import androidx.annotation.NonNull;

public class Type {
    final Integer id;
    final String name;
    final String description;

    public Type(Integer id, String name, String description) {
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
