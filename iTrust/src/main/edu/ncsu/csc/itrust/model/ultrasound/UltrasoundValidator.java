/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultrasound;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.activation.MimetypesFileTypeMap;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates ultrasound form data
 * 
 * @author wyattmaxey
 */
public class UltrasoundValidator extends POJOValidator<Ultrasound> {
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public UltrasoundValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( Ultrasound us ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		if ( us.getDateCreated() == null || us.getDateCreated().equals("")) {
			errorList.addIfNotNull( "Creation date cannot be empty" );
		} else {
			try {
				DATE_FORMAT.parse( us.getDateCreated() );
			} catch ( ParseException e ) {
				errorList.addIfNotNull("Creation Date is invalid");
			}
		}
		
		if ( us.getCrl() < 0 ) {
			errorList.addIfNotNull( "Crown Rump Length  cannot be negative" );
		}
		
		if ( us.getBpd() < 0 ) {
			errorList.addIfNotNull( "Biparietal Diameter cannot be negative" );
		}
		
		if ( us.getHc() < 0 ) {
			errorList.addIfNotNull( "Head Circumference cannot be negative" );
		}
		
		if ( us.getFl() < 0 ) {
			errorList.addIfNotNull( "Femur Length cannot be negative" );
		}
		
		if ( us.getOfd() < 0 ) {
			errorList.addIfNotNull( "Occipitofrontal Diameter cannot be negative" );
		}
		
		if ( us.getAc() < 0 ) {
			errorList.addIfNotNull( "Abdominal Circumference cannot be negative" );
		}
		
		if ( us.getHl() < 0 ) {
			errorList.addIfNotNull( "Humerus Length cannot be negative" );
		}
		
		if ( us.getEfw() < 0 ) {
			errorList.addIfNotNull( "Estimated Fetal Weight cannot be negative" );
		}
		
		File f = null;
		try {
			f = new File( us.getPicturePath() );
		} catch ( Exception e ) {
			errorList.addIfNotNull( "Image could not be opened" );
		}
		
		/**
		 * Help from this post: http://stackoverflow.com/questions/9643228/test-if-file-is-an-image
		 */
		 String mimetype= new MimetypesFileTypeMap().getContentType( f );
	     String type = mimetype.split( "/" )[0];
	     if( !type.equals( "image" ) )
	    	 errorList.addIfNotNull( "Image could not be opened" );
		
		if ( errorList.hasErrors() ) {
			throw new FormValidationException( errorList );
		}
		
	}
}
