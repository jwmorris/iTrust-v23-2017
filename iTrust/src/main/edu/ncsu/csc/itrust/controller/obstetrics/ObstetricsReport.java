package edu.ncsu.csc.itrust.controller.obstetrics;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;

@ViewScoped
@ManagedBean( name="obstetrics_report" )
public class ObstetricsReport extends iTrustController {
	
	
	private List<ObstetricsPregnancy> priors;
	private List<ObstetricsOfficeVisit> officeVisits;
	private String edd;
	private List<AllergyBean> allergies;
	private List<Diagnosis> diagnoses;
	private ObstetricsReportController obc;
	private ObstetricsPregnancy selected;
	private String bloodType;
	
	
	public ObstetricsReport () {
		this.obc = new ObstetricsReportController();
		this.selected = obc.getSelectedVisit();
		this.priors = obc.getPriorPregnancies();
		this.officeVisits = obc.getOfficeVisitsForVisit( selected.getId() );
		this.allergies = obc.getRelevantAllergies();
		this.diagnoses = obc.getRelevantDiagnoses();
		this.bloodType = obc.getCurrentPatientBloodType();
		this.edd = selected.getEdd();
	}


	/**
	 * @return the priors
	 */
	public List<ObstetricsPregnancy> getPriors() { return priors; }


	/**
	 * @return the officeVisits
	 */
	public List<ObstetricsOfficeVisit> getOfficeVisits() { return officeVisits; }


	/**
	 * @return the edd
	 */
	public String getEdd() { return edd; }


	/**
	 * @return the allergies
	 */
	public List<AllergyBean> getAllergies() { return allergies; }


	/**
	 * @return the diagnoses
	 */
	public List<Diagnosis> getDiagnoses() { return diagnoses; }


	/**
	 * @return the obc
	 */
	public ObstetricsReportController getObc() { return obc; }


	/**
	 * @return the selected
	 */
	public ObstetricsPregnancy getSelected() { return selected; }


	/**
	 * @return the bloodType
	 */
	public String getBloodType() { return bloodType; }
	
	public boolean hasAllergies() {
		return allergies != null && allergies.size() != 0;
	}
	
	public boolean hasDiagnoses() {
		return diagnoses != null && diagnoses.size() != 0;
	}
	
	public boolean hasOfficeVisits() {
		return officeVisits != null && diagnoses.size() != 0;
	}
	
	public boolean hasPriorPregnancies() {
		return priors != null && priors.size() != 0;
	}
}
