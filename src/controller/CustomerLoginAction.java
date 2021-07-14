package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import databean.CustomerCredential;
import databean.CustomerInfo;
import formbean.CustomerLoginForm;
import model.*;
import model.Model;

public class CustomerLoginAction extends Action{

	private FormBeanFactory<CustomerLoginForm> formBeanFactory = new FormBeanFactory<>(CustomerLoginForm.class);

    private CustomerCredentialDAO customerCredentialDAO;
    private CustomerInfoDAO customerInfoDAO;
    private FundDAO fundDAO;
    public CustomerLoginAction(Model model) {
    	customerCredentialDAO = model.getCustomerCredentialDAO();
    	customerInfoDAO = model.getCustomerInfoDAO();
    	fundDAO = model.getFundDAO();
    }
    
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "customerLogin.do";
	}
	
	public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("customer_email") != null) {
            return "viewCustomerAccount.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new CustomerLoginForm());
        return "customer_login.jsp";
    }
	
	 public String performPost(HttpServletRequest request) {
	        // If user is already logged in, redirect to todolist.do
	        HttpSession session = request.getSession();
	        if (session.getAttribute("customer_email") != null) {
	            return "viewCustomerAccount.do";
	        }

	        try {
	        	CustomerLoginForm form = formBeanFactory.create(request);
	            request.setAttribute("form", form);

	            // Any validation errors?
	            if (form.hasValidationErrors()) {
	                return "customer_login.jsp";
	            }

	           
	            // Look up the user
	            CustomerCredential user = customerCredentialDAO.read(form.getCustomer_email());
	            CustomerInfo userInfo = customerInfoDAO.read(form.getCustomer_email());
	            System.out.println("looked");
	            if (user == null) {
	                form.addFieldError("customer_email", "User Name not found");
	                return "customer_login.jsp";
	            }

	            // Check the password
	            if (!user.getPassword().equals(form.getPassword())) {
	                form.addFieldError("password", "Incorrect password");
	                return "customer_login.jsp";
	            }

	            // Attach (this copy of) the user bean to the session
	            session.setAttribute("customer", user);
	            session.setAttribute("customerInfo", userInfo);
	            session.setAttribute("all_funds", fundDAO.getItems());
	            System.out.println(fundDAO.getItems().length);
	            // If redirectTo is null, redirect to the "todolist" action
	            return "viewCustomerAccount.do";
	        } catch (RollbackException e) {
	            request.setAttribute("error", e.getMessage());
	            return "error.jsp";
	        }
	    }
	
}
