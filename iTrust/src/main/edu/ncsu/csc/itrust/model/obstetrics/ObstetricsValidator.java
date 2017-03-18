/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

import javax.sql.DataSource;

import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;

/**
 * validates fields for a new pregnancy entry
 * 
 * @author wyattmaxey
 */
public class ObstetricsValidator extends POJOValidator<ObstetricsPregnancy> {
	
	private DataSource ds;
	
	public ObstetricsValidator( DataSource ds ) {
		this.ds = ds;
	}

	@Override
	public void validate( ObstetricsPregnancy op ) throws FormValidationException {
		//Implement with controller
		
	}

}
