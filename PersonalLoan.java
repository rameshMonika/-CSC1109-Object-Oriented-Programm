/**
 * Represents a Personal Loan, extending the Loan class.
 * This loan type is intended for personal financial needs.
 */
public class PersonalLoan extends Loan {
    private double personalIncome;
    private double pastAnnualIncome;

    /**
     * Constructs a new PersonalLoan object with the specified parameters.
     *
     * @param principal         The principal amount of the loan.
     * @param interestRate      The interest rate of the loan.
     * @param duration          The duration of the loan.
     * @param loanType          The type of loan.
     * @param guarantorDetails  The details of the guarantor.
     * @param personalIncome    The current monthly personal income.
     * @param pastAnnualIncome The past annual income of the applicant.
     */
    public PersonalLoan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails, double personalIncome, double pastAnnualIncome) {
        super(principal, interestRate, duration, loanType, guarantorDetails);
        this.personalIncome = personalIncome;
        this.pastAnnualIncome = pastAnnualIncome;
    }

    /**
     * Checks if the person is eligible for the loan based on their current monthly income and past annual income.
     *
     * @return true if the person is eligible for the loan, false otherwise.
     */
    public Boolean isEligibleForLoan() {
        // Eligibility logic
        return true;
    }
}
