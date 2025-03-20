package com.user;

import java.util.Scanner;

public class UserPanel {
    public static void showUserMenu(int userId) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUser Panel:");
            System.out.println("1. View Products");
            System.out.println("2. Buy Product");
            System.out.println("3. View Purchased Products");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: PurchaseProduct.viewProducts(); break;
                case 2: PurchaseProduct.buyProduct(userId); break;
                case 3: PurchaseProduct.viewBoughtProducts(userId); break;
                case 4: return;
                default: System.out.println("❌ Invalid choice!");
            }
        }
    }
}
