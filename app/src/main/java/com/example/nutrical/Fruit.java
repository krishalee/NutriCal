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

public class Fruit extends AppCompatActivity {
    RecyclerView mfRecyclerView;
    MyAdapterSnacks myAdapterSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        mfRecyclerView=findViewById(R.id.recyclerViewFruit);
        mfRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        myAdapterSnacks= new MyAdapterSnacks(this, getMyListSnack());
        mfRecyclerView.setAdapter(myAdapterSnacks);

        getSupportActionBar().setTitle("Fruits");
        overridePendingTransition(0, 0);


    }
    private ArrayList<ModelSnack> getMyListSnack(){

        ArrayList<ModelSnack> modelSnacks = new ArrayList<>();

        ModelSnack ms= new ModelSnack();
        ms.setTitles("Apple");
        ms.setDescriptions("52kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Atis");
        ms.setDescriptions("94kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Avocado");
        ms.setDescriptions("160kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Banana");
        ms.setDescriptions("89kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Buko Meat (coconut)");
        ms.setDescriptions("354kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Caimito (star apple)");
        ms.setDescriptions("67.2kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Chico (naseberry)");
        ms.setDescriptions("83kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Durian");
        ms.setDescriptions("40-50kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Grapes");
        ms.setDescriptions("67kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Guaybano");
        ms.setDescriptions("66kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Kalamansi");
        ms.setDescriptions("37kCal/pc");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Langka (jackfruit)");
        ms.setDescriptions("157kCal/cup");
        modelSnacks.add(ms);
        ms= new ModelSnack();

        ms.setTitles("Lanzones");
        ms.setDescriptions("40kCal/7pcs (100g)");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Makopa (java apple)");
        ms.setDescriptions("30kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Mango");
        ms.setDescriptions("60kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Mangosteen");
        ms.setDescriptions("72.96kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Marang");
        ms.setDescriptions("63-121kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Melon");
        ms.setDescriptions("34kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Orange");
        ms.setDescriptions("47kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Pakwan (watermelon)");
        ms.setDescriptions("30kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Papaya");
        ms.setDescriptions("43.57kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Peras");
        ms.setDescriptions("57kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Pineapple");
        ms.setDescriptions("50kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Ramburan");
        ms.setDescriptions("75kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Sampaloc (tamarind)");
        ms.setDescriptions("321kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Santol (cotton fruit)");
        ms.setDescriptions("321kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Singkamas");
        ms.setDescriptions("38kCal");
        modelSnacks.add(ms);
        ms= new ModelSnack();

        ms.setTitles("Siniguelas (spanish plum)");
        ms.setDescriptions("34kCal");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Strawberry");
        ms.setDescriptions("32kCal/cup");
        modelSnacks.add(ms);

        ms= new ModelSnack();
        ms.setTitles("Suha (pomelo)");
        ms.setDescriptions("38kCal");
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