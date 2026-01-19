package com.mycompany.ross_bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String username;
    private final String password;
    private double balance;
    private final List<String> transactionHistory;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    
    public void recordTransaction(String type, double amount) {
        String timestamp = LocalDateTime.now().format(dateFormatter);
        String transaction = String.format("[%s] %s: $%.2f | Balance: $%.2f", 
            timestamp, type, amount, balance);
        transactionHistory.add(transaction);
    }
    
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n==== Transaction History ====");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("=============================\n");
    }
}
