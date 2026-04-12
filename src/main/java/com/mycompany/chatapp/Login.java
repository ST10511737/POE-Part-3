/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author juibh
 */
public class Login {
    private String regUsername;
    private String regPassword;
    private String regCell;

    
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
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
    
    public String passwordFeedback(String password) {
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
        return "Password successfully captured";
    }

    
   public boolean checkCellPhoneNumber(String cell) {
    // + followed by 1–3 digit country code, then up to 10 digits subscriber number
        return cell.matches("\\+\\d{1,3}\\d{1,10}");
    }
   
   public String cellPhoneFeedback(String cell) {
        if (cell.isEmpty()) {
            return "*Cell phone number cannot be empty\n*Please try again";
        }
        if (!cell.startsWith("+")) {
            return "*Cell phone number must start with '+' followed by country code\n*Please try again";
        }
        if (!cell.substring(1).matches("\\d+")) {
            return "*Cell phone number must contain only digits after '+'\n*Please try again";
        }
        // Split into country code and subscriber number
        String digits = cell.substring(1);
        if (digits.length() <= 3) {
            return "*Missing subscriber number after country code\n*Please try again";
        }
        String subscriber = digits.substring(3); // assume 1–3 digit country code
        if (subscriber.length() > 10) {
            return "*Subscriber number too long (maximum 10 digits)\n*Please try again";
        }

        return "Cell phone number successfully added.";
    }
   
   public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell-phone number incorrectly formatted or does not contain international code.";
        }
        this.regUsername = username;
        this.regPassword = password;
        this.regCell = cell;
        return "All details successfully captured.";
    }
 
    

    public boolean loginUser(String username, String password) {
        return username.equals(regUsername) && password.equals(regPassword);
    }

    public String returnLoginStatus(boolean success, String username) {
        if (success) {
            return "Welcome " + username + ", it is great to see you again.";
        }
            return "Username or password incorrect, please try again.";
    }
        
}



