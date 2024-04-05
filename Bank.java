import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * Author: Darren, Monika, Teren, Jana, Amanda, Kirby
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
    private static ArrayList<Account> accounts;
    private static String bankName;
    private static g02_BRA branch = new g02_BRA("1", false, "Singapore", "DBS", "Ang Mo Kio", 65504302, 5);
    private static Security security = new Security();

    /**
     * Gets the name of the bank.
     * 
     * @return The name of the bank.
     */
    public static String getBankname() {
        return bankName;
    }
    /**
     * Sets the name of the bank.
     * 
     * @params The name of the bank.
     */
    public static void setBankname(String name) {
        bankName = name;
    }
    /**
     * Adds an account to the bank.
     * 
     * @param account The account to add.
     */
    public static void addAccount(Account account) {
        accounts.add(account);
    }
    /**
     * Remove an account from the bank.
     * 
     * @param account The account to remove.
     */
    public static void removeAccount(Account account) {
        accounts.remove(account);
    }
    /**
     * Gets account number from the account class and print the all accountNumbers
     */
    public static void getAccountNumbers() {
        for (Account account : accounts) {
            System.out.println(account.getAccountNumber());
        }
    }
    /**
     * returns a account number from the account class
     * 
     * @params the account number tied to the account
     */
    public static Account getAccount(int accno) {
        for (Account account : accounts) {
            if (accno == account.getAccountNumber())
                return account;
        }
        return null;
    }
    /**
     * Validates the PIN with the following accountNumber's pin and print the
     * outcome.
     * 
     * @param accountNumber The following accountNumber to validate pin.
     * @param PIN           The pin to validate.
     * @return True if the PIN is correct, false otherwise.
     */
    public static boolean validatePIN(int accountNumber, String PIN) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getPIN().equals(PIN)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Gets the account balance and prints the following accountNumber's balance.
     * 
     * @param accountNumber The following accountNumber to find the balance.
     * @param currency      The currency of the balance to show
     */
    public static Double getAccountBalance(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account.getBalance();
            }
        }
        return 0.0;
    }
    /**
     * Prints the start page for the customer
     */
    public static void printStartPage() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to " + bankName);
            System.out.println("1 | Login");
            System.out.println("0 | Exit");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    printCustomerLoginPage();
                    break;
                case 0:
                    System.out.println("Thank you for using our service");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    printStartPage();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            printStartPage();
        }
    }
    /**
     * Checks if the account exists
     * @param accountId The account number to check
     * @return True if the account exists, false otherwise
     */
    public static boolean accountExists(int accountId) {
        // Assuming accounts is a List or Array of Account objects
        for (Account account : accounts) {
            if (account.getAccountNumber() == (accountId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Prints the login page for the customer
     */
    public static void printCustomerLoginPage() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Enter your account number: ");
        int accno = scanner.nextInt();
        if (accountExists(accno)) {
            for (Account account : accounts) {
                if (account.getAccountNumber() == accno) {
                    if (security.login(account.getPIN())) {
                        printCustomerMenu(account);
                    } else {
                        counter++;
                        if (counter < 3) {
                            System.out.println("Invalid PIN, please try again");
                            printCustomerLoginPage();
                        } else {
                            printLoginFailPage();
                        }
                    }
                }
            }
        } else {
            System.out.println("Account not found");
            printCustomerLoginPage();

        }
    }
    /**
     * Prints the login fail page for the customer
     */
    public static void printLoginFailPage() {
        System.out.println("Login failed");
        printStartPage();
    }

    /**
     * prints the Customer menu page
     * @param account The account to print the menu for
     */
    public static void printCustomerMenu(Account account) {
        try {
            Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + account.getCustomer().getName());
        System.out.println("Select actions:");
        System.out.println("1 | Check balance");
        System.out.println("2 | Transfer");
        System.out.println("3 | View account details");
        System.out.println("4 | Branch");
        System.out.println("5 | Insurance");
        System.out.println("6 | Account settings");
        System.out.println("0 | Logout");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printBalancePage(account);
                break;
            case 2:
                printTransferPage(account);
                break;
            case 3:
                printAccountDetailsPage(account);
                break;
            case 4:
                printBranchDetailsPage(account);
                break;
            case 5:
                printInsuranceDetailsPage(account);
                break;
            case 6:
                printAccountSettingsPage(account);
                break;
            case 0:
                printLogoutPage();
                break;
            default:
                System.out.println("Invalid input");
                printCustomerMenu(account);
                break;
        }
        } catch (Exception e) {
            System.out.println("Invalid input");
            printCustomerMenu(account);
        }
        
    }
    /**
     * Prints the balance page for the customer
     * @param account The account to print balance page for
     */
    public static void printBalancePage(Account account) {
        System.out.println("Current Account Balance: $" + account.getBalance());
        printMoreActions(account);
    }

    /**
     * Prints the transfer page for the customer
     * @param account The account to print transfer page for
     */
    public static void printTransferPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 | Inter Account Transfer");
        System.out.println("2 | Third Party Transfer");
        System.out.println("3 | Back to main menu");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                printInterTransferPage(account);
                break;
            case 2:
                printThirdPartyTransferPage(account);
                break;
            case 3:
                printCustomerMenu(account);
                break;
            default:
                System.out.println("Invalid input");
                printTransferPage(account);
                break;
        }
        printMoreActions(account);
    }
    /**
     * Prints the inter account transfer page for the customer
     * @param account The account to print inter account transfer page for
     */
    public static void printInterTransferPage(Account account) {
        ArrayList<Account> availableAccounts = showAvailableAccounts(account);
        if (availableAccounts.size() < 1) {
            System.out.println("There are no accounts linked.");
            printMoreActions(account);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno = scanner.nextInt();
        if (accno == account.getAccountNumber()) {
            System.out.println("Cannot transfer to the same account");
            printTransferPage(account);
        }
        Account receiving = getAccount(accno);
        if (receiving == null){
            System.out.println("Account does not exist");
            printTransferPage(account);
        }
        else if (receiving.getCustomerIC() != account.getCustomerIC()) {
            System.out.println("Cannot transfer to a third party account");
            printTransferPage(account);
        }
        for (Account acc : availableAccounts) {
            if (acc.getAccountNumber() == accno) {
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                if (account.transfer(getAccount(accno), amount)) {

                    System.out.println("Transfer successful");
                } else {
                    printTransactionFailPage(account);
                }
            }
        }
    }
    /**
     * Returns the available accounts for transfer
     * @param account
     * @return The available accounts for transfer
     */
    public static ArrayList<Account> showAvailableAccounts(Account account) {
        System.out.println("Accounts available for transfer: ");
        ArrayList<Account> availableAccounts = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getCustomerIC().equals(account.getCustomerIC())
                    && acc.getAccountNumber() != account.getAccountNumber()) {
                System.out.println(acc.getAccountNumber());
                availableAccounts.add(acc);
            }
        }
        return availableAccounts;
    }
    /**
     * Prints the third party transfer page for the customer
     * @param account The account to print third party transfer page for
     */
    public static void printThirdPartyTransferPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno2 = scanner.nextInt();
        System.out.println("Enter amount to transfer: ");
        double amount2 = scanner.nextDouble();
        Account receivingAcc = getAccount(accno2);
        if (receivingAcc == null) {
            printUserNotFoundPage(account);
        }
        else if (account.transfer(receivingAcc, amount2)) {

            System.out.println("Transfer successful");
        } else {

            printTransactionFailPage(account);
        }
    }
    /**
     * Prints the user not found page for the customer
     * @param account The account to print user not found page for
     */
    public static void printUserNotFoundPage(Account account) {
        System.out.println("User not found");
        printMoreActions(account);
    }

    /**
     * Prints the transaction fail page for the customer
     * @param account The account to print transaction fail page for
     */
    public static void printTransactionFailPage(Account account) {
        System.out.println(
                "Transaction failed, transfer limit may have been reached or insufficient funds, please try again.");
        printMoreActions(account);
    }

    /**
     * Prints the account details page for the customer
     * @param account The account to print account details for
     */
    public static void printAccountDetailsPage(Account account) {
        printAccountDetails(account);
        printMoreActions(account);
    }
    /**
     * Prints the account details for the customer
     * @param account The account to print account details for
     */
    public static void printAccountDetails(Account account) {
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Name: " + account.getCustomer().getName());
        System.out.println("NRIC: " + account.getCustomer().getNRIC());
        System.out.println("Email: " + account.getCustomer().getEmail());
        System.out.println("Contact Number: " + account.getCustomer().getContactNo());
        System.out.println("Account Balance: $" + account.getBalance());
    }

    /**
     * Prints the account settings page for the customer
     * @param account The account to print account settings for
     */
    public static void printAccountSettingsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select actions:");
        System.out.println("1 | Change PIN \t\t\t| " + account.getPIN());
        System.out.println("2 | Change Withdraw Limit \t| " + account.getWithdrawLimit());
        System.out.println("3 | Change Transfer Limit \t| " + account.getTransferLimit());
        System.out.println("4 | Change Email \t\t| " + account.getCustomer().getEmail());
        System.out.println("5 | Change Contact Number \t| " + account.getCustomer().getContactNo());
        System.out.println("6 | Close Account");
        System.out.println("0 | Back to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printChangePINPage(account);
                break;
            case 2:
                printChangeWithdrawLimitPage(account);
                break;
            case 3:
                printChangeTransferLimitPage(account);
                break;
            case 4:
                printChangeEmailPage(account);
                break;
            case 5:
                printChangeContactNumberPage(account);
                break;
            case 6:
                printCloseAccountPage(account);
            case 0:
                printCustomerMenu(account);
                break;
            default:
                System.out.println("Invalid input");
                printAccountSettingsPage(account);
                break;
        }
    }
    /**
     * Prints the change PIN page for the customer
     * @param account The account to change PIN for
     */
    public static void printChangePINPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new PIN: ");
        String newPIN = scanner.nextLine();
        account.setPIN(security.encrypt(newPIN));
        System.out.println("PIN changed successfully");
        printMoreActions(account);
    }

    /**
     * Prints the change Withdraw Limit page for the customer
     * @param account The account to change withdraw limit for
     */
    public static void printChangeWithdrawLimitPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Withdraw Limit: ");
        double newWithdrawLimit = scanner.nextDouble();
        account.setWithdrawLimit(newWithdrawLimit);
        System.out.println("Withdraw Limit changed successfully");
        printMoreActions(account);
    }

    /**
     * Prints the change Transfer Limit page for the customer
     * @param account The account to change transfer limit for
     */
    public static void printChangeTransferLimitPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Transfer Limit: ");
        double newTransferLimit = scanner.nextDouble();
        account.setTransferLimit(newTransferLimit);
        System.out.println("Transfer Limit changed successfully");
        printMoreActions(account);
    }
    /**
     * Prints the change Email page for the customer
     * @param account The account to change email for
     */
    public static void printChangeEmailPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Email: ");
        String newEmail = scanner.nextLine();
        account.getCustomer().setEmail(newEmail);
        System.out.println("Email changed successfully");
        printMoreActions(account);
    }
    /**
     * Prints the change Contact Number page for the customer
     * @param account The account to change contact number for
     */
    public static void printChangeContactNumberPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Contact Number: ");
        int newContactNumber = scanner.nextInt();
        account.getCustomer().setContactNo(newContactNumber);
        System.out.println("Contact Number changed successfully");
        printMoreActions(account);
    }
    /**
     * Prints the close account page for the customer
     * @param account The account to close
     */
    public static void printCloseAccountPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to close your account? (Y/N)");
        String input = scanner.nextLine();
        switch (input) {
            case "Y":
                closeAccountVerification(account);
                break;
            case "N":
                printAccountSettingsPage(account);
                break;
            default:
                System.out.println("Invalid input");
                printCloseAccountPage(account);
                break;
        }
    }
    /**
     * Checks if the NRIC is valid
     * @param nric The NRIC to check
     * @return True if the NRIC is valid, false otherwise
     */
    public static boolean checkNRIC(String nric) {
        for (Account account : accounts) {
            if (account.getCustomer().getNRIC().substring(5).equals(nric)) {
                return true;
            }
        }
        return false;
    }   
    /**
     * Closes the account after verification
     * @param account The account to close
     */
    public static void closeAccountVerification(Account account) {
        System.out.println("Please enter your last four digits of your NRIC to verify your identity: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (checkNRIC(input)) {
            accounts.remove(account);
            System.out.println("Account closed successfully");
            printStartPage();
        } else {
            System.out.println("Verification failed");
            printCloseAccountPage(account);
        }
    }

    /**
     * Checks if customer still wants to do more actions
     * @param account The account to check if customer wants to do more actions
     */
    public static void printMoreActions(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            switch (input) {
                case "Y":
                    printCustomerMenu(account);
                    break;
                case "N":
                    printLogoutPage();
                    break;
                default:
                    System.out.println("Invalid input");
                    printMoreActions(account);
                    break;
            }
        }
    }
    /**
     * Prints the logout page for the customer
     */
    public static void printLogoutPage() {
        System.out.println("Thank you for using our service");
        System.out.println("You have been logged out");
        printStartPage();
    }
    /**
     * Prints the branch details page for the customer
     * @param account The account to print branch details for
     */
    public static void printBranchDetailsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Branch:");
        System.out.println("1 | Display Branch Details");
        System.out.println("2 | Display Current Queue");
        System.out.println("3 | Generate Queue Number");
        System.out.println("0 | Back to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                branch.displayBranchInfo();
                printMoreActions(account);
                break;
            case 2:
                branch.displayQueue();
                printMoreActions(account);
                break;
            case 3:
                int choice = 0;
                do {
                    System.out.println("Do you want a Physical or Virtual Queue?");
                    System.out.println("1 | Physical");
                    System.out.println("2 | Virtual");
                    System.out.println("0 | Back to Main Menu");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            branch.generateQueueNumber(true, 1);
                            printMoreActions(account);
                            break;
                        case 2:
                            branch.generateQueueNumber(false, 1);
                            printMoreActions(account);
                            break;
                        case 0:
                            printCustomerMenu(account);
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                } while (choice > 2 || choice < 0);

                break;
            case 0:
                printCustomerMenu(account);
                break;

        }
    }
    /**
     * Prints the insurance details page for the customer
     * @param account The account to print insurance details for
     */
    public static void printInsuranceDetailsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insurance");
        System.out.println("1 | Display Owned Policy Information");
        System.out.println("2 | Apply Policies");
        System.out.println("3 | Make Payment");
        System.out.println("4 | Remove policy");
        System.out.println("0 | Back to main menu");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                printOwnedPolicies(account);
                break;
            case 2:
                printPoliciesPage(account);
                break;
            case 3:
                printInsurancePaymentPage(account);
            case 4:
                printRemoveInsurancePage(account);
            case 0:
                printCustomerLoginPage();
            default:
                System.out.println("Invalid option");
                break;

        }
        printMoreActions(account);
    }
    /**
     * Prints the owned policies for the customer
     * @param account The account to print owned policies for
     */
    public static void printOwnedPolicies(Account account){
        if (account.getInsurance().isEmpty() || account.getInsurance().size() == 0){
            System.out.println("You do not own any insurance");
            return;
        }
        for (Insurance insurance : account.getInsurance()) {
            System.out.println("------------------------------------------------------");
            System.out.println("Information for Policy Number: " + insurance.getPolicyNumber());
            System.out.println("Policy Name: " + insurance.getPolicyName());
            System.out.println("Policy Type: " + insurance.getPolicyType());
            System.out.println("Policy Owner: " + insurance.getPolicyOwner());
            System.out.println("Policy Status: " + insurance.getPolicyStatus());
            System.out.println("Premium Amount " + insurance.getPremiumAmount());
            System.out.println("Payment Frequency " + insurance.getPremiumFrequency());
            System.out.println("Sum Assured: " + insurance.getSumAssured());
            System.out.println("Total Premium Paid: " + insurance.getTotalPremiumPaid());
            System.out.println("Start Date: " + insurance.getInception_date());
            System.out.println("End Date: " + insurance.getMaturity_date());
        }
        System.out.println("------------------------------------------------------");
    }
    /**
     * Prints the policies page for the customer
     * @param account The account to apply insurance to
     */
    public static void printPoliciesPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Policies");
        System.out.println("1 | Savings");
        System.out.println("2 | Health");
        System.out.println("3 | Vehicle");
        System.out.println("0 | Back to main menu");
        int choice = scanner.nextInt();
        Boolean foundInsurance = false;
        switch (choice) {
            case 1:
                Insurance sinsurance = new Insurance("Savings", "Savings", account.getCustomer().getName(),
                        "MONTHLY", account.getInsurance().isEmpty() ? 1 : account.getInsurance().size() + 1,
                        LocalDate.now(), LocalDate.now().plusYears(10), 10000.0, 83.33, 0, 0);
                account.addInsurance(sinsurance);
                System.out.println("Successfully applied for Savings Insurance");
                break;
            case 2:
                Insurance hinsurance = new Insurance("Health", "Healthcare", account.getCustomer().getName(),
                        "MONTHLY", account.getInsurance().isEmpty() ? 1 : account.getInsurance().size() + 1,
                        LocalDate.now(), LocalDate.now().plusYears(10), 10000.0, 83.33, 0, 0);
                account.addInsurance(hinsurance);
                System.out.println("Successfully applied for Health Insurance");
                break;
            case 3:
                Insurance cinsurance = new Insurance("Vehicle", "Vehicle", account.getCustomer().getName(),
                        "MONTHLY", account.getInsurance().isEmpty() ? 1 : account.getInsurance().size() + 1,
                        LocalDate.now(), LocalDate.now().plusYears(10), 10000.0, 83.33, 0, 0);
                account.addInsurance(cinsurance);
                System.out.println("Successfully applied for Vehicle Insurance");
                break;
            case 0:
                printCustomerLoginPage();
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    public static ArrayList<Insurance> checkOwnedInsurance(Account account){
        ArrayList<Insurance> ownedInsurance = new ArrayList<>();
        System.out.println("Insurance you owned: ");
        if (account.getInsurance().isEmpty() || account.getInsurance().size() == 0) {
            System.out.println("You do not own any insurance under us");
            printInsuranceDetailsPage(account);
        } else {
            for (Insurance insurance : account.getInsurance()) {
                System.out.println(insurance.getPolicyNumber());
                ownedInsurance.add(insurance);
            }
        }
        return ownedInsurance;
    }
    /**
     * Prints the insurance payment page for the customer
     * @param account The account to make payment to
     */
    public static void printInsurancePaymentPage(Account account) {
        ArrayList<Insurance> ownedInsurance = checkOwnedInsurance(account);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select the policy that you want to make payment to");
            int policyNo = scanner.nextInt();
            for (Insurance insurance : ownedInsurance) {
                if (policyNo == insurance.getPolicyNumber())
                {
                    System.out.println("Enter the amount you want to pay");
                    int amount = scanner.nextInt();
                    System.out.println("Confirm? \n1 | Yes \n0 | No");
                    int input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            insurance.makePayment(account, amount);
                            printMoreActions(account);
                            break;
                        case 0:
                            printMoreActions(account);
                            break;
                        default:
                            System.out.println("Invalid input");
                            printMoreActions(account);
                            break;
                    }
                }
                else {
                    System.out.println("Invalid Policy Number");
                    printMoreActions(account);
                }
            }
    }
    /**
     * Prints the remove insurance page for the customer
     * @param account The account to remove insurance from
     */
    public static void printRemoveInsurancePage(Account account){
        ArrayList<Insurance> ownedInsurance = checkOwnedInsurance(account);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select the policy that you want to remove");
            int policyNo = scanner.nextInt();
            int counter = 0;
            boolean found = false;
            for (Insurance insurance : ownedInsurance) {
                counter++;
                if (policyNo == insurance.getPolicyNumber())
                {  
                    found = true;
                    System.out.println("Confirm? \n1 | Yes \n0 | No");
                    int input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            account.getInsurance().remove(counter-1);
                            printMoreActions(account);
                            break;
                        case 0:
                            printMoreActions(account);
                            break;
                    }
                }
            }
            if (!found){
                System.out.println("Invalid Policy Number");
                printMoreActions(account);
            }
    }
    

    public static void main(String[] args) {
        accounts = new ArrayList<>();
        setBankname("DBS");
        Customer customer = new Customer("S1234567A", "John Doe", new Date(2000, 11, 1), 12345678, "John@email.com");
        Customer customer2 = new Customer("S1234567B", "Jane Doe", new Date(2000, 11, 1), 12345678, "Jane@email.com");
        Account account = new Account(customer, 1, security.encrypt("123456"), 1000.0);
        Account account2 = new Account(customer, 2, security.encrypt("123456"));
        Account account3 = new Account(customer2, 3, security.encrypt("123456"));
        Bank.addAccount(account);
        Bank.addAccount(account2);
        Bank.addAccount(account3);
        printStartPage();
    }
}