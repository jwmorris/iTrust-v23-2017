package edu.ncsu.csc.itrust.controller.obstetrics;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.controller.emergencyRecord.EmergencyRecordController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.enums.BloodType;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ViewScoped
@ManagedBean( name="obstetrics_report" )
public class ObstetricsReport extends iTrustController {
	
	private SessionUtils sessionUtils;
	private PatientDAO patientDAO;
	private DAOFactory factory;
	private String bloodType;
	private ObstetricsController oc;
	private ObstetricsVisitController ovc;
	private EmergencyRecordController erCon;
	private long pid;
	private ObstetricsPregnancy current;
	private List<ObstetricsPregnancy> priors;
	private List<ObstetricsOfficeVisit> officeVisits;
	private String edd;
	private List<AllergyBean> allergies;
	private List<Diagnosis> diagnoses;
	private EmergencyRecord record;
	
	public ObstetricsReport () {
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		this.patientDAO = factory.getPatientDAO();
		this.oc = new ObstetricsController();
		this.ovc = new ObstetricsVisitController();
		try {
			this.erCon = new EmergencyRecordController();
		} catch (DBException e1) {
			e1.printStackTrace();
		}
		try {
			this.pid = sessionUtils.getCurrentPatientMIDLong();
			this.bloodType = getCurrentPatientBloodType();
			current = oc.getCurrentPregnancy();
			priors = oc.getPriorPregnancies();
			officeVisits = ovc.getOfficeVisitsForCurrentPatient();
			allergies = getRelevantAllergies();
			diagnoses = getRelevantDiagnoses();
			edd = current.getEdd();
			if( current != null ) {
				edd = current.getEdd();
			}
			record = erCon.loadRecord( Long.toString( pid ) );
		} catch ( NullPointerException e ){ 
			this.pid = 0;
		}
		
	}
	
	private List<AllergyBean> getRelevantAllergies() {
		List<AllergyBean> ret = new ArrayList<AllergyBean>();
		List<AllergyBean> allAllergies = record.getAllergies();
		for( AllergyBean a : allAllergies ) {
			String aName = a.getDescription();
			if( aName.equals( "Penicillin" ) 
					|| aName.equals( "Sulfa Drugs" ) 
					|| aName.equals( "Tetracycline" ) 
					|| aName.equals( "Codeine" ) 
					|| aName.equals( "NSAIDs" ) ) {
				ret.add( a );
			}
		}
		return ret;
	}
	
	private List<Diagnosis> getRelevantDiagnoses() {
		List<Diagnosis> ret = new ArrayList<Diagnosis>();
		List<Diagnosis> allDiagnosis = record.getDiagnoses();
		for( Diagnosis d : allDiagnosis ) {
			d.getIcdCode();
		}
		return ret;
	}
	

	private String getCurrentPatientBloodType() {
		PatientBean pb = null;
		if( pid != 0 ) {
			try {
				pb = patientDAO.getPatient( pid );
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BloodType type = pb.getBloodType();
			return type.getName();
		}
		return "";
		
	}
	
	
	/**
	 * @return the bloodType
	 */
	public String getBloodType() {
		return bloodType;
	}



	/**
	 * @return the current
	 */
	public ObstetricsPregnancy getCurrent() {
		return current;
	}



	/**
	 * @return the priors
	 */
	public List<ObstetricsPregnancy> getPriors() {
		return priors;
	}



	/**
	 * @return the officeVisits
	 */
	public List<ObstetricsOfficeVisit> getOfficeVisits() {
		return officeVisits;
	}



	/**
	 * @return the edd
	 */
	public String getEdd() {
		return edd;
	}



	/**
	 * @return the allergies
	 */
	public List<AllergyBean> getAllergies() {
		return allergies;
	}



	/**
	 * @return the diagnoses
	 */
	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	/**
	 * Returns whether the patient has office visits
	 * @return
	 */

	public boolean hasOfficeVisits() {
		return officeVisits != null;
	}
	
	/**
	 * Returns whether the patient has a current pregnancy
	 * @return
	 */
	public boolean hasCurrentPregnancy() {
		if( current != null ) {
			return current.getId() != 0;
		}
		return false;
	}
	
	/**
	 * Returns whether the patient has prior pregnancies
	 * @return
	 */
	public boolean hasPriorPregnancies() {
		return priors != null;
	}
	
	public boolean hasAllergies() {
		return allergies != null && allergies.size() != 0;
	}
	
	public boolean hasDiagnoses() {
		return diagnoses != null && diagnoses.size() != 0;
	}
	
	
}
