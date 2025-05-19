package com.pluralsight;

public class LeaseContract extends Contract {

    private static final double LEASE_FEE_RATE = 0.07;
    private static final double ENDING_VALUE_PERCENTAGE = 0.50;
    private static final double INTEREST_RATE = 0.04;
    private static final int LEASE_MONTHS = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double expectedEndValue = price * ENDING_VALUE_PERCENTAGE;
        double leaseFee = price * LEASE_FEE_RATE;
        return expectedEndValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return Math.round((getTotalPrice() * (1 + INTEREST_RATE) / LEASE_MONTHS) * 100.0) / 100.0;
    }
}
