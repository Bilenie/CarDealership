package com.pluralsight.dao;


import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    //create our BasicDataSource/ set attribute
    public static BasicDataSource dataSource;

    // Generate Constructor
    public VehicleDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
   }

    // This class is where all the query happen
// 📦 This is like a helper to turn a DB row into a Vehicle
    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(rs.getInt("vin"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setType(rs.getString("vehicle_type"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setOdometer(rs.getInt("odometer"));
        vehicle.setPrice(rs.getDouble("price"));
        return vehicle;
    }


    /**
     * Get all vehicles for a specific dealership
     */
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
            SELECT v.* FROM vehicles v
            WHERE sold = 'Not Sold'
            ORDER BY v.year;
            """;//

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapResultSetToVehicle(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles: " + e.getMessage());
        }

        return vehicles;
    }
    public List<Vehicle> getVehiclesByVin(int vin) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
            SELECT 
                v.Vin
            FROM 
               vehicles v
            WHERE 
              Vin = ?
            """;//

        try (Connection connection = dataSource.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vin);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapResultSetToVehicle(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles: " + e.getMessage());
        }

        return vehicles;
    }

    //  Add a new vehicle
    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getType());
            stmt.setString(6, vehicle.getColor());
            stmt.setDouble(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());

            stmt.executeUpdate(); // run the query

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Remove a vehicle by VIN
    public void removeByVin(Vehicle vehicle) {
        String sql = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Vehicle> getVehicleByPrice(double price1,double price2) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                            
                    SELECT
                          v.*
                    FROM
                        vehicles v
                    WHERE 
                        (v.Price  BETWEEN ? AND ?) AND (sold ='Not Sold')
                    """

            );

            preparedStatement.setDouble(1, price1);// replace the placeholder for firstName
            preparedStatement.setDouble(2, price2);// replace the placeholder for lastName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String type = results.getString("Type");
                String color = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static List<Vehicle> getVehicleByMakeModel(String make, String model) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                            
                    SELECT
                          v.*
                    FROM
                        vehicles v
                    WHERE 
                      (v.Make = ?  AND v.Model = ?) AND (sold = 'Not Sold');
                    """

            );

            preparedStatement.setString(1, make);// replace the placeholder for firstName
            preparedStatement.setString(2, model);// replace the placeholder for lastName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String makes = results.getString("Make");
                String models = results.getString("Model");
                String type = results.getString("Type");
                String color = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, makes, models, type, color, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static List<Vehicle> getVehicleByYear(int yr1, int yr2) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                           
                   SELECT
                         v.*
                   FROM
                       vehicles v
                   WHERE 
                       (v.Year BETWEEN ? AND ?) AND (sold ='Not Sold')
                   """
            );

            preparedStatement.setInt(1, yr1);// replace the placeholder for firstName
            preparedStatement.setInt(2, yr2);// replace the placeholder for firstName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String type = results.getString("Type");
                String color = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static ArrayList<Vehicle> getVehicleByColor(String color) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                           
                   SELECT
                         v.*
                   FROM
                       vehicles v
                   WHERE 
                       (v.Color = ?) AND (sold ='Not Sold')
                   """
            );

            preparedStatement.setString(1, color);// replace the placeholder for firstName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String type = results.getString("Type");
                String colors = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, colors, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static List<Vehicle> getVehicleByMileage(double mileage1, double mileage2) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                           
                   SELECT
                         v.*
                   FROM
                       vehicles v
                   WHERE 
                       (v.Odometer  BETWEEN ? AND ?) AND (sold ='Not Sold')
                   """

            );

            preparedStatement.setDouble(1, mileage1);// replace the placeholder for firstName
            preparedStatement.setDouble(2, mileage2);// replace the placeholder for firstName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String type = results.getString("Type");
                String colors = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, colors, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static List<Vehicle> getVehicleByType(String type) {

        //create an array list to hold the vehicle we will be returning
        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                             
                     SELECT
                           v.*
                     FROM
                         vehicles v
                     WHERE 
                         (v.Type =? ) AND (sold ='Not Sold')
                     """
            );

            preparedStatement.setString(1, type);// replace the placeholder for firstName

            ResultSet results = preparedStatement.executeQuery();//run the query

            // Check if results has any rows / Is there anything inside before we start looping?
            if (!results.isBeforeFirst()) { // If false, no rows returned
                return vehicles; // return empty list
            }

            //loop over the results and create product objects and add the to the array list
            while (results.next()) {
                int vin = results.getInt("Vin");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String types = results.getString("Type");
                String colors = results.getString("Color");
                double odometer = results.getDouble("Odometer");
                double prices = results.getDouble("Price");
                Vehicle vehicle = new Vehicle(vin, year, make, model, types, colors, odometer, prices);//int vin, int year, String make, String model, String type, String color, double odometer, double price
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
//    public static void addVehicle(Vehicle vehicle) {
//
//        try (Connection connection = dataSource.getConnection()) {
//
//            //query to grab the row data from the tables
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    """
//                            INSERT INTO  `vehicles` ( `Vin`,`Year`,`Make`,`Model`,`Type`,`Color`,`Odometer`,`Price` , `Sold`) VALUES\s
//                                            ( ?,? ,?,?,?,?,?,?,?)
//
//                            """
//
//            );
//            // Replace the ? with values from the Vehicle object
//            preparedStatement.setInt(1, vehicle.getVin());
//            preparedStatement.setInt(2, vehicle.getYear());
//            preparedStatement.setString(3, vehicle.getMake());
//            preparedStatement.setString(4, vehicle.getModel());
//            preparedStatement.setString(5, vehicle.getType());
//            preparedStatement.setString(6, vehicle.getColor());
//            preparedStatement.setDouble(7, vehicle.getOdometer());
//            preparedStatement.setDouble(8, vehicle.getPrice());
//            preparedStatement.setBoolean(9, vehicle.isSold());
//
//
//            // Now execute the INSERT
//            int rowsInserted = preparedStatement.executeUpdate();//run the query
//            if (rowsInserted > 0) {
//                System.out.println("🚗 Vehicle added successfully!");
//            } else {
//                System.out.println("⚠️ No vehicle was added.");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//    public static void removeVehicleByVin(int vin) {
//
//        try (Connection connection = dataSource.getConnection()) {
//
//            String sql = "DELETE FROM vehicles WHERE Vin = ?";
//
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, vin);  // put the VIN number in the query
//
//            int rowsDeleted = stmt.executeUpdate();
//
//            if (rowsDeleted > 0) {
//                System.out.println("🚮 Vehicle with VIN " + vin + " was removed.");
//            } else {
//                System.out.println("⚠️ No vehicle found with VIN " + vin + ".");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Error removing vehicle: " + e.getMessage(), e);
//        }
//    }
}





















