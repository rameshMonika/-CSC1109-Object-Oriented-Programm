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
     * @param guarantorName    The name of the guarantor.
     * @param guarantorID      The ID of the guarantor.
     * @param guarantorIncome  The income of the guarantor.
     * @param guarantorContactNo The contact number of the guarantor.
     * @param account          The account object associated with the loan.
     * @param loanStatus       The status of the loan.
     * @param personalIncome   The current monthly personal income.
     * @param annualIncome     The past annual income of the applicant.
     */
    public PersonalLoan(double principal, float interestRate, int duration, String loanType,
                        String guarantorName, double guarantorID, double guarantorIncome, int guarantorContactNo,
                        Account account, String loanStatus, double personalIncome, double annualIncome) {
        super(principal, interestRate, duration, loanType, guarantorName, guarantorID, guarantorIncome, guarantorContactNo, account, loanStatus);
        this.personalIncome = personalIncome;
        this.annualIncome = annualIncome;
    }

    /**
     * Gets the current monthly personal income.
     *
     * @return The current monthly personal income.
     */
    public double getPersonalIncome() {
        return personalIncome;
    }

    /**
     * Gets the past annual income of the applicant.
     *
     * @return The past annual income of the applicant.
     */
    public double getAnnualIncome() {
        return annualIncome;
    }

    @Override
    public void printLoanDetails() {
        super.printLoanDetails();
        System.out.println("Personal Income: " + this.getPersonalIncome());
        System.out.println("Annual Income: " + this.getAnnualIncome());
    }

    /**
     * Checks if the person is eligible for the loan based on their current monthly
     * income and past annual income.
     *
     * @param customer The customer applying for the loan.
     * @return true if the person is eligible for the loan, false otherwise.
     */
    @Override
    public boolean isEligibleForLoan(Customer customer) {
        // Eligibility logic
        return this.annualIncome > 20000 && customer.getAge() > 21;
    }
    @Override
    public boolean isEligibleForLoan() {
    // Provide an implementation based on the specific eligibility criteria for PersonalLoan
    return true; // Example implementation
}
}