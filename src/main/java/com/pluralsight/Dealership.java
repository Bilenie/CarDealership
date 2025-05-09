package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    //Set attribute for the dealership
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    //Generate Constructor


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
       this.inventory = new ArrayList<>();

    }

    //Cstom method for addVehicle(Vehicle v) & getAllVehicles() & // remove method => search method that return a list
    public ArrayList<Vehicle> searchByPrice(double min, double max) {
        return null; //will add real logic later
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    //
    public ArrayList<Vehicle> getAllVehicle(){
        return inventory;

    }

    public void removeVehicle(String vin) {
        //leave empty for now
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vehicle.getVin()) {
                inventory.remove(i); // stop after removing first match
                break;
            }
        }

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
}
