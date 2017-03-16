package edu.ncsu.csc.itrust.controller.calendar;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import edu.ncsu.csc.itrust.controller.fitness.FitnessController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.webutils.SessionUtils;



@ManagedBean(name="calendar")
@ViewScoped
public class CalendarController {
	private ScheduleModel model;
	private FitnessDataEvent event = new FitnessDataEvent();
	private SessionUtils sessionUtils;
	private FitnessController fitnessController;
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private Map<String, FitnessDataEvent> eventMap;
	
	public CalendarController() {
		this.model = new DefaultScheduleModel();
		this.sessionUtils = SessionUtils.getInstance();
		eventMap = new HashMap<>();
		try {
			this.fitnessController = new FitnessController();
		} catch (DBException e) {
			e.printStackTrace();
		}
		String pid = sessionUtils.getSessionPID();
		try {
			List<Fitness> patientData = fitnessController.getFitnessDataForPatient(pid);
			
			if(patientData != null && patientData.size() != 0) {
				for(Fitness f : patientData) {
					Date date = df.parse(f.getDate());
					FitnessDataEvent event = new FitnessDataEvent(date);
					event.setFitnessData(f);
					model.addEvent(event);
					eventMap.put(f.getDate(), event);
				}
			}
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	public ScheduleModel getModel() {		
		return model; 
	}
	public FitnessDataEvent getEvent() { return event; }
	
	public void setEvent(FitnessDataEvent event) { this.event = event; }
	
	public void addEvent() throws DBException, FormValidationException {
		if(event.getId() == null){
			String pid = sessionUtils.getSessionPID();
			event.setPatient(pid);
			try {
				if(fitnessController.add(event.getFitnessData())) {
					model.addEvent(event);
					eventMap.put(df.format(event.getStartDate()), event);
					RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
					event = new FitnessDataEvent(); //reset dialog form
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			
			if(fitnessController.edit(event.getFitnessData())) {
				RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
				model.updateEvent(event);
				eventMap.put(df.format(event.getStartDate()), event);
				event = new FitnessDataEvent(); //reset dialog form
			}
			
		}
		
	}
	public void onEventSelect(SelectEvent e) {
		event = (FitnessDataEvent) e.getObject();
	}
	public void onDateSelect(SelectEvent e) {
		
		Date date = (Date) e.getObject();
		
		if(eventMap.containsKey(df.format(date))) {
			event = eventMap.get(df.format(date));
		} else {
			event = new FitnessDataEvent(date);
		}
		
	
		
	}

	
	
}
