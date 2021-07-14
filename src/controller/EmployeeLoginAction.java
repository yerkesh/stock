package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.*;
import formbean.*;
import model.*;


public class EmployeeLoginAction extends Action {
    private FormBeanFactory<EmployeeLoginForm> formBeanFactory = new FormBeanFactory<>(EmployeeLoginForm.class);

    private EmployeeCredentialDAO employeeCredentialDAO;

    public EmployeeLoginAction(Model model) {
    	employeeCredentialDAO = model.getEmployeeCredentialDAO();
    }

    public String getName() {
        return "employeeCredential.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("employeeId") != null) {
            return "customerCredential.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new EmployeeLoginForm());
        return "employee_login.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("employeeId") != null) {
            return "customerCredential.do";
        }

        try {
        	EmployeeLoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "employee_login.jsp";
            }

            if (form.getAction().equals("Create")) {
                EmployeeCredential newemp = new EmployeeCredential();
                newemp.setEmployeeId(form.getEmployeeId());
                newemp.setPassword(form.getPassword());
                try {
                	employeeCredentialDAO.create(newemp);

                    session.setAttribute("employeeId", newemp);
                    return ("customerCredential.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("employeeId", "A user with this name already exists");
                    return "employee_login.jsp";
                }
            }

            // Look up the user
            EmployeeCredential user = employeeCredentialDAO.read(form.getEmployeeId());
            System.out.println("looked");
            if (user == null) {
                form.addFieldError("employeeId", "User Name not found");
                return "employee_login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "employee_login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("employee", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "customerCredential.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}

