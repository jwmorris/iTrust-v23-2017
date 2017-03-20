/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.obstetrics;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

/**
 * @author wyattmaxey
 *
 */
public class ObstetricsPregnancyTest {
	private TestDataGenerator gen;
	
	private Date initDate;
	
	private Date lmp;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	@Before
	public void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		Calendar cal = Calendar.getInstance();
		cal.set( 2017, 1, 1 );
		initDate = new Date( Calendar.getInstance().getTimeInMillis() );
		lmp = new Date( cal.getTimeInMillis() );
	}
	
	private boolean equalObstetricsPregnancy( ObstetricsPregnancy op1, ObstetricsPregnancy op2 ) {
		return op1.getDateInit().toString().equals( op2.getDateInit().toString() )
				&& op1.getLmp().toString().equals( op2.getLmp().toString() )
				&& op1.getEdd().toString().equals( op2.getEdd().toString() )
				&& op1.getWeeksPregnant() == op2.getWeeksPregnant()
				&& op1.getConcepYear() == op2.getConcepYear()
				&& op1.getTotalWeeksPregnant() == op2.getTotalWeeksPregnant()
				&& op1.getHrsLabor() == op2.getHrsLabor()
				&& op1.getDeliveryType().equals( op2.getDeliveryType() )
				&& op1.getMultiplePregnancy() == op2.getMultiplePregnancy()
				&& op1.getBabyCount() == op2.getBabyCount()
				&& op1.getCurrent() == op2.getCurrent();
				
	}
	
	private ObstetricsPregnancy newObstetricsPregnancy( Date initDate, Date lmp ) {
		ObstetricsPregnancy op = new ObstetricsPregnancy();
		op.setPid( 2 );
		op.setDateInit( DATE_FORMAT.format( initDate ) );
		op.setLmp( DATE_FORMAT.format( lmp ) );
		op.setConcepYear( 0 );
		op.setTotalWeeksPregnant( 0 );
		op.setHrsLabor( 0 );
		op.setWeightGain( 0 );
		op.setDeliveryType( "" );
		op.setMultiplePregnancy( false );
		op.setBabyCount( 1 );
		op.setCurrent( true );
		return op;
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getPid()}.
	 */
	@Test
	public void testGetPid() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( 2, op.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setPid(long)}.
	 */
	@Test
	public void testSetPid() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setPid( 1 );
		assertEquals( 1, op.getPid() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getDateInit()}.
	 */
	@Test
	public void testGetDateInit() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( this.initDate.toString(), op.getDateInit().toString() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setDateInit(java.sql.Date)}.
	 */
	@Test
	public void testSetDateInitDate() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( this.initDate.getTime() );
		cal.add( Calendar.DAY_OF_YEAR, 1 );
		this.initDate.setTime( cal.getTimeInMillis() );
		op.setDateInit( DATE_FORMAT.format( this.initDate ) );
		assertEquals( this.initDate.toString(), op.getDateInit().toString() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setDateInit(java.lang.String)}.
	 */
	@Test
	public void testSetDateInitString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getLmp()}.
	 */
	@Test
	public void testGetLmp() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setLmp(java.sql.Date)}.
	 */
	@Test
	public void testSetLmpDate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setLmp(java.lang.String)}.
	 */
	@Test
	public void testSetLmpString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getEdd()}.
	 */
	@Test
	public void testGetEdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setEdd(java.sql.Date)}.
	 */
	@Test
	public void testSetEddDate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setEdd(java.lang.String)}.
	 */
	@Test
	public void testSetEddString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getWeeksPregnant()}.
	 */
	@Test
	public void testGetWeeksPregnant() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setWeeksPregnant(int)}.
	 */
	@Test
	public void testSetWeeksPregnant() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getConcepYear()}.
	 */
	@Test
	public void testGetConcepYear() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( 0, op.getConcepYear() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setConcepYear(int)}.
	 */
	@Test
	public void testSetConcepYear() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setConcepYear( 2016 );
		assertEquals( 2016, op.getConcepYear() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getTotalWeeksPregnant()}.
	 */
	@Test
	public void testGetTotalWeeksPregnant() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( 0, op.getTotalWeeksPregnant() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setTotalWeeksPregnant(int)}.
	 */
	@Test
	public void testSetTotalWeeksPregnant() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setTotalWeeksPregnant( 40 );
		assertEquals( 40, op.getTotalWeeksPregnant() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getHrsLabor()}.
	 */
	@Test
	public void testGetHrsLabor() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( ( int )0, ( int )op.getHrsLabor() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setHrsLabor(double)}.
	 */
	@Test
	public void testSetHrsLabor() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setHrsLabor( 7.0 );
		assertEquals( ( int )7.0, ( int )op.getHrsLabor() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getWeightGain()}.
	 */
	@Test
	public void testGetWeightGain() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( 0, op.getWeightGain() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setWeightGain(int)}.
	 */
	@Test
	public void testSetWeightGain() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setWeightGain( 10 );
		assertEquals( 10, op.getWeightGain() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getDeliveryType()}.
	 */
	@Test
	public void testGetDeliveryType() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( "", op.getDeliveryType() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setDeliveryType(java.lang.String)}.
	 */
	@Test
	public void testSetDeliveryType() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setDeliveryType( "Magic" );
		assertEquals( "Magic", op.getDeliveryType() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getMultiplePregnancy()}.
	 */
	@Test
	public void testGetMultiplePregnancy() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( false, op.getMultiplePregnancy() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setMultiplePregnancy(boolean)}.
	 */
	@Test
	public void testSetMultiplePregnancy() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setMultiplePregnancy( true );
		assertEquals( true, op.getMultiplePregnancy() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getBabyCount()}.
	 */
	@Test
	public void testGetBabyCount() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( 0, op.getBabyCount() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setBabyCount(int)}.
	 */
	@Test
	public void testSetBabyCount() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setBabyCount( 1 );
		assertEquals( 1, op.getBabyCount() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#getCurrent()}.
	 */
	@Test
	public void testGetCurrent() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		assertEquals( true, op.getCurrent() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy#setCurrent(boolean)}.
	 */
	@Test
	public void testSetCurrent() {
		ObstetricsPregnancy op = newObstetricsPregnancy( this.initDate, this.lmp );
		op.setCurrent( false );
		assertEquals( false, op.getCurrent() );
	}

}
