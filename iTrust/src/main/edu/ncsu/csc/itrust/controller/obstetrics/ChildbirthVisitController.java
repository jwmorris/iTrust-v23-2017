package edu.ncsu.csc.itrust.controller.obstetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.action.AddPatientAction;
import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.ITrustException;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
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
	private ObstetricsPregnancyData sql;
	private ObstetricsOfficeVisitData ovSQL;
	private String hoursLabor;
	private String yearConception;
	
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
		try {
			this.sql = new ObstetricsMySQL();
			this.ovSQL = new ObstetricsOfficeVisitMySQL();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		erBirth = false;
		hoursLabor = "";
		yearConception = "";
	}
	
	/**
	 * Test constructor
	 */
	public ChildbirthVisitController(DataSource ds, DAOFactory factory, SessionUtils utils){
		this.sessionUtils = utils;
		this.factory = factory;
		this.personnelDAO = factory.getPersonnelDAO();
		this.patientDAO = factory.getPatientDAO();
		this.childbirthSQL = new ChildbirthMySQL( ds );
		erBirth = false;
		try {
			sql = new ObstetricsMySQL( ds );
			this.ovSQL = new ObstetricsOfficeVisitMySQL(ds);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hoursLabor = "";
		yearConception = "";
	}
	
	/**
	 * Adds the Childbirth visit to the database and returns the generated ID
	 * @param ov A childbirth visit to add
	 * @return The generated id
	 */
	public long addReturnGeneratedId( Childbirth cb ) {
		long ret = 0;

		ObstetricsPregnancy op = null;
		try {
			op = sql.getCurrentObstetricsPregnancy( sessionUtils.getCurrentPatientMIDLong() );
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			cb.setInitializationId( op.getId() );
			ret = childbirthSQL.addReturnsGeneratedId( cb );
			cb.setChildbirthId( ret );
			logDrugs( cb );
			logTransaction( TransactionType.CREATE_CHILDBIRTH_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), "" );
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
		
		ObstetricsPregnancy op = null;
		try {
			op = sql.getCurrentObstetricsPregnancy( sessionUtils.getCurrentPatientMIDLong() );
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			cb.setInitializationId( op.getId() );
			childbirthSQL.add( cb );
			logDrugs( cb );
			logTransaction( TransactionType.CREATE_CHILDBIRTH_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), "" );
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
			logTransaction( TransactionType.EDIT_CHILDBIRTH_VISIT, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), "" );
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
			logTransaction( TransactionType.A_BABY_IS_BORN, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), "" );
			AddPatientAction addPatientAction = new AddPatientAction( DAOFactory.getProductionInstance(), sessionUtils.getCurrentPatientMIDLong() );
			PatientBean parent = patientDAO.getPatient( sessionUtils.getCurrentPatientMIDLong() );
			PatientBean pb = new PatientBean();
			pb.setEmail( parent.getEmail() );
			if (b.getName().equals("") || b.getName().equals(null)) {
				pb.setFirstName( "Baby" );
			} else {
				pb.setFirstName( b.getName() );
			}
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
			long pid = addPatientAction.addDependentPatient(pb, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong() );
			logTransaction( TransactionType.CREATE_BABY_RECORD, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), Long.toString( pid ) );
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
	
	public boolean canAddChildVisit() {
		if( sessionUtils.getCurrentPatientMID() == null ) {
			return false;
		}
		ObstetricsPregnancyData sql = null;
		try {
			sql = new ObstetricsMySQL();
		} catch (DBException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObstetricsPregnancy op = null;
		try {
			op = sql.getCurrentObstetricsPregnancy( sessionUtils.getCurrentPatientMIDLong() );
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if( op.getId() == 0 ) {
			return false;
		}
		Childbirth cbv = null;
		System.out.println( "ID:" + op.getId() );
		try {
			cbv = childbirthSQL.getChildbirthVisitForInitId( sessionUtils.getCurrentPatientMIDLong(), op.getId() );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cbv == null;
		
		
	}
	
	private void logDrugs( Childbirth cb ) {
		if( !cb.getAmtEpidural().equals( "0" ) 
				|| !cb.getAmtMagnesium().equals( "0" )
				|| !cb.getAmtNitrous().equals( "0" ) 
				|| !cb.getAmtPethidine().equals( "0" )
				|| !cb.getAmtPitocin().equals( "0" )
				|| !cb.getAmtRH().equals( "0" ) ) {
			logTransaction( TransactionType.ADD_CHILDBIRTH_DRUGS, sessionUtils.getSessionLoggedInMIDLong(), sessionUtils.getCurrentPatientMIDLong(), "" );
		}
	}
	
	public String getHoursLabor() {
		return hoursLabor;
	}

	public void setHoursLabor(String hoursLabor) {
		this.hoursLabor = hoursLabor;
	}

	public String getYearConception() {
		return yearConception;
	}

	public void setYearConception(String yearConception) {
		this.yearConception = yearConception;
	}
	
	public void finishPregnancy() {
		try {
			if (Double.parseDouble(hoursLabor) < 0) {
				throw new NumberFormatException();
			}
		} catch(NumberFormatException e) {
			//TODO: Print Error
			printFacesMessage( FacesMessage.SEVERITY_INFO, "Invalid Hours Labor", "Invalid Hours Labor: should be a non-zero number", null );
			System.out.println("hourLabor EXCEPTION");
			return;
		}
		
		try {
			if (yearConception.length() != 4 || Integer.parseInt(yearConception) < 0 || String.valueOf(Integer.parseInt(yearConception)).length() != 4) {
				throw new NumberFormatException();				
			}
		} catch(NumberFormatException e) {
			//TODO: Print Error 
			printFacesMessage( FacesMessage.SEVERITY_INFO, "Invalid Conception Year", "Invalid Conception Year: should be a non-zero four-digit number", null );
			System.out.println("yearConception EXCEPTION");
			return;
		}

		ObstetricsPregnancy current = null;
		try {
			current = sql.getCurrentObstetricsPregnancy(sessionUtils.getCurrentPatientMIDLong());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			printFacesMessage( FacesMessage.SEVERITY_INFO, "No Current Pregnancy", "No Current Pregnancy: first create a current pregnancy", null );
			e.printStackTrace();
			return;
		}
		if (current == null) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, "No Current Pregnancy", "No Current Pregnancy: first create a current pregnancy", null );
			return;
		}
		
		current.setConcepYear(yearConception);
		current.setHoursLabor(hoursLabor);
		
		Childbirth childVisit = null;
		try {
			childVisit = childbirthSQL.getChildbirthVisitForInitId(sessionUtils.getCurrentPatientMIDLong(), current.getId());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			printFacesMessage( FacesMessage.SEVERITY_INFO, "No Childbirth Visit", "No Childbirth Visit: first create a childbirth visit", null );
			e.printStackTrace();
			return;
		}
		
		if (childVisit == null) {
			//TODO: print Error
			printFacesMessage( FacesMessage.SEVERITY_INFO, "No Childbirth Visit", "No Childbirth Visit: first create a childbirth visit", null );
			System.out.println("childVisit == null");
			return;
		}
		
		List<Baby> currentBabies = getBabies(childVisit.getChildbirthId());
		int babyCount = 0;
		if (currentBabies != null) {
			babyCount = currentBabies.size();
		}
		current.setBabyCount(String.valueOf(babyCount));
		current.setMultiplePregnancy(babyCount > 1);
		current.setDeliveryType(childVisit.getDeliveryType());
		current.setCurrent(false);
		current.setTotalWeeksPregnant(calculateWeeksPreg(current.getLmp(), childVisit.getDate()));
		current.setWeightGain(calculateWeightGain(current.getId()));
		try {
			sql.update(current);
		} catch (DBException | FormValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hoursLabor = "";
		yearConception = "";
		
	}
	
	private String calculateWeeksPreg( String lmp, Date visitDate) {
		Date lmpDate = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
		try {
			lmpDate = DATE_FORMAT.parse( lmp );
		} catch( ParseException e ) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( lmpDate );
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime( visitDate );
		
		int initDays = (int)(cal2.getTime().getTime() / (1000 * 60 * 60 * 24));
		int lmpDays = (int)(cal.getTime().getTime() / (1000 * 60 * 60 * 24));
		int totalDays = Math.abs(initDays - lmpDays );
		int weeks = totalDays / 7;
		int days = totalDays - (weeks * 7);
		StringBuilder sb = new StringBuilder();
		sb.append( Integer.toString( weeks ) );
		sb.append( "." );
		sb.append( Integer.toString( days ) );
		return sb.toString();

	}
	
	private String calculateWeightGain(Long currentPregnancy) {
		List<ObstetricsOfficeVisit> visits = null;
		try {
			visits = ovSQL.getOfficeVisitsByPidAndInitId(sessionUtils.getCurrentPatientMIDLong(), currentPregnancy);
		} catch (DBException e) {
			//printFacesMessage( FacesMessage.SEVERITY_INFO, "No Obstetrics Visit", "No Obstetrics Visit: first create a Obstetric visit", null );
			return "";
		}
		
		if (visits == null || visits.size() == 0) {
			//printFacesMessage( FacesMessage.SEVERITY_INFO, "No Obstetrics Visit", "No Obstetrics Visit: first create a Obstetric visit", null );
			return "";
		}
		
		String strInitialWeight = "";
		for (int i = 0; i < visits.size(); i++) {
			if (!visits.get(i).getWeight().equals("")) {
				strInitialWeight = visits.get(i).getWeight();
				break;
			}
		}
		
		String strCurrentWeight = "";
		for (int i = visits.size() - 1; i >= 0; i--) {
			if (!visits.get(i).getWeight().equals("")) {
				strCurrentWeight = visits.get(i).getWeight();
				break;
			}
		}
		
		if (strInitialWeight.equals("") || strCurrentWeight.equals("")) {
			return "";
		}
		
		double initialWeight;
		double currentWeight;
		try {
			initialWeight = Double.parseDouble(strInitialWeight);
			currentWeight = Double.parseDouble(strCurrentWeight);
		} catch (NumberFormatException e) {
			return "";
		}
		double weightGain = currentWeight - initialWeight;
		
		System.out.println(String.valueOf(weightGain));
		return String.valueOf(weightGain);
	}
}
