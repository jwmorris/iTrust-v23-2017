/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetricsOfficeVisit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * tests the functionality of the ObstetricsOfficeVisitMySQL class
 * 
 * @author wyattmaxey
 *
 */
public class ObstetricsOfficeVisitMySQLTest {
	
	private ObstetricsOfficeVisitMySQL sql;
	
	private DataSource ds;
	
	private TestDataGenerator gen;
	
	private ObstetricsOfficeVisit ov;
	
	private Ultrasound us;
	
	private Fetus f;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ds = ConverterDAO.getDataSource();
		sql = new ObstetricsOfficeVisitMySQL( ds );
		
		ov = new ObstetricsOfficeVisit();
		ov.setBp( "1" );
		ov.setFhr( "1" );
		ov.setNumBabies( "1" );
		ov.setPid( 2 );
		ov.setWeeksPregnant( "25" );
		ov.setWeight( "1" );
		
		us = new Ultrasound();
		InputStream in = null;
		in = Mockito.mock( InputStream.class );
		us.setPid( 2 );
		us.setImg( in );
		us.setPicPath( "testing-files/sample_img/bridge-fog.jpg" );
		us.setOvId( 1 );
		
		f = new Fetus();
		f.setOvId( 1 );
		f.setAc( "1" );
		f.setBpd( "1" );
		f.setCrl( "1" );
		f.setEfw( "1" );
		f.setFl( "1" );
		f.setHc( "1" );
		f.setHl( "1" );
		f.setOfd( "1" );
		
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#ObstetricsOfficeVisitMySQL()}.
	 */
	@Test
	public void testObstetricsOfficeVisitMySQL() {
		try {
			sql = new ObstetricsOfficeVisitMySQL();
			fail();
		} catch ( DBException e ) {
			//should be thrown
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getAll()}.
	 */
	@Test
	public void testGetAll() {
		try {
			sql.add( ov );
			List<ObstetricsOfficeVisit> list = Collections.emptyList();
			list = sql.getAll();
			assertEquals( 1, list.size() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getByID(long)}.
	 */
	@Test
	public void testGetByID() {
		try {
			sql.add( ov );
			ObstetricsOfficeVisit ret = null;
			ret = sql.getByID( 1 );
			assertTrue( ret.getBp().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#add(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit)}.
	 */
	@Test
	public void testAdd() {
		try {
			sql.add( ov );
			ObstetricsOfficeVisit ret = null;
			ret = sql.getByID( 1 );
			assertTrue( ret.getBp().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#addReturnsGeneratedId(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit)}.
	 */
	@Test
	public void testAddReturnsGeneratedId() {
		try {
			long id = sql.addReturnsGeneratedId( ov );
			assertEquals( 1, id );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#update(edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit)}.
	 */
	@Test
	public void testUpdate() {
		try {
			ov.setId( sql.addReturnsGeneratedId( ov ) );
			ov.setBp( "30" );
			sql.update( ov );
			ObstetricsOfficeVisit ret = null;
			ret = sql.getByID( 1 );
			assertTrue( ret.getBp().equals( "30" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getOfficeVistsForPatient(long)}.
	 */
	@Test
	public void testGetOfficeVistsForPatient() {
		try {
			sql.add( ov );
			List<ObstetricsOfficeVisit> list = Collections.emptyList();
			list = sql.getOfficeVistsForPatient( 2 );
			assertEquals( 1, list.size() );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getOfficeVisitForDate(long, java.sql.Date)}.
	 */
	@Test
	public void testGetOfficeVisitForDate() {
		try {
			sql.add( ov );
			ObstetricsOfficeVisit ret = null;
			ret = sql.getByID( 1 );
			assertTrue( ret.getBp().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getFetus(long, int)}.
	 */
	@Test
	public void testGetFetus() {
		try {
			sql.addFetus( f );
			Fetus ret = sql.getFetus( 1, 0 );
			assertTrue( ret.getAc().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getFetiForUltrasound(long)}.
	 */
	/*
	@Test
	public void testGetFetiForUltrasound() {
		fail("Not yet implemented");
	}
	 */
	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getFetiForOfficeVisit(long)}.
	 */
	@Test
	public void testGetFetiForOfficeVisit() {
		try {
			sql.addFetus( f );
			List<Fetus> ret = sql.getFetiForOfficeVisit( 1 );
			assertTrue( ret.get( 0 ).getAc().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getFetusForOfficeVisit(long, int)}.
	 */
	@Test
	public void testGetFetusForOfficeVisit() {
		try {
			sql.addFetus( f );
			Fetus ret = sql.getFetusForOfficeVisit( 1, 0 );
			assertTrue( ret.getAc().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getUltrasoundsForPatient(long)}.
	 */
	@Test
	public void testGetUltrasoundsForPatient() {
		try {
			sql.addUltrasound( us );
			List<Ultrasound> ret = sql.getUltrasoundsForPatient( 2 );
			assertTrue( ret.get(0).getPicPath().equals( "testing-files/sample_img/bridge-fog.jpg" ) );
		} catch ( DBException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getUltrasoundByOfficeVisitId(long)}.
	 */
	@Test
	public void testGetUltrasoundByOfficeVisitId() {
		try {
			sql.addUltrasound( us );
			List<Ultrasound> ret = sql.getUltrasoundByOfficeVisitId( 1 );
			assertTrue( ret.get(0).getPicPath().equals( "testing-files/sample_img/bridge-fog.jpg" ) );
		} catch ( DBException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#getUltrasoundByDate(long, java.sql.Date)}.
	 */
	@Test
	public void testGetUltrasoundByDate() {
		try {
			sql.addUltrasound( us );
			Ultrasound ret = sql.getUltrasoundByDate( 2, new Date( Calendar.getInstance().getTimeInMillis() ) );
			assertTrue( ret.getPicPath().equals( "testing-files/sample_img/bridge-fog.jpg" ) );
		} catch ( DBException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#addFetus(edu.ncsu.csc.itrust.model.ultasound.Fetus)}.
	 */
	@Test
	public void testAddFetus() {
		try {
			sql.addFetus( f );
			Fetus ret = sql.getFetus( 1, 0 );
			assertTrue( ret.getAc().equals( "1" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#updateFetus(edu.ncsu.csc.itrust.model.ultasound.Fetus)}.
	 */
	@Test
	public void testUpdateFetus() {
		try {
			sql.addFetus( f );
			f.setAc( "3" );
			sql.updateFetus( f );
			Fetus ret = sql.getFetus( 1, 0 );
			assertTrue( ret.getAc().equals( "3" ) );
		} catch ( DBException | FormValidationException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#addUltrasound(edu.ncsu.csc.itrust.model.ultasound.Ultrasound)}.
	 */
	@Test
	public void testAddUltrasound() {
		try {
			sql.addUltrasound( us );
			List<Ultrasound> ret = sql.getUltrasoundByOfficeVisitId( 1 );
			assertTrue( ret.get(0).getPicPath().equals( "testing-files/sample_img/bridge-fog.jpg" ) );
		} catch ( DBException e ) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL#updateUltrasound(edu.ncsu.csc.itrust.model.ultasound.Ultrasound)}.
	 */
	@Test
	public void testUpdateUltrasound() {
		try {
			sql.addUltrasound( us );
			us.setPicPath( "" );
			sql.updateUltrasound( us );
			List<Ultrasound> ret = sql.getUltrasoundByOfficeVisitId( 1 );
			assertTrue( ret.get(0).getPicPath().equals( "" ) );
		} catch ( DBException e ) {
			fail();
		}
	}

}
