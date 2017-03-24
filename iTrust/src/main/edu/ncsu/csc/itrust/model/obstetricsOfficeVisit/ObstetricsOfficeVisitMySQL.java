/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.io.Serializable;
import java.util.List;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * @author wyatt
 *
 */
public class ObstetricsOfficeVisitMySQL implements ObstetricsOfficeVisitData, Serializable {

	private static final long serialVersionUID = 8463472964665692376L;

	@Override
	public List<ObstetricsOfficeVisit> getAll() throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObstetricsOfficeVisit getByID(long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ObstetricsOfficeVisit addObj) throws FormValidationException, DBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ObstetricsOfficeVisit updateObj) throws DBException, FormValidationException {
		// TODO Auto-generated method stub
		return false;
	}

}
