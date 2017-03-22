package edu.ncsu.csc.itrust.controller.calendar;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

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

}
