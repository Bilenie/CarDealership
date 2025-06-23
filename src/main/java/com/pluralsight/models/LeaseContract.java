package com.pluralsight.models;

public class LeaseContract extends Contract {

    private static double LEASE_FEE_RATE ;// = 0.07;
    private static double ENDING_VALUE_PERCENTAGE ;// = 0.50;
    private static  double INTEREST_RATE;// = 0.04;
    private static  int LEASE_MONTHS;// = 36;
    private static  double endingValue;
    private static double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.endingValue = getVehicle().getPrice() * getEndingValuePercentage();
        this.leaseFee = getVehicle().getPrice() * getLeaseFeeRate();
    }

    public static double getLeaseFeeRate() {
        return LEASE_FEE_RATE = 0.07;
    }

    public static void setLeaseFeeRate(double leaseFeeRate) {
        LEASE_FEE_RATE = leaseFeeRate;
    }

    public static double getEndingValuePercentage() {
        return ENDING_VALUE_PERCENTAGE =0.50;
    }

    public static void setEndingValuePercentage(double endingValuePercentage) {
        ENDING_VALUE_PERCENTAGE = endingValuePercentage;
    }

    public static double getInterestRate() {
        return INTEREST_RATE = 0.04;
    }

    public static void setInterestRate(double interestRate) {
        INTEREST_RATE = interestRate;
    }

    public static int getLeaseMonths() {
        return LEASE_MONTHS = 36;
    }

    public static void setLeaseMonths(int leaseMonths) {
        LEASE_MONTHS = leaseMonths;
    }

    public static double getEndingValue() {
        return endingValue;
    }

    public static void setEndingValue(double endingValue) {
        LeaseContract.endingValue = endingValue;
    }

    public static double getLeaseFee() {
        return leaseFee;
    }

    public static void setLeaseFee(double leaseFee) {
        LeaseContract.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double expectedEndValue = price * getEndingValuePercentage();
        double leaseFee = price * getLeaseFeeRate();
        return expectedEndValue + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        return Math.round((getTotalPrice() * (1 + getInterestRate()) / getLeaseMonths()) * 100.0) / 100.0;
    }
}
