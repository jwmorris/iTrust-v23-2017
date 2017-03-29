/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * load and unpack parameters for sql statments or result sets
 * 
 * @author wyattmaxey
 */
public class UltrasoundMySQLLoader implements SQLLoader<Ultrasound> {
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
		us.setId( rs.getLong( "id" ) );
		us.setOvId( rs.getLong( "ovId" ) );
		us.setPid( rs.getLong( "pid" ) );
		us.setDateCreated( rs.getDate( "dateCreated" ) );
		us.setPicPath( rs.getString( "picPath" ) );
		us.setImg( rs.getBlob( "img" ).getBinaryStream() );
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

		if( newInstance ) {
			ps.setLong( i++, us.getPid() );
			ps.setDate( i++, us.getDateCreated() );
			ps.setString( i++, us.getPicPath() );
			ps.setBlob( i++, us.getImg() );
			
			ps.setLong( i++, us.getOvId() );
		}
		if( !newInstance ) {
			ps.setString( i++, us.getPicPath() );

			//ps.setBlob( i++, us.getImg() );
			ps.setBlob( i++, us.getImg() );
			ps.setLong( i++, us.getOvId() );
		}
		
		return ps;
	}
}
