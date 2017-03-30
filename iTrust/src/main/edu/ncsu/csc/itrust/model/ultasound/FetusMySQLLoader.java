/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * loads and unpacks sql statements and parameters
 * 
 * @author wyattmaxey
 */
public class FetusMySQLLoader implements SQLLoader<Fetus> {

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadList(java.sql.ResultSet)
	 */
	@Override
	public List<Fetus> loadList( ResultSet rs ) throws SQLException {
		List<Fetus> list = new ArrayList<Fetus>();
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
	 * @param ov
	 * 		used for query result storage
	 * @throws SQLException
	 */
	private void loadCommon( ResultSet rs, Fetus f ) throws SQLException {
		f.setOvId( rs.getLong( "ovId" ) );
		f.setCrl( rs.getString( "crl" ) );
		f.setBpd( rs.getString( "bpd" ) );
		f.setHc( rs.getString( "hc" ) );
		f.setFl( rs.getString( "fl" ) );
		f.setOfd( rs.getString( "ofd" ) );
		f.setAc( rs.getString( "ac" ) );
		f.setHl( rs.getString( "hl" ) );
		f.setEfw( rs.getString( "efw" ) );
		f.setMultiNum( rs.getInt( "multiNum" ) );
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadSingle(java.sql.ResultSet)
	 */
	@Override
	public Fetus loadSingle( ResultSet rs ) throws SQLException {
		Fetus f = new Fetus();
		loadCommon( rs, f );
		return f;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadParameters(java.sql.Connection, java.sql.PreparedStatement, java.lang.Object, boolean)
	 */
	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, Fetus f,
			boolean newInstance ) throws SQLException {
		int i = 1;

		if( newInstance ) {
			ps.setLong( i++, f.getOvId() );
			ps.setString( i++, f.getCrl() );
			ps.setString( i++, f.getBpd() );
			ps.setString( i++, f.getHc() );
			ps.setString( i++, f.getFl() );
			ps.setString( i++, f.getOfd() );
			ps.setString( i++, f.getAc() );
			ps.setString( i++, f.getHl() );
			ps.setString( i++, f.getEfw() );
			ps.setInt( i++, f.getMultiNum() );

		}
		if( !newInstance ) {
			ps.setString( i++, f.getCrl() );
			ps.setString( i++, f.getBpd() );
			ps.setString( i++, f.getHc() );
			ps.setString( i++, f.getFl() );
			ps.setString( i++, f.getOfd() );
			ps.setString( i++, f.getAc() );
			ps.setString( i++, f.getHl() );
			ps.setString( i++, f.getEfw() );
			
			ps.setLong( i++, f.getOvId() );
			ps.setInt( i++, f.getMultiNum() );
		}
		
		return ps;
	}

}
