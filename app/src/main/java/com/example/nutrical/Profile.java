package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

import android.graphics.Color;

public class Profile extends AppCompatActivity {
    Button profilebtn_home, profilebtn_info, prof_reset;
    TextView profname, prof_age, prof_weight, prof_height, prof_gender, prof_AF, prof_bmr;
    ImageView genderpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("  NutriCal");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logov21_24x24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(Profile.this);

        profilebtn_home = findViewById(R.id.profilebtn_home);
        profilebtn_info = findViewById(R.id.profilebtn_info);
        prof_reset = findViewById(R.id.prof_reset);

        profname = findViewById(R.id.profiletxt);
        prof_age = findViewById(R.id.prof_age);
        prof_weight = findViewById(R.id.prof_weight);
        prof_height = findViewById(R.id.prof_height);
        prof_gender = findViewById(R.id.prof_gender);
        prof_AF = findViewById(R.id.prof_AF);
        prof_bmr = findViewById(R.id.prof_bmr);
        genderpic=findViewById(R.id.genderpic);

        setData();

        profilebtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Profile.this, Home.class);
                startActivity(intent_one);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        profilebtn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Profile.this, Info.class);
                startActivity(intent_one);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        prof_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Profile.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("This user will be deleted along with it's data")
                        .setConfirmText("Reset")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Successful")
                                        .setContentText("User has been deleted")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                dataBaseHelper.deleteAll();

                                Intent intent_one = new Intent(Profile.this, MainActivity.class);
                                startActivity(intent_one);
                                dataBaseHelper.close();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }

    public void setData() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(Profile.this);

        Cursor name = dataBaseHelper.getData();
        if (name.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer buffername = new StringBuffer();
        while (name.moveToNext()) {
            buffername.append("" + name.getString(1));
        }
        profname.setText(buffername);

        Cursor age = dataBaseHelper.getData();
        if (age.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferage = new StringBuffer();
        while (age.moveToNext()) {
            bufferage.append("Age: " + age.getString(2));
        }
        prof_age.setText(bufferage);

        Cursor weight = dataBaseHelper.getData();
        if (weight.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferaweight = new StringBuffer();
        while (weight.moveToNext()) {
            bufferaweight.append("Weight: " + weight.getString(3) + " kg");
        }
        prof_weight.setText(bufferaweight);

        Cursor height = dataBaseHelper.getData();
        if (height.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferaheight = new StringBuffer();
        while (height.moveToNext()) {
            bufferaheight.append("Height: " + height.getString(4) + " cm");
        }
        prof_height.setText(bufferaheight);

        Cursor gender = dataBaseHelper.getData();
        if (gender.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferagender = new StringBuffer();
        while (gender.moveToNext()) {
            bufferagender.append("Gender: " + gender.getString(5));
        }

        if(dataBaseHelper.getGender().equals(" Female")){
            genderpic.setImageResource(R.drawable.woman);
        }else if(dataBaseHelper.getGender().equals(" Male")){
            genderpic.setImageResource(R.drawable.man);
        }

        prof_gender.setText(bufferagender);

        Cursor af = dataBaseHelper.getData();
        if (af.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferAF = new StringBuffer();
        while (af.moveToNext()) {
            bufferAF.append("Activity Factor: \n" + af.getString(6));
        }
        prof_AF.setText(bufferAF);

        Cursor bmr = dataBaseHelper.getData();
        if (bmr.getCount() == 0) {
            //ERROR MESSAGE
            return;
        }
        StringBuffer bufferbmr = new StringBuffer();
        while (bmr.moveToNext()) {
            bufferbmr.append("BMR: " + bmr.getString(7) + " kCal/day");
        }
        prof_bmr.setText(bufferbmr);

        dataBaseHelper.close();
    }

}
