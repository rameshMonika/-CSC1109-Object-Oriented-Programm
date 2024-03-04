import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a monthly statement for a customer's account.
 */
public class MonthlyStatement {
    private String statementId; // Unique identifier for the statement
    private Map<String, Transaction> transactionMap; // Map to store transactions associated with the statement
    private Boolean paymentMade = false; // Indicates whether payment has been made for the statement
    private Double totalAmount = 0.00; // Total amount for the statement
    private Date statementDate; // Date of the statement

    /**
     * Constructs a monthly statement object.
     *
     * @param transactionMap Map containing transactions associated with the
     *                       statement.
     */
    public MonthlyStatement(Map<String, Transaction> transactionMap) {
        this.statementId = UUID.randomUUID().toString(); // Generate a unique statement ID
        this.statementDate = new Date(); // Set the statement date to the current date
        this.transactionMap = transactionMap; // Set the transaction map
        for(Map.Entry<String, Transaction> transaction : transactionMap.entrySet()){
            totalAmount += transaction.getValue().getAmount();
        }
    }

    /**
     * Retrieves the date of the statement.
     * 
     * @return The date of the statement.
     */
    public Date getStatementDate() {
        return statementDate;
    }

    /**
     * Retrieves the total amount for the statement.
     * 
     * @return The total amount for the statement.
     */
    public Double getTotalAmount() {

       
        return totalAmount;
    }

    /**
     * Checks if payment has been made for the statement.
     * 
     * @return True if payment has been made, false otherwise.
     */
    public Boolean getPaymentMade() {
        return paymentMade;
    }

    /**
     * Marks the statement as payment made.
     */
    public void paymentMade() {
        this.paymentMade = true;
    }

    /**
     * Retrieves a transaction by its ID.
     * 
     * @param transactionId The ID of the transaction to retrieve.
     * @return The transaction with the specified ID, or null if not found.
     */
    public Transaction getTransaction(String transactionId) {
        return transactionMap.get(transactionId);
    }

    /**
     * Retrieves all transactions associated with the statement.
     * 
     * @return A map containing all transactions associated with the statement.
     */
    public Map<String, Transaction> getAllTransaction() {
        return transactionMap;
    }

    /**
     * Retrieves the ID of the statement.
     * 
     * @return The ID of the statement.
     */
    public String getStatementId() {
        return statementId;
    }
}