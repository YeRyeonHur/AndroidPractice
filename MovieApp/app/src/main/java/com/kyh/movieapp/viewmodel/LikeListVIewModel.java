package com.kyh.movieapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.movieapp.adapter.SearchResultAdapter;
import com.kyh.movieapp.databinding.FragmentLikeListBinding;
import com.kyh.movieapp.handler.SearchItemOnClickListener;
import com.kyh.movieapp.model.repositories.RepositoryLikeList;
import com.kyh.movieapp.model.repositories.RepositoryMovieDescription;
import com.kyh.movieapp.view.MovieDescriptionActivity;

public class LikeListVIewModel {
    private static LikeListVIewModel likeListVIewModel;

    private FragmentLikeListBinding binding;
    private Context context;

    public LikeListVIewModel(LayoutInflater inflater) {
        this.binding = FragmentLikeListBinding.inflate(inflater);
        this.context = RepositoryLikeList.getContext();

        binding.likeListRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        SearchResultAdapter adapter = new SearchResultAdapter(RepositoryLikeList.getLikedItems(), context);
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        adapter.setListener(new SearchItemOnClickListener() {
            @Override
            public void onItemClick(SearchResultAdapter.ViewHolder holder, View view, int position) {
                RepositoryMovieDescription.setItem(adapter.getItem(position));

                Intent intent = new Intent(context, MovieDescriptionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        binding.likeListRecyclerView.setAdapter(adapter);
    }

    public static LikeListVIewModel getInstance() {
        return likeListVIewModel;
    }

    public static void setLikeListVIewModel(LayoutInflater inflater) {
        LikeListVIewModel.likeListVIewModel = new LikeListVIewModel(inflater);
    }

    public FragmentLikeListBinding getBinding() {
        return binding;
    }
}
