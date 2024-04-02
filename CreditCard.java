/**
 * Description: The CreditCard class represents a credit card
 * with attributes such as card number, spending limit, amount spent
 * as well as methods for payments and reward point calculations.
 */


 import java.time.LocalDate;
 import java.util.HashMap;
 import java.util.Random;
 
 /**
  * The CreditCard class represents a credit card with attributes such as card
  * number, spending limit, amount spent as well as methods for payments and
  * reward point calculations.
  */
 public class CreditCard {
     private String cardNo;                                    // Stores the card number
     private String cardPrepend = "2";
     private int cardLength = 16;
     private LocalDate expiryDate;                           // Stores the expiry date
     private String cvv;                                       // Stores the cvv
     private CreditCardType cardType;                        // Stores the card type
     private Double spendingLimit;                           // Stores the card's spending limit
     private Double totalAmount = 0.00;
     private Double rewardPoints = 0.00;                            // Stores the reward points
     private int accountId;
 
     private Customer customer;
     public HashMap<String, MonthlyStatement> pendingMonthlyStatements = new HashMap<>();
     public HashMap<String, MonthlyStatement> pastMonthlyStatements = new HashMap<>();
     public HashMap<String, Transaction> pendingTransaction = new HashMap<>();
 
     /**
      * Default constructor that initializes the CreditCard object with a customer,
      * account number, and card type.
      *
      * @param accountNumber The account number.
      * @param customer      The customer of the account.
      * @param cardType      The card type.
      */
     public CreditCard(int accountNumber, Customer customer, CreditCardType cardType) {
         try {
             this.customer = customer;
             this.accountId = accountNumber;
             this.cardType = cardType;
             this.cardNo = generateCardNo();
             this.cvv = generateCvv();
             this.expiryDate = generateExpiryDate();
             this.spendingLimit = calcSpendingLimit();
         } catch (Exception e) {
             // Handle any exceptions that may occur during initialization
             System.err.println("Error occurred during CreditCard initialization: " + e.getMessage());
         }
     }

     public CreditCard(int accountNumber, Customer customer, CreditCardType cardType, String cardNo, String cvv, LocalDate expiryDate, Double spendingLimit) {
        try {
            this.customer = customer;
            this.accountId = accountNumber;
            this.cardType = cardType;
            this.cardNo = cardNo;
            this.cvv = cvv;
            this.expiryDate = expiryDate;
            this.spendingLimit = spendingLimit;
        } catch (Exception e) {
            // Handle any exceptions that may occur during initialization
            System.err.println("Error occurred during CreditCard initialization: " + e.getMessage());
        }
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
     public String generateCardNo() {
         try {
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
             return cardNumber.toString();
         } 
         catch (Exception e) {
             System.err.println("Error occurred during card number generation: " + e.getMessage());
         }
         return null; // Return null if an exception occurs
     }
 
 
     /**
      * Generates a 3-digit Card Verification Value (CVV).
      *
      * @return The 3-digit cvv number.
      */
     public String generateCvv() {
         try {
             return (Math.floor(Math.random() * 9) + "").concat(Math.floor(Math.random() * 9) + "").concat(Math.floor(Math.random() * 9) + "");
         } 
         catch (Exception e) {
             System.err.println("Error occurred during CVV generation: " + e.getMessage());
         }
         return null; // Return null if an exception occurs
     }
 
     /**
      * Generates an expiry date which is 5 years
      * from creation of credit card.
      *
      * @return The card's expiry date.
      */
     public LocalDate generateExpiryDate() {
         try {
             LocalDate current = LocalDate.now();
             return current.plusYears(5);
         } catch (Exception e) {
             // Handle any exceptions that may occur during expiry date generation
             System.err.println("Error occurred during expiry date generation: " + e.getMessage());
         }
         return null; // Return null if an exception occurs
     }
 
     /**
      * Calculates the credit card's spending limit.
      * Spending limit is 4x the user's income.
      * 
      * @return The spending limit.
      */
     public double calcSpendingLimit(){
         double income = customer.getIncome();
         switch(cardType) {
             case STUDENT:
                 this.spendingLimit = 500.00;
                 break;
 
             case REGULAR:
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
      * @param statement The payment amount.
      * @param account The account.
      * @return true if payment has been made, false otherwise.
      */
     //public boolean makePayment(String statementId){
     public boolean makePayment(MonthlyStatement statement, Account account){
         //MonthlyStatement statement = this.getPendingMonthlyStatement(statementId);
         Double pendingAmount = statement.getTotalAmount();
 
         Double balanceAmount = account.getBalance(Currency.SGD);
 
         if (balanceAmount > pendingAmount) {
             account.getBalanceMap().computeIfPresent(Currency.SGD, (k, v) -> v - pendingAmount);
             statement.paymentMade();
             pastMonthlyStatements.put(statement.getStatementId(), statement);
             pendingTransaction.remove(statement.getStatementId());
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