package com.example.myapplication;

public class Material {
    final Integer id,type_id;
    final String name,description;

    public Material(Integer id, Integer type_id, String name, String description) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.description = description;
    }
}
