package com.example.nutrical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyAdapterSnacks extends RecyclerView.Adapter<MyHolderSnack> implements Filterable {

    Context c;
    ArrayList<ModelSnack> modelSnacks, filterListsSnacks;
    CustomFilter filter;

    public MyAdapterSnacks(Context c, ArrayList<ModelSnack> modelSnacks) {
        this.c = c;
        this.modelSnacks = modelSnacks;
        this.filterListsSnacks = modelSnacks;
    }

    @Override
    public MyHolderSnack onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.snackrow,null);
        return new MyHolderSnack(view);
    }

    @Override
    public void onBindViewHolder( MyHolderSnack holder, int position) {
        holder.msTitle.setText(modelSnacks.get(position).getTitles());
        holder.msDes.setText(modelSnacks.get(position).getDescriptions());

    }

    @Override
    public int getItemCount() {
        return modelSnacks.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter(filterListsSnacks, this);
        }

        return filter;
    }
}
