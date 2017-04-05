/**
 * 
 */
package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * tests the functionality of the ChilbirthVisitForm
 * 
 * @author wyattmaxey
 */
public class ChildbirthVisitFormTest {
	
	private ChildbirthVisitForm form;
	
	private SessionUtils mockSessionUtils;
	
	private ChildbirthVisitController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		Mockito.doReturn(Long.parseLong( "9000000012" ) ).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doReturn(Long.parseLong( "2" )).when(mockSessionUtils).getCurrentPatientMIDLong();
		mockSessionUtils.setSessionVariable( "pid", 2 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 2 );
		
		//controller = Mockito.spy( new ChildbirthVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils ) );
		//form = Mockito.spy( new ChildbirthVisitForm( controller, mockSessionUtils ) );
		
		controller = new ChildbirthVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
		form = new ChildbirthVisitForm( controller, mockSessionUtils );
		
		form.setAmtEpidural( "1" );
		form.setAmtMagnesium( "1" );
		form.setAmtNitrous( "1" );
		form.setAmtPethidine( "1" );
		form.setAmtPitocin( "1" );
		form.setAmtRH( "1" );
		form.setBabyDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		form.setBabyId( 1 );
		form.setChildbirthID( 1 );
		form.setDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		form.setDeliveryType( "vaginal delivery" );
		form.setEditBaby( false );
		form.setEr( false );
		form.setErString( "false" );
		form.setEstimateDate( false );
		form.setMultiNum( 0 );
		form.setNumBabies( 0 );
		form.setPid( 2 );
		form.setSex( 'f' );
		form.setBabyDeliveryType( "vaginal delivery" );
		form.setTimeHour( "06" );
		form.setTimeMinute( "30" );
		form.setTimeMerridean( "pm" );
		form.setTime();
	}
	
	@Test
	public void testChildbirthVisitForm() {
		form = null;
		form = new ChildbirthVisitForm( controller );
		assertFalse( form == null );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getPid()}.
	 */
	@Test
	public void testGetPid() {
		assertEquals( 2, form.getPid() );
	}


	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getChildbirthID()}.
	 */
	@Test
	public void testGetChildbirthID() {
		assertEquals( 1, form.getChildbirthID() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals( Calendar.getInstance().getTimeInMillis(), form.getDate().getTime() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#isEr()}.
	 */
	@Test
	public void testIsEr() {
		assertFalse( form.isEr() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getDeliveryType()}.
	 */
	@Test
	public void testGetDeliveryType() {
		assertTrue( form.getDeliveryType().equals( "vaginal delivery" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getAmtPitocin()}.
	 */
	@Test
	public void testGetAmtPitocin() {
		assertTrue( form.getAmtPitocin().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getAmtNitrous()}.
	 */
	@Test
	public void testGetAmtNitrous() {
		assertTrue( form.getAmtNitrous().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getAmtPethidine()}.
	 */
	@Test
	public void testGetAmtPethidine() {
		assertTrue( form.getAmtPethidine().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getAmtEpidural()}.
	 */
	@Test
	public void testGetAmtEpidural() {
		assertTrue( form.getAmtEpidural().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getAmtMagnesium()}.
	 */
	@Test
	public void testGetAmtMagnesium() {
		assertTrue( form.getAmtMagnesium().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#isEstimateDate()}.
	 */
	@Test
	public void testIsEstimateDate() {
		assertFalse( form.isEstimateDate() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getBabyDate()}.
	 */
	@Test
	public void testGetBabyDate() {
		form.setBabyDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		assertEquals( Calendar.getInstance().getTimeInMillis(), form.getDate().getTime(), 4 );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getTime()}.
	 */
	@Test
	public void testGetTime() {
		assertTrue( form.getTime().equals( "06:30 pm" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getTimeHour()}.
	 */
	@Test
	public void testGetTimeHour() {
		assertTrue( form.getTimeHour().equals( "06" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getTimeMinute()}.
	 */
	@Test
	public void testGetTimeMinute() {
		assertTrue( form.getTimeMinute().equals( "30" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getTimeMerridean()}.
	 */
	@Test
	public void testGetTimeMerridean() {
		assertTrue( form.getTimeMerridean().equals( "pm" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getSex()}.
	 */
	@Test
	public void testGetSex() {
		assertEquals( 'f', form.getSex() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getMultiNum()}.
	 */
	@Test
	public void testGetMultiNum() {
		assertEquals( 0, form.getMultiNum() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getNumBabies()}.
	 */
	@Test
	public void testGetNumBabies() {
		assertEquals( 0, form.getNumBabies() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getBabyId()}.
	 */
	@Test
	public void testGetBabyId() {
		assertEquals( 1, form.getBabyId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#isEditBaby()}.
	 */
	@Test
	public void testIsEditBaby() {
		assertFalse( form.isEditBaby() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#getBabies()}.
	 */
	@Test
	public void testGetBabies() {
		form.submitChildbirthVisit();
		form.submitBaby();
		assertEquals( 1, form.getBabies().size() );
	}
	
	@Test
	public void testEditBabies() {
		form.submitChildbirthVisit();
		//form.setBabyId( 2 );
		form.submitBaby();
		form.editBabies();
		assertTrue( form.isEditBaby() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#submitChildbirthVisit()}.
	 */
	@Test
	public void testSubmitChildbirthVisit() {
		//form.setPid( 2 );
		form.setChildbirthID( 0 );
		form.submitChildbirthVisit();
		assertEquals( 1, controller.getChildbirthsForPatient( 2 ).size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm#submitBaby()}.
	 */
	@Test
	public void testSubmitBaby() {
		form.submitBaby();
		assertEquals( 1, form.getBabies().size() );
	}

}
