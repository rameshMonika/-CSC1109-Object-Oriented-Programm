import java.util.Date;
/**
 * Authors: Darren, Min Xuan, Monika, Teren, Jana, Amanda, Kirby
 * E-mail: -
 * Date: 202402019
 *
 * Description: The Customer class represents the customer's
 * personal information with methods to access and
 * modify the information
 */
public class Customer{
    private String NRIC;
    private String name;
    private Date dateOfBirth;
    private int contactNo;
    private String email;

    public Customer(String NRIC, String name, Date dateOfBirth, int contactNo, String email){
        this.NRIC = NRIC;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.email = email;
    }

    /**
     * Gets the customer's name
     * 
     * @return The customer's name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the customer's name
     * 
     * @param name The customer's name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the customer's NRIC
     * 
     * @return The customer's NRIC
     */
    public String getNRIC(){
        return NRIC;
    }

    /**
     * Gets the customer's Date of Birth
     * 
     * @return The customer's Date of Birth
     */
    public Date getDob(){
        return dateOfBirth;
    }

    /**
     * Sets the customer's Date of Birth
     * 
     * @param dateOfBirth The customer's Date of Birth
     */
    public void setDob(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the customer's Contact Number
     * 
     * @return The customer's Contact Number
     */
    public int getContactNo(){
        return contactNo;
    }

    /**
     * Sets the customer's Contact Number
     * 
     * @param contactNo The customer's Contact Number
     */
    public void setContactNo(int contactNo){
        this.contactNo = contactNo;
    }

    /**
     * Gets the customer's email
     * 
     * @return The customer's email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets the customer's email
     * 
     * @param email The customer's email
     */
    public void setEmail(String email){
        this.email = email;
    }
}