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
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ChildbirthMySQL;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
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
	
	/**
	 * Constructor for controller in application
	 */
	public ChildbirthVisitController(){
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		personnelDAO = factory.getPersonnelDAO();
		try {
			this.childbirthSQL = new ChildbirthMySQL();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			childbirthSQL = new ChildbirthMySQL();
		} catch (DBException e) {
			e.printStackTrace();
			
		}
		erBirth = false;
		System.out.println( "Creating new controller" );
	}
	
	/**
	 * Test constructor
	 */
	public ChildbirthVisitController(DataSource ds, DAOFactory factory, SessionUtils utils){
		
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
	public List<Childbirth> getChildbirthsForPatient(Long pid) {
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
		} catch ( DBException e ) {
			e.printStackTrace();
		} catch ( FormValidationException e ) {
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
		System.out.println( "Get: " + erBirth );
		return erBirth;
	}

	/**
	 * @param erBirth the erBirth to set
	 */
	public void setErBirth(boolean erBirth) {
		System.out.println( "Set: " + erBirth );
		this.erBirth = erBirth;
	}

	public void logViewChildbirth(Long childbirthID) {
//		log Transaction(TransactionType.VIEW_OBSTETRIC_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString(visitID));
	}
}
