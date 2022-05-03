package com.example.nutrical;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String NEWUSER_TABLE = "NEWUSER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_USER_WEIGHT = "USER_WEIGHT";
    public static final String COLUMN_USER_HEIGHT = "USER_HEIGHT";
    public static final String COLUMN_USER_GENDER = "USER_GENDER";
    public static final String COLUMN_USER_AF = "USER_AF";
    public static final String COLUMN_USER_BMR = "USER_BMR";

    public static final String NEWCALORIES_TABLE = "NEWCALORIES_TABLE";
    public static final String COLUMN_CALORIESID = "CALORIESID";
    public static final String COLUMN_CALORIES = "COLUMN_CALORIES";
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String COLUMN_NOTES = "COLUMN_NOTES";

    public static final String NEWDAILYCALORIES_TABLE = "NEWDAILYCALORIES_TABLE";
    public static final String COLUMN_DAILYCALORIESID = "COLUMN_DAILYCALORIESID";
    public static final String COLUMN_DAILYCALORIESCOUNTER = "COLUMN_DAILYCALORIESCOUNTER";
    public static final String COLUMN_SUMCALORIES = "COLUMN_SUMCALORIES";
    public static final String COLUMN_DAILYDATE = "COLUMN_DAILYDATE";

    private static final String DATABASE_NAME = "newuser.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //this is the first time a database is accessed. There should a code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableStatement);
        db.execSQL(createTableCalories);
        db.execSQL(createTableDailyCalories);
    }

    public static String createTableStatement = "CREATE TABLE " + NEWUSER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_AGE + " INT, " + COLUMN_USER_WEIGHT + " DOUBLE, " + COLUMN_USER_HEIGHT +
            " DOUBLE, " + COLUMN_USER_GENDER + " TEXT, " + COLUMN_USER_AF + " TEXT, " + COLUMN_USER_BMR + " DOUBLE)";

    public static String createTableCalories = "CREATE TABLE " + NEWCALORIES_TABLE + " (" + COLUMN_CALORIESID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CALORIES + " DOUBLE, " + COLUMN_DATE + " TEXT, "+ COLUMN_NOTES + " TEXT)";

    public static String createTableDailyCalories = "CREATE TABLE " + NEWDAILYCALORIES_TABLE + " (" + COLUMN_DAILYCALORIESID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DAILYCALORIESCOUNTER + " DOUBLE, " + COLUMN_SUMCALORIES + " DOUBLE, " + COLUMN_DAILYDATE + " TEXT)";

    //this is called if the database version number changes. It prevents previous users apps form breaking when you change the database user design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    ///////////////////////NEW USER//////////////////////
    public boolean addOne(newuserModel newuserModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, newuserModel.getName());
        cv.put(COLUMN_USER_AGE, newuserModel.getAge());
        cv.put(COLUMN_USER_WEIGHT, newuserModel.getWeight());
        cv.put(COLUMN_USER_HEIGHT, newuserModel.getHeight());
        cv.put(COLUMN_USER_GENDER, newuserModel.getGenderButton());
        cv.put(COLUMN_USER_AF, newuserModel.getActFactor());
        cv.put(COLUMN_USER_BMR, newuserModel.getBmr());

        long insert = db.insert(NEWUSER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NEWUSER_TABLE, null);
        return res;
    }

    public double getBMR() {
        double BMR = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWUSER_TABLE, null);
        if (cursor.moveToFirst()) BMR = cursor.getDouble(7);
        cursor.close();
        db.close();
        return BMR;
    }

    public String getGender() {
        String gen = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWUSER_TABLE, null);
        if (cursor.moveToFirst()) gen = cursor.getString(5);
        cursor.close();
        db.close();
        return gen;
    }
    //DELETE ENTIRE ROW
    public void deleteAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(" DELETE FROM " + "NEWUSER_TABLE" + " WHERE " + COLUMN_ID + "=\"" + getID() + "\";");
        db.execSQL(" DELETE FROM " + "NEWCALORIES_TABLE");
        db.execSQL(" DELETE FROM " + "NEWDAILYCALORIES_TABLE");
    }

    //GET ID NUMBER FROM THE TOP ROW
    public int getID() {
        SQLiteDatabase db = this.getReadableDatabase();
        String idString = " SELECT * FROM " + NEWUSER_TABLE;

        Cursor cursor = db.rawQuery(idString, null);
        int ID = 0;

        if (cursor.moveToFirst()) {
            do {
                ID = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //
        }
        cursor.close();
        return ID;
    }

    // Method that returns the first word
    public String firstWord(String input) {
        String name = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NEWUSER_TABLE, null);
        if (cursor.moveToFirst()) name = cursor.getString(1);

        cursor.close();
        db.close();

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                return name.substring(0, i);
            }
        }
        return name;
    }

    ///////////////NEW CALORIES///////////////////////
    public boolean addOneCal(newcaloriesModel newcaloriesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CALORIES, newcaloriesModel.getCalories());
        cv.put(COLUMN_DATE, newcaloriesModel.getDate());
        cv.put(COLUMN_NOTES, newcaloriesModel.getNotes());

        long insert = db.insert(NEWCALORIES_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataCal() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NEWCALORIES_TABLE, null);
        return res;
    }

    public double sumCalories() {
        double result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(" + COLUMN_CALORIES + ") from " + NEWCALORIES_TABLE, null);
        if (cursor.moveToFirst()) result = cursor.getDouble(0);
        cursor.close();
        db.close();
        return result;
    }

    public List<newcaloriesModel> getEveryone() {
        List<newcaloriesModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NEWCALORIES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int calID = cursor.getInt(0);
                double calories = cursor.getDouble(1);
                String dateTime = cursor.getString(2);
                String notes = cursor.getString(3);
                newcaloriesModel newcaloriesModel = new newcaloriesModel(calID, calories, dateTime, notes);
                returnList.add(newcaloriesModel);
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public int getIDCal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String idString = " SELECT * FROM " + NEWCALORIES_TABLE;

        Cursor cursor = db.rawQuery(idString, null);
        int ID = 0;

        if (cursor.moveToFirst()) {
            do {
                ID = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //
        }
        cursor.close();
        return ID;
    }

    public boolean deleteCalorie(newcaloriesModel newcaloriesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NEWCALORIES_TABLE + " WHERE " + COLUMN_CALORIESID + " = " + newcaloriesModel.getIdCal();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else
            return false;
    }

    public void resetCalories() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(" DELETE FROM " + "NEWCALORIES_TABLE");
    }

    ////////////////////NEW DAILY CALORIES////////////////////////
    public boolean addOneDailyCal(newdailycaloriesModel newdailycaloriesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DAILYCALORIESCOUNTER, newdailycaloriesModel.getDailyCalories());
        cv.put(COLUMN_SUMCALORIES, newdailycaloriesModel.getSumBMR());
        cv.put(COLUMN_DAILYDATE, newdailycaloriesModel.getDate());

        long insert = db.insert(NEWDAILYCALORIES_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataDailyCal() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NEWDAILYCALORIES_TABLE, null);
        return res;
    }

    public List<newdailycaloriesModel> getDailyCalories() {
        List<newdailycaloriesModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NEWDAILYCALORIES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int calID = cursor.getInt(0);
                double dailycalories = cursor.getDouble(1);
                double calories = cursor.getDouble(2);
                String dateTime = cursor.getString(3);
                newdailycaloriesModel newdailycaloriesModel = new newdailycaloriesModel(calID, dailycalories, calories, dateTime);
                returnList.add(newdailycaloriesModel);
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public int getIDDailyCal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String idString = " SELECT * FROM " + NEWDAILYCALORIES_TABLE;

        Cursor cursor = db.rawQuery(idString, null);
        int ID = 0;

        if (cursor.moveToFirst()) {
            do {
                ID = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //
        }
        cursor.close();
        return ID;
    }
    public double getDailyCal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String idString = " SELECT * FROM " + NEWUSER_TABLE;

        Cursor cursor = db.rawQuery(idString, null);
        double dailyCal = 0;

        if (cursor.moveToFirst()) {
            do {
                dailyCal = cursor.getDouble(7);
            } while (cursor.moveToNext());
        } else {
            //
        }
        cursor.close();
        return dailyCal;
    }

}

