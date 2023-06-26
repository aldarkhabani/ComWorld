package com.example.myapplication;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Agent implements Serializable {
    final Integer id;
    final String name,description;

    public Agent(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String[] getArray(){
        String[] array = new String[3];
        array[0] = this.id.toString();
        array[1] = this.name.toString();
        array[2] = this.description.toString();
        return array;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
