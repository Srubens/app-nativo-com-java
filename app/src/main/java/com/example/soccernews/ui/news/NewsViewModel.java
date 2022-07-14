package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();
        List<News> news = new ArrayList<>();
        news.add(new News("Santos sai eliminado da copa do Brasil", "Apesar do placar de 1x0 Santos é eliminado pelo corinthians"));
        news.add(new News("Titulo 2", "descricao 2"));
        news.add(new News("Titulo 3", "descricao 3"));
        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}