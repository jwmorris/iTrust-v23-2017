/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsController;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates fields for a new pregnancy entry
 * 
 * @author wyattmaxey
 */
public class ObstetricsValidator extends POJOValidator<ObstetricsPregnancy> {
	
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public static final int MAX_WEEKS_PREGNANT = 49;
	
	public static final String DELIVERY_TYPES[] = new String[] {
			"vaginal delivery",
			"vaginal delivery vacuum assist",
			"vaginal delivery forceps assist",
			"caesarean section",
			"miscarriage"
	};
	
	public ObstetricsValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( ObstetricsPregnancy op ) throws FormValidationException {
		//ObstetricsController occ = new ObstetricsController( ds );
		//ObstetricsController occ = new ObstetricsController();
		ErrorList errorList = new ErrorList();
		
		if ( op.getDateInit() == null ) {
			errorList.addIfNotNull( "Cannot add obstetrics data: Initialization Date cannot be empty" );
		}
		
		if ( op.getLmp() == null ) {
			errorList.addIfNotNull( "Cannot add obstetrics data: LMP cannot be empty" );
		}
		
		if ( !op.getWeeksPregnant().equals( "" ) ) {
			int weeksPreg = 0;
			try {
				weeksPreg = Integer.parseInt( op.getWeeksPregnant() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Weeks Pregnant must be numeric" );
			}
			
			if ( weeksPreg < 0 || weeksPreg > MAX_WEEKS_PREGNANT )
				errorList.addIfNotNull( "The value entered for Weeks Pregnant is invalid" );
		}
		
		int concepYear = 0;
		if ( !op.getConcepYear().equals( "" ) ) {
			try {
				concepYear = Integer.parseInt( op.getConcepYear() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Conception Year must be numeric" );
			}
			
			if ( concepYear > Calendar.getInstance().get( Calendar.YEAR ) || concepYear < 0 ) {
				errorList.addIfNotNull( "The Conception Year entered is invalid" );
			}
		}
		
		if ( !op.getTotalWeeksPregnant().equals( "" ) ) {
			int totalWeeks = 0;
			try {
				totalWeeks = Integer.parseInt( op.getTotalWeeksPregnant() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Total Weeks Pregnant must be numeric" );
			}
			
			if ( totalWeeks < 0 || totalWeeks > MAX_WEEKS_PREGNANT ) {
				errorList.addIfNotNull( "The value entered for Total Weeks Pregnant is invalid" );
			}
		}
		
		if ( !op.getHrsLabor().equals( "" ) ) {
			double hrsLabor = 0;
			try {
				hrsLabor = Double.parseDouble( op.getHrsLabor() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Hours in Labor must be numeric" );
			}
			
			if ( hrsLabor < 0 ) {
				errorList.addIfNotNull( "The value entered for Hours in Labor is invalid" );
			}
		}
		
		if ( !op.getWeightGain().equals( "" ) ) {
			int weight = 0;
			try {
				weight = Integer.parseInt( op.getWeightGain() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Weight Gained must be numeric" );
			}
			
			if ( weight < 0 ) {
				errorList.addIfNotNull( "The value entered for Weight Gained is invalid" );
			}
		}
		
		if ( !op.getBabyCount().equals( "" ) ) {
			int babies = 0;
			try {
				babies = Integer.parseInt( op.getBabyCount() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Number of Babies must be numeric" );
			}
			
			if ( babies < 0 ) {
				errorList.addIfNotNull( "The value entered for Babies is invalid" );
			}
			
			if ( babies > 1 && !op.getMultiplePregnancy() ) {
				errorList.addIfNotNull( "Multiple Pregnancy must be set to true if more than one baby is expected." );
			} else if ( babies == 1 && op.getMultiplePregnancy() ) {
				errorList.addIfNotNull( "More than one baby must be expected if there is a multiple pregnancy." );
			}
		}
		
		if ( !op.getDeliveryType().equals( "" ) ) {
			boolean valid = false;
			String delivery = op.getDeliveryType();
			for ( int i = 0; i < DELIVERY_TYPES.length; i++ ) {
				if ( delivery.equalsIgnoreCase( DELIVERY_TYPES[i] ) ) {
					valid = true;
					break;
				}
			}
			if ( !valid )
				errorList.addIfNotNull( "Invalid Delivery Type" );
		}
		
		if ( errorList.hasErrors() ) {
			throw new FormValidationException( errorList );
		}
		
	}

}
