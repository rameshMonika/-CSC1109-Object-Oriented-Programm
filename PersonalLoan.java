public class PersonalLoan extends Loan{
    private Double personalIncome;
    private Double pastAnnualIncome;

    /**
    * Check if person is elgible for loan using current monthly and pastAnnualIncome
    * 
    * @return if person is eligible for loan.
    */
    public Boolean isEligibleForLoan(){
        //Eligibility logic
        return true;
    }
}