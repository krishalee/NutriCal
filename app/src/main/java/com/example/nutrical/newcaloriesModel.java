package com.example.nutrical;

public class newcaloriesModel {
    private int idCal;
    private double calories;
    private String date, notes;

    public newcaloriesModel() {
    }

    //constructors
    public newcaloriesModel(int idCal, double calories, String date, String notes) {
        this.idCal = idCal;
        this.calories = calories;
        this.date = date;
        this.notes = notes;
    }

    //toString is necessary for printing the contents of a class project
    @Override
    public String toString() {
        return "" + calories + " kCal" + "\nNote: " + notes + "\n" +
                date + "\n";
    }

    //Getter and Setters
    public int getIdCal() {
        return idCal;
    }

    public void setIdCal(int idCal) {
        this.idCal = idCal;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
