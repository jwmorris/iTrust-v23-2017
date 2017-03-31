/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthValidator;

/**
 * tests the functionality of the childbirth form validator
 * 
 * @author wyattmaxey
 */
public class ChildbirthValidatorTest {
	private Childbirth cb;
	private ChildbirthValidator validator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		validator = new ChildbirthValidator();
		cb = new Childbirth();
		cb.setPid( 1 );
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
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthValidator#validate(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth)}.
	 * @throws Exception 
	 */
	@Test
	public void testValidateChildbirth() throws Exception {
		try {
			cb.setEr( true );
			cb.setDeliveryType( "forceps assist" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Emergency Room" ) );
			setUp();
		}
		
		try {
			cb.setAmtEpidural( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
			setUp();
		}
		
		try {
			cb.setAmtEpidural( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
			setUp();
		}
		
		try {
			cb.setEpidural( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
		}
		
		try {
			cb.setAmtMagnesium( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
			setUp();
		}
		
		try {
			cb.setAmtMagnesium( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
			setUp();
		}
		
		try {
			cb.setMagnesium( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
		}
		
		try {
			cb.setAmtNitrous( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
			setUp();
		}
		
		try {
			cb.setAmtNitrous( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
			setUp();
		}
		
		try {
			cb.setNitrous( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
		}
		
		try {
			cb.setAmtPethidine( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
			setUp();
		}
		
		try {
			cb.setAmtPethidine( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
			setUp();
		}
		
		try {
			cb.setPethidine( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
		}
		
		try {
			cb.setAmtPitocin( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
			setUp();
		}
		
		try {
			cb.setAmtPitocin( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
			setUp();
		}
		
		try {
			cb.setPitocin( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthValidator#validateEdit(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth)}.
	 * @throws Exception 
	 */
	@Test
	public void testValidateEdit() throws Exception {
		try {
			cb.setEr( true );
			cb.setDeliveryType( "forceps assist" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Emergency Room" ) );
			setUp();
		}
		
		try {
			cb.setAmtEpidural( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
			setUp();
		}
		
		try {
			cb.setAmtEpidural( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
			setUp();
		}
		
		try {
			cb.setEpidural( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Epidural Anaesthesia" ) );
		}
		
		try {
			cb.setAmtMagnesium( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
			setUp();
		}
		
		try {
			cb.setAmtMagnesium( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
			setUp();
		}
		
		try {
			cb.setMagnesium( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Magnesium Sulfate" ) );
		}
		
		try {
			cb.setAmtNitrous( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
			setUp();
		}
		
		try {
			cb.setAmtNitrous( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
			setUp();
		}
		
		try {
			cb.setNitrous( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Nitrous Oxide" ) );
		}
		
		try {
			cb.setAmtPethidine( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
			setUp();
		}
		
		try {
			cb.setAmtPethidine( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
			setUp();
		}
		
		try {
			cb.setPethidine( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pethidine" ) );
		}
		
		try {
			cb.setAmtPitocin( "one" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
			setUp();
		}
		
		try {
			cb.setAmtPitocin( "-1" );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
			setUp();
		}
		
		try {
			cb.setPitocin( false );
			validator.validate( cb );
			fail();
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Pitocin" ) );
		}
	}

}
