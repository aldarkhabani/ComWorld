package com.example.myapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Material implements Serializable {
    final Integer id,type_id;
    final String name,description;

    public Material(Integer id, Integer type_id, String name, String description) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.description = description;
    }

    public String[] getArray(){
        String[] array = new String[3];
        array[0] = this.id.toString();
        array[1] = this.name;
        array[2] = this.description;
        return array;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
