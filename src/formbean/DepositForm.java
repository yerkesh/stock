package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("balance, customer_email")
public class DepositForm extends FormBean{
	private String balance;
    private String customer_email;
    private String action;
    
    public String getBalance()  { return balance; }
    public String getCustomer_email()  { return customer_email; }
    public String getAction()    { return action; }
	
    public void setBalance(String s)  { balance = s.trim(); }
    
    public void setCustomer_email(String s)  { customer_email = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Submit")) {
            this.addFormError("Invalid button");
        }
        
        if (balance.matches(".*[<>\"].*") || balance.matches(".*[a-zA-Z].*")) {
            this.addFieldError("balance", "Invalid balance type!");
        }
    }
}
