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
  private String loanStatus;

   // Guarantor details
  private String guarantorName;
  private double guarantorID;
  private double guarantorIncome;
  private int guarantorContactNo;
  

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

  
    /**
     * Constructs a new Loan object with the specified parameters.
     *
     * @param principal        The principal amount of the loan.
     * @param interestRate     The interest rate of the loan.
     * @param duration         The duration of the loan.
     * @param loanType         The type of loan.
     * @param guarantorName    The name of the guarantor.
     * @param guarantorID      The ID of the guarantor.
     * @param guarantorIncome  The income of the guarantor.
     * @param guarantorContactNo The contact number of the guarantor.
     * @param account          The account object associated with the loan.
     * @param loanStatus       The status of the loan.
     */
    public Loan(double principal, float interestRate, int duration, String loanType,
                String guarantorName, double guarantorID, double guarantorIncome, int guarantorContactNo,
                Account account, String loanStatus) {
        lID++;
        this.loanID = lID;
        this.principal = principal;
        this.interestRate = interestRate;
        this.duration = duration;
        this.loanType = loanType;
        this.guarantorName = guarantorName;
        this.guarantorID = guarantorID;
        this.guarantorIncome = guarantorIncome;
        this.guarantorContactNo = guarantorContactNo;
        this.balance = principal + principal * interestRate;
        this.monthlyPayment = principal * (interestRate / 12) / (1 - Math.pow(1 + interestRate / 12, -duration));
        this.account = account;
        this.loanStatus = "Pending";
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
    return this.loanID;
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

  public String getLoanStatus(){
    return this.loanStatus;
  }

  public void setLoanStatus(String loanStatus){
    this.loanStatus=loanStatus;
  }

  /**
   * Gets the type of loan.
   * 
   * @return The type of loan.
   */

  public String getLoanType() {
    return loanType;
  }

  public Account getAccount() {
    return account;
  }


  /**
     * Gets the name of the guarantor.
     *
     * @return The name of the guarantor.
     */
    public String getGuarantorName() {
        return guarantorName;
    }

    /**
     * Gets the ID of the guarantor.
     *
     * @return The ID of the guarantor.
     */
    public double getGuarantorID() {
        return guarantorID;
    }

    /**
     * Gets the income of the guarantor.
     *
     * @return The income of the guarantor.
     */
    public double getGuarantorIncome() {
        return guarantorIncome;
    }

    /**
     * Gets the contact number of the guarantor.
     *
     * @return The contact number of the guarantor.
     */
    public int getGuarantorContactNo() {
        return guarantorContactNo;
    }



 /**
 * Displays the details of the guarantor associated with the loan.
 *
 * @return A string containing the details of the guarantor.
 */
public String showGuarantorDetails() {
    StringBuilder details = new StringBuilder();
    details.append("Guarantor Name: ").append(guarantorName).append("\n");
    details.append("Guarantor ID: ").append(guarantorID).append("\n");
    details.append("Guarantor Income: ").append(guarantorIncome).append("\n");
    details.append("Guarantor Contact Number: ").append(guarantorContactNo).append("\n");
    return details.toString();
}


}
