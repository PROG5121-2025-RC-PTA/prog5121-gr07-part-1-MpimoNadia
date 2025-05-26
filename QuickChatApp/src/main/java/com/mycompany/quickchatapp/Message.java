/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String messageId;
    private String recipientCell;
    private String message;
    private String messageHash;
    private static int numMessagesSent = 0;
    private static final List<String> messagesSent = new ArrayList<>();

    // Constructor
    public Message(String recipientCell, String message) {
        this.messageId = generateMessageId();
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageHash = createMessageHash();

        numMessagesSent++;
        messagesSent.add(message);
    }

    // Generate random 10-digit message ID
    private String generateMessageId() {
        Random random = new Random();
        long id = (long)(Math.random() * 10000000000L);
        return String.format("%010d", id);
    }

    // Check message ID length <= 10
    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }

    // Check recipient cell number
    public boolean checkRecipientCell() {
        return recipientCell.length() <= 10 && recipientCell.startsWith("+");
    }

    // Create message hash
    private String createMessageHash() {
        String firstTwoDigits = messageId.substring(0, 2);
        String[] words = message.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : "";
        return (firstTwoDigits + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase();
    }

    // Print all sent messages
    public static List<String> getMessagesSent() {
        return messagesSent;
    }

    // Return total number of messages
    public static int returnTotalMessages() {
        return numMessagesSent;
    }

    // Additional method to show message details
    public void printMessageDetails() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipientCell);
        System.out.println("Message: " + message);
    }
}