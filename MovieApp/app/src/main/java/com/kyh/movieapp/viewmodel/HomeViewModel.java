package com.kyh.movieapp.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.kyh.movieapp.R;
import com.kyh.movieapp.adapter.SearchHistoryAdapter;
import com.kyh.movieapp.adapter.SearchHistoryImageSliderAdapter;
import com.kyh.movieapp.databinding.FragmentHomeBinding;
import com.kyh.movieapp.model.repositories.RepositoryHome;

public class HomeViewModel {
    private static HomeViewModel homeViewModel;

    private FragmentHomeBinding binding;
    private Context context;

    private HomeViewModel(LayoutInflater inflater, Context context) {
        this.binding = FragmentHomeBinding.inflate(inflater);
        this.context = RepositoryHome.getContext();

        binding.homeSearchHistory.setLayoutManager(new GridLayoutManager(context, 1));
        binding.homeSearchHistory.setAdapter(new SearchHistoryAdapter());

        binding.homeLatestLikedMovieImageSlider.setAdapter(new SearchHistoryImageSliderAdapter());
        binding.homeLatestLikedMovieImageSlider.setOffscreenPageLimit(1);
        binding.homeLatestLikedMovieImageSlider.setCurrentItem(0);
        binding.homeLatestLikedMovieImageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
        setupIndicators(RepositoryHome.getImageHistories().size());
    }

    public static HomeViewModel getInstance() {
        return homeViewModel;
    }

    public static void setHomeViewModel(LayoutInflater inflater, Context context) {
        HomeViewModel.homeViewModel = new HomeViewModel(inflater, context);
    }

    public FragmentHomeBinding getBinding() {
        return binding;
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(context);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_slider_select_indicator_inactive));
            indicators[i].setLayoutParams(params);
            binding.homeLatestLikedMovieIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int indicatorNumber = binding.homeLatestLikedMovieIndicator.getChildCount();
        for (int i = 0; i < indicatorNumber; i++) {
            ImageView imageView = (ImageView) binding.homeLatestLikedMovieIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_slider_select_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_slider_select_indicator_inactive));
            }
        }
    }
}
