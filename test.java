import java.util.Date;

public class test {
    public static void main(String[] args) {
        Customer customer = new Customer("S1234567A", "John Doe", new Date(2000, 11, 1), 12345678, "email", 30, 4000);
        Customer customer2 = new Customer("S1234567B", "Jane Doe", new Date(2000, 11, 1), 12345678, "email", 25, 5200);
        Customer customer3 = new Customer("S1234567C", "bob", new Date(2000, 11, 1), 12345678, "email", 30, 4000);
        Account account = new Account(customer, 1, 1234);
        Account account2 = new Account(customer, 2, 1234);
        Account account3 = new Account(customer2, 3, 1234);
        Bank.addAccount(account);
        Bank.addAccount(account2);
        Bank.addAccount(account3);
        System.out.println("////Display account numbers test////");
        System.out.println(Bank.getAccountNumbers());
        System.out.println("-----------------------------------------");

        System.out.println("////Inter and third party transfer test////");
        System.out.println("Depositing 1000 into account1 and transfering 500 to account2");
        account.deposit(1000);
        account.interAccountTransfer(account2, 500);
        System.out.println("Account 1 balance: " + Bank.getAccountBalance(1, Currency.SGD));
        System.out.println("Account 2 balance: " + Bank.getAccountBalance(2, Currency.SGD));
        System.out.println("Transfering 100 from account2 to account3");
        account2.thirdPartyTransfer(account3, 100);
        System.out.println("Account 2 balance: " + Bank.getAccountBalance(2, Currency.SGD));
        // Third party transfer no longer working wtf
        System.out.println("Account 3 balance: " + Bank.getAccountBalance(3, Currency.SGD));
        System.out.println("-----------------------------------------");

        System.out.println("////Validate PIN test////");
        System.out.println("Validating PIN for account 1 (Correct PIN)");
        System.out.println(Bank.validatePIN(1, 1234));
        System.out.println("Validating PIN for account 2(Wrong PIN)");
        System.out.println(Bank.validatePIN(2, 1213));
        System.out.println("-----------------------------------------");

        System.out.println("////transfer and withdraw limit test////");
        System.out.println("Setting transfer and withdraw limit for account 1 to 100");
        account.setTransferLimit(100);
        account.setWithdrawLimit(100);
        System.out.println("Transfering 200 from account1 to account2");
        account.interAccountTransfer(account2, 200);
        System.out.println("Withdrawing 200 from account1");
        account.withdraw(200);
        System.out.println("-----------------------------------------");

        System.out.println("////Convert currency test////");
        System.out.println("Depositing 1000 into account1 and converting to USD");
        account.deposit(1000);
        System.out.println("Account 1 balance in USD: " + Bank.convertCurrency(1, Currency.USD, 1000));
        System.out.println("-----------------------------------------");

        System.out.println("////Loan test////");
        System.out.println("Creating a loan for account 1");
        
    }
}