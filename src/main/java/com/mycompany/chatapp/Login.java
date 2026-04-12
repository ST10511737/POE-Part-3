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
        if(username.contains("_") && username.length()>5){
            return "*Username is t"
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

    public boolean checkCellPhoneNumber(String cell) {
        return cell.startsWith("+27") && cell.length() <= 13;
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

    public String returnLoginStatus(boolean success, String firstName, String lastName) {
        if (success) {
            return "Welcome " + firstName + ", " + lastName + " - it’s great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

