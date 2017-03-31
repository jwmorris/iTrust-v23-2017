/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;

/**
 * tests the functionality of the baby pojo
 * 
 * @author wyattmaxey
 */
public class BabyTest {
	private Baby baby;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		baby = new Baby();
		baby.setChildbirthId( 1 );
		baby.setDate( "03/30/2017" );
		baby.setEstimateDate( false );
		baby.setMultiNum( 0 );
		baby.setSex( 'm' );
		baby.setTime( "21:00" );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#getChildbirthId()}.
	 */
	@Test
	public void testGetChildbirthId() {
		assertEquals( 1, baby.getChildbirthId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertTrue( baby.getDate().equals( "03/30/2017" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#isEstimateDate()}.
	 */
	@Test
	public void testIsEstimateDate() {
		assertFalse( baby.isEstimateDate() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#getTime()}.
	 */
	@Test
	public void testGetTime() {
		assertTrue( baby.getTime().equals( "21:00" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#getSex()}.
	 */
	@Test
	public void testGetSex() {
		assertEquals( 'm', baby.getSex() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby#getMultiNum()}.
	 */
	@Test
	public void testGetMultiNum() {
		assertEquals( 0, baby.getMultiNum() );
	}

}
