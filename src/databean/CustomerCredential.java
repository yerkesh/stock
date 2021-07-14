package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_email")
public class CustomerCredential {
    private String customer_email;
    private String password;
    

    public String getPassword()        { return password; }
    public String getCustomer_email()        { return customer_email; }
    

    public void setPassword(String s)  { password = s;    }
    public void setCustomer_email(String s)  { customer_email = s;    }
    
}