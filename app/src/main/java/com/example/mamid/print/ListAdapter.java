package com.example.mamid.print;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    List<Printer> list;

    public ListAdapter() {
        this.list = new ArrayList<>();
    }

    public void adddata(List<Printer> items)
    {
        list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.id.setText(list.get(position).getId());
        holder.email.setText(list.get(position).getOwnerId());
        holder.proxy.setText(list.get(position).getProxy());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
 class ListViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView proxy;
    TextView name;
    TextView email;
    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        id=itemView.findViewById(R.id.printer_id);
        proxy = itemView.findViewById(R.id.proxy);
        name=itemView.findViewById(R.id.name);
        email = itemView.findViewById(R.id.email);
    }
}