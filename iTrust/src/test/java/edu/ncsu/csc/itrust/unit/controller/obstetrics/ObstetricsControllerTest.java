package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

public class ObstetricsControllerTest {

	private ObstetricsController obc;
	private TestDataGenerator gen;
	
	@Before
	public void setUp() throws Exception {
		long hcp = Long.parseLong("9000000012");
		long pid = (long) 7;
		obc = new ObstetricsController(ConverterDAO.getDataSource(),pid, hcp, TestDAOFactory.getTestInstance());
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}

	@Test
	public void testObstetricsController() {
		try {
			new ObstetricsController();
		} catch (Exception e) {
			//pass
		}
	}

	@Test
	public void testGetSelectedDate() {
		assertEquals("", obc.getSelectedDate());
	}
	
	@Test
	public void testSetSelectedDate() {
		obc.setSelectedDate("03/21/2017");
		assertEquals("03/21/2017", obc.getSelectedDate());
	}
	
	@Test
	public void testMakeNewPregnancy() {
		assertTrue((new ObstetricsPregnancy()).equals(obc.makeNewPregnancy()));
	}
	
	@Test
	public void testGetSinglePregnancy() {
		ObstetricsPregnancy op = obc.makeNewPregnancy();
		op.setDateInit("03/21/2017");
		op.setLmp("03/07/2017");
		try {
			obc.initializePregnancy();
		} catch (NullPointerException e) {
			// will throw null pointer because of context
		}
		
		assertEquals(op.getDateInit(), obc.getSinglePregnancy("03/21/2017").getDateInit());
	}
	
	@Test
	public void testCheckPatientEligibility() {
		assertFalse(obc.checkPatientEligibility());
		try {
			obc.activatePatient();
		} catch (NullPointerException e) {
			// will throw null pointer because of attempting to access context
		}
		assertTrue(obc.checkPatientEligibility());
	}

	@Test
	public void testGetPriorPregnancies() {
		assertNull(obc.getPriorPregnancies());
		
	}

	@Test
	public void testGetCurrentPregnancy() {
		assertTrue((new ObstetricsPregnancy()).equals(obc.getCurrentPregnancy()));
	}

	@Test
	public void testCheckOBGYN() {
		assertTrue(obc.checkOBGYN());
		long hcp = Long.parseLong("9000000001");
		long pid = (long) 1;
		ObstetricsController obc2 = new ObstetricsController(ConverterDAO.getDataSource(), pid, hcp,  TestDAOFactory.getTestInstance());
		assertFalse(obc2.checkOBGYN());
	}

	@Test
	public void testActivatePatient() {
		assertFalse(obc.checkPatientEligibility());
		try {
			obc.activatePatient();
		} catch (NullPointerException e) {
			// will throw null pointer because of attempting to access context
		}
		assertTrue(obc.checkPatientEligibility());
	}

	@Test
	public void testInitializePregnancy() {
		ObstetricsPregnancy op = obc.makeNewPregnancy();
		op.setDateInit("03/21/2017");
		op.setLmp("03/07/2017");
		try {
			obc.initializePregnancy();
		} catch (NullPointerException e) {
			// will throw null pointer because of context
		}
		assertEquals(op.getDateInit(), obc.getSingleCurrentPregnancy().getDateInit());
	}

	@Test
	public void testEditCurrentPregnancy() {
		ObstetricsPregnancy op = obc.makeNewPregnancy();
		op.setDateInit("03/21/2017");
		op.setLmp("03/07/2017");
		try {
			obc.initializePregnancy();
		} catch (NullPointerException e) {
			// will throw null pointer because of context
		}
		assertEquals(op.getDateInit(), obc.getSingleCurrentPregnancy().getDateInit());
		
		ObstetricsPregnancy op2 = obc.getCurrentPregnancy();
		op2.setLmp("03/05/2017");
		try {
			obc.editCurrentPregnancy();
		} catch (NullPointerException e) {
			// should throw exception
		}
		
		assertEquals(op2.getDateInit(), obc.getSingleCurrentPregnancy().getDateInit());
	}
	
	@Test
	public void testGetSingleCurrentPregnancy() {
		ObstetricsPregnancy op = obc.makeNewPregnancy();
		op.setDateInit("03/21/2017");
		op.setLmp("03/07/2017");
		try {
			obc.initializePregnancy();
		} catch (NullPointerException e) {
			// will throw null pointer because of context
		}
		assertEquals(op.getDateInit(), obc.getSingleCurrentPregnancy().getDateInit());
	}
	
	/*@Test
	public void testEditPriorPregnancy() {
		ObstetricsPregnancy op = obc.getPriorPregnancy();
		op.setWeeksPregnant("2");
		obc.setSelectedDate("03/19/2015");
		try {
			obc.editPriorPregnancy();
		} catch (NullPointerException e) {
			// should throw null because of context access
		}
		assertEquals(op.getWeeksPregnant(), obc.getSinglePregnancy("03/19/2015").getWeeksPregnant());
	}*/

	@Test
	public void testAddCurrentPregnancy() {
		try {
			obc.addCurrentPregnancy();
			fail();
		} catch (NullPointerException e) {
			// Should pass
		}

	}
	
	@Test
	public void testEditCurrentButton() {
		try {
			obc.editCurrentButton();
			fail();
		} catch (NullPointerException e) {
			// should pass because of the external context
		}
	}
	
	@Test
	public void testeditPriorPregnancyButton() {
		obc.setSelectedDate("03/19/2015");
		try {
			obc.editPriorPregnancyButton();
			fail();
		} catch (NullPointerException e) {
			// should pass because of the external context
		}
		
	}
	
	@Test
	public void testgetPriorPregnancy() {
		assertEquals(new ObstetricsPregnancy(), obc.getPriorPregnancy());
	}
	
	@Test 
	public void testSetPriorPregnancy() {
		ObstetricsPregnancy op = new ObstetricsPregnancy();
		op.setDateInit("03/21/2017");
		op.setLmp("03/07/2017");
		
		obc.setPriorPregnancy(op);
		assertEquals(op, obc.getPriorPregnancy());
	}
	
	@Test
	public void testGetPriors() {
		assertNull(obc.getPriors());
	}
	
	@Test
	public void testSetDate() {
		obc.setDate("03/21/2017");
		assertEquals("03/21/2017", obc.getSelectedDate());
	}
}
