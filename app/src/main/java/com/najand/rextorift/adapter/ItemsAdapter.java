package com.najand.rextorift.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.najand.rextorift.R;
import com.najand.rextorift.model.Items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> implements Filterable {

    private List<Items> itemsList;
    private List<Items> copyItemsList;
    private ItemOnClickListener listener;


    public ItemsAdapter(List<Items> items, ItemOnClickListener listener) {
        this.itemsList = items;
        this.listener = listener;
        copyItemsList = new ArrayList<>(itemsList);

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.titleTextView.setText(itemsList.get(position).getTitle());
        holder.contentTextView.setText(itemsList.get(position).getExplanation());
        Picasso.get()
                .load(itemsList.get(position).getHdUrl())
                .into(holder.imageView);
        Log.i("tag_hd", "onBindViewHolder: "+itemsList.get(position).getHdUrl());
        holder.itemView.setOnClickListener(view -> {
            listener.onClick(itemsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public Filter getFilter() {
        return itemsFilter;
    }
    private Filter itemsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Items> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length()==0){
                filteredList.addAll(copyItemsList);
            }else {
                String filterString = charSequence.toString().toLowerCase().trim();
                for(Items item : copyItemsList){
                    if (item.getTitle().toLowerCase().contains(filterString))   filteredList.add(item);
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            itemsList.clear();
            itemsList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
