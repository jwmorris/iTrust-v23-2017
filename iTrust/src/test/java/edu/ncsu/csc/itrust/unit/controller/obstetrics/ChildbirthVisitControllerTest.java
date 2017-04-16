package edu.ncsu.csc.itrust.unit.controller.obstetrics;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class ChildbirthVisitControllerTest {
	
	private ChildbirthVisitController con;
	private SessionUtils mockSessionUtils;
	private Childbirth birth;
	private DataSource ds;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		
		gen.standardData();
		mockSessionUtils = Mockito.mock(SessionUtils.class);
		Mockito.doReturn(Long.parseLong( "9000000012" ) ).when(mockSessionUtils).getSessionLoggedInMIDLong();
		Mockito.doReturn(Long.parseLong( "2" )).when(mockSessionUtils).getCurrentPatientMIDLong();
		ds = ConverterDAO.getDataSource();
		con = new ChildbirthVisitController( ds, TestDAOFactory.getTestInstance(), mockSessionUtils );
		birth = new Childbirth();
		birth.setAmtEpidural( "3" );
		birth.setAmtMagnesium( "4" );
		birth.setAmtNitrous( "5" );
		birth.setAmtPethidine( "0" );
		birth.setAmtPitocin( "0" );
		birth.setPid( 2 );
		birth.setAmtRH( "0" );
	}

	@Test
	public void testChildbirthVisitController() {
		
		try {
			con = new ChildbirthVisitController();
			fail();
		} catch( DBException e ) {
			//pass
		}
	}

	@Test
	public void testChildbirthVisitControllerDataSourceDAOFactorySessionUtils() {
		new ChildbirthVisitController( ConverterDAO.getDataSource(), TestDAOFactory.getTestInstance(), mockSessionUtils );
	}

	@Test
	public void testAddReturnGeneratedId() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
		
		birth.setChildbirthId( 0 );
		birth.setAmtEpidural( "-1" );
		id = con.addReturnGeneratedId( birth );
		assertTrue( con.getChildbirthsForCurrentPatient().size() == 1 );
		
	}

	@Test
	public void testAddChildbirth() {
		con.addChildbirth( birth );
		List<Childbirth> cbList = con.getChildbirthsForPatient( 2 );
		assertNotNull( cbList );
		Childbirth cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		
		birth.setChildbirthId( 0 );
		birth.setAmtEpidural( "-1" );
		con.addChildbirth( birth );
		assertTrue( con.getChildbirthsForCurrentPatient().size() == 1 );
	}

	@Test
	public void testEditChildbirth() {
		con.addChildbirth( birth );
		List<Childbirth> cbList = con.getChildbirthsForPatient( 2 );
		assertNotNull( cbList );
		Childbirth cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		
		cb.setAmtEpidural( "1" );
		
		con.editChildbirth( cb );
		cbList = con.getChildbirthsForPatient( 2 );
		assertNotNull( cbList );
		cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "1" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		
		cb.setAmtEpidural( "-1" );
		
		con.editChildbirth( cb );
		cbList = con.getChildbirthsForPatient( 2 );
		assertNotNull( cbList );
		cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "1" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
	}

	@Test
	public void testGetSelectedVisit() {
		assertNull( con.getSelectedVisit() );
	}

	@Test
	public void testGetChildbirthByID() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
	}

	@Test
	public void testGetChildbirthsForPatient() {
		con.addChildbirth( birth );
		List<Childbirth> cbList = con.getChildbirthsForPatient( 2 );
		assertNotNull( cbList );
		Childbirth cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
	}

	@Test
	public void testGetChildbirthsForCurrentPatient() {
		con.addChildbirth( birth );
		List<Childbirth> cbList = con.getChildbirthsForCurrentPatient();
		assertNotNull( cbList );
		Childbirth cb = cbList.get( 0 );
		assertNotNull( cb );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
	}

	@Test
	public void testCurrentPatientHasVisits() {
		assertFalse( con.currentPatientHasVisits() );
		con.addChildbirth( birth );
		assertTrue( con.currentPatientHasVisits() );
	}

	@Test
	public void testGetBabies() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
		
		Baby b = new Baby();
		b.setDate( "04/02/2017" );
		b.setEstimateDate( false );
		b.setSex( 'M' );
		b.setChildbirthId( id );
		b.setTime( "12:04PM" );
		
		con.addBaby( b );
		
		List<Baby> bList = con.getBabies( id );
		assertNotNull( bList );
		Baby retB = bList.get( 0 );
		assertTrue( retB.getChildbirthId() == id );
		assertTrue( retB.getSex() == 'M' );
		
	}

	@Test
	public void testAddBaby() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
		
		Baby b = new Baby();
		b.setDate( "04/02/2017" );
		b.setEstimateDate( false );
		b.setSex( 'M' );
		b.setChildbirthId( id );
		b.setTime( "12:04PM" );
		
		con.addBaby( b );
		
		List<Baby> bList = con.getBabies( id );
		
		assertNotNull( bList );
		Baby retB = bList.get( 0 );
		assertTrue( retB.getChildbirthId() == id );
		assertTrue( retB.getSex() == 'M' );
		
		b.setDate( "date" );
		
		con.addBaby( b );
		
		bList = con.getBabies( id );
		
		assertNotNull( bList );
		assertTrue( bList.size() == 1 );
		
	}

	@Test
	public void testEditBaby() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
		
		Baby b = new Baby();
		b.setDate( "04/02/2017" );
		b.setEstimateDate( false );
		b.setSex( 'M' );
		b.setChildbirthId( id );
		b.setTime( "12:04PM" );
		
		con.addBaby( b );
		
		List<Baby> bList = con.getBabies( id );
		
		assertNotNull( bList );
		Baby retB = bList.get( 0 );
		assertTrue( retB.getChildbirthId() == id );
		assertTrue( retB.getSex() == 'M' );
		
		b.setSex( 'F' );
		
		con.editBaby( b );
		bList = con.getBabies( id );
		
		assertNotNull( bList );
		assertTrue( bList.size() == 1 );
		retB = bList.get( 0 );
		assertTrue( retB.getChildbirthId() == id );
		assertTrue( retB.getSex() == 'F' );
		
		b.setDate( "date" );
		con.editBaby( b );
		bList = con.getBabies( id );
		
		assertNotNull( bList );
		assertTrue( bList.size() == 1 );
		retB = bList.get( 0 );
		assertTrue( retB.getDate().equals( "04/02/2017" ) );
		
	}

	@Test
	public void testGetBaby() {
		long id = con.addReturnGeneratedId( birth );
		assertTrue( id != 0 );
		Childbirth cb = con.getChildbirthByID( Long.toString( id ) );
		assertTrue( cb.getPid() == 2 );
		assertTrue( cb.getAmtEpidural().equals( "3" ) );
		assertTrue( cb.getAmtMagnesium().equals( "4" ) );
		assertTrue( cb.getAmtNitrous().equals( "5" ) );
		assertTrue( cb.getChildbirthId() == id );
		
		Baby b = new Baby();
		b.setDate( "04/02/2017" );
		b.setEstimateDate( false );
		b.setSex( 'M' );
		b.setChildbirthId( id );
		b.setTime( "12:04PM" );
		
		con.addBaby( b );
		
		Baby retB = con.getBaby( id, 0 );
		assertTrue( retB.getChildbirthId() == id );
		assertTrue( retB.getSex() == 'M' );
	}

	@Test
	public void testIsOBGYN() {
		assertTrue( con.isOBGYN() );
	}

	@Test
	public void testIsErBirth() {
		assertFalse( con.isErBirth() );
		con.setErBirth( true );
		assertTrue( con.isErBirth() );
	}

	@Test
	public void testSetErBirth() {
		assertFalse( con.isErBirth() );
		con.setErBirth( true );
		assertTrue( con.isErBirth() );
	}
	
	@Test
	public void testFinishPregnancy() throws DBException {
		con.addChildbirth( birth );
		Baby baby = new Baby();
		baby.setChildbirthId( 1 );
		baby.setDeliveryType( "vaginal delivery" );
		baby.setName( "Kiddo" );
		baby.setSex( 'm' );
		baby.setTime( "6:00 pm" );
		con.addBaby( baby );
		con.setHoursLabor( "4" );
		con.setYearConception( "2016" );
		con.finishPregnancy();
		ObstetricsPregnancyData opSql = new ObstetricsMySQL( ds );
		assertTrue( opSql.getCurrentObstetricsPregnancy( 2 ).getConcepYear().equals( "" ) );
	}

	@Test
	public void testLogViewChildbirth() {
		/*long id = con.addReturnGeneratedId( birth );
		con.logViewChildbirth( id );
		Mockito.verify(con, 
				Mockito.times(1)).logTransaction(TransactionType.VIEW_CHILDBIRTH_VISIT, Long.parseLong( "9000000012" ), (long)2, Long.toString( id ) );
	*/}

}
