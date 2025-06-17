/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CHATAPP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessageStorage {
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();

    public static void storeToJson(Message msg) {
        JSONObject json = new JSONObject();
        json.put("messageID", msg.getMessageID());
        json.put("messageHash", msg.getMessageHash());
        json.put("recipient", msg.getRecipientPhone());
        json.put("message", msg.getText());

        JSONArray array = new JSONArray();
        array.add(json);

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(array.toJSONString());
            file.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
