import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Author: Darren, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 20240219
 *
 * Description: The Insurance class represents a basic insurance policy with policy details.
 * 
 */

public class Insurance {
   private String policyName;
   private String policyStatus;
   private String policyType;
   private String policyOwner;
   private String premiumFrequency;
   private int policyNumber;
   private LocalDate inception_date;
   private LocalDate maturity_date;
   private double sumAssured;
   private double premiumAmount;
   private double totalPremiumPaid;
   private double totalWithdrawalAmount;
   static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

   public Insurance(String policyName, String policyType, String policyOwner, String premiumFrequency, int policyNumber, LocalDate inception_date,
    LocalDate maturity_date, double sumAssured, double premiumAmount, double totalPremiumPaid, double totalWithdrawalAmount) {
      this.policyNumber = policyNumber;
      this.policyName = policyName;
      this.policyStatus = "Inforce";
      this.policyType = policyType;
      this.inception_date = inception_date;
      this.maturity_date = maturity_date;
      this.policyOwner = policyOwner;
      this.sumAssured = sumAssured;
      this.premiumAmount = premiumAmount;
      this.premiumFrequency = premiumFrequency;
      this.totalPremiumPaid = totalPremiumPaid;
      this.totalWithdrawalAmount = totalWithdrawalAmount;
   }
   /**
    * Gets the policy number of the insurance policy.
    * @return The policy number.
    */
   public int getPolicyNumber() {
      return this.policyNumber;
   }
   /**
    * Gets the policy name of the insurance policy.
   * @return The policy name.
   */
   public String getPolicyName() {
      return this.policyName;
   }
   /*
   * Gets the policy status of the insurance policy.
   * @return The policy status.
   */
   public String getPolicyStatus() {
      return this.policyStatus;
   }
   /**
    * Gets the policy type of the insurance policy.
    * @return The policy type.
    */
   public String getPolicyType() {
      return this.policyType;
   }
   /**
    * Gets the inception date of the insurance policy.
    * @return The inception date.
    */
   public LocalDate getInception_date() {
      return this.inception_date;
   }
   /**
    * Gets the maturity date of the insurance policy.
    * @return The maturity date.
    */
   public LocalDate getMaturity_date() {
      return this.maturity_date;
   }
   /**
    * Gets the policy owner of the insurance policy.
    * @return The policy owner.
    */
   public String getPolicyOwner() {
      return this.policyOwner;
   }
   /**
    * Gets the sum assured of the insurance policy.
    * @return The sum assured.
    */
   public double getSumAssured() {
      return this.sumAssured;
   }
   /**
    * Gets the premium amount of the insurance policy.
    * @return The premium amount.
    */
   public double getPremiumAmount() {
      return this.premiumAmount;
   }
   /**
    * Gets the premium frequency of the insurance policy.
    * @return The premium frequency.
    */
   public String getPremiumFrequency() {
      return this.premiumFrequency;
   }
   /**
    * Gets the total premium paid of the insurance policy.
    * @return The total premium paid.
    */
   public double getTotalPremiumPaid() {
      return this.totalPremiumPaid;
   }
   /**
    * Sets the total premium paid of the insurance policy.
    * @param amount The total premium paid.
    */
   public void setTotalPremiumPaid(Double amount) {
      totalPremiumPaid = amount;
   }
   /**
    * Gets the total withdrawal amount of the insurance policy.
    * @return The total withdrawal amount.
    */
   public double getTotalWithdrawalAmount() {
      return this.totalWithdrawalAmount;
   }

   public void makePayment(Account account, double amount) {
      if (amount < 0){
         System.out.println("Invalid Amount");
      }
      else if (account.getBalance() < amount) {
         System.out.println("Insufficient Funds");
      }
      Double balance = account.getBalance();
      balance -= amount;
      account.setBalance(balance);
      Double premiumPaid = this.getTotalPremiumPaid();
      this.setTotalPremiumPaid(premiumPaid + amount);
      System.out.println("Payment Successful");
   }
}
