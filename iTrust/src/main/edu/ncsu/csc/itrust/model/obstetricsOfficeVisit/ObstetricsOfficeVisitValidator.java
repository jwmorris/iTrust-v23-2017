/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

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
	
	DataSource ds;
	
	public static final int MAX_WEEKS_PREGNANT = 49;
	
	public ObstetricsOfficeVisitValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( ObstetricsOfficeVisit ov ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		int weight = 0;
		if ( ov.getWeight().equals( "" ) )
			errorList.addIfNotNull( "Weight cannot be empty" );
		else {
			try {
				weight = Integer.parseInt( ov.getWeight() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Weight must be numeric" );
			}
			
			if ( weight < 0 )
				errorList.addIfNotNull( "Weight cannot be negative" );
		}

		int bpOne = 0;
		int bpTwo = 0;
		if ( ov.getBp().equals( "" ) )
			errorList.addIfNotNull( "Blood Pressure cannot be empty" );
		if(!ov.getBp().contains("/"))
			errorList.addIfNotNull("Blood Pressure is invalid.");
		else {
			String[] bpNum = ov.getBp().split("/");
			String bpFirst = bpNum[0];
			String bpSecond = bpNum[1];
			try {
				bpOne = Integer.parseInt( bpFirst );
				bpTwo = Integer.parseInt(bpSecond);
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Blood Pressure must be numeric" );
			}
			
			if ( bpOne < 0 || bpTwo < 0 )
				errorList.addIfNotNull( "Blood Pressure cannot be negative" );
		}
		
		int fhr = 0;
		if ( ov.getFhr().equals( "" ) )
			errorList.addIfNotNull( "Fetal Heartrate cannot be empty" );
		else {
			try {
				fhr = Integer.parseInt( ov.getFhr() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Fetal Heartrate must be numeric" );
			}
			
			if ( fhr < 0 )
				errorList.addIfNotNull( "Fetal Heartrate cannot be negative" );
		}
		
		int babies = 0;
		if ( ov.getNumBabies().equals( "" ) )
			errorList.addIfNotNull( "Number of babies cannot be empty" );
		else {
			try {
				babies = Integer.parseInt( ov.getNumBabies() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Number of Babies must be numeric" );
			}
			
			if ( babies < 0 ) {
				errorList.addIfNotNull( "Number of Babies cannot be negative" );
			}
			
			if ( babies > 1 && !ov.isMultiplePregnancy() ) {
				errorList.addIfNotNull( "Multiple Pregnancy must be set to true if more than one baby is expected." );
			} else if ( babies == 1 && ov.isMultiplePregnancy() ) {
				errorList.addIfNotNull( "More than one baby must be expected if there is a multiple pregnancy." );
			}
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
	}
	
	public void validateEdit( ObstetricsOfficeVisit ov ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
				
		int weight = 0;
		if ( !ov.getWeight().equals( "" ) ) {
			try {
				weight = Integer.parseInt( ov.getWeight() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Weight must be numeric" );
			}
			
			if ( weight < 0 )
				errorList.addIfNotNull( "Weight cannot be negative" );
		}

		int bpOne = 0;
		int bpTwo = 0;
		if (! ov.getBp().equals( "" ) ) {
			if(!ov.getBp().contains("/"))
				errorList.addIfNotNull("Blood Pressure is invalid.");
			String[] bpNum = ov.getBp().split("/");
			String bpFirst = bpNum[0];
			String bpSecond = bpNum[1];
			try {
				bpOne = Integer.parseInt( bpFirst );
				bpTwo = Integer.parseInt(bpSecond);
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Blood Pressure must be numeric" );
			}
			
			if ( bpOne < 0 || bpTwo < 0)
				errorList.addIfNotNull( "Blood Pressure cannot be negative" );
		}
		
		int fhr = 0;
		if ( !ov.getFhr().equals( "" ) ) {
			try {
				fhr = Integer.parseInt( ov.getFhr() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Fetal Heartrate must be numeric" );
			}
			
			if ( fhr < 0 )
				errorList.addIfNotNull( "Fetal Heartrate cannot be negative" );
		}
		
		int babies = 0;
		if ( ov.getNumBabies().equals( "" ) ) {
			try {
				babies = Integer.parseInt( ov.getNumBabies() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Number of Babies must be numeric" );
			}
			
			if ( babies < 0 ) {
				errorList.addIfNotNull( "Number of Babies cannot be negative" );
			}
			
			if ( babies > 1 && !ov.isMultiplePregnancy() ) {
				errorList.addIfNotNull( "Multiple Pregnancy must be set to true if more than one baby is expected." );
			} else if ( babies == 1 && ov.isMultiplePregnancy() ) {
				errorList.addIfNotNull( "More than one baby must be expected if there is a multiple pregnancy." );
			}
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
	}

}
