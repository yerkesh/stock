package controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import formbean.CustomerFundsForm;
import model.CustomerFundsDAO;
import model.Model;

public class ViewCustomerAccountAction extends Action{

	private CustomerFundsDAO customerFundsDAO;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "viewCustomerAccount.do";
	}
	
	
	public ViewCustomerAccountAction(Model model) {
    	customerFundsDAO = model.getCustomerFundsDAO();
    }
	
	public String performGet(HttpServletRequest request) {
        return performPost(request);
    }
	
	public String performPost(HttpServletRequest request) {
		try {
            request.setAttribute("items", customerFundsDAO.getItems());
            request.setAttribute("form",  new CustomerFundsForm());
            return ("customer_view_account.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
        
    }
}
