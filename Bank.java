import java.util.ArrayList;
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
 * The Bank class represents a bank that manages a list of accounts, PIN
 * validation, and account balance.
 */
public class Bank {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static String bankname = "ABC Bank";
    private static Exchange exchange = new Exchange();

    // /**
    //  * Default constructor that initializes the Bank object with a bank name and an
    //  * empty list of accounts.
    //  * 
    //  * @param bankname The name of the bank.
    //  */
    // public static Bank(String bankname) {
    //     bankname = bankname;
    //     accounts = new ArrayList<>();
    //     exchange = new Exchange();
    // }

    /**
     * Gets the name of the bank.
     * 
     * @return The name of the bank.
     */
    public static String getBankname() {
        return bankname;
    }

     /**
     * Sets the name of the bank.
     * 
     * @params The name of the bank.
     */
    public static void setBankname(String bankName) {
        bankname = bankName;
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
     * returns a list of account number from the account class
     */
    public static ArrayList<Account> getAccountNumbers() {
        return accounts;
    }

    /**
     * returns a account number from the account class
     * 
     * @params the account number tied to the account
     */
    public static Account getAccount(int accno) {
        for (Account account : accounts){
            if (accno == account.getAccountNumber())
                return account;
        }
        return null;
    }


    /**
     * Validates the PIN with the following accountNumber's pin and print the
     * outcome.
     * 
     * @param accountNumber The following accountNumber to validate pin.
     * @param PIN           The pin to validate.
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
     * Converts the given amount from SGD to given currency.
     * 
     * @param accountNumber The account number to convert the currency.
     * @param currency      The currency of the amount to be converted.
     * @param amount        The amount to be converted.
     */

     public static Double convertCurrency(int accountNumber, String fromCurrency, String toCurrency, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                double balanceInSGD = account.getBalance(Currency.SGD);
                if (balanceInSGD < amount)
                    return -1.0; // Insufficient balance
                else {
                    // Assuming exchange.convertCurrency method exists and returns the converted amount
                    double convertedAmount = exchange.convertCurrency(fromCurrency, toCurrency, amount);
                    account.setBalance(Currency.SGD, balanceInSGD - amount);
                    account.setBalance(Currency.valueOf(toCurrency), account.getBalance(Currency.valueOf(toCurrency)) + convertedAmount);
                    return convertedAmount;
                }
            }
        }
        return -1.0; // Account not found
    }
}