package edu.ncsu.csc.itrust.model.fitnessData;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

public class FitnessValidator extends POJOValidator<Fitness> {
	/** data source */
	private DataSource ds;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * default constructor
	 * 
	 * @param ds
	 * 		data source
	 */
	public FitnessValidator( DataSource ds ) {
		this.ds = ds;
	}
	
	public FitnessValidator() {
		
	}

	@Override
	public void validate( Fitness obj ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		String date = obj.getDate();
		String pid = obj.getPid();
		
		if ( pid == null) {
			errorList.addIfNotNull( "Cannot add fitness data: invalid patient MID" );
			throw new FormValidationException( errorList );
		}
		
		if(obj.getSteps() < 0) {
			errorList.addIfNotNull( "Steps must be a positive integer\n" );
		}
		
		if ( obj.getCalories() < 0 ) {
			errorList.addIfNotNull( "Calories must be a positive integer\n" );
		}
		
		if ( obj.getDistance() < 0 ) {
			errorList.addIfNotNull( "Distance must be a positive value\n" );
		}
		
		if ( obj.getFloors() < 0 ) {
			errorList.addIfNotNull( "Floors must be a positive integer\n" );
		}
		
		if ( obj.getMinsSed() < 0 || obj.getMinsSed() > 1440) {
			errorList.addIfNotNull( "Minutes Sedentary must be a positive integer less than 1440" );
		}
		if ( obj.getMinsFA() < 0 || obj.getMinsFA() > 1440) {
			errorList.addIfNotNull( "Minutes Fairly Active must be a positive integer less than 1440" );
		}
		
		if ( obj.getMinsLA() < 0 || obj.getMinsLA() > 1440) {
			errorList.addIfNotNull( "Minutes Lightly Active must be a positive integer less than 1440" );
		}
		
		if ( obj.getMinsVA() < 0 || obj.getMinsVA() > 1440) {
			errorList.addIfNotNull( "Minutes Very Active must be a positive integer less than 1440" );
		}
		
		if ( obj.getActiveCals() < 0 ) {
			errorList.addIfNotNull( "Active Calories must be a positive integer\n" );
		}
		
		if(obj.getActiveHours() < 0 || obj.getActiveHours() > 24) {
			errorList.addIfNotNull( "Active Hours must be a positive integer less than 24" );
		}
		
		if(obj.getHRAvg() < 0) {
			errorList.addIfNotNull( "Heart Rate Average must be a positive integer\n" );
		}
		
		if(obj.getHRHigh() < 0) {
			errorList.addIfNotNull( "High Heart Rate must be a positive integer\n" );
		}
		
		if(obj.getHRLow() < 0) {
			errorList.addIfNotNull( "Low Heart Rate must be a positive integer\n" );
		}
		
		if(obj.getUVExposure() < 0 || obj.getUVExposure() > 1440) {
			errorList.addIfNotNull( "UV Exposure must be a positive integer less than 1440" );
		}
		
		Date dateSQL = null;
		try {
			dateSQL = new Date( DATE_FORMAT.parse( date ).getTime() );
		} catch ( ParseException e ) {
			errorList.addIfNotNull( "Date is incorrectly formatted\n" );
		}

		if (errorList.hasErrors()) {
			throw new FormValidationException(errorList);
		}
		
	}

}
