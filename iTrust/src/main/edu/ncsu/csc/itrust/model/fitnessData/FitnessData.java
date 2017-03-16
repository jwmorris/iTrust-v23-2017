/**
 * 
 */
package edu.ncsu.csc.itrust.model.fitnessData;

import java.util.*;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

/**
 * provides database access
 * 
 * @author wyattmaxey
 */
public interface FitnessData extends DataBean<Fitness> {
	/**
	 * returns a Fitness object given a date
	 * 
	 * @param pid
	 * 		patient id
	 * @param date
	 * 		date of fitness data to get
	 * @return
	 * 		fitness data
	 * @throws DBException
	 */
	public Fitness getFitnessData( String pid, String date ) throws DBException;
	
	public List<Fitness> getFitnessDataForPatient( String pid ) throws DBException;
	
	public List<Fitness> getFitnessDataForPatientDates(String pid, String startDate, String endDate) throws DBException;
}
