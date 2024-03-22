/**
 * Represents a Study Loan, extending the Loan class.
 * This loan type is intended for educational purposes.
 */
public class StudyLoan extends Loan {
    private String studentID;
    private String institution;


    /**
     * Constructs a new StudyLoan object with the specified parameters.
     *
     * @param principal        The principal amount of the loan.
     * @param interestRate     The interest rate of the loan.
     * @param duration         The duration of the loan.
     * @param loanType         The type of loan.
     * @param guarantorDetails The details of the guarantor.
     * @param account          The account object associated with the loan.
     * @param studentID        The ID of the student.
     * @param institution      The educational institution associated with the loan.
     */
    public StudyLoan(double principal, float interestRate, int duration, String loanType,  String guarantorName, double guarantorID, double guarantorIncome,int guarantorContactNo,
            Account account, String studentID, String institution,String loanStatus) {
        super(principal, interestRate, duration, loanType,guarantorName,guarantorID,guarantorIncome, guarantorContactNo , account, loanStatus);
        this.studentID = studentID;
        this.institution = institution;
    }


    public String getStudentID() {
        return studentID;
    }

    public String getInstitution() {
        return institution;
    }

   


    /**
     * Checks if the student is eligible for the loan based on having a student ID
     * and being associated with an institution.
     *
     * @return true if the student is eligible for the loan, false otherwise.
     */

   
    // public Boolean isEligibleForLoan() {
    //     // Eligibility logic
    //     if (this.principal > 11350) {
    //         return true;
    //     }


    //     return false;
    // }
}



