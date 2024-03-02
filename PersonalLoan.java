public class PersonalLoan extends Loan{
    private double personalIncome;
    private double pastAnnualIncome;

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