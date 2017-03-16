package edu.ncsu.csc.itrust.unit.model.fitnessData;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessValidator;

public class FitnessValidatorTest {
	private FitnessValidator valid;
	@Before
	public void setUp() throws Exception {
		valid = new FitnessValidator();
	}

	@Test
	public void testValidateFitness() {
		Fitness f = new Fitness();
		f.setActiveCals(-1);
		try {
			f.setPid("2");
		} catch (SQLException e) {
			fail("Can't set pid");
		}
		f.setActiveHours(-1);
		f.setCalories(-1);
		try {
			f.setDate("asdas");
		} catch (SQLException e) {
			//pass
		}
		try {
			f.setDate("02/02/2017");
		} catch (SQLException e) {
			fail("Unable to parse date");
		}
		
		f.setDistance(-1);
		f.setFloors(-1);
		f.setHRAvg(-1);
		f.setHRLow(-1);
		f.setHRHigh(-1);
		f.setMinsFA(-1);
		f.setMinsLA(-1);
		f.setMinsSed(-1);
		f.setMinsVA(-1);
		f.setSteps(-1);
		f.setUVExposure(-1);
		
		try {
			valid.validate(f);
			fail("Should throw an exception");
		} catch (FormValidationException e) {
			List<String> errors = e.getErrorList();
			assertEquals(14, errors.size());
		}
		f.setActiveCals(1);
		try {
			f.setPid("2");
		} catch (SQLException e) {
			fail("Can't set pid");
		}
		f.setActiveHours(1);
		f.setCalories(1);
		try {
			f.setDate("02/02/2017");
		} catch (SQLException e) {
			fail("Unable to parse date");
		}
		
		f.setDistance(1);
		f.setFloors(1);
		f.setHRAvg(1);
		f.setHRLow(1);
		f.setHRHigh(1);
		f.setMinsFA(1);
		f.setMinsLA(1);
		f.setMinsSed(1);
		f.setMinsVA(1);
		f.setSteps(1);
		f.setUVExposure(1);
		
		try {
			valid.validate(f);
		} catch (FormValidationException e) {
			//Should pass
		}
		
		f.setActiveCals(1);
		try {
			f.setPid("2");
		} catch (SQLException e) {
			fail("Can't set pid");
		}
		f.setActiveHours(36);
		f.setCalories(1);
		try {
			f.setDate("02/02/2017");
		} catch (SQLException e) {
			fail("Unable to parse date");
		}
		
		f.setDistance(1);
		f.setFloors(1);
		f.setHRAvg(1);
		f.setHRLow(1);
		f.setHRHigh(1);
		f.setMinsFA(7000);
		f.setMinsLA(7000);
		f.setMinsSed(7000);
		f.setMinsVA(7000);
		f.setSteps(1);
		f.setUVExposure(7000);
		
		try {
			valid.validate(f);
		} catch (FormValidationException e) {
			List<String> errors = e.getErrorList();
			assertEquals(6, errors.size());
		}
		
		
	}

}
