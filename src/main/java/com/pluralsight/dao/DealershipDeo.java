package com.pluralsight.dao;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.io.*;
import java.util.ArrayList;

import static com.pluralsight.userInterface.UiHelper.capitalizeFirstLetter;

public class DealershipDeo {

    private static final String FILE_PATH = "src/main/resources/inventory.csv";

//    public Dealership getDealership() {
//        Dealership dealership = null;
////collecting dealership information from the inventory.csv by splitting it and pass it to list and pass it to the dealership object we create.
//        try {
//            File file = new File(FILE_PATH);
//            if (!file.exists()) {
//                System.out.println("ERROR: File not found at " + file.getAbsolutePath());  // Added existence check
//                return null;
//            }
//
//            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
//            String line = reader.readLine();
//
//            if (line != null && !line.isBlank()) {
//
//                // First line: Dealership info
//                String[] parts = line.split("\\|");
//
//                if (parts.length < 3) {
//                    System.out.println("ERROR: Invalid dealership info format."); // Safety check
//                    reader.close();
//                    return null;
//                }
//
//                String name = parts[0];
//                String address = parts[1];
//                String phone = parts[2];
//                 dealership = new Dealership(name, address, phone);
//            }
//
//            // Remaining lines: Vehicle records
//            //collecting car detail information from the inventory.csv by splitting it and pass it to list and pass it to the dealership object we create.
//            while ((line = reader.readLine()) != null) {
//
//                if (line.isBlank()) continue;  //  Skip blank lines
//
//                String[] parts = line.split("\\|");
//
//                if (parts.length != 8) {
//                    System.out.println("Not correct format line: " + line); //  validation if read incorrect index
//                    continue;
//                }
//
//                try {
//
//                int vin = Integer.parseInt(parts[0]);
//                int year = Integer.parseInt(parts[1]);
//                String make = parts[2];
//                String model = parts[3];
//                String type = parts[4];
//                String color = parts[5];
//                double odometer = Double.parseDouble(parts[6]);
//                double price = Double.parseDouble(parts[7]);
//
//                    // Standardizing the capitalization of 'make', 'type', and 'model' (e.g., "Ford" instead of "ford")
//                    make = capitalizeFirstLetter(make); // Fix make capitalization
//                    model = capitalizeFirstLetter(model); // Fix model capitalization
//                    type = capitalizeFirstLetter(type); // Fix type capitalization
//
//                Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
//                dealership.addVehicle(newVehicle);
//                } catch (Exception e) {
//                    System.out.println("Skipping format error: " + line);;
//                }
//            }
//
//            reader.close();
//
//        } catch (Exception e) {
//            System.out.println("Error reading dealership file: " + e.getMessage());
//        }
//
//        return dealership;
//    }

//    public void saveDealership(Dealership dealership) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
//
//            // Write dealership info
//            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
//            writer.newLine();
//
//            // Write vehicles
//            ArrayList<Vehicle> inventory = dealership.getAllVehicle();
//            for (Vehicle vehicle : inventory) {
//                String line = vehicle.getVin() + "|" +
//                        vehicle.getYear() + "|" +
//                        vehicle.getMake() + "|" +
//                        vehicle.getModel() + "|" +
//                        vehicle.getType() + "|" +
//                        vehicle.getColor() + "|" +
//                        vehicle.getOdometer() + "|" +
//                        vehicle.getPrice();
//                writer.write(line);
//                writer.newLine();
//            }
//            System.out.println("Dealership data saved.");
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Error writing dealership file: " + e.getMessage());
//        }
//    }
//    //Custom method  => search method that return a list based on our filter or search.
//
//    public ArrayList<Vehicle> getVehiclesByPrice(String min , String max ) {
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        while (true) {  // keep asking until valid inputs
//            if (min.isEmpty() || max.isEmpty()) {
//                System.out.println("Input cannot be empty. Please enter price again.");
//                return matches;
//            }
//
//            //parse the string to double back so that it give use number
//
//            double minParse = Double.parseDouble(min);
//            double maxParse = Double.parseDouble(max);
//
//            for (int i = 0; i < inventory.size(); i++) {
//                Vehicle vehicle = inventory.get(i);
//                if (vehicle.getPrice() >= minParse && vehicle.getPrice() <= maxParse) {
//                    matches.add(vehicle);
//                }
//            }
//            return matches;
//        }
//    }
//
//    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        for(int i =0 ; i < inventory.size() ; i++){
//            Vehicle vehicle = inventory.get(i);
//            if(vehicle.getMake().equalsIgnoreCase(make)  && vehicle.getModel().equalsIgnoreCase(model)){
//                matches.add(vehicle);
//            }
//        }
//        return matches;
//    }
//
//    public ArrayList<Vehicle> getVehiclesByYear( int min , int max){
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        for(int i =0 ; i < inventory.size() ; i++){
//            Vehicle vehicle = inventory.get(i);
//            if(vehicle.getYear()>=min && vehicle.getYear()<= max){
//                matches.add(vehicle);
//            }
//        }
//        return matches;
//    }
//
//    public ArrayList<Vehicle> getVehiclesByColor(String color){
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        for(int i =0 ; i < inventory.size() ; i++){
//            Vehicle vehicle = inventory.get(i);
//            if(vehicle.getColor().equalsIgnoreCase(color) ){
//                matches.add(vehicle);
//            }
//        }
//        return matches;
//    }
//
//    public ArrayList<Vehicle> getVehiclesByMileage( double min , double max){
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        for(int i =0 ; i < inventory.size() ; i++){
//            Vehicle vehicle = inventory.get(i);
//            if(vehicle.getOdometer()>=min && vehicle.getOdometer()<= max){
//                matches.add(vehicle);
//            }
//        }
//        return matches;
//    }
//
//    public ArrayList<Vehicle> getVehiclesByType(String type){
//        ArrayList<Vehicle> matches = new ArrayList<>();
//        for(int i =0 ; i < inventory.size() ; i++){
//            Vehicle vehicle = inventory.get(i);
//            if(vehicle.getType().equalsIgnoreCase(type) ){
//                matches.add(vehicle);
//            }
//        }
//        return matches;
//    }
//    public ArrayList<Vehicle> getVehiclesByVin(String vin) {
//        ArrayList<Vehicle> matches = new ArrayList<>();
//
//        if (vin.isEmpty()) {
//            System.out.println("VIN input cannot be empty.");
//            return matches;
//        }
//
//        int vinParsed = Integer.parseInt(vin);
//
//        for (int i = 0; i < inventory.size(); i++) {
//            Vehicle vehicle = inventory.get(i);
//            if (vehicle.getVin() == vinParsed) {
//                matches.add(vehicle);
//            }
//        }
//
//        return matches;
//    }
//
//    public void addVehicle(Vehicle vehicle){
//        inventory.add(vehicle);
//    }
//    //
//    public ArrayList<Vehicle> getAllVehicle(){
//        return inventory;
//
//    }
//
//    public void removeVehicle(Vehicle vehicle) {
//        //leave empty for now
//
//        for (int i = 0; i < inventory.size(); i++) {//one by one it's replacing not storing overwritten not storing one is for one no duplicate
//            Vehicle vehicles = inventory.get(i);
//            if (vehicles.getVin() == vehicle.getVin()) {// is it the same hand color when find the same color type
//                inventory.remove(i); // stop after removing first match
//                break;
//            }
//        }
//    }

}
