package com.admin;

import java.util.Scanner;

public class AdminPanel {
    public static void showAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1️ View Products");
            System.out.println("2️ Add Product");
            System.out.println("3️ Modify Product");
            System.out.println("4️ Delete Product");
            System.out.println("5️ Logout");
            System.out.print("➡ Choose an option: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> ProductManagement.viewProducts();
                case 2 -> ProductManagement.addProduct();
                case 3 -> ProductManagement.updateProduct();
                case 4 -> ProductManagement.deleteProduct();
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
