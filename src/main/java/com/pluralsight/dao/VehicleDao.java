package com.pluralsight.dao;

import com.pluralsight.models.Dealership;
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


    public static List<Vehicle> getVehicleByPrice(double price) {

        //create an array list to hold the vehicle we will be returning
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Price = ? BETWEEN v.price = ?"

            );

            preparedStatement.setDouble(1, price);// replace the placeholder for firstName
            preparedStatement.setDouble(2, price);// replace the placeholder for lastName

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
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Make = ? AND v.Model = ?"

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

    public static List<Vehicle> getVehicleByYear(int yr) {

        //create an array list to hold the vehicle we will be returning
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Year = ?"

            );

            preparedStatement.setInt(1, yr);// replace the placeholder for firstName

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

    public static List<Vehicle> getVehicleColor(String color) {

        //create an array list to hold the vehicle we will be returning
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Color = ?"

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

    public static List<Vehicle> getVehicleByMileage(double mileage) {

        //create an array list to hold the vehicle we will be returning
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Odometer = ?"

            );

            preparedStatement.setDouble(1, mileage);// replace the placeholder for firstName

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
        List<Vehicle> vehicles = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {

            //query to grab the row data from the tables
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "\tv.*\n" +
                            "FROM \n" +
                            "   vehicles v\n" +
                            "WHERE \n" +
                            "  v.Type = ?"

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
}





















