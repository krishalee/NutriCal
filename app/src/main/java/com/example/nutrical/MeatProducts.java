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

public class MeatProducts extends AppCompatActivity {
    RecyclerView mmRecyclerView;
    MyAdapterSnacks myAdapterSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_products);

        mmRecyclerView=findViewById(R.id.recyclerViewMeat);
        mmRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        myAdapterSnacks= new MyAdapterSnacks(this, getMyListSnack());
        mmRecyclerView.setAdapter(myAdapterSnacks);

        getSupportActionBar().setTitle("Common Meat/Product");
        overridePendingTransition(0, 0);


    }
    private ArrayList<ModelSnack> getMyListSnack(){

        ArrayList<ModelSnack> modelSnacks = new ArrayList<>();

        ModelSnack ms= new ModelSnack();
        ms.setTitles("Pork");
        ms.setDescriptions("242kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Beef");
        ms.setDescriptions("250kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Tuna");
        ms.setDescriptions("132kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Milk Fish");
        ms.setDescriptions("52.58kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Shrimp");
        ms.setDescriptions("99kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Crabs");
        ms.setDescriptions("97kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Hotdog");
        ms.setDescriptions("290kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Egg");
        ms.setDescriptions("155kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Bacon");
        ms.setDescriptions("541kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Ham");
        ms.setDescriptions("145kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Telapia");
        ms.setDescriptions("129kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Tahong");
        ms.setDescriptions("172kCal");
        modelSnacks.add(ms);
        ms= new ModelSnack();

        ms.setTitles("Squid");
        ms.setDescriptions("175kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Octopus");
        ms.setDescriptions("164kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Chevon (goat meat)");
        ms.setDescriptions("143kCal");
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