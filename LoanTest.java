import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;


public class LoanTest {
    public static void main(String[] args) {
        // Create a Customer

        Customer customer = new Customer("S1234567A", "John Doe", new Date(), 12345678, "john.doe@example.com", 30, 50000.0);

        // Create an Account for the Customer
        Account account = new Account(customer, 123456, 1234);

       BusinessLoan businessLoan = new BusinessLoan("Technology", "Software Development", 75000.0, 0.05f, 12, "Business Loan", "Jake",2,220000.00,93887001, account, "EN123456", 200000.0, 100000, 50000, "Pending");

        BusinessLoan businessLoan2 = new BusinessLoan("Food", "Fast", 75000.0, 0.05f, 12, "Business Loan", "Jake",2,220000.00,93887001, account, "EN123456", 200000.0, 100000, 50000, "Pending");

        PersonalLoan personalLoan = new PersonalLoan(5000.0, 0.1f, 24, "Personal Loan", "Jane", 3, 30000.0, 98765432, account, "Pending", 3000.0, 36000.0);

         // Create a Study Loan
        StudyLoan studyLoan = new StudyLoan(10000.0, 0.05f, 36, "Study Loan", "Jane", 3, 30000.0, 98765432, account, "STU123456", "University ABC", "Pending");





        // Set the Business Loan for the Account
        //account.setLoan(businessLoan);
        account.addLoan(businessLoan);
        account.addLoan(businessLoan2);
        account.addLoan(personalLoan);
        account.addLoan(studyLoan);

        

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
        // System.out.println("Is eligible for loan: " + businessLoan.isEligibleForLoan());
        System.out.println("Cash Flow: " + businessLoan.calcCashFlow());

        System.out.println("=======================================================================================");
         ArrayList<Loan> pendingLoans = Bank.getPendingLoans();
        System.out.println("\nPersonal Loans:");
        ArrayList<Loan> pendingPersonalLoans = Bank.getPendingLoansByType("Personal Loan");
        for (Loan loan : pendingPersonalLoans) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Loan ID: " + loan.getLoanID());
            System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Loan Status: " + loan.getLoanStatus());
            
            System.out.println("----------------------------------------------------------------------------");

        }

        // Filter and print pending business loans
        System.out.println("\nBusiness Loans:");
        ArrayList<Loan> pendingBusinessLoans = Bank.getPendingLoansByType("Business Loan");
        for (Loan loan : pendingBusinessLoans) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Loan ID: " + loan.getLoanID());
            System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Loan Status: " + loan.getLoanStatus());
            System.out.println("----------------------------------------------------------------------------");

        }

        // Filter and print pending study loans
        System.out.println("\nStudy Loans:");
        ArrayList<Loan> pendingStudyLoans = Bank.getPendingLoansByType("Study Loan");
        for (Loan loan : pendingStudyLoans) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Loan ID: " + loan.getLoanID());
             System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Loan Status: " + loan.getLoanStatus());
            System.out.println("----------------------------------------------------------------------------");

          

     

        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the loan ID to review details: ");
        int loanID = scanner.nextInt();
        scanner.nextLine();

 
     
            // Loan details found, parse and customize output based on loan type
            Loan loanToReview = null;
            for (Account acc : Bank.getAccounts()) {
                for (Loan loan : acc.getLoans()) {
                    if (loan.getLoanID() == loanID) {
                        loanToReview = loan;
                        break;
                    }
                }
            }

            if (loanToReview != null) {
                System.out.println("Load ID: "+loanToReview.getLoanID());
                System.out.println("Principal: "+loanToReview.getprincipal());
                System.out.println("Duration: "+loanToReview.getDuration());
                System.out.println("Loan Type: " + loanToReview.getLoanType());
                System.out.println("Balance: "+loanToReview.getBalance());
                System.out.println("Monthly Payment: "+ loanToReview.getMonthlyPayment());
                System.out.println("Guarantor Name: "+loanToReview.getGuarantorName());
                System.out.println("Guarantor ID: "+loanToReview.getGuarantorID());
                System.out.println("Guarantor Income: "+ loanToReview.getGuarantorIncome());
                System.out.println("Guarantor Contact no: "+loanToReview.getGuarantorContactNo());

               

                if (loanToReview instanceof PersonalLoan) {
                
                    // Customize output for   System.out.println("Loan Type: " + loanToReview.getLoanType());
                 PersonalLoan personalLoanInstance = (PersonalLoan) loanToReview;
               
                System.out.println("Personal Income: " + personalLoanInstance.getPersonalIncome());
                System.out.println("Annual Income: " + personalLoanInstance.getAnnualIncome());
                }
                
                 else if (loanToReview instanceof BusinessLoan) {
                    BusinessLoan businessLoanInstance = (BusinessLoan) loanToReview;
                    // Customize output for BusinessLoan
                    System.out.println("Unique EN: " + businessLoanInstance.getUniqueEn());
                    System.out.println("Business Type: " + businessLoanInstance.getBusinessType());
                      System.out.println("Business Description: " + businessLoanInstance.getBusinessDescription());
                      System.out.println("Cash in: "+businessLoanInstance.getCashInFlow());
                      System.out.println("Cash out: "+businessLoanInstance.getCashOutFlow());
                }
                else if (loanToReview instanceof StudyLoan) {
                    StudyLoan studyLoanInstance = (StudyLoan) loanToReview;
                    // Customize output for StudyLoan
                   
                    System.out.println("Student ID: " + studyLoanInstance.getStudentID());
                    System.out.println("Institution: " + studyLoanInstance.getInstitution());
                }
                System.out.print("Do you want to approve (A) or reject (R) this loan? :");
                String decision = scanner.nextLine();

                if (decision.equals("A")) {
                    loanToReview.approveLoan();
                } else if (decision.equals("R")) {
                    loanToReview.rejectLoan();
                } 

                



                 
            }

        
    }
}