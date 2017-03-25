/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.ultrasound;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.ultrasound.Ultrasound;

/**
 * tests the functionality of the Ultasound class
 * 
 * @author wyattmaxey
 */
public class UltrasoundTest {
	private Ultrasound us;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		us = new Ultrasound();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getPid()}.
	 */
	@Test
	public void testGetPid() {
		assertEquals( 0, us.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setPid(long)}.
	 */
	@Test
	public void testSetPid() {
		us.setPid( 2 );
		assertEquals( 2, us.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getDateCreated()}.
	 */
	@Test
	public void testGetDateCreated() {
		assertTrue( us.getDateCreated().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setDateCreated(java.lang.String)}.
	 */
	@Test
	public void testSetDateCreated() {
		us.setDateCreated( "03/24/2017" );
		assertTrue( us.getDateCreated().equals( "03/24/2017" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getCrl()}.
	 */
	@Test
	public void testGetCrl() {
		assertEquals( 0, ( int )us.getCrl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setCrl(float)}.
	 */
	@Test
	public void testSetCrl() {
		us.setCrl( ( float )4.0 );
		assertEquals( 4, ( int )us.getCrl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getBpd()}.
	 */
	@Test
	public void testGetBpd() {
		assertEquals( 0, ( int )us.getBpd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setBpd(float)}.
	 */
	@Test
	public void testSetBpd() {
		us.setBpd( ( float )4.0 );
		assertEquals( 4, ( int )us.getBpd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getHc()}.
	 */
	@Test
	public void testGetHc() {
		assertEquals( 0, ( int )us.getHc() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setHc(float)}.
	 */
	@Test
	public void testSetHc() {
		us.setHc( ( float )4.0 );
		assertEquals( 4, ( int )us.getHc() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getFl()}.
	 */
	@Test
	public void testGetFl() {
		assertEquals( 0, ( int )us.getFl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setFl(float)}.
	 */
	@Test
	public void testSetFl() {
		us.setFl( ( float )4.0 );
		assertEquals( 4, ( int )us.getFl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getOfd()}.
	 */
	@Test
	public void testGetOfd() {
		assertEquals( 0, ( int )us.getOfd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setOfd(float)}.
	 */
	@Test
	public void testSetOfd() {
		us.setOfd( ( float )4.0 );
		assertEquals( 4, ( int )us.getOfd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getAc()}.
	 */
	@Test
	public void testGetAc() {
		assertEquals( 0, ( int )us.getAc() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setAc(float)}.
	 */
	@Test
	public void testSetAc() {
		us.setAc( ( float )4.0 );
		assertEquals( 4, ( int )us.getAc() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getHl()}.
	 */
	@Test
	public void testGetHl() {
		assertEquals( 0, ( int )us.getHl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setHl(float)}.
	 */
	@Test
	public void testSetHl() {
		us.setHl( ( float )4.0 );
		assertEquals( 4, ( int )us.getHl() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getEfw()}.
	 */
	@Test
	public void testGetEfw() {
		assertEquals( 0, ( int )us.getEfw() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setEfw(float)}.
	 */
	@Test
	public void testSetEfw() {
		us.setEfw( ( float )4.0 );
		assertEquals( 4, ( int )us.getEfw() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getPicturePath()}.
	 */
	@Test
	public void testGetPicturePath() {
		assertTrue( us.getPicturePath().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setPicturePath(java.lang.String)}.
	 */
	@Test
	public void testSetPicturePath() {
		us.setPicturePath( "/ultrasound/photos/us1.jpg" );
		assertTrue( us.getPicturePath().equals( "/ultrasound/photos/us1.jpg" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#getMultiNum()}.
	 */
	@Test
	public void testGetMultiNum() {
		assertEquals( 0, us.getMultiNum() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setMultiNum(int)}.
	 */
	@Test
	public void testSetMultiNum() {
		us.setMultiNum( 2 );
		assertEquals( 2, us.getMultiNum() );
	}

}
