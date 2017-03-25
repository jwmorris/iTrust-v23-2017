/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates form fields for a fetus entry
 * 
 * @author wyattmaxey
 */
public class FetusValidator extends POJOValidator<Fetus> {

	@Override
	public void validate( Fetus f ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		double crl = 0;
		if ( f.getCrl().equals( "" ) )
			errorList.addIfNotNull( "Crown Rump Length cannot be empty" );
		else {
			try {
				crl = Double.parseDouble( f.getCrl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Crown Rump Length must be numeric" );
			}
		}
		
		if ( crl < 0 ) {
			errorList.addIfNotNull( "Crown Rump Length cannot be negative" );
		}
		
		double bpd = 0;
		if ( f.getBpd().equals( "" ) )
			errorList.addIfNotNull( "Biparietal Diameter cannot be empty" );
		else {
			try {
				bpd = Double.parseDouble( f.getBpd() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Biparietal Diameter must be numeric" );
			}
		}
		
		if ( bpd < 0 ) {
			errorList.addIfNotNull( "Biparietal Diameter cannot be negative" );
		}
		
		double hc = 0;
		if ( f.getHc().equals( "" ) )
			errorList.addIfNotNull( "Head Circumference cannot be empty" );
		else {
			try {
				hc = Double.parseDouble( f.getHc() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Head Circumference must be numeric" );
			}
		}
		
		if ( hc < 0 ) {
			errorList.addIfNotNull( "Head Circumference cannot be negative" );
		}
		
		double fl = 0;
		if ( f.getFl().equals( "" ) )
			errorList.addIfNotNull( "Femur Length cannot be empty" );
		else {
			try {
				fl = Double.parseDouble( f.getFl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Femur Length must be numeric" );
			}
		}
		
		if ( fl < 0 ) {
			errorList.addIfNotNull( "Femur Length cannot be negative" );
		}
		
		double ofd = 0;
		if ( f.getOfd().equals( "" ) )
			errorList.addIfNotNull( "Occipitofrontal Diameter cannot be empty" );
		else {
			try {
				ofd = Double.parseDouble( f.getOfd() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Occipitofrontal Diameter must be numeric" );
			}
		}
		
		if ( ofd < 0 ) {
			errorList.addIfNotNull( "Occipitofrontal Diameter cannot be negative" );
		}
		
		double ac = 0;
		if ( f.getAc().equals( "" ) )
			errorList.addIfNotNull( "Abdominal Circumference cannot be empty" );
		else {
			try {
				ac = Double.parseDouble( f.getAc() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Abdominal Circumference must be numeric" );
			}
		}
		
		if ( ac < 0 ) {
			errorList.addIfNotNull( "Abdominal Circumference cannot be negative" );
		}
		
		double hl = 0;
		if ( f.getHl().equals( "" ) )
			errorList.addIfNotNull( "Humerus Length cannot be empty" );
		else {
			try {
				hl = Double.parseDouble( f.getHl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Humerus Length must be numeric" );
			}
		}
		
		if ( hl < 0 ) {
			errorList.addIfNotNull( "Humerus Length cannot be negative" );
		}
		
		double efw = 0;
		if ( f.getEfw().equals( "" ) )
			errorList.addIfNotNull( "Estimated Fetal Weight cannot be empty" );
		else {
			try {
				efw = Double.parseDouble( f.getEfw() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Estimated Fetal Weight must be numeric" );
			}
		}
		
		if ( efw < 0 ) {
			errorList.addIfNotNull( "Estimated Fetal Weight cannot be negative" );
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
		
	}
	
	public void validateEdit( Fetus f ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		double crl = 0;
		if ( !f.getCrl().equals( "" ) ) {
			try {
				crl = Double.parseDouble( f.getCrl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Crown Rump Length must be numeric" );
			}
		}
		
		if ( crl < 0 ) {
			errorList.addIfNotNull( "Crown Rump Length cannot be negative" );
		}
		
		double bpd = 0;
		if ( !f.getBpd().equals( "" ) ) {
			try {
				bpd = Double.parseDouble( f.getBpd() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Biparietal Diameter must be numeric" );
			}
		}
		
		if ( bpd < 0 ) {
			errorList.addIfNotNull( "Biparietal Diameter cannot be negative" );
		}
		
		double hc = 0;
		if ( !f.getHc().equals( "" ) ) {
			try {
				hc = Double.parseDouble( f.getHc() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Head Circumference must be numeric" );
			}
		}
		
		if ( hc < 0 ) {
			errorList.addIfNotNull( "Head Circumference cannot be negative" );
		}
		
		double fl = 0;
		if ( !f.getFl().equals( "" ) ) {
			try {
				fl = Double.parseDouble( f.getFl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Femur Length must be numeric" );
			}
		}
		
		if ( fl < 0 ) {
			errorList.addIfNotNull( "Femur Length cannot be negative" );
		}
		
		double ofd = 0;
		if ( !f.getOfd().equals( "" ) ) {
			try {
				ofd = Double.parseDouble( f.getOfd() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Occipitofrontal Diameter must be numeric" );
			}
		}
		
		if ( ofd < 0 ) {
			errorList.addIfNotNull( "Occipitofrontal Diameter cannot be negative" );
		}
		
		double ac = 0;
		if ( !f.getAc().equals( "" ) ) {
			try {
				ac = Double.parseDouble( f.getAc() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Abdominal Circumference must be numeric" );
			}
		}
		
		if ( ac < 0 ) {
			errorList.addIfNotNull( "Abdominal Circumference cannot be negative" );
		}
		
		double hl = 0;
		if ( !f.getHl().equals( "" ) ) {
			try {
				hl = Double.parseDouble( f.getHl() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Humerus Length must be numeric" );
			}
		}
		
		if ( hl < 0 ) {
			errorList.addIfNotNull( "Humerus Length cannot be negative" );
		}
		
		double efw = 0;
		if ( !f.getEfw().equals( "" ) ) {
			try {
				efw = Double.parseDouble( f.getEfw() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Estimated Fetal Weight must be numeric" );
			}
		}
		
		if ( efw < 0 ) {
			errorList.addIfNotNull( "Estimated Fetal Weight cannot be negative" );
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
	}

}
