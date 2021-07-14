package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

@FieldOrder("fund_name, shares_number")
public class CustomerFundsForm extends FormBean {
    private String action;
    private String fund_name;
    private String shares_number;
    
    public String getAction() {
        return action;
    }
    
    public String getFund_name() {
        return fund_name;
    }
    
    public String getShares_number() {
        return shares_number;
    }
    
    @InputType("button")
    public void setAction(String action) {
        this.action = action;
    }

    @Label("Fund Name: ")
    public void setFund_name(String item) {
        this.fund_name = item;
    }
    
    @Label("Number of Shares: ")
    public void setShares_number(String item) {
        this.shares_number = item;
    }

    public void validate() {
        super.validate();
        
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Submit")) {
            addFormError("Invalid action: " + action);
        }
        
        if (shares_number.matches(".*[<>\"].*") || shares_number.matches(".*[a-zA-Z].*")) {
            this.addFieldError("shares_number", "Invalid number!");
        }
    }
}