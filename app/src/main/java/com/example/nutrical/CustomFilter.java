package com.example.nutrical;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<ModelSnack> filterListsSnacks;
    MyAdapterSnacks adapter;


    public CustomFilter(ArrayList<ModelSnack> filterListsSnacks, MyAdapterSnacks myAdapterSnacks) {
        this.filterListsSnacks = filterListsSnacks;
        this.adapter = myAdapterSnacks;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        if(constraint!=null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();

            ArrayList<ModelSnack> filterModelsSnacks = new ArrayList<>();

            for(int i=0; i<filterListsSnacks.size(); i++){
                if(filterListsSnacks.get(i).getTitles().toUpperCase().contains(constraint)){
                    filterModelsSnacks.add(filterListsSnacks.get(i));
                }
            }
            results.count = filterModelsSnacks.size();
            results.values = filterModelsSnacks;
        }
        else{
            results.count = filterListsSnacks.size();
            results.values = filterListsSnacks;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.modelSnacks=(ArrayList<ModelSnack>) results.values;
        adapter.notifyDataSetChanged();
    }

}
