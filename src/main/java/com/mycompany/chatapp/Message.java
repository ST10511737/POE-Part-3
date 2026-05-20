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
public class Message {
   private int totalMessages = 0;

    // Check that message ID is no longer than 10 characters
    public boolean checkMessageID(String id) {
        return id.length() <= 10;
    }

    // Validate recipient cell number
    public String checkRecipientCell(String cell) {
        if (cell.startsWith("+27") && cell.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain an international code.";
        }
    }

    // Create a message hash using ID, message number, and first/last words
    public String createMessageHash(String id, int num, String message) {
        String[] words = message.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        return id.substring(0, 2) + ":" + num + ":" + first + last;
    }

    // Send a message with options
    public void sendMessage(int num) {
        Scanner sc = new Scanner(System.in);
        String id = String.valueOf((int)(Math.random() * 1000000000));

        System.out.print("Enter recipient number (+27...): ");
        String cell = sc.nextLine();
        System.out.print("\nEnter message (max 250 chars): ");
        String message = sc.nextLine();

        if (message.length() > 250) {
            System.out.println("\nMessage exceeds 250 characters by " + (message.length() - 250) + "; please reduce the size.");
            return;
        }

        String hash = createMessageHash(id, num, message);
        System.out.println("\nMessage Hash: " + hash);

        System.out.println("Choose option:\n1. Send Message\n2. Disregard Message\n3. Store Message");
        int choice = sc.nextInt();
        sc.nextLine();

       switch (choice) {
           case 1:
               System.out.println("Message successfully sent.");
               totalMessages++;
               break;
           case 2:
               System.out.println("Press 0 to delete the message.");
               break;
           case 3:
               System.out.println("Message successfully stored.");
               totalMessages++;
               break;
           default:
               System.out.println("Invalid choice.");
               break;
       }

        System.out.println("Message ID: " + id);
        System.out.println("Recipient: " + cell);
        System.out.println("Message: " + message);
    }

    // Return total messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }
}