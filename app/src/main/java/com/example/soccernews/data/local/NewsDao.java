package com.example.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.soccernews.domain.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = :Favorite")
    List<News> loadFavoriteNews(Boolean Favorite);

}
