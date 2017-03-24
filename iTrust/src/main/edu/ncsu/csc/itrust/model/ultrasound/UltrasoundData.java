/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultrasound;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

/**
 * outlines sql operations for the ultrasound data type
 * 
 * @author wyattmaxey
 */
public interface UltrasoundData extends DataBean<Ultrasound> {
	/**
	 * returns the ultrasound from a given date for a given date
	 * typically only one, multiple if there are multiple children expected
	 * 
	 * @param pid
	 * 		patient mid
	 * @param dateCreated
	 * 		date ultrasound was done
	 * @param
	 * 		number of child (usually 0) to lookup, used for multipregnancy
	 * @return
	 * 		ultrasound for a given patient from the date
	 * 		multiple are returned if mroe than one child is expected
	 */
	public Ultrasound getUltrasound( long pid, String dateCreated, int multiNum ) throws DBException;
	
	/**
	 * returns all of the ultrasounds for a given patient
	 * 
	 * @param pid
	 * 		patient mid
	 * @return
	 * 		all ultrasounds for a given patient
	 */
	public List<Ultrasound> getUltrasoundsForPatient( long pid ) throws DBException;
}
