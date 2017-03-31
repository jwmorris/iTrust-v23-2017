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
public class ChildbirthLoaderMySQL implements SQLLoader<Childbirth> {

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadList(java.sql.ResultSet)
	 */
	@Override
	public List<Childbirth> loadList( ResultSet rs ) throws SQLException {
		List<Childbirth> list = new ArrayList<Childbirth>();
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
	private void loadCommon( ResultSet rs, Childbirth cb ) throws SQLException {
		cb.setchildbirthId( rs.getLong( "id" ) );
		cb.setPid( rs.getLong( "pid" ) );
		cb.setDeliveryType( rs.getString( "deliveryType" ) );
		cb.setEr( rs.getBoolean( "ER" ) );
		cb.setAmtEpidural( rs.getString( "amtEpidural" ) );
		cb.setAmtMagnesium( rs.getString( "amtMagnesium" ) );
		cb.setAmtNitrous( rs.getString( "amtNitrous" ) );
		cb.setAmtPethidine( rs.getString( "amtPethidine" ) );
		cb.setAmtPitocin( rs.getString( "amtPitocin" ) );
		cb.setEpidural( rs.getBoolean( "epidural" ) );
		cb.setMagnesium( rs.getBoolean( "magnesium" ) );
		cb.setNitrous( rs.getBoolean( "nitrous" ) );
		cb.setPethidine( rs.getBoolean( "pethidine" ) );
		cb.setPitocin( rs.getBoolean( "pitocin" ) );
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadSingle(java.sql.ResultSet)
	 */
	@Override
	public Childbirth loadSingle( ResultSet rs ) throws SQLException {
		Childbirth cb = new Childbirth();
		loadCommon( rs, cb );
		return cb;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadParameters(java.sql.Connection, java.sql.PreparedStatement, java.lang.Object, boolean)
	 */
	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, Childbirth cb,
			boolean newInstance ) throws SQLException {
		int i = 1;

		if( newInstance ) {
			ps.setLong( i++, cb.getPid() );
			ps.setString( i++, cb.getDeliveryType() );
			ps.setBoolean( i++, cb.isEr() );
			ps.setString( i++, cb.getAmtEpidural() );
			ps.setString( i++, cb.getAmtMagnesium() );
			ps.setString( i++, cb.getAmtNitrous() );
			ps.setString( i++, cb.getAmtPethidine() );
			ps.setString( i++, cb.getAmtPitocin() );
			ps.setBoolean( i++, cb.isEpidural() );
			ps.setBoolean( i++, cb.isMagnesium() );
			ps.setBoolean( i++, cb.isNitrous() );
			ps.setBoolean( i++, cb.isPethidine() );
			ps.setBoolean( i++, cb.isPitocin() );
		}
		
		if ( !newInstance ) {
			ps.setString( i++, cb.getDeliveryType() );
			ps.setBoolean( i++, cb.isEr() );
			ps.setString( i++, cb.getAmtEpidural() );
			ps.setString( i++, cb.getAmtMagnesium() );
			ps.setString( i++, cb.getAmtNitrous() );
			ps.setString( i++, cb.getAmtPethidine() );
			ps.setString( i++, cb.getAmtPitocin() );
			ps.setBoolean( i++, cb.isEpidural() );
			ps.setBoolean( i++, cb.isMagnesium() );
			ps.setBoolean( i++, cb.isNitrous() );
			ps.setBoolean( i++, cb.isPethidine() );
			ps.setBoolean( i++, cb.isPitocin() );
			
			ps.setLong( i++, cb.getchildbirthId() );
		}
		
		return ps;
	}

}
