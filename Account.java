import java.util.HashMap;

/**
 * Author: Darren, Min xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
 * Description: The Account class represents a basic financial account with deposit,
 * withdrawal, and balance operations, as well as transfer operations to other accounts.
 */
public class Account{
    private int accountNumber;
    private HashMap<Currency, Double> balance = new HashMap<>();
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private double debt;

    public Account(Customer customer, int accountNumber, int PIN){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.debt = 0;
        this.balance.put(Currency.SGD, 0.0);
        this.balance.put(Currency.EUR, 0.0);
        this.balance.put(Currency.JPY, 0.0);
        this.balance.put(Currency.MYR, 0.0);
        this.balance.put(Currency.USD, 0.0);
    }

    public Account(Customer customer, int accountNumber, int PIN, double SGD, double EUR, double JPY, double MYR, double USD){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.debt = 0;
        this.balance.put(Currency.SGD, SGD);
        this.balance.put(Currency.EUR, EUR);
        this.balance.put(Currency.JPY, JPY);
        this.balance.put(Currency.MYR, MYR);
        this.balance.put(Currency.USD, USD);
    }
    /**
    * Gets the account number of the account.
    *
    * @return The account.
    */
    public int getAccountNumber(){
        return accountNumber;
    }
    /**
     * Sets the account number of the account.
     * 
     * @param accountNumber The account number.
     */
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    public Customer getCustomer(){
        return customer;
    }

    public String getCustomerIC(){
        return customer.getNRIC();
    }

    /**
    * Gets the current balance of the account.
    *
    * @return The current balance.
    */
    public double getBalance(Currency currency){
        return balance.get(currency);
    }
    /**
     * Sets the current balance of the account.
     *  
     * @param balance The current balance.
     */
    public void setBalance(Currency currency, double balance) {
        this.balance.put(currency, balance);
    }
    /**
     * Gets the PIN of the account.
     * 
     * @return The PIN.
     */
    public int getPIN(){
        return PIN;
    }
    /**
     * Sets the PIN of the account.
     * 
     * @param PIN The PIN.
     */
    public void setPIN(int PIN){
        this.PIN = PIN;
    } 
    /**
     * Gets the withdraw limit of the account.
     * 
     * @return The withdraw limit.
     */
    public double getWithdrawLimit(){
        return withdrawLimit;
    }
    /**
     * Sets the withdraw limit of the account.
     * 
     * @param withdrawLimit The withdraw limit.
     */
    public void setWithdrawLimit(double withdrawLimit){
        this.withdrawLimit = withdrawLimit;
    }
    /**
     * Gets the transfer limit of the account.
     * 
     * @return The transfer limit.
     */
    public double getTransferLimit(){
        return transferLimit;
    }
    /**
     * Sets the transfer limit of the account.
     * 
     * @param transferLimit The transfer limit.
     */
    public void setTransferLimit(double transferLimit){
        this.transferLimit = transferLimit;
    } 
    /**
    * Deposits the specified amount into the account.
    *
    * @param amount The amount to deposit.
    */
    public void deposit(double amount){
        double temp = balance.get(Currency.SGD) + amount;
        balance.put(Currency.SGD, temp);
    }
    /**
     * Withdraws the specified amount from the account.
     * 
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount){
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
     * Transfers the specified amount from the account to another account.
     * 
     * @param account The account to transfer to.
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
/*     public void interAccountTransfer(Account account, double amount){
        if(amount > transferLimit){
            System.out.println("Transfer limit exceeded");
        }
        else if(amount > balance){
            System.out.println("Insufficient funds");
        }
        else{
            balance -= amount;
            account.deposit(amount);
        }
    } */
    /**
     * Transfers the specified amount from the account to a third party account.
     * 
     * @param account The account to transfer to.
     */
    public boolean thirdPartyTransfer(Account account, double amount) {
        double balanceSGD = balance.get(Currency.SGD);
        if (amount > transferLimit) {
            System.out.println("Transfer limit exceeded");
            return false;
        } else if (amount > balanceSGD) {
            System.out.println("Insufficient funds");
            return false;
        } else {
            balanceSGD -= amount;
            this.balance.put(Currency.SGD, balanceSGD);
            account.deposit(amount);
            return true;
        }
    }
}