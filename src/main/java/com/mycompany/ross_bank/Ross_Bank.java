package com.mycompany.ross_bank;

import static java.lang.Double.parseDouble;
import java.util.Scanner;

public class Ross_Bank {

    public static void main(String[] args) {
        try ( //scanner created to be able to read the user's input
                Scanner userinput = new Scanner(System.in)) {
            UserManager userManager = new UserManager();
            Account currentUser = null;
            
            // Authentication menu - user must create or login first
            boolean authenticated = false;
            while (!authenticated) {
                System.out.println("\n==== Welcome to Ross Bank ====");
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                String authOption = userinput.nextLine().trim();
                
                switch (authOption) {
                    case "1" -> {
                        // Create account
                        System.out.print("Enter username: ");
                        String newUsername = userinput.nextLine().trim();
                        if (newUsername.isEmpty()) {
                            System.out.println("Username cannot be empty");
                            break;
                        }
                        if (userManager.userExists(newUsername)) {
                            System.out.println("Username already exists. Please try another.");
                            break;
                        }
                        System.out.print("Enter password: ");
                        String newPassword = userinput.nextLine().trim();
                        if (newPassword.isEmpty()) {
                            System.out.println("Password cannot be empty");
                            break;
                        }
                        if (userManager.createAccount(newUsername, newPassword)) {
                            System.out.println("Account created successfully! You can now login.");
                        }
                    }
                        
                    case "2" -> {
                        // Login
                        System.out.print("Enter username: ");
                        String username = userinput.nextLine().trim();
                        System.out.print("Enter password: ");
                        String password = userinput.nextLine().trim();
                        
                        currentUser = userManager.login(username, password);
                        if (currentUser != null) {
                            System.out.println("Login successful! Welcome, " + username + "!");
                            authenticated = true;
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                    }
                        
                    case "3" -> {
                        // Exit
                        System.out.println("Thank you for banking with Ross. Goodbye!");
                        userinput.close();
                        return;
                    }
                        
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
            
            // Bank operations menu (after successful login)
            String option;
            if (currentUser == null) {
                System.out.println("Error: User not authenticated");
                return;
            }
            do {
                System.out.println("\n==== Ross Bank - " + currentUser.getUsername() + " ====");
                System.out.println("Please choose an option");
                System.out.println("1. Wire");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Logout");
                
                option = userinput.nextLine().trim();
                double balance = currentUser.getBalance();
                String amtstring;
                switch (option) {
                    case "1" -> {
                        //case for wiring money
                        System.out.println("Enter amount that you would like to wire");
                        amtstring = userinput.nextLine().trim();
                        double amount; //updating the double "amount"
                        try {
                            amount = parseDouble(amtstring); //taking the amount (as a string value) and converting it into a double to be able to do math calculations
                        } catch (NumberFormatException e){ //catching when Java fails to convert the sting into a number and tell the user to enter a correct one this time
                            System.out.println("Please enter a number");
                            break;
                        }
                        if (amount <= 0) {
                            System.out.println("Wire must be greater than 0");
                            break;
                        }
                        if (amount > balance) {
                            System.out.println("Balance is insufficient");
                            break;
                        }
                        balance -= amount;//subtracting the amount from the balance
                        currentUser.setBalance(balance);
                        System.out.println("Wire has been successful. Thank you for banking with Ross. Updated Balance: " + String.format("%.2f", balance));
                        //terminating the loop
                    }
                    case "2" -> {
                        //case for depositing money
                        System.out.println("Enter amount that you would like to deposit");
                        amtstring = userinput.nextLine().trim();
                        double amount;
                        try{
                            amount = parseDouble(amtstring);
                        } catch (NumberFormatException e) { //catching when Java fails to convert the sting into a number and tell the user to enter a correct one this time
                            System.out.println("Please enter a number");
                            break;
                        }
                        if (amount <=0) {
                            System.out.println("Deposit must be greater than 0");
                            break;
                        }
                        balance += amount;//adding the amount to the balance
                        currentUser.setBalance(balance);
                        System.out.println("Deposit has been successful. Thank you for banking with Ross. Updated Balance: " + String.format("%.2f", balance));
                    }
                    case "3" -> System.out.println("Account Balance: " + String.format("%.2f", balance));
                    case "4" -> System.out.println("Goodbye. Thank you for banking with Ross");
                }
            } while (!option.equals("4"));
        }
    }
}