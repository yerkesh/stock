package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;


@FieldOrder("customer_email, name, password, address")
public class CreateCustomerAccountForm extends FormBean {
	private String customer_email;
    private String address;
    private String password;
    private String name;
    private String action;
    
    public String getCustomer_email()  { return customer_email; }
    public String getAddress()  { return address; }
    public String getPassword()  { return password; }
    public String getName()  { return name; }
    
    
    public String getAction()    { return action; }
	
    @Label("Customer Email")
    public void setCustomer_email(String s)  { customer_email = s.trim(); }
    public void setAddress(String s)  { address = s.trim(); }
    
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    public void setName(String s)  { name = s.trim(); }
    
    
    @InputType("button")
    public void setAction(String s)    { action   = s; }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Create")) {
            this.addFormError("Invalid button");
        }
        
        if (customer_email.matches(".*[<>\"].*") || !customer_email.contains("@")) {
            this.addFieldError("customer_email", "Invalid email format!");
        }
        if (password.length() < 3) {
            this.addFieldError("password", "Invalid password length < 3");
        }
    }
}

