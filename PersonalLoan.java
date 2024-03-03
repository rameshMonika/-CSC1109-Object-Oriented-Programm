/**
 * Represents a Personal Loan, extending the Loan class.
 * This loan type is intended for personal financial needs.
 */
public class PersonalLoan extends Loan {
    private double personalIncome;
    private double annualIncome;

    /**
     * Constructs a new PersonalLoan object with the specified parameters.
     *
     * @param principal        The principal amount of the loan.
     * @param interestRate     The interest rate of the loan.
     * @param duration         The duration of the loan.
     * @param loanType         The type of loan.
     * @param guarantorDetails The details of the guarantor.
     * @param account          The details of the guarantor.
     * @param personalIncome   The current monthly personal income.
     * @param pastAnnualIncome The past annual income of the applicant.
     */
    public PersonalLoan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails,
            Account account, double personalIncome, double annualIncome) {
        super(principal, interestRate, duration, loanType, guarantorDetails, account);
        this.personalIncome = personalIncome;
        this.annualIncome = annualIncome;
    }

    /**
     * Checks if the person is eligible for the loan based on their current monthly
     * income and past annual income.
     *
     * @return true if the person is eligible for the loan, false otherwise.
     */
    public Boolean isEligibleForLoan(Customer customer) {
        // Eligibility logic
        if (this.annualIncome > 20000 && customer.getAge() > 21) {
            return true;
        }

        return false;

    }
}
