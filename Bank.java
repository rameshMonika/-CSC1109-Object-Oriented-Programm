import java.util.ArrayList;

/**
 * Author: Darren, Min Xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
 * Description: The Bank class represents a bank
 * that manages a ArrayList of accounts, PIN validation
 * and account balance.
 */
/**
 * The Bank class represents a bank that manages a list of accounts, PIN
 * validation, and account balance.
 */
public class Bank {
    private ArrayList<Account> accounts;
    private String bankname;
    private Exchange exchange;

    /**
     * Default constructor that initializes the Bank object with a bank name and an
     * empty list of accounts.
     * 
     * @param bankname The name of the bank.
     */
    public Bank(String bankname) {
        this.bankname = bankname;
        this.accounts = new ArrayList<>();
        this.exchange = new Exchange();
    }

    /**
     * Gets the name of the bank.
     * 
     * @return The name of the bank.
     */
    public String getBankname() {
        return this.bankname;
    }

    /**
     * Adds an account to the bank.
     * 
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Remove an account from the bank.
     * 
     * @param account The account to remove.
     */
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    /**
     * Gets account number from the account class and print the all accountNumbers
     */
    public void getAccountNumbers() {
        for (Account account : accounts) {
            System.out.println(account.getAccountNumber());
        }
    }

    /**
     * Validates the PIN with the following accountNumber's pin and print the
     * outcome.
     * 
     * @param accountNumber The following accountNumber to validate pin.
     * @param PIN           The pin to validate.
     */
    public void validatePIN(int accountNumber, int PIN) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getPIN() == PIN) {
                    System.out.println("PIN is correct");
                } else {
                    System.out.println("PIN is incorrect");
                }
            }
        }
    }

    /**
     * Gets the account balance and prints the following accountNumber's balance.
     * 
     * @param accountNumber The following accountNumber to find the balance.
     * @param currency      The currency of the balance to show
     */
    public void getAccountBalance(int accountNumber, Currency currency) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("Account " + currency + " balance for account " + accountNumber + " is $"
                        + String.format("%.2f", account.getBalance(currency)));
            }
        }
    }

    /**
     * Converts the given amount from the given currency to SGD.
     * 
     * @param accountNumber The account number to convert the currency.
     * @param currency      The currency of the amount to be converted.
     * @param amount        The amount to be converted.
     */

    public void convertCurrency(int accountNumber, Currency currency, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                double temp = account.getBalance(currency);
                if (temp < amount) {
                    System.out.println("Insufficient balance");
                    return;
                }

                //double convertedcurrency = exchange.convertCurrency(Currency.USD, amount);
                double convertedcurrency = amount / exchange.getRate(currency);
                System.out.println("Converted amount is " + Currency.SGD + String.format("%.2f", convertedcurrency));
                account.setBalance(currency, temp - amount);
                account.addBalance(Currency.SGD, convertedcurrency);

            }
        }
    }

    /**
     * Apply Loan
     * 
     * @param loan The loan details.
     * @param accountNumber The account number that is linked to the loan.
     */
    public void applyLoan(Loan loan, int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setLoan(loan);
            }
        }
    }

    /**
     * Gets the Loan
     * 
     * @param accountNumber The account number tied to the loan.
     * @return The loan details. Returns null if there is no loan available.
     */
    public Loan getLoan(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account.getAllLoans();
            }
        }
        return null;
    }
}