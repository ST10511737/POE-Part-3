/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author juibh
 */
public class ChatApp {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Login login = new Login();
            boolean running = true;
            
            while (running) {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String user = sc.nextLine();
                        System.out.print("Enter password: ");
                        String pass = sc.nextLine();
                        System.out.print("Enter cell number (+27...): ");
                        String cell = sc.nextLine();
                        System.out.println(login.registerUser(user, pass, cell));
                        break;
                        
                    case 2:
                        System.out.print("Enter username: ");
                        String loginUser = sc.nextLine();
                        System.out.print("Enter password: ");
                        String loginPass = sc.nextLine();
                        boolean success = login.loginUser(loginUser, loginPass);
                        System.out.println(login.returnLoginStatus(success, "John", "Doe"));
                        break;
                        
                    case 3:
                        running = false;
                        System.out.println("Thank you for using our application");
                        break;
                        
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
    }

