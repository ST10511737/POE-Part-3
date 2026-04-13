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
                        String name;
                        String surname;
                        
                        // Name
                        System.out.print("Enter first name: ");
                        name = sc.nextLine().trim();
                        if (name.equalsIgnoreCase("exit")) break;

                        // Surname
                        System.out.print("Enter surname: ");
                        surname = sc.nextLine().trim();
                        if (surname.equalsIgnoreCase("exit")) break;
                        
                        
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
                                break;
                            }
                        }
                        if (user.equalsIgnoreCase("exit")) break;

                        // Password validation
                        while (true) {
                            System.out.println("\nPassword Requirmenst:\n- At least 8 Characters long\n- Contain a capiital letter\n- Contain a number\n- Contain a special character");
                            System.out.print("Enter password: ");
                            pass = sc.nextLine();
                            if (pass.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            String feedback = login.passwordFeedback(pass);
                            System.out.println(feedback);

                            if (login.checkPasswordComplexity(pass)) {
                                break; 
                            }
                        }
                        if (pass.equalsIgnoreCase("exit")) break;

                        // Cell number validation
                        while (true) {
                            System.out.print("\nPlease type in your international code followed by your cellnumber\nEnter cell number (+27XXXXXXXXXX): ");
                            cell = sc.nextLine().trim();
                            if (cell.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            String feedback = login.cellPhoneFeedback(cell);
                            System.out.println(feedback);

                            // Only move on if valid
                            if (login.checkCellPhoneNumber(cell)) {
                                break;
                            }
                        }
                        if (cell.equalsIgnoreCase("exit")) break;

                        System.out.println(login.registerUser(user, pass, cell, name, surname));
                        System.out.println("\nYou have successfully registered with Chatter!");
                        System.out.println("\nRedirecting...");
                        
                        
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
                                System.out.println("Username cannot be empty. Try again.");
                                continue;
                            }

                            System.out.print("Enter password: ");
                            loginPass = sc.nextLine();
                            if (loginPass.equalsIgnoreCase("exit")) {
                                System.out.println("Returning to home page...");
                                break;
                            }
                            boolean success = login.loginUser(loginUser, loginPass);
                            System.out.println("\n"+login.returnLoginStatus(success, loginUser));
                            
                        }
                        break;

                    case 3:
                        // Exit confirmation
                        System.out.print("\nAre you sure you want to exit? (y/n): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        if (confirm.equals("y")) {
                            running = false;
                            System.out.println("\nThank you for using Chatter. Goodbye!");
                        } else {
                            System.out.println("\nReturning to main menu...");
                        }
                        break;
                }
            }
        }
    }
}