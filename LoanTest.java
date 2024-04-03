import java.util.Date;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;


public class LoanTest {
    public static void main(String[] args) {

        try {
            // Create a Customer

            Customer customer = new Customer("S1234567A", "John Doe", new Date(), 12345678, "john.doe@example.com", 30, 50000.0);

            // Create an Account for the Customer
            Account account = new Account(customer, 123456, 1234);

            BusinessLoan businessLoan = new BusinessLoan("Technology", "Software Development", 75000.0,  12, "Business Loan", "Jake",2,220000.00,93887001, account, "EN123456", 200000.0, 100000, 50000);

            BusinessLoan businessLoan2 = new BusinessLoan("Food", "Fast", 75000.0,  12, "Business Loan", "Jake",2,220000.00,93887001, account, "EN123456", 200000.0, 100000, 50000);

            PersonalLoan personalLoan = new PersonalLoan(5000.0, 24, "Personal Loan", "Jane", 3, 30000.0, 98765432, account, 3000.0, 36000.0);

            // Create a Study Loan
            StudyLoan studyLoan = new StudyLoan(10000.0,  36, "Study Loan", "Jane", 3, 30000.0, 98765432, account, "STU123456", "University ABC");

            // Set the Business Loan for the Account

            account.addLoan(businessLoan);
            account.addLoan(businessLoan2);
            account.addLoan(personalLoan);
            account.addLoan(studyLoan);

            // Create a Bank
            Bank.setBankname("Example Bank");

            // Add the Account to the Bank
            Bank.addAccount(account);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
            }


        System.out.println("=======================================================================================");
        //ArrayList<Loan> pendingLoans = Bank.getPendingLoans();
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
        
        int loanID = 0;
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print("Enter the loan ID to review details: ");
            String userInt = scanner.nextLine();
            loanID = Integer.parseInt(userInt);
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Please enter a a proper Loan ID!"); 
            scanner.nextLine();
        }

 
     
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
                if (loanToReview instanceof PersonalLoan) {
      
                 PersonalLoan personalLoanInstance = (PersonalLoan) loanToReview;
                 personalLoanInstance.printLoanDetails();
               
                }
                
                 else if (loanToReview instanceof BusinessLoan) {
                    BusinessLoan businessLoanInstance = (BusinessLoan) loanToReview;
                    businessLoanInstance.printLoanDetails();
                  
                }
                else if (loanToReview instanceof StudyLoan) {
                    StudyLoan studyLoanInstance = (StudyLoan) loanToReview;
                    // Customize output for StudyLoan

                    studyLoanInstance.printLoanDetails();                   
                
                }
                System.out.print("Do you want to approve (A) or reject (R) this loan? :");
                String decision = scanner.nextLine().toUpperCase();
                scanner.close();
                if(!decision.equals("A") && !decision.equals("R")){
                    throw new IllegalArgumentException("Please enter either 'A' to approve or 'R' to reject.");
                }

                if (decision.equals("A")) {
                    loanToReview.approveLoan();
                } else if (decision.equals("R")) {
                    loanToReview.rejectLoan();
                } 
                 
            } else{
                System.out.println("Loan ID " + loanID + " not found.");
            }
        
    }
}