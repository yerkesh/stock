package controller;

import javax.servlet.http.HttpServletRequest;


import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.Funds;
import formbean.CreateFundForm;
import model.FundDAO;
import model.Model;

public class CreateFundsAction extends Action{
	
	private FormBeanFactory<CreateFundForm> formBeanFactory = new FormBeanFactory<>(CreateFundForm.class);

    private FundDAO fundDAO;

    public CreateFundsAction(Model model) {
    	fundDAO = model.getFundDAO();
    }

	@Override
	public String getName() {
		return "createFund.do";
	}
	
public String performGet(HttpServletRequest request) {
		
		request.setAttribute("form", new CreateFundForm());
        return "create_fund.jsp";
		
	}
	
	public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do

        try {
        	CreateFundForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "create_fund.jsp";
            }

            if (form.getAction().equals("Create")) {
            	Funds fund = new Funds();
                fund.setFund_name(form.getFund_name());
                fund.setPrice(form.getPrice());
               
                try {
                	fundDAO.create(fund);
//                    session.setAttribute("customer_email", newcus);
                	request.setAttribute("all_funds", fundDAO.getItems());
                    return ("customerCredential.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("fund_name", "A fund with this name already exists");
                    return "create_fund.jsp";
                }
            }

            // Look up the user
//            CustomerCredential user = CustomerCredentialDAO.read(form.getEmployeeId());
//
//            if (user == null) {
//                form.addFieldError("employeeId", "User Name not found");
//                return "employee_login.jsp";
//            }
//
//            // Check the password
//            if (!user.getPassword().equals(form.getPassword())) {
//                form.addFieldError("password", "Incorrect password");
//                return "employee_login.jsp";
//            }

            // Attach (this copy of) the user bean to the session
//            session.setAttribute("customer_login", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "createFund.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
	
	

}
