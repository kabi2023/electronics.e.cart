package com.user;

import com.database.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class UserLogin {
    public static int login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?")) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!");
                return rs.getInt("id");
            } else {
                System.out.println("Invalid Username or Password!");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
