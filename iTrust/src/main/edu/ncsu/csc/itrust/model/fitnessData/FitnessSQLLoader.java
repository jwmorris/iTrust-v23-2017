package edu.ncsu.csc.itrust.model.fitnessData;

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
 * prepares database statements and parses results into data beans
 * 
 * @author wyattmaxey
 */
public class FitnessSQLLoader implements SQLLoader<Fitness> {
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * fills list from database
	 * 
	 * @param rs
	 * 		result set from database
	 * @throws SQLException
	 */
	@Override
	public List<Fitness> loadList( ResultSet rs ) throws SQLException {
		List<Fitness> list = new ArrayList<Fitness>();
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
	 * @param fdBean
	 * 		used for UI access
	 * @throws SQLException
	 */
	private void loadCommon( ResultSet rs, Fitness f ) throws SQLException {
		f.setPid( rs.getString( "pid" ) );
		f.setCalories( rs.getInt( "calories" ) );
		f.setSteps( rs.getInt( "steps" ) );
		f.setDistance( rs.getDouble( "distance" ) );
		f.setFloors( rs.getInt( "floors" ) );
		f.setMinsSed( rs.getInt( "minsSed" ) );
		f.setMinsLA( rs.getInt( "minsLA" ) );
		f.setMinsFA( rs.getInt( "minsFA" ) );
		f.setMinsVA( rs.getInt( "minsVA" ) );
		f.setActiveCals( rs.getInt( "activeCals" ) );
		f.setActiveHours(rs.getInt("activeHours"));
		f.setHRLow(rs.getInt("hrLow"));
		f.setHRHigh(rs.getInt("hrHigh"));
		f.setHRAvg(rs.getInt("hrAvg"));
		f.setUVExposure(rs.getInt("uvExposure"));
		Date fitnessDate = rs.getDate( "fitnessDate" );
		if ( fitnessDate != null ) {
			f.setDate( DATE_FORMAT.format( fitnessDate ) );
		}
	}

	/**
	 * load a single fitness data bean
	 * 
	 * @param rs
	 * 		from database
	 * @return fdBean
	 * 		fitness data bean for ui use
	 * @throws SQLException
	 */
	@Override
	public Fitness loadSingle( ResultSet rs ) throws SQLException {
		Fitness f = new Fitness();
		loadCommon( rs, f );
		return f;
	}

	/**
	 * place parameters for statement
	 * 
	 * @throws SQLException
	 */
	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, Fitness f, boolean newInstance) throws SQLException {
		int i = 1;
		if(newInstance) {
			ps.setString( i++, f.getPid() );
		}
		ps.setInt( i++, f.getCalories() );
		ps.setInt( i++, f.getSteps() );
		ps.setDouble( i++, f.getDistance() );
		ps.setDouble( i++, f.getFloors() );
		ps.setInt( i++, f.getMinsSed() );
		ps.setInt( i++, f.getMinsLA() );
		ps.setInt( i++, f.getMinsFA() );
		ps.setInt( i++, f.getMinsVA() );
		ps.setInt( i++, f.getActiveCals() );
		ps.setInt(i++, f.getActiveHours());
		ps.setInt(i++, f.getHRLow());
		ps.setInt(i++, f.getHRHigh());
		ps.setInt(i++, f.getHRAvg());
		ps.setInt(i++, f.getUVExposure());
		Date date = null;
		try {
			date = new Date( DATE_FORMAT.parse( f.getDate() ).getTime() );
		} catch (ParseException e) {
			date = null;
		}
		ps.setDate( i++, date );
		if(newInstance) {
			ps.setInt( i++, f.getCalories() );
			ps.setInt( i++, f.getSteps() );
			ps.setDouble( i++, f.getDistance() );
			ps.setInt( i++, f.getFloors() );
			ps.setInt( i++, f.getMinsSed() );
			ps.setInt( i++, f.getMinsLA() );
			ps.setInt( i++, f.getMinsFA() );
			ps.setInt( i++, f.getMinsVA() );
			ps.setInt( i++, f.getActiveCals() );
			ps.setInt(i++, f.getActiveHours());
			ps.setInt(i++, f.getHRLow());
			ps.setInt(i++, f.getHRHigh());
			ps.setInt(i++, f.getHRAvg());
			ps.setInt(i++, f.getUVExposure());
		}
		if(!newInstance) {
			ps.setString( i++, f.getPid() );
			ps.setDate( i++, date );
		}
		
		return ps;
	}

}
