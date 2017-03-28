package edu.ncsu.csc.itrust.controller.obstetrics;

import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "obstetrics_visit_controller")
@SessionScoped
public class ObstetricsVisitController extends iTrustController {
	
	// Patient DAO to get patient information
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	// DAO Factory to get other DAO
	private DAOFactory factory;
	private ObstetricsOfficeVisitData obstetricsVisitData;
	private SessionUtils sessionUtils;
	private ObstetricsPregnancyData sql;
	
	/**
	 * Constructor used in application
	 */
	public ObstetricsVisitController() {
		
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		try {
			this.obstetricsVisitData = new ObstetricsOfficeVisitMySQL();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sql = new ObstetricsMySQL();
		} catch (DBException e) {
			e.printStackTrace();
			
		}
	}

	/**
	 * Constructor used in testing
	 * @param ds
	 */
	public ObstetricsVisitController(DataSource ds, DAOFactory factory, SessionUtils utils) {
		this.sessionUtils = utils;
		this.obstetricsVisitData = new ObstetricsOfficeVisitMySQL(ds);
		this.factory = factory;
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		try {
			sql = new ObstetricsMySQL(ds);
		} catch (DBException e) {
			System.out.println("DB fail");
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the Obstetrics visit to the database and returns the generated ID
	 * @param ov An obstetrics visit to add
	 * @return The generated id
	 */
	public long addReturnGeneratedId( ObstetricsOfficeVisit ov ) {
		long ret = 0;
		try {
			ret = obstetricsVisitData.addReturnsGeneratedId( ov );
			logTransaction(TransactionType.CREATE_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));
		} catch ( DBException e ) {

			e.printStackTrace();
		} catch ( FormValidationException e ) {

			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
		return ret;
	}
	
	/**
	 * Adds the Obstetrics visit to the database
	 * @param Obstetric visit to add
	 */
	public void add(ObstetricsOfficeVisit ov) {
		try {
			obstetricsVisitData.add( ov );
			logTransaction(TransactionType.CREATE_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));

		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	/**
	 * Returns a list of Obstetrics visit for the specified patient
	 * @param pid Patient's mid
	 * @return List of obstetric visits for the patient
	 */
	public List<ObstetricsOfficeVisit> getObstetricsVisitsForPatient(Long pid) {
		List<ObstetricsOfficeVisit> ret = Collections.emptyList();
		try {
			ret = obstetricsVisitData.getOfficeVistsForPatient( pid );
			return ret;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Returns the obstetric visits for the currently selected patient
	 * @return List of obstetric visits
	 */
	public List<ObstetricsOfficeVisit> getOfficeVisitsForCurrentPatient() {
		return getObstetricsVisitsForPatient(sessionUtils.getCurrentPatientMIDLong());
	}
	
	/**
	 * Returns an obstetric visit based on the visit id
	 * 
	 * @param visitID The obstetrics visit ID
	 * @return The obstetrics visit with the id
	 */
	public ObstetricsOfficeVisit getVisitByID(String visitID) {
		ObstetricsOfficeVisit ov = new ObstetricsOfficeVisit();
		try {
			ov = obstetricsVisitData.getByID( Long.parseLong( visitID ) );
			return ov;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ov;
	}
	
	/**
	 * @return Returns the obstetrics visit of the selected patient
	 */
	public ObstetricsOfficeVisit getSelectedVisit() {
		String visitID = sessionUtils.getRequestParameter("visitID");
		if(visitID == null || visitID.isEmpty()) {
			return null;
		}
		return getVisitByID(visitID);
	}
	
	/**
	 * Checks if the patient has any office visits
	 * @param mid
	 * @return
	 */
	public boolean hasVisits(String mid) {
		boolean ret = false;
		if((mid != null) ) {
			if(getObstetricsVisitsForPatient( Long.parseLong( mid ) ) != null && getObstetricsVisitsForPatient( Long.parseLong( mid ) ).size() > 0) {
				ret = true;
			}
		}
		return ret;
	}
	
	public boolean currentPatientHasVisits() {
		return hasVisits(sessionUtils.getCurrentPatientMID());
	}
	
	/**
	 * Edits the Obstetrics visit
	 * @param ov The obstetrics visit to edit
	 */
	public void edit( ObstetricsOfficeVisit ov ) {
		try {
			obstetricsVisitData.update( ov );
			logTransaction(TransactionType.EDIT_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));

		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	public List<Fetus> getFeti( long visitID ) {
		List<Fetus> ret = Collections.emptyList();
		try {
			ret = obstetricsVisitData.getFetiForOfficeVisit( visitID );
			return ret;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void addFetus( Fetus f ) {
		try {
			obstetricsVisitData.addFetus( f );
		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	public void addUltrasound( Ultrasound us ) {
		try {
			obstetricsVisitData.addUltrasound( us );
			logTransaction( TransactionType.ULTRASOUND, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( us.getId() ) );

		} catch ( DBException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the current logged in user is an OBGYN
	 * @return
	 */
	public boolean isOBGYN() {
		boolean eligible = false;
		try {
			eligible = personnelDAO.getPersonnel(sessionUtils.getSessionLoggedInMIDLong()).getSpecialty().equals("OB/GYN");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eligible;
	}
	
	/**
	 * Checks if the selected patient is a valid obstetrics patient and has an initialization record
	 * @return
	 */
	public boolean isValidObstetricsPatient() {
		boolean obstetricEligible = false;
		Long pid = sessionUtils.getCurrentPatientMIDLong();
		if(pid != null) {
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
		boolean rhWeeksRequired = true;
		boolean rhFlag = false;
		Long pid = sessionUtils.getCurrentPatientMIDLong();
		List<ObstetricsOfficeVisit> allDates;
		if(pid != null) {
			try {
				allDates = obstetricsVisitData.getOfficeVistsForPatient(pid);
				rhFlag = sql.getCurrentObstetricsPregnancy(pid).getrhFlag();
				rhFlag = true;
				if(allDates != null) {
					ObstetricsOfficeVisit mostCurrentDate = allDates.get(allDates.size() - 1);
					String weeksPreg = mostCurrentDate.getWeeksPregnant();
					if(weeksPreg.equals("") || weeksPreg.equals(null)) {
						rhWeeksRequired = false;
					} else {
						int weeksPrego = Integer.parseInt(weeksPreg);
						if(weeksPrego >= 28) {
							rhWeeksRequired = true;
						} else {
							rhWeeksRequired = false;
						}
					}
				} else {
					rhWeeksRequired = false;
				}
				
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		boolean needRH = rhWeeksRequired && rhFlag;
		
		return needRH;
	}
	
	public void logViewObstetricsVisit(Long visitID) {
		logTransaction(TransactionType.VIEW_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString(visitID));
	}
	

	public List<Ultrasound> getUltrasounds( Long visitID ) {
		List<Ultrasound> ret = null;
		try {
			ret = obstetricsVisitData.getUltrasoundByOfficeVisitId( visitID );
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
