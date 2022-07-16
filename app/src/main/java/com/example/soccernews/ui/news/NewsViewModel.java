package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.remote.SoccerNewsApi;
import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/Srubens/db69a7b9ab4376a2f483112b73ae3bfd/raw/0d0103e253e5fe10735b5dd05a98a7511476b9de/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsApi.class);
        this.findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()){
                    news.setValue(response.body());
                }else{
                    //PENSAR EM UMA STRATEGIA DE TRATAMENTO DE ERROS
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //PENSAR EM UMA STRATEGIA DE TRATAMENTO DE ERROS
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}