/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Login login = new Login();
            boolean running = true;
            
            // Home page
            while (running) {
                System.out.println("==================================");
                System.out.println("      Hi! Welcome to Chatter.");
                System.out.println("==================================");
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("\nChoose option: ");

                String input = sc.nextLine().trim();

                if (!input.matches("[1-3]")) {
                    System.out.println("\nInvalid option. Please select 1, 2, or 3 from the menu below.");
                    continue;
                }
                
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        // Registration
                        System.out.println("\n==================================");
                        System.out.println("        USER REGISTRATION");
                        System.out.println("==================================");
                        System.out.println("Please fill in the following details to get started!");
                        System.out.println("(Type 'exit' at any prompt to return to the home page)");

                        String user;
                        String pass;
                        String cell;

                        // Username validation
                        while (true) {
                            System.out.print("\nA username must contain an underscore and be no more than 5 characters long, including the underscore\nEnter username: ");
                            user = sc.nextLine().trim();
                            if (user.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            String feedback = login.usernameFeedback(user);
                            System.out.println(feedback);

                            if (feedback.equals("Username successfully captured")) {
                                break; // ✅ only move on when valid
                            }
                        }
                        if (user.equalsIgnoreCase("exit")) break;

                        // Password validation
                        while (true) {
                            System.out.print("Enter password: ");
                            pass = sc.nextLine();
                            if (pass.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            if (pass.length() < 4) {
                                System.out.println("❌ Password too short. Must be at least 4 characters. Try again.");
                            } else {
                                break;
                            }
                        }
                        if (pass.equalsIgnoreCase("exit")) break;

                        // Cell number validation
                        while (true) {
                            System.out.print("Enter cell number (+27XXXXXXXXXX): ");
                            cell = sc.nextLine().trim();
                            if (cell.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            if (!cell.matches("\\+27\\d{10}")) {
                                System.out.println("❌ Invalid cell number. Must start with +27 followed by 10 digits. Try again.");
                            } else {
                                break;
                            }
                        }
                        if (cell.equalsIgnoreCase("exit")) break;

                        System.out.println(login.registerUser(user, pass, cell));
                        System.out.println("\n✅ You have successfully registered with Chatter!");
                        
                        // End program after successful registration
                        running = false;
                        break;

                    case 2:
                        // Login
                        System.out.println("\n==================================");
                        System.out.println("            LOGIN");
                        System.out.println("==================================");
                        System.out.println("(Type 'exit' at any prompt to return to the home page)");

                        String loginUser;
                        String loginPass;

                        while (true) {
                            System.out.print("Enter username: ");
                            loginUser = sc.nextLine().trim();
                            if (loginUser.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            if (loginUser.isEmpty()) {
                                System.out.println("❌ Username cannot be empty. Try again.");
                                continue;
                            }

                            System.out.print("Enter password: ");
                            loginPass = sc.nextLine();
                            if (loginPass.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }

                            boolean success = login.loginUser(loginUser, loginPass);
                            if (!success) {
                                System.out.println("❌ Invalid credentials. Please try again.");
                            } else {
                                System.out.println("\n✅ Welcome back, " + loginUser + "!");
                                // End program after successful login
                                running = false;
                                break;
                            }
                        }
                        break;

                    case 3:
                        // Exit confirmation
                        System.out.print("\nAre you sure you want to exit? (y/n): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        if (confirm.equals("y")) {
                            running = false;
                            System.out.println("\n✅ Thank you for using Chatter. Goodbye!");
                        } else {
                            System.out.println("\nReturning to main menu...");
                        }
                        break;
                }
            }
        }
    }
}