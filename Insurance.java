import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

   public int getPolicyNumber() {
    return this.policyNumber;
 }

 public String getPolicyName() {
    return this.policyName;
 }

 public String getPolicyStatus() {
    return this.policyStatus;
 }

 public String getPolicyType() {
    return this.policyType;
 }

 public LocalDate getInception_date() {
    return this.inception_date;
 }

 public LocalDate getMaturity_date() {
    return this.maturity_date;
 }

 public String getPolicyOwner() {
    return this.policyOwner;
 }

 public double getSumAssured() {
    return this.sumAssured;
 }

 public double getPremiumAmount() {
    return this.premiumAmount;
 }

 public String getPremiumFrequency() {
    return this.premiumFrequency;
 }

 public double getTotalPremiumPaid() {
    return this.totalPremiumPaid;
 }

 public void setTotalPremiumPaid(Double amount) {
    totalPremiumPaid = amount;
 }

 public double getTotalWithdrawalAmount() {
    return this.totalWithdrawalAmount;
 }
}
