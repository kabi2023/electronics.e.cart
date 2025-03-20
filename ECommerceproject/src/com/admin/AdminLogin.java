package com.admin;

import java.util.Scanner;

public class AdminLogin {
    private static final String ADMIN_USERNAME = "kabilan";
    private static final String ADMIN_PASSWORD = "kabilan55";
    private static final int MAX_ATTEMPTS = 3;

    public static boolean login(Scanner scanner) {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("\nEnter Admin Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Admin Password: ");
            String password = scanner.nextLine();

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                System.out.println("Login successful! Welcome, Admin.");
                return true;
            } else {
                attempts++;
                System.out.println("❌ Invalid credentials! Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        System.out.println("Too many failed attempts. Access denied!");
        return false;
    }
}
