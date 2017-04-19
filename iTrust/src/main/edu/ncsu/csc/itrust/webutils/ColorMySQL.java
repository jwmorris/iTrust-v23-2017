/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ColorMySQL implements ColorData, Serializable {

	private static final long serialVersionUID = -4323991906061868397L;
	
	private ColorMySQLLoader loader;
	
	private DataSource ds;
	
	public ColorMySQL() throws DBException {
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		
		loader = new ColorMySQLLoader();
	}
	
	public ColorMySQL( DataSource ds ) {
		this.ds = ds;
		loader = new ColorMySQLLoader();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getAll()
	 */
	@Override
	public List<ColorBean> getAll() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#getByID(long)
	 */
	@Override
	public ColorBean getByID(long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.DataBean#add(java.lang.Object)
	 */
	@Override
	public boolean add( ColorBean bean ) throws FormValidationException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try { 
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement("INSERT INTO colorData (pid, lmb, mtc, mbc, ftc, nbc, ntc, "
					+ "fbc, spb, tb1, tb2, thb, tht, etc, wtc) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"), bean, true );
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
	public boolean update( ColorBean bean ) throws DBException, FormValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = loader.loadParameters( conn, conn.prepareStatement( "UPDATE colorData SET lmb=?, mtc=?, mbc=?"
					+ ", ftc=?, nbc=?, ntc=?, fbc=?, spb=?, tb1=?, tb2=?, thb=?, tht=?, etc=?, wtc=? WHERE pid=?" ),
					bean, false );
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.webutils.ColorData#getColorBean(long)
	 */
	@Override
	public ColorBean getColorBean( long pid ) throws DBException {
		ColorBean ret = new ColorBean();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM colorData WHERE pid=?" );
			ps.setLong( 1, pid );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? loader.loadSingle( rs ) : null;
			rs.close();
			ps.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

}
