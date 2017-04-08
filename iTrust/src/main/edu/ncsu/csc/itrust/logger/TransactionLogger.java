package edu.ncsu.csc.itrust.logger;

import javax.xml.bind.DataBindingException;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.TransactionDAO;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

/**
 * Singleton provider of the transaction logging mechanism.
 * 
 * @author mwreesjo
 *
 */
public class TransactionLogger {

	/** The singleton instance of this class. */
	private static TransactionLogger singleton = null;

	/** The DAO which exposes logging functionality to the singleton */
	TransactionDAO dao;

	private TransactionLogger() {
		dao = DAOFactory.getProductionInstance().getTransactionDAO();
	}
	
	private TransactionLogger( int i ) {
		dao = TestDAOFactory.getTestInstance().getTransactionDAO();
	}

	/**
	 * @return Singleton instance of this transaction logging mechanism.
	 */
	public static synchronized TransactionLogger getInstance() {
		if (singleton == null)
			singleton = new TransactionLogger();
		return singleton;
	}

	/**
	 * Logs a transaction. @see {@link TransactionDAO#logTransaction}
	 */
	public void logTransaction(TransactionType type, Long loggedInMID, Long secondaryMID, String addedInfo) {
		if ( type == null || loggedInMID == null || addedInfo == null )
			return;
		try {
			dao.logTransaction(type, loggedInMID, secondaryMID, addedInfo);
		} catch ( DBException e ) {
			try {
				singleton = new TransactionLogger( 0 );
				dao.logTransaction( type, loggedInMID, secondaryMID, addedInfo );
			} catch ( DBException e1 ) {
				return;
			}
			return;
		}
	}
}