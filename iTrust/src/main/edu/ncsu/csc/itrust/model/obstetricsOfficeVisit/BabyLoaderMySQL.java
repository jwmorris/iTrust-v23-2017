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
 * load sql prepared statements or unpack sql resultset paramters
 * 
 * @author wyattmaxey
 */
public class BabyLoaderMySQL implements SQLLoader<Baby> {

	@Override
	public List<Baby> loadList( ResultSet rs ) throws SQLException {
		List<Baby> list = new ArrayList<Baby>();
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
	 * @param cb
	 * 		used for query result storage
	 * @throws SQLException
	 */
	private void loadCommon( ResultSet rs, Baby baby ) throws SQLException {
		baby.setChildbirthId( rs.getLong( "childbirthId" ) );
		baby.setDate( rs.getString( "birthDate" ) );
		baby.setTime( rs.getString( "birthTime" ) );
		baby.setEstimateDate( rs.getBoolean( "estimatedDate" ) );
		baby.setSex( ( char )( rs.getInt( "sex" ) ) );
		baby.setMultiNum( rs.getInt( "multiNum" ) );
	}

	@Override
	public Baby loadSingle( ResultSet rs ) throws SQLException {
		Baby baby = new Baby();
		loadCommon( rs, baby );
		return baby;
	}

	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, Baby baby,
			boolean newInstance ) throws SQLException {
		int i = 1;
		
		if ( newInstance ) {
			ps.setLong( i++, baby.getChildbirthId() );
			ps.setString( i++, baby.getDate() );
			ps.setString( i++, baby.getTime() );
			ps.setBoolean( i++, baby.isEstimateDate() );
			ps.setInt( i++, baby.getSex() );
			ps.setInt( i++, baby.getMultiNum() );
		}
		
		if ( !newInstance ) {
			ps.setString( i++, baby.getDate() );
			ps.setString( i++, baby.getTime() );
			ps.setBoolean( i++, baby.isEstimateDate() );
			ps.setInt( i++, baby.getSex() );
			
			ps.setLong( i++, baby.getChildbirthId() );
			ps.setInt( i++, baby.getMultiNum() );
		}
		
		return ps;
	}

}
