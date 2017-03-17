
package edu.ncsu.csc.itrust.controller.calendar;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.ScheduleEvent;

import edu.ncsu.csc.itrust.model.fitnessData.Fitness;

public class FitnessDataEvent implements ScheduleEvent, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5220540993254378063L;

	private Date endDate;
	
	private String id;
	
	private Date startDate;
	
	private String styleClass;
	
	private String title;
	
	private boolean editable;
	
	private boolean allDay;
	
	private Fitness fitnessData;
	
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private String description;
	
	public FitnessDataEvent(Date startDate) {
		
		this.startDate = startDate;
		this.endDate = startDate;
		
		this.fitnessData = new Fitness();
		this.title = "Fitness Data";
		try {
			this.fitnessData.setCalories(0);
			this.fitnessData.setDistance(0);
			this.fitnessData.setSteps(0);
			this.fitnessData.setFloors(0);
			String formattedDate = DATE_FORMAT.format( startDate );
			this.fitnessData.setDate( formattedDate );
			this.fitnessData.setMinsLA(0);
			this.fitnessData.setMinsFA(0);
			this.fitnessData.setMinsSed(0);
			this.fitnessData.setMinsVA(0);
			this.fitnessData.setMinsFA(0);
			this.fitnessData.setActiveHours(0);
			this.fitnessData.setHRAvg(0);
			this.fitnessData.setHRLow(0);
			this.fitnessData.setHRHigh(0);
			this.fitnessData.setUVExposure(0);
		} catch ( Exception e ) {
			//Do nothing
			//Exception cannot occur since values are 0
		}
		this.description = "Fitbit Tracker Information";
		this.allDay = true;
		this.editable = true;
		
	}
	
	public FitnessDataEvent(Date startDate, int calories, int steps, double distance, int floors, 
			int minsSed, int minsLA, int minsFA, int minsVA, int activeCals, int activeHours, int hrLow, int hrHigh,
			int hrAvg, int uvExposure)  {
		this.startDate = startDate;
		this.endDate = startDate;
		this.fitnessData = new Fitness();
		try {
			this.fitnessData.setCalories(calories);
			this.fitnessData.setSteps(steps);
			this.fitnessData.setDistance(distance);
			this.fitnessData.setFloors(floors);
			this.fitnessData.setMinsSed(minsSed);
			this.fitnessData.setMinsLA(minsLA);
			this.fitnessData.setMinsFA(minsFA);
			this.fitnessData.setMinsVA(minsVA);
			this.fitnessData.setMinsFA(minsFA);
			this.fitnessData.setActiveCals(activeCals);
			this.fitnessData.setActiveHours(activeHours);
			this.fitnessData.setHRAvg(hrAvg);
			this.fitnessData.setHRLow(hrLow);
			this.fitnessData.setHRHigh(hrHigh);
			this.fitnessData.setUVExposure(uvExposure);
			this.fitnessData.setDate(df.format(startDate));
		} catch ( SQLException e ) {
			showMessage( "Invalid values for fitness data" );
		}
		
		this.allDay = true;
		this.editable = true;
		this.title = "Fitness Data";
		this.description = "Fitbit Tracker Information";
		
	}
	
	public FitnessDataEvent() {
		this.title = "Fitness Data";
		this.fitnessData = new Fitness();
		
		try {
		this.fitnessData.setDate( "01/01/2000" );
		this.fitnessData.setCalories(0);
		this.fitnessData.setDistance(0);
		this.fitnessData.setSteps(0);
		this.fitnessData.setFloors(0);
		this.fitnessData.setActiveCals(0);
		this.fitnessData.setMinsLA(0);
		this.fitnessData.setMinsFA(0);
		this.fitnessData.setMinsSed(0);
		this.fitnessData.setMinsVA(0);
		this.fitnessData.setMinsFA(0);
		this.fitnessData.setActiveHours(0);
		this.fitnessData.setHRAvg(0);
		this.fitnessData.setHRLow(0);
		this.fitnessData.setHRHigh(0);
		this.fitnessData.setUVExposure(0);
		} catch ( SQLException e ) {
			//Do nothing
			//Error cannot occur since values are 0
		}
		this.description = "Fitbit Tracker Information";
		this.allDay = true;
		this.editable = true;
		
	}
	
	public void setActivityHours(int activityHours) {
		this.fitnessData.setActiveHours(activityHours);
	}
	
	public int getActivityHours() {
		return this.fitnessData.getActiveHours();
	}
	
	public void sethrLow(int hrLow) {
		this.fitnessData.setHRLow(hrLow);
	}
	
	public int gethrLow() {
		return this.fitnessData.getHRLow();
	}
	
	public int gethrHigh() {
		return this.fitnessData.getHRHigh();
	}
	
	public void sethrHigh(int hrHigh) {
		this.fitnessData.setHRHigh(hrHigh);
	}
	
	public void sethrAvg(int hrAvg) {
		this.fitnessData.setHRAvg(hrAvg);
	}
	
	public int gethrAvg() {
		return this.fitnessData.getHRAvg();
	}
	
	public int getuvExposure() {
		return this.fitnessData.getUVExposure();
	}
	
	public void setuvExposure(int uvExposure) {
		this.fitnessData.setUVExposure(uvExposure);
	}
	public void setDate(String date) {
		try {
			this.fitnessData.setDate(date);
		} catch (SQLException e) {
			showMessage("Invalid date");
		}
	}
	public void setFitnessData(Fitness fitness) {
		this.fitnessData = fitness;
	}
	
	public Fitness getFitnessData() {
		return this.fitnessData;
	}
	
	public String getDate() {
		return this.fitnessData.getDate();
	}
	
	public void setDateFromString( String date ) {
		try {
			this.fitnessData.setDate( date );
		} catch (SQLException e) {
			showMessage("Invalid Date");
		}
	}
	
	public void setDateFromDateObj( Date date ) {
		try {
			this.fitnessData.setDate( DATE_FORMAT.format( date ) );
		} catch (SQLException e) {
			showMessage("Invalid Date.");
		}
	}
	
	public int getCalories() {
		return this.fitnessData.getCalories();
	}


	public void setCalories(int calories) {
		this.fitnessData.setCalories(calories);
	}


	public int getSteps() {
		return this.fitnessData.getSteps();
	}


	public void setSteps(int steps) {
		this.fitnessData.setSteps(steps);
	}


	public double getDistance() {
		return this.fitnessData.getDistance();
	}


	public void setDistance(double distance) {
		this.fitnessData.setDistance(distance);
	}


	public int getFloors() {
		return this.fitnessData.getFloors();
	}


	public void setFloors(int floors) {
		this.fitnessData.setFloors(floors);
	}


	public int getMinsSed() {
		return this.fitnessData.getMinsSed();
	}


	public void setMinsSed(int minsSed) {
		this.fitnessData.setMinsSed(minsSed);
	}


	public int getMinsLA() {
		return this.fitnessData.getMinsLA();
	}


	public void setMinsLA(int minsLA) {
		this.fitnessData.setMinsLA(minsLA);
	}
	
	public int getMinsFA() {
		return this.fitnessData.getMinsFA();
	}


	public void setMinsFA(int minsFA) {
		this.fitnessData.setMinsFA(minsFA);
	}


	public int getMinsVA() {
		return this.fitnessData.getMinsVA();
	}


	public void setMinsVA(int minsVA) {
		this.fitnessData.setMinsVA(minsVA);
	}


	public int getActiveCals() {
		return this.fitnessData.getActiveCals();
	}


	public void setActiveCals(int activeCals) {
		this.fitnessData.setActiveCals(activeCals);
	}


	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Date getEndDate() {
		return this.endDate;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	@Override
	public String getStyleClass() {
		return this.styleClass;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public boolean isAllDay() {
		return this.allDay;
	}

	@Override
	public boolean isEditable() {
		return this.editable;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}
	
	public boolean equalsDate(FitnessDataEvent event) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(event.getStartDate());
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}
	
	public void setPatient(String pid) {
		try {
			this.fitnessData.setPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showMessage( String message ) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
