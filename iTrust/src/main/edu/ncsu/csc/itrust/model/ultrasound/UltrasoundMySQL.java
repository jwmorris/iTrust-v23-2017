/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultrasound;

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
 * ultrasound db access
 * 
 * @author wyattmaxey
 */
public class UltrasoundMySQL implements UltrasoundData, Serializable {
	private static final long serialVersionUID = -2514962448923693081L;
	
	/** used to help prepare statements and unpack results */
	private UltrasoundMySQLLoader loader;
	
	/** data source */
	private DataSource ds;
	
	/** validates input from forms */
	private UltrasoundValidator validator;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public UltrasoundMySQL() throws DBException {
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			System.out.println( "It's a naming exception" );
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		loader = new UltrasoundMySQLLoader();
		validator = new UltrasoundValidator( this.ds );
	}
	
	public UltrasoundMySQL( DataSource ds ) {
		loader = new UltrasoundMySQLLoader();
		this.ds = ds;
		validator  = new UltrasoundValidator( ds );
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getAll()
	 */
	@Override
	public List<Ultrasound> getAll() throws DBException {
		List<Ultrasound> ret;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM ultrasoundData" );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? loader.loadList( rs ) : null;
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
	public Ultrasound getByID( long id ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE id = ?" );
			ps.setLong( 1, id );
			ResultSet rs = ps.executeQuery();
			Ultrasound us = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return us;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#add(java.lang.Object)
	 */
	@Override
	public boolean add( Ultrasound us ) throws FormValidationException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		validator.validate( us );
		try { 
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("INSERT INTO ultrasoundData (pid, dateCreated, clr"
					+ ", bpd, hc, fl, ofd, ac, hl, efw, picPath, multiNum) VALUES( ?,?,?,?,?,?,?,?,?,?,?,?)") , us, true );
			
			ps.executeUpdate();
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
	public boolean update( Ultrasound us ) throws DBException, FormValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		validator.validate( us );
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("UPDATE ultrasoundData SET clr=?, bpd=?, "
					+ "hc=?, fl=?, ofd=?, ac=?, hl=?, efw=?, picPath=? WHERE pid=? and dateCreated=? and multiNum=?" ), us, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.ultrasound.UltrasoundData#getUltrasound(long, java.lang.String)
	 */
	@Override
	public Ultrasound getUltrasound( long pid, String dateCreated, int multiNum ) throws DBException {
		Connection conn = null;
		Date creationDate = null;
		try {
			creationDate = new Date( DATE_FORMAT.parse( dateCreated ).getTime() );
		} catch ( ParseException e ) {
			//Some kind of error handling done in validator
			creationDate = null;
			e.printStackTrace();
		}
		
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE pid = ? and dateCreated = ? and multiNum = ?" );
			ps.setLong( 1, pid );
			ps.setDate( 2, creationDate );
			ps.setInt( 3, multiNum );
			ResultSet rs = ps.executeQuery();
			Ultrasound us = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return us;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.ultrasound.UltrasoundData#getUltrasoundsForPatient(long)
	 */
	@Override
	public List<Ultrasound> getUltrasoundsForPatient( long pid ) throws DBException {
		Connection conn = null;
		List<Ultrasound> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE pid=? ORDER BY dateCreated DESC" );
			ps.setLong( 1, pid );
			ResultSet rs = ps.executeQuery();
			res = rs.next() ? loader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

}
