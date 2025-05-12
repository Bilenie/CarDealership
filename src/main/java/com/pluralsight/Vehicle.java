package com.pluralsight;

public class Vehicle {

    //Set attribute/properties for the car
//skull => that represent what it need to become.
    private int vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private double odometer;
    private double price;

//Generate a constructor

    public Vehicle(int vin, int year, String make, String model, String type, String color, double odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }
    //Custom method  toString method show us error on mistake, make the code safe/clean, easy to read/understand when other want what my object is.
    @Override
    public String toString() {
        return "Vehicle{" +
                "vin : " + vin +
                ", year : " + year +
                ", make : " + make + '\'' +
                ", model : " + model + '\'' +
                ", type : " + type + '\'' +
                ", color : " + color + '\'' +
                ", odometer : " + odometer +
                ", price : " + price +
                '}';
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
}
