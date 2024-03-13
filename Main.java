import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Customer
        Customer customer = new Customer("S1234567A", "John Doe", new Date(), 12345678, "john.doe@example.com", 30, 50000.0);

        // Create an Account for the Customer
        Account account = new Account(customer, 123456, 1234);

        // Create multiple Business Loans associated with the Account
        BusinessLoan businessLoan1 = new BusinessLoan("Tech", "Software Development", 75000.0f, 0.05f, 12, "Business Loan", null, account, "EN123456", 200000.0,"Pending", 100000, 50000);
        BusinessLoan businessLoan2 = new BusinessLoan("Retail", "Online Store", 50000.0f, 0.04f, 24, "Business Loan", null, account, "EN789012", 150000.0,"Pending", 80000, 70000);

        // Add the Loans to the Account
        account.addLoan(businessLoan1);
        account.addLoan(businessLoan2);

     
        Bank.setBankname("Example Bank");

        // Add the Account to the Bank
        Bank.addAccount(account);

        // Test accessing information
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Account Number: " + account.getAccountNumber());
        for (Loan loan : account.getLoans()) {
            if (loan instanceof BusinessLoan) {
                BusinessLoan businessLoan = (BusinessLoan) loan;
                System.out.println("Loan Principal: " + businessLoan.getprincipal());
                System.out.println("Loan Type: " + businessLoan.getLoanType());
                System.out.println("Business Type: " + businessLoan.getBusinessType());
                System.out.println("Business Description: " + businessLoan.getBusinessDescription());
                System.out.println("Cash In Flow: " + businessLoan.getCashInFlow());
                System.out.println("Cash Out Flow: " + businessLoan.getCashOutFlow());
                System.out.println("Loan Status: " + businessLoan.getLoanStatus());
                System.out.println("Is eligible for loan: " + businessLoan.isEligibleForLoan());
                System.out.println("Cash Flow: " + businessLoan.calcCashFlow());
                System.out.println();

            }
        }

        System.out.println("======================= Admin =================================");

        // Create an instance of Admin
        Admin admin = new Admin("Admin Name", "admin", "admin123");

        // Show pending loans to the admin
        ArrayList<Loan> pendingLoans = Bank.getPendingLoans();
        admin.showPendingLoans(pendingLoans);

        // Review loan details
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the loan ID to review loan details: ");
        int loanID = scanner.nextInt();
        Loan loanToReview = null;
        for (Loan loan : pendingLoans) {
            if (loan.getLoanID() == loanID) {
                loanToReview = loan;
                break;
            }
        }
        if (loanToReview != null) {
            admin.reviewLoanDetails(loanToReview);
            System.out.println("Do you want to approve or reject this loan? (Type 'approve' or 'reject'): ");
            String decision = scanner.next();
            if (decision.equalsIgnoreCase("approve")) {
                admin.approveLoan(loanToReview);
            } else if (decision.equalsIgnoreCase("reject")) {
                admin.rejectLoan(loanToReview);
            } else {
                System.out.println("Invalid decision. Loan remains pending.");
            }
        } else {
            System.out.println("Invalid loan ID. Loan not found.");
        }
        scanner.close();
    }
}
