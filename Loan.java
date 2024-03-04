import static java.lang.Math.pow;

/**
 * Represents a Loan entity.
 * This class encapsulates the attributes and functionalities of a loan.
 */

public class Loan {
  // Static variable to generate unique loan IDs
  private static int lID = 0;

  // Instance variables
  private int loanID;
  /**
   * The principal amount of the loan.
   */
  protected double principal;
  private double balance;
  private float interestRate;
  private int duration;
  private double monthlyPayment;
  private String loanType;
  private Guarantor guarantorDetails;
  private Account account;

  /**
   * Constructs a new Loan object with the specified parameters.
   *
   * @param principal        The principal amount of the loan.
   * @param interestRate     The interest rate of the loan.
   * @param duration         The duration of the loan.
   * @param loanType         The type of loan.
   * @param guarantorDetails The details of the guarantor.
   * @param account          The account object associated with the loan.
   */

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
   * @param guarantorDetails The guarantor object whose details are to be
   *                         displayed.
   * @return The guarantor object whose details are to be displayed.
   */

  public Guarantor showGuarantorDetails(Guarantor guarantorDetails) {

    System.out.println("Guarantor is " + guarantorDetails.getGuarantorName());

    return guarantorDetails;

  }

}
