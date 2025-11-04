package com.pluralsight.data;


import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            // opening file to read
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));

            // Read the first line – this holds dealership info
            String firstLine = reader.readLine();
            String[] parts = firstLine.split("\\|");

            // making dealership object - with first line info
            dealership = new Dealership(parts[0], parts[1], parts[2]);

            // making rest oif the lines read for each vehicle
            String line;

            // read through every car line
            while ((line = reader.readLine()) != null) {
                String[] info = line.split("\\|");

                // now making a vehicle object
                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(info[0]),  // Vin
                        Integer.parseInt(info[1]),  // year
                        info[2],                    // make
                        info[3],                    // model
                        info[4],                    // vehicle type
                        info[5],                    // color
                        Integer.parseInt(info[6]),  // odometer
                        Double.parseDouble(info[7]) // price
                );

                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }

        return dealership;
    }

    public static void saveNewVehicle(Vehicle vehicle) {
        try {
            // 'true' = append mode (adds to end of file instead of overwriting)
            FileWriter fw = new FileWriter("inventory.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.newLine();

            bw.write(vehicle.getVin() + "|" +
                    vehicle.getYear() + "|" +
                    vehicle.getMake() + "|" +
                    vehicle.getModel() + "|" +
                    vehicle.getVehicleType() + "|" +
                    vehicle.getColor() + "|" +
                    vehicle.getOdometer() + "|" +
                    vehicle.getPrice());

            bw.close();

            System.out.println("Vehicle saved successfully!");
        }
        catch (IOException e) {
            System.out.println("Error saving vehicle...");
        }
    }

    public static void saveDealership(Dealership dealership) {
        try {
            FileWriter fw = new FileWriter("inventory.csv");
            BufferedWriter bw = new BufferedWriter(fw);

            // write dealership info first
            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            // write vehicles
            for (Vehicle v : dealership.getAllVehicles()) {
                bw.write(v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        v.getPrice());
                bw.newLine();

                bw.close();
            }
            System.out.println("dealership saved successfully!");
        }
        catch (IOException e) {
            System.out.println("Error saving dealership...");
        }
    }


}