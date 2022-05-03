package com.example.nutrical;

public class newdailycaloriesModel {
    private int idDailyCal;
    private double dailyCalories, sumBMR;
    private String date;

    public newdailycaloriesModel() {
    }

    //constructors
    public newdailycaloriesModel(int idDailyCal, double dailyCalories, double sumBMR, String date) {
        this.idDailyCal = idDailyCal;
        this.dailyCalories = dailyCalories;
        this.sumBMR = sumBMR;
        this.date = date;
    }

    //toString
    @Override
    public String toString() {
        return "" + date +
                "\t\t" + String.format("%.2f/%.2f", sumBMR, dailyCalories);

    }

    //setter and getters
    public int getIdDailyCal() {
        return idDailyCal;
    }

    public void setIdDailyCal(int idDailyCal) {
        this.idDailyCal = idDailyCal;
    }

    public double getDailyCalories() {
        return dailyCalories;
    }

    public void setDailyCalories(double dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public double getSumBMR() {
        return sumBMR;
    }

    public void setSumBMR(double sumBMR) {
        this.sumBMR = sumBMR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
