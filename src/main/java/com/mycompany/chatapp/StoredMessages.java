package com.mycompany.chatapp;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StoredMessages {
    private static List<Message> allMessages = new ArrayList<>();
    private static final String FILE_NAME = "messages.json";

    // ===== JSON Handling =====
    public static List<Message> loadMessages() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            List<Message> messages = gson.fromJson(reader, new TypeToken<List<Message>>(){}.getType());
            if (messages != null) {
                allMessages = messages;
            }
        } catch (IOException e) {
            System.out.println("No existing messages found, starting fresh.");
        }
        return allMessages;
    }

    public static void saveMessages(List<Message> messages) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    public static int returnTotalMessages() {
        return allMessages.size();
    }

    // ===== Stored Messages Menu =====
    public void storedMessagesMenu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== STORED MESSAGES MENU =====");
            System.out.println("1. Display sender and recipient");
            System.out.println("2. Display longest message");
            System.out.println("3. Search by message ID");
            System.out.println("4. Search by recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back to Chat Menu");
            System.out.print("Choose option: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    for (Message msg : allMessages) {
                        System.out.println("Sender: " + msg.getSender() +
                                           " | Recipient: " + msg.getRecipient());
                    }
                    break;
                case "2":
                    Message longest = null;
                    for (Message msg : allMessages) {
                        if (longest == null || msg.getText().length() > longest.getText().length()) {
                            longest = msg;
                        }
                    }
                    if (longest != null) {
                        System.out.println("Longest message: " + longest.getText());
                    } else {
                        System.out.println("No messages stored.");
                    }
                    break;
                case "3":
                    System.out.print("Enter message ID: ");
                    String id = sc.nextLine().trim();
                    for (Message msg : allMessages) {
                        if (id.equals(msg.getId())) {
                        System.out.println("Recipient: " + msg.getRecipient() +
                                           " | Message: " + msg.getText());
                        break;
                    }

                    }
                    break;
                case "4":
                    System.out.print("Enter recipient: ");
                    String recipient = sc.nextLine().trim();
                    for (Message msg : allMessages) {
                        if (msg.getRecipient().equalsIgnoreCase(recipient)) {
                            System.out.println("Sender: " + msg.getSender() +
                                               " | Message: " + msg.getText());
                        }
                    }
                    break;
                case "5":
                    System.out.print("Enter hash to delete: ");
                    String hash = sc.nextLine().trim();
                    Message toRemove = null;
                    for (Message msg : allMessages) {
                        if (msg.getHash().equals(hash)) {
                            toRemove = msg;
                            break;
                        }
                    }
                    if (toRemove != null) {
                        allMessages.remove(toRemove);
                        saveMessages(allMessages);
                        System.out.println("Message deleted.");
                    } else {
                        System.out.println("No message found with that hash.");
                    }
                    break;
                case "6":
                    System.out.println("\n===== STORED MESSAGES REPORT =====");
                    for (Message msg : allMessages) {
                        System.out.println(msg.toString());
                    }
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
