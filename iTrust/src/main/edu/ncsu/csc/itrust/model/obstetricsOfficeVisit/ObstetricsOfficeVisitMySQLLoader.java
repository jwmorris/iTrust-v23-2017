/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

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
public class ObstetricsOfficeVisitMySQLLoader implements SQLLoader<ObstetricsOfficeVisit> {

	@Override
	public List<ObstetricsOfficeVisit> loadList(ResultSet rs) throws SQLException {
		List<ObstetricsOfficeVisit> list = new ArrayList<ObstetricsOfficeVisit>();
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
	private void loadCommon( ResultSet rs, ObstetricsOfficeVisit ov ) throws SQLException {
		ov.setId( rs.getLong( "id" ) );
		ov.setPid( rs.getLong( "pid" ) );
		ov.setVisitDate( rs.getDate( "visitDate" ) );
		ov.setWeeksPregnant( rs.getString( "weeksPregnant" ) );
		ov.setWeight( rs.getString(  "weight") );
		ov.setBp( rs.getString( "bp" ) );
		ov.setFhr( rs.getString( "fhr" ) );
		ov.setMultiplePregnancy( rs.getBoolean( "multiplePregnancy" ) );
		ov.setNumBabies( rs.getString( "numBabies" ) );
		ov.setLowLying( rs.getBoolean( "lowPlacenta" ) );
	}

	@Override
	public ObstetricsOfficeVisit loadSingle( ResultSet rs ) throws SQLException {
		ObstetricsOfficeVisit ov = new ObstetricsOfficeVisit();
		loadCommon( rs, ov );
		return ov;
	}

	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, ObstetricsOfficeVisit ov,
			boolean newInstance ) throws SQLException {
		int i = 1;

		if( newInstance ) {
			ps.setLong( i++, ov.getPid() );
			ps.setDate( i++, ov.getVisitDate() );
			ps.setString( i++, ov.getWeeksPregnant() );
			ps.setString( i++, ov.getWeight() );
			ps.setString( i++, ov.getBp() );
			ps.setString( i++, ov.getFhr() );
			ps.setBoolean( i++, ov.isMultiplePregnancy() );
			ps.setString( i++, ov.getNumBabies() );
			ps.setBoolean( i++, ov.isLowLying() );

		}
		if( !newInstance ) {
			ps.setString( i++, ov.getWeeksPregnant() );
			ps.setString( i++, ov.getWeight() );
			ps.setString( i++, ov.getBp() );
			ps.setString( i++, ov.getFhr() );
			ps.setBoolean( i++, ov.isMultiplePregnancy() );
			ps.setString( i++, ov.getNumBabies() );
			ps.setBoolean( i++, ov.isLowLying() );
			
			ps.setLong( i++, ov.getPid() );
			ps.setDate( i++, ov.getVisitDate() );
		}
		
		return ps;
	}

}
