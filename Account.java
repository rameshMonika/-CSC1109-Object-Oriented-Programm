import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    private String output = "records.json";
    private ArrayList<Loan> loans = new ArrayList<>();
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private JSONObject accountJson = new JSONObject();
    private static JSONObject mainJson = new JSONObject();
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
        this.cc = null;
        this.balance.put(Currency.SGD, 0.0);
        this.balance.put(Currency.EUR, 0.0);
        this.balance.put(Currency.JPY, 0.0);
        this.balance.put(Currency.MYR, 0.0);
        this.balance.put(Currency.USD, 0.0);

        JSONObject customerJson = new JSONObject();
        JSONObject loanJson = new JSONObject();
        JSONObject currencyJson = new JSONObject();
        JSONObject creditcardJson = new JSONObject();

        JSONArray customerArray = new JSONArray();
        JSONArray accountArray = new JSONArray();
        JSONArray loanArray = new JSONArray();
        JSONArray currencyArray = new JSONArray();
        JSONArray creditcardArray = new JSONArray();

        addCustomerJson(customerJson, customer);
        customerArray.add(customerJson);
        loanJson.put("", "");
        loanArray.add(loanJson);
        creditcardJson.put("", "");
        creditcardArray.add(creditcardJson);
        addCurrencyJson(currencyJson, balance.get(Currency.SGD), balance.get(Currency.EUR), balance.get(Currency.JPY), balance.get(Currency.MYR), balance.get(Currency.USD));
        currencyArray.add(currencyJson);
        addAccountJson(accountJson, customerArray, loanArray, currencyArray, creditcardArray, PIN, 1000, 1000);
        //add loan, cc 
        accountArray.add(accountJson);
        mainJson.put(accountNumber, accountArray);

        System.out.println(mainJson.toJSONString());
        try {
            FileWriter fw = new FileWriter(output);
            fw.write(mainJson.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Account(Customer customer, int accountNumber, int PIN, Double SGD, Double EUR, Double JPY, Double MYR, Double USD) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        // this.debt = 0;
        this.loan = null;
        this.balance.put(Currency.SGD, SGD);
        this.balance.put(Currency.EUR, EUR);
        this.balance.put(Currency.JPY, JPY);
        this.balance.put(Currency.MYR, MYR);
        this.balance.put(Currency.USD, USD);

        JSONObject customerJson = new JSONObject();
        JSONObject loanJson = new JSONObject();
        JSONObject currencyJson = new JSONObject();
        JSONObject creditcardJson = new JSONObject();

        JSONArray customerArray = new JSONArray();
        JSONArray accountArray = new JSONArray();
        JSONArray loanArray = new JSONArray();
        JSONArray currencyArray = new JSONArray();
        JSONArray creditcardArray = new JSONArray();
        
        addCustomerJson(customerJson, customer);
        customerArray.add(customerJson);
        loanJson.put("", "");
        creditcardJson.put("", "");
        loanArray.add(loanJson);
        addCurrencyJson(currencyJson, SGD, EUR, JPY, MYR, USD);
        currencyArray.add(currencyJson);
        addAccountJson(accountJson, customerArray, loanArray, currencyArray, creditcardArray, PIN, 1000, 1000);
        //add loan, cc 
        accountArray.add(accountJson);
        mainJson.put(accountNumber, accountArray);

        System.out.println(mainJson.toJSONString());
        try {
            FileWriter fw = new FileWriter(output);
            fw.write(mainJson.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a g
     * etter for the balance map.
     * 
     * @return The balance.
     */
    public HashMap<Currency, Double> getBalanceMap() {
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
     * Sets the Account balance.
     * 
     * @param currency the currency to set the balance.
     * @param balance  the amount to set the balance.
     */
    public void setBalance(Currency currency, double balance) {
        this.balance.put(currency, balance);
        saveAccount();
    }

    /**
     * Adds the amount into the balance.
     * 
     * @param currency the currency to add the balance.
     * @param balance  the amount to be added into the balance.
     */
    public void addBalance(Currency currency, double balance) {
        this.balance.put(currency, this.balance.get(currency) + balance);
        saveAccount();
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
        saveAccount();
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
        saveAccount();
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
        saveAccount();
    }

    /**
     * Adds the amount into the balance.
     * 
     * @param amount the value to be added into the balance.
     */
    public void deposit(double amount) {
        double temp = balance.get(Currency.SGD) + amount;
        balance.put(Currency.SGD, temp);
        saveAccount();
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
            saveAccount();
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
        saveAccount();
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

    public void addCustomerJson(JSONObject customerJson, Customer customer) {
        customerJson.put("name", customer.getName());
        customerJson.put("NRIC", customer.getNRIC());
        customerJson.put("DOB", inputDateFormat.format(customer.getDob()));
        customerJson.put("Contact Number", customer.getContactNo());
        customerJson.put("email", customer.getEmail());
        customerJson.put("age", customer.getAge());
        customerJson.put("income", customer.getIncome());
    }

    public void addAccountJson(JSONObject accountJson, JSONArray customerArray, JSONArray loanArray, JSONArray currencyArray, JSONArray creditcardArray, int pin, int withdrawal, int transfer) {
        accountJson.put("Customer", customerArray);
        accountJson.put("Loan", loanArray);
        accountJson.put("Currency", currencyArray);
        accountJson.put("CreditCard",creditcardArray);
        accountJson.put("PIN", PIN);
        accountJson.put("withdrawLimit", 1000);
        accountJson.put("transferLimit", 1000);

    }

    public void addCurrencyJson(JSONObject currencyJson, Double SGD, Double EUR, Double JPY, Double MYR, Double USD) {
        currencyJson.put(Currency.SGD, SGD);
        currencyJson.put(Currency.EUR, EUR);
        currencyJson.put(Currency.JPY, JPY);
        currencyJson.put(Currency.MYR, MYR);
        currencyJson.put(Currency.USD, USD);
    }
    
    public void saveAccount(){
        
        JSONObject customerJson = new JSONObject();
        JSONObject loanJson = new JSONObject();
        JSONObject currencyJson = new JSONObject();
        JSONObject creditcardJson = new JSONObject();

        JSONArray customerArray = new JSONArray();
        JSONArray accountArray = new JSONArray();
        JSONArray loanArray = new JSONArray();
        JSONArray currencyArray = new JSONArray();
        JSONArray creditcardArray = new JSONArray();

        addCustomerJson(customerJson, customer);
        customerArray.add(customerJson);
        //LOAN Details double check
        loanJson.put("","");
        loanArray.add(loanJson);
        //CC Details double check
        creditcardJson.put("", "");
        creditcardArray.add(creditcardJson);
        addCurrencyJson(currencyJson, balance.get(Currency.SGD), balance.get(Currency.EUR), balance.get(Currency.JPY), balance.get(Currency.MYR), balance.get(Currency.USD));
        currencyArray.add(currencyJson);
        addAccountJson(accountJson, customerArray, loanArray, currencyArray, creditcardArray, PIN, 1000, 1000);
        //add loan, cc 
        accountArray.add(accountJson);
        mainJson.put(accountNumber, accountArray);

        System.out.println(mainJson.toJSONString());
        try {
            FileWriter fw = new FileWriter(output);
            fw.write(mainJson.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
   
}