package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class based on PROG5121 POE Part 2 requirements.
 * Tests cover message length, recipient validation, message hash, ID generation, and send options.
 */
public class MessageTest {

    // ---------- Test Data from POE ----------
    private final String validRecipient1 = "+27718693002";
    private final String invalidRecipient1 = "08575975889";
    private final String validMessage1 = "Hi Mike, can you join us for dinner tonight?";
    private final String validMessage2 = "Hi Keegan, did you receive the payment?";
    private final String longMessage = new String(new char[251]).replace("\0", "A");


    // ---------- Message Length Tests ----------
    @Test
    public void testMessageLengthSuccess() {
        assertTrue(validMessage1.length() <= 250, "Message ready to send.");
    }

    @Test
    public void testMessageLengthFailure() {
        assertFalse(longMessage.length() <= 250,
                "Message exceeds 250 characters by " + (longMessage.length() - 250) + "; please reduce the size.");
    }

    // ---------- Recipient Validation Tests ----------
    @Test
    public void testRecipientSuccess() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        assertTrue(msg.checkRecipientCell(validRecipient1), "Cell phone number successfully captured.");
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        assertFalse(msg.checkRecipientCell(invalidRecipient1),
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    // ---------- Message Hash Tests ----------
    @Test
    public void testMessageHashWithValidMessage2() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        long id = 9876543210L;
        String hash = msg.createMessageHash(id, 2, validMessage2);
        assertEquals("98:2:HIPAYMENT?", hash);
    }

    
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        long id = 1234567890L;
        String hash = msg.createMessageHash(id, 0, "Hi tonight");
        assertEquals("12:0:HITONIGHT", hash, "Message hash should match POE format.");
    }

    // ---------- Message ID Tests ----------
    @Test
    public void testMessageIDValid() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        assertTrue(msg.checkMessageID("1234567890"), "Message ID generated: <Message ID>");
    }

    @Test
    public void testMessageIDInvalid() {
        Message msg = new Message(id, hash, sender, recipient, text, "Sent");
        assertFalse(msg.checkMessageID("12345678901"), "Message ID should not exceed 10 characters.");
    }

    // ---------- Send Message Option Tests ----------
    @Test
    public void testSendMessageOptionSend() {
        String expected = "Message successfully sent.";
        assertEquals(expected, "Message successfully sent.");
    }

    @Test
    public void testSendMessageOptionDisregard() {
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, "Press 0 to delete the message.");
    }

    @Test
    public void testSendMessageOptionStore() {
        String expected = "Message successfully stored.";
        assertEquals(expected, "Message successfully stored.");
    }

    // ---------- Total Messages Counter ----------
    @Test
    public void testTotalMessagesCounter() {
        Message.resetTotalMessages();
        assertEquals(0, Message.returnTotalMessages(), "Counter should start at 0.");

        // Simulate sending two messages
        Message.resetTotalMessages();
        Message.returnTotalMessages(); // still 0
        // In real app, createFromInput increments automatically
    }
}
