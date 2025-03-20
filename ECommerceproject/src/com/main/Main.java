package com.main;

import com.admin.AdminLogin;
import com.admin.AdminPanel;
import com.user.UserRegistration;
import com.user.UserLogin;
import com.user.UserPanel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Admin Login");
            System.out.println("2. User Registration");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    if (AdminLogin.login(scanner)) {  
                        AdminPanel.showAdminMenu(scanner);
                    }
                    break;
                case 2:
                    UserRegistration.registerUser();
                    break;
                case 3:
                    int userId = UserLogin.login();
                    if (userId != -1) {
                        UserPanel.showUserMenu(userId);
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid option! Please try again.");
            }
        }
    }
}
