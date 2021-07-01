package com.kyh.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.movieapp.R;
import com.kyh.movieapp.model.repositories.RepositoryHome;
import com.kyh.movieapp.util.ImagePutter;

public class SearchHistoryImageSliderAdapter extends RecyclerView.Adapter<SearchHistoryImageSliderAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_search_history_image_slider, parent,false);
        return new SearchHistoryImageSliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(RepositoryHome.getImageHistories().get(position));
    }

    @Override
    public int getItemCount() {
        return RepositoryHome.getImageHistories().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setItem(String imgPath) {
            ImagePutter.putImage(RepositoryHome.getContext(), imgPath, itemView.findViewById(R.id.ItemSearchHistoryImageSliderImage));
        }
    }
}
