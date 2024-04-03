import static java.lang.Math.pow;


/**
 * Represents a Loan entity.
 * This class encapsulates the attributes and functionalities of a loan.
 */


public abstract class Loan {
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
        this.loanStatus = loanStatus;
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


/**
 * Gets the current status of the loan.
 *
 * @return The current status of the loan.
 */
public String getLoanStatus() {
    return this.loanStatus;
}

/**
 * Sets the status of the loan.
 *
 * @param loanStatus The status to be set for the loan.
 */
public void setLoanStatus(String loanStatus) {
    this.loanStatus = loanStatus;
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
 * Gets the account associated with the loan.
 *
 * @return The account associated with the loan.
 */
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
 * Approves the loan and sets its status to "Approved".
 * Prints a confirmation message.
 */
public void approveLoan() {
    if(!this.getLoanStatus().equals("Pending")){
      throw new IllegalStateException("Loan cannot be approved.");
    }
    this.setLoanStatus("Approved");
    System.out.println("Loan with ID " + this.getLoanID() + " has been approved.");
}

/**
 * Rejects the loan and sets its status to "Rejected".
 * Prints a rejection message.
 */
public void rejectLoan() {
    if(!this.getLoanStatus().equals("Pending")){
      throw new IllegalStateException("Loan cannot be rejected.");
    }
    this.setLoanStatus("Rejected");
    System.out.println("Loan with ID " + this.getLoanID() + " has been rejected.");
}

/**
 * Displays the details of the loan, including ID, principal amount, duration, loan type,
 * balance, monthly payment, guarantor name, guarantor ID, guarantor income, guarantor contact number,
 * and eligibility for loan approval.
 */
public void printLoanDetails() {
    System.out.println("Loan ID: " + this.getLoanID());
    System.out.println("Principal: " + this.getprincipal());
    System.out.println("Duration: " + this.getDuration());
    System.out.println("Loan Type: " + this.getLoanType());
    System.out.println("Balance: " + this.getBalance());
    System.out.println("Monthly Payment: " + this.getMonthlyPayment());
    System.out.println("Guarantor Name: " + this.getGuarantorName());
    System.out.println("Guarantor ID: " + this.getGuarantorID());
    System.out.println("Guarantor Income: " + this.getGuarantorIncome());
    System.out.println("Guarantor Contact no: " + this.getGuarantorContactNo());
    System.out.println("Is eligible for loan: " + this.isEligibleForLoan());
}




    public abstract boolean isEligibleForLoan() ;

    public abstract boolean isEligibleForLoan(Customer customer) ;




}