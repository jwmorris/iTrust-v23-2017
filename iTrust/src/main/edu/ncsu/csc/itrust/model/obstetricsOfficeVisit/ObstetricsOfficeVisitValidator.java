/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates an obstetrics office visit
 * 
 * @author wyattmaxey
 *
 */
public class ObstetricsOfficeVisitValidator extends POJOValidator<ObstetricsOfficeVisit> {
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	DataSource ds;
	
	public static final int MAX_WEEKS_PREGNANT = 49;
	
	public ObstetricsOfficeVisitValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( ObstetricsOfficeVisit ov ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		if ( ov.getVisitDate() == null || ov.getVisitDate().equals("")) {
			errorList.addIfNotNull( "Office Visit Date cannot be empty" );
		} else {
			try {
				DATE_FORMAT.parse( ov.getVisitDate() );
			} catch ( ParseException e ) {
				errorList.addIfNotNull( "Office Visit Date is invalid" );
			}
		}
		
		if ( !ov.getWeeksPregnant().equals( "" ) ) {
			int weeksPreg = 0;
			try {
				weeksPreg = Integer.parseInt( ov.getWeeksPregnant() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Weeks Pregnant must be numeric" );
			}
			
			if ( weeksPreg < 0 || weeksPreg > MAX_WEEKS_PREGNANT )
				errorList.addIfNotNull( "The value entered for Weeks Pregnant is invalid" );
		}
		
		if ( ov.getWeight() < 0 )
			errorList.addIfNotNull( "Weight cannot be negative" );
		
		if ( ov.getBp() < 0 )
			errorList.addIfNotNull( "Blood Pressure cannot be negative" );
		
		if ( ov.getFhr() < 0 )
			errorList.addIfNotNull( "Fetal Heartrate cannot be negative" );
	}

}
