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
    private ArrayList<Loan> loans = new ArrayList<>();
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private Loan loan;
    private CreditCard cc;
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

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the Account number.
     * 
     * @param accountNumber The account number set to the account.
     */
    public int setAccountNumber() {
        Random random = new Random();
        ArrayList<Account> accounts = Bank.getAccountNumbers();
        int newAccountNumber;

        do {
            newAccountNumber = 100000 + random.nextInt(900000);
        } while (isAccountNumberExists(accounts, newAccountNumber));

        return this.accountNumber = newAccountNumber;
    }

    private boolean isAccountNumberExists(ArrayList<Account> accounts, int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
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
     * Retrieves a map containing balances for different currencies.
     *
     * @return A map where the key is the currency and the value is the balance.
     */
    public Map<Currency, Double> getBalances() {
    return balance;
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
    public boolean withdraw(double amount) {
        double temp = balance.get(Currency.SGD);

        if (amount > withdrawLimit) {
            return false;
        } else if (amount > temp) {
            return false;
        } else {
            temp -= amount;
            balance.put(Currency.SGD, temp);
            return true;
        }
    }

    /**
     * transfer of specified values between accounts owned by the same customer.
     * 
     * @param account the account to transfer the amount to.
     * @param amount  the value to be transfered into the given account.
     */
    public boolean interAccountTransfer(Account account, double amount) {
        if (account.getCustomerIC().equals(customer.getNRIC())) {
            double balanceSGD = balance.get(Currency.SGD);
            if (amount > transferLimit) {
                return false;
            } else if (amount > balanceSGD) {
                return false;
            } else {
                balanceSGD -= amount;
                this.balance.put(Currency.SGD, balanceSGD);
                account.deposit(amount);
                return true;
            }
        } else {
            return false;
        }
    }
    /**
     * transfer of specified values between accounts owned by the different customer
     * 
     * @param account the account to transfer the amount to.
     * @param amount  the value to be transfered into the given account.
     */
    public boolean thirdPartyTransfer(Account account, double amount) {
        double balanceSGD = balance.get(Currency.SGD);
        if (amount > transferLimit) {
            return false;
        } else if (amount > balanceSGD) {
            return false;
        } else {
            balanceSGD -= amount;
            this.balance.put(Currency.SGD, balanceSGD);
            account.deposit(amount);
            return true;
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

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
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

}