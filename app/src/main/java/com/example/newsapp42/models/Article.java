package com.example.newsapp42.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Article implements Serializable {
    @PrimaryKey
    private long data;
    private String text;

    public Article() {
    }

    public Article(String text, long data) {
        this.text = text;
        this.data = data;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public Article(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
