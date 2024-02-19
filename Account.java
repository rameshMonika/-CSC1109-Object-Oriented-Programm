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
    public int getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
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
        balance += amount;
    }
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