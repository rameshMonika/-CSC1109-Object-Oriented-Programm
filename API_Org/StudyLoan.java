public class StudyLoan extends Loan {
    private String studentID;
    private String institution;

    public StudyLoan(double principal, float interestRate, int duration, String loanType, Guarantor guarantorDetails,
            Account account, String studentID, String institution) {
        super(principal, interestRate, duration, loanType, guarantorDetails, account);
        this.studentID = studentID;
        this.institution = institution;
    }

    public Boolean isEligibleForLoan() {
        // Eligibility logic
        if (this.principal > 11350) {
            return true;
        }

        return false;
    }
}
