/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.sql.Date;
import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;

/**
 * 
 * outlines db access methods for obstetrics office visits
 * 
 * @author wyattmaxey
 */
public interface ObstetricsOfficeVisitData extends DataBean<ObstetricsOfficeVisit> {
	public long addReturnsGeneratedId( ObstetricsOfficeVisit ov ) throws FormValidationException, DBException;
	
	/**
	 * returns list of obstetrics office visits for the given patient
	 * 
	 * @param pid
	 * 		patient id
	 * @return
	 * 		list of obstetrics office visits for given patient
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public List<ObstetricsOfficeVisit> getOfficeVistsForPatient( long pid ) throws DBException;
	
	/**
	 * returns office visit for given patient from given date
	 * 
	 * @param pid
	 * 		patient id
	 * @param date
	 * 		date of the office visit
	 * @return
	 * 		the office visit
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public ObstetricsOfficeVisit getOfficeVisitForDate( long pid, Date date ) throws DBException;
	
	/**
	 * returns the ultrasound from a given date for a given date
	 * typically only one, multiple if there are multiple children expected
	 * 
	 * @param ovId
	 * 		office visit id
	 * @param multiNum
	 * 		id of child if multiple pregnancy, pass 0 otherwise
	 * @return
	 * 		fetus data from a given ultrasound
	 */
	public Fetus getFetus( long ovId, int multiNum ) throws DBException;
	
	/**
	 * returns all of the feti for a given patient
	 * I know the plural of fetus is fetuses but feti is cooler
	 * 
	 * @param ultrasoundId
	 * 		ultrasound  id
	 * @return
	 * 		all feti for a given ultrasound
	 */
	//public List<Fetus> getFetiForUltrasound( long ultrasoundId ) throws DBException;
	
	/**
	 * returns a list of fetus data from a given office visit
	 * 
	 * @param ovId
	 * 		id from the office visit
	 * @return
	 * 		list of fetus data
	 */
	public List<Fetus> getFetiForOfficeVisit( long ovId ) throws DBException;
	
	/**
	 * returns a fetus data object given the office visit and the multi num, usually 0
	 * 
	 * @param ovId
	 * 		office visit id
	 * @param multiNum
	 * 		used if there is a multiple pregnancy, usually 0
	 * @return
	 * 		fetus data object
	 */
	public Fetus getFetusForOfficeVisit( long ovId, int multiNum ) throws DBException;
	
	/**
	 * returns list of ultrasounds for a given patient
	 * 
	 * @param pid
	 * 		patient id
	 * @return
	 * 		list of ultrasound data objects
	 */
	public List<Ultrasound> getUltrasoundsForPatient( long pid ) throws DBException;
	
	/**
	 * returns an ultrasound data object given the office visit id
	 * 
	 * @param ovId
	 * 		office visit id
	 * @return
	 * 		ultrasound from the office visit
	 */
	public List<Ultrasound> getUltrasoundByOfficeVisitId( long ovId ) throws DBException;
	
	/**
	 * return the ultrasound data object for a given patient on the given date
	 * 
	 * @param pid
	 * 		patient id
	 * @param date
	 * 		date of ultrasound
	 * @return
	 * 		ultrasound data object
	 */
	public Ultrasound getUltrasoundByDate( long pid, Date date ) throws DBException;
	
	/**
	 * adds a fetus to the fetus sql database
	 * 
	 * @param f
	 * 		fetus to add
	 * @return
	 * 		true if added, false otherwise
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean addFetus( Fetus f ) throws DBException, FormValidationException;
	
	/**
	 * updates fetus data info
	 * 
	 * @param f
	 * 		fetus data object to update
	 * @return
	 * 		true if successfully updated, false otherwise
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean updateFetus( Fetus f ) throws DBException, FormValidationException;
	
	/**
	 * add a given ultrasound object to the ultrasound database
	 * 
	 * @param us
	 * 		ultrasound data object to add to the database
	 * @return
	 * 		true if successfully added, false otherwise
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean addUltrasound( Ultrasound us ) throws DBException;
	
	/**
	 * updates a given ultrasound object in the database
	 * 
	 * @param us
	 * 		ultrasound object with the updated values
	 * @return
	 * 		true if successfully updated, false otherwise
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean updateUltrasound( Ultrasound us ) throws DBException;
}
