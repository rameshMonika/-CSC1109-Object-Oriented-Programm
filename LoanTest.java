import java.util.Date;

public class LoanTest {
    public static void main(String[] args) {
        // Create a Customer
        Customer customer = new Customer("S1234567A", "John Doe", new Date(), 12345678, "john.doe@example.com", 30, 50000.0);

        // Create an Account for the Customer
        Account account = new Account(customer, 123456, 1234);

        // Create a Business Loan associated with the Account
        BusinessLoan businessLoan = new BusinessLoan("Tech", "Software Development", 75000.0f, 0.05f, 12, "Business Loan", null, account, "EN123456", 200000.0,"Pending", 100000, 50000);

        // Set the Business Loan for the Account
        account.setLoan(businessLoan);

        // Create a Bank
        
        Bank.setBankname("Example Bank");

        // Add the Account to the Bank
        Bank.addAccount(account);

        // Test accessing information
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Loan Principal: " + businessLoan.getprincipal());
        System.out.println("Loan Type: " + businessLoan.getLoanType());
        System.out.println("Business Type: " + businessLoan.getBusinessType());
        System.out.println("Business Description: " + businessLoan.getBusinessDescription());
        System.out.println("Cash In Flow: " + businessLoan.getCashInFlow());
        System.out.println("Cash Out Flow: " + businessLoan.getCashOutFlow());
        System.out.println("Loan Status: " + businessLoan.getLoanStatus());
        System.out.println("Is eligible for loan: " + businessLoan.isEligibleForLoan());
        System.out.println("Cash Flow: " + businessLoan.calcCashFlow());
    }
}
