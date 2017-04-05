package edu.ncsu.csc.itrust.unit.model.obstetrics;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * tests the functionality of the ObstetricsMySQL class
 * 
 * @author wyattmaxey
 */
public class ObstetricsMySQLTest {
	
	private ObstetricsMySQL sql;
	
	private TestDataGenerator gen;
	
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private ObstetricsPregnancy newObstetricsPregnancy( Date initDate, Date lmp ) {
		ObstetricsPregnancy op = new ObstetricsPregnancy();
		op.setPid( 9 );
		op.setDateInit( DATE_FORMAT.format( initDate ) );
		op.setLmp( DATE_FORMAT.format( lmp ) );
		op.setConcepYear( "0" );
		op.setTotalWeeksPregnant( "0" );
		op.setHoursLabor( "0.0" );
		op.setWeightGain( "0" );
		op.setDeliveryType( "" );
		op.setMultiplePregnancy( false );
		op.setBabyCount( "1" );
		op.setCurrent( true );
		return op;
	}
	
	@Before
	public void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		sql = new ObstetricsMySQL( ds );
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}
	
	@Test
	public void testConstructor() {
		
		try {
			sql = new ObstetricsMySQL();
			fail();
		} catch ( DBException e ) {
			//should be thrown
		}
		
	}

	@Test
	public void testGetAll() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		ObstetricsPregnancy op = null;
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			list = sql.getAll();
		} catch (DBException | FormValidationException e) {
			fail();
		}

		assertTrue( op.getBabyCount().equals( list.get( 0 ).getBabyCount() ) );
		
	}

	@Test
	public void testGetByID() {
		ObstetricsPregnancy op = null;
		ObstetricsPregnancy res = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			res = sql.getByID( 1 );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( op.getBabyCount().equals( res.getBabyCount() ) );
	}

	@Test
	public void testAdd() {
		ObstetricsPregnancy op = null;
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		ObstetricsPregnancy ret = null;
		try {
			ret = sql.getObstetricsPregnancy( 9, op.getDateInit() );
		} catch (DBException e1) {
			fail();
		}
		
		assertTrue( op.getBabyCount().equals( ret.getBabyCount() ) );
	}

	@Test
	public void testUpdate() {
		ObstetricsPregnancy op = null;
		ObstetricsPregnancy res = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			op.setPid( 9 );
			op.setCurrent( true );
			sql.add( op );
			op.setConcepYear( "2013" );
			res = sql.getCurrentObstetricsPregnancy( 9 );
			op.setId( res.getId() );
			sql.update( op );
			res = sql.getCurrentObstetricsPregnancy( 9 );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		assertTrue( res.getConcepYear().equals( "2013" ) );
	}

	@Test
	public void testGetObstetricsPregnancy() {
		ObstetricsPregnancy op = null;
		ObstetricsPregnancy res = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			res = sql.getObstetricsPregnancy( op.getPid(), op.getDateInit() );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( op.getBabyCount().equals( res.getBabyCount() ) );
	}

	@Test
	public void testGetPastObstetricsPregnanciesForPatient() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		ObstetricsPregnancy op1 = null;
		ObstetricsPregnancy op2 = null;
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op1 = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			op1.setCurrent( false );
			sql.add( op1 );
			
			Calendar cal2 = Calendar.getInstance();
			cal2.set( 2016, 1, 1 );
			Calendar cal3 = Calendar.getInstance();
			cal3.set( 2016, 2, 1 );
			op2 = newObstetricsPregnancy( new Date( cal3.getTimeInMillis() ), new Date( cal2.getTimeInMillis() ) );
			op2.setCurrent( false );
			sql.add( op2 );
			list = sql.getPastObstetricsPregnanciesForPatient( 9 );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertEquals( 2, list.size() );
	}

	@Test
	public void testGetCurrentObstetricsPregnancy() {
		ObstetricsPregnancy op = null;
		ObstetricsPregnancy res = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			res = sql.getCurrentObstetricsPregnancy( 9 );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( op.getBabyCount().equals( res.getBabyCount() ) );
	}

}