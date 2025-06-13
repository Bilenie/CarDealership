package com.pluralsight.models;

public abstract class Contract {

        private String date;  // format: YYYYMMDD
        private String customerName;
        private String customerEmail;
        private Vehicle vehicle;  //
        protected double totalPrice;
        protected double monthlyPayment;

    //Generate constructor for all except the total price and monthly payment
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

       public String getContractDetails() {
        return "Lease Contract\n" +
                "Date: " + date + "\n" +
                "Customer: " + customerName + " (" + customerEmail + ")\n" +
                "Vehicle: " + vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + "\n" +
                "Lease Price: $" + vehicle.getPrice();  // You can modify this if lease has monthly price, etc.
    }

//Generate getter and setter for all except the total price and monthly payment

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
//Make the method abstract that force the child class to implement the method.
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();


}
