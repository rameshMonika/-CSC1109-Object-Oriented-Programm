import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import java.util.Date;
import java.util.Scanner;
/**
 * Author: Darren, Min Xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
 * Description: The Bank class represents a bank
 * that manages a ArrayList of accounts, PIN validation
 * and account balance.
 */

/**
 * The Bank class represents a bank that manages a list of accounts, PIN validation, and account balance.
 */
 public class Bank {
     private static ArrayList<Account> accounts;
     private static String bankName;
     //private static Exchange exchange;
   
     /**
      * Gets the name of the bank.
      * 
      * @return The name of the bank.
      */
     public static String getBankname() {
         return bankName;
     }
    /**
     * Sets the name of the bank.
     * 
     * @params The name of the bank.
     */
    public static void setBankname(String name) {
        bankName = name;
    }

     /**
      * Adds an account to the bank.
      * 
      * @param account The account to add.
      */
     public static void addAccount(Account account) {
         accounts.add(account);
     }
 
     /**
      * Remove an account from the bank.
      * 
      * @param account The account to remove.
      */
     public static void removeAccount(Account account) {
         accounts.remove(account);
     }
 
     /**
      * Gets account number from the account class and print the all accountNumbers
      */
     public static void getAccountNumbers() {
         for (Account account : accounts) {
             System.out.println(account.getAccountNumber());
         }
     }
      /**
     * returns a account number from the account class
     * 
     * @params the account number tied to the account
     */
    public static Account getAccount(int accno) {
        for (Account account : accounts) {
            if (accno == account.getAccountNumber())
                return account;
        }
        return null;
    }
 
     /**
      * Validates the PIN with the following accountNumber's pin and print the outcome.
      * 
      * @param accountNumber The following accountNumber to validate pin.
      * @param PIN           The pin to validate.
      * @return True if the PIN is correct, false otherwise.
      */
      public static boolean validatePIN(int accountNumber, int PIN) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getPIN() == PIN) {
                    return true;
                }
            }
        }
        return false;
    }
 
     /**
      * Gets the account balance and prints the following accountNumber's balance.
      * 
      * @param accountNumber The following accountNumber to find the balance.
      * @param currency      The currency of the balance to show
      */
      public static Double getAccountBalance(int accountNumber, Currency currency) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account.getBalance(currency);
            }
        }
        return 0.0;
    }
    /**
      * Converts the given amount from the given currency to SGD.
      * 
      * @param accountNumber The account number to convert the currency.
      * @param currency      The currency of the amount to be converted.
      * @param amount        The amount to be converted.
      */
     /* public void convertCurrency(int accountNumber, String currency, double amount){
         for (Account account : accounts) {
             if (account.getAccountNumber() == accountNumber) {
                if (account.getBalance("SGD") < amount) {
                    System.out.println("Insufficient balance");
                    return;
                }
                 System.out.println("Converted amount is " + exchange.convertCurrency(currency, amount));
                 account.setBalance("SGD", account.getBalance("SGD") - amount);
                 account.addBalance(currency, exchange.convertCurrency(currency, amount));

             }
         }
     } */

    /*
    * Prints the start page for the customer
    */
    public static void printStartPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + bankName);
        System.out.println("1 | Login");
        System.out.println("0 | Exit");
        int input = scanner.nextInt();
        try{
            switch (input) {
                case 1:
                    printCustomerLoginPage();
                    break;
                case 0:
                    System.out.println("Thank you for using our service");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid input");
                    printStartPage();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            printStartPage();
        }
    }
    public static boolean accountExists(int accountId) {
        // Assuming accounts is a List or Array of Account objects
        for (Account account : accounts) {
            if (account.getAccountNumber()== (accountId)) {
                return true;
            }
        }
        return false;
    }
     /*
      * Prints the login page for the customer
      */
    public static void printCustomerLoginPage(){
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Enter your account number: ");
        int accno = scanner.nextInt();
        if (accountExists(accno)){
            System.out.println("Enter your PIN: ");
            int pin = scanner.nextInt();
            for (Account account : accounts) {
                if (account.getAccountNumber() == accno) {
                    if (validatePIN(accno, pin)) {
                        printCustomerMenu(account);
                    } else {
                        counter++;
                        if (counter < 3) {
                            System.out.println("Invalid PIN, please try again");
                            printCustomerLoginPage();
                        } else {
                            printLoginFailPage();
                        }
                    }
                }
            }
        } else {
            System.out.println("Account not found");
            printCustomerLoginPage();
        
        }
    }
    /*
     * Prints the login fail page for the customer
     */
    public static void printLoginFailPage() {
        System.out.println("Login failed");
        printStartPage();
    }
     /*
      * prints the Customer menu page
      */
    public static void printCustomerMenu(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + account.getCustomer().getName());
        System.out.println("Select actions:");
        System.out.println("1 | Check balance");
        System.out.println("2 | Transfer");
        System.out.println("3 | View account details");
        System.out.println("4 | Account settings");
        System.out.println("0 | Logout");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printBalancePage(account);
                break;
            case 2:
                printTransferPage(account);
                break;
            case 3:
                printAccountDetailsPage(account);
                break;
            case 4:
                printAccountSettingsPage(account);
                break;
            case 0:
                printLogoutPage();
                break;
            default:
                System.out.println("Invalid input");
                printCustomerMenu(account);
                break;
        }
    }
    /*
     * Prints the balance page for the customer
     */
    public static void printBalancePage(Account account) {
        System.out.println("Current Account Balance");
        System.out.println("SGD: " + account.getBalance(Currency.SGD));
        System.out.println("USD: " + account.getBalance(Currency.USD));
        System.out.println("EUR: " + account.getBalance(Currency.EUR));
        System.out.println("JPY: " + account.getBalance(Currency.JPY));
        System.out.println("MYR: " + account.getBalance(Currency.MYR));
        printMoreActions(account);
    }
    /*
     * Prints the transfer page for the customer
     */
    public static void printTransferPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 | Inter Account Transfer");
        System.out.println("2 | Third Party Transfer");
        System.out.println("3 | Back to main menu");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                printInterTransferPage(account);
                break;
            case 2:
                printThirdPartyTransferPage(account);
                break;
            case 3:
                printCustomerMenu(account);
            case 0:
                printLogoutPage();
                break;
        }
        printMoreActions(account);
    }
    /*
     * Prints the inter account transfer page for the customer
     */
    public static void printInterTransferPage(Account account) {
        System.out.println("Accounts available for transfer: ");
        ArrayList<Account> availableAccounts = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getCustomerIC().equals(account.getCustomerIC())
                    && acc.getAccountNumber() != account.getAccountNumber()) {
                System.out.println(acc.getAccountNumber());
                availableAccounts.add(acc);
            }
        }
        if (availableAccounts.size() < 1) {
            System.out.println("There are no accounts linked.");
            printMoreActions(account);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno = scanner.nextInt();
        if (accno == account.getAccountNumber()) {
            System.out.println("Cannot transfer to the same account");
            printTransferPage(account);
        }
        Account receiving = getAccount(accno);
        if(receiving.getCustomerIC() != account.getCustomerIC()){
            System.out.println("Cannot transfer to a third party account");
            printTransferPage(account);
        }
        for (Account acc : availableAccounts) {
            if (acc.getAccountNumber() == accno) {
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                if (account.transfer(getAccount(accno), amount)) {

                    System.out.println("Transfer successful");
                } else {
                    printTransactionFailPage(account);
                }
            }
        }
    }
    /*
     * Prints the third party transfer page for the customer
     */
    public static void printThirdPartyTransferPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno2 = scanner.nextInt();
        System.out.println("Enter amount to transfer: ");
        double amount2 = scanner.nextDouble();
        Account receivingAcc = getAccount(accno2);
        if (receivingAcc == null) {
            printUserNotFoundPage(account);
        }
        if (account.transfer(receivingAcc, amount2)) {

            System.out.println("Transfer successful");
        } else {

            printTransactionFailPage(account);
        }
    }
    /*
     * Prints the user not found page for the customer
     */
    public static void printUserNotFoundPage(Account account) {
        System.out.println("User not found");
        printMoreActions(account);
    }
    /*
     * Prints the transaction fail page for the customer
     */
    public static void printTransactionFailPage(Account account) {
        System.out.println(
                "Transaction failed, transfer limit may have been reached or insufficient funds, please try again.");
        printMoreActions(account);
    }
    /*
     * Prints the account details page for the customer
     */
    public static void printAccountDetailsPage(Account account){
        printAccountDetails(account);
        printMoreActions(account);
    }
    public static void printAccountDetails(Account account){
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Name: " + account.getCustomer().getName());
        System.out.println("NRIC: " + account.getCustomer().getNRIC());
        System.out.println("Email: " + account.getCustomer().getEmail());
        System.out.println("Contact Number: " + account.getCustomer().getContactNo());
        System.out.println("Account Balance: ");
        System.out.println("SGD: " + account.getBalance(Currency.SGD));
        System.out.println("USD: " + account.getBalance(Currency.USD));
        System.out.println("EUR: " + account.getBalance(Currency.EUR));
        System.out.println("JPY: " + account.getBalance(Currency.JPY));
        System.out.println("MYR: " + account.getBalance(Currency.MYR));
    }
    /*
     * Prints the account settings page for the customer
     */
    public static void printAccountSettingsPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select actions:");
        System.out.println("1 | Change PIN \t\t\t| " + account.getPIN());
        System.out.println("2 | Change Withdraw Limit \t| " + account.getWithdrawLimit());
        System.out.println("3 | Change Transfer Limit \t| " + account.getTransferLimit());
        System.out.println("4 | Change Email \t\t| " + account.getCustomer().getEmail());
        System.out.println("5 | Change Contact Number \t| " + account.getCustomer().getContactNo());
        System.out.println("6 | Close Account");
        System.out.println("0 | Back to main menu");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                printChangePINPage(account);
                break;
            case 2:
                printChangeWithdrawLimitPage(account);
                break;
            case 3:
                printChangeTransferLimitPage(account);
                break;
            case 4:
                printChangeEmailPage(account);
                break;
            case 5:
                printChangeContactNumberPage(account);
                break;
            case 6:
                printCloseAccountPage(account);
            case 0:
                printCustomerMenu(account);
                break;
            default:
                System.out.println("Invalid input");
                printAccountSettingsPage(account);
                break;
        }
    }
    /*
     * Prints the change PIN page for the customer
     */
    public static void printChangePINPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new PIN: ");
        int newPIN = scanner.nextInt();
        account.setPIN(newPIN);
        System.out.println("PIN changed successfully");
        printMoreActions(account);
    }
    /*
     * Prints the change Withdraw Limit page for the customer
     */
    public static void printChangeWithdrawLimitPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Withdraw Limit: ");
        double newWithdrawLimit = scanner.nextDouble();
        account.setWithdrawLimit(newWithdrawLimit);
        System.out.println("Withdraw Limit changed successfully");
        printMoreActions(account);
    }
    /*
     * Prints the change Transfer Limit page for the customer
     */
    public static void printChangeTransferLimitPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Transfer Limit: ");
        double newTransferLimit = scanner.nextDouble();
        account.setTransferLimit(newTransferLimit);
        System.out.println("Transfer Limit changed successfully");
        printMoreActions(account);
    }
    /*
     * Prints the change Email page for the customer
     */
    public static void printChangeEmailPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Email: ");
        String newEmail = scanner.nextLine();
        account.getCustomer().setEmail(newEmail);
        System.out.println("Email changed successfully");
        printMoreActions(account);
    }
    /*
     * Prints the change Contact Number page for the customer
     */
    public static void printChangeContactNumberPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Contact Number: ");
        int newContactNumber = scanner.nextInt();
        account.getCustomer().setContactNo(newContactNumber);
        System.out.println("Contact Number changed successfully");
        printMoreActions(account);
    }
    /*
     * Prints the close account page for the customer
     */
    public static void printCloseAccountPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to close your account? (Y/N)");
        String input = scanner.nextLine();
        switch(input){
            case "Y":
                closeAccountVerification(account);
                break;
            case "N":
                printAccountSettingsPage(account);
                break;
            default:
                System.out.println("Invalid input");
                printCloseAccountPage(account);
                break;
        }
    }
    public static boolean checkNRIC(String nric){
        for (Account account : accounts) {
            if (account.getCustomer().getNRIC().substring(5).equals(nric)) {
                return true;
            }
        }
        return false;
    }
    public static void closeAccountVerification(Account account){
        System.out.println("Please enter your last four digits of your NRIC to verify your identity: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (checkNRIC(input)){
            accounts.remove(account);
            System.out.println("Account closed successfully");
            printStartPage();
        } else {
            System.out.println("Verification failed");
            printCloseAccountPage(account);
        }
    }
    /*
     * Checks if customer still wants to do more actions
     */
    public static void printMoreActions(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input = scanner.nextLine();

        switch(input){
            case "Y":
                printCustomerMenu(account);
                break;
            case "N":
                printLogoutPage();
                break;
            default:
                System.out.println("Invalid input");
                printMoreActions(account);
                break;
        }
    }
    /*
     * Prints the logout page for the customer
     */
    public static void printLogoutPage() {
        System.out.println("Thank you for using our service");
        System.out.println("You have been logged out");
        printStartPage();
    }

    public static void main(String[] args) {
        accounts = new ArrayList<>();
        setBankname("DBS");
        Customer customer = new Customer("S1234567A", "John Doe", new Date(2000, 11, 1), 12345678,"email");
        Customer customer2 = new Customer("S1234567B", "Jane Doe", new Date(2000, 11, 1), 12345678,"email");
        Account account = new Account(customer, 1, 1234, 1000.0, 0.0, 0.0, 0.0, 0.0);
        Account account2 = new Account(customer, 2, 1234);
        Account account3 = new Account(customer2, 3, 1234);
        Bank.addAccount(account);
        Bank.addAccount(account2);
        Bank.addAccount(account3);
        printStartPage();
    }
 }