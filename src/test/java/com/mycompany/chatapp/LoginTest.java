/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author juibh
 */
public class LoginTest {
    // ---------- assertEquals tests (string outputs) ----------

    @Test
    public void testUsernameCorrectlyFormattedMessage() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Welcome Kyle, Smith it is great to see you.", result);
    }

    @Test
    public void testUsernameIncorrectlyFormattedMessage() {
        Login login = new Login();
        String result = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testPasswordMeetsRequirementsMessage() {
        Login login = new Login();
        String result = login.passwordFeedback("Ch&&sec@ke99!");
        assertEquals("Password successfully captured.", result);
    }

    @Test
    public void testPasswordDoesNotMeetRequirementsMessage() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testCellPhoneCorrectlyFormattedMessage() {
        Login login = new Login();
        String result = login.cellPhoneFeedback("+27838968976");
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedMessage() {
        Login login = new Login();
        String result = login.cellPhoneFeedback("08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean success = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", login.returnLoginStatus(success, "kyl_1"));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean success = login.loginUser("kyl_1", "wrongPass");
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(success, "kyl_1"));
    }

    // ---------- assertTrue / assertFalse tests (boolean outputs) ----------

    @Test
    public void testLoginSuccessful() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testPasswordMeetsRequirementsBoolean() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetRequirementsBoolean() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormattedBoolean() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedBoolean() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}