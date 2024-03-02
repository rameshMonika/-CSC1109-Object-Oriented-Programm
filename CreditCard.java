/**
 * </p>
 * Description: The CreditCard class is
 */

import java.util.Date;
import java.util.Random;

public class CreditCard {
    // Attributes
    private long cardNo;
    private Date expiryDate;
    private long cvv;
    private String cardType;
    private double spendingLimit;
    private double spentAmount;
    private double paymentMade;
    private double minPayment = 200.0;
    private double rewardPoints;
    private double availableBalance;

    //Classes
    private Customer customer;
    private Account account;

    // Constructor
    public CreditCard(Customer customer, Account account, String cardType, Date expiryDate){
        this.customer = customer;
        this.account = account;
        this.cardNo = generateCardNo();
        this.expiryDate = expiryDate;
        this.cvv = generateCvv();
        this.cardType = cardType;
        this.spendingLimit = calcSpendingLimit();
        //setSpentAmount(4000.0);
        this.rewardPoints = 0;
    }

    public long getCardNo(){
        return cardNo;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public long getCvv(){
        return cvv;
    }

    public String getCardType(){
        return cardType;
    }

    public double getSpendingLimit(){
        return spendingLimit;
    }

    public double getSpentAmount(){
        return spentAmount;
    }

    public void setSpentAmount(double spentAmount){
        this.spentAmount = spentAmount;
    }

    public double getPaymentMade(){
        return paymentMade;
    }

    public void setPaymentMade(double paymentMade){
        this.paymentMade = paymentMade;
    }

    public long generateCardNo(){
        Random random = new Random();
        long cardNo = 0;
        for (int i = 0; i < 16; i++){
            cardNo = cardNo * 10 + random.nextInt(10);
        }
        return cardNo;
    }

    public long generateCvv(){
        Random random = new Random();
        long cvv = 0;
        for (int i = 0; i < 3; i++){
            cvv = cvv * 10 + random.nextInt(10);
        }
        return cvv;
    }

    public int calcSpendingLimit(){
        int income = customer.getIncome();
        return 4*income;
    }

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

    public double calcRewardPoints(double spentAmount){
        return spentAmount * 1.4;
    }

    public void DisplayInfo(){
        System.out.print("\n\nCredit Card Information\n");
        System.out.println("Card Number: " + this.cardNo);
        System.out.println("Card Type: " + this.cardType);
        System.out.println("Card Verification Value: " + this.cvv);
        System.out.print("Spending Limit: $" + this.spendingLimit);
        System.out.print("\nSpent Amount: $" + this.spentAmount);
        System.out.print("\nReward Points: " + calcRewardPoints(this.spentAmount));
    }
}