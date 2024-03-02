public class BusinessLoan extends Loan{
    private String uniqueEN;
    private Double annualProfit;

    /**
    * Check if business is elgible for loan using annualProfit
    * 
    * @return if business is eligible for loan.
    */
    public Boolean isEligibleForLoan(){
        //Eligibility logic
        return true;
    }
}

