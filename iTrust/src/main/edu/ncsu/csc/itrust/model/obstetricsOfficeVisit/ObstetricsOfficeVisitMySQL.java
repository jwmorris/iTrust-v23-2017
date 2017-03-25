/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.model.ultasound.UltrasoundMySQLLoader;
import edu.ncsu.csc.itrust.model.ultasound.UltrasoundValidator;

/**
 * provides database access for office visits, ultrasounds, and fetus data object
 * 
 * @author wyattmaxey
 */
public class ObstetricsOfficeVisitMySQL implements ObstetricsOfficeVisitData, Serializable {

	private static final long serialVersionUID = 8463472964665692376L;
	
	/** used to help prepare statements and unpack results */
	private ObstetricsOfficeVisitMySQLLoader ovLoader;
	
	/** used to help prepare statements and unpack results */
	private UltrasoundMySQLLoader usLoader;
	
	private FetusMySQLLoader fetusLoader;
	
	/** data source */
	private DataSource ds;
	
	/** validates input from forms */
	private ObstetricsOfficeValidator ovValidator;
	
	/** validates input from forms */
	private UltrasoundValidator usValidator;
	
	private FetusValidator fetusValidator;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public ObstetricsOfficeVisitMySQL() throws DBException {
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			System.out.println( "It's a naming exception" );
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		usLoader = new UltrasoundMySQLLoader();
		usValidator = new UltrasoundValidator( this.ds );
	}
	
	public ObstetricsOfficeVisitMySQL( DataSource ds ) {
		usLoader = new UltrasoundMySQLLoader();
		this.ds = ds;
		usValidator  = new UltrasoundValidator( ds );
	}

	@Override
	public List<ObstetricsOfficeVisit> getAll() throws DBException {
		List<ObstetricsOfficeVisit> ret;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM obstetricsOfficeVisitData" );
			ResultSet rs = ps.executeQuery();
			ret = rs.next() ? ovLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
		return ret;
	}

