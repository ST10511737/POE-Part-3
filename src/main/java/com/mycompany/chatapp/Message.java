/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {
    private int id;
    private String recipient;
    private String text;
    private int totalMessages = 0;

    // Needed for Gson
    public Message() {}

    public Message(int id, String recipient, String text) {
        this.id = id;
        this.recipient = recipient;
        this.text = text;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

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

    // Send a message with options and save to JSON
    public void sendMessage(int num) {
        Scanner sc = new Scanner(System.in);
        String idStr = String.valueOf((int)(Math.random() * 1000000000));

        System.out.print("Enter recipient number (+27...): ");
        String cell = sc.nextLine();
        System.out.print("\nEnter message (max 250 chars): ");
        String message = sc.nextLine();

        if (message.length() > 250) {
            System.out.println("\nMessage exceeds 250 characters by " + (message.length() - 250) + "; please reduce the size.");
            return;
        }

        String hash = createMessageHash(idStr, num, message);
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
                System.out.println("Message disregarded.");
                return;
            case 3:
                System.out.println("Message successfully stored.");
                totalMessages++;
                break;
            default:
                System.out.println("Invalid choice.");
               
        }

    }

    // Return total messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }
    
    // prompt user and return a Message object
    public Message createFromInput(int num) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter recipient number (+27...): ");
        String cell = sc.nextLine();

        System.out.print("\nEnter message (max 250 chars): ");
        String messageText = sc.nextLine();

        if (messageText.length() > 250) {
            System.out.println("\nMessage exceeds 250 characters by " + (messageText.length() - 250) + "; please reduce the size.");
            return null;
        }

        System.out.println("Choose option:\n1. Send Message\n2. Disregard Message\n3. Store Message");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 2) {
            System.out.println("Message disregarded.");
            return null; // don’t save
        }

        System.out.println("Message successfully processed.");
        return new Message(num, cell, messageText); // return actual message
    }
    
    // Save messages to JSON
        public static void saveMessages(List<Message> messages) {
            try (FileWriter writer = new FileWriter("src/main/resources/messages.json")) {
                Gson gson = new Gson();
                gson.toJson(messages, writer);
            } catch (IOException e) {
                System.out.println("Error saving messages: " + e.getMessage());
            }
        }

    // Load messages from JSON
        public static List<Message> loadMessages() {
            try (FileReader reader = new FileReader("src/main/resources/messages.json")) {
                Gson gson = new Gson();
                java.lang.reflect.Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
                return gson.fromJson(reader, listType);
            } catch (IOException e) {
                return new ArrayList<>(); // return empty list if file not found
            }
        }
}
