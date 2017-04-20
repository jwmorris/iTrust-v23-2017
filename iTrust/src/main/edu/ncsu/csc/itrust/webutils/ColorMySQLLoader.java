/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * @author wyattmaxey
 */
public class ColorMySQLLoader implements SQLLoader<ColorBean> {

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadList(java.sql.ResultSet)
	 */
	@Override
	public List<ColorBean> loadList( ResultSet rs ) throws SQLException {
		List<ColorBean> list = new ArrayList<ColorBean>();
		list.add( loadSingle( rs ) );
		while ( rs.next() ) {
			list.add( loadSingle( rs ) );
		}
		return list;
	}
	
	private void loadCommon( ResultSet rs, ColorBean bean ) throws SQLException {
		bean.setPid( rs.getLong( "pid" ) );
		bean.setErrorText( rs.getString( "etc" ) );
		bean.setFooterBackground( rs.getString( "fbc" ) );
		bean.setWelcomeText( rs.getString( "wtc" ) );
		bean.setTableRowBackground2( rs.getString( "tb2" ) );
		bean.setTableRowBackground1( rs.getString( "tb1" ) );
		bean.setTableHeadingText( rs.getString( "tht" ) );
		bean.setTableHeadingBackground( rs.getString( "thb" ) );
		bean.setSelectedPatient( rs.getString( "spb" ) );
		bean.setPrimaryText( rs.getString( "mtc" ) );
		bean.setPrimaryBackground( rs.getString( "mbc" ) );
		bean.setNavigationBarText( rs.getString( "ntc" ) );
		bean.setNavigationBarBackground( rs.getString( "nbc" ) );
		bean.setLeftMenuBackground( rs.getString( "lmb" ) );
		bean.setFooterText( rs.getString( "ftc" ) );
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadSingle(java.sql.ResultSet)
	 */
	@Override
	public ColorBean loadSingle( ResultSet rs ) throws SQLException {
		ColorBean cb = new ColorBean();
		loadCommon( rs, cb );
		return cb;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.SQLLoader#loadParameters(java.sql.Connection, java.sql.PreparedStatement, java.lang.Object, boolean)
	 */
	@Override
	public PreparedStatement loadParameters( Connection conn, PreparedStatement ps, ColorBean bean,
			boolean newInstance ) throws SQLException {
		int i = 1;
		
		if ( newInstance ) {
			ps.setLong( i++,  bean.getPid() );
			ps.setString( i++, bean.getLeftMenuBackground() );
			ps.setString( i++, bean.getPrimaryText() );
			ps.setString( i++, bean.getPrimaryBackground() );
			ps.setString( i++, bean.getFooterText() );
			ps.setString( i++, bean.getNavigationBarBackground() );
			ps.setString( i++, bean.getNavigationBarText() );
			ps.setString( i++, bean.getFooterBackground() );
			ps.setString( i++, bean.getSelectedPatient() );
			ps.setString( i++, bean.getTableRowBackground1() );
			ps.setString( i++, bean.getTableRowBackground2() );
			ps.setString( i++, bean.getTableHeadingBackground() );
			ps.setString( i++, bean.getTableHeadingText() );
			ps.setString( i++, bean.getErrorText() );
			ps.setString( i++, bean.getWelcomeText() );
			return ps;
		}
		
		if ( !newInstance ) {
			ps.setString( i++, bean.getLeftMenuBackground() );
			ps.setString( i++, bean.getPrimaryText() );
			ps.setString( i++, bean.getPrimaryBackground() );
			ps.setString( i++, bean.getFooterText() );
			ps.setString( i++, bean.getNavigationBarBackground() );
			ps.setString( i++, bean.getNavigationBarText() );
			ps.setString( i++, bean.getFooterBackground() );
			ps.setString( i++, bean.getSelectedPatient() );
			ps.setString( i++, bean.getTableRowBackground1() );
			ps.setString( i++, bean.getTableRowBackground2() );
			ps.setString( i++, bean.getTableHeadingBackground() );
			ps.setString( i++, bean.getTableHeadingText() );
			ps.setString( i++, bean.getErrorText() );
			ps.setString( i++, bean.getWelcomeText() );
			
			ps.setLong( i++, bean.getPid() );
			return ps;
		}
		return null;
	}

}
