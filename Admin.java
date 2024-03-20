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
            allLoans.put(loan.getLoanId(), loan);
        }

        return allLoans;
    }
}
