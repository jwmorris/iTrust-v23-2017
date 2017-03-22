package edu.ncsu.csc.itrust.unit.model.obstetrics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsValidator;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class ObstetricsValidatorTest {
	private TestDataGenerator gen;
	
	private ObstetricsPregnancy op;

	@Before
	public void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		op = new ObstetricsPregnancy();
		op.setPid( 2 );
	}

	@Test
	public void testValidateObstetricsPregnancy() {
		ObstetricsValidator validator = new ObstetricsValidator( ConverterDAO.getDataSource() );
		
		
		op.setDateInit( null );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Init" ) );
		}
		op.setDateInit( "03/20/2017" );
		
		op.setLmp( "" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "LMP" ) );
		}
		op.setLmp( "01/01/2017" );
		
		op.setWeeksPregnant( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setWeeksPregnant( "2" );
		
		op.setWeeksPregnant( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setWeeksPregnant( "2" );
		
		op.setWeeksPregnant( "55" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setWeeksPregnant( "2" );
		
		op.setConcepYear( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Year" ) );
		}
		op.setConcepYear( "2017" );
		
		op.setConcepYear( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Year" ) );
		}
		op.setConcepYear( "2017" );
		
		op.setConcepYear( "3025" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Year" ) );
		}
		op.setConcepYear( "2017" );
		
		op.setTotalWeeksPregnant( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setTotalWeeksPregnant( "7" );
		
		op.setTotalWeeksPregnant( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setTotalWeeksPregnant( "7" );
		
		op.setTotalWeeksPregnant( "55" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weeks" ) );
		}
		op.setTotalWeeksPregnant( "7" );
		
		op.setHoursLabor( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Labor" ) );
		}
		op.setHoursLabor( "5" );
		
		op.setHoursLabor( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Labor" ) );
		}
		op.setHoursLabor( "5" );
		
		op.setWeightGain( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weight" ) );
		}
		op.setWeightGain( "5" );
		
		op.setWeightGain( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Weight" ) );
		}
		op.setWeightGain( "5" );
		
		op.setBabyCount( "w" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Babies" ) );
		}
		op.setBabyCount( "1" );
		
		op.setBabyCount( "-1" );
		try {
			validator.validate( op );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Babies" ) );
		}
		op.setBabyCount( "2" );
		
		try {
			validator.validate( op );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "more than one baby" ) );
		}
		op.setMultiplePregnancy( true );
		
		op.setBabyCount( "1" );
		try {
			validator.validate( op );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "More than one baby" ) );
		}
		op.setMultiplePregnancy( false );
		
		op.setDeliveryType( "magic" );
		try {
			validator.validate( op );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Delivery" ) );
		}
	}

}
