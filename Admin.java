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

    
}
