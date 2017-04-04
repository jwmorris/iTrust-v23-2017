/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * methods for classes to interact with database
 * 
 * @author wyattmaxey
 */
public class ChildbirthMySQL implements ChildbirthData, Serializable {

	private static final long serialVersionUID = 6390008413116740347L;
	
	private ChildbirthLoaderMySQL cbLoader;
	
	private BabyLoaderMySQL babyLoader;
	
	private ChildbirthValidator cbValidator;
	
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
		cbLoader = new ChildbirthLoaderMySQL();
		babyLoader = new BabyLoaderMySQL();
		cbValidator = new ChildbirthValidator();
		babyValidator = new BabyValidator();
	}
	
	public ChildbirthMySQL( DataSource ds ) {
		this.ds = ds;
		cbLoader = new ChildbirthLoaderMySQL();
		babyLoader = new BabyLoaderMySQL();
		cbValidator = new ChildbirthValidator();
		babyValidator = new BabyValidator();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getAll()
	 */
	@Override
	public List<Childbirth> getAll() throws DBException {
		List<Childbirth> ret = Collections.emptyList();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM childbirthData" );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? cbLoader.loadList( rs ) : null;
			rs.close();
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
	public Childbirth getByID( long id ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM childbirthData WHERE id = ?" );
			ps.setLong( 1, id );
			ResultSet rs = ps.executeQuery();
			Childbirth cb = rs.next() ? cbLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return cb;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#add(java.lang.Object)
	 */
	@Override
	public boolean add( Childbirth cb ) throws FormValidationException, DBException {
		cbValidator.validate( cb );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try { 
			conn = ds.getConnection();
			ps = cbLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO childbirthData (pid, initId, visitDate, deliveryType, "
					+ "ER, amtEpidural, amtMagnesium, amtNitrous, amtPethidine, amtPitocin, epidural, magnesium, "
					+ "nitrous, pethidine, pitocin, amtRH)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"), cb, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}
	
	@Override
	public long addReturnsGeneratedId(Childbirth cb) throws DBException, FormValidationException {
		cbValidator.validate( cb );
		Connection conn = null;
		PreparedStatement ps = null;
		long ret = 0;
		
		try { 
			conn = ds.getConnection();
			ps = cbLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO childbirthData (pid, initId, visitDate, deliveryType, "
					+ "ER, amtEpidural, amtMagnesium, amtNitrous, amtPethidine, amtPitocin, epidural, magnesium, "
					+ "nitrous, pethidine, pitocin, amtRH)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS), 
					cb, true );
			
			ps.executeUpdate();
			ResultSet genKeys = ps.getGeneratedKeys();
			
			if ( genKeys.next() )
				ret = genKeys.getLong( 1 );
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#update(java.lang.Object)
	 */
	@Override
	public boolean update( Childbirth cb ) throws DBException, FormValidationException {
		cbValidator.validateEdit( cb );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = cbLoader.loadParameters( conn, conn.prepareStatement("UPDATE childbirthData SET visitDate=?, deliveryType=?, ER=?"
					+ ", amtEpidural=?, amtMagnesium=?, amtNitrous=?, amtPethidine=?, amtPitocin=?, epidural=?, "
					+ "magnesium=?, nitrous=?, pethidine=?, pitocin=?, amtRH=? WHERE id=?" ), cb, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public List<Baby> getBabiesForChildbirth( long childbirthId ) throws DBException {
		List<Baby> ret = Collections.emptyList();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM babyData WHERE childbirthId=?" );
			ps.setLong( 1, childbirthId );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? babyLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

	@Override
	public List<Childbirth> getChildbirthsForPatient( long pid ) throws DBException {
		List<Childbirth> ret = Collections.emptyList();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM childbirthData WHERE pid=?" );
			ps.setLong( 1, pid );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? cbLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}
	
	

	@Override
	public Baby getBaby( long childbirthId, int multiNum ) throws DBException {
		Baby ret = new Baby();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM babyData WHERE childbirthId=? AND multiNum=?" );
			ps.setLong( 1, childbirthId );
			ps.setInt( 2, multiNum );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? babyLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

	@Override
	public boolean addBaby(Baby baby) throws DBException, FormValidationException {
		babyValidator.validate( baby );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try { 
			conn = ds.getConnection();
			ps = babyLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO babyData (childbirthId, birthDate, "
					+ "birthTime, estimatedDate, sex, multiNum)VALUES(?,?,?,?,?,?)"), baby, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean updateBaby( Baby baby ) throws DBException, FormValidationException {
		babyValidator.validate( baby );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = babyLoader.loadParameters( conn, conn.prepareStatement("UPDATE babyData SET birthDate=?"
					+ ", birthTime=?, estimatedDate=?, sex=? WHERE childbirthId=? AND multiNum=?" ), baby, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public Childbirth getChildbirthVisitForInitId( long pid, long initId ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM childbirthData WHERE pid = ? AND initId = ?" );
			ps.setLong( 1, pid );
			ps.setLong( 2, initId );
			ResultSet rs = ps.executeQuery();
			Childbirth cb = rs.next() ? cbLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return cb;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	

}
