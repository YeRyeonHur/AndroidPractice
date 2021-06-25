package com.kyh.knucommunity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.knucommunity.R;
import com.kyh.knucommunity.handler.OnUserDataItemClickListener;
import com.kyh.knucommunity.model.DTOUser;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
implements OnUserDataItemClickListener {
    static OnUserDataItemClickListener Listener;

    ArrayList<DTOUser> data;

    public RecyclerViewAdapter(ArrayList<DTOUser> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DTOUser item = data.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(Listener != null) {
            Listener.onItemClick(holder, view, position);
        }
    }

    public DTOUser getItem(int position) {
        return data.get(position);
    }

    public void setListener(OnUserDataItemClickListener listener) {
        Listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView index, name, tel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.index);
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Listener != null) {
                        Listener.onItemClick(ViewHolder.this, v, getAdapterPosition());
                    }
                }
            });
        }

        public void setItem(DTOUser item) {
            index.setText(Integer.toString(item.getIndex()));
            name.setText(item.getName());
            tel.setText(item.getTel());
        }
    }
}
