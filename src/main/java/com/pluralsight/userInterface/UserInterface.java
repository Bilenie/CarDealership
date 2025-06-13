package com.pluralsight.userInterface;

import com.pluralsight.dao.ContractFileManager;
import com.pluralsight.dao.DealershipFileManager;
import com.pluralsight.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.pluralsight.userInterface.UiHelper.*;

public class UserInterface {
    //set it static
    //object..
    private static Dealership dealership;

    // Private constructor to prevent instantiation
    public UserInterface() {
    }

    // This method launches the menu and keeps looping until the user exits
    public static void display() {
        init();  // Load the dealership from file
        Scanner scanner = new Scanner(System.in);
        boolean menuRunning = true;


        while (menuRunning) {
            printMainMenu();
            System.out.print("Choose an option: ");

            // Wrap input in try-catch to avoid InputMismatchException if user types letters!
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Oops! That wasn't a number. Please try again.");
                scanner.nextLine(); // clean bad input
            }

            switch (choice) {
                case 1:
                    searchVehicles();
                    break;
                case 2:
                    processSaleOrLease(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for visiting!");
                    menuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

   public static void printMainMenu() {
        System.out.println("\n=== Welcome to Bilenie Auto !!! ===");
        System.out.println("\n==== DEALERSHIP MENU ====");
        System.out.println("1. Search vehicles");
        System.out.println("2. Sell/Lease a vehicle");
        System.out.println("3. Exit");
    }

    // Vehicle search logic
   public static void searchVehicles() {
        // Show menu options
        System.out.println("\n=== Search Vehicle Filters ===");
        System.out.println("1 - Find vehicles by price");
        System.out.println("2 - Find vehicles by make/model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List all vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter option: ");

        // Read user input
        int option;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
        } else {
            scanner.nextLine(); // discard invalid input
            option = -1;
        }

        // Route to the appropriate method
        switch (option) {
            case 1:
                processGetByPriceRequest();
                break;
            case 2:
                processGetByMakeModelRequest();
                break;
            case 3:
                processGetByYearRequest();
                break;
            case 4:
                processGetByColorRequest();
                break;
            case 5:
                processGetByMileageRequest();
                break;
            case 6:
                processGetByVehicleTypeRequest();
                break;
            case 7:
                processAllVehiclesRequest();
                break;
            case 8:
                processAddVehicleRequest();
                break;
            case 9:
                processRemoveVehicleRequest();
                break;
            case 99:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
        }

    }


    // Loads the dealership inventory from the file using DealershipFileManager
    private static void init() {
        UserInterface ui = new UserInterface();
        UiHelper.showLoadingSpinner(1000); // 1 second spinner
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    // Search for vehicles within a price range
    public static void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        String min = scanner.nextLine();
        System.out.print("Enter maximum price: ");
        String max = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by make and model
    public static void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(results);
    }

    // Search for vehicles by year range
    public static void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> results = dealership.getVehiclesByYear(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by color
    public static void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }

    // Search for vehicles by mileage
    public static void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double max = scanner.nextDouble();

        ArrayList<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by type
    public static void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> results = dealership.getVehiclesByType(type);
        displayVehicles(results);
    }

    // Show all vehicles in the dealership
    public static void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicle();
        // Check if the list is empty
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available in the dealership.");
        } else {
            System.out.println(vehicles.get(0));
            displayVehicles(vehicles);//
        }
    }

    // Add a new vehicle to the inventory
    public static void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter mileage: ");
        double mileage = scanner.nextDouble();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(newVehicle);

        UserInterface ui = new UserInterface();
        showLoadingSpinner(1000); // 1 second spinner

        DealershipFileManager dfm = new DealershipFileManager();
        dfm.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");
        waitForEnter();
    }

    // Remove a vehicle by VIN
    public static void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;

        // Loop through all vehicles to find the one matching the VIN

        for (Vehicle currentVehicle : dealership.getAllVehicle()) {
            if (currentVehicle.getVin() == vin) {
                vehicleToRemove = currentVehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);

            UserInterface ui = new UserInterface();
            showLoadingSpinner(1000); // 1 second spinner

            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
            waitForEnter();
        } else {
            System.out.println("Vehicle with that VIN not found.");
        }
    }

    // Sell or lease vehicle method
    public static void processSaleOrLease(Scanner scanner) {
        System.out.print("Enter contract date (MM/DD/YYYY): ");
        String date = scanner.nextLine();

        System.out.print("Customer full name: ");
        String name = scanner.nextLine();

        System.out.print("Customer email: ");
        String email = scanner.nextLine();

        List<Vehicle> inventory = dealership.getAllVehicle();
        if (inventory.isEmpty()) {
            System.out.println("No vehicles available to sell.");
            return;
        }

        System.out.println("\nAvailable Vehicles:");
        displayVehicles(new ArrayList<>(inventory));

        System.out.print("\nEnter VIN of vehicle to sell or lease: ");
        String vin = scanner.nextLine().trim();

        if (vin.isEmpty()) {
            System.out.println("VIN input cannot be empty.");
            return;
        }

        ArrayList<Vehicle> results = dealership.getVehiclesByVin(vin);
        if (results == null || results.isEmpty()) {
            System.out.println("Vehicle not found.");
            return;
        }

        Vehicle selectedVehicle = results.get(0);

        System.out.print("Is this a Sale or Lease? (Enter 'sale' or 'lease'): ");
        String contractType = scanner.nextLine().trim().toLowerCase();

        Contract contract;

        if (contractType.equals("sale")) {
            System.out.print("Does customer need financing? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            boolean needsFinance = answer.equals("yes");
            contract = new SalesContract(date, name, email, selectedVehicle, needsFinance);
        } else if (contractType.equals("lease")) {
            contract = new LeaseContract(date, name, email, selectedVehicle);
        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        ContractFileManager contractManager = new ContractFileManager();
        contractManager.saveContract(contract);

        dealership.removeVehicle(selectedVehicle);
        DealershipFileManager dealershipManager = new DealershipFileManager();
        dealershipManager.saveDealership(dealership);

        System.out.println("\nContract saved and vehicle removed from inventory.");
        System.out.println("Contract Details:");
        System.out.println(contract.getContractDetails());
    }


    // Utility method to display a list of vehicles, or a message if empty
    private static void displayVehicles(ArrayList<Vehicle> vehicleList) {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle currentVehicle : vehicleList) {
                System.out.println(currentVehicle);
            }
        }
        waitForEnter();
    }


}

