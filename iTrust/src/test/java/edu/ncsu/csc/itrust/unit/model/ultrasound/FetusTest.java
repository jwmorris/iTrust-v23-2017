/**
 * 
 */
package edu.ncsu.csc.itrust.unit.model.ultrasound;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.model.ultasound.Fetus;

/**
 * @author wyatt
 *
 */
public class FetusTest {
	
	private Fetus f;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		f = new Fetus();
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getOvId()}.
	 */
	@Test
	public void testGetOvId() {
		assertEquals( 0, f.getOvId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setOvId(long)}.
	 */
	@Test
	public void testSetOvId() {
		f.setOvId( 1 );
		assertEquals( 1, f.getOvId() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getCrl()}.
	 */
	@Test
	public void testGetCrl() {
		assertTrue( f.getCrl().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setCrl(java.lang.String)}.
	 */
	@Test
	public void testSetCrl() {
		f.setCrl( "1" );
		assertTrue( f.getCrl().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getBpd()}.
	 */
	@Test
	public void testGetBpd() {
		assertTrue( f.getBpd().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setBpd(java.lang.String)}.
	 */
	@Test
	public void testSetBpd() {
		f.setBpd( "1" );
		assertTrue( f.getBpd().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getHc()}.
	 */
	@Test
	public void testGetHc() {
		assertTrue( f.getHc().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setHc(java.lang.String)}.
	 */
	@Test
	public void testSetHc() {
		f.setHc( "1" );
		assertTrue( f.getHc().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getFl()}.
	 */
	@Test
	public void testGetFl() {
		assertTrue( f.getFl().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setFl(java.lang.String)}.
	 */
	@Test
	public void testSetFl() {
		f.setFl( "1" );
		assertTrue( f.getFl().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getOfd()}.
	 */
	@Test
	public void testGetOfd() {
		assertTrue( f.getOfd().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setOfd(java.lang.String)}.
	 */
	@Test
	public void testSetOfd() {
		f.setOfd( "1" );
		assertTrue( f.getOfd().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getAc()}.
	 */
	@Test
	public void testGetAc() {
		assertTrue( f.getAc().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setAc(java.lang.String)}.
	 */
	@Test
	public void testSetAc() {
		f.setAc( "1" );
		assertTrue( f.getAc().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getHl()}.
	 */
	@Test
	public void testGetHl() {
		assertTrue( f.getHl().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setHl(java.lang.String)}.
	 */
	@Test
	public void testSetHl() {
		f.setHl( "1" );
		assertTrue( f.getHl().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getEfw()}.
	 */
	@Test
	public void testGetEfw() {
		assertTrue( f.getEfw().equals( "" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setEfw(java.lang.String)}.
	 */
	@Test
	public void testSetEfw() {
		f.setEfw( "1" );
		assertTrue( f.getEfw().equals( "1" ) );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#getMultiNum()}.
	 */
	@Test
	public void testGetMultiNum() {
		assertEquals( 0, f.getMultiNum() );
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.Fetus#setMultiNum(int)}.
	 */
	@Test
	public void testSetMultiNum() {
		f.setMultiNum( 1 );
		assertEquals( 1, f.getMultiNum() );
	}

}
