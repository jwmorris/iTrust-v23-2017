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
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.FetusMySQLLoader;
import edu.ncsu.csc.itrust.model.ultasound.FetusValidator;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.model.ultasound.UltrasoundMySQLLoader;

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
	private ObstetricsOfficeVisitValidator ovValidator;
	
	private FetusValidator fetusValidator;
	
	public ObstetricsOfficeVisitMySQL() throws DBException {
		try {
			Context ctx = new InitialContext();
			this.ds = ( ( DataSource ) ( ( ( Context ) ctx.lookup( "java:comp/env" ) ) ).lookup( "jdbc/itrust" ) );
		} catch ( NamingException e ) {
			System.out.println( "It's a naming exception" );
			throw new DBException( new SQLException( "Context Lookup Naming Exception: " + e.getMessage() ) );
		}
		ovValidator = new ObstetricsOfficeVisitValidator( ds );
		fetusValidator = new FetusValidator();
		ovLoader = new ObstetricsOfficeVisitMySQLLoader();
		fetusLoader = new FetusMySQLLoader();
		usLoader = new UltrasoundMySQLLoader();
	}
	
	public ObstetricsOfficeVisitMySQL( DataSource ds ) {
		ovValidator = new ObstetricsOfficeVisitValidator( ds );
		ovLoader = new ObstetricsOfficeVisitMySQLLoader();
		fetusLoader = new FetusMySQLLoader();
		fetusValidator = new FetusValidator();
		usLoader = new UltrasoundMySQLLoader();
		this.ds = ds;
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
		ovValidator.validate( ov );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try { 
			conn = ds.getConnection();
			ps = ovLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO obstetricsOfficeVisitData (pid, "
					+ "visitDate, weeksPregnant, weight, bp, fhr, multiPregnancy, numBabies, lowPlacenta) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)"),
					ov, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}
	
	public long addReturnsGeneratedId( ObstetricsOfficeVisit ov ) throws FormValidationException, DBException {
		ovValidator.validate( ov );
		Connection conn = null;
		PreparedStatement ps = null;
		long ret = 0;
		
		try { 
			conn = ds.getConnection();
			ps = ovLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO obstetricsOfficeVisitData (pid, "
					+ "visitDate, weeksPregnant, weight, bp, fhr, multiPregnancy, numBabies, lowPlacenta) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS ),
					ov, true );
			
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

	@Override
	public boolean update( ObstetricsOfficeVisit ov ) throws DBException, FormValidationException {
		ovValidator.validateEdit( ov );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = ovLoader.loadParameters( conn, conn.prepareStatement("UPDATE obstetricsOfficeVisitData SET weeksPregnant=?, weight=?"
					+ ", bp=?, fhr=?, multiPregnancy=?, numBabies=?, lowPlacenta=? WHERE id=?" ), ov, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public List<ObstetricsOfficeVisit> getOfficeVistsForPatient( long pid ) throws DBException {
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
	public ObstetricsOfficeVisit getOfficeVisitForDate( long pid, Date date ) throws DBException {
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
	public Fetus getFetus( long ovId, int multiNum ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fetusData WHERE ovId = ? and multiNum = ?" );
			ps.setLong( 1, ovId );
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
	/*
	@Override
	public List<Fetus> getFetiForUltrasound( long ultrasoundId ) throws DBException {
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
	*/
	@Override
	public List<Fetus> getFetiForOfficeVisit( long ovId ) throws DBException {
		Connection conn = null;
		List<Fetus> res;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fetusData WHERE ovId=?" );
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
	public Fetus getFetusForOfficeVisit( long ovId, int multiNum ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fetusData WHERE ovId=? AND multiNum=?" );
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
	public List<Ultrasound> getUltrasoundsForPatient( long pid ) throws DBException {
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
	public Ultrasound getUltrasoundByOfficeVisitId( long ovId ) throws DBException {
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
	public Ultrasound getUltrasoundByDate( long pid, Date date ) throws DBException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM ultrasoundData WHERE pid=? AND dateCreated=?" );
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
			ps = fetusLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO fetusData (ovId, "
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
		fetusValidator.validateEdit( f );
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = fetusLoader.loadParameters( conn, conn.prepareStatement("UPDATE fetusData SET crl=?, bpd=?, hc=?, fl=?"
					+ ", ofd=?, ac=?, hl=?, efw=? WHERE ovId=? and multiNum=?" ), f, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

	@Override
	public boolean addUltrasound( Ultrasound us ) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try { 
			conn = ds.getConnection();
			ps = usLoader.loadParameters( conn, conn.prepareStatement("INSERT INTO ultrasoundData (pid, dateCreated"
					+ ", picPath, img, ovId) VALUES(?,?,?,?,?)"), us, true );
			
			ps.executeUpdate();
			conn.close();
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
	}

	@Override
	public boolean updateUltrasound( Ultrasound us ) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = usLoader.loadParameters( conn, conn.prepareStatement("UPDATE ultrasoundData SET picPath=?, img=? WHERE ovId=?" ), us, false );
			ps.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new DBException( e );
		}
		return true;
	}

}
