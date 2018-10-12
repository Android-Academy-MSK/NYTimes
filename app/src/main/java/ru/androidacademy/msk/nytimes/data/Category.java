package ru.androidacademy.msk.nytimes.data;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Category implements Serializable {
    private final int id;
    @NonNull
    private final String name;

    public Category(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
