package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("fund_name, price")
public class CreateFundForm extends FormBean{
	private String price;
    private String fund_name;
    private String action;
    
    public String getPrice()  { return price; }
    public String getFund_name()  { return fund_name; }
    public String getAction()    { return action; }
	
    public void setPrice(String s)  { price = s.trim(); }
    
    public void setFund_name(String s)  { fund_name = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Create")) {
            this.addFormError("Invalid button");
        }
        
        if (price.matches(".*[<>\"].*") || price.matches(".*[a-zA-Z].*")) {
            this.addFieldError("price", "Invalid price!");
        }
    }
}