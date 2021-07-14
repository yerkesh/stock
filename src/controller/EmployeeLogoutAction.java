package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import formbean.EmployeeLoginForm;
import model.Model;



public class EmployeeLogoutAction extends Action {

    public EmployeeLogoutAction(Model model) {
    }

    public String getName() {
        return "employeeLogout.do";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("employee", null);

        request.setAttribute("form", new EmployeeLoginForm());
        return "employee_login.jsp";
    }
}