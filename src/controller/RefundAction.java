package controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import databean.CustomerCredential;
import databean.CustomerFunds;
import databean.CustomerInfo;
import formbean.CustomerFundsForm;
import formbean.IdForm;
import model.CustomerFundsDAO;
import model.CustomerInfoDAO;
import model.Model;

public class RefundAction extends Action{

	private FormBeanFactory<IdForm> formBeanFactory = new FormBeanFactory<>(IdForm.class);
    private CustomerFundsDAO customerFundsDAO;
    private CustomerInfoDAO customerInfoDAO;

    public RefundAction(Model model) {
        customerFundsDAO = model.getCustomerFundsDAO();
        customerInfoDAO = model.getCustomerInfoDAO();
    }
	
	@Override
	public String getName() {
		return "refund.do";
	}
	
	public String performPost(HttpServletRequest request) {
        IdForm form = formBeanFactory.create(request);
        
        if (form.hasValidationErrors()) {
            return "error.jsp";
        }

        try {
        	CustomerInfo customerInfo = customerInfoDAO.read(((CustomerCredential) request.getSession().getAttribute("customer")).getCustomer_email());
        	CustomerFunds customerFunds = customerFundsDAO.read(form.getIdAsInt());
        	
        	double balance = Double.parseDouble(customerInfo.getBalance());
        	double balance_refunded = Double.parseDouble(customerFunds.getTotal_value());
        	customerInfo.setBalance((balance + balance_refunded) + "");
        	
        	customerInfoDAO.update(customerInfo);
            customerFundsDAO.delete(form.getIdAsInt());
            
            request.setAttribute("customerInfo", customerInfo);
            request.setAttribute("items", customerFundsDAO.getItems());
            request.setAttribute("form", new CustomerFundsForm());
            return "customer_view_account.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
	

}
