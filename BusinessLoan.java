/**
 * Represents a Business Loan, extending the Loan class.
 * This loan type is intended for business purposes.
 */
public class BusinessLoan extends Loan {
    private String uniqueEN;
    // private double annualProfit;
    private String businessType;
    private String businessDescription;
    private int cashInFlow;
    private int cashOutFlow;


    /**
     * Constructs a new BusinessLoan object with the specified parameters.
     *
     * @param principal        The principal amount of the loan.
     * @param interestRate     The interest rate of the loan.
     * @param duration         The duration of the loan.
     * @param loanType         The type of loan.
     * @param guarantorDetails The details of the guarantor.
     * @param account          The account object associated with the loan.
     * @param uniqueEN         The unique enterprise number associated with the
     *                         business.
     * @param annualProfit     The annual profit of the business.
     */
   public BusinessLoan(String businessType, String businessDescription, double principal, float interestRate, int duration, String loanType, String guarantorName, double guarantorID, double guarantorIncome, int guarantorContactNo, Account account, String uniqueEN, double annualProfit, int cashInFlow, int cashOutFlow, String loanStatus) {
    super(principal, interestRate, duration, loanType, guarantorName, guarantorID, guarantorIncome, guarantorContactNo, account, loanStatus);
    this.uniqueEN = uniqueEN;
    this.cashInFlow = cashInFlow;
    this.cashOutFlow = cashOutFlow;
    this.businessType = businessType;
    this.businessDescription = businessDescription;
}



    public int calcCashFlow(){
     
        int cashFlow= cashInFlow-cashOutFlow;


        return cashFlow;


    }


    public String getBusinessType(){
        return businessType;
    }


    public String getBusinessDescription(){
        return businessDescription;


    }


    public int getCashInFlow(){
        return cashInFlow;
    }


    public int getCashOutFlow(){
        return cashOutFlow;


    }


    /**
     * Checks if the business is eligible for the loan based on its annual profit.
     *
     * @return true if the business is eligible for the loan, false otherwise.
     */

  
    // public Boolean isEligibleForLoan() {
    //     // Eligibility logic
    //     if (this.principal > 50000 && this.principal< 100000) {
    //         return true;
    //     }
    //     return true;
    // }
}



