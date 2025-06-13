package com.pluralsight.models;


public class SalesContract extends Contract {
    //properties that is specific to this class.
    // Constants
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double PROCESSING_FEE_UNDER_10000 = 295.0;
    private static final double PROCESSING_FEE_OVER_10000 = 495.0;
    private boolean finance;

    //Generate constructor
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;

    }

    //Custom Method

    public boolean isFinance() {
        return finance;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;

        double totalPrice = getTotalPrice();
        double rate;
        int months;

        if (totalPrice >= 10000) {
            rate = 0.0425;
            months = 48;
        } else {
            rate = 0.0525;
            months = 24;
        }

        double monthlyPayment = (totalPrice * (1 + rate)) / months;
        return Math.round(monthlyPayment * 100.0) / 100.0; // Rounded to 2 decimal places
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicle().getPrice();
        double tax = basePrice * SALES_TAX_RATE;
        double processingFee = basePrice < 10000 ? PROCESSING_FEE_UNDER_10000 : PROCESSING_FEE_OVER_10000;
        return basePrice + tax + RECORDING_FEE + processingFee;
    }

    //Generate getter and setter for all except total price and monthly payment.

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

}

