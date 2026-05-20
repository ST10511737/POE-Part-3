package com.mycompany.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Login login = new Login();
            boolean running = true;
            
            // Main loop for the home page
            while (running) {
                System.out.println("==================================");
                System.out.println("      Hi! Welcome to Chatter.");
                System.out.println("==================================");
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("\nChoose option: ");

                String input = sc.nextLine().trim();

                // Validate menu choice
                if (!input.matches("[1-3]")) {
                    System.out.println("\nInvalid option. Please select 1, 2, or 3 from the menu below.\n");
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
                        System.out.println("(Type 'exit' at any prompt to return to the home page)\n");

                        String user = "";
                        String pass = "";
                        String cell = "";
                        String name = "";
                        String surname = "";

                        // First name validation
                        while (true) {
                            System.out.print("Enter first name: ");
                            String rawName = sc.nextLine().trim();
                            if (rawName.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit registration
                            }
                            if (rawName.trim().isEmpty()) {
                                System.out.println("\nName cannot be empty. Try again or type 'exit'.\n");
                                continue;
                            }
                            String formatted = login.formatName(rawName);
                            if (formatted != null) {
                                name = formatted;
                                break;
                            } else {
                                System.out.println("\nInvalid name. Only letters allowed. Try again or type 'exit'.\n");
                            }
                        }
                        if (name.isEmpty()) break; // return to home page

                        // Surname validation
                        while (true) {
                            System.out.print("Enter surname: ");
                            String rawSurname = sc.nextLine().trim();
                            if (rawSurname.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit registration
                            }
                            if (rawSurname.trim().isEmpty()) {
                                System.out.println("\nSurname cannot be empty. Try again or type 'exit'.\n");
                                continue;
                            }
                            String formatted = login.formatName(rawSurname);
                            if (formatted != null) {
                                surname = formatted;
                                break;
                            } else {
                                System.out.println("\nInvalid surname. Only letters allowed. Try again or type 'exit'.\n");
                            }
                        }
                        if (surname.isEmpty()) break; // return to home page

                        // Username validation
                        while (true) {
                            System.out.print("\nA username must contain an underscore and be no more than 5 characters long, including the underscore\nEnter username: ");
                            user = sc.nextLine().trim();
                            if (user.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit registration
                            }
                            if (user.trim().isEmpty()) {
                                System.out.println("\nUsername cannot be empty. Try again or type 'exit'.\n");
                                continue;
                            }
                            String feedback = login.usernameFeedback(user);
                            System.out.println("\n" + feedback + "\n");

                            if (feedback.equals("Username successfully captured")) {
                                break;
                            }
                        }
                        if (user.equalsIgnoreCase("exit")) break;

                        // Password validation
                        while (true) {
                            System.out.println("\nPassword Requirements:\n- At least 8 Characters long\n- Contain a capital letter\n- Contain a number\n- Contain a special character\n");
                            System.out.print("Enter password: ");
                            pass = sc.nextLine();
                            if (pass.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit registration
                            }
                            if (pass.trim().isEmpty()) {
                                System.out.println("\nPassword cannot be empty. Try again or type 'exit'.\n");
                                continue;
                            }
                            String feedback = login.passwordFeedback(pass);
                            System.out.println("\n" + feedback + "\n");

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
                                System.out.println("\nReturning to home page...\n");
                                break; // exit registration
                            }
                            if (cell.trim().isEmpty()) {
                                System.out.println("\nCell number cannot be empty. Try again or type 'exit'.\n");
                                continue;
                            }
                            String feedback = login.cellPhoneFeedback(cell);
                            System.out.println("\n" + feedback + "\n");

                            if (login.checkCellPhoneNumber(cell)) {
                                break;
                            }
                        }
                        if (cell.equalsIgnoreCase("exit")) break;

                        // Guard against incomplete registration
                        if (user.isEmpty() || pass.isEmpty() || cell.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                            System.out.println("\nRegistration cancelled. Returning to home page...\n");
                            break;
                        }

                        System.out.println("\n" + login.registerUser(user, pass, cell, name, surname) + "\n");
                        System.out.println("You have successfully registered with Chatter!\n");
                        break; // end case 1, return to home page

                    case 2:
                        // Login
                        System.out.println("\n==================================");
                        System.out.println("            LOGIN");
                        System.out.println("==================================");
                        System.out.println("(Type 'exit' at any prompt to return to the home page)\n");

                        String loginUser;
                        String loginPass = "";

                        while (true) {
                            System.out.print("Enter username: ");
                            loginUser = sc.nextLine().trim();
                            if (loginUser.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit login
                            }
                            if (loginUser.trim().isEmpty()) {
                                System.out.println("\nUsername cannot be empty. Try again.\n");
                                continue;
                            }

                            System.out.print("Enter password: ");
                            loginPass = sc.nextLine();
                            if (loginPass.equalsIgnoreCase("exit")) {
                                System.out.println("\nReturning to home page...\n");
                                break; // exit login
                            }
                            if (loginPass.trim().isEmpty()) {
                                System.out.println("\nPassword cannot be empty. Try again.\n");
                                continue;
                            }

                            boolean success = login.loginUser(loginUser, loginPass);                           
                            System.out.println("\n" + login.returnLoginStatus(success, loginUser) + "\n");   

                            if (success) {
                                System.out.println("You are now logged in. Welcome to Chatter!\n");
                                boolean chatRunning = true;
                                Message message = new Message();

                                while (chatRunning) {
                                    System.out.println("\n==================================");
                                    System.out.println("        \nWelcome to QuickChat.");
                                    System.out.println("==================================");
                                    
                                    System.out.println("\n1. Send Messages");
                                    System.out.println("2. Show Recently Sent Messages");
                                    System.out.println("3. Quit");
                                    System.out.print("\nChoose option: ");
                                    String chatChoice = sc.nextLine();
                                    
                                    if (!chatChoice.matches("[1-3]")) {
                                        System.out.println("\nInvalid option. Please select 1, 2, or 3 from the menu below.\n");
                                        continue;
                                    }
                                    
                                     int option = Integer.parseInt(chatChoice);
                                    

                                    switch (option) {
                                        case 1:
                                            while (true){
                                            System.out.print("How many messages do you want to send? ");
                                            String num = sc.nextLine().trim();
                                            
                                            if (num.equalsIgnoreCase("exit")) {
                                                System.out.println("\nReturning to home page...\n");
                                                break; // exit login
                                            }
                                            if (num.trim().isEmpty()) {
                                                System.out.println("\nThis field cannot be empty. Try again.\n");
                                                continue;
                                            }

                                            int msg = Integer.parseInt(num);
                                            for (int i = 1; i <= msg; i++) {
                                                message.sendMessage(i);
                                            }
                                            System.out.println("Total messages sent: " + message.returnTotalMessages());
                                            
                                            break;
                                            }

                                        case 2:
                                            System.out.println("Coming Soon.");
                                            break;

                                        case 3:
                                            chatRunning = false;
                                            System.out.println("Exiting QuickChat... Goodbye!");
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                }
                                    break;
                            }
                        }
                        break; // end case 2, return to home page if not successful

                    case 3:
                        // Exit confirmation
                        System.out.print("\nAre you sure you want to exit? (y/n): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        if (confirm.equals("y")) {
                            running = false;
                            System.out.println("\nThank you for using Chatter. Goodbye!\n");
                        } else {
                            System.out.println("\nReturning to main menu...\n");
                        }
                        break;
                }
            }
        }
    }
}