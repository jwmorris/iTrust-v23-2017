package edu.ncsu.csc.itrust.controller.obstetrics;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "obstetrics_visit_controller")
@SessionScoped
public class ObstetricsVisitController extends iTrustController {
	
	// id of the hcp
	Long hcp;
	// id of the currently selected patient
	Long pid;
	// Personnel Dao to get the currently logged in hcp
	private PersonnelDAO personnelDAO;
	// Patient DAO to get patient information
	private PatientDAO patientDAO;
	// DAO Factory to get other DAO
	private DAOFactory factory;
	//private ObstetricsVisitData obstetricsVisitData;
	private SessionUtils sessionUtils;
	
	/**
	 * Constructor used in application
	 */
	public ObstetricsVisitController() {
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		personnelDAO = factory.getPersonnelDAO();
		patientDAO = factory.getPatientDAO();
		this.hcp = sessionUtils.getSessionLoggedInMIDLong();
		this.pid = sessionUtils.getCurrentPatientMIDLong();
		//this.obstetricsVisitData = newObstetricsVisitMySQL();
	}

	/**
	 * Constructor used in testing
	 * @param ds
	 */
	public ObstetricsVisitController(DataSource ds) {
		this.sessionUtils = SessionUtils.getInstance();
		//this.obstetricsVisitData = new ObstetricsVisitMySQL(ds);
	}
	
	/**
	 * Adds the Obstetrics visit to the database and returns the generated ID
	 * @param ov An obstetrics visit to add
	 * @return The generated id
	 */
	public long addReturnGeneratedId(/*ObstetricsVisit ov*/) {
		return 0;
	}
	
	/**
	 * Adds the Obstetrics visit to the database
	 * @param Obstetric visit to add
	 */
	public void add(/*ObstetricsVisit ov*/) {
		
	}
	
	/**
	 * Returns a list of Obstetrics visit for the specified patient
	 * @param pid Patient's mid
	 * @return List of obstetric visits for the patient
	 */
	public void /*List<ObstetricsVisit>*/getObstetricsVisitsForPatient(String pid) {
		
	}
	
	/**
	 * Returns the obstetric visits for the currently selected patient
	 * @return List of obstetric visits
	 */
	public void/*List<ObstetricsVisit>*/ getOfficeVisitsForCurrentPatient() {
		/*return getOfficeVisitsForPatient(sessionUtils.getCurrentPatientMID());*/
	}
	
	/**
	 * Returns an obstetric visit based on the visit id
	 * 
	 * @param visitID The obstetrics visit ID
	 * @return The obstetrics visit with the id
	 */
	public void/*ObstetricsVisit*/ getVisitByID(String visitID) {
		
	}
	
	/**
	 * @return Returns the obstetrics visit of the selected patient
	 */
	public void/*ObstetricsVisit*/ getSelectedVisit() {
		String visitID = sessionUtils.getRequestParameter("visitID");
		if(visitID == null || visitID.isEmpty()) {
			//return null;
		}
		//return getVisitByID(visitID);
	}
	
	/**
	 * Checks if the patient has any office visits
	 * @param mid
	 * @return
	 */
	public boolean hasVisits(String mid) {
		boolean ret = false;
		/*if((mid != null) ) {
			if(getOfficeVisitsForPatient(mid).size() > 0) {
				ret = true;
			}
		}*/
		return ret;
	}
	
	public boolean currentPatientHasVisits() {
		return hasVisits(sessionUtils.getCurrentPatientMID());
	}
	
	/**
	 * Edits the Obstetrics visit
	 * @param ov The obstetrics visit to edit
	 */
	public void edit(/*ObstetricsVisit ov*/) {
		
	}
	
	/**
	 * Checks if the current logged in user is an OBGYN
	 * @return
	 */
	public boolean isOBGYN() {
		return sessionUtils.getSessionUserRole().equals("OB/GYN");
	}
	
	/**
	 * Checks if the selected patient is a valid obstetrics patient and has an initialization record
	 * @return
	 */
	public boolean isValidObstetricsPatient() {
		boolean obstetricEligible = false;
		if(pid != null){
			try {
				obstetricEligible = patientDAO.getPatient(pid).isObstetricsPatient();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obstetricEligible;
	}
	
	/**
	 * Checks to see if the patient's RH flag is set
	 * @return
	 */
	public boolean isRHChecked() {
		return false;
	}
	
	public void logViewObstetricsVisit() {
		
	}
	
	public void logEditObstetricsVisit() {
		
	}
	
	public void logCreateObstetricsVisit() {
		
	}
	
}
