package edu.ncsu.csc.itrust.controller.fitness;


import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessData;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessValidator;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class FitnessController extends iTrustController {
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/** data source */
	DataSource ds;
	
	/**
	 * Constant for the error message to be displayed if the Fitness Data is
	 * invalid.
	 */
	private static final String FITNESS_DATA_CANNOT_BE_UPDATED = "Invalid Fitnes Data";

	/**
	 * Constant for the message to be displayed if the fitness data was
	 * unsuccessfully updated
	 */
	private static final String FITNESS_DATA_CANNOT_BE_ADDED = "Fitness Data Cannot Be Added";

	/**
	 * Constant for the message to be displayed if the fitness data was
	 * successfully created
	 */
	private static final String FITNESS_DATA_SUCCESSFULLY_ADDED = "Fitness Data Successfully Added";

	/**
	 * Constant for the message to be displayed if the fitness data was
	 * successfully updated
	 */
	private static final String FITNESS_DATA_SUCCESSFULLY_UPDATED = "Fitness Data Successfully Updated";

	private FitnessData fitnessData;
	
	private FitnessValidator validate;
	
	private SessionUtils sessionUtils;

	public FitnessController() throws DBException {
		fitnessData = new FitnessMySQL();
		sessionUtils = SessionUtils.getInstance();
		validate = new FitnessValidator();
	}
	
	public FitnessController( DataSource ds ) throws DBException {
		fitnessData = new FitnessMySQL();
		sessionUtils = SessionUtils.getInstance();
		this.ds = ds;
	}
	
	/**
	 * Sends a FacesMessage for FacesContext to display.
	 * 
	 * @param severity
	 *            severity of the message
	 * @param summary
	 *            localized summary message text
	 * @param detail
	 *            localized detail message text
	 * @param clientId
	 *            The client identifier with which this message is associated
	 *            (if any)
	 */
	public void printFacesMessage( Severity severity, String summary, String detail, String clientId ) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null) {
			return;
		}
		ctx.getExternalContext().getFlash().setKeepMessages( true );
		ctx.addMessage( clientId, new FacesMessage( severity, summary, detail ) );
	}

	/**
	 * add fitness data to database
	 * 
	 * @param f
	 * 		fitness data to add
	 * @throws SQLException 
	 */
	public boolean add( Fitness f ) throws SQLException {
		boolean res = false;
		if ( f.getPid() == null ) {
			f.setPid( sessionUtils.getCurrentPatientMID() );
		}
		
		try {
			validate.validate(f);
		} catch(FormValidationException e) {
			List<String> errorList = e.getErrorList();
			String errorString = "";
			for(String s : errorList) {
				printFacesMessage(FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_ADDED, s, null);
			}
			
			return false;
		}

		try {
			res = fitnessData.add( f );
		} catch (DBException e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_ADDED, e.getExtendedMessage(),
					null);
			return false;
		} catch (Exception e) {
			printFacesMessage(FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_ADDED,
					FITNESS_DATA_CANNOT_BE_ADDED, null);
			return false;
		}
		printFacesMessage(FacesMessage.SEVERITY_INFO, FITNESS_DATA_SUCCESSFULLY_ADDED,
					FITNESS_DATA_SUCCESSFULLY_ADDED, null);
		return true;
	}

	/**
	 * @param pid
	 *            patient mid
	 * @return list of fitness data for the given patient
	 */
	public List<Fitness> getFitnessDataForPatient( String pid ) {
		List<Fitness> ret = Collections.emptyList();
		if ( pid != null ) {
			try {
				try {
					Long.parseLong( pid );
				} catch ( Exception e ) {
					return null;
				}
				ret =  fitnessData.getFitnessDataForPatient( pid );
			} catch ( Exception e ) {
				e.printStackTrace();
				printFacesMessage( FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Fitness Data",
						"Unable to Retrieve Fitness Data", null );
			}
		} else {
			pid = sessionUtils.getCurrentPatientMID();
			try {
				ret = fitnessData.getFitnessDataForPatient( pid );
			} catch ( DBException e) {
				e.printStackTrace();
				printFacesMessage( FacesMessage.SEVERITY_ERROR, "Unable to Retrieve Fitness Data",
						"Unable to Retrieve Fitness Data", null );
			}
		}
		return ret;
	}
	
	/**
	 * @return fitnessdata for patient in given range
	 * @throws DBException 
	 */
	public List<Fitness> getFitnessDateRange( String startDate, String endDate, String pid) throws DBException {
		//String pid = sessionUtils.getCurrentPatientMID();
		if ( startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty() ) {
			return null;
		}
		
		List<Fitness> ret = fitnessData.getFitnessDataForPatientDates(pid, startDate, endDate);
		
		return ret;
	}
	
	public boolean edit( Fitness f ) {
		boolean res = false;

		try {
			validate.validate(f);
		} catch(FormValidationException e) {
			List<String> errorList = e.getErrorList();
			String errorString = "";
			for(String s : errorList) {
				printFacesMessage(FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_ADDED, s, null);
			}
			return false;
		}
		try {
			res = fitnessData.update( f );
		} catch (DBException e) {
			printFacesMessage( FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_UPDATED, e.getExtendedMessage(),
					null );
			return false;
		} catch (Exception e) {
			printFacesMessage( FacesMessage.SEVERITY_ERROR, FITNESS_DATA_CANNOT_BE_UPDATED,
					FITNESS_DATA_CANNOT_BE_UPDATED, null );
			return false;
		}
		printFacesMessage( FacesMessage.SEVERITY_INFO, FITNESS_DATA_SUCCESSFULLY_UPDATED,
				FITNESS_DATA_SUCCESSFULLY_UPDATED, null );
		return true;
	}
}

