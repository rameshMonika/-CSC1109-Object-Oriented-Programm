/**
 * Represents a Business Loan, extending the Loan class.
 * This loan type is intended for business purposes.
 */
public class BusinessLoan extends Loan {
    private String uniqueEN;
    private String businessType;
    private String businessDescription;
    private int cashInFlow;
    private int cashOutFlow;

    /**
     * Constructs a new BusinessLoan object with the specified parameters.
     *
     * @param businessType        The type of business.
     * @param businessDescription A description of the business.
     * @param principal           The principal amount of the loan.
     * @param interestRate        The interest rate of the loan.
     * @param duration            The duration of the loan.
     * @param loanType            The type of loan.
     * @param guarantorName       The name of the guarantor.
     * @param guarantorID         The ID of the guarantor.
     * @param guarantorIncome     The income of the guarantor.
     * @param guarantorContactNo  The contact number of the guarantor.
     * @param account             The account object associated with the loan.
     * @param uniqueEN            The unique enterprise number associated with the business.
     * @param annualProfit        The annual profit of the business.
     * @param cashInFlow          The cash inflow of the business.
     * @param cashOutFlow         The cash outflow of the business.
     * @param loanStatus          The status of the loan.
     */
    public BusinessLoan(String businessType, String businessDescription, double principal, float interestRate, int duration, String loanType, String guarantorName, double guarantorID, double guarantorIncome, int guarantorContactNo, Account account, String uniqueEN, double annualProfit, int cashInFlow, int cashOutFlow, String loanStatus) {
        super(principal, interestRate, duration, loanType, guarantorName, guarantorID, guarantorIncome, guarantorContactNo, account, loanStatus);
        this.uniqueEN = uniqueEN;
        this.cashInFlow = cashInFlow;
        this.cashOutFlow = cashOutFlow;
        this.businessType = businessType;
        this.businessDescription = businessDescription;
    }

    /**
     * Calculates the cash flow of the business.
     *
     * @return The cash flow of the business.
     */
    public int calcCashFlow() {
        return cashInFlow - cashOutFlow;
    }

    /**
     * Gets the type of business.
     *
     * @return The type of business.
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Gets the description of the business.
     *
     * @return The description of the business.
     */
    public String getBusinessDescription() {
        return businessDescription;
    }

    /**
     * Gets the cash inflow of the business.
     *
     * @return The cash inflow of the business.
     */
    public int getCashInFlow() {
        return cashInFlow;
    }

    /**
     * Gets the cash outflow of the business.
     *
     * @return The cash outflow of the business.
     */
    public int getCashOutFlow() {
        return cashOutFlow;
    }

    /**
     * Gets the unique enterprise number associated with the business.
     *
     * @return The unique enterprise number.
     */
    public String getUniqueEn() {
        return uniqueEN;
    }

    @Override
    public void printLoanDetails() {
        super.printLoanDetails();
        System.out.println("Unique EN: " + this.getUniqueEn());
        System.out.println("Business Type: " + this.getBusinessType());
        System.out.println("Business Description: " + this.getBusinessDescription());
        System.out.println("Cash in: " + this.getCashInFlow());
        System.out.println("Cash out: " + this.getCashOutFlow());
    }

    /**
     * Checks if the business is eligible for the loan based on its annual profit.
     *
     * @return true if the business is eligible for the loan, false otherwise.
     */
    @Override
    public boolean isEligibleForLoan() {
        // Eligibility logic
        return this.principal > 50000 && this.principal < 100000;
    }

    @Override
    public boolean isEligibleForLoan(Customer customer) {
        // Provide an implementation based on the specific eligibility criteria for PersonalLoan
        return true; // Example implementation
    }
}
