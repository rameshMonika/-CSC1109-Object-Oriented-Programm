import java.util.Date;

public class Customer{
    // var name nric
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
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNRIC(){
        return NRIC;
    }
    public Date getDob(){
        return dateOfBirth;
    }
    public void setDob(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public int getContactNo(){
        return contactNo;
    }
    public void setContactNo(int contactNo){
        this.contactNo = contactNo;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}