package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databean.*;
import model.Model;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        Action.add(new EmployeeLoginAction(model));
        Action.add(new CreateAccountAction(model));
        Action.add(new DepositAction(model));
        Action.add(new EmployeeLogoutAction(model));
        Action.add(new CustomerLoginAction(model));
        Action.add(new ViewCustomerAccountAction(model));
        Action.add(new CreateFundsAction(model));
        Action.add(new BuyAction(model));
        Action.add(new RefundAction(model));
        Action.add(new WelcomeAction(model));
        Action.add(new CustomerLogoutAction(model));
//        Action.add(new ToDoListAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    /*
     * Extracts the requested action and (depending on whether the user is
     * logged in) perform it (or make the user login).
     * 
     * @param request
     * 
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        EmployeeCredential user = (EmployeeCredential) session.getAttribute("employee");
        CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");
        CustomerInfo customerInfo = (CustomerInfo) session.getAttribute("customerInfo");
        Funds[] funds = (Funds[]) session.getAttribute("all_funds");
        String action = getActionName(servletPath);

        if (user != null) {
            // Let the logged in user run his chosen action
            return Action.perform(action, request);
        }
        
        if (customer != null) {
            // Let the logged in user run his chosen action
            return Action.perform(action, request);
        }
        
        if (customerInfo != null) {
            // Let the logged in user run his chosen action
            return Action.perform(action, request);
        }
        
        if (funds != null) {
            // Let the logged in user run his chosen action
            return Action.perform(action, request);
        }
        // If the user hasn't logged in, login is the only option
        // Note in this example, register is in the login action.
        if (action.equals("employeeCredential.do")) {
            return Action.perform("employeeCredential.do", request);
        }
        
        if (action.equals("customerLogin.do")) {
            return Action.perform("customerLogin.do", request);
        }
        
        if (action.equals("viewCustomerAccount.do")) {
            return Action.perform("viewCustomerAccount.do", request);
        }
        
        
        
        
        
        
        
        // The not-logged user is trying to execute an action other
        // than login.  This is usually due to his session timing
        // out but he could be fooling around.  Let's give him a
        // stale session error message.  Another option would be
        // to send him to login.jsp, instead.
        return "controller-stale-session.jsp";
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
            d.forward(request, response);
            return;
        }

        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    /*
     * Returns the path component after the last slash removing any "extension"
     * if present.
     */
    private String getActionName(String path) {
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1);
    }
}

