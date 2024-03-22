import java.util.ArrayList;

public class Admin {
    private String name;
    private String loginID;
    private String password;

    public Admin(String name, String loginID, String password) {
        this.name = name;
        this.loginID = loginID;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
}
