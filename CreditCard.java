/**
 * Description: The CreditCard class represents a credit card
 * with attributes such as card number, spending limit, amount spent
 * as well as methods for payments and reward point calculations.
 */

// Import libraries
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;

public class CreditCard {
    // Attributes
    private String cardNo;                                    // Stores the card number
    private String cardPrepend = "2";
    private int cardLength = 16;
    private LocalDate expiryDate;                           // Stores the expiry date
    private String cvv;                                       // Stores the cvv
    private CreditCardType cardType;                        // Stores the card type
    private Double spendingLimit;                           // Stores the card's spending limit
    private Double totalAmount = 0.00;
    private Double rewardPoints = 0.00;                            // Stores the reward points

    //Associations
    private Customer customer;
    private Account account;
    public HashMap<String, MonthlyStatement> pendingMonthlyStatements;
    public HashMap<String, MonthlyStatement> pastMonthlyStatements;
    public HashMap<String, Transaction> pendingTransaction;

    /**
     * Constructs a Credit Card instance that specifies the details of the credit card.
     *
     * @param customer The customer's details.
     * @param account The account details associated with the customer.
     * @param cardType The card type.
     */
    public CreditCard(Customer customer, Account account, CreditCardType cardType){
        this.customer = customer;
        this.account = account;
        this.cardType = cardType;
        this.cardNo = generateCardNo();
        this.cvv = generateCvv();
        this.expiryDate = generateExpiryDate();
        this.spendingLimit = calcSpendingLimit();
    }

    /**
     * Gets the card number.
     *
     * @return The card number.
     */
    public String getCardNo(){
        return cardNo;
    }

    /**
     * Gets the card's expiry date.
     *
     * @return The card's expiry date.
     */
    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    /**
     * Gets the card's cvv.
     *
     * @return The card's cvv.
     */
    public String getCvv(){
        return cvv;
    }

    /**
     * Gets the card type.
     *
     * @return The card type.
     */
    public CreditCardType getCardType(){
        return cardType;
    }

    /**
     * Gets the card's spending limit.
     *
     * @return The card's spending limit.
     */
    public double getSpendingLimit(){
        return spendingLimit;
    }

    /**
     * Generates a 16-digit number for the credit card.
     *
     * @return The 16-digit card number.
     */
    public String generateCardNo(){
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < this.cardLength - this.cardPrepend.length(); i++) {
            int randomNumber = random.nextInt(9);
            cardNumber.append(randomNumber);
        }

        cardNumber.insert(0, cardPrepend);
        String cardNumberWithoutLastDigit = cardNumber.substring(0,cardNumber.length() - 1);

        int weight = 2;
        int sum = 0;

        for (int i = cardNumberWithoutLastDigit.length() - 1; i >= 0; i--) {
            int digit = weight * Integer.parseInt(cardNumberWithoutLastDigit.substring(i, i + 1));
            sum += digit / 10 + digit % 10;
            weight = (weight == 2) ? 1 : 2;
        }
        int mod10Digit = (10 - (sum % 10)) % 10;
        return cardNo;
    }

    /**
     * Generates a 3-digit Card Verification Value (CVV).
     *
     * @return The 3-digit cvv number.
     */
    public String generateCvv(){
        return (Math.floor(Math.random() * 9) + "").concat(Math.floor(Math.random() * 9) + "").concat(Math.floor(Math.random() * 9) + "");
    }

    /**
     * Generates an expiry date which is 5 years
     * from creation of credit card.
     *
     * @return The card's expiry date.
     */
    public LocalDate generateExpiryDate(){
        LocalDate current = LocalDate.now();
        return current.plusYears(5);
    }

    /**
     * Calculates the credit card's spending limit.
     * Spending limit is 4x the user's income.
     */
    public double calcSpendingLimit(){
        double income = customer.getIncome();
        switch(cardType) {
            case Student:
                this.spendingLimit = 500.00;
                break;

            case Regular:
                if (customer.getAge() > 55) {
                    if (income <= 15000.00) {
                        this.spendingLimit = 2500.00;
                        break;
                    }

                    if (income <= 30000.00) {
                        this.spendingLimit = income * 2;
                        break;
                    }

                    if (income <= 120000.00) {
                        this.spendingLimit = income * 4;
                        break;
                    }
                }

                else {
                    if (income < 30000.00) {
                        this.spendingLimit = income * 2;
                        break;
                    }

                    if (income < 120000.00) {
                        this.spendingLimit = income * 4;
                        break;
                    }
                }
        }

        return spendingLimit;
    }

    /**
     * Get the past monthly statements.
     *
     * @param statementId The statement ID.
     * @return The statement ID.
     */
    public MonthlyStatement getPastMonthlyStatement(String statementId) {
        return pastMonthlyStatements.get(statementId);
    }

    /**
     * Get all the past monthly statements.
     *
     * @return The MonthlyStatement hashmap.
     */
    public HashMap<String, MonthlyStatement> getAllPastMonthlyStatements() {
        return pastMonthlyStatements;
    }

    /**
     * Get the pending monthly statement.
     *
     * @param statementId The statement ID.
     * @return The statement ID.
     */
    public MonthlyStatement getPendingMonthlyStatement(String statementId) {
        return pendingMonthlyStatements.get(statementId);
    }

    /**
     * Get all the pending monthly statements.
     *
     * @return The pending monthly statements.
     */
    public HashMap<String, MonthlyStatement> getAllPendingMonthlyStatements() {
        return pendingMonthlyStatements;
    }

    /**
     * Checks if payment has been made.
     *
     * @param statementId The payment amount.
     */
    public boolean makePayment(String statementId){
        MonthlyStatement statement = this.getPendingMonthlyStatement(statementId);
        Double pendingAmount = statement.getTotalAmount();

        Account balance = Bank.getAccount(this.accountId).getBalanceMap();
        Double balanceAmount = balance.get(Currency.SGD);

        if (balanceAmount > pendingAmount) {
            balance.computeIfPresent(Currency.SGD, (k, v) -> v - pendingAmount);
            statement.paymentMade();
            pendingMonthlyStatements.remove(statementId);
            pastMonthlyStatements.put(statementId, statement);
            return true;
        }

        return false;
    }

    /**
     * Makes a transaction in the credit card.
     *
     * @param transaction The transaction hashmap.
     */
    public void makeTransaction(Transaction transaction){
        totalAmount += transaction.getAmount();
        pendingTransaction.put(transaction.getTransactionId(), transaction);
        rewardPoints += (transaction.getAmount() * 1.4);
    }
}