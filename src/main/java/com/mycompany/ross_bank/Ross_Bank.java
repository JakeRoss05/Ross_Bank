package com.mycompany.ross_bank;

//impoting parseDouble to convert strings to double values and a scanner to read user input
import static java.lang.Double.parseDouble;
import java.util.Scanner;

public class Ross_Bank {

    public static void main(String[] args) {
        try ( 
                Scanner userinput = new Scanner(System.in)) { //scanner created to be able to read the user's input
            UserManager userManager = new UserManager(); //userManager object created to manage user accounts
            Account currentUser = null; //variable to store the currently logged-in user    
            
            // Authentication menu - user must create or login first
            boolean authenticated = false; //variable to track if the user is authenticated
            while (!authenticated) {
                System.out.println("\n==== Welcome to Ross Bank ====");
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                String authOption = userinput.nextLine().trim(); //reading the users input, taking and storing it as a string and trimming any extra spaces
                
                switch (authOption) {
                    case "1" -> {
                        // Create account
                        System.out.print("Enter username: ");
                        String newUsername = userinput.nextLine().trim();
                        if (newUsername.isEmpty()) { //checking if the username is empty
                            System.out.println("Username cannot be empty");
                            break; //exiting the case
                        }
                        if (userManager.userExists(newUsername)) { //checking if the username already exists
                            System.out.println("Username already exists. Please try another.");
                            break;
                        }
                        System.out.print("Enter password: ");
                        String newPassword = userinput.nextLine().trim();
                        if (newPassword.isEmpty()) { //checking if the password is empty
                            System.out.println("Password cannot be empty");
                            break;
                        }
                        if (userManager.createAccount(newUsername, newPassword)) { //creating the account
                            System.out.println("Account created successfully! You can now login.");
                        }
                    }
                        
                    case "2" -> {
                        // Login
                        System.out.print("Enter username: ");
                        String username = userinput.nextLine().trim();
                        System.out.print("Enter password: ");
                        String password = userinput.nextLine().trim();
                        
                        currentUser = userManager.login(username, password); //attempting to login with the provided username and password
                        if (currentUser != null) { //checking if the login was successful
                            System.out.println("Login successful! Welcome, " + username + "!");
                            authenticated = true; //setting authenticated to true to exit the loop
                        } else {
                            System.out.println("Invalid username or password. Please try again."); //what happens when the logins incorrect
                        }
                    }
                        
                    case "3" -> {
                        // Exit
                        System.out.println("Thank you for banking with Ross. Goodbye!");
                        userinput.close(); //closing the scanner
                        return; //exiting the program
                    }
                        
                    default -> System.out.println("Invalid option. Please try again."); //handling invalid menu options
                }
            }
            
            // Bank operations menu (after successful login)
            String option; //variable to store the user's menu option
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
                System.out.println("4. Transaction History");
                System.out.println("5. Logout");
                
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
                        currentUser.recordTransaction("WITHDRAWAL", amount);
                        System.out.println("Wire has been successful. Thank you for banking with Ross. Updated Balance: " + String.format("%.2f", balance));
                        //terminating the loop
                    }
                    case "2" -> {
                        //case for depositing money
                        System.out.println("Enter amount that you would like to deposit");
                        amtstring = userinput.nextLine().trim();
                        double amount; //updating the double "amount"
                        try{
                            amount = parseDouble(amtstring);
                        } catch (NumberFormatException e) { //catching when Java fails to convert the sting into a number and tell the user to enter a correct one this time
                            System.out.println("Please enter a number");
                            break;
                        }
                        if (amount <=0) { //checking if the amount is less than or equal to 0
                            System.out.println("Deposit must be greater than 0");
                            break;
                        }
                        balance += amount;//adding the amount to the balance
                        currentUser.setBalance(balance); //updating the balance in the account
                        currentUser.recordTransaction("DEPOSIT", amount); //recording the transaction
                        System.out.println("Deposit has been successful. Thank you for banking with Ross. Updated Balance: " + String.format("%.2f", balance));
                    }
                    case "3" -> System.out.println("Account Balance: " + String.format("%.2f", balance)); //displaying the balance formatted to 2 decimal places
                    case "4" -> currentUser.displayTransactionHistory(); //displaying the transaction history
                    case "5" -> System.out.println("Goodbye. Thank you for banking with Ross"); 
                }
            } while (!option.equals("5")); //loop continues until you logout
        }
    }
}