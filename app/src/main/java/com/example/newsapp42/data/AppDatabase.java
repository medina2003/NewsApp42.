package com.example.newsapp42.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.newsapp42.models.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao dao();
}
