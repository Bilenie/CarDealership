package com.pluralsight.models;

import java.util.ArrayList;

public class Dealership {
    //Set attribute for the dealership
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;//not holding but way of accessing it with condition.

    //Generate Constructor


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
       this.inventory = new ArrayList<>();

    }



    //Generate getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //sell and lease a vehicle
    // user select sell => info collected  => same for lease.
}
