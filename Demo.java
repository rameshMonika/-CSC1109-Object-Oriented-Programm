import java.util.Date;
import java.util.ArrayList;

public class Demo{
    public static void main(String[] args){
        Bank bank = new Bank("DBS");
        Customer customer = new Customer("S1234567A", "John Doe", new Date(2000, 11, 1), 12345678,"email");
        Customer customer2 = new Customer("S1234567B", "Jane Doe", new Date(2000, 11, 1), 12345678,"email");
        Account account = new Account(customer, 1, 1234);
        Account account2 = new Account(customer, 2, 1234);
        Account account3 = new Account(customer2, 3, 1234);
        bank.addAccount(account);
        bank.addAccount(account2);
        bank.addAccount(account3);
        account.deposit(1000);
        bank.getAccountBalance(1,"SGD");
        bank.getAccountBalance(1,"USD");
    }
}