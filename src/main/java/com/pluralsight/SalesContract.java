package com.pluralsight;

import java.util.Scanner;


public class SalesContract extends Contract {
    //properties that is specific to this class.
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financing;

    //Generate constructor


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle,
                         double salesTaxAmount, double recordingFee, double processingFee, boolean financing) {
        super(date, customerName, customerEmail, vehicle);
        this.salesTaxAmount = 0.05;
        this.recordingFee = 100;
        this.processingFee = processingFee;
        this.financing = financing;

    }

    //Custom Method
    public double processingCost() {
        //Processing fee for vehicles under $10,000 =$295  and its $495 for all other
        if (getVehicle().getPrice() == 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
        return this.processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println(" Do you want to finance ?\n y (Yes) / n (No)");
            String choice = myScanner.nextLine().toUpperCase().trim();

            if (choice.equalsIgnoreCase("Y") && isFinancing()) {

                double price = getVehicle().getPrice();
                double interestRate;
                int months;

                if (price >= 10000) {
                    interestRate = 0.045;
                    months = 48;

                } else {
                    interestRate = 0.0525;
                    months = 24;
                }
                double loanAmount = getTotalPrice(); // Includes tax, fees, etc.
                double monthlyInterest = interestRate / 12;
                double monthlyPayment = (loanAmount * monthlyInterest) /
                        (1 - Math.pow(1 + monthlyInterest, -months));

                return monthlyPayment;
            } else if (choice.equals("N")) {
                return 0.0;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + recordingFee + processingFee;

    }

    //Generate getter and setter for all except total price and monthly payment.

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinancing() {
        return financing;
    }

    public void setFinancing(boolean financing) {
        this.financing = financing;
    }
}
