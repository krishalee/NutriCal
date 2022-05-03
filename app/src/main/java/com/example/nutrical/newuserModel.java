package com.example.nutrical;

public class newuserModel {

    private int id, age;
    private String name, genderButton, actFactor;
    private double weight, height, bmr;

    //constructors
    public newuserModel(int id, String name, String genderButton, String actFactor, int age, double weight, double height, double bmr) {
        this.id = id;
        this.name = name;
        this.genderButton = genderButton;
        this.actFactor = actFactor;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bmr = bmr;
    }

    public newuserModel() {
    }

    //toString is necessary for printing the contents of a class project
    @Override
    public String toString() {
        return "newuserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genderButton='" + genderButton + '\'' +
                ", actFactor='" + actFactor + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", bmr=" + bmr +
                '}';
    }

    //Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderButton() {
        return genderButton;
    }

    public void setGenderButton(String genderButton) {
        this.genderButton = genderButton;
    }

    public String getActFactor() {
        return actFactor;
    }

    public void setActFactor(String actFactor) {
        this.actFactor = actFactor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmr() {return bmr;}

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }
}