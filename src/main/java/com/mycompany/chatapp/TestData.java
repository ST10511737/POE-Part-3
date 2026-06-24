package com.mycompany.chatapp;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Message> getTestMessages() {
        List<Message> testMessages = new ArrayList<>();

        testMessages.add(new Message("1", "hash1", "SYSTEM", "+27834557896", "Did you get the cake?", "Sent"));
        testMessages.add(new Message("2", "hash2", "SYSTEM", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        testMessages.add(new Message("3", "hash3", "SYSTEM", "+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
        testMessages.add(new Message("4", "hash4", "Developer", "0838884567", "It is dinner time!", "Sent"));
        testMessages.add(new Message("5", "hash5", "SYSTEM", "+27838884567", "Ok, I am leaving without you.", "Stored"));

        return testMessages;
    }
}
