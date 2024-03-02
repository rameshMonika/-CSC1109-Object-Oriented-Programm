public class StudyLoan extends Loan{
    private String studentID;
    private String institution;

    /**
    * Check if student is elgible for loan if person has studentID and instituition
    * 
    * @return if Student is eligible for loan.
    */
    public Boolean isEligibleForLoan(){
        //Eligibility logic
        return true;
    }
}