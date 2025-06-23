package com.pluralsight.userInterface;

import com.pluralsight.dao.ContractDao;
import com.pluralsight.dao.DealershipDeo;
import com.pluralsight.dao.VehicleDao;
import com.pluralsight.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.pluralsight.userInterface.UiHelper.*;

public class UserInterface {
    //set it static
    //object..
    private static VehicleDao vehicleDao;
    private static ContractDao contractDao;
    private static DealershipDeo dealershipDao;

    public static BasicDataSource dataSource = new BasicDataSource();
    // Private constructor to prevent instantiation
    public UserInterface() {
    }

    // This method launches the menu and keeps looping until the user exits
    public static void display() {



        String url = "jdbc:mysql://localhost:3306/cardealershipdb";
        String username = System.getProperty("dbUsername");
        String password = System.getProperty("dbPassword");

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        vehicleDao = new VehicleDao(dataSource);
        contractDao = new ContractDao(dataSource);
        dealershipDao = new DealershipDeo(dataSource);//forgot to create instance

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
        System.out.println("10 - Display Dealerships");
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
            case 10:
                processAllDealershipRequest();
                break;
            case 99:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
        }

    }

    // Search for vehicles within a price range
    public static void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        String min = scanner.nextLine();
        System.out.print("Enter maximum price: ");
        String max = scanner.nextLine();

        double minPrice = Double.parseDouble(min);
        double maxPrice = Double.parseDouble(max);


        ArrayList<Vehicle> results = vehicleDao.getVehicleByPrice(minPrice, maxPrice);
        displayVehicles(results);
    }

    // Search for vehicles by make and model
    public static void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> results = vehicleDao.getVehicleByMakeModel(make, model);
        displayVehicles(results);
    }

    // Search for vehicles by year range
    public static void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();

        List<Vehicle> results = vehicleDao.getVehicleByYear(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by color
    public static void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> results = vehicleDao.getVehicleByColor(color);
        displayVehicles(results);
    }

    // Search for vehicles by mileage
    public static void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double max = scanner.nextDouble();

        List<Vehicle> results = vehicleDao.getVehicleByMileage(min, max);
        displayVehicles(results);
    }

    // Search for vehicles by type
    public static void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> results = vehicleDao.getVehicleByType(type);
        displayVehicles(results);
    }

    // Show all vehicles in the dealership
    public static void processAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        // Check if the list is empty
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available in the dealership.");
        } else {
            displayVehicles(vehicles);//
        }
    }
    // Show all vehicles in the dealership
    public static void processAllDealershipRequest() {

        List<Dealership> dealerships = dealershipDao.getAllDealerships(dataSource);
        // Check if the list is empty
        if (dealerships.isEmpty()) {
            System.out.println("No Dealership available in the Database.");
        } else {
            displayDealership(dealerships);//
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
        vehicleDao.addVehicle(newVehicle);

        showLoadingSpinner(1000); // 1 second spinner

        System.out.println("Vehicle added successfully.");
        waitForEnter();
    }

    // Remove a vehicle by VIN
    public static void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;//empty box

        // Loop through all vehicles to find the one matching the VIN

        for (Vehicle currentVehicle : vehicleDao.getAllVehicles()) {
            if (currentVehicle.getVin() == vin) {
                vehicleToRemove = currentVehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            vehicleDao.removeByVin(vehicleToRemove);

            showLoadingSpinner(1000); // 1 second spinner

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

        List<Vehicle> inventory = vehicleDao.getAllVehicles();
        if (inventory.isEmpty()) {
            System.out.println("No vehicles available to sell.");
            return;
        }

        System.out.println("\nAvailable Vehicles:");
        displayVehicles(new ArrayList<>(inventory));

        System.out.print("\nEnter VIN of vehicle to sell or lease: ");
        int vin = scanner.nextInt();

        scanner.nextLine();

        if (vin < 0) {
            System.out.println("VIN input cannot be empty.");
            return;
        }

        List<Vehicle> results = vehicleDao.getVehiclesByVin(vin);//create a method getting a vehicle by vin
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

           SalesContract salesContract = new SalesContract(date, name, email, selectedVehicle, needsFinance);
           contractDao.saveSalesContract(salesContract);

        } else if (contractType.equals("lease")) {
            LeaseContract leaseContract = new LeaseContract(date, name, email, selectedVehicle);
            contractDao.saveLeaseContract(leaseContract);

        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        System.out.println("\nContract saved and vehicle removed from inventory.");
        System.out.println("Contract Details:");

    }

}

