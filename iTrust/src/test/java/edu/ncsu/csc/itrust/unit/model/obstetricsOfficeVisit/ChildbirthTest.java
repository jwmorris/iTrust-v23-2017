/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;

/**
 * tests functionality of the childbirth pojo
 * 
 * @author wyattmaxey
 */
public class ChildbirthTest {
	private Childbirth cb;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getPid()}.
	 */
	@Test
	public void testGetPid() {
		assertEquals( 1, cb.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getchildbirthId()}.
	 */
	@Test
	public void testGetchildbirthId() {
		assertEquals( 1, cb.getchildbirthId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isEr()}.
	 */
	@Test
	public void testIsEr() {
		assertFalse( cb.isEr() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getDeliveryType()}.
	 */
	@Test
	public void testGetDeliveryType() {
		assertTrue( cb.getDeliveryType().equals( "Vaginal" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isPitocin()}.
	 */
	@Test
	public void testIsPitocin() {
		assertTrue( cb.isPitocin() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getAmtPitocin()}.
	 */
	@Test
	public void testGetAmtPitocin() {
		assertTrue( cb.getAmtPitocin().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isNitrous()}.
	 */
	@Test
	public void testIsNitrous() {
		assertTrue( cb.isNitrous() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getAmtNitrous()}.
	 */
	@Test
	public void testGetAmtNitrous() {
		assertTrue( cb.getAmtNitrous().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isPethidine()}.
	 */
	@Test
	public void testIsPethidine() {
		assertTrue( cb.isPethidine() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getAmtPethidine()}.
	 */
	@Test
	public void testGetAmtPethidine() {
		assertTrue( cb.getAmtPethidine().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isEpidural()}.
	 */
	@Test
	public void testIsEpidural() {
		assertTrue( cb.isEpidural() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getAmtEpidural()}.
	 */
	@Test
	public void testGetAmtEpidural() {
		assertTrue( cb.getAmtEpidural().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#isMagnesium()}.
	 */
	@Test
	public void testIsMagnesium() {
		assertTrue( cb.isMagnesium() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth#getAmtMagnesium()}.
	 */
	@Test
	public void testGetAmtMagnesium() {
		assertTrue( cb.getAmtMagnesium().equals( "1" ) );
	}

}
