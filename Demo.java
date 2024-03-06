import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Demo class contains the main method to test the functionality of the
 * banking system.
 */
public class Demo {
    /**
     * The main method to test the functionality of the banking system.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        // naming the bank
        Bank bank = new Bank("DBS");

        // creating the customers
        Customer customer = new Customer("S1234567A", "John Doe", new Date(2000, 11, 1), 12345678, "email", 30, 4000);
        Customer customer2 = new Customer("S1234567B", "Jane Doe", new Date(2000, 11, 1), 12345678, "email", 25, 5200);

        // creating the accounts
        Account account = new Account(customer, 1, 1234);
        Account account2 = new Account(customer, 2, 1234);
        Account account3 = new Account(customer2, 3, 1234);

        // adding the accounts
        bank.addAccount(account);
        bank.addAccount(account2);
        bank.addAccount(account3);

        // getting the account numbers
        System.out.println("////Display account numbers test////");
        bank.getAccountNumbers();
        account.deposit(1000);

        // transfer test
        System.out.println("////Inter and third party transfer test////");
        account.interAccountTransfer(account2, 500);
        bank.getAccountBalance(1, Currency.SGD);
        bank.getAccountBalance(2, Currency.SGD);
        account2.thirdPartyTransfer(account3, 100);
        bank.getAccountBalance(3, Currency.SGD);

        // validating PIN
        System.out.println("////Validate PIN test////");
        bank.validatePIN(1, 1234);
        bank.validatePIN(2, 1213);

        // transfer and withdrawal limits
        System.out.println("////transfer and withdraw limit test////");
        account.setTransferLimit(100);
        account.setWithdrawLimit(100);
        account.interAccountTransfer(account2, 200);
        account.withdraw(200);
        bank.getAccountBalance(1, Currency.SGD);

        // convert currency
        account.setBalance(Currency.USD, 100);
        bank.convertCurrency(1, Currency.USD, 100);
        bank.getAccountBalance(1, Currency.SGD);
        bank.getAccountBalance(1, Currency.USD);

        // Loan
        Guarantor g1 = new Guarantor("John Doe", 123456789.0, 50000.0, 123456789);
        // Create a PersonalLoan object
        PersonalLoan p1 = new PersonalLoan(10000.0, 3.4f, 12, "Personal", g1, account, 3000.0, 35000.0);
        StudyLoan s1 = new StudyLoan(10000.0, 3.4f, 12, "Study", g1, account, "S1234567A", "NUS");
        bank.applyLoan(p1, 1);
        bank.applyLoan(s1, 1);
        bank.getLoan(1);
        
       /*  Loan currentLoan = bank.getLoan(1);
        
        if (currentLoan == null)
            System.out.println("there is no loan");
        else
            System.out.println("Loan type: " + currentLoan.getLoanType() + " Amount due: " + currentLoan.getBalance());
 */
        // creditcard
        account.setCC(CreditCardType.REGULAR);
        account2.setCC(CreditCardType.STUDENT);
        CreditCard cc = account.getCC();
        System.out.println("account cc number " + cc.getCardNo() + " account cc type: " + cc.getCardType());
        cc = account2.getCC();
        System.out.println("account cc number " + cc.getCardNo() + " account cc type: " + cc.getCardType());
        Transaction firstTransaction = new Transaction(10000.00);
        Transaction secondTransaction = new Transaction(15000.00);
        cc.makeTransaction(firstTransaction);
        cc.makeTransaction(secondTransaction);
        Map<String, Transaction> transactionHashmap = new HashMap<>();
        transactionHashmap.put("1", firstTransaction);
        transactionHashmap.put("2", secondTransaction);
        MonthlyStatement monthlyStatement = new MonthlyStatement(transactionHashmap);
        System.out.println("Monthly statement for " + account2.getAccountNumber() + "\nStatement ID:"
                + monthlyStatement.getStatementId() + "\nTotal Amount:" + monthlyStatement.getTotalAmount());

        System.out.println("Make payment? " + cc.makePayment(monthlyStatement, account2));
        account2.addBalance(Currency.SGD, 200000);
        System.out.println("Make payment? " + cc.makePayment(monthlyStatement, account2));

        // remove account
        System.out.println("////Remove account test////");
        bank.removeAccount(account);
        bank.getAccountNumbers();

    }
}