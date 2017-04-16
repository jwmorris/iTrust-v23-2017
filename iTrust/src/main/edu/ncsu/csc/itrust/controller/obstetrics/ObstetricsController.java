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
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

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
import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ViewScoped
@ManagedBean(name = "obstetrics_controller")
public class ObstetricsController extends iTrustController {
	
	private ObstetricsPregnancy currentPregnancy;
	private List<ObstetricsPregnancy> priorPregnancies;
	private ObstetricsPregnancy newPregnancy = new ObstetricsPregnancy();
	private ObstetricsPregnancy priorPregnancy = new ObstetricsPregnancy();
	private ObstetricsPregnancy newPriorPregnancy = new ObstetricsPregnancy();
	private ObstetricsPregnancyData sql;
	private SessionUtils utils;
	private Long pid;
	private Long hcp;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	private String selectedDate;

	public ObstetricsController () throws DBException {
		sql = new ObstetricsMySQL();
		this.utils = SessionUtils.getInstance();
		pid = utils.getCurrentPatientMIDLong();
		this.hcp = utils.getSessionLoggedInMIDLong();
		DAOFactory factory = DAOFactory.getProductionInstance();
		patientDAO = factory.getPatientDAO();
		personnelDAO = factory.getPersonnelDAO();
		priorPregnancies = getPriorPregnancies();
		currentPregnancy = getCurrentPregnancy();
		newPriorPregnancy = getNewPriorPregnancy();
		newPriorPregnancy.setCurrent( false );
		selectedDate = "";
	}
	
	public ObstetricsController(DataSource ds, Long pid, Long hcp, DAOFactory df) {
		try {
			sql = new ObstetricsMySQL(ds);
		} catch (DBException e) {
			e.printStackTrace();
		}
		this.pid = pid;
		this.hcp = hcp;
	
		patientDAO = df.getPatientDAO();
		personnelDAO = df.getPersonnelDAO();
		priorPregnancies = getPriorPregnancies();
		currentPregnancy = getCurrentPregnancy();
		newPriorPregnancy = getNewPriorPregnancy();
		newPriorPregnancy.setCurrent( false );
		selectedDate = "";
	}
	

	
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String date) {
		this.selectedDate = date;
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
	public ObstetricsPregnancy getSinglePregnancy(String date){
		
		try {
			priorPregnancy = sql.getObstetricsPregnancy(pid, date);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return priorPregnancy;
	}
	
	public ObstetricsPregnancy getCurrentPregnancy() {
		ObstetricsPregnancy current = new ObstetricsPregnancy();
		
		if(pid != null) {
			try {
				current = sql.getCurrentObstetricsPregnancy(pid);
			} catch (DBException e) {
				// TODO throw error here
				e.printStackTrace();
			}
			/*if(current != null) {
				logTransaction(TransactionType.VIEW_INITIAL_OBSTETRICS_RECORD, hcp, pid, current.getEdd());
			}*/
		}
		
		return current;
	}
	
	public ObstetricsPregnancy getSingleCurrentPregnancy(){
		return currentPregnancy;
	}
	
	public boolean checkOBGYN() {
		boolean eligible = false;
		try {
			eligible = personnelDAO.getPersonnel(hcp).getSpecialty().equals("OB/GYN");
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
			patientDAO.editPatient(patient, hcp);
		} catch (DBException e) {
			//TODO throw error
			e.printStackTrace();
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
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
			currentPregnancy = newPregnancy;
			logTransaction(TransactionType.CREATE_INITIAL_OBSTETRICS_RECORD, hcp, pid, newPregnancy.getEdd());
			redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch (FormValidationException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
		
	}
	
	public void editCurrentPregnancy() {
		try {

			sql.update(currentPregnancy);
			redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch (FormValidationException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
		
		
	}
	
	public void addPriorPregnancy() {
		newPriorPregnancy.setPid( pid );
		try {
			sql.add( newPriorPregnancy );
			redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch (FormValidationException e) {
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
	}
	
	public void editPriorPregnancy() {
		try {
			sql.updatePriorPregnancy(priorPregnancy, selectedDate);
			redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
		} catch (DBException e) {
			// TODO Throw exception
			e.printStackTrace();
		} catch ( FormValidationException e ) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages( true );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage() ) );
			try {
			        FacesContext.getCurrentInstance().getExternalContext().redirect( "/iTrust/auth/hcp-obstetrics/editPriorPregnancy.xhtml?priorDate=" + selectedDate );
			} catch (IOException e1) {
			        redirect("/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
			}
			printFacesMessage( FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage(), null );
		}
		
	}
	
	public void addCurrentPregnancy() {
		if(!checkOBGYN()) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "You do not have access to edit a pregnancy.", "currentPregnancy:addNewPregnancy");
			return;
		}
		
		if (!getCurrentPregnancy().equals(new ObstetricsPregnancy())) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "There is already an active pregnancy.", "currentPregnancy:addNewPregnancy");
			return;
		}
		if(checkOBGYN()) {
			redirect("/iTrust/auth/hcp-obstetrics/addNewPregnancy.xhtml");
		}
	}
	
	public void editCurrentButton() {
		if(!checkOBGYN()) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "You do not have access to edit a pregnancy.", "editCurrentForm:editCurrentPregnancy");
			return;
		} else if(checkOBGYN()) {
			logTransaction(TransactionType.VIEW_OBSTETRICS_OV, hcp, pid, getCurrentPregnancy().getEdd());
			redirect("/iTrust/auth/hcp-obstetrics/editCurrentPregnancy.xhtml");
		} else if (getCurrentPregnancy().equals(new ObstetricsPregnancy())) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "There is not a current pregnancy to edit.", "editCurrentForm:editCurrentPregnancy");
		} 
	}
	
	public void editPriorPregnancyButton() {
		if(!checkOBGYN()) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "You do not have access to edit a prior pregnancy.", "editPriorForm:editPriorPregnancy");
			return;
		}
		if (selectedDate.equals("")) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "There are no prior pregnancies.", "editPriorForm:editPriorPregnancy");
		}
		else if(checkOBGYN()) {
			redirect("/iTrust/auth/hcp-obstetrics/editPriorPregnancy.xhtml?priorDate=" + selectedDate);
		} 
	}
	
	public void addPriorPregnancyButton() {
		if(!checkOBGYN()) {
			printFacesMessage(FacesMessage.SEVERITY_WARN, "Blocked", "You do not have access to edit a prior pregnancy.", "editPriorForm:editPriorPregnancy");
			return;
		}
		else if(checkOBGYN()) {
			redirect( "/iTrust/auth/hcp-obstetrics/addPriorPregnancy.xhtml" );
		} 
	}
	
	private void redirect(String url) {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Object req = ctx.getRequest();
		try {
			ctx.redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObstetricsPregnancy getPriorPregnancy() {
		return priorPregnancy;
	}
	
	public void setPriorPregnancy(ObstetricsPregnancy op) {
		this.priorPregnancy = op;
	}
	public List<ObstetricsPregnancy> getPriors() {
		return getPriorPregnancies();
	}
	
	public ObstetricsPregnancy getNewPriorPregnancy() {
		return newPriorPregnancy;
	}

	public void setDate(String date) {
		this.selectedDate = date;
	}
	
	public ObstetricsPregnancy getPregnancyByID( String id ) {
		ObstetricsPregnancy op = null;
		try {
			op = sql.getByID( Long.parseLong( id ) );
		} catch (NumberFormatException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return op;
	}
}
