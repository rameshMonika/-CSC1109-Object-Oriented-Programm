/**
 * Represents a Study Loan, extending the Loan class.
 * This loan type is intended for educational purposes.
 */
public class StudyLoan extends Loan {
    private String studentID;
    private String institution;
    private static final float STUDY_LOAN_INTEREST_RATE = 0.02f;

    /**
     * Constructs a new StudyLoan object with the specified parameters.
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
     * @param studentID        The ID of the student.
     * @param institution      The educational institution associated with the loan.
     * @param loanStatus       The status of the loan.
     */
    public StudyLoan(double principal,  int duration, String loanType,
                     String guarantorName, double guarantorID, double guarantorIncome, int guarantorContactNo,
                     Account account, String studentID, String institution, String loanStatus) {
        super(principal, STUDY_LOAN_INTEREST_RATE, duration, loanType, guarantorName, guarantorID, guarantorIncome, guarantorContactNo, account, loanStatus);
        this.studentID = studentID;
        this.institution = institution;
    }

    /**
     * Gets the ID of the student.
     *
     * @return The ID of the student.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Gets the educational institution associated with the loan.
     *
     * @return The educational institution associated with the loan.
     */
    public String getInstitution() {
        return institution;
    }

    @Override
    public void printLoanDetails() {
        super.printLoanDetails();
        System.out.println("Student ID: " + this.getStudentID());
        System.out.println("Institution: " + this.getInstitution());
    }

    /**
     * Checks if the student is eligible for the loan based on having a student ID
     * and being associated with an institution.
     *
     * @return true if the student is eligible for the loan, false otherwise.
     */
    @Override
    public boolean isEligibleForLoan() {
        // Eligibility logic
        return this.principal > 11350;
    }

    @Override
    public boolean isEligibleForLoan(Customer customer) {
      
        return true; // Example implementation
    }
}
