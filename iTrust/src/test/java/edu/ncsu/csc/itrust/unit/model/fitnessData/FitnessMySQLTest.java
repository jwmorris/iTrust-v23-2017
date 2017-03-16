package edu.ncsu.csc.itrust.unit.model.fitnessData;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class FitnessMySQLTest {

	private FitnessMySQL sql;
	private TestDataGenerator gen;
	@Before
	public void setUp() throws Exception {
		sql = new FitnessMySQL(ConverterDAO.getDataSource());
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}
	
	private boolean equalFitness(Fitness f1, Fitness f2) {
		return f1.getActiveCals() == f2.getActiveCals()
				&& f1.getActiveHours() == f2.getActiveHours()
				&& f1.getCalories() == f2.getCalories()
				&& f1.getDate().equals(f2.getDate())
				&& f1.getDistance() == f2.getDistance()
				&& f1.getFloors() == f2.getFloors()
				&& f1.getHRAvg() == f2.getHRAvg()
				&& f1.getHRHigh() == f2.getHRHigh()
				&& f1.getHRLow() == f2.getHRLow()
				&& f1.getMinsFA() == f2.getMinsFA()
				&& f1.getMinsLA() == f2.getMinsLA()
				&& f1.getMinsSed() == f2.getMinsSed()
				&& f1.getMinsVA() == f2.getMinsVA()
				&& f1.getPid().equals(f2.getPid())
				&& f1.getSteps() == f2.getSteps()
				&& f1.getUVExposure() == f2.getUVExposure();
	}
	
	private Fitness newFitness(String date) {
		Fitness f = new Fitness();
		f.setActiveCals(0);
		try {
			f.setPid("2");
		} catch (SQLException e) {
			fail("Can't set pid");
		}
		f.setActiveHours(0);
		f.setCalories(0);
		try {
			f.setDate(date);
		} catch (SQLException e) {
			fail("Can't set date");
		}
		f.setDistance(0);
		f.setFloors(0);
		f.setHRAvg(0);
		f.setHRLow(0);
		f.setHRHigh(0);
		f.setMinsFA(0);
		f.setMinsLA(0);
		f.setMinsSed(0);
		f.setMinsVA(0);
		f.setSteps(0);
		f.setUVExposure(0);
		return f;
	}

	@Test
	public void testGetAll() {
		List<Fitness> all = null;
		try {
			all = sql.getAll();
		} catch (DBException e) {
			fail("Error getting all data");
		}
		assertNull(all);
		Fitness f = newFitness("02/02/2017");
		try {
			sql.add(f);
		} catch (DBException e1) {
			fail("Error adding data");
		}
		try {
			
			all = sql.getAll();
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(1, all.size());
		
		
	}

	@Test
	public void testGetByID() {
		Fitness id = null;
		try {
			id = sql.getByID(2);
		} catch (DBException e) {
			fail("Error getting data");
		}
		
		assertNull(id);
	}

	@Test
	public void testAdd() {
		Fitness f = newFitness("02/02/2017");
		try {
			sql.add(f);
		} catch (DBException e) {
			fail("Can't add fitness data");
		}
		
		List<Fitness> data = null;
		try {
			data = sql.getFitnessDataForPatient("2");
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(1, data.size());
		assertTrue(equalFitness(f, data.get(0)));
	}

	@Test
	public void testUpdate() {
		Fitness f = newFitness("02/02/2017");
		Fitness f2 = newFitness("02/25/2017");
		try {
			sql.add(f);
			sql.add(f2);
		} catch (DBException e) {
			fail("Error adding data");
		}
		List<Fitness> data = null;
		try {
			data = sql.getFitnessDataForPatient("2");
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(2, data.size());
		
		f.setDistance(23.0);
		
		try {
			sql.update(f);
		} catch (DBException e) {
			fail("Error updating data");
		} catch (FormValidationException e) {
			fail("Error updating data");
		}
		
		try {
			data = sql.getFitnessDataForPatient("2");
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(2, data.size());
		
		assertTrue(equalFitness(f, data.get(0)));
		assertTrue(equalFitness(f2, data.get(1)));
		
		
		
	}

	@Test
	public void testGetFitnessData() throws DBException {
		Fitness f = newFitness("02/02/2017");
		Fitness f2 = newFitness("02/03/2017");
		Fitness f3 = newFitness("02/04/2017");
		
		sql.add(f);
		sql.add(f2);
		sql.add(f3);
		
		Fitness fSql = null;
		Fitness f2Sql = null;
		Fitness f3Sql = null;
		try {
			fSql = sql.getFitnessData("2", "02/02/2017");
			f2Sql = sql.getFitnessData("2", "02/03/2017");
			f3Sql = sql.getFitnessData("2", "02/04/2017");
		} catch (DBException e) {
			fail(e.toString());
		}
		
		assertTrue(equalFitness(f, fSql));
		assertTrue(equalFitness(f2, f2Sql));
		assertTrue(equalFitness(f3, f3Sql));
		
	}

	@Test
	public void testGetFitnessDataForPatient() {
		Fitness f = newFitness("02/02/2017");
		Fitness f2 = newFitness("02/25/2017");
		try {
			sql.add(f);
			sql.add(f2);
		} catch (DBException e) {
			fail("Error adding data");
		}
		List<Fitness> data = null;
		try {
			data = sql.getFitnessDataForPatient("2");
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(2, data.size());
	}

	@Test
	public void testGetFitnessDataForPatientDates() {
		Fitness f = newFitness("02/02/2017");
		Fitness f2 = newFitness("02/25/2017");
		try {
			sql.add(f);
			sql.add(f2);
		} catch (DBException e) {
			fail("Error adding data");
		}
		List<Fitness> data = null;
		try {
			data = sql.getFitnessDataForPatientDates("2", "02/01/2017", "02/05/2017");
		} catch (DBException e) {
			fail("Error getting data");
		}
		assertEquals(1, data.size());
		
		assertTrue(equalFitness(f, data.get(0)));
	}

}
