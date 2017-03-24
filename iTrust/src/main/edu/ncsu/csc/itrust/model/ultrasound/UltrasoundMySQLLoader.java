/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultrasound;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * load and unpack parameters for sql statments or result sets
 * 
 * @author wyattmaxey
 */
public class UltrasoundMySQLLoader implements SQLLoader<Ultrasound> {
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public List<Ultrasound> loadList( ResultSet rs ) throws SQLException {
		List<Ultrasound> list = new ArrayList<Ultrasound>();
		list.add( loadSingle( rs ) );
		while ( rs.next() ) {
			list.add( loadSingle( rs ) );
		}
		return list;
	}
	
	/**
	 * loads parameters from database to bean
	 * 
	 * @param rs
	 * 		database results
	 * @param op
	 * 		used for query result storage
	 * @throws SQLException
	 */
	private void loadCommon( ResultSet rs, Ultrasound us ) throws SQLException {
		Date dateCreated = rs.getDate("dateCreated");
		if( dateCreated != null ){
			us.setDateCreated( DATE_FORMAT.format( dateCreated ) );
		}
		us.setPid( rs.getLong( "pid" ) );
		us.setCrl( rs.getFloat( "clr" ) );
		us.setBpd( rs.getFloat( "bpd" ) );
		us.setHc( rs.getFloat( "hc" ) );
		us.setFl( rs.getFloat( "fl" ) );
		us.setOfd( rs.getFloat( "ofd" ) );
		us.setAc( rs.getFloat( "ac" ) );
		us.setHl( rs.getFloat( "hl" ) );
		us.setEfw( rs.getFloat( "efw" ) );
		us.setPicturePath( rs.getString( "picPath" ) );
		us.setMultiNum( rs.getInt( "multiNum" ) );
	}

	@Override
	public Ultrasound loadSingle( ResultSet rs ) throws SQLException {
		Ultrasound us = new Ultrasound();
		loadCommon( rs, us );
		return us;
	}

	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, Ultrasound us,
			boolean newInstance ) throws SQLException {
		int i = 1;
		Date dateCreated = null;
		
		try {
			dateCreated = new java.sql.Date( DATE_FORMAT.parse( us.getDateCreated() ).getTime() );
		} catch ( ParseException e ) {
			//Some kind of error handling done in validator
			e.printStackTrace();
		}

		if( newInstance ) {
			ps.setLong(i++, us.getPid());
			ps.setDate( i++, dateCreated );
			ps.setDouble( i++, us.getCrl() );
			ps.setDouble( i++, us.getBpd() );
			ps.setDouble( i++, us.getHc() );
			ps.setDouble( i++, us.getFl() );
			ps.setDouble( i++, us.getOfd() );
			ps.setDouble( i++, us.getAc() );
			ps.setDouble( i++, us.getHl() );
			ps.setDouble( i++, us.getEfw() );
			ps.setString( i++, us.getPicturePath() );
			ps.setInt( i++, us.getMultiNum() );
		}
		if( !newInstance ) {
			ps.setDouble( i++, us.getCrl() );
			ps.setDouble( i++, us.getBpd() );
			ps.setDouble( i++, us.getHc() );
			ps.setDouble( i++, us.getFl() );
			ps.setDouble( i++, us.getOfd() );
			ps.setDouble( i++, us.getAc() );
			ps.setDouble( i++, us.getHl() );
			ps.setDouble( i++, us.getEfw() );
			ps.setString( i++, us.getPicturePath() );
			
			ps.setLong(i++, us.getPid());
			ps.setDate( i++, dateCreated );
			ps.setInt( i++, us.getMultiNum() );
		}
		
		return ps;
	}
}
