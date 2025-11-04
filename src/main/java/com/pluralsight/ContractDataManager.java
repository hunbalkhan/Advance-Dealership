package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ContractDataManager {

    public void saveContract(Contract contract) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv", true))) {

            String line = "";

            // Checking if it is a lease contract or not
            if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                // then format how the lease contract data should look
                line = String.format("LEASE|%s|%s|%s|%s|%.2f|%.2f",
                        lease.getDate(),
                        lease.getCustomerName(),
                        lease.getCustomerEmail(),
                        lease.getVehicleSold().getVin(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment());

            }
            // Or if it's a Sales contract :
            else if (contract instanceof SalesContract) {

                SalesContract sale = (SalesContract) contract;

                // Format how the sales contract would look like
                line = String.format("SALE|%s|%s|%s|%s|%.2f|%.2f",
                        sale.getDate(),
                        sale.getCustomerName(),
                        sale.getCustomerEmail(),
                        sale.getVehicleSold().getVin(),
                        sale.getTotalPrice(),
                        sale.getMonthlyPayment());

            }
            bw.write(line); // writes that line to the file
            bw.newLine();   // moves the cursor to the new line
            bw.close();     // closes the buffered writer

            System.out.println("Contract saved: " + line);

        } catch (IOException e) {
            System.out.println("Error saving contract...");;
        }

    }

}
