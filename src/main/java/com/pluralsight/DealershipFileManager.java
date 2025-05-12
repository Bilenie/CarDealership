package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;
//collecting dealership information from the inventory.csv by splitting it and pass it to list and pass it to the dealership object we create.
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("ERROR: File not found at " + file.getAbsolutePath());  // Added existence check
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();

            if (line != null && !line.isBlank()) {

                // First line: Dealership info
                String[] parts = line.split("\\|");

                if (parts.length < 3) {
                    System.out.println("ERROR: Invalid dealership info format."); // Safety check
                    reader.close();
                    return null;
                }

                String name = parts[0];
                String address = parts[1];
                String phone = parts[2];
                 dealership = new Dealership(name, address, phone);
            }

            // Remaining lines: Vehicle records
            //collecting car detail information from the inventory.csv by splitting it and pass it to list and pass it to the dealership object we create.
            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) continue;  //  Skip blank lines

                String[] parts = line.split("\\|");

                if (parts.length != 8) {
                    System.out.println("Not correct format line: " + line); //  validation if read incorrect index
                    continue;
                }

                try {

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                double odometer = Double.parseDouble(parts[6]);
                double price = Double.parseDouble(parts[7]);

                    // Standardizing the capitalization of 'make', 'type', and 'model' (e.g., "Ford" instead of "ford")
                    make = capitalizeFirstLetter(make); // Fix make capitalization
                    model = capitalizeFirstLetter(model); // Fix model capitalization
                    type = capitalizeFirstLetter(type); // Fix type capitaliz

                Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(newVehicle);
                } catch (Exception e) {
                    System.out.println("Skipping format error: " + line);;
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            // Write dealership info
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write vehicles
            ArrayList<Vehicle> inventory = dealership.getAllVehicle();
            for (Vehicle vehicle : inventory) {
                String line = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Dealership data saved.");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }
//Method to assist with making the first letter of our string to capital to avoid error
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
