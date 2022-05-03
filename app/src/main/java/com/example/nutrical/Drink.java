package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class Drink extends AppCompatActivity {
    RecyclerView mdRecyclerView;
    MyAdapterSnacks myAdapterSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        mdRecyclerView=findViewById(R.id.recyclerViewDrinks);
        mdRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        myAdapterSnacks= new MyAdapterSnacks(this, getMyListSnack());
        mdRecyclerView.setAdapter(myAdapterSnacks);

        getSupportActionBar().setTitle("Drinks");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(0, 0);


    }
    private ArrayList<ModelSnack> getMyListSnack(){

        ArrayList<ModelSnack> modelSnacks = new ArrayList<>();

        ModelSnack ms= new ModelSnack();
        ms.setTitles("Coffee");
        ms.setDescriptions("2 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Sprite");
        ms.setDescriptions("131 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Beer");
        ms.setDescriptions("153 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("7up");
        ms.setDescriptions("156 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Apple Juice");
        ms.setDescriptions("110 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cappuccino");
        ms.setDescriptions("74 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Chocolate Milk");
        ms.setDescriptions("190 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Coconut Water");
        ms.setDescriptions("10 kCal/100ml");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Coke");
        ms.setDescriptions("149 kCal/ 12Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Coffee w/ milk");
        ms.setDescriptions("115 kCal/250ml");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cranberry Juice");
        ms.setDescriptions("115 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Diet Coke");
        ms.setDescriptions("4 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Energy Drink");
        ms.setDescriptions("108 kCal/8 Oz.(240ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Fanta");
        ms.setDescriptions("138 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Fruit Smoothie");
        ms.setDescriptions("162 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Gatorade ");
        ms.setDescriptions("89 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Grape Juice");
        ms.setDescriptions("152 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Ice Tea");
        ms.setDescriptions("96 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Kool-Aid");
        ms.setDescriptions("62 kCal/8 Oz.(237ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Lemonade");
        ms.setDescriptions("149 kCal/8 Oz.(237ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Milk");
        ms.setDescriptions("149 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Minute Maid");
        ms.setDescriptions("110 kCal/8 Oz(240ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Mug Root Beer");
        ms.setDescriptions("101 kCal/8 Oz.(240ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Orange Juice");
        ms.setDescriptions("110 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Pepsi");
        ms.setDescriptions("156 kCal/12 Oz.(355ml)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Red Wine");
        ms.setDescriptions("200 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Root Beer");
        ms.setDescriptions("101 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Soy Milk ");
        ms.setDescriptions("127 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Tomato Juice");
        ms.setDescriptions("50 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("White Wine");
        ms.setDescriptions("128 kCal/5 Oz.(145ml)");
        modelSnacks.add(ms);
        return modelSnacks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                myAdapterSnacks.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapterSnacks.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();
        if(id==R.id.sorting){
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}