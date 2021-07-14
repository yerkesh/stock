package controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.*;
import formbean.DepositForm;
import model.CustomerInfoDAO;
import model.Model;

public class DepositAction extends Action{
	
	private FormBeanFactory<DepositForm> formBeanFactory = new FormBeanFactory<>(DepositForm.class); 
	private CustomerInfoDAO customerInfoDAO;
	
	public DepositAction(Model model) {
		customerInfoDAO = model.getCustomerInfoDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "deposit.do";
	}
	
	
	public String performGet(HttpServletRequest request) {
		
		request.setAttribute("form", new DepositForm());
        return "deposit.jsp";
		
	}
	
	public String performPost(HttpServletRequest request) {
		
		try {
			DepositForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "deposit.jsp";
            }

            if (form.getAction().equals("Submit")) {
                
            	// Look up the user
            	CustomerInfo user = customerInfoDAO.read(form.getCustomer_email());
//              System.out.println("looked");
              if (user == null) {
                  form.addFieldError("customer_email", "User Email not found");
                  return "deposit.jsp";
              }

//              session.setAttribute("employee", user);
  //
//              // If redirectTo is null, redirect to the "todolist" action
              CustomerInfo newcus2 = new CustomerInfo();
              newcus2.setCustomer_email(form.getCustomer_email());
              newcus2.setAddress(user.getAddress());
              newcus2.setBalance(form.getBalance());
              newcus2.setName(user.getName());
                try {
                	customerInfoDAO.update(newcus2);

//                    session.setAttribute("employeeId", newemp);
                    return ("deposit.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("customer_email", "A user with this name already exists");
                    return "deposit.jsp";
                }
            }

            // Look up the user
//            CustomerInfo user = customerInfoDAO.read(form.getEmployeeId());
//            System.out.println("looked");
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
//
//            // Attach (this copy of) the user bean to the session
//            session.setAttribute("employee", user);
//
//            // If redirectTo is null, redirect to the "todolist" action
            return "deposit.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }

	}
	
	
	
}
