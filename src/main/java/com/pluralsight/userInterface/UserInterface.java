package com.pluralsight.userInterface;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Person;
import com.pluralsight.models.Vehicle;
import com.pluralsight.data.DealershipFileManager;


import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    // core display method to start user interface
    public void display() {
        init(); // loads dealership from file


        Person p = new Person("Matt", "C", 43);
        String firstName = p.getFirstName();


        boolean running = true;
        while (running) {
            displayMenu(); // show menu
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processViewAllVehicles();
                    break;
                case "2":
                    processSearchByPrice();
                    break;
                case "3":
                    processSearchByMakeModel();
                    break;
                case "4":
                    processSearchByYear();
                    break;
                case "5":
                    processSearchByColor();
                    break;
                case "6":
                    processSearchByMileage();
                    break;
                case "7":
                    processSearchByType();
                    break;
                case "8":
                    processAddVehicle();
                    break;
                case "9":
                    processRemoveVehicle();
                    break;
                case "99":
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Initialize dealership from file
    private void init() {
        // creates and loads an object that can read/write the inventory file.
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();

        System.out.println("Loaded dealership: " + dealership.getName() + " " + dealership.getAddress() + " " + dealership.getPhone());
    }

    // Helper to display any list of vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\n--- Vehicles ---");
        for (Vehicle v : vehicles) {
            System.out.println(v); // calls to print out each vehicle
        }
    }

    // Display main menu
    private void displayMenu() {
        System.out.println("\n=== Dealership Menu ===");
        System.out.println("1) View All Vehicles");
        System.out.println("2) Search Vehicles by Price");
        System.out.println("3) Search Vehicles by Make & Model");
        System.out.println("4) Search Vehicles by Year");
        System.out.println("5) Search Vehicles by Color");
        System.out.println("6) Search Vehicles by Mileage");
        System.out.println("7) Search Vehicles by Type");
        System.out.println("8) Add a vehicle");
        System.out.println("9) Remove a vehicle");
        System.out.println("99) Exit");
    }

    // Placeholder methods for processing commands
    private void processViewAllVehicles() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processSearchByPrice() {
        System.out.print("Enter minimum price: $");
        double min = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter maximum price: $");
        double max = Double.parseDouble(scanner.nextLine());

        // call dealership search
        List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    private void processSearchByMakeModel() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(results);
    }

    private void processSearchByYear() {
        System.out.print("Enter minimum year: ");
        int min = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum year: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Vehicle> result = dealership.getVehiclesByYear(min, max);
        displayVehicles(result);
    }

    private void processSearchByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> result = dealership.getVehiclesByColor(color);
        displayVehicles(result);
    }

    private void processSearchByMileage() {
        System.out.println("Enter minimum mileage: ");
        int min = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum mileage: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Vehicle> result = dealership.getVehiclesByMileage(min, max);
        displayVehicles(result);
    }

    private void processSearchByType() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> result = dealership.getVehiclesByType(type);
        displayVehicles(result);
    }

    private void processAddVehicle() {
        System.out.println("\nPlease provide details of the vehicle.");
        System.out.print("VIN: "); int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Make: "); String make = scanner.nextLine();
        System.out.print("Model: "); String model = scanner.nextLine();
        System.out.print("Vehicle Type: "); String type = scanner.nextLine();
        System.out.print("Color: "); String color = scanner.nextLine();
        System.out.print("Odometer: "); int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine());

        Vehicle vehicle = new Vehicle (vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle); // adding to inventory
        DealershipFileManager.saveNewVehicle(vehicle);

    }

    private void processRemoveVehicle() {
        System.out.println("Enter VIN of vehicle you want to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle toRemove = null; // placeholder for empty VIN until found a vehicle to remove in for loop

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }

        // Check if matching vehicle VIN was found.
        if (toRemove != null) {
            // remove the vehicle
            dealership.getAllVehicles().remove(toRemove);

            // save the updated dealership to the CSV
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successful!");
        }
        else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }
}