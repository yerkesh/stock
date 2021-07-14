package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.CustomerFunds;


public class CustomerFundsDAO extends GenericDAO<CustomerFunds> {
	public CustomerFundsDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerFunds.class, tableName, cp);
	}

	public void addToFunds(CustomerFunds item) throws RollbackException {
		try {
			Transaction.begin();

			create(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	

	public CustomerFunds[] getItems() throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		CustomerFunds[] items = match();
		
		Arrays.sort(items, (CustomerFunds i1, CustomerFunds i2) -> i1.getId() - i2.getId());

		return items;
	}

}