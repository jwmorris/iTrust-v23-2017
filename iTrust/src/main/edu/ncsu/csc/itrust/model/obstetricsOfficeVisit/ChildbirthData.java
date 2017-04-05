/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.DataBean;

/**
 * sql access methods for a childbirth pojo
 * 
 * @author wyattmaxey
 */
public interface ChildbirthData extends DataBean<Childbirth> {
	/**
	 * adds a childbirth to databse and returns the auto id
	 * 
	 * @param cb
	 * 		childbirth to add
	 * @return
	 * 		generated id
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public long addReturnsGeneratedId( Childbirth cb ) throws DBException, FormValidationException;
	
	/**
	 * returns a childbirth visit given the pid and initId
	 * 
	 * @param pid
	 * 		patient mid
	 * @param initId
	 * 		id of initialization record
	 * @return
	 * 		childbirth visit data
	 * @throws DBException
	 */
	public Childbirth getChildbirthVisitForInitId( long pid, long initId ) throws DBException;
	
	/**
	 * get baby pojos for a given childbirth
	 * 
	 * @param childbirthId
	 * 		childbirth id
	 * @return
	 * 		children from childbirth
	 */
	public List<Baby> getBabiesForChildbirth( long childbirthId ) throws DBException;
	
	/**
	 * return all childbirth events for a given patient
	 * 
	 * @param pid
	 * 		id of the patient
	 * @return
	 * 		list of childbirth events objects
	 * @throws DBException
	 */
	public List<Childbirth> getChildbirthsForPatient( long pid ) throws DBException;
	
	/**
	 * returns a child pojo from a childbirth
	 * 
	 * @param childbirthId
	 * 		childbirth visit/event id
	 * @param multiNum
	 * 		number if multiple, usually 0
	 * @return
	 * 		Baby pojo
	 * @throws DBException
	 */
	public Baby getBaby( long childbirthId, int multiNum ) throws DBException;
	
	/**
	 * add child data to the database
	 * 
	 * @param baby
	 * 		baby data to add
	 * @return
	 * 		true if successful
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean addBaby( Baby baby ) throws DBException, FormValidationException;
	
	/**
	 * edits data for a baby in database
	 * 
	 * @param baby
	 * 		new data
	 * @return
	 * 		true if successful
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public boolean updateBaby( Baby baby ) throws DBException, FormValidationException;
}
