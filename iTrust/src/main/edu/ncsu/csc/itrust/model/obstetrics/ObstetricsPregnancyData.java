/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetrics;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

/**
 * interface for database access
 * 
 * @author wyattmaxey
 */
public interface ObstetricsPregnancyData extends DataBean<ObstetricsPregnancy> {
	/**
	 * return the pregnancy info with intializationDate for the given patient
	 * 
	 * @param patientMid
	 * 		patient id for pregnancy
	 * @param initializationDate
	 * 		initialization date of pregnancy
	 * @return
	 * 		pregnancy with matching initialization date and patient id
	 */
	public ObstetricsPregnancy getObstetricsPregnancy( long pid, String initDate ) throws DBException;
	
	/**
	 * return all prior pregnancies for patient
	 * 
	 * @param patientMid
	 * 		patient id to query
	 * @return
	 * 		list of all prior pregnancies for given patient
	 */
	public List<ObstetricsPregnancy> getPastObstetricsPregnanciesForPatient( long pid ) throws DBException;
	
	/**
	 * return current pregnancy for patient
	 * 
	 * @param patientMid
	 * 		patient to query
	 * @return
	 * 		current pregnancy for patient, null if there is not one
	 */
	public ObstetricsPregnancy getCurrentObstetricsPregnancy( long pid ) throws DBException;
	
	
	public void updatePriorPregnancy(ObstetricsPregnancy op, String date) throws DBException;
}
