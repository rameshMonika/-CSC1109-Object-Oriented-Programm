import java.util.*;

/**
 * Author: Darren, Min Xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
 * Description: The Account class represents a basic financial account with
 * deposit,
 * withdrawal, and balance operations, as well as transfer operations to other
 * accounts.
 */
public class Account {
    private int accountNumber;
    private HashMap<Currency, Double> balance = new HashMap<>();
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private Loan loan;
    private CreditCard cc;
    private ArrayList<Loan> loans = new ArrayList<>();
    // private double debt;
    // private HashMap<int, Loan> loans = new HashMap<>();

    /**
     * Default constructor that initializes the Account object with a customer,
     * account number and PIN.
     * 
     * @param customer      The customer of the account.
     * @param accountNumber The account number.
     * @param PIN           The PIN of the account.
     */
    public Account(Customer customer, int accountNumber, int PIN) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        // this.debt = 0;
        this.loan = null;
        this.balance.put(Currency.SGD, 0.0);
        this.balance.put(Currency.EUR, 0.0);
        this.balance.put(Currency.JPY, 0.0);
        this.balance.put(Currency.MYR, 0.0);
        this.balance.put(Currency.USD, 0.0);
    }

    /**
     * Creates a getter for the balance map.
     * 
     * @return The balance.
     */
    public HashMap<Currency, Double> getBalanceMap(){
        return balance;
    }
    /**
     * Gets the Account number.
     * 
     * @return The account's number.
     *         Gets the account number of the account.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the Customer's name.
     * 
     * @return The customer's name.
     */
    public String getCustomerIC() {
        return customer.getNRIC();
    }

    /**
     * Sets the Account number.
     * 
     * @param accountNumber The account number set to the account.
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the Account balance.
     * 
     * @param currency Get the balance based on the currency provided.
     * @return The account's balance.
     */
    public double getBalance(Currency currency) {
        return balance.get(currency);
    }
    /**
     * Sets the Account balance.
     * 
     * @param currency the currency to set the balance.
     * @param balance  the amount to set the balance.
     */
    public void setBalance(Currency currency, double balance) {
        this.balance.put(currency, balance);
    }

    /**
     * Adds the amount into the balance.
     * 
     * @param currency the currency to add the balance.
     * @param balance  the amount to be added into the balance.
     */
    public void addBalance(Currency currency, double balance) {
        this.balance.put(currency, this.balance.get(currency) + balance);
    }

    /**
     * Gets the account's PIN.
     * 
     * @return the account's PIN.
     */
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
    }

    /**
     * Adds the amount into the balance.
     * 
     * @param amount the value to be added into the balance.
     */
    public void deposit(double amount) {
        double temp = balance.get(Currency.SGD) + amount;
        balance.put(Currency.SGD, temp);
    }

    /**
     * Subtract the amount from the balance.
     * 
     * @param amount the value to be deducted from the balance.
     */
    public void withdraw(double amount) {
        double temp = balance.get(Currency.SGD);

        if (amount > withdrawLimit) {
            System.out.println("Withdraw limit exceeded");
        } else if (amount > temp) {
            System.out.println("Insufficient funds");
        } else {
            temp -= amount;
            balance.put(Currency.SGD, temp);
        }
    }

    /**
     * transfer of specified values between accounts owned by the same customer.
     * 
     * @param account the account to transfer the amount to.
     * @param amount  the value to be transfered into the given account.
     */
    public void interAccountTransfer(Account account, double amount) {
        if (account.getCustomerIC().equals(customer.getNRIC())) {
            double balanceSGD = balance.get(Currency.SGD);
            if (amount > transferLimit) {
                System.out.println("Transfer limit exceeded");
            } else if (amount > balanceSGD) {
                System.out.println("Insufficient funds");
            } else {
                balanceSGD -= amount;
                this.balance.put(Currency.SGD, balanceSGD);
                account.deposit(amount);
            }
        } else {
            System.out.println("Invalid account");
        }
    }

    /**
     * transfer of specified values between accounts owned by the different customer
     * 
     * @param account the account to transfer the amount to.
     * @param amount  the value to be transfered into the given account.
     */
    public void thirdPartyTransfer(Account account, double amount) {
        double balanceSGD = balance.get(Currency.SGD);
        if (amount > transferLimit) {
            System.out.println("Transfer limit exceeded");
        } else if (amount > balanceSGD) {
            System.out.println("Insufficient funds");
        } else {
            balanceSGD -= amount;
            this.balance.put(Currency.SGD, balanceSGD);
            account.deposit(amount);
        }
    }

    /**
     * Sets the Loan.
     * 
     * @param loan the loan details.
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Gets the Loan.
     * 
     * @return the loan details.
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Sets the Credit Card.
     * 
     * @param cardType the type of credit card.
     */
    public void setCC(CreditCardType cardType) {
        cc = new CreditCard(accountNumber, customer, cardType);
    }

    /**
     * Gets the Credit Card
     * 
     * @return The Credit Card information.
     */
    public CreditCard getCC() {
        return cc;
    }

    
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

}