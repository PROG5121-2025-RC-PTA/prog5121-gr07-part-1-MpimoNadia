/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CHATAPP;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String regUsername;
    private String regPassword;
    private String regCell;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+") && number.length() <= 13 && number.matches("\\+\\d+");
    }

    public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        this.regUsername = username;
        this.regPassword = password;
        this.regCell = cell;
        return "Welcome, it is great to see you.";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(regUsername) && inputPassword.equals(regPassword);
    }

    public String returnLoginStatus(boolean status) {
        return status ? "Login successful. Welcome back!" : "Login failed. Please check your credentials.";
    }
}
