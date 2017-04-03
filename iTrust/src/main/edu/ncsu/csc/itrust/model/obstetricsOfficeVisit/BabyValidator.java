/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.text.SimpleDateFormat;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates form input for baby data
 * 
 * @author wyattmaxey
 */
public class BabyValidator extends POJOValidator<Baby> {
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/* (non-Javadoc)
	 * @see edu.ncsu.csc.itrust.model.POJOValidator#validate(java.lang.Object)
	 */
	@Override
	public void validate( Baby baby ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		try {
			DATE_FORMAT.parse( baby.getDate() );
		} catch ( Exception e ) {
			errorList.addIfNotNull( "Incorrect date format for Delivery Date.  Should be MM/dd/yyyy" );
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
		
		//Need to validate time
	}

}
