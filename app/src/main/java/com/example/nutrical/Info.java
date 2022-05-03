package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Info extends AppCompatActivity {
    Button infobtn_home, infobtn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle("  NutriCal");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logov21_24x24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        infobtn_home = findViewById(R.id.infobtn_home);
        infobtn_profile = findViewById(R.id.infobtn_profile);

        infobtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Info.this, Home.class);
                startActivity(intent_one);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        infobtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Info.this, Profile.class);
                startActivity(intent_one);
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }
}