package com.example.soccernews.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.soccernews.data.local.AppDataBase;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private AppDataBase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = Room.databaseBuilder(this.getContext(),
                AppDataBase.class, "soccer-news").build();

        //final TextView textView = binding.textNews;
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->{
            binding.rvNews.setAdapter(new NewsAdapter(news, updateNews ->{
                db.newsDao().insert(updateNews);
            }));
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}