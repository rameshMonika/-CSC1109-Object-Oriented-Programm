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


     public static void printStartPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + bankName);
        System.out.println("1 | Login");
        System.out.println("0 | Exit");
        int input = scanner.nextInt();
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
    }
     /*
      * Prints the login page for the customer
      */
     public static void printCustomerLoginPage(){
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Enter your account number: ");
        int accno = scanner.nextInt();
        do {
            System.out.println("Enter your PIN: ");
            int pin = scanner.nextInt();
            if (validatePIN(accno, pin)) {

                System.out.println("Login successful");
                printCustomerMenu(getAccount(accno));
                counter = 0;
                break;
                } else {
                     counter++;
                     }
                    if (counter == 3) {
                         printLoginFailPage();
                        break;
                    }
             } while (counter > 0);
    }

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
        System.out.println("0 | Logout");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printBalancePage(account);
                break;
            case 2:
                printTransferPage(account);
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
        Account receiving = getAccount(accno);
        if (receiving == null) {
            System.out.println("Account not found");

            printTransferPage(account);
        }
        for (Account acc : availableAccounts) {
            if (acc.getAccountNumber() == accno) {
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                if (account.interAccountTransfer(getAccount(accno), amount)) {

                    System.out.println("Transfer successful");
                } else {
                    printTransactionFailPage(account);
                }
            }
        }
    }
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
        if (receivingAcc.thirdPartyTransfer(account, amount2)) {

            System.out.println("Transfer successful");
        } else {

            printTransactionFailPage(account);
        }
    }
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
     * Checks if customer still wants to do more actions
     */
    public static void printMoreActions(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input = scanner.nextLine();

        if (input.equals("Y") || input.equals("y")) {
            printCustomerMenu(account);
        } else {
            printLogoutPage();
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