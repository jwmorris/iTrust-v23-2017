/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

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
 * creates sql statements and unpacks sql results
 * 
 * @author wyattmaxey
 */
public class ObstetricsSQLLoader implements SQLLoader<ObstetricsPregnancy> {
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public List<ObstetricsPregnancy> loadList( ResultSet rs ) throws SQLException {
		List<ObstetricsPregnancy> list = new ArrayList<ObstetricsPregnancy>();
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
	private void loadCommon( ResultSet rs, ObstetricsPregnancy op ) throws SQLException {
		op.setPid( rs.getLong( "pid" ) );
		op.setDateInit( DATE_FORMAT.format( rs.getDate( "initDate" ) ) );
		op.setLmp( DATE_FORMAT.format( rs.getDate( "lmp" ) ) );
		op.setEdd( DATE_FORMAT.format( rs.getDate( "edd" ) ) );
		op.setWeeksPregnant( rs.getInt( "weeksPregnant" ) );
		op.setConcepYear( rs.getInt( "concepYear" ) );
		op.setTotalWeeksPregnant( rs.getInt( "totalWeeks" ) );
		op.setHrsLabor( rs.getDouble( "hrsLabor" ) );
		op.setWeightGain( rs.getInt( "weightGain" ) );
		op.setDeliveryType( rs.getString( "deliveryType" ) );
		op.setMultiplePregnancy( rs.getBoolean( "multiplePregnancy" ) );
		op.setBabyCount( rs.getInt( "babyCount" ) );
		op.setCurrent( rs.getBoolean( "current" ) );
	}

	@Override
	public ObstetricsPregnancy loadSingle( ResultSet rs ) throws SQLException {
		ObstetricsPregnancy op = new ObstetricsPregnancy();
		loadCommon( rs, op );
		return op;
	}

	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, ObstetricsPregnancy op,
			boolean newInstance ) throws SQLException {
		int i = 1;
		Date dateInit = null;
		Date lmp = null;
		Date edd = null;
		
		try {
			dateInit = new Date( DATE_FORMAT.parse( op.getDateInit() ).getTime() );
			lmp = new Date( DATE_FORMAT.parse( op.getLmp() ).getTime() );
			edd = new Date( DATE_FORMAT.parse( op.getEdd() ).getTime() );
		} catch ( ParseException e ) {
			//Some kind of error handling done in validator
			dateInit = null;
			lmp = null;
			edd = null;
			e.printStackTrace();
		}
		
		if(newInstance) {
			ps.setLong( i++, op.getPid() );
			ps.setDate( i++, dateInit );
		}
		
		ps.setDate( i++, lmp );
		ps.setDate( i++, edd );
		ps.setInt( i++,  op.getWeeksPregnant() );
		ps.setInt( i++, op.getConcepYear() );
		ps.setInt( i++, op.getTotalWeeksPregnant() );
		ps.setDouble( i++, op.getHrsLabor() );
		ps.setInt( i++, op.getWeightGain() );
		ps.setString( i++, op.getDeliveryType() );
		ps.setBoolean( i++, op.getMultiplePregnancy() );
		ps.setInt( i++, op.getBabyCount() );
		ps.setBoolean( i++, op.getCurrent() );
		if( newInstance ) {
			ps.setDate( i++, dateInit );
			ps.setDate( i++, lmp );
			ps.setDate( i++, edd );
			ps.setInt( i++,  op.getWeeksPregnant() );
			ps.setInt( i++, op.getConcepYear() );
			ps.setInt( i++, op.getTotalWeeksPregnant() );
			ps.setDouble( i++, op.getHrsLabor() );
			ps.setInt( i++, op.getWeightGain() );
			ps.setString( i++, op.getDeliveryType() );
			ps.setBoolean( i++, op.getMultiplePregnancy() );
			ps.setInt( i++, op.getBabyCount() );
			ps.setBoolean( i++, op.getCurrent() );
		}
		if(!newInstance) {
			ps.setLong( i++, op.getPid() );
			ps.setDate( i++, dateInit );
		}
		
		return ps;
	}

}
