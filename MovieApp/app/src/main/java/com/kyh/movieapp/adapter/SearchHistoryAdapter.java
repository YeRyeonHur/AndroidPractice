package com.kyh.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.movieapp.R;
import com.kyh.movieapp.model.repositories.RepositoryHome;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_search_history, parent,false);
        return new SearchHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(RepositoryHome.getSearchHistories().get(position));
    }

    @Override
    public int getItemCount() {
        return RepositoryHome.getSearchHistories().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setItem(String history) {
            ((TextView)itemView.findViewById(R.id.itemSearchHistoryText)).setText(history);

            itemView.findViewById(R.id.itemSearchHistoryText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : History 클릭 하면 검색을 바로 실행
                }
            });

            itemView.findViewById(R.id.itemSearchHistoryDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RepositoryHome.getSearchHistories().remove(history);
                    itemView.setVisibility(View.GONE);
                }
            });
        }
    }
}
