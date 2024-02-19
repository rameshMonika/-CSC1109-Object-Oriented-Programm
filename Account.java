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
    private double balance;
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private double debt;
    public Account(Customer customer, int accountNumber, int PIN){
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.debt = 0;
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
    public void setBalance(double balance){
        this.balance = balance;
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
        balance += amount;
    }
    /**
     * Withdraws the specified amount from the account.
     * 
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount){
        if(amount > withdrawLimit){
            System.out.println("Withdraw limit exceeded");
        }
        else if(amount > balance){
            System.out.println("Insufficient funds");
        }
        else{
            balance -= amount;
        }
    }
    /**
     * Transfers the specified amount from the account to another account.
     * 
     * @param account The account to transfer to.
     */
    public void interAccountTransfer(Account account, double amount){
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
    }
    /**
     * Transfers the specified amount from the account to a third party account.
     * 
     * @param account The account to transfer to.
     */
    public void thirdPartyTransfer(Account account, double amount){
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
    }
}