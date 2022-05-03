package com.example.nutrical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    ImageView mImageView;
    TextView mTitle, mDes;
    ItemClickListener itemClickListener;
    MyHolder(View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.titleIv);
        this.mDes = itemView.findViewById(R.id.descriptopnTv);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClickListener(v, getLayoutPosition());

    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener=ic;
    }
}
