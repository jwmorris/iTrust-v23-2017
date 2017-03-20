package edu.ncsu.csc.itrust.unit.model.obstetrics;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	@Before
	public void setUp() throws Exception {
		try {
			sql = new ObstetricsMySQL( ConverterDAO.getDataSource() );
		} catch (DBException e) {
			fail();
		}
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}
	
	private boolean equalObstetricsPregnancy( ObstetricsPregnancy op1, ObstetricsPregnancy op2 ) {
		return op1.getDateInit().toString().equals( op2.getDateInit().toString() )
				&& op1.getLmp().toString().equals( op2.getLmp().toString() )
				&& op1.getEdd().toString().equals( op2.getEdd().toString() )
				&& op1.getWeeksPregnant() == op2.getWeeksPregnant()
				&& op1.getConcepYear() == op2.getConcepYear()
				&& op1.getTotalWeeksPregnant() == op2.getTotalWeeksPregnant()
				&& op1.getHrsLabor() == op2.getHrsLabor()
				&& op1.getDeliveryType().equals( op2.getDeliveryType() )
				&& op1.getMultiplePregnancy() == op2.getMultiplePregnancy()
				&& op1.getBabyCount() == op2.getBabyCount()
				&& op1.getCurrent() == op2.getCurrent();
				
	}
	
	private ObstetricsPregnancy newObstetricsPregnancy( Date initDate, Date lmp ) {
		ObstetricsPregnancy op = new ObstetricsPregnancy();
		op.setPid( 2 );
		op.setDateInit( DATE_FORMAT.format( initDate ) );
		op.setLmp( DATE_FORMAT.format( lmp ) );
		op.setConcepYear( 0 );
		op.setTotalWeeksPregnant( 0 );
		op.setHrsLabor( 0 );
		op.setWeightGain( 0 );
		op.setDeliveryType( "" );
		op.setMultiplePregnancy( false );
		op.setBabyCount( 1 );
		op.setCurrent( true );
		return op;
	}
	
	@Test
	public void testConstructor() {
		try {
			sql = new ObstetricsMySQL();
			fail();
		} catch ( DBException e ) {
			//should be thrown
		}
		
		try {
			sql = new ObstetricsMySQL( ConverterDAO.getDataSource() );
		} catch (DBException e) {
			fail();
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

		assertTrue( equalObstetricsPregnancy( op, list.get( 0 ) ) );
		
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
		
		assertTrue( equalObstetricsPregnancy( op, res ) );
	}

	@Test
	public void testAdd() {
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
		
		try {
			assertTrue( equalObstetricsPregnancy( op, sql.getObstetricsPregnancy( 2, op.getDateInit() ) ) );
		} catch (DBException e) {
			fail();
		}
	}

	@Test
	public void testUpdate() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		ObstetricsPregnancy op = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			op.setBabyCount( 2 );
			op.setMultiplePregnancy( true );
			sql.update( op );
			list = sql.getAll();
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( equalObstetricsPregnancy( op, list.get( 0 ) ) );
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
		
		assertTrue( equalObstetricsPregnancy( op, res ) );
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
			list = sql.getPastObstetricsPregnanciesForPatient( 2 );
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
			res = sql.getCurrentObstetricsPregnancy( 2 );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( equalObstetricsPregnancy( op, res ) );
	}

}
