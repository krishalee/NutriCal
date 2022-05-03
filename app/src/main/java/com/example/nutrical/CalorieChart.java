package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CalorieChart extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_chart);

        getSupportActionBar().setTitle("Calorie Chart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(0, 0);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter= new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

    }

    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Snacks");
        m.setDescription("Some chips, pizzas, burgers, streetfood etc.");
        m.setImg(R.drawable.snack);
        models.add(m);

        m = new Model();
        m.setTitle("Drinks");
        m.setDescription("Apple juice, beer, coffee, milk etc.");
        m.setImg(R.drawable.drink);
        models.add(m);

        m = new Model();
        m.setTitle("Vegetables");
        m.setDescription("Peas, potatoes, spinach, onions etc.");
        m.setImg(R.drawable.vegetable);
        models.add(m);

        m = new Model();
        m.setTitle("Meat/products");
        m.setDescription("Pork, beef, shrimp, eggs, ham etc.");
        m.setImg(R.drawable.meat);
        models.add(m);

        m = new Model();
        m.setTitle("Fruits");
        m.setDescription("Bananas, mango, oranges, avocado etc.");
        m.setImg(R.drawable.fruit);
        models.add(m);

        return models;
    }
}