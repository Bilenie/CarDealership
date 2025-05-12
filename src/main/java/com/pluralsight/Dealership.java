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

    //Custom method  => search method that return a list based on our filter or search.

    public ArrayList<Vehicle> getVehiclesByPrice(double min , double max ){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getPrice()>=min && vehicle.getPrice()<= max){
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getMake().equalsIgnoreCase(make)  && vehicle.getModel().equalsIgnoreCase(model)){
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByYear( int min , int max){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getYear()>=min && vehicle.getYear()<= max){
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getColor().equalsIgnoreCase(color) ){
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMileage( double min , double max){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getOdometer()>=min && vehicle.getOdometer()<= max){
                matches.add(vehicle);
            }
        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type){
        ArrayList<Vehicle> matches = new ArrayList<>();
        for(int i =0 ; i < inventory.size() ; i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicle.getType().equalsIgnoreCase(type) ){
                matches.add(vehicle);
            }
        }
        return matches;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    //
    public ArrayList<Vehicle> getAllVehicle(){
        return inventory;

    }

    public void removeVehicle(Vehicle vehicle) {
        //leave empty for now

        for (int i = 0; i < inventory.size(); i++) {//one by one it's replacing not storing overwritten not storing one is for one no duplicate
            Vehicle vehicles = inventory.get(i);
            if (vehicles.getVin() == vehicle.getVin()) {// is it the same hand color when find the same color type
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
