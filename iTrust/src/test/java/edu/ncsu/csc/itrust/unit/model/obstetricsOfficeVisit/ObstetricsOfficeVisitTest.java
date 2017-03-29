/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;

/**
 * tests the functionality of the ObstetricsOfficeVisit class
 * 
 * @author wyattmaxey
 *
 */
public class ObstetricsOfficeVisitTest {
	
	private ObstetricsOfficeVisit ov;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ov = new ObstetricsOfficeVisit();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals( 0, ov.getId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setId(long)}.
	 */
	@Test
	public void testSetId() {
		ov.setId( 1 );
		assertEquals( 1, ov.getId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getPid()}.
	 */
	@Test
	public void testGetPid() {
		assertEquals( 0, ov.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setPid(long)}.
	 */
	@Test
	public void testSetPid() {
		ov.setPid( 1 );
		assertEquals( 1, ov.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getVisitDate()}.
	 */
	@Test
	public void testGetVisitDate() {
		assertTrue( DATE_FORMAT.format( new Date( Calendar.getInstance().getTimeInMillis() ) ).equals( DATE_FORMAT.format( ov.getVisitDate() ) ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setVisitDate(java.sql.Date)}.
	 */
	@Test
	public void testSetVisitDate() {
		ov.setVisitDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		assertTrue( DATE_FORMAT.format( new Date( Calendar.getInstance().getTimeInMillis() ) ).equals( DATE_FORMAT.format( ov.getVisitDate() ) ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getWeeksPregnant()}.
	 */
	@Test
	public void testGetWeeksPregnant() {
		assertTrue( ov.getWeeksPregnant().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setWeeksPregnant(java.lang.String)}.
	 */
	@Test
	public void testSetWeeksPregnant() {
		ov.setWeeksPregnant( "1" );
		assertTrue( ov.getWeeksPregnant().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getWeight()}.
	 */
	@Test
	public void testGetWeight() {
		assertTrue( ov.getWeight().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setWeight(java.lang.String)}.
	 */
	@Test
	public void testSetWeight() {
		ov.setWeight( "1" );
		assertTrue( ov.getWeight().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getBp()}.
	 */
	@Test
	public void testGetBp() {
		assertTrue( ov.getBp().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setBp(java.lang.String)}.
	 */
	@Test
	public void testSetBp() {
		ov.setBp( "1" );
		assertTrue( ov.getBp().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getFhr()}.
	 */
	@Test
	public void testGetFhr() {
		assertTrue( ov.getFhr().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setFhr(java.lang.String)}.
	 */
	@Test
	public void testSetFhr() {
		ov.setFhr( "1" );
		assertTrue( ov.getFhr().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#isMultiplePregnancy()}.
	 */
	@Test
	public void testIsMultiplePregnancy() {
		assertFalse( ov.isMultiplePregnancy() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setMultiplePregnancy(boolean)}.
	 */
	@Test
	public void testSetMultiplePregnancy() {
		ov.setMultiplePregnancy( true );
		assertTrue( ov.isMultiplePregnancy() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#getNumBabies()}.
	 */
	@Test
	public void testGetNumBabies() {
		assertTrue( ov.getNumBabies().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setNumBabies(java.lang.String)}.
	 */
	@Test
	public void testSetNumBabies() {
		ov.setNumBabies( "1" );
		assertTrue( ov.getNumBabies().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#isLowLying()}.
	 */
	@Test
	public void testIsLowLying() {
		assertFalse( ov.isLowLying() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit#setLowLying(boolean)}.
	 */
	@Test
	public void testSetLowLying() {
		ov.setLowLying( true );
		assertTrue( ov.isLowLying() );
	}

}
