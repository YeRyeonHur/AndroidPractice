package com.kyh.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.movieapp.R;
import com.kyh.movieapp.handler.SearchItemOnClickListener;
import com.kyh.movieapp.model.dtos.DTOMovie;
import com.kyh.movieapp.util.ImagePutter;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private static SearchItemOnClickListener listener;

    private ArrayList<DTOMovie> data;
    private Context context;

    public SearchResultAdapter(ArrayList<DTOMovie> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_search_result, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DTOMovie dtoMovie = data.get(position);
        holder.setItem(context, dtoMovie);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setListener(SearchItemOnClickListener listener) {
        SearchResultAdapter.listener = listener;
    }

    public DTOMovie getItem(int position) {
        return data.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, getAdapterPosition());
                    }
                }
            });
        }

        public void setItem(Context context, DTOMovie dtoMovie) {
            ((TextView)itemView.findViewById(R.id.searchResultItemTitle)).setText(dtoMovie.getTitle());
            ((TextView)itemView.findViewById(R.id.searchResultItemRating)).setText(dtoMovie.getUserRating() + "");
            ImagePutter.putImage(context, dtoMovie.getImage(), itemView.findViewById(R.id.searchResultItemImage));
        }
    }
}
