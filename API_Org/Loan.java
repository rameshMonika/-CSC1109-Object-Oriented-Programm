import static java.lang.Math.pow;

public class Loan {
  // Static variable to generate unique loan IDs
  private static int lID = 0;

  // Instance variables
  private int loanID;
  protected double principal;
  private double balance;
  private float interestRate;
  private int duration;
  private double monthlyPayment;
  private String loanType;
  private Guarantor guarantorDetails;
  private Account account;

  public Loan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails,
      Account account) {
    lID++;
    this.loanID = lID;
    this.principal = principal;
    this.interestRate = interestRate;
    this.duration = duration;
    this.loanType = loanType;
    this.guarantorDetails = guarantorDetails;
    this.balance = principal + principal * interestRate;
    this.monthlyPayment = principal * (interestRate / 12) / (1 - pow(1 + interestRate / 12, -duration));
    this.account = account;
  }

  public double getprincipal() {
    return principal;
  }

  public void setprincipal(double principal) {
    this.principal = principal;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public float getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getLoanID() {
    return loanID;
  }

  public double getMonthlyPayment() {
    return monthlyPayment;
  }

  /**
   * Sets the amount of money to be paid monthlym for the loan
   * 
   * @param monthlyPayment The amount of money to be paid monthly for the loan.
   */

  public void setMonthlyPayment(double monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  /**
   * Gets the type of loan.
   * 
   * @return The type of loan.
   */

  public String getLoanType() {
    return loanType;
  }

  public void displayLoan() {
    System.out.println("Your pending loan amount is:" + this.principal);

  }

  public Guarantor showGuarantorDetails(Guarantor guarantorDetails) {

    System.out.println("Guarantor is " + guarantorDetails.getGuarantorName());

    return guarantorDetails;

  }

}
