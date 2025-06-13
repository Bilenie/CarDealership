package com.pluralsight.dao;

import com.pluralsight.models.Contract;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_NAME = "contracts.csv";

    // Save contract to file
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            StringBuilder line = new StringBuilder();

            Vehicle v = contract.getVehicle();
            String type = contract instanceof SalesContract ? "SALE" : "LEASE";

            line.append(type).append("|")
                    .append(contract.getDate()).append("|")
                    .append(contract.getCustomerName()).append("|")
                    .append(contract.getCustomerEmail()).append("|")
                    .append(v.getVin()).append("|")
                    .append(v.getYear()).append("|")
                    .append(v.getMake()).append("|")
                    .append(v.getModel()).append("|")
                    .append(v.getType()).append("|")
                    .append(v.getColor()).append("|")
                    .append(v.getOdometer()).append("|")
                    .append(v.getPrice()).append("|");

            if (contract instanceof SalesContract sale) {
                double tax = v.getPrice() * 0.05;
                double recording = 100.0;
                double processing = v.getPrice() < 10000 ? 295.0 : 495.0;

                line.append(tax).append("|")
                        .append(recording).append("|")
                        .append(processing).append("|")
                        .append(sale.getTotalPrice()).append("|")
                        .append(sale.isFinance() ? "YES" : "NO").append("|")
                        .append(sale.getMonthlyPayment());

            } else if (contract instanceof LeaseContract lease) {
                double price = v.getPrice();
                double expectedEnd = price * 0.5;
                double leaseFee = price * 0.07;

                line.append(expectedEnd).append("|")
                        .append(leaseFee).append("|")
                        .append(lease.getTotalPrice()).append("|")
                        .append(lease.getMonthlyPayment());
            }

            line.append("\n");
            writer.write(line.toString());

        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
