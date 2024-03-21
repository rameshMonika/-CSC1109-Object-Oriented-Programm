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

    public static Double convertCurrency(int accountNumber, Currency currency, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                double temp = account.getBalance(Currency.SGD);
                if (temp < amount)
                    return -1.0;
                else
                    return exchange.convertCurrency(Currency.USD, amount);    
            }
        }
        return -1.0;
    }

    public static void printLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + bankname);
        System.out.println("Select 1 for Customer | Select 2 for Admin: ");
        int input = scanner.nextInt();
        switch(input){
            case 1:
                System.out.println("Enter your account number: ");
                int accno = scanner.nextInt();
                System.out.println("Enter your PIN: ");
                int pin = scanner.nextInt();
                if (validatePIN(accno, pin)){
                    System.out.println("Login successful");
                    printCustomerMenu(getAccount(accno));
                }
                else{
                    System.out.println("Login failed");
                }
        }
    }
    public static void printMoreActions(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y")){
            printCustomerMenu(account);
        }
        else{
            printLogoutPage();
        }
    }
    public static void printLogoutPage(){
        System.out.println("Thank you for using our service");
        System.out.println("You have been logged out");
        printLogin();
    }
    public static void printBalancePage(Account account){
        System.out.println("Current Account Balance");
        System.out.println("SGD: " + account.getBalance(Currency.SGD));
        System.out.println("USD: " + account.getBalance(Currency.USD));
        System.out.println("EUR: " + account.getBalance(Currency.EUR));
        System.out.println("JPY: " + account.getBalance(Currency.JPY));
        System.out.println("MYR: " + account.getBalance(Currency.MYR));
        printMoreActions(account);
    }
    public static void printDepositPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        printMoreActions(account);
    }
    public static void printWithdrawPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
        printMoreActions(account);
    }
    public static void printTransferPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 for Inter Account Transfer | 2 for Third Party Transfer");
        int input = scanner.nextInt();
        switch(input){
            case 1:
                System.out.println("Enter account number to transfer to: ");
                int accno = scanner.nextInt();
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                account.interAccountTransfer(getAccount(accno), amount);
                printMoreActions(account);
            case 2:
                System.out.println("Enter account number to transfer to: ");
                int accno2 = scanner.nextInt();
                System.out.println("Enter amount to transfer: ");
                double amount2 = scanner.nextDouble();
                getAccount(accno2).thirdPartyTransfer(account, amount2);
                printMoreActions(account);
        }
    }
    public static void printConvertCurrencyPage(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to convert: ");
        double amount = scanner.nextDouble();
        account.setBalance(Currency.SGD, account.getBalance(Currency.SGD) - amount);
        System.out.println("1 | USD");
        System.out.println("2 | EUR");
        System.out.println("3 | JPY");
        System.out.println("4 | MYR");
        int input = scanner.nextInt();
        double converted = 0;
        switch(input){
            case 1:
                converted = Bank.convertCurrency(account.getAccountNumber(), Currency.USD, amount);
                account.setBalance(Currency.USD, converted);
                printMoreActions(account);
            case 2:
                converted = Bank.convertCurrency(account.getAccountNumber(), Currency.EUR, amount);
                account.setBalance(Currency.EUR, converted);
                printMoreActions(account);
            case 3:
                converted = Bank.convertCurrency(account.getAccountNumber(), Currency.JPY, amount);
                account.setBalance(Currency.JPY, converted);
                printMoreActions(account);
            case 4:
                converted = Bank.convertCurrency(account.getAccountNumber(), Currency.MYR, amount);
                account.setBalance(Currency.MYR, converted);
                printMoreActions(account);
        }
    }

    public static void printCustomerMenu(Account account){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + account.getCustomer().getName());
        System.out.println("Select actions:");
        System.out.println("1 | Check balance");
        System.out.println("2 | Deposit");
        System.out.println("3 | Withdraw");
        System.out.println("4 | Transfer");
        System.out.println("5 | Convert currency");
        System.out.println("6 | Apply for loan");
        System.out.println("7 | View loan status");
        System.out.println("8 | View transaction history");
        System.out.println("9 | Logout");
        int input = scanner.nextInt();
        switch(input){
            case 1:
                printBalancePage(account);
            case 2:
                printDepositPage(account);
            case 3:
                printWithdrawPage(account);
            case 4:
                printTransferPage(account);
            case 5:
                printConvertCurrencyPage(account);
            case 9:
                printLogoutPage();
            
        }
    }

    public static void main(String[] args) {
        Customer bob = new Customer("S1234567A", "bob", new Date(2000, 11, 1), 12345678, "email", 30, 4000);
        Admin admin = new Admin("admin", "123");
        Account bobAccount = new Account(bob, 1, 1234);
        Account bobAccount2 = new Account(bob, 2, 4321);
        Bank.addAccount(bobAccount);
        Bank.addAccount(bobAccount2);
        Bank.printLogin();

    }


}