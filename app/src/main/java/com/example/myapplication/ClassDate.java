package com.example.myapplication;

import androidx.annotation.NonNull;

public class ClassDate {
    final long dateLongTime;
    final String dateString;

    public ClassDate(long dateLongTime, String dateString) {
        this.dateLongTime = dateLongTime;
        this.dateString = dateString;
    }

    @NonNull
    @Override
    public String toString() {
        return dateString;
    }
}
