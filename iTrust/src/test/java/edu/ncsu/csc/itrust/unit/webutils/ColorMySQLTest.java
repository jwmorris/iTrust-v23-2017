package edu.ncsu.csc.itrust.unit.webutils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.webutils.ColorBean;
import edu.ncsu.csc.itrust.webutils.ColorData;
import edu.ncsu.csc.itrust.webutils.ColorMySQL;

public class ColorMySQLTest {
	
	private ColorData sql;
	private ColorBean bean;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		sql = new ColorMySQL( ConverterDAO.getDataSource() );
		bean = new ColorBean();
	}

	@Test
	public void testColorMySQL() {
		try {
			new ColorMySQL();
		} catch( Exception e ) {
			// pass
		}
	}

	@Test
	public void testGetAll() {
		try {
			assertNull( sql.getAll() );
		} catch (DBException e) {
			
		}
	}

	@Test
	public void testGetByID() {
		try {
			assertNull( sql.getByID( 2 ) );
		} catch (DBException e) {
			
		}
	}

	@Test
	public void testAdd() {
		bean.setErrorText( "#000000" );
		bean.setFooterBackground( "#000000" );
		bean.setFooterText( "#000000" );
		bean.setLeftMenuBackground( "#000000" );
		bean.setNavigationBarBackground( "#000000" );
		bean.setNavigationBarText( "#000000" );
		bean.setPid( 2 );
		bean.setPrimaryBackground( "#000000" );
		bean.setPrimaryText( "#000000" );
		bean.setSelectedPatient( "#000000" );
		bean.setTableHeadingBackground( "#000000" );
		bean.setTableHeadingText( "#000000" );
		bean.setTableRowBackground1( "#000000" );
		bean.setTableRowBackground2( "#000000" );
		bean.setWelcomeText( "#000000" );
		
		try {
			sql.add( bean );
		} catch (DBException | FormValidationException e) {
			
		}
		
		try {
			ColorBean ret = sql.getColorBean( 2 );
			assertNotNull( ret );
			assertEquals( "#000000", ret.getErrorText() );
		} catch (DBException e) {
			fail();
		}
		
	}

	@Test
	public void testUpdate() {
		bean.setErrorText( "#000000" );
		bean.setFooterBackground( "#000000" );
		bean.setFooterText( "#000000" );
		bean.setLeftMenuBackground( "#000000" );
		bean.setNavigationBarBackground( "#000000" );
		bean.setNavigationBarText( "#000000" );
		bean.setPid( 2 );
		bean.setPrimaryBackground( "#000000" );
		bean.setPrimaryText( "#000000" );
		bean.setSelectedPatient( "#000000" );
		bean.setTableHeadingBackground( "#000000" );
		bean.setTableHeadingText( "#000000" );
		bean.setTableRowBackground1( "#000000" );
		bean.setTableRowBackground2( "#000000" );
		bean.setWelcomeText( "#000000" );
		
		try {
			sql.add( bean );
		} catch (DBException | FormValidationException e) {
			
		}
		try {
			ColorBean ret = sql.getColorBean( 2 );
			assertNotNull( ret );
			assertEquals( "#000000", ret.getErrorText() );
		} catch (DBException e) {
			fail();
		}
		
		bean.setErrorText( "#FFFFFF" );
		try {
			sql.update( bean );
		} catch (DBException e1) {
		} catch (FormValidationException e1) {
		}
		
		try {
			ColorBean ret = sql.getColorBean( 2 );
			assertNotNull( ret );
			assertEquals( "#FFFFFF", ret.getErrorText() );
		} catch (DBException e) {
			fail();
		}
	}



}
