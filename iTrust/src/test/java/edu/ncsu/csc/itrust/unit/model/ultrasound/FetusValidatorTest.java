package edu.ncsu.csc.itrust.unit.model.ultrasound;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.FetusValidator;

/**
 * @author wyatt
 *
 */
public class FetusValidatorTest {
	private Fetus f;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		f = new Fetus();
		f.setCrl( "1" );
		f.setAc( "1" );
		f.setBpd( "1" );
		f.setEfw( "1" );
		f.setFl( "1" );
		f.setHc( "1" );
		f.setHl( "1" );
		f.setOfd( "1" );
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.FetusValidator#validateEdit(edu.ncsu.csc.itrust.model.ultasound.Fetus)}.
	 */
	@Test
	public void testValidate() {
		FetusValidator validator = new FetusValidator();
		
		f.setCrl( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Crown Rump" ) );
		}
		
		f.setCrl( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Crown Rump" ) );
		}
		
		f.setAc( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Abdominal Circumference" ) );
		}
		
		f.setAc( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Abdominal Circumference" ) );
		}
		
		f.setHl( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Humerus Length" ) );
		}
		
		f.setHl( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Humerus Length" ) );
		}
		
		f.setEfw( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Estimated Fetal" ) );
		}
		
		f.setEfw( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Estimated Fetal" ) );
		}
		
		f.setBpd( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Biparietal Diameter" ) );
		}
		
		f.setBpd( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Biparietal Diameter" ) );
		}
		
		f.setHc( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Head Circumference" ) );
		}
		
		f.setHc( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Head Circumference" ) );
		}
		
		f.setFl( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Femur Length" ) );
		}
		
		f.setFl( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Femur Length" ) );
		}
		
		f.setOfd( "-1" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Occipitofrontal Diameter" ) );
		}
		
		f.setOfd( "one" );
		try {
			validator.validate( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Occipitofrontal Diameter" ) );
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc.itrust.model.ultasound.FetusValidator#validateEdit(edu.ncsu.csc.itrust.model.ultasound.Fetus)}.
	 */
	@Test
	public void testValidateEdit() {
		FetusValidator validator = new FetusValidator();
		
		f.setCrl( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Crown Rump" ) );
		}
		
		f.setCrl( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Crown Rump" ) );
		}
		
		f.setAc( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Abdominal Circumference" ) );
		}
		
		f.setAc( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Abdominal Circumference" ) );
		}
		
		f.setHl( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Humerus Length" ) );
		}
		
		f.setHl( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Humerus Length" ) );
		}
		
		f.setEfw( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Estimated Fetal" ) );
		}
		
		f.setEfw( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Estimated Fetal" ) );
		}
		
		f.setBpd( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Biparietal Diameter" ) );
		}
		
		f.setBpd( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Biparietal Diameter" ) );
		}
		
		f.setHc( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Head Circumference" ) );
		}
		
		f.setHc( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Head Circumference" ) );
		}
		
		f.setFl( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Femur Length" ) );
		}
		
		f.setFl( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Femur Length" ) );
		}
		
		f.setOfd( "-1" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Occipitofrontal Diameter" ) );
		}
		
		f.setOfd( "one" );
		try {
			validator.validateEdit( f );
		} catch ( FormValidationException e ) {
			assertTrue( e.getMessage().contains( "Occipitofrontal Diameter" ) );
		}
	}

}
