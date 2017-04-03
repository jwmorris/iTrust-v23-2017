package edu.ncsu.csc.itrust.controller.obstetrics;

import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.action.AddPatientAction;
import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.ITrustException;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.model.old.enums.Gender;
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * We based some of the code for this class on the OfficeVisitController.xhtml
 * @author David
 *
 */
@ManagedBean(name = "childbirth_visit_controller")
@SessionScoped
public class ChildbirthVisitController extends iTrustController {
	
	private PersonnelDAO personnelDAO;
	private DAOFactory factory;
	private ChildbirthData childbirthSQL;
	private SessionUtils sessionUtils;
	private boolean erBirth;
	private PatientDAO patientDAO;
	
	/**
	 * Constructor for controller in application
	 */
	public ChildbirthVisitController(){
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		personnelDAO = factory.getPersonnelDAO();
		this.patientDAO = factory.getPatientDAO();
		try {
			this.childbirthSQL = new ChildbirthMySQL();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		erBirth = false;
	}
	
	/**
	 * Test constructor
	 */
	public ChildbirthVisitController(DataSource ds, DAOFactory factory, SessionUtils utils){
		this.sessionUtils = utils;
		this.factory = factory;
		this.personnelDAO = factory.getPersonnelDAO();
		this.childbirthSQL = new ChildbirthMySQL( ds );
		erBirth = false;
	}
	
	/**
	 * Adds the Childbirth visit to the database and returns the generated ID
	 * @param ov A childbirth visit to add
	 * @return The generated id
	 */
	public long addReturnGeneratedId( Childbirth cb ) {
		long ret = 0;
		try {
			ret = childbirthSQL.addReturnsGeneratedId( cb );
			cb.setChildbirthId( ret );
//			logTransaction(TransactionType.CREATE_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));
		} catch ( DBException e ) {

			e.printStackTrace();
		} catch ( FormValidationException e ) {

			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
			System.out.println( e.getMessage() );
		}
		return ret;
	}
	
	/**
	 * Adds the Childbirth visit to the database
	 * @param Childbirth visit to add
	 */
	public void addChildbirth(Childbirth cb) {
		try {
			childbirthSQL.add( cb );
//			logTransaction(TransactionType.CREATE_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));

		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	/**
	 * Edits the Childbirth visit
	 * @param ov The childbirth visit to edit
	 */
	public void editChildbirth( Childbirth cb ) {
		try {
			childbirthSQL.update( cb );
//			logTransaction(TransactionType.EDIT_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( ov.getId() ));

		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	/**
	 * @return Returns the childbirth visit of the selected patient
	 */
	public Childbirth getSelectedVisit() {
		String childbirthID = sessionUtils.getRequestParameter("childbirthID");
		if(childbirthID == null || childbirthID.isEmpty()) {
			return null;
		}
		return getChildbirthByID(childbirthID);
	}
	
	/**
	 * Returns a childbirth visit based on the visit id
	 * 
	 * @param visitID The childbrith visit ID
	 * @return The childbirth visit with the id
	 */
	public Childbirth getChildbirthByID(String childbirthID) {
		Childbirth cb = new Childbirth();
		try {
			cb = childbirthSQL.getByID( Long.parseLong( childbirthID ) );
			return cb;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return cb;
	}
	
	/**
	 * Returns a list of childbirth visits for the specified patient
	 * @param pid Patient's mid
	 * @return List of obstetric visits for the patient
	 */
	public List<Childbirth> getChildbirthsForPatient(long pid) {
		List<Childbirth> ret = Collections.emptyList();
		try {
			ret = childbirthSQL.getChildbirthsForPatient( pid );
			return ret;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Returns a list of childbirth visits for the specified patient
	 * @param pid Patient's mid
	 * @return List of obstetric visits for the patient
	 */
	public List<Childbirth> getChildbirthsForCurrentPatient() {
		List<Childbirth> ret = Collections.emptyList();
		try {
			ret = childbirthSQL.getChildbirthsForPatient( sessionUtils.getCurrentPatientMIDLong() );
			return ret;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean currentPatientHasVisits() {
		if ( sessionUtils.getCurrentPatientMIDLong() != null ) {
			if ( getChildbirthsForCurrentPatient() == null )
				return false;
			int s = getChildbirthsForCurrentPatient().size();
			if ( s > 0 )
				return true;
		}
		
		
		return false;
	}
	
	/**
	 * returns a list of babies based on childbirth ID
	 * @return
	 */
	public List<Baby> getBabies( long childbirthID ) {
		List<Baby> ret = Collections.emptyList();
		try {
			ret = childbirthSQL.getBabiesForChildbirth( childbirthID );
			return ret;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Add a baby to database
	 */
	public void addBaby( Baby b ) {
		try {
			childbirthSQL.addBaby(b);
			AddPatientAction addPatientAction = new AddPatientAction( DAOFactory.getProductionInstance(), sessionUtils.getCurrentPatientMIDLong() );
			PatientBean parent = patientDAO.getPatient( sessionUtils.getCurrentPatientMIDLong() );
			PatientBean pb = new PatientBean();
			pb.setEmail( parent.getEmail() );
			pb.setFirstName( "Baby" );
			pb.setLastName( parent.getLastName() );
			pb.setDateOfBirthStr( b.getDate() );
			pb.setMotherMID( sessionUtils.getCurrentPatientMID() );
			pb.setStreetAddress1( parent.getStreetAddress1() );
			pb.setStreetAddress2( parent.getStreetAddress2() );
			pb.setCity( parent.getCity() );
			pb.setState( parent.getState() );
			pb.setZip( parent.getZip() );
			if ( Character.toString( b.getSex() ).equals( "m" ) ) {
				pb.setGender( Gender.Male );
			} else {
				pb.setGender( Gender.Female );
			}
			addPatientAction.addDependentPatient(pb, sessionUtils.getCurrentPatientMIDLong(), sessionUtils.getSessionLoggedInMIDLong() );
		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		} catch (ITrustException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	/**
	 * edits a baby in the database
	 * @param b
	 */
	public void editBaby( Baby b ) {
		try {
			childbirthSQL.updateBaby(b);
		} catch ( DBException e ) {
			e.printStackTrace();
		} catch (FormValidationException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	/**
	 * gets a baby based on childbirth id and multiNum
	 * @param childbirthID
	 * @param multiNum
	 * @return
	 */
	public Baby getBaby(long childbirthID, int babyId){
		Baby b = new Baby();
		try {
			b = childbirthSQL.getBaby( childbirthID, babyId );
			return b;
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return b;
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
	 * @return the erBirth
	 */
	public boolean isErBirth() {
		return erBirth;
	}

	/**
	 * @param erBirth the erBirth to set
	 */
	public void setErBirth(boolean erBirth) {
		this.erBirth = erBirth;
	}

	public void logViewChildbirth(Long childbirthID) {
//		log Transaction(TransactionType.VIEW_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString(visitID));
	}
}
