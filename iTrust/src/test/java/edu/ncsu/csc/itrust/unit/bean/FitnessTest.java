package edu.ncsu.csc.itrust.unit.bean;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import junit.framework.Assert;

public class FitnessTest {
	
	private Fitness bean;

	@Before
	public void setUp() throws Exception {
		bean = new Fitness();
		
	}

	@Test
	public void testSetHRLow() {
		bean.setHRLow(85);
		assertEquals(85, bean.getHRLow());
	}

	@Test
	public void testSetHRHigh() {
		bean.setHRHigh(95);
		assertEquals(95, bean.getHRHigh());
	}

	@Test
	public void testSetHRAvg() {
		bean.setHRAvg(100);
		assertEquals(100, bean.getHRAvg());
	}

	@Test
	public void testSetActiveHours() {
		bean.setActiveHours(4);
		assertEquals(4, bean.getActiveHours());
	}

	@Test
	public void testSetUVExposure() {
		bean.setUVExposure(85);
		assertEquals(85,bean.getUVExposure());
	}

	@Test
	public void testSetPid() {
		try {
			bean.setPid("2");
		} catch (SQLException e) {
			fail("2 is a numeric value so it should not throw an exception");
		}
		
		assertEquals("2", bean.getPid());
		
		try {
			bean.setPid("aw");
			fail("Should not allow a non-numeric pid");
		} catch(SQLException e) {
			//pass
		}
	}

	@Test
	public void testSetCalories() {
		bean.setCalories(4500);
		assertEquals(4500, bean.getCalories());
	}

	@Test
	public void testSetSteps() {
		bean.setSteps(10000);

		assertEquals(10000, bean.getSteps());
	}

	@Test
	public void testSetDistance() {
		bean.setDistance(9.5);
		assertEquals(9.5, bean.getDistance(),0);
	}

	@Test
	public void testSetFloors() {
		bean.setFloors(9);
		assertEquals(9, bean.getFloors());
	}

	@Test
	public void testSetMinsSed() {
		bean.setMinsSed(56);
		assertEquals(56, bean.getMinsSed());
	}

	@Test
	public void testSetMinsLA() {
		bean.setMinsLA(90);
		assertEquals(90, bean.getMinsLA());
	}

	@Test
	public void testSetMinsFA() {
		bean.setMinsFA(63);
		assertEquals(63, bean.getMinsFA());
	}

	@Test
	public void testSetMinsVA() {
		bean.setMinsVA(3);
		assertEquals(3, bean.getMinsVA());
	}

	@Test
	public void testSetActiveCals() {
		bean.setActiveCals(400);
		assertEquals(400, bean.getActiveCals());
	}

	@Test
	public void testSetDate() {
		try {
			bean.setDate("02/02/2017");
		} catch (SQLException e) {
			fail("Valid date");
		}
		assertEquals("02/02/2017", bean.getDate());
		
		try {
			bean.setDate("sdfs");
			fail("Invalid date");
		} catch (SQLException e) {
			//pass
		}
	}

}
