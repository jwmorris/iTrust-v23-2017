/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates childbirth form input
 * 
 * @author wyattmaxey
 */
public class ChildbirthValidator extends POJOValidator<Childbirth> {

	@Override
	public void validate( Childbirth cb ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		if ( cb.isEr() && !cb.getDeliveryType().equalsIgnoreCase( "vaginal delivery" ) ) {
			errorList.addIfNotNull( "Delivery Type must be Vaginal during an Emergency Room Delivery" );
		}
		
		double epidural = 0;
		if ( cb.getAmtEpidural().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of Epidural Anaesthesia administered cannot be empty" );
		} else {
			try {
				epidural = Double.parseDouble( cb.getAmtEpidural() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Epidural Anaesthesia administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( epidural > 0 && !cb.isEpidural() )
				errorList.addIfNotNull( "Epidural Anaesthesia administration not indicated" );
			else if ( cb.isEpidural() && epidural == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Epidural Anaesthesia administered" );
			else if ( epidural < 0 )
				errorList.addIfNotNull( "Amount of Epidural Anaesthesia administered cannot be negative" );
		}
		
		double magnesium = 0;
		if ( cb.getAmtMagnesium().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of Magnesium Sulfate administered cannot be empty" );
		} else {
			try {
				magnesium = Double.parseDouble( cb.getAmtMagnesium() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Magnesium Sulfate administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( magnesium > 0 && !cb.isMagnesium() )
				errorList.addIfNotNull( "Magnesium Sulfate administration not indicated" );
			else if ( cb.isMagnesium() && magnesium == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Magnesium Sulfate administered" );
			else if ( magnesium < 0 )
				errorList.addIfNotNull( "Amount of Magnesium Sulfate administered cannot be negative" );
		}
		
		double nitrous = 0;
		if ( cb.getAmtNitrous().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of Nitrous Oxide administered cannot be empty" );
		} else {
			try {
				nitrous = Double.parseDouble( cb.getAmtNitrous() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Nitrous Oxide administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( nitrous > 0 && !cb.isNitrous() )
				errorList.addIfNotNull( "Nitrous Oxide administration not indicated" );
			else if ( cb.isNitrous() && nitrous == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Nitrous Oxide administered" );
			else if ( nitrous < 0 )
				errorList.addIfNotNull( "Amount of Nitrous Oxide administered cannot be negative" );
		}
		
		double pethidine = 0;
		if ( cb.getAmtPethidine().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of Pethidine administered cannot be empty" );
		} else {
			try {
				pethidine = Double.parseDouble( cb.getAmtPethidine() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Pethidine administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( pethidine > 0 && !cb.isPethidine() )
				errorList.addIfNotNull( "Pethidine administration not indicated" );
			else if ( cb.isPethidine() && pethidine == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Pethidine administered" );
			else if ( pethidine < 0 )
				errorList.addIfNotNull( "Amount of Pethidine administered cannot be negative" );
		}
		
		double pitocin = 0;
		if ( cb.getAmtPitocin().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of Pitocin administered cannot be empty" );
		} else {
			try {
				pitocin = Double.parseDouble( cb.getAmtPitocin() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Pitocin administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( pitocin > 0 && !cb.isPitocin() )
				errorList.addIfNotNull( "Pitocin administration not indicated" );
			else if ( cb.isPitocin() && pitocin == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Pitocin administered" );
			else if ( pitocin < 0 )
				errorList.addIfNotNull( "Amount of Pitocin administered cannot be negative" );
		}
		
		double rh = 0;
		if ( cb.getAmtRH().equals( "" ) ) {
			errorList.addIfNotNull( "Amount of RH Immune Globulin administered cannot be empty" );
		} else {
			try {
				rh = Double.parseDouble( cb.getAmtRH() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of RH Immune Globulin must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}
			if ( rh < 0 )
				errorList.addIfNotNull( "Amount of RH Immune Globulin cannot be negative" );
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
	}
	
	public void validateEdit( Childbirth cb ) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		
		if ( cb.isEr() && !cb.getDeliveryType().equalsIgnoreCase( "vaginal" ) ) {
			errorList.addIfNotNull( "Delivery Type must be Vaginal during an Emergency Room Delivery" );
		}
		
		if ( !cb.getAmtEpidural().equals( "" ) ) {
			double epidural = 0;
			try {
				epidural = Double.parseDouble( cb.getAmtEpidural() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Epidural Anaesthesia administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( epidural > 0 && !cb.isEpidural() )
				errorList.addIfNotNull( "Epidural Anaesthesia administration not indicated" );
			else if ( cb.isEpidural() && epidural == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Epidural Anaesthesia administered" );
			else if ( epidural < 0 )
				errorList.addIfNotNull( "Amount of Epidural Anaesthesia administered cannot be negative" );
		}
		
		if ( !cb.getAmtMagnesium().equals( "" ) ) {
			double magnesium = 0;
			try {
				magnesium = Double.parseDouble( cb.getAmtMagnesium() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Magnesium Sulfate administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( magnesium > 0 && !cb.isMagnesium() )
				errorList.addIfNotNull( "Magnesium Sulfate administration not indicated" );
			else if ( cb.isMagnesium() && magnesium == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Magnesium Sulfate administered" );
			else if ( magnesium < 0 )
				errorList.addIfNotNull( "Amount of Magnesium Sulfate administered cannot be negative" );
		}
		
		if ( !cb.getAmtNitrous().equals( "" ) ) {
			double nitrous = 0;
			try {
				nitrous = Double.parseDouble( cb.getAmtNitrous() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Nitrous Oxide administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( nitrous > 0 && !cb.isNitrous() )
				errorList.addIfNotNull( "Nitrous Oxide administration not indicated" );
			else if ( cb.isNitrous() && nitrous == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Nitrous Oxide administered" );
			else if ( nitrous < 0 )
				errorList.addIfNotNull( "Amount of Nitrous Oxide administered cannot be negative" );
		}
		
		if ( !cb.getAmtPethidine().equals( "" ) ) {
			double pethidine = 0;
			try {
				pethidine = Double.parseDouble( cb.getAmtPethidine() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Pethidine administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( pethidine > 0 && !cb.isPethidine() )
				errorList.addIfNotNull( "Pethidine administration not indicated" );
			else if ( cb.isPethidine() && pethidine == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Pethidine administered" );
			else if ( pethidine < 0 )
				errorList.addIfNotNull( "Amount of Pethidine administered cannot be negative" );
		}
		
		if ( !cb.getAmtPitocin().equals( "" ) ) {
			double pitocin = 0;
			try {
				pitocin = Double.parseDouble( cb.getAmtPitocin() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of Pitocin administered must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}

			if ( pitocin > 0 && !cb.isPitocin() )
				errorList.addIfNotNull( "Pitocin administration not indicated" );
			else if ( cb.isPitocin() && pitocin == 0 )
				errorList.addIfNotNull( "Please indicate the amount of Pitocin administered" );
			else if ( pitocin < 0 )
				errorList.addIfNotNull( "Amount of Pitocin administered cannot be negative" );
		}
		
		if ( !cb.getAmtRH().equals( "" ) ) {
			double rh = 0;
			try {
				rh = Double.parseDouble( cb.getAmtRH() );
			} catch ( Exception e ) {
				errorList.addIfNotNull( "Amount of RH Immune Globulin must be numeric" );
				if ( errorList.hasErrors() )
					throw new FormValidationException( errorList );
			}
			if ( rh < 0 )
				errorList.addIfNotNull( "Amount of RH Immune Globulin cannot be negative" );
		}
		
		if ( errorList.hasErrors() )
			throw new FormValidationException( errorList );
	}
	
}
