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
    @Test
    public void testCheckUserNameValid() {
        Login login = new Login();
        assertTrue(login.checkUserName("abc_"), "Valid username should pass");
    }

    @Test
    public void testCheckUserNameInvalid() {
        Login login = new Login();
        assertFalse(login.checkUserName("abcdef"), "Invalid username should fail");
    }

    @Test
    public void testUsernameFeedbackValid() {
        Login login = new Login();
        assertEquals("Username successfully captured", login.usernameFeedback("abc_"));
    }

    @Test
    public void testUsernameFeedbackTooLong() {
        Login login = new Login();
        assertTrue(login.usernameFeedback("abcde_").contains("too long"));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Abc123!@"), "Valid password should pass");
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("abc"), "Too short password should fail");
    }

    @Test
    public void testPasswordFeedbackValid() {
        Login login = new Login();
        assertEquals("Password successfully captured", login.passwordFeedback("Abc123!@"));
    }

    @Test
    public void testPasswordFeedbackInvalid() {
        Login login = new Login();
        assertTrue(login.passwordFeedback("abc").contains("too short"));
    }

    @Test
    public void testCheckCellPhoneNumberValid() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testCheckCellPhoneNumberInvalid() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("123456"));
    }

    @Test
    public void testCellPhoneFeedbackValid() {
        Login login = new Login();
        assertEquals("Cell phone number successfully added.", login.cellPhoneFeedback("+27123456789"));
    }

    @Test
    public void testCellPhoneFeedbackInvalid() {
        Login login = new Login();
        assertTrue(login.cellPhoneFeedback("12345").contains("must start with '+'"));
    }

    @Test
    public void testRegisterUserValid() {
        Login login = new Login();
        String result = login.registerUser("abc_", "Abc123!@", "+27123456789");
        assertEquals("All details successfully captured.", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        Login login = new Login();
        String result = login.registerUser("abcdef", "Abc123!@", "+27123456789");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        Login login = new Login();
        String result = login.registerUser("abc_", "abc", "+27123456789");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUserInvalidCell() {
        Login login = new Login();
        String result = login.registerUser("abc_", "Abc123!@", "12345");
        assertTrue(result.contains("Cell-phone number incorrectly formatted"));
    }

    @Test
    public void testLoginUserSuccess() {
        Login login = new Login();
        login.registerUser("abc_", "Abc123!@", "+27123456789");
        assertTrue(login.loginUser("abc_", "Abc123!@"));
    }

    @Test
    public void testLoginUserFailure() {
        Login login = new Login();
        login.registerUser("abc_", "Abc123!@", "+27123456789");
        assertFalse(login.loginUser("abc_", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        String message = login.returnLoginStatus(true, "abc_");
        assertTrue(message.contains("Welcome abc_"));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login login = new Login();
        String message = login.returnLoginStatus(false, "abc_");
        assertTrue(message.contains("incorrect"));
    }
}
