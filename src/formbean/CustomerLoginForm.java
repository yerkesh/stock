package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;



@FieldOrder("customer_email, password")
public class CustomerLoginForm extends FormBean {
	private String customer_email;
    private String password;
    private String action;
    
    public String getCustomer_email()  { return customer_email; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
	
    @Label("Customer Email")
    public void setCustomer_email(String s)  { customer_email = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Login")) {
            this.addFormError("Invalid button");
        }
        
    }
}
