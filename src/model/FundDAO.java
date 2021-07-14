package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import databean.Funds;

public class FundDAO extends GenericDAO<Funds> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Funds.class, tableName, cp);
	}
	
	public Funds[] getItems() throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		Funds[] items = match();

		return items;
	}
}
