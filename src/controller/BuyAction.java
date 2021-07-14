package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import databean.CustomerFunds;
import databean.CustomerInfo;
import databean.Funds;
import databean.CustomerCredential;
import formbean.CustomerFundsForm;
import model.CustomerFundsDAO;
import model.CustomerInfoDAO;
import model.FundDAO;
import model.Model;


public class BuyAction extends Action {
    private FormBeanFactory<CustomerFundsForm> formBeanFactory = new FormBeanFactory<>(CustomerFundsForm.class);

    private CustomerFundsDAO customerFundsDAO;
    private FundDAO fundDAO;
    private CustomerInfoDAO customerInfoDAO;
    public BuyAction(Model model) {
    	customerFundsDAO = model.getCustomerFundsDAO();
    	fundDAO = model.getFundDAO();
    	customerInfoDAO = model.getCustomerInfoDAO();
    }

    public String getName() {
        return "customerFunds.do";
    }
    
    public String performGet(HttpServletRequest request) {
		
		request.setAttribute("form", new CustomerFundsForm());
        return "buy.jsp";
	}

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	
        try {
        	CustomerFundsForm form = formBeanFactory.create(request);
        	request.setAttribute("form", form);
        	
        	if (form.hasValidationErrors()) {
                return "buy.jsp";
            }
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                request.setAttribute("items", customerFundsDAO.getItems());
                return "buy.jsp";
            }

            CustomerFunds bean = new CustomerFunds();
            bean.setFund_name(form.getFund_name());
            bean.setShares_number(form.getShares_number());
            bean.setCustomer_email(((CustomerCredential) request.getSession().getAttribute("customer")).getCustomer_email());
            
            Funds funds = fundDAO.read(form.getFund_name());
            CustomerInfo customerInfo = customerInfoDAO.read(((CustomerCredential) request.getSession().getAttribute("customer")).getCustomer_email());
            
            if (funds == null) {
                form.addFieldError("fund_name", "Fund Name not found");
                return "buy.jsp";
            }
            
            double number = Double.parseDouble(form.getShares_number());
            double price = Double.parseDouble(funds.getPrice());
            double balance = Double.parseDouble(customerInfo.getBalance());
            double total = number*price;
            
            bean.setTotal_value(total+"");
            // Check the password
            if (balance < (number*price)) {
                form.addFieldError("shares_number", "Not enough balance! You need " + (balance - total) + " more.");
                return "buy.jsp";
            }
            
            
            else if (form.getAction().equals("Submit")) {
                customerFundsDAO.addToFunds(bean);
                customerInfo.setBalance((balance-total)+"");
                customerInfoDAO.update(customerInfo);
                session.setAttribute("customerInfo", customerInfo);
            }

            request.setAttribute("form", new CustomerFundsForm());
            request.setAttribute("items", customerFundsDAO.getItems());

            return "buy.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
