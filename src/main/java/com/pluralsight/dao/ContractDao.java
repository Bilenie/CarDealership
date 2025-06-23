package com.pluralsight.dao;

import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ContractDao {
    private BasicDataSource dataSource;

    public ContractDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 💰 Save a sales contract to the database
    public void saveSalesContract(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (contract_date, " +
                "customer_name, customer_email, vin, sale_price," +
                " sales_tax_amount, processing_fee, total_price, finance, MonthlyPayment)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contract.getDate());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getCustomerEmail());
            stmt.setInt(4, contract.getVehicle().getVin());
            stmt.setDouble(5, contract.getVehicle().getPrice());
            stmt.setDouble(6, contract.getSalesTaxRate());
            stmt.setDouble(7, contract.getRecordingFee());
            stmt.setDouble(8, contract.getTotalPrice());
            stmt.setBoolean(9, contract.isFinance());
            stmt.setDouble(10,contract.getMonthlyPayment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save a lease contract
    public void saveLeaseContract(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (contract_date, customer_name, customer_email, vin, ending_value, lease_fee, total_price,MonthlyPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contract.getDate());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getCustomerEmail());
            stmt.setInt(4, contract.getVehicle().getVin());
            stmt.setDouble(5, contract.getEndingValue());
            stmt.setDouble(6, contract.getLeaseFee());
            stmt.setDouble(7, contract.getTotalPrice());
            stmt.setDouble(8, contract.getMonthlyPayment());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
