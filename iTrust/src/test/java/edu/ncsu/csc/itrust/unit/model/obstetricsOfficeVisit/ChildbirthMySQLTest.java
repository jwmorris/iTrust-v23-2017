/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * tests the functionality of the sql access methods for childbirth data
 * 
 * @author wyattmaxey
 */
public class ChildbirthMySQLTest {
	ChildbirthMySQL sql;
	
	private DataSource ds;
	
	private TestDataGenerator gen;
	
	private Childbirth cb;
	
	private Baby baby;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		sql = new ChildbirthMySQL( ds );
		
		cb = new Childbirth();
		cb.setPid( 9 );
		cb.setDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		cb.setAmtEpidural( "1" );
		cb.setAmtMagnesium( "1" );
		cb.setAmtNitrous( "1" );
		cb.setAmtPethidine( "1" );
		cb.setAmtPitocin( "1" );
		cb.setchildbirthId( 1 );
		cb.setDeliveryType( "Vaginal" );
		cb.setEpidural( true );
		cb.setMagnesium( true );
		cb.setNitrous( true );
		cb.setPethidine( true );
		cb.setPitocin( true );
		
		baby = new Baby();
		baby.setChildbirthId( 1 );
		baby.setDate( "03/30/2017" );
		baby.setEstimateDate( false );
		baby.setMultiNum( 0 );
		baby.setSex( 'm' );
		baby.setTime( "21:00" );
		
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#getAll()}.
	 */
	@Test
	public void testGetAll() {
		try {
			sql.add( cb );
			assertEquals( 1, sql.getAll().size() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#getByID(long)}.
	 */
	@Test
	public void testGetByID() {
		try {
			sql.add( cb );
			assertTrue( sql.getByID( 1 ).getAmtEpidural().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#addReturnsGeneratedId(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth)}.
	 */
	@Test
	public void testAddReturnsGeneratedId() {
		try {
			assertEquals( 1, sql.addReturnsGeneratedId( cb ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#update(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth)}.
	 */
	@Test
	public void testUpdate() {
		try {
			sql.add( cb );
			cb.setAmtEpidural( "2" );
			sql.update( cb );
			assertTrue( sql.getByID( 1 ).getAmtEpidural().equals( "2" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#getBabiesForChildbirth(long)}.
	 */
	@Test
	public void testGetBabiesForChildbirth() {
		try {
			sql.add( cb );
			sql.addBaby( baby );
			assertEquals( 1, sql.getBabiesForChildbirth( 1 ).size() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#getChildbirthsForPatient(long)}.
	 */
	@Test
	public void testGetChildbirthsForPatient() {
		try {
			sql.add( cb );
			assertEquals( 1, sql.getChildbirthsForPatient( 9 ).size() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#getBaby(long, int)}.
	 */
	@Test
	public void testGetBaby() {
		try {
			sql.add( cb );
			sql.addBaby( baby );
			assertEquals( 0, sql.getBaby( 1, 0 ).getMultiNum() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL#updateBaby(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby)}.
	 */
	@Test
	public void testUpdateBaby() {
		try {
			sql.add( cb );
			sql.addBaby( baby );
			baby.setEstimateDate( true );
			sql.updateBaby( baby );
			assertTrue( sql.getBaby( 1, 0 ).isEstimateDate() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

}
