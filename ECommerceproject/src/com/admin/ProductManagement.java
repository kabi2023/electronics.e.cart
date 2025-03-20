package com.admin;

import com.database.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class ProductManagement {
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

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO products (name, price) VALUES (?, ?)")) {
            
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.executeUpdate();
            System.out.println("Product Added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to Delete: ");
        int id = scanner.nextInt();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM products WHERE id = ?")) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Product Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                 "UPDATE products SET name = ?, price = ? WHERE id = ?")) {  
            
            stmt.setString(1, name); 
            stmt.setDouble(2, price);
            stmt.setInt(3, id); 
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product Updated Successfully!");
            } else {
                System.out.println("Product ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}