package com.pluralsight;

import com.pluralsight.userInterface.UserInterface;

public class Program {
    public static void main(String[] args) {

        System.out.println("\nDealership Application Loading . . . ");

        // Create an instance
        UserInterface ui = new UserInterface();

        // starts the loop
        ui.display();

    }
}