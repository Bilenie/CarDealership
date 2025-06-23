package com.pluralsight.models;

public class Vehicle {

    //Set attribute/properties for the car
   //skull => that represent what it needs to become.

    private int vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private double odometer;
    private double price;
    private boolean sold;


    //Generate a constructor

    public Vehicle() {
    }

    public Vehicle(int vin, int year, String make, String model, String type, String color, double odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
        this.sold = false;
    }
    //Custom method  toString method show us error on mistake, make the code safe/clean, easy to read/understand when other want what my object is.
    @Override
    public String toString() {
        return String.format("VIN: %s | %d %s %s | Type: %s | Color: %s | Miles: %.2f | Price: $%.2f%s",
                vin, year, make, model, type, color, odometer, price,
                sold ? " [SOLD]" : "");
    }

    //Generate getter and setter

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }


}
