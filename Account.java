import java.util.*;

public class Account{
    private int accountNumber;
    private HashMap<String, Double> balance = new HashMap<>();
    private Customer customer;
    private int PIN;
    private double withdrawLimit;
    private double transferLimit;
    private double debt;
    private String currency;
    public Account(Customer customer, int accountNumber, int PIN){
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
        this.withdrawLimit = 1000;
        this.transferLimit = 1000;
        this.debt = 0;
        this.currency = "SGD";
        this.balance.put("SGD", 0.0);
        this.balance.put("USD", 0.0);
        this.balance.put("EUR", 0.0);
        this.balance.put("JPY", 0.0);
        this.balance.put("MYR", 0.0);
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    /*public double getBalance(){
        return balance.get("SGD");
    }
    public void setBalance(double balance){
        this.balance = balance;
    }*/
    public int getPIN(){
        return PIN;
    }
    public void setPIN(int PIN){
        this.PIN = PIN;
    } 
    public double getWithdrawLimit(){
        return withdrawLimit;
    }
    public void setWithdrawLimit(double withdrawLimit){
        this.withdrawLimit = withdrawLimit;
    }
    public double getTransferLimit(){
        return transferLimit;
    }
    public void setTransferLimit(double transferLimit){
        this.transferLimit = transferLimit;
    } 
    public void deposit(double amount){
        double temp = balance.get("SGD") + amount;
        balance.put("SGD", temp);
    }
    public void withdraw(double amount){
        double temp = balance.get("SGD");
        
        if(amount > withdrawLimit){
            System.out.println("Withdraw limit exceeded");
        }
        else if(amount > balance.get("SGD")){
            System.out.println("Insufficient funds");
        }
        else{
            temp -= amount;
            balance.put("SGD", temp);
        }
    }
    public void interAccountTransfer(Account account, double amount){
        double balanceSGD = balance.get("SGD");
        if(amount > transferLimit){
            System.out.println("Transfer limit exceeded");
        }
        else if(amount > balanceSGD){
            System.out.println("Insufficient funds");
        }
        else{
            balanceSGD -= amount;
            account.deposit(amount);
        }
    }
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
            account.deposit(amount);
        }
    }
    public String getCurrency(){
        return currency;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
}