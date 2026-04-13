/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author juibh
 */
public class Login {
    private String regUsername;
    private String regPassword;
    private String regCell;
    private String regFirstName;
    private String regLastName;
    
    // Username validation logic adapted from assignment requirements (POE Task Document, 2026)
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    // Feedbaack on username to enhance user friendliness
    public String usernameFeedback(String username){
        if (username.contains("_" ) && username.length()>5){
            return "*Username is too long\n*Please try again";
        }
        if (!username.contains("_") && username.length()>=5){
            return "*Username does not contain an underscore and is too long\n*Please try again";
        }
        if(username.length()<= 4 && !username.contains("_")){
            return "*Username is missing an underscore\n*Please try again";
        }
        if(username.isEmpty()){
            return "*Username cannot be empty\nPlease try again";
        }
        if (checkUserName(username)) {
        return "Username successfully captured";
        }
         // Fallback (shouldn’t normally be reached)
        return "*Invalid username\n*Please try again";
    }
    
    // Password complexity rules adapted from Oracle Java documentation and general security guidelines (Oracle, 2026)
    public boolean checkPasswordComplexity(String password) {
        boolean length = password.length() >= 8;
        boolean capital = false, number = false, special = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) capital = true;
            else if (Character.isDigit(c)) number = true;
            else if (!Character.isLetterOrDigit(c)) special = true;
        }
        return length && capital && number && special;
    }
    
    // Feedbaack on password complexity to enhance user friendliness
    public String passwordFeedback(String password) {
        // StringBuilder used for efficient string concatenation (Oracle, 2026)
        StringBuilder feedback = new StringBuilder();

        if (password.isEmpty()) {
            return "*Password cannot be empty\n*Please try again";
        }
        if (password.length() < 8) {
            feedback.append("*Password too short (minimum 8 characters)\n");
        }
        if (!password.chars().anyMatch(Character::isUpperCase)) {
            feedback.append("*Password must contain at least one uppercase letter\n");
        }
        if (!password.chars().anyMatch(Character::isDigit)) {
            feedback.append("*Password must contain at least one number\n");
        }
        if (password.chars().allMatch(Character::isLetterOrDigit)) {
            feedback.append("*Password must contain at least one special character (e.g. !, @, #)\n");
        }

        // If we collected any problems, return them
        if (feedback.length() > 0) {
            feedback.append("*Please try again");
            return feedback.toString();
        }
        return "Password successfully captured.";
    }

   // Phone number validation logic adapted from assignment requirements (POE Task Document, 2026)
   // Regex based on ITU E.164 international numbering plan
   // Reference: International Telecommunication Union (ITU), Recommendation E.164
   // https://www.itu.int/rec/T-REC-E.164/en 
   public boolean checkCellPhoneNumber(String cell) {
    // + followed by 1–3 digit country code, then up to 10 digits subscriber number
        return cell.matches("\\+\\d{1,3}\\d{10}");
    }
   
   // Feedback on cell phone number
   public String cellPhoneFeedback(String cell) {
        // If the number is valid, return success
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        }
        // Otherwise, return the single required error message
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }
   
   // registerUser validation logic adapted from assignment requirements (POE Task Document, 2026)
   public String registerUser(String username, String password, String cell, String name, String surname) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        this.regUsername = username;
        this.regPassword = password;
        this.regCell = cell;
        this.regFirstName = name;
        this.regLastName = surname;
        return "Welcome "+name + ", "+surname+" it is great to see you.";
    }
 
   // loginUser validation logic adapted from assignment requirements (POE Task Document, 2026)
    public boolean loginUser(String username, String password) {
        return username.equals(regUsername) && password.equals(regPassword);
    }

    //  returnLoginStatus validation logic adapted from assignment requirements (POE Task Document, 2026)
    public String returnLoginStatus(boolean success, String username) {
        if (success) {
            return "Welcome "+regFirstName+", "+regLastName+" it is great to see you again.";
        }
            return "Username or password incorrect, please try again.";
    }
        
    
}



