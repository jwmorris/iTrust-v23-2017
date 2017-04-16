/**
 * 
 */
package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * @author David
 *
 */
public class ObstetricsReportControllerTest {

	private SessionUtils mockSessionUtils;
	private ObstetricsReportController obc;
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
		obc = new ObstetricsReportController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#ObstetricsReportController()}.
	 */
	@Test
	public void testObstetricsReportController() {
		try {
			new ObstetricsReportController();
		} catch ( Exception e ) {
			//pass;
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#hasPriorPregnancies()}.
	 */
	@Test
	public void testHasPriorPregnancies() {
		assertTrue( obc.hasPriorPregnancies() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getSelectedVisit()}.
	 */
	@Test
	public void testGetSelectedVisit() {
		assertNull( obc.getSelectedVisit() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#hasCurrentPregnancy()}.
	 */
	@Test
	public void testHasCurrentPregnancy() {
		assertTrue( obc.hasCurrentPregnancy() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getCurrentPatientBloodType()}.
	 */
	@Test
	public void testGetCurrentPatientBloodType() {
		assertEquals( "O-", obc.getCurrentPatientBloodType() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getRelevantAllergies()}.
	 */
	@Test
	public void testGetRelevantAllergies() {
		List< AllergyBean > ret = obc.getRelevantAllergies();
		assertNotNull( ret );
		assertEquals( 1, ret.size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getRelevantDiagnoses()}.
	 */
	@Test
	public void testGetRelevantDiagnoses() {
		List< Diagnosis > ret = obc.getRelevantDiagnoses();
		assertNotNull( ret );
		assertEquals( 0, ret.size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getPriorPregnancies()}.
	 */
	@Test
	public void testGetPriorPregnancies() {
		List< ObstetricsPregnancy > ret = obc.getPriorPregnancies();
		assertNotNull( ret );
		assertEquals( 2, ret.size() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getOfficeVisitsForVisit(long)}.
	 */
	@Test
	public void testGetOfficeVisitsForVisit() {
		ObstetricsPregnancy curr = obc.getCurrentPregnancy();
		assertNull( obc.getOfficeVisitsForVisit( curr.getId() ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getCurrentPregnancy()}.
	 */
	@Test
	public void testGetCurrentPregnancy() {
		assertTrue( obc.getCurrentPregnancy().getId() != 0 );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController#getDOB()}.
	 */
	@Test
	public void testGetDOB() {
		assertEquals( "05/19/1984", obc.getDOB() );
	}

}
