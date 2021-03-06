/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * implements sql database access for obstetrics
 * 
 * @author wyattmaxey
 */
public class ObstetricsMySQL implements ObstetricsPregnancyData, Serializable {

	/** serial uid */
	private static final long serialVersionUID = -1487996870390116633L;
	
	/** sql statement helper */
	private ObstetricsSQLLoader loader;
	
	/** data source */
	private DataSource ds;
	
	
	/** validates input from forms */
	private ObstetricsValidator validator;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * default constructor
	 * 
	 * @throws DBException
	 */
	public ObstetricsMySQL() throws DBException {
		loader = new ObstetricsSQLLoader();
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		
		validator = new ObstetricsValidator( this.ds );
	}
	
	/**
	 * constructor used for testing
	 * 
	 * @param ds
	 * 		testing datasource
	 * @throws DBException
	 */
	public ObstetricsMySQL( DataSource ds ) throws DBException {
		loader = new ObstetricsSQLLoader();
		this.ds = ds;
		
		validator = new ObstetricsValidator( this.ds );
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getAll()
	 */
	@Override
	public List<ObstetricsPregnancy> getAll() throws DBException {
		List<ObstetricsPregnancy> ret;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM obstetricsData" );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? loader.loadList( rs ) : null;
			rs.close();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getByID(long)
	 */
	@Override
	public ObstetricsPregnancy getByID( long id ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsData WHERE id = ?" );
			ps.setLong( 1, id );
			ResultSet rs = ps.executeQuery();
			ObstetricsPregnancy op = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			ps.close();
			conn.close();
			return op;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#add(java.lang.Object)
	 */
	@Override
	public boolean add( ObstetricsPregnancy op ) throws FormValidationException, DBException {
		PreparedStatement ps = null;
		validator.validate( op );
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("INSERT INTO obstetricsData (pid, initDate, lmp"
					+ ", edd, weeksPregnant, concepYear, totalWeeks, hrsLabor, weightGain, deliveryType, "
					+ "multiplePregnancy, babyCount, current, rhFlag, hpMiscarriage) VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)") , op, true );
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#update(java.lang.Object)
	 */
	@Override
	public boolean update( ObstetricsPregnancy op ) throws DBException, FormValidationException {
		PreparedStatement ps = null;
		validator.validate( op );
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("UPDATE obstetricsData SET initDate=?, lmp=?, edd=?, "
					+ "weeksPregnant=?, concepYear=?, totalWeeks=?, hrsLabor=?, weightGain=?, deliveryType=?, "
					+ "multiplePregnancy=?, babyCount=?, current=?, rhFlag=?, hpMiscarriage=? WHERE id = ?" ), op, false );
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}
	
	public void updatePriorPregnancy(ObstetricsPregnancy op, String date) throws DBException, FormValidationException {
		PreparedStatement ps = null;
		validator.validate( op );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date sqldate = null;
		try {
			sqldate = DATE_FORMAT.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String d = sdf.format(sqldate);
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("UPDATE obstetricsData SET initDate=?, lmp=?, edd=?, "
					+ "weeksPregnant=?, concepYear=?, totalWeeks=?, hrsLabor=?, weightGain=?, deliveryType=?, "
					+ "multiplePregnancy=?, babyCount=?, current=?, rhFlag=?, hpMiscarriage=? WHERE pid=? and current=? and initDate='" + d + "'" ), op, false );
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		//return true;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData#getObstetricsPregnancy(long, java.sql.Date)
	 */
	@Override
	public ObstetricsPregnancy getObstetricsPregnancy( long pid, String initDate ) throws DBException {
		Date dateInit = null;
		try {
			dateInit = new Date( DATE_FORMAT.parse( initDate ).getTime() );
		} catch ( ParseException e ) {
			//Some kind of error handling done in validator
			dateInit = null;
			e.printStackTrace();
		}
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsData WHERE pid = ? and initDate = ?" );
			ps.setLong( 1, pid );
			ps.setDate( 2, dateInit );
			ResultSet rs = ps.executeQuery();
			ObstetricsPregnancy op = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			ps.close();
			conn.close();
			return op;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData#getPastObstetricsPregnanciesForPatient(long)
	 */
	@Override
	public List<ObstetricsPregnancy> getPastObstetricsPregnanciesForPatient( long pid ) throws DBException {
		List<ObstetricsPregnancy> res;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM obstetricsData WHERE pid=? AND current=? ORDER BY initDate DESC");
				ps.setLong( 1, pid );
				ps.setBoolean( 2, false );
				ResultSet rs = ps.executeQuery();
				res = rs.next() ? loader.loadList( rs ) : null;
				rs.close();
				ps.close();
				conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData#getCurrentObstetricsPregnancy(long)
	 */
	@Override
	public ObstetricsPregnancy getCurrentObstetricsPregnancy( long pid ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsData WHERE pid=? AND current=?" );
			ps.setLong( 1, pid );
			ps.setBoolean( 2, true );
			ResultSet rs = ps.executeQuery();
			ObstetricsPregnancy op = rs.next() ? loader.loadSingle( rs ) : null;
			if(op == null){
				op = new ObstetricsPregnancy();
			}
			rs.close();
			ps.close();
			conn.close();
			return op;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}
	
	

}
