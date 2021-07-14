package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.*;
//FIX TYPE
public class CustomerInfoDAO extends GenericDAO<CustomerInfo> {
	public CustomerInfoDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerInfo.class, tableName, cp);
	}
}
