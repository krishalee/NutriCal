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

public class Vegetables extends AppCompatActivity {
    RecyclerView mvRecyclerView;
    MyAdapterSnacks myAdapterSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);

        mvRecyclerView=findViewById(R.id.recyclerViewVegetables);
        mvRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        myAdapterSnacks= new MyAdapterSnacks(this, getMyListSnack());
        mvRecyclerView.setAdapter(myAdapterSnacks);

        getSupportActionBar().setTitle("Vegetables");
        overridePendingTransition(0, 0);


    }
    private ArrayList<ModelSnack> getMyListSnack(){

        ArrayList<ModelSnack> modelSnacks = new ArrayList<>();

        ModelSnack ms= new ModelSnack();
        ms.setTitles("Artichoke");
        ms.setDescriptions("60 kCal/pc (128g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Asparagus");
        ms.setDescriptions("27 kCal/cup (134g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Bell Peppers");
        ms.setDescriptions("38 kCal/cup (135g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Broccoli");
        ms.setDescriptions("31 kCal/cup (91g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Brussel Sprouts");
        ms.setDescriptions("38 kCal/cup (88g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cabbage");
        ms.setDescriptions("22 kCal/cup (90g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Carrots");
        ms.setDescriptions("53 kCal/cup (128g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cauliflower");
        ms.setDescriptions("27 kCal/pc (107g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Celery");
        ms.setDescriptions("14 kCal/cup (100g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Corn");
        ms.setDescriptions("177 kCal/cup (164g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cucumber");
        ms.setDescriptions("16 kCal/cup (100g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Fennel");
        ms.setDescriptions("11 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Green Beans");
        ms.setDescriptions("30 kCal/cup (100g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Green Olives");
        ms.setDescriptions("130 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Kale");
        ms.setDescriptions("28 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Lettuce");
        ms.setDescriptions("5 kCal/cup (36g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Mushroom");
        ms.setDescriptions("21 kCal/cup (96g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Okra");
        ms.setDescriptions("23 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Onion");
        ms.setDescriptions("46 kCal/cup (115g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Peas");
        ms.setDescriptions("118 kCal/cup (145g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Potatoes");
        ms.setDescriptions("116 kCal/cup diced (150g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Pumpkin");
        ms.setDescriptions("30 kCal/cup (116g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Radishes");
        ms.setDescriptions("18 kCal/cup (116g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Spinach");
        ms.setDescriptions("7 kCal/cup (30g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Sweet Potatoes");
        ms.setDescriptions("129 kCal/cup (150g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Tomatoes");
        ms.setDescriptions("36 kCal/cup (180g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Turnips");
        ms.setDescriptions("36 kCal/cup (130g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Winter Squash");
        ms.setDescriptions("40 kCal/cup (116g)");
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