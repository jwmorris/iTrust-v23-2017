/**
 * 
 */
package edu.ncsu.csc.itrust.controller.obstetrics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.controller.emergencyRecord.EmergencyRecordController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.emergencyRecord.EmergencyRecord;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.enums.BloodType;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * @author daseybol
 *
 */
@SessionScoped
@ManagedBean( name="report_controller" )
public class ObstetricsReportController extends iTrustController {
	private SessionUtils sessionUtils;
	private PatientDAO patientDAO;
	private DAOFactory factory;
	private ObstetricsPregnancyData obstretricsData;
	private ObstetricsOfficeVisitData officeData;
	private EmergencyRecordController erCon;
	
	
	public ObstetricsReportController() {
		this.sessionUtils = SessionUtils.getInstance();
		factory = DAOFactory.getProductionInstance();
		this.patientDAO = factory.getPatientDAO();
		try {
			erCon = new EmergencyRecordController();
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.obstretricsData = new ObstetricsMySQL();
			this.officeData = new ObstetricsOfficeVisitMySQL();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Returns whether the patient has prior pregnancies
	 * @return
	 */
	public boolean hasPriorPregnancies() {
		try {
			return obstretricsData.getPastObstetricsPregnanciesForPatient( sessionUtils.getCurrentPatientMIDLong() ) != null;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @return Returns the pregnancy of the selected patient
	 */
	public ObstetricsPregnancy getSelectedVisit() {
		String visitID = sessionUtils.getRequestParameter("id");
		if(visitID == null || visitID.isEmpty()) {
			return null;
		}
		try {
			return obstretricsData.getByID( Long.parseLong( visitID ) );
		} catch (NumberFormatException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Returns whether the patient has a current pregnancy
	 * @return
	 */
	public boolean hasCurrentPregnancy() {
		ObstetricsPregnancy current = null;;
		try {
			current = obstretricsData.getCurrentObstetricsPregnancy( sessionUtils.getCurrentPatientMIDLong() );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( current != null ) {
			return current.getId() != 0;
		}
		return false;
	}
	
	public String getCurrentPatientBloodType() {
		PatientBean pb = null;
		long pid = sessionUtils.getCurrentPatientMIDLong();
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
	
	
	public List<AllergyBean> getRelevantAllergies() {
		EmergencyRecord record = erCon.loadRecord( sessionUtils.getCurrentPatientMID() );
		List<AllergyBean> ret = new ArrayList<AllergyBean>();
		List<AllergyBean> allAllergies = record.getAllergies();
		for( AllergyBean a : allAllergies ) {
			String aName = a.getDescription();
			if( aName.equals( "Penicillin" ) 
					|| aName.equals( "Bactrim" ) 
					|| aName.equals( "Tetracycline" ) 
					|| aName.equals( "Codeine" ) 
					|| aName.equals( "Ibuprofen" ) ) {
				ret.add( a );
			}
		}
		return ret;
	}
	
	public List<Diagnosis> getRelevantDiagnoses() {
		EmergencyRecord record = erCon.loadRecord( sessionUtils.getCurrentPatientMID() );
		List<Diagnosis> ret = new ArrayList<Diagnosis>();
		List<Diagnosis> allDiagnosis = record.getDiagnoses();
		for( Diagnosis d : allDiagnosis ) {
			if( d.getCode().startsWith( "E" ) 
				|| d.getCode().startsWith( "O" ) 
				|| d.getCode().startsWith( "C" ) 
				|| d.getCode().startsWith( "A5" ) 
				|| d.getCode().startsWith( "A60" )
				|| d.getCode().startsWith( "A63" )
				|| d.getCode().startsWith( "A64" )
				|| d.getCode().startsWith( "A70" )
				|| d.getCode().startsWith( "A74" )
				|| d.getCode().startsWith( "D" )
				|| d.getIcdCode().isChronic()) {
				boolean notAdded = true;
				for(int j = 0; j < ret.size(); j++) {
					if(ret.get(j).getCode().equals(d.getCode())) {
						notAdded = false;
						break;
					}
				}
				if(notAdded) {
					ret.add(d);
				}
			}
		}
		return ret;
	}
	
	public List<ObstetricsPregnancy> getPriorPregnancies() {
		if ( sessionUtils.getCurrentPatientMIDLong() != null ) {
			try {
				return obstretricsData.getPastObstetricsPregnanciesForPatient( sessionUtils.getCurrentPatientMIDLong() );
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	public List<ObstetricsOfficeVisit> getOfficeVisitsForVisit(long id) {
		try {
			return officeData.getOfficeVisitsByPidAndInitId( sessionUtils.getCurrentPatientMIDLong(), id );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ObstetricsPregnancy getCurrentPregnancy() {
		if( sessionUtils.getCurrentPatientMIDLong() != null ) {
			try {
				return obstretricsData.getCurrentObstetricsPregnancy( sessionUtils.getCurrentPatientMIDLong() );
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public String getDOB() {
		PatientBean p = null;
		try {
			p = patientDAO.getPatient( sessionUtils.getCurrentPatientMIDLong() );
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getDateOfBirthStr();
	}
}
