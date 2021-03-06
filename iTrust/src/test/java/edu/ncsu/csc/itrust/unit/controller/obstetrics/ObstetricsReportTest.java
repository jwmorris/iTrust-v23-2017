/**
 * 
 */
package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport.Complications;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * @author David
 *
 */
public class ObstetricsReportTest {
	
	private SessionUtils mockSessionUtils;
	private ObstetricsReport report;
	private ObstetricsReportController con;

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
		Mockito.doReturn( "2" ).when(mockSessionUtils).getCurrentPatientMID();
		Mockito.doReturn( "1" ).when( mockSessionUtils ).getRequestParameter( "id" );
		
		con = new ObstetricsReportController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
		report = new ObstetricsReport( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils, con.getCurrentPregnancy() );
	}


	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#ObstetricsReport()}.
	 */
	@Test
	public void testObstetricsReport() {
		try {
			new ObstetricsReport();
		} catch( Exception e ) {
			//pass
		}
	}
	
	@Test
	public void testObstetricsReportOfficeVisits() {
		ObstetricsPregnancy op = con.getCurrentPregnancy();
		ObstetricsVisitController ovc = new ObstetricsVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
		ObstetricsOfficeVisit ov = new ObstetricsOfficeVisit();
		ov.setBp( "120/40" );
		ov.setFhr( "120" );
		ov.setLowLying( false );
		ov.setMultiplePregnancy( false );
		ov.setNumBabies( "1" );
		ov.setWeight( "120" );
		ov.setInitID( op.getId() ); 
		ov.setPid( 2 );
		ov.setVisitDate( new java.sql.Date( Calendar.getInstance().getTime().getTime() ) );
		ovc.add( ov );
		
		ov = new ObstetricsOfficeVisit();
		ov.setBp( "120/40" );
		ov.setFhr( "120" );
		ov.setLowLying( false );
		ov.setMultiplePregnancy( false );
		ov.setNumBabies( "1" );
		ov.setWeight( "120" );
		ov.setInitID( op.getId() );
		ov.setVisitDate( new java.sql.Date( Calendar.getInstance().getTime().getTime() ) );
		ov.setPid( 2 );
		ovc.add( ov );
	
		List<ObstetricsOfficeVisit> l = ovc.getOfficeVisitsForInitId( 2, op.getId() );
		assertNotNull( l );
		assertEquals( 2, l.size() );
		
		report = new ObstetricsReport( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils, con.getCurrentPregnancy() );
		
	}
	
	

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getPriors()}.
	 */
	@Test
	public void testGetPriors() {
		List< ObstetricsPregnancy > ret = report.getPriors();
		assertNotNull( ret );
		assertEquals( 2, ret.size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getOfficeVisits()}.
	 */
	@Test
	public void testGetOfficeVisits() {
		
		assertNull( report.getOfficeVisits() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getEdd()}.
	 */
	@Test
	public void testGetEdd() {
		assertEquals( "12/12/2017", report.getEdd() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getAllergies()}.
	 */
	@Test
	public void testGetAllergies() {
		List< AllergyBean > ret = report.getAllergies();
		assertNotNull( ret );
		assertEquals( 1, ret.size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getDiagnoses()}.
	 */
	@Test
	public void testGetDiagnoses() {
		List< Diagnosis > ret = report.getDiagnoses();
		assertNotNull( ret );
		assertEquals( 0, ret.size() );
	}


	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getSelected()}.
	 */
	@Test
	public void testGetSelected() {
		ObstetricsPregnancy selected = report.getSelected();
		assertEquals( "03/19/2017", selected.getDateInit() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getBloodType()}.
	 */
	@Test
	public void testGetBloodType() {
		assertEquals( "O-", report.getBloodType() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#hasAllergies()}.
	 */
	@Test
	public void testHasAllergies() {
		assertTrue( report.hasAllergies() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#hasDiagnoses()}.
	 */
	@Test
	public void testHasDiagnoses() {
		assertFalse( report.hasDiagnoses() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#hasOfficeVisits()}.
	 */
	@Test
	public void testHasOfficeVisits() {
		assertFalse( report.hasOfficeVisits() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#hasPriorPregnancies()}.
	 */
	@Test
	public void testHasPriorPregnancies() {
		assertTrue( report.hasPriorPregnancies() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getComplications()}.
	 */
	@Test
	public void testGetComplications() {
		Complications comps = report.getComplications();
		assertTrue( comps.getAllergies() );
		assertFalse( comps.getPreExisting() );
		assertFalse( comps.getRh() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport#getComplicationsForVisit(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit, edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit)}.
	 */
	@Test
	public void testGetComplicationsForVisit() {
		try {
			report.getComplicationsForVisit( null, null );
		} catch ( Exception e ) {
			
		}
	}
	
	@Test
	public void testComplications() {
		Complications c = report.new Complications();
		assertFalse( c.getAbnormalFhr() );
		assertFalse( c.getAbnormalWeightChange() );
		assertFalse( c.getAllergies() );
		assertFalse( c.getBp() );
		assertFalse( c.getHyperemesis() );
		assertFalse( c.getHypothyroidism() );
		assertFalse( c.getMiscarriage() );
		assertFalse( c.getMultiple() );
		assertFalse( c.getOld() );
		assertFalse( c.getPlacenta() );
		assertFalse( c.getPreExisting() );
		assertFalse( c.getRh() );
	}

}
