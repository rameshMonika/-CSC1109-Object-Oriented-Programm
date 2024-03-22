public class Demo {
    public static void main(String[] args) {
        // Create a Bank instance
        Bank bank = new Bank("MyBank");

        // Create accounts and add them to the bank
        Customer customer1 = new Customer("John", "123 Main St", null, 0, null, 0);
        Customer customer2 = new Customer("Jane", "456 Elm St", null, 0, null, 0);

        Account account1 = new Account(customer1, 1001, 1234);
        Account account2 = new Account(customer2, 1002, 5678);
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Deposit and withdraw from accounts
        account1.deposit(1000);
        account2.deposit(2000);
        account1.withdraw(500);
        account2.withdraw(1000);

        // Print account balances
        System.out.println("Account balances after transactions:");
        bank.getAccountBalance(account1.getAccountNumber(), "USD");
        bank.getAccountBalance(account2.getAccountNumber(), "USD");

        // Convert currency using ForeignExchange
        bank.convertCurrency(account1.getAccountNumber(), "USD", 500); // Convert 500 USD to SGD

        // Print top frequently used currencies
        System.out.println("Top frequently used currencies:");

        // Access ForeignExchange through Bank instance and print top frequently used
        bank.getForeignExchange().printTopFrequentlyUsedCurrencies(2);
    }
}
