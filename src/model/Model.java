package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;


public class Model {
//	private ItemDAO itemDAO;
	private EmployeeCredentialDAO employeeCredentialDAO;
//	private CustomerInfoDAO customerInfoDAO;
	private CustomerCredentialDAO customerCredentialDAO;
	private CustomerInfoDAO customerInfoDAO;
	private CustomerFundsDAO customerFundsDAO;
	private FundDAO fundDAO;
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			employeeCredentialDAO  = new EmployeeCredentialDAO(pool, "employee_credential");
			customerCredentialDAO = new CustomerCredentialDAO(pool, "customer_credential");
			customerInfoDAO = new CustomerInfoDAO(pool, "customer_info");
			fundDAO = new FundDAO(pool, "funds");
			customerFundsDAO = new CustomerFundsDAO(pool, "customer_funds");
//			customerInfoDAO = new CustomerInfoDAO (pool,"customer_info");
//			itemDAO = new ItemDAO(pool, "to do list");
		} catch (DAOException e) { 
			throw new ServletException(e);
		}
	}
	
	public CustomerCredentialDAO getCustomerCredentialDAO()  { return customerCredentialDAO; }
	public EmployeeCredentialDAO getEmployeeCredentialDAO()  { return employeeCredentialDAO; }
	public CustomerInfoDAO getCustomerInfoDAO()  { return customerInfoDAO; }
	public FundDAO getFundDAO()  { return fundDAO; }
	public CustomerFundsDAO getCustomerFundsDAO()  { return customerFundsDAO; }
}
