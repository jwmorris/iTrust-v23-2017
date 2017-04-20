/**
 * 
 */
package edu.ncsu.csc.itrust.unit.webutils;

import static org.junit.Assert.*;

import java.beans.Beans;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.webutils.ColorBean;

/**
 * @author wyatt
 *
 */
public class ColorBeanTest {
	private ColorBean bean;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bean = new ColorBean();
		bean.setPid( 7 );
		bean.setErrorText( "error" );
		bean.setFooterBackground( "footer" );
		bean.setFooterText( "footer" );
		bean.setLeftMenuBackground( "left" );
		bean.setNavigationBarBackground( "nav" );
		bean.setNavigationBarText( "nav" );
		bean.setPrimaryBackground( "primary" );
		bean.setPrimaryText( "primary" );
		bean.setSelectedPatient( "patient" );
		bean.setTableHeadingBackground( "heading" );
		bean.setTableHeadingText( "heading" );
		bean.setTableRowBackground1( "row" );
		bean.setTableRowBackground2( "row" );
		bean.setWelcomeText( "welcome" );
		
		bean.setErrorText( "#error" );
		bean.setFooterBackground( "#footer" );
		bean.setFooterText( "#footer" );
		bean.setLeftMenuBackground( "#left" );
		bean.setNavigationBarBackground( "#nav" );
		bean.setNavigationBarText( "#nav" );
		bean.setPrimaryBackground( "#primary" );
		bean.setPrimaryText( "#primary" );
		bean.setSelectedPatient( "#patient" );
		bean.setTableHeadingBackground( "#heading" );
		bean.setTableHeadingText( "#heading" );
		bean.setTableRowBackground1( "#row" );
		bean.setTableRowBackground2( "#row" );
		bean.setWelcomeText( "#welcome" );

	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getPid()}.
	 */
	@Test
	public void testGetPid() {
		assertEquals( 7, bean.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getPrimaryText()}.
	 */
	@Test
	public void testGetPrimaryText() {
		assertTrue( "#primary".equals( bean.getPrimaryText() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getPrimaryBackground()}.
	 */
	@Test
	public void testGetPrimaryBackground() {
		assertTrue( "#primary".equals( bean.getPrimaryBackground() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getLeftMenuBackground()}.
	 */
	@Test
	public void testGetLeftMenuBackground() {
		assertTrue( "#left".equals( bean.getLeftMenuBackground() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getNavigationBarText()}.
	 */
	@Test
	public void testGetNavigationBarText() {
		assertTrue( "#nav".equals( bean.getNavigationBarText() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getNavigationBarBackground()}.
	 */
	@Test
	public void testGetNavigationBarBackground() {
		assertTrue( "#nav".equals( bean.getNavigationBarBackground() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getWelcomeText()}.
	 */
	@Test
	public void testGetWelcomeText() {
		assertTrue( "#welcome".equals( bean.getWelcomeText() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getFooterText()}.
	 */
	@Test
	public void testGetFooterText() {
		assertTrue( "#footer".equals( bean.getFooterText() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getFooterBackground()}.
	 */
	@Test
	public void testGetFooterBackground() {
		assertTrue( "#footer".equals( bean.getFooterBackground() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getTableHeadingText()}.
	 */
	@Test
	public void testGetTableHeadingText() {
		assertTrue( "#heading".equals( bean.getTableHeadingText() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getTableHeadingBackground()}.
	 */
	@Test
	public void testGetTableHeadingBackground() {
		assertTrue( "#heading".equals( bean.getTableHeadingBackground() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getTableRowBackground1()}.
	 */
	@Test
	public void testGetTableRowBackground1() {
		assertTrue( "#row".equals( bean.getTableRowBackground1() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getTableRowBackground2()}.
	 */
	@Test
	public void testGetTableRowBackground2() {
		assertTrue( "#row".equals( bean.getTableRowBackground2() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getSelectedPatient()}.
	 */
	@Test
	public void testGetSelectedPatient() {
		assertTrue( "#patient".equals( bean.getSelectedPatient() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.webutils.ColorBean#getErrorText()}.
	 */
	@Test
	public void testGetErrorText() {
		assertTrue( "#error".equals( bean.getErrorText() ) );
	}

}
