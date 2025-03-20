package com.user;

import com.database.DBConnection;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserRegistration {
    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine().trim();

            if (!isValidPassword(password)) {
                System.out.println("Password must be at least 6 characters long, contain one uppercase letter, one number, and one special character.");
            } else {
                break;
            }
        }

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM users WHERE username = ?");
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Username already taken! Please choose a different one.");
                return;
            }

            PreparedStatement stmt = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User Registered Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{6,}$";
        return Pattern.matches(regex, password);
    }
}
