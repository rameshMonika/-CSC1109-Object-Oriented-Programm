/**
 * </p>
 * Description: The CreditCard class represents a credit card
 * with attributes such as card number, spending limit, amount spent
 * as well as methods for payments and reward point calculations.
 */

// Import libraries
import java.time.LocalDate;
import java.util.Random;

public class CreditCard {
    // Attributes
    private long cardNo;                                    // Stores the card number
    private LocalDate expiryDate;                           // Stores the expiry date
    private long cvv;                                       // Stores the cvv
    private String cardType;                                // Stores the card type
    private double spendingLimit;                           // Stores the card's spending limit
    private double spentAmount;                             // Stores the amount spent on card
    private double paymentMade;                             // Stores the payment amount towards the spent amount
    private double minPayment = 200.0;                      // Stores the minimum payment to be made
    private double rewardPoints;                            // Stores the reward points

    //Associations
    private Customer customer;
    private Account account;

    /**
     * Constructs a Credit Card instance that specifies the details of the credit card.
     *
     * @param customer The customer's details.
     * @param account The account details associated with the customer.
     * @param cardType The card type.
     */
    public CreditCard(Customer customer, Account account, String cardType){
        this.customer = customer;
        this.account = account;
        this.cardType = cardType;
        this.cardNo = generateCardNo();
        this.cvv = generateCvv();
        this.expiryDate = generateExpiryDate();
        this.spendingLimit = calcSpendingLimit();
        this.rewardPoints = 0;
    }

    /**
     * Gets the card number.
     *
     * @return The card number.
     */
    public long getCardNo(){
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
    public long getCvv(){
        return cvv;
    }

    /**
     * Gets the card type.
     *
     * @return The card type.
     */
    public String getCardType(){
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
     * Gets the spent amount on the card.
     *
     * @return The spent amount.
     */
    public double getSpentAmount(){
        return spentAmount;
    }

    /**
     * Gets the amount to make payment.
     *
     * @return The payment made.
     */
    public double getPaymentMade(){
        return paymentMade;
    }

    /**
     * Sets the payment amount.
     *
     * @param paymentMade The payment made.
     */
    public void setPaymentMade(double paymentMade){
        this.paymentMade = paymentMade;
    }

    /**
     * Generates a 16-digit number for the credit card.
     *
     * @return The 16-digit card number.
     */
    public long generateCardNo(){
        Random random = new Random();
        long cardNo = 0;
        for (int i = 0; i < 16; i++){
            cardNo = cardNo * 10 + random.nextInt(10);
        }
        return cardNo;
    }

    /**
     * Generates a 3-digit Card Verification Value (CVV).
     *
     * @return The 3-digit cvv number.
     */
    public long generateCvv(){
        Random random = new Random();
        long cvv = 0;
        for (int i = 0; i < 3; i++){
            cvv = cvv * 10 + random.nextInt(10);
        }
        return cvv;
    }

    /**
     * Generates an expiry date which is 5 years
     * from creation of credit card.
     *
     * @return The card's expiry date.
     */
    public LocalDate generateExpiryDate(){
        LocalDate current = LocalDate.now();
        LocalDate expiryDate = current.plusYears(5);
        return expiryDate;
    }

    /**
     * Calculates the credit card's spending limit.
     * Spending limit is 4x the user's income.
     *
     * @return The spending limit.
     */
    public int calcSpendingLimit(){
        int income = customer.getIncome();
        return 4*income;
    }

    /**
     * Makes payment towards the spent amount balance.
     *
     * @param paymentMade The payment amount.
     */
    public void makePayment(double paymentMade){
        double balance = account.getBalance();
        if (balance < paymentMade) {
            System.out.print("Insufficient funds. Please deposit funds");
        }
        else if (paymentMade < minPayment)
        {
            System.out.print("Minimum payment is $200");
        }
        account.setBalance(balance - paymentMade);
        spentAmount -= paymentMade;
    }

    /**
     * Calculates the reward points based on the spent amount.
     * Reward points are 1.4 per $1 spent.
     *
     * @param spentAmount
     * @return The reward points.
     */
    public double calcRewardPoints(double spentAmount){
        rewardPoints = spentAmount * 1.4;
        return rewardPoints;
    }

    /**
     * Displays an overview of the credit card information
     * such as the card number, card type, spending limit, spent amount and reward points.
     */
    public void DisplayInfo(){
        System.out.print("\n\nCredit Card Information\n");
        System.out.println("Card Number: " + this.cardNo);
        System.out.println("Card Type: " + this.cardType);
        System.out.println("Card Verification Value: " + this.cvv);
        System.out.println("Card Expiry Date (YYYY/MM/DD): " + this.expiryDate);
        System.out.print("Spending Limit: $" + this.spendingLimit);
        System.out.print("\nSpent Amount: $" + this.spentAmount);
        System.out.print("\nReward Points: " + calcRewardPoints(this.spentAmount));
    }
}