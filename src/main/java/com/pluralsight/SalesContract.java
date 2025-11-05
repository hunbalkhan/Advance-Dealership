package com.pluralsight;

public class SalesContract extends Contract {

    private double salesTaxAmount;// 5% of vehicle price
    private double recordingFee;  // fixed $100
    private double processingFee; // $295 if price < 10000, otherwise $495
    private boolean finance;      // Y/N

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);

        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.finance = finance;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return finance;
    }


    @Override
    public double getTotalPrice() {

        double vehiclePrice = getVehicleSold().getPrice();

        // total + interest + recording fee + processing fee
        double total = vehiclePrice + (vehiclePrice * 0.05) +  recordingFee + processingFee;

        return total;
    }

    @Override
    public double getMonthlyPayment() {
                                        // true hits left side, false hits right
        //double rate = (price < 10000) ? $295:$495;

        double totalPrice = getTotalPrice();

        // if totalPrice < 10000, then rate = 5.25%, else it will be 4.25%
        double annualRate = (totalPrice < 10000) ? 0.0525 : 0.0425;
        int months = (totalPrice < 10000) ? 24 : 48;

        double monthlyRate = annualRate / 12;

       double monthlyPayment = (totalPrice / months) + (totalPrice * monthlyRate);
       return monthlyPayment;
    }
}
