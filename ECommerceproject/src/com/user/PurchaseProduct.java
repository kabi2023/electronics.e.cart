package com.user;

import com.database.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class PurchaseProduct {
    public static void viewProducts() {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            
            System.out.println("\nAvailable Products:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name") + " ($" + rs.getDouble("price") + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buyProduct(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to Buy: ");
        int productId = scanner.nextInt();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO orders (user_id, product_id) VALUES (?, ?)")) {
            
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("Product Purchased!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewBoughtProducts(int userId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT p.name, p.price FROM orders o JOIN products p ON o.product_id = p.id WHERE o.user_id = ?")) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nYour Purchased Products:");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " ($" + rs.getDouble("price") + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
