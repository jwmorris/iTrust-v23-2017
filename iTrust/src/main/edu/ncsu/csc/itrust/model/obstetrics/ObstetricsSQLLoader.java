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
		Date initDate = rs.getDate("initDate");
		Date lmp = rs.getDate("lmp");
		Date edd = rs.getDate("edd");
		if(initDate != null){
			op.setDateInit(DATE_FORMAT.format(initDate));
		}
		if(lmp != null){
			op.setLmp(DATE_FORMAT.format(lmp));
		}
		if(edd != null){
			op.setEdd();
//			op.assignEdd(DATE_FORMAT.format(edd));
		}
		op.setPid( rs.getLong( "pid" ) );
		op.setId( rs.getLong( "id" ));
//		op.setDateInit( DATE_FORMAT.format( initDate) );
//		op.setLmp( DATE_FORMAT.format( rs.getDate( "lmp" ) ) );
//		op.setEdd( DATE_FORMAT.format( rs.getDate( "edd" ) ) );
		op.setWeeksPregnant(  );
		op.setConcepYear( rs.getString( "concepYear" ) );
		op.setTotalWeeksPregnant( rs.getString( "totalWeeks" ) );
		op.setHoursLabor( rs.getDouble( "hrsLabor" ) + "" );
		op.setWeightGain( rs.getString( "weightGain" ) );
		op.setDeliveryType( rs.getString( "deliveryType" ) );
		op.setMultiplePregnancy( rs.getBoolean( "multiplePregnancy" ) );
		op.setBabyCount( rs.getString( "babyCount" ) );
		op.setCurrent( rs.getBoolean( "current" ) );
		op.setrhFlag( rs.getBoolean( "rhFlag" ) );
		op.sethpMiscarriage( rs.getBoolean( "hpMiscarriage" ) );
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
			dateInit = new java.sql.Date( DATE_FORMAT.parse( op.getDateInit() ).getTime() );
			lmp = new java.sql.Date( DATE_FORMAT.parse( op.getLmp() ).getTime() );
			edd = new java.sql.Date( DATE_FORMAT.parse( op.getEdd() ).getTime() );
		} catch ( ParseException e ) {

			e.printStackTrace();
		}

		if( newInstance ) {
			ps.setLong(i++, op.getPid());
			ps.setDate( i++, dateInit );
			ps.setDate( i++, lmp );

			ps.setDate( i++, edd );
			ps.setString( i++,  op.getWeeksPregnant() );
			ps.setString( i++, op.getConcepYear() );
			ps.setString( i++, op.getTotalWeeksPregnant() );
			ps.setDouble( i++, Double.parseDouble(op.getHoursLabor()) );
			ps.setString( i++, op.getWeightGain() );
			ps.setString( i++, op.getDeliveryType() );
			ps.setBoolean( i++, op.getMultiplePregnancy() );
			ps.setString( i++, op.getBabyCount() );
			ps.setBoolean( i++, op.getCurrent() );
			ps.setBoolean( i++, op.getrhFlag() );
			ps.setBoolean( i++, op.gethpMiscarriage() );
		}
		if(!newInstance) {
			ps.setDate( i++, dateInit );
			ps.setDate( i++, lmp );
			ps.setDate( i++, edd );
			System.out.println( lmp );
			System.out.println( dateInit );
			ps.setString( i++,  op.getWeeksPregnant() );
			ps.setString( i++, op.getConcepYear() );
			ps.setString( i++, op.getTotalWeeksPregnant() );
			ps.setDouble( i++, Double.parseDouble(op.getHoursLabor()) );
			ps.setString( i++, op.getWeightGain() );
			ps.setString( i++, op.getDeliveryType() );
			ps.setBoolean( i++, op.getMultiplePregnancy() );
			ps.setString( i++, op.getBabyCount() );
			ps.setBoolean( i++, op.getCurrent() );
			ps.setBoolean( i++, op.getrhFlag() );
			ps.setBoolean( i++, op.gethpMiscarriage() );
			ps.setLong(i++, op.getId());
		}
		
		return ps;
	}

	public PreparedStatement loadPriorParameters(Connection conn, PreparedStatement ps, ObstetricsPregnancy op){
		
		return ps;
	}
}
