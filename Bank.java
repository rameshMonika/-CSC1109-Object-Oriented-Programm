import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static String bankname = "ABC Bank";
    private static ForeignExchange exchange = new ForeignExchange();

    // /**
    // * Default constructor that initializes the Bank object with a bank name and
    // an
    // * empty list of accounts.
    // *
    // * @param bankname The name of the bank.
    // */
    // public static Bank(String bankname) {
    // bankname = bankname;
    // accounts = new ArrayList<>();
    // exchange = new Exchange();
    // }

    /**
     * Gets the name of the bank.
     * 
     * @return The name of the bank.
     */

    public static String getBankname() {
        return bankname;
    }

    /**
     * Sets the name of the bank.
     * 
     * @params The name of the bank.
     */
    public static void setBankname(String bankName) {
        bankname = bankName;
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
     * returns a list of account number from the account class
     */
    public static ArrayList<Account> getAccountNumbers() {
        return accounts;
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

    public static void addAdmin(Admin admin) {
        admins.add(admin);
        saveAdminAccount();
    }

    public static Admin getAdmin(String id) {
        for (Admin admin : admins) {
            if (id.equals(admin.getAdminID())) {
                return admin;
            }
        }
        return null;
    }

    /**
     * Validates the PIN with the following accountNumber's pin and print the
     * outcome.
     * 
     * @param accountNumber The following accountNumber to validate pin.
     * @param PIN           The pin to validate.
     */
    public static boolean validatePIN(int accountNumber, int PIN) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getPIN() == PIN) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validateAdmin(String adminID, String password) {
        for (Admin admin : admins) {
            if (admin.getAdminID().equals(adminID)) {
                if (admin.getPassword().equals(password)) {
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
    public static Double getAccountBalance(int accountNumber, Currency currency) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account.getBalance(currency);
            }
        }
        return 0.0;
    }

    /**
     * Converts the given amount from SGD to given currency.
     * 
     * @param accountNumber The account number to convert the currency.
     * @param currency      The currency of the amount to be converted.
     * @param amount        The amount to be converted.
     */

    public static Double convertCurrency(int accountNumber, Currency currency, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                double temp = account.getBalance(Currency.SGD);
                if (temp < amount)
                    return -1.0;
                else
                    return exchange.convertCurrency(Currency.SGD.toString(), currency.toString(), amount);
                // return exchange.convertCurrency(Currency.USD, amount);
            }
        }
        return -1.0;
    }

    public static void applyCreditCard(Account account, CreditCardType type) {
        CreditCard newCard = new CreditCard(account.getAccountNumber(), account.getCustomer(), type);
        account.addCreditCard(newCard);
    }

    public static void printLoginPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + bankname);
        int input = -1;
        do {
            if (input > 2) {
                System.out.println("Selection was invalid. Please select the given options");
            }
            System.out.println("Select 1 for Customer | Select 2 for Admin | Select 0 to close the application");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    int counter = 0;
                    System.out.println("Enter your account number: ");
                    int accno = scanner.nextInt();
                    do {
                        System.out.println("Enter your PIN: ");
                        int pin = scanner.nextInt();
                        if (validatePIN(accno, pin)) {

                            System.out.println("Login successful");
                            printCustomerMenu(getAccount(accno));
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                        if (counter == 3) {
                            printLoginFailPage();
                            break;
                        }
                    } while (counter > 0);
                    break;
                case 2:
                    printAdminLoginPage();
                    break;
            }
        } while (input > 2);
        scanner.close();
    }

    public static void printLoginFailPage() {
        System.out.println("Login failed");
        printLoginPage();
    }

    public static void printMoreActions(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input = scanner.nextLine();

        if (input.equals("Y") || input.equals("y")) {
            printCustomerMenu(account);
        } else {
            printLogoutPage();
        }
    }

    public static void printLogoutPage() {
        System.out.println("Thank you for using our service");
        System.out.println("You have been logged out");
        printLoginPage();
    }

    public static void printBalancePage(Account account) {
        System.out.println("Current Account Balance");
        System.out.println("SGD: " + account.getBalance(Currency.SGD));
        System.out.println("USD: " + account.getBalance(Currency.USD));
        System.out.println("EUR: " + account.getBalance(Currency.EUR));
        System.out.println("JPY: " + account.getBalance(Currency.JPY));
        System.out.println("MYR: " + account.getBalance(Currency.MYR));
        printMoreActions(account);
    }
    // public static void printDepositPage(Account account){
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Enter amount to deposit: ");
    // double amount = scanner.nextDouble();
    // account.deposit(amount);
    // printMoreActions(account);
    // }
    // public static void printWithdrawPage(Account account){
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Enter amount to withdraw: ");
    // double amount = scanner.nextDouble();
    // if(account.withdraw(amount)){
    // System.out.println("Withdrawal successful");
    // }
    // else{
    // System.out.println("Withdrawal failed, insufficient funds or withdrawal limit
    // reached. Please try again.");
    // }

    // printMoreActions(account);
    // }
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
        }
        printMoreActions(account);
    }

    public static void printInterTransferPage(Account account) {
        System.out.println("Accounts available for transfer: ");
        ArrayList<Account> availableAccounts = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getCustomerIC().equals(account.getCustomerIC())
                    && acc.getAccountNumber() != account.getAccountNumber()) {
                System.out.println(acc.getAccountNumber());
                availableAccounts.add(acc);
            }
        }

        if (availableAccounts.size() < 1) {
            System.out.println("There are no accounts linked.");
            printMoreActions(account);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno = scanner.nextInt();
        Account receiving = getAccount(accno);
        if (receiving == null) {
            System.out.println("Account not found");

            printTransferPage(account);
        }
        for (Account acc : availableAccounts) {
            if (acc.getAccountNumber() == accno) {
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                if (account.interAccountTransfer(getAccount(accno), amount)) {

                    System.out.println("Transfer successful");
                } else {
                    printTransactionFailPage(account);
                }
            }
        }
    }

    public static void printThirdPartyTransferPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number to transfer to: ");
        int accno2 = scanner.nextInt();
        System.out.println("Enter amount to transfer: ");
        double amount2 = scanner.nextDouble();
        Account receivingAcc = getAccount(accno2);
        if (receivingAcc == null) {

            printTransactionFailPage(account);
        }
        if (receivingAcc.thirdPartyTransfer(account, amount2)) {

            System.out.println("Transfer successful");
        } else {

            printTransactionFailPage(account);
        }
    }

    public static void printTransactionFailPage(Account account) {
        System.out.println(
                "Transaction failed, transfer limit may have been reached or insufficient funds, please try again.");
        printMoreActions(account);
    }

    public static void printConvertCurrencyPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount to convert: ");
        double amount = scanner.nextDouble();
        if (account.getBalance(Currency.SGD) < amount) {

            System.out.println("There is insufficient funds");
            printCustomerMenu(account);
        } else {
            System.out.println("1 | USD");
            System.out.println("2 | EUR");
            System.out.println("3 | JPY");
            System.out.println("4 | MYR");
            System.out.println("5 | Back to main menu");
            int input = scanner.nextInt();
            account.setBalance(Currency.SGD, account.getBalance(Currency.SGD) - amount);
            double converted = 0;
            switch (input) {
                case 1:

                    converted = Bank.convertCurrency(account.getAccountNumber(), Currency.USD, amount);
                    account.setBalance(Currency.USD, converted);
                    printMoreActions(account);
                    break;
                case 2:

                    converted = Bank.convertCurrency(account.getAccountNumber(), Currency.EUR, amount);
                    account.setBalance(Currency.EUR, converted);
                    printMoreActions(account);
                    break;
                case 3:

                    converted = Bank.convertCurrency(account.getAccountNumber(), Currency.JPY, amount);
                    account.setBalance(Currency.JPY, converted);
                    printMoreActions(account);
                    break;
                case 4:

                    converted = Bank.convertCurrency(account.getAccountNumber(), Currency.MYR, amount);
                    account.setBalance(Currency.MYR, converted);
                    printMoreActions(account);
                    break;
                case 5:

                    printCustomerMenu(account);
                    break;
            }
        }
    }

    public static void printSettingsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 | Account details & security settings");
        System.out.println("2 | Transactions settings");
        System.out.println("3 | Back to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                printAccountSettingsPage(account);
                break;
            case 2:

                printTransactionSettingsPage(account);
                break;
            case 3:

                printCustomerMenu(account);
                break;
            default:

                System.out.println("Invalid input");
                printSettingsPage(account);
        }
    }

    public static void printAccountSettingsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Account details & security settings");
        System.out.println("1 | Change PIN " + "Current PIN | " + account.getPIN());
        System.out.println("2 | Change Email " + "Current Email | " + account.getCustomer().getEmail());
        System.out.println(
                "3 | Change Contact Number " + "Current Contact Number | " + account.getCustomer().getContactNo());
        System.out.println("4 | Back to main menu ");

        int input = scanner.nextInt();
        switch (input) {
            case 1:

                printChangePinPage(account);
                break;
            case 2:

                printChangeEmailPage(account);
                break;
            case 3:

                printChangeContact(account);
                break;
            case 4:

                printCustomerMenu(account);
                break;
            default:

                System.out.println("Invalid input");
                printAccountSettingsPage(account);
        }
        printMoreActions(account);
    }

    public static void printChangePinPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new PIN: ");
        int pin = scanner.nextInt();
        account.setPIN(pin);

        printMoreActions(account);
    }

    public static void printChangeEmailPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new email: ");
        String email = scanner.nextLine();
        account.getCustomer().setEmail(email);

        printMoreActions(account);
    }

    public static void printChangeContact(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new phone number: ");
        int phone = scanner.nextInt();
        account.getCustomer().setContactNo(phone);

        printMoreActions(account);
    }

    public static void printTransactionSettingsPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Transaction settings");
        System.out.println("1 | Set daily transfer limit");
        System.out.println("2 | Set daily withdrawal limit");
        System.out.println("3 | Back to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                printSetTransferLimitPage(account);
                break;
            case 2:

                printSetWithdrawLimitPage(account);
                break;
            case 3:

                printCustomerMenu(account);
                break;
        }
    }

    public static void printSetTransferLimitPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new daily transfer limit: ");
        double limit = scanner.nextDouble();

        account.setTransferLimit(limit);
        printMoreActions(account);
    }

    public static void printSetWithdrawLimitPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new daily withdrawal limit: ");
        double limit = scanner.nextDouble();
        account.setWithdrawLimit(limit);

        printMoreActions(account);
    }

    public static void printCustomerMenu(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + account.getCustomer().getName());
        System.out.println("Select actions:");
        System.out.println("1 | Check balance");
        System.out.println("2 | Transfer");
        System.out.println("3 | Convert currency");
        System.out.println("4 | Apply for loan");
        System.out.println("5 | View loan status");
        System.out.println("6 | View transaction history");
        System.out.println("7 | Credit card services");
        System.out.println("8 | Account settings");
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

                printConvertCurrencyPage(account);
                break;
            case 4:

                printApplyLoanPage(account);
                break;
            case 5:

                printLoanStatusPage(account);
                break;
            case 7:

                printCreditCardPage(account);
                break;
            case 8:

                printSettingsPage(account);
                break;
            case 0:

                printLogoutPage();
                break;
            default:

                System.out.println("Invalid input");
                printCustomerMenu(account);
                break;

        }
    }

    public static void printCreditCardPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Credit Card Services");
        System.out.println("1 | Apply for Credit Card");
        System.out.println("2 | View Credit Card Details");
        System.out.println("3 | View Monthly Statement");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                checkForCreditCard(account);
                break;
            case 2:

                printViewCreditCardPage(account);
                break;
            case 3:

                printViewMonthlyStatementPage(account);
                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void printApplyCreditCardPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application of Credit Card");
        System.out.println("Select Credit Card Type");
        System.out.println("1 | Regular");
        System.out.println("2 | Student");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                applyCreditCard(account, CreditCardType.REGULAR);
                printApplicationSuccessPage(account);
                break;
            case 2:

                applyCreditCard(account, CreditCardType.STUDENT);
                printApplicationSuccessPage(account);
                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void checkForCreditCard(Account account) {
        if (account.getCC() != null) {
            System.out.println("You already have a credit card");
            printMoreActions(account);
        } else {
            printApplyCreditCardPage(account);
        }
    }

    public static void printApplicationSuccessPage(Account account) {
        System.out.println("Credit application successful");
        System.out.println("Your credit card will be delivered to your address within 7 working days");
        printMoreActions(account);
    }

    public static void printViewCreditCardPage(Account account) {
        if (account.getCC() == null) {
            System.out.println("You do not have a credit card");
            printMoreActions(account);
        } else {
            printCreditCardDetails(account);
        }
    }

    public static void printCreditCardDetails(Account account) {
        System.out.println("View Credit Card Details");
        System.out.println("Card No." + account.getCC().getCardNo());
        System.out.println("Card Type: " + account.getCC().getCardType());
        System.out.println("CVV: " + account.getCC().getCvv());
        System.out.println("Spending Limit: " + account.getCC().getSpendingLimit());
        printMoreActions(account);
    }

    public static void printViewMonthlyStatementPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("View Monthly Statement");
        System.out.println("1 | pending monthly statement");
        System.out.println("2 | past monthly statement");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                printPendingMonthlyStatement(account);
                break;
            case 2:

                printPastMonthlyStatement(account);
                break;
            case 0:

                printCustomerMenu(account);

        }

    }

    public static void printPendingMonthlyStatement(Account account) {
        System.out.println("Pending Monthly Statement");
        System.out.println(account.getCC().getAllPendingMonthlyStatements());
        printMoreActions(account);
    }

    public static void printPastMonthlyStatement(Account account) {
        System.out.println("Past Monthly Statement");
        System.out.println(account.getCC().getAllPastMonthlyStatements());
        printMoreActions(account);
    }

    public static void printLoanStatusPage(Account account) {
        System.out.println("Status of all loans");
        for (Loan loan : account.getLoans()) {
            System.out.println(loan.getLoanType() + " | " + loan.getLoanStatus());
        }
        printMoreActions(account);
    }

    public static void printApplyLoanPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application of Loan");
        System.out.println("1 | Business Loan");
        System.out.println("2 | Personal Loan");
        System.out.println("3 | Student Loan");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        switch (input) {
            case 1:

                printBusinessLoanPage(account);
                break;
            case 2:

                printPersonalLoanPage(account);
                break;
            case 3:

                printStudentLoanPage(account);
                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void printLoanApplicationSuccessPage(Account account) {
        System.out.println("Loan application successfully sent for approval");
        printMoreActions(account);
    }

    public static void printBusinessLoanPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application of Business Loan");
        System.out.println("1 | Apply Loan");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1:
                try {
                    System.out.println("Please enter business: ");
                    String business = scanner.nextLine();
                    System.out.println("Please enter a description of the business: ");
                    String businessDesc = scanner.nextLine();
                    System.out.println("Please enter the Guarantor's name: ");
                    String guarantorName = scanner.nextLine();
                    System.out.println("Please enter the Guarantor's Income: ");
                    Double guarantorIncome = scanner.nextDouble();
                    System.out.println("Please enter the Guarantor's Contact Number: ");
                    int guarantorContactNo = scanner.nextInt();
                    System.out.println("Please enter your business's annual income: ");
                    Double businessAnnualIncome = scanner.nextDouble();
                    System.out.println("Please enter your business's cash inflow: ");
                    int cashInFlow = scanner.nextInt();
                    System.out.println("Please enter your business's cash outflow: ");
                    int cashOutFlow = scanner.nextInt();
                    System.out.println("Enter principal amount: ");
                    int principal = scanner.nextInt();
                    BusinessLoan bLoan = new BusinessLoan(business, businessDesc, principal, 0.05f, 12, "Business Loan",
                            guarantorName, 2, guarantorIncome, guarantorContactNo, account, "EN123456",
                            businessAnnualIncome, cashInFlow, cashOutFlow, "Pending");
                    if (bLoan.isEligibleForLoan()) {
                        account.addLoan(bLoan);
                        printLoanApplicationSuccessPage(account);
                    } else {
                        System.out.println("You are not eligible for a loan");
                        System.out.println("Principal must be between 50000 and 100000");
                        printMoreActions(account);
                    }

                } catch (Exception e) {
                    System.out.println("Input was incorrect");
                    printPersonalLoanPage(account);
                }

                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void printPersonalLoanPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application of Personal Loan");
        System.out.println("1 | Apply Loan");
        System.out.println("0 | Return to main menu");
        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1:
                try {
                    System.out.println("Please enter the Guarantor's name: ");
                    String guarantorName = scanner.nextLine();
                    System.out.println("Please enter the Guarantor's income: ");
                    Double guarantorIncome = scanner.nextDouble();
                    System.out.println("Please enter the Guarantor's Contact Number: ");
                    int guarantorContactNo = scanner.nextInt();
                    System.out.println("Please enter the your income: ");
                    Double personalIncome = scanner.nextDouble();
                    System.out.println("Please enter your annual income: ");
                    Double personalAnnualIncome = scanner.nextDouble();
                    PersonalLoan pLoan = new PersonalLoan(5000.0, 0.1f, 24, "Personal Loan",
                            guarantorName, 3, guarantorIncome, guarantorContactNo, account, "Pending",
                            personalIncome, personalAnnualIncome);
                    if (pLoan.isEligibleForLoan(account.getCustomer())) {
                        account.addLoan(pLoan);
                        printLoanApplicationSuccessPage(account);
                    } else {
                        System.out.println("You are not eligible for a loan");
                        System.out.println("Age must be over 21 and annual income must be over 20000");
                        printMoreActions(account);
                    }
                } catch (Exception e) {
                    System.out.println("Input was incorrect");
                    printPersonalLoanPage(account);
                }

                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void printStudentLoanPage(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application of Student Loan");
        System.out.println("1 | Apply Loan");
        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1:
                try {
                    System.out.println("Please enter Principal Amount");
                    Double principal = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Please enter the Guarantor's name: ");
                    String guarantorName = scanner.nextLine();
                    System.out.println("Please enter the Guarantor's income: ");
                    Double guarantorIncome = scanner.nextDouble();
                    System.out.println("Please enter the Guarantor's Contact Number: ");
                    int guarantorContactNo = scanner.nextInt();
                    StudyLoan sLoan = new StudyLoan(principal, 0.1f, 24, "Study Loan",
                            guarantorName, 3, guarantorIncome, guarantorContactNo, account, "STU123456",
                            "University ABC", "Pending");
                    if (sLoan.isEligibleForLoan()) {
                        account.addLoan(sLoan);
                        printLoanApplicationSuccessPage(account);
                    } else {
                        System.out.println("You are not eligible for a loan");
                        System.out.println("Principal must be over 11350");
                        printMoreActions(account);
                        break;
                    }
                    //account.addLoan(sLoan);
                    //printLoanApplicationSuccessPage(account);
                } catch (Exception e) {
                    System.out.println("Input was incorrect");
                    printStudentLoanPage(account);
                }

                break;
            case 0:

                printCustomerMenu(account);
        }
    }

    public static void printAdminLoginPage() {
        System.out.println("Admin Login");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter admin ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Admin admin = getAdmin(id);
        printAdminMenu(admin);
        /*
         * if (validateAdmin(id, password)){
         * printAdminMenu(getAdmin(id));
         * }
         * else{
         * System.out.println("Login failed");
         * printLoginPage();
         * }
         */
    }

    public static void printAdminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + admin.getAdminID());
        System.out.println("Select actions:");
        System.out.println("1 | View all accounts");
        System.out.println("2 | View all loans");
        System.out.println("3 | Approve loan");
        System.out.println("4 | Reject loan");
        System.out.println("0 | Logout");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printAllAccounts(admin);
                break;
            case 2:
                printAllLoans(admin);
                break;
            case 3:
                printApproveLoan(admin);
                break;
            case 4:
                printRejectLoan(admin);
                break;
            case 0:
                printLogoutPage();
                break;
            default:
                System.out.println("Invalid input");
                printAdminMenu(admin);
        }
    }

    public static void printAllAccounts(Admin admin) {
        System.out.println("All accounts");
        System.out.println("Account Number");
        for (Account account : accounts) {
            System.out.println(account.getAccountNumber());
        }
        printMoreActions2(admin);
    }

    public static void printAllLoans(Admin admin) {
        System.out.println("All loans");
        System.out.println("Loan ID | Loan Type | Loan Status");
        for (Account account : accounts) {
            for (Loan loan : account.getLoans()) {
                System.out.println(loan.getLoanID() + " \t| " + loan.getLoanType() + "| " + loan.getLoanStatus());
            }
        }
        printMoreActions2(admin);
    }

    public static void printApproveLoan(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter loan ID to approve: ");
        int loanID = scanner.nextInt();
        for (Account account : accounts) {
            for (Loan loan : account.getLoans()) {
                if (loan.getLoanID() == loanID) {
                    loan.approveLoan();
                    account.saveAccount();
                    printMoreActions2(admin);
                }
            }
        }
    }

    public static void printRejectLoan(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter loan ID to reject: ");
        int loanID = scanner.nextInt();
        for (Account account : accounts) {
            for (Loan loan : account.getLoans()) {
                if (loan.getLoanID() == loanID) {
                    loan.rejectLoan();
                    account.saveAccount();
                    printMoreActions2(admin);
                }
            }
        }
    }

    public static void printMoreActions2(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Back to main menu? (Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y") || input.equals("y")) {
            printAdminMenu(admin);
        } else {
            printLogoutPage();
        }
    }

    public static void onStart() {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        // read current json
        File fp = new File("records.json");
        // ArrayList<String> accountList = new ArrayList<String>();
        if (fp.exists()) {
            try {
                Object obj = parser.parse(new FileReader("records.json"));
                jsonObject = (JSONObject) obj;
                String keys = jsonObject.keySet().toString();
                String[] listofkeys = keys.split(",");
                for (int i = 0; i < listofkeys.length; i++) {
                    listofkeys[i] = listofkeys[i].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
                }
                // listofkeys.
                for (String key : listofkeys) {
                    JSONArray accountDetailsArray = (JSONArray) jsonObject.get(key);
                    JSONObject accountDetails = (JSONObject) accountDetailsArray.get(0);
                    JSONArray customerDetailsArray = (JSONArray) accountDetails.get("Customer");
                    JSONObject customerDetails = (JSONObject) customerDetailsArray.get(0);
                    JSONArray currencyArray = (JSONArray) accountDetails.get("Currency");
                    JSONObject currencyDetails = (JSONObject) currencyArray.get(0);
                    JSONArray loanArray = (JSONArray) accountDetails.get("Loan");
                    
                    Date date = new Date(customerDetails.get("DOB").toString());
                    Customer newCustomer = new Customer(customerDetails.get("NRIC").toString(),
                            customerDetails.get("name").toString(), date,
                            Integer.parseInt(customerDetails.get("Contact Number").toString()),
                            customerDetails.get("email").toString(),
                            Integer.parseInt(customerDetails.get("age").toString()),
                            Double.parseDouble(customerDetails.get("income").toString()));
                    Account newAccount = new Account(newCustomer, Integer.parseInt(key),
                            Integer.parseInt(accountDetails.get("PIN").toString()), (Double) currencyDetails.get("SGD"),
                            (Double) currencyDetails.get("EUR"), (Double) currencyDetails.get("JPY"),
                            (Double) currencyDetails.get("MYR"), (Double) currencyDetails.get("USD"));

                    ArrayList<Loan> loans = new ArrayList<>();
                    if (loanArray != null){
                        for (int i = 0; i < loanArray.size(); i++){
                            JSONObject loanDetails = (JSONObject) loanArray.get(i);
                            try{
                                if (loanDetails.get("Loan Type").equals("Business Loan")){

                                
                                    BusinessLoan bloan = new BusinessLoan(loanDetails.get("Business").toString(), 
                                    loanDetails.get("Business Description").toString(), Double.parseDouble(loanDetails.get("Principal").toString()),
                                     0.05f, 12, loanDetails.get("Loan Type").toString(), loanDetails.get("Guarantor Name").toString(),
                                     Double.parseDouble(loanDetails.get("Guarantor ID").toString()),Double.parseDouble(loanDetails.get("Guarantor Income").toString()),
                                     Integer.parseInt(loanDetails.get("Guarantor Contact Number").toString()), newAccount, loanDetails.get("Unique EN").toString(),
                                     Double.parseDouble(loanDetails.get("Business Annual Income").toString()), Integer.parseInt(loanDetails.get("Cash In Flow").toString()),
                                     Integer.parseInt(loanDetails.get("Cash Out Flow").toString()),loanDetails.get("Status").toString());                    
                                    
                                     loans.add(bloan);
                                     }
                                else if(loanDetails.get("Loan Type").equals("Personal Loan"))
                                {
                                    PersonalLoan ploan = new PersonalLoan(5000.0, 
                                    0.1f, 24, loanDetails.get("Loan Type").toString(), loanDetails.get("Guarantor Name").toString(),
                                     Double.parseDouble(loanDetails.get("Guarantor ID").toString()),Double.parseDouble(loanDetails.get("Guarantor Income").toString()),
                                     Integer.parseInt(loanDetails.get("Guarantor Contact Number").toString()), newAccount, loanDetails.get("Status").toString(), Double.parseDouble(loanDetails.get("Personal Income").toString()), 
                                     Double.parseDouble(loanDetails.get("Personal Annual Income").toString()));       
                                     
                                     loans.add(ploan);
                                }
                                else if(loanDetails.get("Loan Type").equals("Study Loan"))
                                {
                                    StudyLoan sloan = new StudyLoan(Double.parseDouble(loanDetails.get("Principal").toString()), 
                                    0.05f, 36, loanDetails.get("Loan Type").toString(), loanDetails.get("Guarantor Name").toString(),
                                     Double.parseDouble(loanDetails.get("Guarantor ID").toString()),Double.parseDouble(loanDetails.get("Guarantor Income").toString()),
                                     Integer.parseInt(loanDetails.get("Guarantor Contact Number").toString()), newAccount, loanDetails.get("Student ID").toString()
                                     ,loanDetails.get("Institution").toString(),loanDetails.get("Status").toString()) ;
        
                                     loans.add(sloan);
                                }
                            }catch (Exception e){
                                System.out.println("Loan is empty");
                            }
                            
                        }
                        for (Loan loan: loans){
                            newAccount.addLoan(loan);
                        }
                    }
                   

                    JSONArray ccArray = (JSONArray) accountDetails.get("CreditCard");
                    if (ccArray != null)
                    {
                        JSONObject ccDetails = (JSONObject) ccArray.get(0);
                    try{
                        CreditCard cc;
                        if (ccDetails.get("Type").toString() == "STUDENT"){
                            cc = new CreditCard(newAccount.getAccountNumber(), newCustomer, CreditCardType.STUDENT,
                        ccDetails.get("Number").toString(), ccDetails.get("CVV").toString(), LocalDate.parse(ccDetails.get("Expiry Date").toString()), 
                        Double.parseDouble(ccDetails.get("Spending Limit").toString()));
                        }
                        else
                        cc = new CreditCard(newAccount.getAccountNumber(), newCustomer, CreditCardType.REGULAR,
                        ccDetails.get("Number").toString(), ccDetails.get("CVV").toString(), LocalDate.parse(ccDetails.get("Expiry Date").toString()), 
                        Double.parseDouble(ccDetails.get("Spending Limit").toString()));
                        newAccount.addCreditCard(cc);
                    }catch (Exception e){
                        System.out.println("No credit card");
                    }
                    }
                    Bank.addAccount(newAccount);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        File adminfp = new File("admin.json");
        if (adminfp.exists()){
            try{
                Object obj = parser.parse(new FileReader("admin.json"));
                jsonObject = (JSONObject) obj;
                Admin admin = new Admin(jsonObject.get("ID").toString(),jsonObject.get("Password").toString());
                Bank.addAdmin(admin);
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            Bank.addAdmin(new Admin("admin", "password"));
        }
        
    }

    public static void saveAdminAccount(){
        
        JSONObject adminJson = new JSONObject();
        String output = "admin.json";
        for (Admin admin : admins){
            adminJson.put("ID", admin.getAdminID());
            adminJson.put("Password", admin.getPassword());
        }
        
        try {
            FileWriter fw = new FileWriter(output);
            fw.write(adminJson.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {

        onStart();

        Bank.printLoginPage();
        // Customer bob = new Customer("S1234567A", "bob", new Date(2000, 11, 1), 12345678, "email@email.com", 30, 4000);
        // Admin admin1 = new Admin("admin", "password");
        // Account bobAccount = new Account(bob, 1, 1234, 1000.0, 0.0, 0.0, 0.0, 0.0);
        // Account bobAccount2 = new Account(bob, 2, 4321, 1000.0, 0.0, 0.0, 0.0, 0.0);
        // Bank.addAccount(bobAccount);
        // Bank.addAccount(bobAccount2);
        // Bank.addAdmin(admin1);
    }

}