package com.pluralsight.models;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters
    public String getDate() {
        return date;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    // Abstract methods for child classes to implement
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
