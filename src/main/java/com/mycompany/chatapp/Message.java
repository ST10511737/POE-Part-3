package com.mycompany.chatapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {
    private long id;              // 10-digit random ID
    private String recipient;
    private String text;
    private String hash;          // message hash
    private static int totalMessages = 0; // batch counter

    // Needed for Gson
    public Message(String id1, String hash1, String sender, String recipient1, String text1, String sent) {}

    public Message(long id, String recipient, String text, String hash) {
        this.id = id;
        this.recipient = recipient;
        this.text = text;
        this.hash = hash;
    }

    // Getters
    public long getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getHash() { return hash; }

    // Reset counter per batch
    public static void resetTotalMessages() {
        totalMessages = 0;
    }

    // Return total messages sent/stored
    public static int returnTotalMessages() {
        return totalMessages;
    }

    //Check that message ID is no longer than 10 characters
    public boolean checkMessageID(String id) {
        return id.length() <= 10;
    }

    //Validate recipient cell number (South Africa format only)
    public boolean checkRecipientCell(String cell) {
        return cell.matches("\\+27\\d{9}");
    }

    //Create a message hash using ID, message number, and first/last words
    public String createMessageHash(long id, int num, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        String idStr = String.valueOf(id);
        return idStr.substring(0, 2) + ":" + num + ":" + first + last;
    }

    //Prompt user and return a Message object (for JSON saving)
    public Message createFromInput(int num) {
        Scanner sc = new Scanner(System.in);

        // Recipient loop
        String cell;
        while (true) {
            System.out.print("\nEnter recipient number (+27XXXXXXXXX): ");
            cell = sc.nextLine().trim();
            if (checkRecipientCell(cell)) {
                break;
            } else {
                System.out.println("Invalid cell number. Must be +27 followed by 9 digits. Try again.");
            }
        }

        // Message loop
        String messageText;
        while (true) {
            System.out.print("\nEnter message (max 250 chars): ");
            messageText = sc.nextLine();
            if (messageText.length() <= 250) {
                break;
            } else {
                System.out.println("Message too long. Reduce size and try again.");
            }
        }

        // Choice loop
        while (true) {
            System.out.println("Choose option:\n1. Send Message\n0. Disregard/Delete Message\n3. Store Message");
            String choiceStr = sc.nextLine().trim();
            if (!choiceStr.matches("[0-3]")) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            int choice = Integer.parseInt(choiceStr);

            if (choice == 0) {
                System.out.println("Message disregarded and deleted.");
                // ✅ Reset counter to zero if discarded
                resetTotalMessages();
                System.out.println("Total messages sent: 0");
                return null;
            } else {
                // Generate unique 10-digit ID
                long id = (long)(Math.random() * 9000000000L) + 1000000000L;
                String idStr = String.valueOf(id);
                if (!checkMessageID(idStr)) {
                    System.out.println("Generated ID invalid (too long).");
                    return null;
                }
                String hash = createMessageHash(id, num, messageText);

                System.out.println("\nMessage successfully processed.");
                System.out.println("Message ID: " + id);
                System.out.println("Message Hash: " + hash);
                System.out.println("Recipient: " + cell);
                System.out.println("Message: " + messageText);

                totalMessages++;
                System.out.println("Total messages sent: " + totalMessages);
                return new Message(id, cell, messageText, hash);
            }
        }
    }

    //Save messages to JSON
    public static void saveMessages(List<Message> messages) {
        try (FileWriter writer = new FileWriter("src/main/resources/messages.json")) {
            Gson gson = new Gson();
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    //Load messages from JSON
    public static List<Message> loadMessages() {
        try (FileReader reader = new FileReader("src/main/resources/messages.json")) {
            Gson gson = new Gson();
            java.lang.reflect.Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>(); // return empty list if file not found
        }
    }

    
    String getSender() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
