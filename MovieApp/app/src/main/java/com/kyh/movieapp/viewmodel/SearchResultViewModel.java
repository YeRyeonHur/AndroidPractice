package com.kyh.movieapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.movieapp.adapter.SearchResultAdapter;
import com.kyh.movieapp.databinding.FragmentSearchResultBinding;
import com.kyh.movieapp.handler.SearchItemOnClickListener;
import com.kyh.movieapp.model.repositories.RepositoryMovieDescription;
import com.kyh.movieapp.model.repositories.RepositorySearchResult;
import com.kyh.movieapp.view.MovieDescriptionActivity;

@SuppressLint("StaticFieldLeak")
public class SearchResultViewModel {
    public static final int SCROLL_DIRECTION_TO_BOTTOM = 1;

    private static SearchResultViewModel searchResultViewModel;

    private FragmentSearchResultBinding binding;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SearchResultViewModel(LayoutInflater inflater) {
        this.binding = FragmentSearchResultBinding.inflate(inflater);
        this.context = RepositorySearchResult.getContext();

        binding.searchResultRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        SearchResultAdapter adapter = new SearchResultAdapter(RepositorySearchResult.getItems(), context);
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
        binding.searchResultRecyclerView.setAdapter(adapter);

        binding.searchResultLoading.setVisibility(View.GONE);

        binding.searchResultRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(!binding.searchResultRecyclerView.canScrollVertically(SCROLL_DIRECTION_TO_BOTTOM)) {
                    SearchResultViewModel.getInstance().getBinding().searchResultLoading.setVisibility(View.VISIBLE);

                    SearchViewModel.getInstance().requestAddItem();

                    SearchResultAdapter adapter = new SearchResultAdapter(RepositorySearchResult.getItems(), context);
                    adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                    binding.searchResultRecyclerView.setAdapter(adapter);
                }
            }
        });
    }

    public static SearchResultViewModel getInstance() {
        return searchResultViewModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setSearchResultViewModel(LayoutInflater inflater) {
        SearchResultViewModel.searchResultViewModel = new SearchResultViewModel(inflater);
    }

    public FragmentSearchResultBinding getBinding() {
        return binding;
    }
}
