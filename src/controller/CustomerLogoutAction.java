package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import formbean.CustomerLoginForm;
import model.Model;



public class CustomerLogoutAction extends Action {

    public CustomerLogoutAction(Model model) {
    }

    public String getName() {
        return "customerLogout.do";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("customer", null);

        request.setAttribute("form", new CustomerLoginForm());
        return "customer_login.jsp";
    }
}