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
        System.out.println("////Display account numbers test////");
        bank.getAccountNumbers();
        account.deposit(1000);
        System.out.println("////Inter and third party transfer test////");
        account.interAccountTransfer(account2, 500);
        bank.getAccountBalance(1);
        bank.getAccountBalance(2);
        account2.thirdPartyTransfer(account3, 100);
        bank.getAccountBalance(3);
        System.out.println("////Validate PIN test////");
        bank.validatePIN(1, 1234);
        bank.validatePIN(2, 1213);
        System.out.println("////transfer and withdraw limit test////");
        account.setTransferLimit(100);
        account.setWithdrawLimit(100);
        account.interAccountTransfer(account2, 200);
        account.withdraw(200);
        bank.getAccountBalance(1);
        System.out.println("////Remove account test////");
        bank.removeAccount(account);
        bank.getAccountNumbers();
    }
}