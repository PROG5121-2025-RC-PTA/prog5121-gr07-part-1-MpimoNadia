/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CHATAPP;
import java.util.Random;

public class Message {
    private static int totalMessagesSent = 0;
    private String text;
    private String recipientPhone;
    private String messageID;
    private String messageHash;

    public Message(String text, String recipientPhone) {
        this.text = text;
        this.recipientPhone = recipientPhone;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public boolean checkRecipientCell() {
        return recipientPhone.startsWith("+") && recipientPhone.length() <= 13;
    }

    public String createMessageHash() {
        String[] words = text.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + totalMessagesSent + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    public String sendMessageOption() {
        System.out.println("Choose an option:\n1) Send Message\n2) Disregard Message\n3) Store Message");
        String input = new java.util.Scanner(System.in).nextLine();

        switch (input) {
            case "1":
                totalMessagesSent++;
                return "sent";
            case "2":
                return "disregard";
            case "3":
                return "store";
            default:
                System.out.println("Invalid choice. Defaulting to 'store'.");
                return "store";
        }
    }

    public String printMessageDetails() {
        return "Message ID: " + messageID + "\nHash: " + messageHash + "\nRecipient: " + recipientPhone + "\nMessage: " + text;
    }

    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    public String getText() {
        return text;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}