	@Override
	public ObstetricsOfficeVisit getByID( long id ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsOfficeVisitData WHERE id = ?" );
			ps.setLong( 1, id );
			ResultSet rs = ps.executeQuery();
			ObstetricsOfficeVisit ov = rs.next() ? ovLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return ov;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	@Override
	public boolean add( ObstetricsOfficeVisit ov ) throws FormValidationException, DBException {
		ovValidator.validateAdd( ov );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try { 
			conn = ds.getConnection();
			ps = ovLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO obstetricsOfficeVisitData (pid, "
					+ "weeksPregnant, weight, bp, fhr, multiPregnancy, numBabies, lowPlacenta) VALUES(?,?,?,?,?,?,?,?,?)"),
					ov, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean update( ObstetricsOfficeVisit ov ) throws DBException, FormValidationException {
		ovValidator.validateUpdate( ov );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = ovLoader.loadParameters( conn, conn.prepareStatement("UPDATE obstetricsOfficeVisitData SET weeksPregnant=?, weight=?"
					+ ", bp=?, fhr=?, multiPregnancy=?, numBabies=?, lowPlacenta=? WHERE pid=? and visitDate=?" ), ov, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public List<ObstetricsOfficeVisit> getOfficeVistsForPatient( long pid ) throws DBException, FormValidationException {
		Connection conn = null;
		List<ObstetricsOfficeVisit> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsOfficeVisitData WHERE pid=? ORDER BY visitDate DESC" );
			ps.setLong( 1, pid );
			ResultSet rs = ps.executeQuery();
			res = rs.next() ? ovLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

	@Override
	public ObstetricsOfficeVisit getOfficeVisitForDate( long pid, Date date )
			throws DBException, FormValidationException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM obstetricsOfficeVisitData WHERE pid = ? and visitDate = ?" );
			ps.setLong( 1, pid );
			ps.setDate( 2, date );
			ResultSet rs = ps.executeQuery();
			ObstetricsOfficeVisit ov = rs.next() ? ovLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return ov;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	@Override
	public Fetus getFetus( long ultrasoundId, int multiNum ) throws DBException, FormValidationException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fetusData WHERE ultrasoundId = ? and multiNum = ?" );
			ps.setLong( 1, ultrasoundId );
			ps.setInt( 2, multiNum );
			ResultSet rs = ps.executeQuery();
			Fetus f = rs.next() ? fetusLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return f;
		} catch ( SQLException e ) {
			throw new DBException( e );
		}
	}

	@Override
	public List<Fetus> getFetiForUltrasound( long ultrasoundId ) throws DBException, FormValidationException {
		Connection conn = null;
		List<Fetus> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fetusData WHERE ultrasoundId=?" );
			ps.setLong( 1, ultrasoundId );
			ResultSet rs = ps.executeQuery();
			res = rs.next() ? fetusLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

	@Override
	public List<Fetus> getFetiForOfficeVisit( long ovId ) throws DBException, FormValidationException {
		Connection conn = null;
		List<Fetus> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT ultrasoundId, crl, bpd, hc, fl, ofd, ac, hl, efw, multiNum FROM fetusData, "
					+ "ultrasoundData WHERE fetusData.ultrasoundId=ultrasoundData.id, ultrasoundData.ovId=?" );
			ps.setLong( 1, ovId );
			ResultSet rs = ps.executeQuery();
			res = rs.next() ? fetusLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

	@Override
	public Fetus getFetusForOfficeVisit( long ovId, int multiNum ) throws DBException, FormValidationException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT ultrasoundId, crl, bpd, hc, fl, ofd, ac, hl, efw, multiNum FROM fetusData, "
					+ "ultrasoundData WHERE fetusData.ultrasoundId=ultrasoundData.id, ultrasoundData.ovId=?, fetusData.multiNum=?" );
			ps.setLong( 1, ovId );
			ps.setInt( 2, multiNum );
			ResultSet rs = ps.executeQuery();
			Fetus f = rs.next() ? fetusLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return f;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public List<Ultrasound> getUltrasoundsForPatient( long pid ) {
		Connection conn = null;
		List<Ultrasound> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE pid=? ORDER BY dateCreated DESC" );
			ps.setLong( 1, pid );
			ResultSet rs = ps.executeQuery();
			res = rs.next() ? usLoader.loadList( rs ) : null;
			rs.close();
			conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return res;
	}

	@Override
	public Ultrasound getUltrasoundByOfficeVisitId( long ovId ) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE ovId=?" );
			ps.setLong( 1, ovId );
			ResultSet rs = ps.executeQuery();
			Ultrasound us = rs.next() ? usLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return us;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public Ultrasound getUltrasoundByUltrasoundId( long usId ) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE id=?" );
			ps.setLong( 1, usId );
			ResultSet rs = ps.executeQuery();
			Ultrasound us = rs.next() ? usLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return us;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public Ultrasound getUltrasoundByDate( long pid, Date date ) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE pid=?, dateCreated=?" );
			ps.setLong( 1, pid );
			ps.setDate( 2, date );
			ResultSet rs = ps.executeQuery();
			Ultrasound us = rs.next() ? usLoader.loadSingle( rs ) : null;
			rs.close();
			conn.close();
			return us;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean addFetus( Fetus f ) throws DBException, FormValidationException {
		fetusValidator.validate( f );
		Connection conn = null;
		PreparedStatement ps = null;
		try { 
			conn = ds.getConnection();
			ps = fetusLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO fetusData (ultrasoundId, "
					+ "crl, bpd, hc, fl, ofd, ac, hl, efw, multiNum) VALUES(?,?,?,?,?,?,?,?,?,?)"),
					f, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean updateFetus( Fetus f ) throws DBException, FormValidationException {
		fetusValidator.validate( f );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = fetusLoader.loadParameters( conn, conn.prepareStatement("UPDATE fetusData SET crl=?, bpd=?, hc=?, fl=?"
					+ ", ofd=?, ac=?, hl=?, efw=? WHERE ultrasoundId=? and multiNum=?" ), f, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public boolean addUltrasound( Ultrasound us ) throws DBException, FormValidationException {
		usValidator.validateAdd( us );
		Connection conn = null;
		PreparedStatement ps = null;
		try { 
			conn = ds.getConnection();
			ps = usLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO ultrasoundData (pid, dateCreated"
					+ ", picPath, ovId) VALUES(?,?,?,?)"), us, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean updateUltrasound( Ultrasound us ) throws DBException, FormValidationException {
		usValidator.validateEdit( us );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = usLoader.loadParameters( conn, conn.prepareStatement("UPDATE ultrasoundData SET picPath=? WHERE ovId=?" ), us, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

}
