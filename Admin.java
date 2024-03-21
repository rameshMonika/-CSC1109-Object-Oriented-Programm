import java.util.HashMap;
import java.util.List;

public class Admin {
    /**
     * This method creates a hashmap that stores
     * all of the back account numbers.
     * 
     * @return The hashmap containing account numbers.
     */
    public HashMap<Integer, Account> getAllAccounts() {
        HashMap<Integer, Account> allAccounts = new HashMap<>();

        for (Account account : Bank.getAccountNumbers()) {
            allAccounts.put(account.getAccountNumber(), account);
        }

        return allAccounts;
    }

    // Amended getAllLoans to return HashMap
    public HashMap<Integer, Loan> getAllLoans(List<Loan> loansList) {
        HashMap<Integer, Loan> allLoans = new HashMap<>();

        for (Loan loan : loansList) {
            allLoans.put(loan.getLoanID(), loan);
        }

        return allLoans;
    }

    
    /**
     * Adds an account to the bank.
     * 
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Remove an account from the bank.
     * 
     * @param account The account to remove.
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    /**
     * returns a list of account number from the account class
     */
    public ArrayList<Account> getAccountNumbers() {
        return accounts;
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
