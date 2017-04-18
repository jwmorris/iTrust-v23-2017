/**
 * 
 */
package edu.ncsu.csc.itrust.unit.controller.calendar;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.fitness.FitnessController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * @author david
 *
 */
public class FitnessControllerTest {

	private SessionUtils mockSessionUtils;
	private FitnessController con;
	private Fitness f;
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
		con = new FitnessController( ConverterDAO.getDataSource(), mockSessionUtils, TestDAOFactory.getTestInstance() );
		f = new Fitness();
		f.setActiveCals( 100 );
		f.setActiveHours( 5 );
		f.setCalories( 100 );
		f.setDate( "04/04/2017" );
		f.setDistance( 9.0 );
		f.setFloors( 2 );
		f.setHRAvg( 90 );
		f.setHRHigh( 90 );
		f.setHRLow( 90 );
		f.setMinsFA( 30 );
		f.setMinsLA( 30 );
		f.setMinsSed( 30 );
		f.setMinsVA( 30 );
		f.setPid( "2" );
		f.setSteps( 200 );
		f.setUVExposure( 100 );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.fitness.FitnessController#FitnessController()}.
	 */
	@Test
	public void testFitnessController() {
		try {
			new FitnessController();
		} catch( Exception e ){
			//donothing
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.fitness.FitnessController#add(edu.ncsu.csc.itrust.model.fitnessData.Fitness)}.
	 */
	@Test
	public void testAdd() {
		try {
			con.add( f );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Fitness> list = con.getFitnessDataForPatient( "2" );
		assertNotNull( list );
		
	}


	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.fitness.FitnessController#getFitnessDateRange(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetFitnessDateRange() {
		try {
			con.add( f );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Fitness> list = null;
		try {
			list = con.getFitnessDateRange( "01/01/2017", "07/01/2017", "2" );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull( list );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.controller.fitness.FitnessController#edit(edu.ncsu.csc.itrust.model.fitnessData.Fitness)}.
	 */
	@Test
	public void testEdit() {
		try {
			con.add( f );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f.setActiveCals( 0 );
		con.edit( f );
		List<Fitness> list = con.getFitnessDataForPatient( "2" );
		assertNotNull( list );
		assertEquals( 1, list.size() );
	}

}
