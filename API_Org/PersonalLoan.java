public class PersonalLoan extends Loan {
    private double personalIncome;
    private double annualIncome;

    public PersonalLoan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails,
            Account account, double personalIncome, double annualIncome) {
        super(principal, interestRate, duration, loanType, guarantorDetails, account);
        this.personalIncome = personalIncome;
        this.annualIncome = annualIncome;
    }

    public Boolean isEligibleForLoan(Customer customer) {
        // Eligibility logic
        if (this.annualIncome > 20000 && customer.getAge() > 21) {
            return true;
        }

        return false;

    }
}
