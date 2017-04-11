package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class ObstetricsVisitControllerTest {
	
	private ObstetricsVisitController controller;
	private ObstetricsOfficeVisit ov;
	private SessionUtils mockSessionUtils;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		
		gen.standardData();
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		Mockito.doReturn(Long.parseLong( "9000000012" ) ).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doReturn(Long.parseLong( "2" )).when(mockSessionUtils).getCurrentPatientMIDLong();
		
		controller = Mockito.spy( new ObstetricsVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils ) );
		ov = new ObstetricsOfficeVisit();
		ov.setBp( "80/120" );
		ov.setFhr( "145" );
		ov.setPid( 2 );
		ov.setLowLying( false );
		ov.setMultiplePregnancy( false );
		ov.setNumBabies( "1" );
		ov.setWeeksPregnant( "45" );
		ov.setWeight("100");
		
	}

	@Test
	public void testObstetricsVisitController() {
		try {
			new ObstetricsVisitController();
			//fail("Should throw an exception");
		} catch( Exception e ) {
			//pass
		}
	}

	@Test
	public void testObstetricsVisitControllerDataSource() {
		try {
			controller = new ObstetricsVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
			//pass
		} catch ( Exception e ) {
			fail("An exception should not be thrown");
		}
	}

	@Test
	public void testAddReturnGeneratedId() {
		long id = controller.addReturnGeneratedId( ov );
		assertTrue( id != 0 );
		List<ObstetricsOfficeVisit> visits = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( visits );
		assertEquals( 1, visits.size() );
		assertEquals( id, visits.get( 0 ).getId() );
	}

	@Test
	public void testAdd() {
		controller.add( ov );
		List<ObstetricsOfficeVisit> visits = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( visits );
		assertEquals( 1, visits.size() );
		assertEquals( "80/120", visits.get( 0 ).getBp() );
	}

	@Test
	public void testGetObstetricsVisitsForPatient() {
		List<ObstetricsOfficeVisit> list = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNull( list );
		controller.add( ov );
		list = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( list );
		assertEquals( 1, list.size() );
	}

	@Test
	public void testGetOfficeVisitsForCurrentPatient() {
		List<ObstetricsOfficeVisit> list = controller.getOfficeVisitsForCurrentPatient();
		assertNull( list );
		controller.add( ov );
		list = controller.getOfficeVisitsForCurrentPatient();
		assertNotNull( list );
		assertEquals( 1, list.size() );
	}

	@Test
	public void testGetVisitByID() {
		long id = controller.addReturnGeneratedId( ov );
		
		assertNotEquals( "0", Long.toString( id ) );
		assertTrue( id != 0 );
		ObstetricsOfficeVisit visit = controller.getVisitByID( Long.toString( id ) );
		assertNotNull( visit );
		assertEquals( id, visit.getId() );
		assertEquals( ov.getBp(), visit.getBp() );
		assertEquals( ov.getFhr(), visit.getFhr() );
		assertEquals( ov.getNumBabies(), visit.getNumBabies() );
	}

	@Test
	public void testGetSelectedVisit() {
		assertNull( controller.getSelectedVisit() );
	}

	@Test
	public void testHasVisits() {
		assertFalse( controller.hasVisits( "2" ) );
		controller.add( ov );
		assertTrue( controller.hasVisits( "2" ) );
	}

	@Test
	public void testCurrentPatientHasVisits() {
		assertFalse( controller.currentPatientHasVisits() );
		controller.add( ov );
		assertFalse( controller.currentPatientHasVisits() );
	}

	@Test
	public void testEdit() {
		long id = controller.addReturnGeneratedId( ov );
		ov.setId( id );
		ov.setBp( "90/120" );
		controller.edit( ov );
		ObstetricsOfficeVisit visit = controller.getVisitByID( Long.toString( id ) );
		assertNotNull( visit );
		assertEquals( ov.getBp(), visit.getBp() );
		assertEquals( ov.getFhr(), visit.getFhr() );
		
	}

	@Test
	public void testGetFeti() {
		long id = controller.addReturnGeneratedId( ov );
		List<Fetus> feti = controller.getFeti( id );
		assertNull( feti );
		Fetus f = new Fetus();
		f.setAc( "2" );
		f.setBpd( "2" );
		f.setCrl( "2" );
		f.setEfw( "2" );
		f.setFl( "2" );
		f.setHc( "2" );
		f.setHl( "2" );
		f.setMultiNum( 0 );
		f.setOfd( "2" );
		f.setOvId( id );
		controller.addFetus( f );
		feti = controller.getFeti( id );
		assertNotNull( feti );
		assertEquals( 1, feti.size() );
	}

	@Test
	public void testAddFetus() {
		long id = controller.addReturnGeneratedId( ov );
		Fetus f = new Fetus();
		f.setAc( "2" );
		f.setBpd( "2" );
		f.setCrl( "2" );
		f.setEfw( "2" );
		f.setFl( "2" );
		f.setHc( "2" );
		f.setHl( "2" );
		f.setMultiNum( 0 );
		f.setOfd( "2" );
		f.setOvId( id );
		controller.addFetus( f );
		List<Fetus> feti = controller.getFeti( id );
		assertNotNull( feti );
		assertEquals( 1, feti.size() );
	}

	@Test
	public void testAddUltrasound() {
		long id = controller.addReturnGeneratedId( ov );
		Ultrasound us = new Ultrasound();
		Date d = new Date( Calendar.getInstance().getTimeInMillis() );
		us.setDateCreated( d );
		us.setPicPath( "image.jpg" );
		us.setPid( 2 );
		us.setId( id );
		InputStream s = Mockito.mock(InputStream.class);
		us.setImg( s );
		us.setOvId( id );
		controller.addUltrasound( us );
		List<Ultrasound> list = controller.getUltrasounds( id );
		assertNotNull( list );
		assertEquals( 1, list.size() );
		
		assertEquals( us.getPicPath(), list.get( 0 ).getPicPath() );
		
	}

	@Test
	public void testIsOBGYN() {
		assertTrue( controller.isOBGYN() );
		Mockito.doReturn(Long.parseLong( "9000000002" ) ).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doReturn(Long.parseLong( "2" )).when(mockSessionUtils).getCurrentPatientMIDLong();
		
		ObstetricsVisitController oc = new ObstetricsVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
		assertFalse( oc.isOBGYN() );
	}

	@Test
	public void testIsValidObstetricsPatient() {
		assertTrue( controller.isValidObstetricsPatient() );
	}

	@Test
	public void testIsRHChecked() {
		controller.add( ov );
		assertFalse( controller.isRHChecked() );
		
	}

	@Test
	public void testLogViewObstetricsVisit() {
		long id = controller.addReturnGeneratedId( ov );
		controller.logViewObstetricsVisit( id );
		Mockito.verify(controller, 
				Mockito.times(1)).logTransaction(TransactionType.VIEW_OBSTETRIC_OFFICE_VISIT, Long.parseLong( "9000000012" ), (long)2, Long.toString( id ) );
	}

	@Test
	public void testGetUltrasounds() {
		long id = controller.addReturnGeneratedId( ov );
		Ultrasound us = new Ultrasound();
		Date d = new Date( Calendar.getInstance().getTimeInMillis() );
		us.setDateCreated( d );
		us.setPicPath( "image.jpg" );
		us.setPid( 2 );
		us.setId( id );
		InputStream s = Mockito.mock(InputStream.class);
		us.setImg( s );
		us.setOvId( id );
		controller.addUltrasound( us );
		List<Ultrasound> list = controller.getUltrasounds( id );
		assertNotNull( list );
		assertEquals( 1, list.size() );
		assertEquals( us.getPicPath(), list.get( 0 ).getPicPath() );
	}

}
