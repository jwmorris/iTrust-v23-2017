package edu.ncsu.csc.itrust.unit.controller.calendar;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.controller.calendar.FitnessDataEvent;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;

public class FitnessDataEventTest {
	
	FitnessDataEvent event;

	@Before
	public void setUp() throws Exception {
		event = new FitnessDataEvent();
	}


	@Test
	public void testSetActivityHours() {
		event.setActivityHours(9);
		assertEquals(event.getActivityHours(), 9);
	}

	@Test
	public void testGetActivityHours() {
		event.setActivityHours(9);
		assertEquals(event.getActivityHours(), 9);
	}

	@Test
	public void testSethrLow() {
		event.sethrLow(90);
		assertEquals(event.gethrLow(), 90);
	}

	@Test
	public void testGethrLow() {
		event.sethrLow(90);
		assertEquals(event.gethrLow(), 90);
	}

	@Test
	public void testGethrHigh() {
		event.sethrHigh(120);
		assertEquals(event.gethrHigh(), 120);
	}

	@Test
	public void testSethrHigh() {
		event.sethrHigh(120);
		assertEquals(event.gethrHigh(), 120);
	}

	@Test
	public void testSethrAvg() {
		event.sethrAvg(78);
		assertEquals(78, event.gethrAvg());
	}

	@Test
	public void testGethrAvg() {
		event.sethrAvg(78);
		assertEquals(78, event.gethrAvg());
	}

	@Test
	public void testGetuvExposure() {
		event.setuvExposure(56);
		assertEquals(56, event.getuvExposure());
	}

	@Test
	public void testSetuvExposure() {
		event.setuvExposure(56);
		assertEquals(56, event.getuvExposure());
	}
	
	@Test
	public void testSetFitnessData() {
		Fitness f = new Fitness();
		f.setActiveCals( 100 );
		event.setFitnessData( f );
		assertEquals( 100, event.getFitnessData().getActiveCals() );
	}
	
	@Test
	public void testSetCalories() {
		event.setCalories( 100 );
		assertEquals( 100, event.getCalories() );
	}
	
	@Test
	public void testSetSteps() {
		event.setSteps( 100 );
		assertEquals( 100, event.getSteps() );
	}
	
	@Test
	public void testSetDistance(){
		event.setDistance( 100 );
		assertEquals( 100, event.getDistance(), 2 );
	}
	
	@Test
	public void testSetFloors() {
		event.setFloors( 100 );
		assertEquals( 100, event.getFloors() );
	}
	
	@Test
	public void testSetMinsSed() {
		event.setMinsSed( 100 );
		assertEquals( 100, event.getMinsSed() );
	}
	
	@Test
	public void testSetMinsLA() {
		event.setMinsLA( 100 );
		assertEquals( 100, event.getMinsLA() );
	}
	
	@Test
	public void testSetMinsFA() {
		event.setMinsFA( 100 );
		assertEquals( 100, event.getMinsFA() );
	}
	
	@Test
	public void testSetMinsVA() {
		event.setMinsVA( 100 );
		assertEquals( 100, event.getMinsVA() );
	}
	
	@Test
	public void testSetActiveCals() {
		event.setActiveCals( 100 );
		assertEquals( 100, event.getActiveCals() );
	}
	
	@Test
	public void testSetPatient() {
		event.setPatient( "2" );
		assertEquals( "2", event.getFitnessData().getPid() );
	}
	
	@Test
	public void testDateEquals() {
		Date d = Calendar.getInstance().getTime();
		event = new FitnessDataEvent( d );
		FitnessDataEvent ev = new FitnessDataEvent( d );
		assertTrue( event.equalsDate( ev ) );
	}

}
