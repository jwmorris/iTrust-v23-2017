package edu.ncsu.csc.itrust.controller.obstetrics;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
public class ObstetricsController {
	
	private ObstetricsPregnancy currentPregnancy;
	private List<ObstetricsPregnancy> priorPregnancies;
	private ObstetricsPregnancyData sql;
	private SessionUtils utils;
	private Long pid;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	
	public ObstetricsController () {
		try {
			sql = new ObstetricsMySQL();
		} catch (DBException e) {
			//bad
		}
		priorPregnancies = getPriorPregnancies();
		currentPregnancy = getCurrentPregnancy();
		this.utils = SessionUtils.getInstance();
		pid = utils.getCurrentPatientMIDLong();
		DAOFactory factory = DAOFactory.getProductionInstance();
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
	}
	
	public boolean checkPatientEligibility() {
		boolean eligible = false;
		try {
			eligible = patientDAO.getPatient(pid).isObstetricsPatient();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eligible;
	}
	
	public List<ObstetricsPregnancy> getPriorPregnancies() {
		List<ObstetricsPregnancy> list = Collections.emptyList();
		try {
			list = sql.getPastObstetricsPregnanciesForPatient(pid);
		} catch (DBException e) {
			// TODO Throw error here
		
		}
		return list;
	}
	
	public ObstetricsPregnancy getCurrentPregnancy() {
		ObstetricsPregnancy current = new ObstetricsPregnancy();
		try {
			current = sql.getCurrentObstetricsPregnancy(pid);
		} catch (DBException e) {
			// TODO throw error here
		}
		return current;
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
		try {
			PatientBean patient = patientDAO.getPatient(pid);
			patient.setObstetricsPatient(true);
			patientDAO.editPatient(patient, utils.getSessionLoggedInMIDLong());
		} catch (DBException e) {
			//TODO throw error
		}
		
		
	}
	
	public void initializePregnancy() {
		try {
			sql.add(currentPregnancy);
		} catch (DBException e) {
			// TODO Throw exception
		} catch (FormValidationException e) {
			// TODO invalid data
		}
	}
	
	public void editCurrentPregnancy() {
		try {
			sql.update(currentPregnancy);
		} catch (DBException e) {
			// TODO Throw exception
		} catch (FormValidationException e) {
			// TODO invalid data
		}
	}
}
