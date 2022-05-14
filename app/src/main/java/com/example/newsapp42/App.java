package com.example.newsapp42;


import android.app.Application;

import androidx.room.Room;

import com.example.newsapp42.data.AppDatabase;

public class App extends Application {
    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "article_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
