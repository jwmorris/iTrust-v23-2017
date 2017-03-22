package edu.ncsu.csc.itrust.unit.model.fitnessData;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessSQLLoader;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class FitnessSQLLoaderTest {

	private FitnessSQLLoader loader;
	private DataSource ds;
	private Connection conn;
	private FitnessMySQL sql;
	private TestDataGenerator gen;
	@Before
	public void setUp() throws Exception {
		loader = new FitnessSQLLoader();
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		this.ds = ConverterDAO.getDataSource();
		try{
			conn = ds.getConnection();
		} catch(Exception e) {
			throw new DBException(new SQLException("Can't get connection"));
		}
		sql = new FitnessMySQL(ds);
		sql.add(newFitness("02/02/2017"));
		sql.add(newFitness("02/03/2017"));
		sql.add(newFitness("02/04/2017"));
	}

	@Test
	public void testLoadList() throws SQLException {
		List<Fitness> ret = Collections.emptyList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//conn = ds.getConnection();
			ps = conn.prepareStatement( "SELECT * FROM fitnessData" );
			rs = ps.executeQuery();
		
			
		} catch ( SQLException e ) {
			fail("Error executing query");
		}
		try {
			ret = rs.next() ? loader.loadList( rs ) : Collections.emptyList();
		} catch (SQLException e) {
			fail("Error loading");
		}
		rs.close();
		ps.close();
		assertEquals(3, ret.size());
	}

	@Test
	public void testLoadSingle() throws SQLException {
		String pid = "2";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dateSQL = null;
		Fitness f = new Fitness();
		try {
			dateSQL = new Date(sdf.parse("02/02/2017").getTime());
		} catch (ParseException e1) {
			//Will not happen. The entered date is parsable
		}
		//PreparedStatement ps = null;
		ResultSet rs = null;
		try {
				PreparedStatement ps = conn.prepareStatement( "SELECT * FROM fitnessData WHERE PID = ? and fitnessDate = ?;" );
				
				ps.setString( 1, pid );
				ps.setDate( 2, dateSQL );
				rs = ps.executeQuery();
				//ps.close();
				
		} catch ( SQLException e ) {
				fail("Error executing query");
		}
		
		try {
			if(rs.next()) {
				f = loader.loadSingle(rs);
			} else {
				//f = null;
			}
			//f = rs.next() ? loader.loadSingle( rs ) : null;
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error loading");
		} 
		rs.close();
		assertTrue(equalFitness(f, newFitness("02/02/2017")));
		
	}

	@Test
	public void testLoadParameters() {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO fitnessData (pid, calories, steps"
					+ ", distance ,floors, minsSed, minsLA, minsFA, minsVA, activeCals, activeHours, hrLow," 
					+ " hrHigh, hrAvg, uvExposure, fitnessDate) VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " 
					+ "ON DUPLICATE KEY UPDATE calories=?, steps=?, distance=?, floors=?, minsSed=?," 
					+" minsLA=?, minsFA=?, minsVA=?, activeCals=?, activeHours=?, hrLow=?, hrHigh=?, hrAvg=?, uvExposure=?");
		} catch (SQLException e) {
			fail("Error creating statement");
		}
		Fitness f = newFitness("02/02/2017");
		PreparedStatement newPS = null;
		try {
			newPS = loader.loadParameters(conn, ps, f, true);
		} catch (SQLException e) {
			fail("Error loading parameters");
		}
		
		
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


}
