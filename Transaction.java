import java.util.Date;
import java.util.UUID;

/**
 * Represents a financial transaction.
 */
public class Transaction {
    private String transactionId; // Unique identifier for the transaction
    private Date dateTime; // Date and time of the transaction
    private Double amount; // Amount of the transaction

    /**
     * Constructs a transaction object with the specified amount.
     * @param amount The amount of the transaction.
     */
    public Transaction(Double amount) {
        this.amount = amount; // Set the transaction amount
        this.transactionId = UUID.randomUUID().toString(); // Generate a unique transaction ID
        this.dateTime = new Date(); // Set the transaction date and time to the current date and time
    }

    /**
     * Retrieves the ID of the transaction.
     * @return The ID of the transaction.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the ID of the transaction.
     * @param transactionId The ID of the transaction to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Retrieves the date and time of the transaction.
     * @return The date and time of the transaction.
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the transaction.
     * @param dateTime The date and time of the transaction to set.
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retrieves the amount of the transaction.
     * @return The amount of the transaction.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     * @param amount The amount of the transaction to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}