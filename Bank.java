import java.util.ArrayList;

public class Bank{
    private ArrayList<Account> accounts;
    private String bankName;
    public Bank(String bankname){
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }
    public String getBankname(){
        return this.bankName;
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }
    public void removeAccount(Account account){
        this.accounts.remove(account);
    }
    public void getAccountNumbers(){
        for(Account account: accounts){
            System.out.println(account.getAccountNumber());
        }
    }
    public void validatePIN(int accountNumber, int PIN){
        for(Account account: accounts){
            if(account.getAccountNumber() == accountNumber){
                if(account.getPIN() == PIN){
                    System.out.println("PIN is correct");
                }
                else{
                    System.out.println("PIN is incorrect");
                }
            }
        }
    }
    public void getAccountBalance(int accountNumber){
        for(Account account: accounts){
            if(account.getAccountNumber() == accountNumber){
                System.out.println("Account balance for account "+accountNumber+" is "+account.getBalance());
            }
        }
    }
}