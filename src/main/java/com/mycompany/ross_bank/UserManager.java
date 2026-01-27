package com.mycompany.ross_bank;

//importing HashMap and Map to store user accounts
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, Account> accounts; //map to store accounts with username as key
    
    public UserManager() {
        accounts = new HashMap<>(); //initialising the accounts map
    }
    
    public boolean createAccount(String username, String password) { //method to create a new account
        if (accounts.containsKey(username)) {  //checking if the username already exists
            return false; //returning false if it does
        }
        accounts.put(username, new Account(username, password)); //adding the new account to the map
        return true;
    }
    
    public Account login(String username, String password) { //method to login to an account
        if (!accounts.containsKey(username)) {
            return null; 
        }
        Account account = accounts.get(username); //retrieving the account associated with the username
        if (account.verifyPassword(password)) { //verifying the password
            return account; //return ifpassword is correct
        }
        return null;
    }
    
    public boolean userExists(String username) { //method to check if a user exists
        return accounts.containsKey(username);
    }

}
