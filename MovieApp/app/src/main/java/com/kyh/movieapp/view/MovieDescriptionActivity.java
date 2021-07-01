package com.kyh.movieapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kyh.movieapp.viewmodel.MovieDescriptionViewModel;

public class MovieDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieDescriptionViewModel.setMovieDescriptionViewModel(getLayoutInflater(), getApplicationContext());
        setContentView(MovieDescriptionViewModel.getInstance().getBinding().getRoot());
    }

    @Override
    protected void onStop() {
        super.onStop();
        MovieDescriptionViewModel.getInstance().handleItemToLikeList();
    }
}
