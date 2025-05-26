/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChatApp {
    private static final List<Message> messages = new ArrayList<>();
    private static boolean loggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate login
        System.out.println("Welcome to QuickChat!");
        System.out.print("Enter username to log in: ");
        String username = scanner.nextLine();
        if (!username.isEmpty()) {
            loggedIn = true;
            System.out.println("Login successful. Hello, " + username + "!");
        } else {
            System.out.println("Login failed. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages (Coming Soon)");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (loggedIn) {
                        System.out.print("How many messages do you want to send? ");
                        int num = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        for (int i = 0; i < num; i++) {
                            System.out.print("Enter recipient cell number (with + code): ");
                            String recipientCell = scanner.nextLine();

                            System.out.print("Enter message (max 250 chars): ");
                            String messageContent = scanner.nextLine();

                            if (messageContent.length() > 250) {
                                System.out.println("Please enter a message of less than 250 characters.");
                                i--; // retry this message
                                continue;
                            }

                            // Create and add message
                            Message message = new Message(recipientCell, messageContent);

                            // Validate recipient
                            if (!message.checkRecipientCell()) {
                                System.out.println("Invalid recipient number. Must be <=10 and start with '+'.");
                                i--; // retry this message
                                continue;
                            }

                            // Print details
                            message.printMessageDetails();

                            // Ask what to do with the message
                            System.out.println("Options:\n1) Send\n2) Discard\n3) Store later");
                            int action = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (action) {
                                case 1:
                                    messages.add(message);
                                    System.out.println("Message sent.");
                                    break;
                                case 2:
                                    System.out.println("Message discarded.");
                                    break;
                                case 3:
                                    // Simulate storing to JSON (could be file write)
                                    System.out.println("Message stored for later (JSON feature to be implemented).");
                                    break;
                            }
                        }
                        // Show total
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    } else {
                        System.out.println("You must log in first!");
                    }
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}