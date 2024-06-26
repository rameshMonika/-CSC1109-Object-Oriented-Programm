import java.util.ArrayList;
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
    private Double balance;
    private Customer customer;
    private String PIN;
    private double withdrawLimit;
    private double transferLimit;
    private ArrayList<Insurance> insurance = new ArrayList<>();

    public Account(Customer customer, int accountNumber, String PIN){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.balance = 0.0;
    }

    public Account(Customer customer, int accountNumber, String PIN, double SGD){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.balance = SGD;
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
    public double getBalance(){
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
     * 
     * @return The PIN.
     */
    public String getPIN(){
        return PIN;
    }
    /**
     * Sets the PIN of the account.
     * 
     * @param PIN The PIN.
     */
    public void setPIN(String PIN){
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
        double temp = balance + amount;
        balance = temp;
    }
    /**
     * Withdraws the specified amount from the account.
     * 
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount){
        double temp = balance;

        if (amount > withdrawLimit) {
            System.out.println("Withdraw limit exceeded");
        } else if (amount > temp) {
            System.out.println("Insufficient funds");
        } else {
            temp -= amount;
            balance = temp;
        }
    }

    public void addInsurance(Insurance insurance){
        this.insurance.add(insurance);
    }

    public ArrayList<Insurance> getInsurance(){
        return insurance;
    }
    /**
     * Transfers the specified amount from the account to another account.
     * @param account The account to transfer to.
     * @param amount The amount to transfer.
     * @return True if the transfer is successful, false otherwise.
     */
    public boolean transfer(Account account, double amount) {
        double balanceSGD = balance;
        if (amount < 0){
            System.out.println("Invalid amount");
            return false;
        }
        if (amount > transferLimit) {
            System.out.println("Transfer limit exceeded");
            return false;
        } else if (amount > balanceSGD) {
            System.out.println("Insufficient funds");
            return false;
        } else {
            balanceSGD -= amount;
            this.balance = balanceSGD ;
            account.deposit(amount);
            return true;
        }
    }
}