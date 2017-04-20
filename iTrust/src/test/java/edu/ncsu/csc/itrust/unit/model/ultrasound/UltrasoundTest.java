/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.ultrasound;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;



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
	
	@Test
	public void testGetId() {
		assertEquals( 0, us.getId() );
	}
	
	@Test
	public void testSetId() {
		us.setId( 1 );
		assertEquals( 1, us.getId() );
	}
	
	@Test
	public void testGetOvId() {
		assertEquals( 0, us.getOvId() );
	}
	
	@Test
	public void testSetOvId() {
		us.setOvId( 1 );
		assertEquals( 1, us.getOvId() );
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
		assertEquals( us.getDateCreated().getTime(), Calendar.getInstance().getTimeInMillis(), 1000 );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultrasound.Ultrasound#setDateCreated(java.lang.String)}.
	 */
	@Test
	public void testSetDateCreated() {
		Date d = new Date( Calendar.getInstance().getTimeInMillis() );
		us.setDateCreated( d );
		assertTrue( us.getDateCreated().equals( d ) );
	}

	@Test
	public void testGetPicPath() {
		assertTrue( us.getPicPath().equals( "" ) );
	}
	
	@Test
	public void testSetPicPath() {
		us.setPicPath( "img" );
		assertTrue( us.getPicPath().equals( "img" ) );
	}
	
	@Test
	public void testGetImg() {
		assertNull( us.getImg() );
	}
	
	@Test
	public void testSetImg() {
		File f = new File( "testing-files/fitness_data/fitbit_export_HW3.csv" );
		InputStream in = null;
		try {
			in = new FileInputStream( f );
		} catch (FileNotFoundException e) {
			fail();
		}
		us.setImg( in );
		assertEquals( in, us.getImg() );
	}

}
