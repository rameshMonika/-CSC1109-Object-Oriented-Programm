public class Loan {
    private double loanAmount;
    private float interestRate;
    private int duration;
    private int loanID;
    private double monthlyPayment;
    private String loanType;
    private String guarantorName;
    private Double guarantorID;
    private Double guarantorIncome;
    private int guarantorContactNo;


    /**
      * Gets the loan amount owned by customer.
      * 
      * @return The loan amount owned by customer.
    */

    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the loan amount owned.
     *  
     * @param loanAmount The loan amount owned.
     */

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
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
     * Sets the loan id
     *  
     * @param duration The loan id.
     */

    public void setLoanID(int loanID) {
        this.loanID = loanID;
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
     * Sets the amount of money to be paid monthly for the loan
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
     * Sets the type of loan
     *  
     * @param loanType the type of loan.
     */

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
      * Gets the guarantor name.
      * 
      * @return The guarantor name
    */

    public String getGuarantorName() {
        return guarantorName;
    }

       /**
     * Sets the guarantor name
     *  
     * @param guarantorName the guarantor name.
     */

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    /**
      * Gets the guarantor id.
      * 
      * @return The guarantor id.
    */

    public Double getGuarantorID() {
        return guarantorID;
    }

      /**
     * Sets the guarantor ID
     *  
     * @param guarantorID the guarantor ID.
     */

    public void setGuarantorID(Double guarantorID) {
        this.guarantorID = guarantorID;
    }

    /**
      * Gets the guarantor income.
      * 
      * @return The  guarantor income.
    */

    public Double getGuarantorIncome() {
        return guarantorIncome;
    }

      /**
     * Sets the guarantor income
     *  
     * @param guarantorIncome the guarantor income.
     */

    public void setGuarantorIncome(Double guarantorIncome) {
        this.guarantorIncome = guarantorIncome;
    }

    /**
      * Gets the guarantor contact number.
      * 
      * @return The guarantor contact number.
    */

    public int getGuarantorContactNo() {
        return guarantorContactNo;
    }

    /**
     * Sets the guarantor contact number
     *  
     * @param guarantorContactNo the guarantor contact number.
     */

    public void setGuarantorContactNo(int guarantorContactNo) {
        this.guarantorContactNo = guarantorContactNo;
    }

      /**
      * Displays the loan amount.
      * 
      *  Displays the loan amount.
    */

    public void displayLoan(){
        System.out.println("Your pending loan amount is:"+this.loanAmount);

    }

     /**
      * Check if customer is eligible for loan.
      * 
      * @return if customer is eligible for loan.
    */

    public Boolean isEligibleForLoan(){
        return true;
    }
    
}
