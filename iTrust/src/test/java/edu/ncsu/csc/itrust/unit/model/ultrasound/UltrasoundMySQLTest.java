/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.ultrasound;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.ultrasound.Ultrasound;
import edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * tests the functionality of the UltrasoundMySQL class
 * 
 * @author wyattmaxey
 */
public class UltrasoundMySQLTest {
	private UltrasoundMySQL sql;
	
	private TestDataGenerator gen;
	
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private Ultrasound getNewUltrasound( String dateCreated ) {
		Ultrasound us = new Ultrasound();
		us.setPid( 9 );
		us.setDateCreated( dateCreated );
		us.setPicturePath( "" );
		us.setOfd( 0 );
		us.setMultiNum( 0 );
		us.setHl( 0 );
		us.setHc( 0 );
		us.setFl( 0 );
		us.setEfw( 0 );
		us.setCrl( 0 );
		us.setBpd( 0 );
		return us;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		sql = new UltrasoundMySQL( ds );
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#UltrasoundMySQL()}.
	 */
	@Test
	public void testUltrasoundMySQL() {
		try {
			sql = new UltrasoundMySQL();
			fail();
		} catch ( DBException e ) {
			//should be thrown
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#getAll()}.
	 * @throws DBException 
	 * @throws FormValidationException 
	 */
	@Test
	public void testGetAll() throws DBException, FormValidationException {
		sql.add( getNewUltrasound( "03/24/2017" ) );
		assertEquals( 0, ( int )sql.getAll().get( 0 ).getBpd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#getByID(long)}.
	 */
	@Test
	public void testGetByID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#add(edu.ncsu.csc.itrust.model.ultrasound.Ultrasound)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#update(edu.ncsu.csc.itrust.model.ultrasound.Ultrasound)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#getUltrasound(long, java.lang.String, int)}.
	 */
	@Test
	public void testGetUltrasound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.UltrasoundMySQL#getUltrasoundsForPatient(long)}.
	 */
	@Test
	public void testGetUltrasoundsForPatient() {
		fail("Not yet implemented");
	}

}
