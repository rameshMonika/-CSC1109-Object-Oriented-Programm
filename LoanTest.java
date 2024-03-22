import java.util.Date;
import java.util.ArrayList;


public class LoanTest {
    public static void main(String[] args) {
        // Create a Customer

        Customer customer = new Customer("S1234567A", "John Doe", new Date(), 12345678, "john.doe@example.com", 30, 50000.0);

        // Create an Account for the Customer
        Account account = new Account(customer, 123456, 1234);

       BusinessLoan businessLoan = new BusinessLoan("Technology", "Software Development", 75000.0, 0.05f, 12, "Business Loan", "Jake",2,220000.00,93887001, account, "EN123456", 200000.0, 100000, 50000, "Pending");




        // Set the Business Loan for the Account
        account.setLoan(businessLoan);

        // Create a Bank
        Bank.setBankname("Example Bank");

        // Add the Account to the Bank
        Bank.addAccount(account);

        ArrayList<Loan> pendingLoans = Bank.getPendingLoans();


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
        // System.out.println("Is eligible for loan: " + businessLoan.isEligibleForLoan());
        System.out.println("Cash Flow: " + businessLoan.calcCashFlow());


        System.out.println("======================")
;
        System.out.println("Pending Loans:");
        for (Loan loan : pendingLoans) {
            System.out.println("Loan ID: " + loan.getLoanID());
            System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Loan Status: " + loan.getLoanStatus());
            System.out.println("Principal: " + loan.getprincipal());
            System.out.println("Interest Rate: " + loan.getInterestRate());
            System.out.println("Duration: " + loan.getDuration());
            System.out.println("Guarantor Name: " + loan.getGuarantorName());
            System.out.println("Guarantor ID: " + loan.getGuarantorID());
            System.out.println("Guarantor Income: " + loan.getGuarantorIncome());
            System.out.println("Guarantor Contact Number: " + loan.getGuarantorContactNo());
            System.out.println();
        }

        
    }
}
