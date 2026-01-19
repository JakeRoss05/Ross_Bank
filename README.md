Ross Bank (Java)

Overview
This is a console-based banking application written in Java as part of a college programming assignment.
The program simulates basic banking functionality using a menu system and user input from the terminal.
The project focuses on fundamental programming concepts, not real-world security or persistence.

Features
•	Menu-driven interface
•	Create a bank account
•	View account details
•	Deposit money
•	Withdraw money
•	Basic input validation
•	Prevents withdrawing more than the available balance

Technologies Used
•	Java
•	Console input/output (Scanner)
•	Object-Oriented Programming (classes and methods)

How to Run
Using the command line
1.	Open a terminal in the project directory
2.	Compile the program:
3.	javac *.java
4.	Run the program (replace Main with your main class name):
5.	java Main

Using an IDE (IntelliJ / Eclipse)
•	Open the project
•	Locate the class containing public static void main(String[] args)
•	Run the program

How the Program Works
When the program starts, the user is shown a menu with numbered options.
The user selects an option by entering a number.
The program then:
•	Reads the input
•	Performs the selected banking operation
•	Returns to the menu until the user chooses to exit

Program Structure
•	Main class
Handles the menu system and user interaction.
•	Bank account class

Stores account details such as account number and balance, and contains methods for depositing and withdrawing money.

Limitations
•	Data is not saved when the program closes
•	No login or authentication system
•	Designed for educational purposes only

Purpose
This project was created to demonstrate:
•	Basic Java syntax
•	Control flow (loops and conditionals)
•	Object-oriented programming principles
•	Handling user input

