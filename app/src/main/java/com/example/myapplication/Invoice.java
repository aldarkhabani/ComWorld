package com.example.myapplication;

public class Invoice {
    final Integer id ,agent_id,is_buy;
    final String date,description;

    public Invoice(Integer id, Integer agent_id, Integer is_buy, String date, String description) {
        this.id = id;
        this.agent_id = agent_id;
        this.is_buy = is_buy;
        this.date = date;
        this.description = description;
    }
}
