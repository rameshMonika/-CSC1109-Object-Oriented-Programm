import static java.lang.Math.pow;

public class Loan {

  private static int lID = 0;

  private int loanID;
  private double principal;
  private double balance;
  private float interestRate;
  private int duration;
  private double monthlyPayment;
  private String loanType;
  private Guarantor guarantorDetails;

  public Loan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails){
    lID++;
    this.loanID = lID;
    this.principal = principal;
    this.interestRate = interestRate;
    this.duration = duration;
    this.loanType = loanType;
    this.guarantorDetails = guarantorDetails;
    this.balance = principal + principal * interestRate;
    this.monthlyPayment = principal * (interestRate/12)/(1-pow(1+interestRate/12, -duration));
  }
  /**
   * Gets the loan amount owned by customer.
   * 
   * @return The loan amount owned by customer.
   */

  public double getprincipal() {
    return principal;
  }

  /**
   * Sets the loan amount owned.
   * 
   * @param principal The loan amount owned.
   */

  public void setprincipal(double principal) {
    this.principal = principal;
  }

  /**
   * Gets the balance owned by customer.
   * 
   * @return The balance owned by customer.
   */

  public double getBalance() {
    return balance;
  }

  /**
   * Sets the balance amount owned by customer.
   * 
   * @param balance The balance amount owned by customer.
   */

  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Gets the interest rate of the loan.
   * 
   * @return The interest rate of the loan.
   */

  public float getInterestRate() {
    return interestRate;
  }

  /**
   * Sets the interest rate of the loan.
   * 
   * @param interestRate The interest rate of the loan.
   */

  public void setInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * Gets the interest rate of the loan.
   * 
   * @return The interest rate of the loan.
   */

  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration given to return the amount owned
   * 
   * @param duration The duration given to return the amount owned.
   */

  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Gets the loan ID.
   * 
   * @return The loan ID.
   */

  public int getLoanID() {
    return loanID;
  }

  /**
   * Gets the amount of money to be paid monthly for the loan.
   * 
   * @return The amount of money to be paid monthly .
   */

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

  /**
   * Displays the loan amount.
   * 
   * Displays the loan amount.
   */

  public void displayLoan() {
    System.out.println("Your pending loan amount is:" + this.principal);

  }

  /**
   * Displays the details of a guarantor.
   *
   * @param guarantorDetails The guarantor object whose details are to be displayed.
   */

  public void showGuarantorDetails(Guarantor guarantorDetails) {

    System.out.println("Guarantor is " + guarantorDetails.getGuarantorName());

  }

  /**
   * Check if customer is eligible for loan.
   * 
   * @return if customer is eligible for loan.
   */

  public Boolean isEligibleForLoan() {
    return true;
  }

}
