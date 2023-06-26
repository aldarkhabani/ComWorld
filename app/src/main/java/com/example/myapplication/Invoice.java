package com.example.myapplication;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Invoice implements Serializable {
    final Integer id ,agent_id,is_buy,type_id;
    final String date,description;
    final InvoiceDetail invoiceDetail;

    public Invoice(Integer id, Integer agent_id,Integer type_id, Integer is_buy, String date, String description,InvoiceDetail invoiceDetail) {
        this.id = id;
        this.agent_id = agent_id;
        this.type_id = type_id;
        this.is_buy = is_buy;
        this.date = date;
        this.description = description;
        this.invoiceDetail = invoiceDetail;
    }

    public String[] getArray(){
        String[] array = new String[9];
        array[0] = this.id.toString();
        array[1] = this.agent_id.toString();
        array[2] = this.is_buy.toString();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        array[3] = format.format(calendar.getTime());
        array[4] = this.description;
        array[5] = this.invoiceDetail.material_id.toString();
        array[6] = this.invoiceDetail.number.toString();
        array[7] = String.valueOf(this.invoiceDetail.price);
        array[8] = String.valueOf(this.invoiceDetail.total);
        return array;
    }
}
