/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * creates sql statements and unpacks sql results
 * 
 * @author wyattmaxey
 */
public class ObstetricsSQLLoader implements SQLLoader<ObstetricsPregnancy> {

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
		op.setDateInit( rs.getDate( "initDate" ) );
		op.setLmp( rs.getDate( "lmp" ) );
		op.setEdd( rs.getDate( "edd" ) );
		op.setExpectedWeeksPregnant( rs.getInt( "expectedWeeks" ) );
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
		if(newInstance) {
			ps.setLong( i++, op.getPid() );
		}
		ps.setDate( i++, op.getDateInit() );
		ps.setDate( i++, op.getLmp() );
		ps.setDate( i++, op.getEdd() );
		ps.setInt( i++,  op.getExpectedWeeksPregnant() );
		ps.setInt( i++, op.getConcepYear() );
		ps.setInt( i++, op.getTotalWeeksPregnant() );
		ps.setDouble( i++, op.getHrsLabor() );
		ps.setInt( i++, op.getWeightGain() );
		ps.setString( i++, op.getDeliveryType() );
		ps.setBoolean( i++, op.getMultiplePregnancy() );
		ps.setInt( i++, op.getBabyCount() );
		ps.setBoolean( i++, op.getCurrent() );
		if( newInstance ) {
			ps.setDate( i++, op.getLmp() );
			ps.setDate( i++, op.getEdd() );
			ps.setInt( i++,  op.getExpectedWeeksPregnant() );
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
			ps.setDate( i++, op.getDateInit() );
		}
		
		return ps;
	}

}
