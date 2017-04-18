package edu.ncsu.csc.itrust.controller.obstetrics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
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
/**
 * We based some of the code for this class on the OfficeVisitController.xhtml
 * @author David
 *
 */
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
	public ObstetricsPregnancyData sql;
	private long currentPregnancyID;
	
	/**
	 * Constructor used in application
	 */
	public ObstetricsVisitController() throws DBException {
		
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		this.obstetricsVisitData = new ObstetricsOfficeVisitMySQL();
		sql = new ObstetricsMySQL();
	}

	/**
	 * Constructor used in testing
	 * @param ds
	 */
	public ObstetricsVisitController(DataSource ds, DAOFactory factory, SessionUtils utils) {
		super(utils, null, factory);
		this.sessionUtils = utils;
		this.obstetricsVisitData = new ObstetricsOfficeVisitMySQL(ds);
		this.factory = factory;
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		try {
			sql = new ObstetricsMySQL(ds);
		} catch (DBException e) {
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
			ov.setInitID( sql.getCurrentObstetricsPregnancy( ov.getPid() ).getId() );
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
			ov.setInitID( sql.getCurrentObstetricsPregnancy( ov.getPid() ).getId() );
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
	
	public List<ObstetricsOfficeVisit> getOfficeVisitsForInitId( long pid, long id ) {
		List<ObstetricsOfficeVisit> visits = null;
		try {
			visits = obstetricsVisitData.getOfficeVisitsByPidAndInitId( pid, id );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return visits;
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
				ObstetricsPregnancy op = sql.getCurrentObstetricsPregnancy( pid );
				if( op.getId() != 0 ) {
					obstetricEligible = true;
				}
				
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
				if(allDates != null) {
					ObstetricsOfficeVisit mostCurrentDate = allDates.get(allDates.size() - 1);
					String weeksPreg = mostCurrentDate.getWeeksPregnant();
					if(weeksPreg.equals("") || weeksPreg.equals(null)) {
						rhWeeksRequired = false;
					} else {
						int weeksPrego = (int)Double.parseDouble(weeksPreg);
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
	
	public Fetus getFetusById( long visitID, int fetusID ) {
		Fetus ret = null;
		try {
			ret = obstetricsVisitData.getFetus( visitID, fetusID );
		} catch ( DBException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void edit( Fetus f ) {
		try {
			obstetricsVisitData.updateFetus( f );
		} catch ( DBException e ) {
			e.printStackTrace();
		} catch (FormValidationException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	public void deleteUltrasound( long visitID, String ultrasoundName ) {
		try {
			obstetricsVisitData.deleteUltrasound( visitID, ultrasoundName );
		} catch ( DBException e ) { 
			e.printStackTrace();
		}
	}

	public void download(String ultrasound, long ovID) {
		//used code from http://www.codejava.net/java-ee/servlet/java-servlet-to-download-file-from-database
		//used code from http://stackoverflow.com/questions/9391838/how-to-provide-a-file-download-from-a-jsf-backing-bean
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		int size = 0;
		String name = "";
		InputStream is = null;
		try {
			Ultrasound us = obstetricsVisitData.getUltrasoundByPicPath(ovID, ultrasound);
			is = us.getImg();
			size = is.available();
			name = us.getPicPath();
			
			
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ec.responseReset();
		int dotIndex = name.lastIndexOf( '.' );

		String type = name.substring( dotIndex );
		String mimeType = ec.getMimeType( name );
		if( type.equalsIgnoreCase( ".pdf" ) ) {
			mimeType = "application/pdf";
		} else if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }
		ec.setResponseContentType( mimeType );
		ec.setResponseContentLength( size );
		ec.setResponseHeader( "Content-Disposition", "attachment; filename=\"" + name + "\"" );
	
		try {
			OutputStream os = ec.getResponseOutputStream();
			byte[] buffer = new byte[ 4096 ];
            int bytesRead = -1;
             
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
             
            is.close();
            os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fc.responseComplete();
	}

	public String getLmp(long pid){
		String lmp = "";
		try {
			lmp = sql.getCurrentObstetricsPregnancy(pid).getLmp();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lmp;
	}
	
	public void logScheduleNextVisit(long patientMID, long currentID, int nextID) {
		logTransaction(TransactionType.SCHEDULE_NEXT_OFFICE_VISIT, sessionUtils.getSessionLoggedInMIDLong(), patientMID, String.valueOf(currentID) + "," + String.valueOf(nextID));
	}
	
	public void logChildbirthVisit(long patientMID, long currentID, int nextID) {
		logTransaction(TransactionType.SCHEDULE_CHILDBIRTH, sessionUtils.getSessionLoggedInMIDLong(), patientMID, String.valueOf(currentID) + "," + String.valueOf(nextID));
	}
}
