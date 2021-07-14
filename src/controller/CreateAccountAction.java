package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.CustomerCredential;
import databean.CustomerInfo;
import formbean.CreateCustomerAccountForm;
import model.CustomerCredentialDAO;
import model.CustomerInfoDAO;
import model.Model;

public class CreateAccountAction extends Action {
	private FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = new FormBeanFactory<>(CreateCustomerAccountForm.class);

    private CustomerCredentialDAO customerCredentialDAO;
    private CustomerInfoDAO customerInfoDAO;

    public CreateAccountAction(Model model) {
    	customerCredentialDAO = model.getCustomerCredentialDAO();
    	customerInfoDAO = model.getCustomerInfoDAO();
    }

    public String getName() {
        return "customerCredential.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("customer_email") != null) {
            return "customerCredential.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new CreateCustomerAccountForm());
        return "create_account.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("customer_email") != null) {
            return "customerCredential.do";
        }

        try {
        	CreateCustomerAccountForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "create_account.jsp";
            }

            if (form.getAction().equals("Create")) {
            	CustomerCredential newcus = new CustomerCredential();
                newcus.setCustomer_email(form.getCustomer_email());
                newcus.setPassword(form.getPassword());
                
                CustomerInfo newcus2 = new CustomerInfo();
                newcus2.setCustomer_email(form.getCustomer_email());
                newcus2.setAddress(form.getAddress());
                newcus2.setBalance(0+"");
                newcus2.setName(form.getName());
                try {
                	customerCredentialDAO.create(newcus);
                	customerInfoDAO.create(newcus2);
//                    session.setAttribute("customer_email", newcus);
                    return ("customerCredential.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("customer_email", "A user with this name already exists");
                    return "create_account.jsp";
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
            return "customerCredential.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
