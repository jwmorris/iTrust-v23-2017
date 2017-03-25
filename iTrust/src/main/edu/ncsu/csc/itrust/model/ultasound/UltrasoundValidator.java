/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

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
	
	public UltrasoundValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( Ultrasound us ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		if ( !us.getPicPath().equals( "" ) ) {
			File f = null;
			try {
				f = new File( us.getPicPath() );
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
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
		
	}
}
