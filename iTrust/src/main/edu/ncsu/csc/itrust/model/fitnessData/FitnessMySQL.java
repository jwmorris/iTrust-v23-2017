package edu.ncsu.csc.itrust.model.fitnessData;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * fitness data access via mysql
 * 
 * @author wyattmaxey
 */
@ManagedBean
public class FitnessMySQL implements FitnessData, Serializable {
	@Resource(name = "jdbc/itrust2")
	private static final long serialVersionUID = 475580799056914445L;
	
	/** sql statement helper */
	private FitnessSQLLoader loader;
	
	/** data source */
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * default constructor
	 * 
	 * @throws DBException
	 */
	public FitnessMySQL() throws DBException {
		loader = new FitnessSQLLoader();
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
	}
	
	public FitnessMySQL(DataSource ds) throws DBException {
		loader = new FitnessSQLLoader();
		this.ds = ds;
	}
	
	@Override
	public List<Fitness> getAll() throws DBException {
		List<Fitness> ret;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM fitnessData" );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? loader.loadList( rs ) : null;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

	@Override
	public Fitness getByID( long id ) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Adds a new fitness data row or if one exists for that patient on that date it will update that row.
	 */
	@Override
	public boolean add( Fitness f ) throws DBException {
		PreparedStatement ps = null;
		Connection conn = null;
		try { 
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("INSERT INTO fitnessData (pid, calories, steps"
					+ ", distance ,floors, minsSed, minsLA, minsFA, minsVA, activeCals, activeHours, hrLow," 
					+ " hrHigh, hrAvg, uvExposure, fitnessDate) VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " 
					+ "ON DUPLICATE KEY UPDATE calories=?, steps=?, distance=?, floors=?, minsSed=?," 
					+" minsLA=?, minsFA=?, minsVA=?, activeCals=?, activeHours=?, hrLow=?, hrHigh=?, hrAvg=?, uvExposure=?")
					, f, true );
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	@Override
	public boolean update( Fitness f ) throws DBException, FormValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("UPDATE fitnessData SET calories=?"
					+ ", steps=?, distance=?, floors=?, minsSed=?, minsLA=?, minsFA=?, minsVA=?, activeCals=?, "
					+ "hrLow=?, hrHigh=?, hrAvg=?, activeHours=?, uvExposure=?, fitnessDate=?" +
					" where pid=? and fitnessDate=?")
					, f, false );
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public Fitness getFitnessData( String pid, String date ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fitnessData WHERE PID = ? and fitnessDate = ?" );
			Date dateSQL = null;
			try {
				Long.parseLong( pid );
			} catch ( Exception e ) {
				return null;
			}
			try {
				dateSQL = new Date( DATE_FORMAT.parse( date ).getTime() );
			} catch ( ParseException e ) {
				dateSQL = null;
			}
			ps.setString( 1, pid );
			ps.setDate( 2, dateSQL );
			ResultSet rs = ps.executeQuery();
			Fitness f = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			ps.close();
			conn.close();
			return f;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	@Override
	public List<Fitness> getFitnessDataForPatient( String pid ) throws DBException {
		List<Fitness> res;
		try ( Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fitnessData WHERE PID = ?" ) ) {
				ps.setString( 1, pid );
				ResultSet rs = ps.executeQuery();
				res = rs.next() ? loader.loadList( rs ) : null;
				rs.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return res;
	}

	@Override
	public List<Fitness> getFitnessDataForPatientDates(String pid, String startDate, String endDate) throws DBException {
		List<Fitness> res;
		try ( Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fitnessData WHERE PID = ? and fitnessDate BETWEEN ? and ? ORDER BY fitnessDate ASC" ) ) {
				Date startDateSQL = null;
				Date endDateSQL = null;
				try {
					Long.parseLong( pid );
				} catch ( Exception e ) {
					return null;
				}
				try {
					startDateSQL = new Date( DATE_FORMAT.parse( startDate ).getTime() );
					endDateSQL = new Date( DATE_FORMAT.parse( endDate ).getTime() );
				} catch ( ParseException e ) {
					startDateSQL = null;
					endDateSQL = null;
				}
				
				ps.setString(1, pid);
				ps.setDate(3, endDateSQL);
				ps.setDate(2, startDateSQL);
				
				ResultSet rs = ps.executeQuery();
				res = rs.next() ? loader.loadList(rs) : null;
				rs.close();
				
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return res;
	}

}
