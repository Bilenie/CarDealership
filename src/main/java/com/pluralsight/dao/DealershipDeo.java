package com.pluralsight.dao;

import com.pluralsight.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DealershipDeo {
    //getting all the dealership
    // query => display list of dealership from
   private BasicDataSource dataSource;

   @Autowired
    public DealershipDeo(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> getAllDealerships(BasicDataSource dataSource) {
        List<Dealership> dealerships = new ArrayList<>();
        System.out.println("starting here ...1");
        String sql = """
                SELECT d.* FROM dealership d;
                """;
        System.out.println(sql);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                dealerships.add(mapResultSetToDealership(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles: " + e.getMessage());
        }

        return dealerships;
    }

    private Dealership mapResultSetToDealership(ResultSet rs) throws SQLException {
        Dealership dealership = new Dealership();
        dealership.setDealershipId(rs.getInt("Dealership_ID"));
        dealership.setName(rs.getString("Name"));
        dealership.setAddress(rs.getString("Address"));
        dealership.setPhone(rs.getString("Contact_Phone"));

        return dealership;
    }
}