package com.osicorp.adebayo_osipitan.view.list_ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;


import com.osicorp.adebayo_osipitan.R;

public class SimpleListAdapter extends Adapter<SimpleListAdapter.ListViewHolder> {

    private final LayoutInflater inflater;
    String[] items;
    private ListViewHolder holder;

    public SimpleListAdapter(String[] items, Activity context) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filter_item, parent, false);
        holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if(items.length>0)
            holder.setItems(items);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView textItem;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textItem);
        }

        public void setItems(String[] items) {
            textItem.setText(items[getAdapterPosition()]);
        }
    }
}
