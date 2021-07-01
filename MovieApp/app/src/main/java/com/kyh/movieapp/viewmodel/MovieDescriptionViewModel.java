package com.kyh.movieapp.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.kyh.movieapp.databinding.ActivityMovieDescriptionBinding;
import com.kyh.movieapp.model.repositories.RepositoryHome;
import com.kyh.movieapp.model.repositories.RepositoryLikeList;
import com.kyh.movieapp.model.repositories.RepositoryMovieDescription;
import com.kyh.movieapp.util.ImagePutter;

public class MovieDescriptionViewModel {
    private static MovieDescriptionViewModel movieDescriptionViewModel;

    private ActivityMovieDescriptionBinding binding;
    private boolean isLiked;

    private MovieDescriptionViewModel(LayoutInflater inflater, Context context) {
        this.binding = ActivityMovieDescriptionBinding.inflate(inflater);
        this.isLiked = RepositoryLikeList.getLikedItems().contains(RepositoryMovieDescription.getItem());

        RepositoryHome.addImage(RepositoryMovieDescription.getItem().getImage());

        if(isLiked) {
            binding.movieDescriptionLikeBtn.setColorFilter(Color.parseColor("#FF0000"));
        } else {
            binding.movieDescriptionLikeBtn.setColorFilter(Color.parseColor("#000000"));
        }

        binding.movieDescriptionTitleText.setText(RepositoryMovieDescription.getItem().getTitle());
        binding.movieDescriptionSubtitleText.setText(RepositoryMovieDescription.getItem().getSubtitle());
        binding.movieDescriptionPubDateText.setText(RepositoryMovieDescription.getItem().getPubDate());
        binding.movieDescriptionDirectorText.setText(RepositoryMovieDescription.getItem().getDirector());
        binding.movieDescriptionActorText.setText(RepositoryMovieDescription.getItem().getActor());
        binding.movieDescriptionUserRatingText.setText(RepositoryMovieDescription.getItem().getUserRating() + "");
        ImagePutter.putImage(context, RepositoryMovieDescription.getItem().getImage(), binding.movieDescriptionImage);

        binding.movieDescriptionLikeBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(isLiked) {
                    binding.movieDescriptionLikeBtn.setColorFilter(Color.parseColor("#000000"));
                } else {
                    binding.movieDescriptionLikeBtn.setColorFilter(Color.parseColor("#FF0000"));
                }
                isLiked = !isLiked;
            }
        });
    }

    public static MovieDescriptionViewModel getInstance() {
        return movieDescriptionViewModel;
    }

    public static void setMovieDescriptionViewModel(LayoutInflater inflater, Context context) {
        MovieDescriptionViewModel.movieDescriptionViewModel = new MovieDescriptionViewModel(inflater, context);
    }

    public ActivityMovieDescriptionBinding getBinding() {
        return binding;
    }

    public void handleItemToLikeList() {
        if(isLiked) {
            if(!RepositoryLikeList.getLikedItems().contains(RepositoryMovieDescription.getItem())) {
                RepositoryLikeList.addLikedItems(RepositoryMovieDescription.getItem());
            }
        } else {
            RepositoryLikeList.getLikedItems().remove(RepositoryMovieDescription.getItem());
        }
    }
}
