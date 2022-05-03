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

public class Snack extends AppCompatActivity {
    RecyclerView msRecyclerView;
    MyAdapterSnacks myAdapterSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        msRecyclerView=findViewById(R.id.recyclerViewSnacks);
        msRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        myAdapterSnacks= new MyAdapterSnacks(this, getMyListSnack());
        msRecyclerView.setAdapter(myAdapterSnacks);

        getSupportActionBar().setTitle("Snacks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(0, 0);


    }
    private ArrayList<ModelSnack> getMyListSnack(){

        ArrayList<ModelSnack> modelSnacks = new ArrayList<>();

        ModelSnack ms= new ModelSnack();
        ms.setTitles("Pizza");
        ms.setDescriptions("180 kCal/slice");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Burger");
        ms.setDescriptions("296 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Fish Tempura");
        ms.setDescriptions("180 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Kwek kwek");
        ms.setDescriptions("33 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Fish balls");
        ms.setDescriptions("296 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Kikiam");
        ms.setDescriptions("164 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Pancake");
        ms.setDescriptions("108 kCal/pc");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Cake");
        ms.setDescriptions("370 kCal/slice");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("French Fries");
        ms.setDescriptions("142.86 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Siomai");
        ms.setDescriptions("179.87 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Siopao (bola-bola)");
        ms.setDescriptions("324 kCal/head");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Ramen");
        ms.setDescriptions("190 kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Bibingka");
        ms.setDescriptions("218 kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Suman");
        ms.setDescriptions("84 kCal/50g");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Puto");
        ms.setDescriptions("150 kCal/pc");
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