package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Part;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitForm;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;
import gherkin.deps.net.iharder.Base64.InputStream;

public class ObstetricsVisitFormTest {
	
	private ObstetricsVisitForm form;
	private SessionUtils mockSessionUtils;
	private ObstetricsVisitController controller;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		
		gen.standardData();
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		Mockito.doReturn(Long.parseLong( "9000000012" ) ).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doReturn(Long.parseLong( "2" )).when(mockSessionUtils).getCurrentPatientMIDLong();
		
		controller = Mockito.spy( new ObstetricsVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils ) );
		form = new ObstetricsVisitForm( controller, mockSessionUtils );
	}

	@Test
	public void testObstetricsVisitForm() {
		new ObstetricsVisitForm();
	}

	@Test
	public void testObstetricsVisitFormObstetricsVisitController() {
		new ObstetricsVisitForm(new ObstetricsVisitController());
	}

	@Test
	public void testGetPid() {
		form.setPid( (long)2 );
		assertEquals( "2", Long.toString( form.getPid() ) );
	}

	@Test
	public void testSetPid() {
		form.setPid( (long)2 );
		assertEquals( "2", Long.toString( form.getPid() ) );
	}

	@Test
	public void testGetVisitID() {
		assertEquals( "0", Long.toString( form.getVisitID() ) );
	}

	@Test
	public void testSetVisitID() {
		form.setVisitID( (long)9 );
		
	}

	@Test
	public void testGetDate() {
		Date d = new Date( Calendar.getInstance().getTimeInMillis() );
		form.setDate( d );
		assertEquals( d, form.getDate() );
	}

	@Test
	public void testSetDate() {
		Date d = new Date( Calendar.getInstance().getTimeInMillis() );
		form.setDate( d );
		assertEquals( d, form.getDate() );
	}

	@Test
	public void testGetWeeksPregnant() {
		form.setWeeksPregnant( "34" );
		assertEquals( "34", form.getWeeksPregnant());
	}

	@Test
	public void testSetWeeksPregnant() {
		form.setWeeksPregnant( "34" );
		assertEquals( "34", form.getWeeksPregnant());
	}

	@Test
	public void testGetWeight() {
		form.setWeight( "120" );
		assertEquals( "120", form.getWeight() );
	}

	@Test
	public void testSetWeight() {
		form.setWeight( "120" );
		assertEquals( "120", form.getWeight() );
	}

	@Test
	public void testGetBloodPressure() {
		form.setBloodPressure( "100" );
		assertEquals( "100", form.getBloodPressure() );
	}

	@Test
	public void testSetBloodPressure() {
		form.setBloodPressure( "100" );
		assertEquals( "100", form.getBloodPressure() );
	}

	@Test
	public void testGetFtr() {
		form.setFtr( "45" );
		assertEquals( "45", form.getFtr() );
	}

	@Test
	public void testSetFtr() {
		form.setFtr( "45" );
		assertEquals( "45", form.getFtr() );
	}

	@Test
	public void testGetMultiplePregnancy() {
		form.setMultiplePregnancy( true );
		assertTrue( form.getMultiplePregnancy() );
	}

	@Test
	public void testSetMultiplePregnancy() {
		form.setMultiplePregnancy( true );
		assertTrue( form.getMultiplePregnancy() );
	}

	@Test
	public void testGetBabyNum() {
		form.setBabyNum( "2" );
		assertEquals( "2", form.getBabyNum() );
	}

	@Test
	public void testSetBabyNum() {
		form.setBabyNum( "2" );
		assertEquals( "2", form.getBabyNum() );
	}

	@Test
	public void testGetPlacenta() {
		form.setPlacenta( true );
		assertTrue(form.getPlacenta() );
	}

	@Test
	public void testSetPlacenta() {
		form.setPlacenta( true );
		assertTrue(form.getPlacenta() );
	}

	@Test
	public void testGetCrl() {
		form.setCrl( "23" );
		assertEquals( "23", form.getCrl() );
	}

	@Test
	public void testSetCrl() {
		form.setCrl( "23" );
		assertEquals( "23", form.getCrl() );
	}

	@Test
	public void testGetBpd() {
		form.setBpd( "45" );
		assertEquals( "45", form.getBpd() );
	}

	@Test
	public void testSetBpd() {
		form.setBpd( "45" );
		assertEquals( "45", form.getBpd() );
	}

	@Test
	public void testGetHc() {
		form.setHc( "120" );
		assertEquals( "120", form.getHc() );
	}

	@Test
	public void testSetHc() {
		form.setHc( "120" );
		assertEquals( "120", form.getHc() );
	}

	@Test
	public void testGetFl() {
		form.setFl( "34" );
		assertEquals( "34", form.getFl() );
	}

	@Test
	public void testSetFl() {
		form.setFl( "34" );
		assertEquals( "34", form.getFl() );
	}

	@Test
	public void testGetOfd() {
		form.setOfd( "34" );
		assertEquals( "34", form.getOfd() );
	}

	@Test
	public void testSetOfd() {
		form.setOfd( "34" );
		assertEquals( "34", form.getOfd() );
	}

	@Test
	public void testGetAc() {
		form.setAc( "34" );
		assertEquals( "34", form.getAc() );
	}

	@Test
	public void testSetAc() {
		form.setAc( "34" );
		assertEquals( "34", form.getAc() );
	}

	@Test
	public void testGetHl() {
		form.setHl( "34" );
		assertEquals( "34", form.getHl() );
	}

	@Test
	public void testSetHl() {
		form.setHl( "34" );
		assertEquals( "34", form.getHl() );
	}

	@Test
	public void testGetEfw() {
		form.setEfw( "34" );
		assertEquals( "34", form.getEfw() );
	}

	@Test
	public void testSetEfw() {
		form.setEfw( "34" );
		assertEquals( "34", form.getEfw() );
	}

	@Test
	public void testGetNext() {
		form.setNext( "2017-03-22");
		assertEquals( "2017-03-22", form.getNext() );
	}

	@Test
	public void testSetNext() {
		form.setNext( "2017-03-22");
		assertEquals( "2017-03-22", form.getNext() );
	}

	@Test
	public void testSetImage() {
		Part p = Mockito.mock(Part.class);
		form.setImage( p );
		assertEquals( p, form.getImage() );
	}

	@Test
	public void testGetImage() {
		Part p = Mockito.mock(Part.class);
		form.setImage( p );
		assertEquals( p, form.getImage() );
	}

	@Test
	public void testSubmitVisitInfo() {
		
		form.setBabyNum( "1" );
		form.setBloodPressure( "1" );
		form.setMultiplePregnancy( false );
		form.setFtr( "23" );
		form.setPlacenta( true );
		form.setWeeksPregnant( "23" );
		form.setWeight( "34" );
		form.submitVisitInfo();
		
		List<ObstetricsOfficeVisit> ov = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( ov );
		assertEquals( 1, ov.size() );
	}

	@Test
	public void testSubmitFetusInfo() {
		form.setBabyNum( "1" );
		form.setBloodPressure( "1" );
		form.setMultiplePregnancy( false );
		form.setFtr( "23" );
		form.setPlacenta( true );
		form.setWeeksPregnant( "23" );
		form.setWeight( "34" );
		form.submitVisitInfo();
		
		List<ObstetricsOfficeVisit> ov = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( ov );
		assertEquals( 1, ov.size() );
		
		form.setAc( "1" );
		form.setBpd( "1" );
		form.setCrl( "1" );
		form.setEfw( "1" );	
		form.setFl( "1" );
		form.setHc( "1" );
		form.setHl( "1" );
		form.setOfd( "1" );
		
		form.submitFetusInfo();
		
		List<Fetus> f = form.getFeti();
		
		assertNotNull( f );
		
		assertEquals( "1", f.get( 0 ).getAc() );
	}

	@Test
	public void testSubmitUltrasound() {

		form.setBabyNum( "1" );
		form.setBloodPressure( "1" );
		form.setMultiplePregnancy( false );
		form.setFtr( "23" );
		form.setPlacenta( true );
		form.setWeeksPregnant( "23" );
		form.setWeight( "34" );
		form.submitVisitInfo();
		
		List<ObstetricsOfficeVisit> ov = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( ov );
		assertEquals( 1, ov.size() );
		
		
		Part p = Mockito.mock( Part.class );
		InputStream i = Mockito.mock( InputStream.class );
		try {
			Mockito.doReturn(i).when(p).getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		form.setImage( p );
		form.submitUltrasound();
		
		List<Ultrasound> us = form.getUltrasound();
		
		assertNotNull(us);
		assertEquals( 1, us.size() );
	}


	@Test
	public void testLogViewObstetricsVisit() {

		form.setBabyNum( "1" );
		form.setBloodPressure( "1" );
		form.setMultiplePregnancy( false );
		form.setFtr( "23" );
		form.setPlacenta( true );
		form.setWeeksPregnant( "23" );
		form.setWeight( "34" );
		form.submitVisitInfo();
		
		List<ObstetricsOfficeVisit> ov = controller.getObstetricsVisitsForPatient( (long)2 );
		assertNotNull( ov );
		assertEquals( 1, ov.size() );
		
		form.logViewObstetricsVisit();
		
		Mockito.verify(controller, 
				Mockito.times(1)).logTransaction(TransactionType.VIEW_OBSTETRIC_OFFICE_VISIT, Long.parseLong( "9000000012" ), (long)2, Long.toString( ov.get( 0 ).getId() ) );
	
	}

}
