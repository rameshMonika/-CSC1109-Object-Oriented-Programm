 import java.util.ArrayList;
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
     private ArrayList<Account> accounts;
     private String bankName;
     private Exchange exchange;
    
    /**
      * Default constructor that initializes the Bank object with a bank name and an empty list of accounts.
      * 
      * @param bankName The name of the bank.
      */
     public Bank(String bankname) {
         this.bankName = bankName;
         this.accounts = new ArrayList<>();
         this.exchange = new Exchange();
     }
 
     /**
      * Gets the name of the bank.
      * 
      * @return The name of the bank.
      */
     public String getBankname() {
         return this.bankName;
     }
 
     /**
      * Adds an account to the bank.
      * 
      * @param account The account to add.
      */
     public void addAccount(Account account) {
         this.accounts.add(account);
     }
 
     /**
      * Remove an account from the bank.
      * 
      * @param account The account to remove.
      */
     public void removeAccount(Account account) {
         this.accounts.remove(account);
     }
 
     /**
      * Gets account number from the account class and print the all accountNumbers
      */
     public void getAccountNumbers() {
         for (Account account : accounts) {
             System.out.println(account.getAccountNumber());
         }
     }
 
     /**
      * Validates the PIN with the following accountNumber's pin and print the outcome.
      * 
      * @param accountNumber The following accountNumber to validate pin.
      * @param PIN           The pin to validate.
      */
     public void validatePIN(int accountNumber, int PIN) {
         for (Account account : accounts) {
             if (account.getAccountNumber() == accountNumber) {
                 if (account.getPIN() == PIN) {
                     System.out.println("PIN is correct");
                 } else {
                     System.out.println("PIN is incorrect");
                 }
             }
         }
     }
 
     /**
      * Gets the account balance and prints the following accountNumber's balance.
      * 
      * @param accountNumber The following accountNumber to find the balance.
      * @param currency      The currency of the balance to show
      */
     public void getAccountBalance(int accountNumber, String currency) {
         for (Account account : accounts) {
             if (account.getAccountNumber() == accountNumber) {
                 System.out.println("Account " + currency + " balance for account " + accountNumber + " is " + account.getBalance(currency));
             }
         }
     }
    /**
      * Converts the given amount from the given currency to SGD.
      * 
      * @param accountNumber The account number to convert the currency.
      * @param currency      The currency of the amount to be converted.
      * @param amount        The amount to be converted.
      */
     public void convertCurrency(int accountNumber, String currency, double amount){
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
     }
 }