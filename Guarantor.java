/**
 * Represents a Guarantor entity.
 * This class encapsulates the attributes of a guarantor.
 */
public class Guarantor {
    // Instance variables
    private String guarantorName;
    private Double guarantorID;
    private Double guarantorIncome;
    private int guarantorContactNo;

    /**
     * Constructs a new Guarantor object with the specified parameters.
     *
     * @param guarantorName      The name of the guarantor.
     * @param guarantorID        The ID of the guarantor.
     * @param guarantorIncome    The income of the guarantor.
     * @param guarantorContactNo The contact number of the guarantor.
     */
    public Guarantor(String guarantorName, Double guarantorID, Double guarantorIncome, int guarantorContactNo) {
        this.guarantorName = guarantorName;
        this.guarantorID = guarantorID;
        this.guarantorIncome = guarantorIncome;
        this.guarantorContactNo = guarantorContactNo;
    }

    /**
     * Gets the guarantor name.
     *
     * @return The guarantor name.
     */
    public String getGuarantorName() {
        return guarantorName;
    }

    /**
     * Gets the guarantor ID.
     *
     * @return The guarantor ID.
     */
    public Double getGuarantorID() {
        return guarantorID;
    }

    /**
     * Gets the guarantor income.
     *
     * @return The guarantor income.
     */
    public Double getGuarantorIncome() {
        return guarantorIncome;
    }

    /**
     * Gets the guarantor contact number.
     *
     * @return The guarantor contact number.
     */
    public int getGuarantorContactNo() {
        return guarantorContactNo;
    }
}
