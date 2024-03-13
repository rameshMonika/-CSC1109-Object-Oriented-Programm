import java.util.ArrayList;

public class Admin {
    private String name;
    private String loginID;
    private String password;

    public Admin(String name, String loginID, String password) {
        this.name = name;
        this.loginID = loginID;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void approveLoan(Loan loan) {
        loan.setLoanStatus("Approved");
        System.out.println("Loan approved for account number: " + loan.getAccount().getAccountNumber());
    }

    public void rejectLoan(Loan loan) {
        loan.setLoanStatus("Rejected");
        System.out.println("Loan rejected for account number: " + loan.getAccount().getAccountNumber());
    }

    public void showPendingLoans(ArrayList<Loan> loans) {
        System.out.println("Pending Loans:");
        for (Loan loan : loans) {
            if (loan.getLoanStatus().equals("Pending")) {
                System.out.println(
                        "Loan ID: " + loan.getLoanID() + ", Account Number: " + loan.getAccount().getAccountNumber());
            }
        }
    }

    public void reviewLoanDetails(Loan loan) {
        if (loan != null) {
            System.out.println("Loan Details:");
            System.out.println("Loan ID: " + loan.getLoanID());
            System.out.println("Account Number: " + loan.getAccount().getAccountNumber());
            System.out.println("Loan Principal: " + loan.getprincipal());
            System.out.println("Loan Type: " + loan.getLoanType());
            if (loan instanceof BusinessLoan) {
                BusinessLoan businessLoan = (BusinessLoan) loan;
                System.out.println("Business Type: " + businessLoan.getBusinessType());
                System.out.println("Business Description: " + businessLoan.getBusinessDescription());
                System.out.println("Cash In Flow: " + businessLoan.getCashInFlow());
                System.out.println("Cash Out Flow: " + businessLoan.getCashOutFlow());
            }
            System.out.println("Loan Status: " + loan.getLoanStatus());
        } else {
            System.out.println("No loan selected for review.");
        }
    }
}
