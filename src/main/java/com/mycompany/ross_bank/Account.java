package com.mycompany.ross_bank;

public class Account {
    private final String username;
    private final String password;
    private double balance;
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
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
}
