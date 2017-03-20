package edu.ncsu.csc.itrust.controller.obstetrics;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.NavigationController;
import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "obstetrics_controller")
@ViewScoped
public class ObstetricsController extends iTrustController {
	
	private ObstetricsPregnancy currentPregnancy;
	private List<ObstetricsPregnancy> priorPregnancies;
	private ObstetricsPregnancy newPregnancy = new ObstetricsPregnancy();
	private ObstetricsPregnancy priorPregnancy = new ObstetricsPregnancy();
	private ObstetricsPregnancyData sql;
	private SessionUtils utils;
	private Long pid;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	
	public ObstetricsController () {
		try {
			sql = new ObstetricsMySQL();
		} catch (DBException e) {
			System.out.println("DB fail");
			e.printStackTrace();
		}
		this.utils = SessionUtils.getInstance();
		pid = utils.getCurrentPatientMIDLong();
		DAOFactory factory = DAOFactory.getProductionInstance();
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		priorPregnancies = getPriorPregnancies();
		currentPregnancy = getCurrentPregnancy();
		
	}
	
	public ObstetricsPregnancy makeNewPregnancy(){
		return newPregnancy;
	}
	public boolean checkPatientEligibility() {
		boolean eligible = false;
		if(pid != null) {
			try {
				eligible = patientDAO.getPatient(pid).isObstetricsPatient();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return eligible;
	}
	
	public List<ObstetricsPregnancy> getPriorPregnancies() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		if(pid != null){
			try {
				list = sql.getPastObstetricsPregnanciesForPatient(pid);
			} catch (DBException e) {
				// TODO Throw error here
				e.printStackTrace();
			}
		}

		return list;
	}
	
	// for single priorPregnancy
	public void getSinglePregnancy(String date){
		try {
			priorPregnancy = sql.priorPregnancy(pid, date);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObstetricsPregnancy getCurrentPregnancy() {
		ObstetricsPregnancy current = new ObstetricsPregnancy();
		if(pid != null){
			try {
				current = sql.getCurrentObstetricsPregnancy(pid);
			} catch (DBException e) {
				// TODO throw error here
				e.printStackTrace();
			}
		}

		return current;
	}
	
	public ObstetricsPregnancy getSingleCurrentPregnancy(){
		return currentPregnancy;
	}
	
	public boolean checkOBGYN() {
		boolean eligible = false;
		try {
			eligible = personnelDAO.getPersonnel(utils.getSessionLoggedInMIDLong()).getSpecialty().equals("OB/GYN");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eligible;
	}
	
	public void activatePatient() {
		System.out.println("activating");
		try {
			PatientBean patient = patientDAO.getPatient(pid);
			patient.setObstetricsPatient(true);
			patientDAO.editPatient(patient, utils.getSessionLoggedInMIDLong());
		} catch (DBException e) {
			//TODO throw error
			e.printStackTrace();
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println("redirecting");
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO ERROR
			e.printStackTrace();
		}
		
	}
	
	public void initializePregnancy() {
		newPregnancy.setPid(pid);
		try {
			sql.add(newPregnancy);
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch (FormValidationException e) {
			// TODO invalid data
			e.printStackTrace();
		}
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Object req = ctx.getRequest();
		try {
			ctx.redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editCurrentPregnancy() {
		try {
			sql.update(currentPregnancy);
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch (FormValidationException e) {
			// TODO invalid data
			e.printStackTrace();
		}
	}
	
	public void addCurrentPregnancy() {
		if (!getCurrentPregnancy().equals(new ObstetricsPregnancy())) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "There is already an active pregnancy.", "currentPregnancy:addNewPregnancy");
		}
		else if(checkOBGYN()) {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			Object req = ctx.getRequest();
			try {
				ctx.redirect("/iTrust/auth/hcp-obstetrics/addNewPregnancy.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "You do not have access to add initialize a pregnancy.", "currentPregnancy:addNewPregnancy");
		}
	}

}
