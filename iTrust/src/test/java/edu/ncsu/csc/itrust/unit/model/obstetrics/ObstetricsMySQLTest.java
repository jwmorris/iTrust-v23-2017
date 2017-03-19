package edu.ncsu.csc.itrust.unit.model.obstetrics;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
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

public class ObstetricsMySQLTest {
	
	private ObstetricsMySQL sql;
	private TestDataGenerator gen;
	@Before
	public void setUp() throws Exception {
		sql = null;
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}
	
	private boolean equalObstetricsPregnancy( ObstetricsPregnancy op1, ObstetricsPregnancy op2 ) {
		return op1.getDateInit() == op2.getDateInit()
				&& op1.getLmp() == op2.getLmp()
				&& op1.getEdd() == op2.getEdd()
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
		//need princess peach pid
		op.setPid( 2 );
		op.setDateInit( initDate );
		op.setLmp( lmp );
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
			list = sql.getAll();
		} catch ( DBException e ) {
			fail();
		}
		
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
		//fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		ObstetricsPregnancy op = null;
		try {
			list = sql.getAll();
		} catch ( DBException e ) {
			fail();
		}
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			list = sql.getAll();
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertEquals( 1, list.size() );
	}

	@Test
	public void testUpdate() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		ObstetricsPregnancy op = null;
		try {
			list = sql.getAll();
		} catch ( DBException e ) {
			fail();
		}
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.set( 2017, 1, 1 );
			op = newObstetricsPregnancy( new Date( Calendar.getInstance().getTimeInMillis() ), new Date( cal.getTimeInMillis() ) );
			sql.add( op );
			op.setBabyCount( 2 );
			op.setMultiplePregnancy( true );
			sql.update( op );
		} catch (DBException | FormValidationException e) {
			fail();
		}
		
		assertTrue( equalObstetricsPregnancy( op, list.get( 0 ) ) );
	}

	@Test
	public void testGetObstetricsPregnancy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPastObstetricsPregnanciesForPatient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentObstetricsPregnancy() {
		fail("Not yet implemented");
	}

}
