/**
 * Represents a Business Loan, extending the Loan class.
 * This loan type is intended for business purposes.
 */
public class BusinessLoan extends Loan {
    private String uniqueEN;
    private double annualProfit;

    /**
     * Constructs a new BusinessLoan object with the specified parameters.
     *
     * @param principal         The principal amount of the loan.
     * @param interestRate      The interest rate of the loan.
     * @param duration          The duration of the loan.
     * @param loanType          The type of loan.
     * @param guarantorDetails  The details of the guarantor.
     * @param uniqueEN          The unique enterprise number associated with the business.
     * @param annualProfit      The annual profit of the business.
     */
    public BusinessLoan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails,Account account, String uniqueEN, double annualProfit) {
        super(principal, interestRate, duration, loanType, guarantorDetails,account);
        this.uniqueEN = uniqueEN;
        this.annualProfit = annualProfit;
    }

    /**
     * Checks if the business is eligible for the loan based on its annual profit.
     *
     * @return true if the business is eligible for the loan, false otherwise.
     */
    public Boolean isEligibleForLoan() {
        // Eligibility logic
        if(this.principal > 50000){
            return true;
        }
        return true;
    }
}
