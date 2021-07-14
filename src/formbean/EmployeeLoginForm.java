package formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("employeeId,password")
public class EmployeeLoginForm extends FormBean {
	private String employeeId;
    private String password;
    private String action;
    
    public String getEmployeeId()  { return employeeId; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
	
    public void setEmployeeId(String s)  { employeeId = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Login") && !action.equals("Register")) {
            this.addFormError("Invalid button");
        }
        
        if (employeeId.matches(".*[<>\"].*")) {
            this.addFieldError("userName", "May not contain angle brackets or quotes");
        }
    }
}
