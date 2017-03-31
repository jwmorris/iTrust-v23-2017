/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * @author wyatt
 *
 */
public class ChildbirthMySQL implements ChildbirthData, Serializable {

	private static final long serialVersionUID = 6390008413116740347L;
	
	private ChildbirthLoaderMySQL childbirthLoader;
	
	private BabyLoaderMySQL babyLoader;
	
	private ChildbirthValidator childbirthValidator;
	
	private BabyValidator babyValidator;
	
	private DataSource ds;
	
	public ChildbirthMySQL() throws DBException {
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			System.out.println( "It's a naming exception" );
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		childbirthLoader = new ChildbirthLoaderMySQL();
		babyLoader = new BabyLoaderMySQL();
		childbirthValidator = new ChildbirthValidator();
		babyValidator = new BabyValidator();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getAll()
	 */
	@Override
	public List<Childbirth> getAll() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getByID(long)
	 */
	@Override
	public Childbirth getByID(long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#add(java.lang.Object)
	 */
	@Override
	public boolean add(Childbirth addObj) throws FormValidationException, DBException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public long addReturnsGeneratedId(Childbirth cb) throws DBException, FormValidationException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#update(java.lang.Object)
	 */
	@Override
	public boolean update(Childbirth updateObj) throws DBException, FormValidationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Baby> getBabiesForChildbirth(long childbirthId) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Childbirth> getChildbirthsForPatient(long pid) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Baby getBaby(long childbirthId, int multiNum) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBaby(Baby baby) throws DBException, FormValidationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBaby(Baby baby) throws DBException, FormValidationException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
