package com.pluralsight.models;

import java.util.ArrayList;

public class Dealership {
    //Set attribute for the dealership

    private int dealershipId;
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;//not holding but way of accessing it with condition.

    //Generate Constructor

    // Default constructor
    public Dealership() {
        this.inventory = new ArrayList<>();
    }

    public Dealership(int dealershipId,String name, String address, String phone) {

        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phone = phone;
       this.inventory = new ArrayList<>();

    }
    //Custom method
    public void addVehicle(Vehicle vehicle) {
        if (!inventory.contains(vehicle)) {
            inventory.add(vehicle);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", name, address, phone);
    }

    //Generate getter and setter

    public int getDealershipId() {
        return dealershipId;
    }
    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }
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

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }
    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

}
