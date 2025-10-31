package com.pluralsight;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double expectedEndingValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);

        // Calculate the lease-specific values:
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5; // 50% of the original price
        this.leaseFee = vehicleSold.getPrice() * 0.07;           // 7% lease fee
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }
    public double getLeaseFee() {
        return leaseFee;
    }


    @Override
    public double getTotalPrice() {

        // TotalPrice = VehiclePrice + LeaseFee

        return getVehicleSold().getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();

        double annualRate = 0.04;
        double monthlyRate = annualRate / 12;
        int months = 36;

        double monthlyPayment = (totalPrice / months) + (totalPrice * monthlyRate);
        return monthlyPayment;
    }
}
