package com.najand.rextorift.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.najand.rextorift.R;
import com.najand.rextorift.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Items> items;
    private ItemOnClickListener listener;


    public ItemsAdapter(List<Items> items, ItemOnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.titleTextView.setText(items.get(position).getTitle());
        holder.contentTextView.setText(items.get(position).getExplanation());
        Picasso.get()
                .load(items.get(position).getHdUrl())
                .into(holder.imageView);
        Log.i("tag_hd", "onBindViewHolder: "+items.get(position).getHdUrl());
        holder.itemView.setOnClickListener(view -> {
            listener.onClick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView contentTextView;
        public ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_title);
            contentTextView = itemView.findViewById(R.id.item_content);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }
}
