package com.example.nutrical;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);

        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(MyHolder myHolder, int i){

        myHolder.mTitle.setText(models.get(i).getTitle());
        myHolder.mDes.setText(models.get(i).getDescription());
        myHolder.mImageView.setImageResource(models.get(i).getImg());

        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent =  new Intent(c, Snack.class);
                        break;

                    case 1:
                        intent =  new Intent(c, Drink.class);
                        break;

                    case 2:
                        intent =  new Intent(c, Vegetables.class);
                        break;

                    case 3:
                        intent =  new Intent(c, MeatProducts.class);
                        break;

                    case 4:
                        intent =  new Intent(c, Fruit.class);
                        break;

                    default:
                        intent =  new Intent(c, Snack.class);
                        break;
                }
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount(){
        return models.size();
    }
}
