package com.mycompany.ross_bank;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, Account> accounts;
    
    public UserManager() {
        accounts = new HashMap<>();
    }
    
    public boolean createAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            return false;
        }
        accounts.put(username, new Account(username, password));
        return true;
    }
    
    public Account login(String username, String password) {
        if (!accounts.containsKey(username)) {
            return null;
        }
        Account account = accounts.get(username);
        if (account.verifyPassword(password)) {
            return account;
        }
        return null;
    }
    
    public boolean userExists(String username) {
        return accounts.containsKey(username);
    }

}
