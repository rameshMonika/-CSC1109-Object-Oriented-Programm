import java.util.*;
/**
 * Author: Darren, Min Xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
<<<<<<< Updated upstream
 * Description: The Account class represents an account
 * that manages an accounts setting and balance
 */

/**
 * The Account class represents an account that manages an accounts setting and balance
 */
=======
 * Description: The Account class represents a basic financial account with
 * deposit,
 * withdrawal, and balance operations, as well as transfer operations to other
 * accounts.
 */
>>>>>>> Stashed changes
public class Account {
    private int accountNumber;
    private HashMap<String, Double> balance = new HashMap<>();
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private double debt;
<<<<<<< Updated upstream
    private String currency;
    //private Loan loan;
    //private HashMap<int, Loan> loans = new HashMap<>();

    /**
     * Default constructor that initializes the Account object with a customer, account number and PIN.
     * 
     * @param customer The customer of the account.
     * @param accountNumber The account number.
     * @param PIN The PIN of the account.
     */
=======

>>>>>>> Stashed changes
    public Account(Customer customer, int accountNumber, int PIN) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.debt = 0;
        //this.loan = loan;
        this.currency = "SGD";
        this.balance.put("SGD", 0.0);
        this.balance.put("USD", 0.0);
        this.balance.put("EUR", 0.0);
        this.balance.put("JPY", 0.0);
        this.balance.put("MYR", 0.0);
    }

    /**
<<<<<<< Updated upstream
     * Gets the Account number.
     * 
     * @return The account's number.
=======
     * Gets the account number of the account.
     *
     * @return The account.
>>>>>>> Stashed changes
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the Customer's name.
     * 
     * @return The customer's name.
     */
<<<<<<< Updated upstream
    public String getCustomerIC(){
        return customer.getNRIC();
    }

    /**
     * Sets the Account number.
     * 
     * @param accountNumber The account number set to the account.
     */
=======
>>>>>>> Stashed changes
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

<<<<<<< Updated upstream
     /**
     * Gets the Account balance.
=======
    /**
     * Gets the current balance of the account.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the current balance of the account.
     * 
     * @param balance The current balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the PIN of the account.
>>>>>>> Stashed changes
     * 
     * @param currency Get the balance based on the currency provided.
     * @return The account's balance.
     */
<<<<<<< Updated upstream
    public double getBalance(String currency) {
        return balance.get(currency);
    }

    /**
     * Sets the Account balance.
     * 
     * @param currency the currency to set the balance.
     * @param balance the amount to set the balance.
     */
    public void setBalance(String currency, double balance) {
        this.balance.put(currency, balance);
    }
    /**
     * Adds the amount into the balance.
     * 
     * @param currency the currency to add the balance.
     * @param balance the amount to be added into the balance.
     */
    public void addBalance(String currency, double balance) {
        this.balance.put(currency, this.balance.get(currency) + balance);
    }

    /**
     * Gets the account's PIN.
     * 
     * @return the account's PIN.
     */
=======
>>>>>>> Stashed changes
    public int getPIN() {
        return PIN;
    }

    /**
     * Sets the Account's PIN.
     * 
     * @param PIN the value to set for Account's PIN.
     */
    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    /**
     * Gets the Account's withdrawal limit.
     * 
     * @return the account's withdrawal limit.
     */
    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    /**
     * Sets the Account's withdrawl limit.
     * 
     * @param withdrawLimit the value to set for Account's withdrawal limits.
     */
    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    /**
     * Gets the Account's transfer limit.
     * 
     * @return the account's transfer limit.
     */
    public double getTransferLimit() {
        return transferLimit;
    }

    /**
     * Sets the Account's transfer limit.
     * 
     * @param transferLimit the value to set the transfer limit of the Account.
     */
    public void setTransferLimit(double transferLimit) {
        this.transferLimit = transferLimit;
<<<<<<< Updated upstream
    }
    
=======
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        balance += amount;
    }

>>>>>>> Stashed changes
    /**
     * Adds the amount into the balance.
     * 
     * @param amount the value to be added into the balance.
     */
<<<<<<< Updated upstream
    public void deposit(double amount) {
        double temp = balance.get("SGD") + amount;
        balance.put("SGD", temp);
    }

    /**
     * Subtract the amount from the balance.
     * 
     * @param amount the value to be deducted from the balance.
     */
    public void withdraw(double amount) {
        double temp = balance.get("SGD");

        if (amount > withdrawLimit) {
            System.out.println("Withdraw limit exceeded");
        } else if (amount > balance.get("SGD")) {
            System.out.println("Insufficient funds");
        } else {
            temp -= amount;
            balance.put("SGD", temp);
=======
    public void withdraw(double amount) {
        if (amount > withdrawLimit) {
            System.out.println("Withdraw limit exceeded");
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
>>>>>>> Stashed changes
        }
    }

    /**
     * transfer of specified values between accounts owned by the same customer.
     * 
     * @param account the account to transfer the amount to.
     * @param amount the value to be transfered into the given account.
     */
<<<<<<< Updated upstream
    public void interAccountTransfer(Account account, double amount){
        if(account.getCustomerIC().equals(customer.getNRIC())){
            double balanceSGD = balance.get("SGD");
            if(amount > transferLimit){
                System.out.println("Transfer limit exceeded");
            }
            else if(amount > balanceSGD){
                System.out.println("Insufficient funds");
            }
            else{
                balanceSGD -= amount;
                this.balance.put("SGD", balanceSGD);
                account.deposit(amount);
            }
        }
        else{
            System.out.println("Invalid account");
=======
    public void interAccountTransfer(Account account, double amount) {
        if (amount > transferLimit) {
            System.out.println("Transfer limit exceeded");
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
            account.deposit(amount);
>>>>>>> Stashed changes
        }
    }

    /**
     * transfer of specified values between accounts owned by the different customer
     * 
     * @param account the account to transfer the amount to.
     * @param amount the value to be transfered into the given account.
     */
<<<<<<< Updated upstream
    public void thirdPartyTransfer(Account account, double amount){
        double balanceSGD = balance.get("SGD");
        if(amount > transferLimit){
            System.out.println("Transfer limit exceeded");
        }
        else if(amount > balanceSGD){
            System.out.println("Insufficient funds");
        }
        else{
            balanceSGD -= amount;
            this.balance.put("SGD", balanceSGD);
=======
    public void thirdPartyTransfer(Account account, double amount) {
        if (amount > transferLimit) {
            System.out.println("Transfer limit exceeded");
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
>>>>>>> Stashed changes
            account.deposit(amount);
        }
    }

<<<<<<< Updated upstream
    /**
     * Gets the currency code.
     * 
     * @return the currency.
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * Set the currency code.
     * 
     * @param currency the value to set to Currency.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

   
=======
    Guarantor g1 = new Guarantor("John Doe", 123456789.0, 50000.0, 123456789);
// Create a PersonalLoan object
    PersonalLoan p1 = new PersonalLoan(10000.0, 3.4f, 12, "Personal", a1,g1, 3000.0, 35000.0);


>>>>>>> Stashed changes
}