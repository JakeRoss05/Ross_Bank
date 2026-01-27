package com.mycompany.ross_bank;

//importing LocalDateTime and DateTimeFormatter for timestamping transactions (taking those values from the computer's system time) and ArrayList and List to store the transaction history
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String username;
    private final String password;
    private double balance;
    private final List<String> transactionHistory; //list to store transaction history
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //format for time on transactions
    
    public Account(String username, String password) {
        this.username = username; //setting the username
        this.password = password; //setting the password
        this.balance = 0.0; //initial balance set to 0
        this.transactionHistory = new ArrayList<>(); //initialising the transaction history list
    }
    
    public String getUsername() {
        return username; //getter for username
    }
    
    public String getPassword() {
        return password; //getter for password
    }
    
    public double getBalance() {
        return balance; //getter for balance
    }
    
    public void setBalance(double balance) {
        this.balance = balance; //setting balance
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password); //checking if the provided password matches the account's password
    }
    
    public void recordTransaction(String type, double amount) { //method to record a transaction
        String timestamp = LocalDateTime.now().format(dateFormatter); //getting the current time from the computer and formatting it
        String transaction = String.format("[%s] %s: $%.2f | Balance: $%.2f", 
            timestamp, type, amount, balance); //formatting the transaction string
        transactionHistory.add(transaction); //adding the transaction to the history list
    }
    
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) { //checking if there are no transactions
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n==== Transaction History ====");
        for (String transaction : transactionHistory) { //looping through and displaying each transaction
            System.out.println(transaction);
        }
        System.out.println("=============================\n");
    }
}
