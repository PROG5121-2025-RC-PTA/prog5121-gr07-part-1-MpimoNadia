/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CHATAPP;
import java.util.Scanner;

public class QuickChatApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = true; // Set to true for now; replace with login logic

        if (loggedIn) {
            System.out.println("Welcome to QuickChat.");
            System.out.print("How many messages would you like to send? ");
            int messageCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < messageCount; i++) {
                System.out.print("Enter recipient phone number (with country code): ");
                String recipient = scanner.nextLine();

                System.out.print("Enter your message (max 250 chars): ");
                String text = scanner.nextLine();

                if (text.length() > 250) {
                    int excess = text.length() - 250;
                    System.out.println("Message exceeds 250 characters by " + excess + ", please reduce size.");
                    i--; // retry the message
                    continue;
                }

                Message msg = new Message(text, recipient);

                if (!msg.checkRecipientCell()) {
                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                    i--;
                    continue;
                }

                String choice = msg.sendMessageOption();

                switch (choice) {
                    case "sent":
                        MessageStorage.sentMessages.add(msg);
                        MessageStorage.messageHashes.add(msg.getMessageHash());
                        MessageStorage.messageIDs.add(msg.getMessageID());
                        System.out.println("Message successfully sent.");
                        break;

                    case "disregard":
                        MessageStorage.disregardedMessages.add(msg);
                        System.out.println("Press 0 to delete message.");
                        break;

                    case "store":
                        MessageStorage.storedMessages.add(msg);
                        MessageStorage.storeToJson(msg);
                        System.out.println("Message successfully stored.");
                        break;
                }

                System.out.println("\nMessage Details:");
                System.out.println(msg.printMessageDetails());
                System.out.println();
            }

            System.out.println("Total messages sent: " + Message.getTotalMessagesSent());
        }

        scanner.close();
    }
}