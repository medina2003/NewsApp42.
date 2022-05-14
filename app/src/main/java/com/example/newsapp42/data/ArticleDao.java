package com.example.newsapp42.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsapp42.models.Article;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    @Query("SELECT * FROM article ORDER BY data DESC")
    List<Article> getAllArticles();

    @Query("SELECT * FROM article WHERE text LIKE '%' || :text ||'%'")
    List <Article> getAllWithTitle(String text);
}
