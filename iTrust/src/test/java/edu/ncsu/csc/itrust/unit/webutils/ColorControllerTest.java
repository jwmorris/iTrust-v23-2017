package edu.ncsu.csc.itrust.unit.webutils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.webutils.ColorController;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class ColorControllerTest {
	private ColorController con;
	private SessionUtils utils;

	@Before
	public void setUp() throws Exception {
		utils = Mockito.mock( SessionUtils.class );
		Mockito.doReturn( Long.parseLong( "9000000012" ) ).when( utils ).getSessionLoggedInMIDLong();
		con = new ColorController( ConverterDAO.getDataSource(), utils );
	}

	@Test
	public void testColorController() {
		try {
			new ColorController();
		} catch ( Exception e ) {
			//pass
		}
	}

	@Test
	public void testSetPrimaryText() {
		con.setPrimaryText( "#444" );
		assertEquals( "#444", con.getPrimaryText() );
	}


	@Test
	public void testSetPrimaryBackground() {
		con.setPrimaryBackground( "#444" );
		assertEquals( "#444", con.getPrimaryBackground() );
	}


	@Test
	public void testSetLeftMenuBackground() {
		con.setLeftMenuBackground( "#444" );
		assertEquals( "#444", con.getLeftMenuBackground() );
		
	}


	@Test
	public void testSetNavigationBarText() {
		con.setNavigationBarText( "#444" );
		assertEquals( "#444", con.getNavigationBarText() );
	}


	@Test
	public void testSetNavigationBarBackground() {
		con.setNavigationBarBackground( "#444" );
		assertEquals( "#444", con.getNavigationBarBackground() );
	}


	@Test
	public void testSetWelcomeText() {
		con.setWelcomeText( "#444" );
		assertEquals( "#444", con.getWelcomeText() );
	}


	@Test
	public void testSetFooterText() {
		con.setFooterText( "#444" );
		assertEquals( "#444", con.getFooterText() );
	}


	@Test
	public void testSetFooterBackground() {
		con.setFooterBackground( "#444" );
		assertEquals( "#444", con.getFooterBackground() );
	}


	@Test
	public void testSetTableHeadingText() {
		con.setTableHeadingText( "#444" );
		assertEquals( "#444", con.getTableHeadingText() );
	}



	@Test
	public void testSetTableHeadingBackground() {
		con.setTableHeadingBackground( "#4444" );
		assertEquals( "#4444", con.getTableHeadingBackground() );
	}


	@Test
	public void testSetTableRowBackground1() {
		con.setTableRowBackground1( "#4444" );
		assertEquals( "#4444", con.getTableRowBackground1() );
	}


	@Test
	public void testSetTableRowBackground2() {
		con.setTableRowBackground2( "#4444" );
		assertEquals( "#4444", con.getTableRowBackground2() );
	}


	@Test
	public void testSetSelectedPatient() {
		con.setSelectedPatient( "#444" );
		assertEquals( "#444", con.getSelectedPatient() );
	}


	@Test
	public void testSetErrorText() {
		con.setErrorText( "#444" );
		assertEquals( "#444", con.getErrorText() );
	}

	@Test
	public void testAddDefaultColor() {
		con.addDefaultColor();
	}

	@Test
	public void testAddCustomColor() {
		con.addCustomColor();
	}

	@Test
	public void testGetColor() {
		con.getColor();
	}

}
