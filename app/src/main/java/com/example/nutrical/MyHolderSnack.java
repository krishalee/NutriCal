package com.example.nutrical;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolderSnack extends RecyclerView.ViewHolder implements  View.OnClickListener {

    TextView msTitle, msDes;
    ItemClickListener itemClickListener;

    public MyHolderSnack(View itemView) {
        super(itemView);
        this.msTitle =itemView.findViewById(R.id.titleIvS);
        this.msDes =itemView.findViewById(R.id.descriptopnTvS);
    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClickListener(v, getLayoutPosition());


    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener=ic;
    }
}
