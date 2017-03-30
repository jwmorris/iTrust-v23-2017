package edu.ncsu.csc.itrust.controller.obstetrics;


import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import javax.servlet.http.Part;


import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;


import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.ApptBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.ApptDAO;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.webutils.SessionUtils;
/**
 * We modeled some of the code off of the OfficeVisitForm.xhtml
 * @author David
 *
 */
@ManagedBean(name = "obstetrics_visit_form")
@ViewScoped
public class ObstetricsVisitForm {
	
	private String API_KEY = "AIzaSyB05jE7CNQmNEpoOhF5nBUMmW0CzFW6wa4";

	// Shouldn't need to initialize since it exits because of session scope
	private ObstetricsVisitController controller;
	// obstetrics office visit object here
	private ObstetricsOfficeVisit ov;

	// Ultrasound 
	private Ultrasound us;
	// Fetus
	private Fetus fetus;

	/** Fields that can be reused*/
	// pid of patient
	private Long pid;
	// id of visit, might not need
	private Long visitID;
	// date of visit
	private Date date;
	
	/** Fields unique to  Office Visits */
	// weeks pregnant at visit
	private String weeksPregnant;
	private String weight;
	private String bloodPressure;
	// fetal heart rate
	private String ftr;
	// multiple babies?
	private boolean multiplePregnancy;
	//Number of babies
	private String babyNum;
	// low lyting placenta?
	private boolean placenta;
	
	/** Fields unique to fetus data */ 
	// crown rump length
	private String crl;
	// biparietal diameter
	private String bpd;
	// head cirumference
	private String hc;
	// femur length
	private String fl;
	// occipitofrontal diameter
	private String ofd;
	// abdominal circumference
	private String ac;
	// humerus length
	private String hl;
	// estimated fetal weight
	private String efw;
	//file of image upload
	private Part image;
	
	// next appointment date
	private String next;
	//The number of fetuses in the office visit
	private int numFeti;
	//the id of the selected fetus
	private int selectedFetus;
	//are we editing a fetus?
	private boolean editFetus;

	//ultrasound to delete
	private String selectedUltrasound;


	private String calendarEmail;
	private String apptType;
	
	public String getCalendarEmail() {
		return calendarEmail;
	}

	public void setCalendarEmail(String calendarEmail) {
		this.calendarEmail = calendarEmail;
	}

	
	public ObstetricsVisitForm() {
		this(null);
	}
	
	public ObstetricsVisitForm(ObstetricsVisitController ovc) {
		try {
			controller = (ovc == null) ? new ObstetricsVisitController() : ovc;
			fetus = new Fetus();
			us = new Ultrasound();
			ov = controller.getSelectedVisit();// ? null : new ObstetricsOfficeVisit();
			if ( ov == null )
				ov = new ObstetricsOfficeVisit();
			visitID = ov.getId();
			pid = ov.getPid();// ? null : 
			if ( pid == 0 )
				pid = SessionUtils.getInstance().getCurrentPatientMIDLong();
			date = ov.getVisitDate();
			weeksPregnant = ov.getWeeksPregnant();
			weight = ov.getWeight();
			bloodPressure = ov.getBp();
			ftr = ov.getFhr();
			multiplePregnancy = ov.isMultiplePregnancy();
			babyNum = ov.getNumBabies();
			placenta = ov.isLowLying();
			List<Fetus> f = getFeti();
			numFeti = (f == null) ? 0 : f.size();
			editFetus = false;
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obsetrics Visit Controller Error",
					"Obstetrics Visit Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
		}
	}
	/**
	 * Constructor used for testing
	 */
	public ObstetricsVisitForm(ObstetricsVisitController ovc, SessionUtils utils) {
		try {
			controller = (ovc == null) ? new ObstetricsVisitController() : ovc;
			fetus = new Fetus();
			us = new Ultrasound();
			ov = controller.getSelectedVisit();// ? null : new ObstetricsOfficeVisit();
			if ( ov == null )
				ov = new ObstetricsOfficeVisit();
			visitID = ov.getId();
			pid = ov.getPid();// ? null : 
			if ( pid == 0 )
				pid = utils.getCurrentPatientMIDLong();
			date = ov.getVisitDate();
			weeksPregnant = ov.getWeeksPregnant();
			weight = ov.getWeight();
			bloodPressure = ov.getBp();
			ftr = ov.getFhr();
			multiplePregnancy = ov.isMultiplePregnancy();
			babyNum = ov.getNumBabies();
			placenta = ov.isLowLying();
			image = null;
		} catch ( Exception e ) {
			//do nothing
		}
	}
	
	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * @return the visitID
	 */
	public Long getVisitID() {
		return visitID;
	}

	/**
	 * @param visitID the visitID to set
	 */
	public void setVisitID(Long visitID) {
		this.visitID = visitID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the weeksPregnant
	 */
	public String getWeeksPregnant() {
		return weeksPregnant;
	}

	/**
	 * @param weeksPregnant the weeksPregnant to set
	 */
	public void setWeeksPregnant(String weeksPregnant) {
		this.weeksPregnant = weeksPregnant;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @return the ftr
	 */
	public String getFtr() {
		return ftr;
	}

	/**
	 * @param ftr the ftr to set
	 */
	public void setFtr(String ftr) {
		this.ftr = ftr;
	}

	/**
	 * @return the multiplePregnancy
	 */
	public boolean getMultiplePregnancy() {
		return multiplePregnancy;
	}

	/**
	 * @param multiplePregnancy the multiplePregnancy to set
	 */
	public void setMultiplePregnancy(boolean multiplePregnancy) {
		this.multiplePregnancy = multiplePregnancy;
	}
	
	public String getBabyNum() {
		return babyNum;
	}
	
	public void setBabyNum(String babyNum) {
		this.babyNum = babyNum;
	}

	/**
	 * @return the placenta
	 */
	public boolean getPlacenta() {
		return placenta;
	}

	/**
	 * @param placenta the placenta to set
	 */
	public void setPlacenta(boolean placenta) {
		this.placenta = placenta;
	}

	/**
	 * @return the crl
	 */
	public String getCrl() {
		return crl;
	}

	/**
	 * @param crl the crl to set
	 */
	public void setCrl(String crl) {
		this.crl = crl;
	}

	/**
	 * @return the bpd
	 */
	public String getBpd() {
		return bpd;
	}

	/**
	 * @param bpd the bpd to set
	 */
	public void setBpd(String bpd) {
		this.bpd = bpd;
	}

	/**
	 * @return the hc
	 */
	public String getHc() {
		return hc;
	}

	/**
	 * @param hc the hc to set
	 */
	public void setHc(String hc) {
		this.hc = hc;
	}

	/**
	 * @return the fl
	 */
	public String getFl() {
		return fl;
	}

	/**
	 * @param fl the fl to set
	 */
	public void setFl(String fl) {
		this.fl = fl;
	}

	/**
	 * @return the ofd
	 */
	public String getOfd() {
		return ofd;
	}

	/**
	 * @param ofd the ofd to set
	 */
	public void setOfd(String ofd) {
		this.ofd = ofd;
	}

	/**
	 * @return the ac
	 */
	public String getAc() {
		return ac;
	}

	/**
	 * @param ac the ac to set
	 */
	public void setAc(String ac) {
		this.ac = ac;
	}

	/**
	 * @return the hl
	 */
	public String getHl() {
		return hl;
	}

	/**
	 * @param hl the hl to set
	 */
	public void setHl(String hl) {
		this.hl = hl;
	}

	/**
	 * @return the efw
	 */
	public String getEfw() {
		return efw;
	}

	/**
	 * @param efw the efw to set
	 */
	public void setEfw(String efw) {
		this.efw = efw;
	}

	/**
	 * @return the nextAppoint
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @param nextAppoint the nextAppoint to set
	 */
	public void setNext(String next) {
		this.next = next;
	}
	/**
	 * 
	 * @param image The image part that is uploaded.
	 */
	public void setImage(Part image) {
		this.image = image;
	}
	
	public Part getImage() {
		return image;
	}
	/**
	 * Called when user clicks on the submit button in obstetricsVisitInfo.xhtml. Takes data from form
	 * and sends to sql/loader/validator class
	 */
	public void submitVisitInfo() {
		ov.setBp( bloodPressure );
		ov.setFhr( ftr );
		ov.setLowLying( placenta );
		ov.setMultiplePregnancy( multiplePregnancy );
		ov.setNumBabies( babyNum );
		ov.setPid( pid );
		if ( ov.getVisitDate() == null )
			ov.setVisitDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		ov.setWeeksPregnant( weeksPregnant );
		ov.setWeight( weight );
		if ( visitID == 0 )
			visitID = controller.addReturnGeneratedId( ov );
		else
			controller.edit( ov );
	}
	
	/**
	 * Called when user updates fetal data in obstetricsVisitInfo.xhtml. Takes data from form
	 * and sends to sql/loader/validator class
	 */
	public void submitFetusInfo(){
		if ( visitID == 0 ) {
			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "Enter General Information first.","Enter General Information first.", null );
			return;
		}
			// set fetus data with data
		fetus.setOvId( visitID );
		fetus.setAc( ac );
		fetus.setBpd( bpd );
		fetus.setCrl( crl );
		fetus.setEfw( efw );
		fetus.setFl( fl );
		fetus.setHc( hc );
		fetus.setHl( hl );
		fetus.setOfd( ofd );
		
		
		if( editFetus ) {
			fetus.setMultiNum( selectedFetus );
			controller.edit( fetus );
			editFetus = false;
		} else {
			fetus.setMultiNum( numFeti + 1 );
			controller.addFetus( fetus );
			numFeti++;
		}
		
		ac = "";
		bpd = "";
		crl = "";
		efw = "";
		fl = "";
		hc = "";
		hl = "";
		ofd = "";

	}
	
	/**
	 * Not sure if we need
	 * 
	 * Called when user submits an ultrasound
	 */
	public void submitUltrasound() {
		if ( visitID == 0 ) {
			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "Enter General Information first.","Enter General Information first.", null );
			return;
		}
		if( image == null ) {
			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "No file selected.","No file selected", null );
			return;
		}
		
		InputStream input = null;
		try {
			input = image.getInputStream();
			int dotIndex = image.getSubmittedFileName().lastIndexOf( '.' );
			String name = image.getSubmittedFileName().substring( 0, dotIndex );
			String type = image.getSubmittedFileName().substring( dotIndex );
			
			us.setPicPath( name + Calendar.getInstance().getTimeInMillis() + type );
			us.setImg( input );

	    }
	    catch ( IOException e ) {
	    	SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "Image could not be stored", "Image could not be stored", null );
	    	e.printStackTrace();
	    }
		
		us.setDateCreated( ov.getVisitDate() );
		us.setOvId( visitID );
		us.setPid( pid );
		
		controller.addUltrasound( us );
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Fetus> getFeti() {
		return controller.getFeti( visitID );
	}
	
	public List<Ultrasound> getUltrasound() {
		return controller.getUltrasounds( visitID );
	}
	
	public void logViewObstetricsVisit() {
		if(visitID != 0) {
			controller.logViewObstetricsVisit( visitID );
		}
		
	}
	
	public void editFetus() {
		Fetus selected = controller.getFetusById( visitID, selectedFetus );
		
		ac = selected.getAc();
		bpd = selected.getBpd();
		crl = selected.getCrl();
		efw = selected.getEfw();
		fl = selected.getFl();
		hc = selected.getHc();
		hl = selected.getHl();
		ofd = selected.getOfd();
		editFetus = true;
	}
	
	public void setSelectedFetus( int num ) {
		this.selectedFetus = num;
	}
	
	public int getSelectedFetus() {
		return selectedFetus;
	}
	
	public void submitNextScheduledOfficeVisit() {		
		if ( weeksPregnant == null || weeksPregnant.equals("") ) {
			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "Enter General Information first.", "Enter General Information first.", null );
			return;
		}
		
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ssZ");
		String stringAppointmentDay = getNextAppointmentDay(weeksPregnant);
		if(calendarEmail == null || calendarEmail.equals("")) {
			System.out.println("Empty entry for calendarEmail");
			stringAppointmentDay = getNullAppointmentDay(weeksPregnant);
		}
		String stringTimeMin = stringAppointmentDay + "09:00:00-0400";
		String stringTimeMax = stringAppointmentDay + "16:00:00-0400";
		
		Date timeMin = null;
		Date timeMax = null;
		try {
			timeMin = new Date(dateFormat.parse(stringTimeMin).getTime());
			timeMax = new Date(dateFormat.parse(stringTimeMax).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Full Day: " + dateFormat.format(timeMin) + " TO " + dateFormat.format(timeMax));
		
		String goodTime = null;
		Date nextAppt = null;

		Calendar cal = Calendar.getInstance();
		try {	
			if(calendarEmail != null && !calendarEmail.equals("")) {
				goodTime = checkEvents(timeMin, timeMax);
				System.out.println("Good Time for Appointment: " + goodTime);
	
				if (goodTime == null) {
					// Schedule next day at same time
					nextAppt = new Date(dateFormat.parse(stringAppointmentDay + cal.get(Calendar.HOUR_OF_DAY) + ":00:00-0400").getTime());
	            	cal.setTime(nextAppt);
	            	cal.add(Calendar.DAY_OF_MONTH, 1);
	            	nextAppt = new Date(cal.getTime().getTime());
	            	DateFormat printDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk");
					
	            	nextAppt = new Date(dateFormat.parse(printDateFormat.format(nextAppt) + ":00:00-0400").getTime());
					// Add to schedule
				} else {
					// Schedule at returned time
					nextAppt = new Date(dateFormat.parse(goodTime).getTime());
								}
			} else {
				nextAppt = new Date(dateFormat.parse(stringAppointmentDay + cal.get(Calendar.HOUR_OF_DAY) + ":00:00-0400").getTime());
				
			}
		} catch( ParseException e) {
			nextAppt = new Date(Calendar.getInstance().getTimeInMillis());
		}
		System.out.println("Found Good Time for Appointment to be: " + dateFormat.format(nextAppt));
		ApptBean apptBean = new ApptBean();
		apptBean.setHcp(SessionUtils.getInstance().getSessionLoggedInMIDLong());
		apptBean.setPatient(pid);
		Timestamp nextApptTS = new Timestamp(nextAppt.getTime());
		apptBean.setDate(nextApptTS);
		apptBean.setApptType(apptType);
		ApptDAO appointmentDAO = new ApptDAO(DAOFactory.getProductionInstance());
		try {
			appointmentDAO.scheduleAppt(apptBean);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getNullAppointmentDay(String weeksPregnant) {
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'");
		
		int deltaDays = 0;
		apptType = "ObstetricsOfficeVisit";
		if (Integer.parseInt(weeksPregnant) <= 13) {
			deltaDays = 28;
		} else if (Integer.parseInt(weeksPregnant) <= 28) {
			deltaDays = 14;
		} else {
			deltaDays = 7;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, deltaDays);
		String appointmentDay = dateFormat.format(new Date(cal.getTimeInMillis()));
		
		return appointmentDay;
	}

	private String getNextAppointmentDay(String weeksPregnant) {
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'");
		
		int deltaDays = 0;
		apptType = "ObstetricsOfficeVisit";
		if (Integer.parseInt(weeksPregnant) <= 13) {
			deltaDays = 28;
		} else if (Integer.parseInt(weeksPregnant) <= 28) {
			deltaDays = 14;
		} else if (Integer.parseInt(weeksPregnant) <= 39) {
			deltaDays = 7;
		} else if (Integer.parseInt(weeksPregnant) <= 41) {
			deltaDays = 2;
		} else if (Integer.parseInt(weeksPregnant) >= 42) {
			apptType = "ChildbirthVisit";
			deltaDays = 2;
		}
		
//		System.out.println("deltaDays: " + deltaDays);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, deltaDays);
		String appointmentDay = dateFormat.format(new Date(cal.getTimeInMillis()));
		
		return appointmentDay;
	}
	
	public String checkEvents(Date timeMin, Date timeMax) {
		System.out.println("Calendar Email: " + calendarEmail);
		DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ssZ");
		
		String stringTimeMin = dateFormat.format(timeMin);
		String stringTimeMax = dateFormat.format(timeMax);
    	
    	DateFormat printDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'");
    	Calendar cal = Calendar.getInstance();
    	String stringDateNow = printDateFormat.format(timeMin) + cal.get(Calendar.HOUR_OF_DAY) + ":00:00-0400";
    	cal.add(Calendar.HOUR_OF_DAY, 1);
    	String stringDateLater = printDateFormat.format(timeMin) + cal.get(Calendar.HOUR_OF_DAY) + ":00:00-0400"; 
    	Date dateNow, dateLater;
		try {
			dateNow = new Date(dateFormat.parse(stringDateNow).getTime());
			dateLater = new Date(dateFormat.parse(stringDateLater).getTime());
		} catch (ParseException e1) {
			dateNow = new Date(cal.getTimeInMillis());
			dateLater = new Date(cal.getTimeInMillis());
			e1.printStackTrace();
		}
		System.out.println(dateFormat.format(dateNow) + " COMAPARED TO " + dateFormat.format(dateLater));
    	
    	if (checkHolidays(dateNow)) {
    		System.out.println("////////////////////////////////////////////////////\r\nIT'S A HOLIDAY\r\n/////////////////////////////////////////");
    		return null;
    	}
    	
    	boolean available = true;
    	if ( dateNow.before(timeMin) || dateLater.after(timeMax)) {
    		available = false;
    	}
		
		HttpURLConnection conn = null;
		String stringURL = "https://www.googleapis.com/calendar/v3/calendars/" + calendarEmail + "/events?key=" + API_KEY + "&timeMin=" + stringTimeMin + "&timeMax=" + stringTimeMax + "&singleEvents=True&orderBy=starttime";
		
		try {
			URL url = new URL(stringURL);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");

		    System.out.println(conn.getURL());
		    
		    if ( conn.getResponseCode() < 200 || conn.getResponseCode() >= 300 ) {
		    	System.out.println(conn.getResponseCode() + ": " + conn.getResponseMessage());
		    	return null;
		    } else {
		    	System.out.println(conn.getResponseCode() + ": " + conn.getResponseMessage());
		    }
		    
		    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringJSON = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringJSON.append(line+"\n");
            }
            br.close();
            
            System.out.println(stringJSON.toString());
            
            JSONObject wholeResponse = new JSONObject(stringJSON.toString());
            JSONArray items = wholeResponse.getJSONArray("items");
            
            System.out.println("length: " + items.length());
            for (int i = 0; i < items.length(); i++) {
            	JSONObject event = items.getJSONObject(i);
            	JSONObject startTimeObj = event.getJSONObject("start");
            	JSONObject endTimeObj = event.getJSONObject("end");
            	String stringStartTime = startTimeObj.getString("dateTime");
            	stringStartTime = stringStartTime.substring(0, stringStartTime.length()-3) +stringStartTime.substring(stringStartTime.length()-2);
            	String stringEndTime = endTimeObj.getString("dateTime");
            	stringEndTime = stringEndTime.substring(0, stringEndTime.length()-3) +stringEndTime.substring(stringEndTime.length()-2);
            	Date startTime, endTime;
            	startTime = new Date(dateFormat.parse(stringStartTime).getTime());
            	endTime = new Date(dateFormat.parse(stringEndTime).getTime());
            	
            	if ( dateNow.before(startTime) || dateLater.before(startTime) || dateNow.after(endTime) || dateLater.after(endTime) ) {
            		System.out.println("YOU ARE AFTER THE EXPECTED TIME!!!");
            		available = false;
            		break;
            	}
            	
            }
            if (available) {
            	return stringDateNow;
            }
            
            for ( int i = 0; i < items.length(); i++) {
            	if (timeMin.after(timeMax)) {
            		return null;
            	}
            	JSONObject event = items.getJSONObject(i);
            	JSONObject startTimeObj = event.getJSONObject("start");
            	JSONObject endTimeObj = event.getJSONObject("end");
            	String stringStartTime = startTimeObj.getString("dateTime");
            	stringStartTime = stringStartTime.substring(0, stringStartTime.length()-3) +stringStartTime.substring(stringStartTime.length()-2);
            	String stringEndTime = endTimeObj.getString("dateTime");
            	stringEndTime = stringEndTime.substring(0, stringEndTime.length()-3) +stringEndTime.substring(stringEndTime.length()-2);
            	Date startTime, endTime;
            	startTime = new Date(dateFormat.parse(stringStartTime).getTime());
            	endTime = new Date(dateFormat.parse(stringEndTime).getTime());
            	
            	System.out.println("Event Time: " + stringStartTime + " TO " + stringEndTime);
            	
            	Date timeMinOneHour = timeMin;
            	cal.setTime(timeMinOneHour);
            	cal.add(Calendar.HOUR_OF_DAY, 1);
            	timeMinOneHour = new Date(cal.getTime().getTime());
            	if (timeMinOneHour.before(startTime)) {
            		return dateFormat.format(timeMin);
            	} else {
            		timeMin = endTime;
            	}
            }

		    if (timeMin.before(timeMax)) {
		    	return dateFormat.format(timeMin);
		    } else {
		    	return null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		} finally {
			if (conn != null) {
		    	conn.disconnect();
		    }
		}
	}

	private boolean checkHolidays(Date dateNow) {
		boolean notHoliday = true;
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(dateNow);
		//NEW YEARS
		if(calDate.get(Calendar.MONTH) == Calendar.JANUARY && calDate.get(Calendar.DAY_OF_MONTH) == 1) {
			notHoliday = false;
		}
		//GEORGE WASHINGTONS BIRTHDAY
		if(calDate.get(Calendar.MONTH) == Calendar.FEBRUARY && calDate.get(Calendar.DAY_OF_MONTH) == 22) {
			notHoliday = false;
		}
		//INDEPENDENCE DAY
		if(calDate.get(Calendar.MONTH) == Calendar.JULY && calDate.get(Calendar.DAY_OF_MONTH) == 4) {
			notHoliday = false;
		}
		//CHISTMAS EVE
		if(calDate.get(Calendar.MONTH) == Calendar.DECEMBER && calDate.get(Calendar.DAY_OF_MONTH) == 24) {
			notHoliday = false;
		}
		//CHRISTMAS DAY
		if(calDate.get(Calendar.MONTH) == Calendar.DECEMBER && calDate.get(Calendar.DAY_OF_MONTH) == 25) {
			notHoliday = false;
		}
		// More complex holidays used help from http://www.javaworld.com/article/2077543/learn-java/java-tip-44--calculating-holidays-and-their-observances.html
		int year = calDate.get(Calendar.YEAR);
		//MARTIN LUTHER KING JR. DAY
		Date mlk = MartinLutherKingObserved(year);
		if (dateNow.getYear() == mlk.getYear() && dateNow.getMonth() == mlk.getMonth() && dateNow.getDay() == mlk.getDay()) {
			notHoliday = false;
		}
		Date memDay = MemorialDayObserved(year);
		if (dateNow.getYear() == memDay.getYear() && dateNow.getMonth() == memDay.getMonth() && dateNow.getDay() == memDay.getDay()) {
			notHoliday = false;
		}
		Date laborDay = LaborDayObserved(year);
		if (dateNow.getYear() == laborDay.getYear() && dateNow.getMonth() == laborDay.getMonth() && dateNow.getDay() == laborDay.getDay()) {
			notHoliday = false;
		}
		Date columbusDay = ColumbusDayObserved(year);
		if (dateNow.getYear() == columbusDay.getYear() && dateNow.getMonth() == columbusDay.getMonth() && dateNow.getDay() == columbusDay.getDay()) {
			notHoliday = false;
		}
		Date thanksgiving = ThanksgivingObserved(year);
		if (dateNow.getYear() == thanksgiving.getYear() && dateNow.getMonth() == thanksgiving.getMonth() && dateNow.getDay() == thanksgiving.getDay()) {
			notHoliday = false;
		}
		return !notHoliday;
	}
	
	public Date MartinLutherKingObserved (int nYear) {
    // Third Monday in January
    int nX;
    int nMonth = 0; // January
    Date dtD;
    dtD = new Date(nYear, nMonth, 1);
    nX = dtD.getDay();
    switch(nX)
        {
        case 0 : // Sunday
        return new Date(nYear, nMonth, 16);
        case 1 : // Monday
        return new Date(nYear, nMonth, 15);
        case 2 : // Tuesday
        return new Date(nYear, nMonth, 21);
        case 3 : // Wednesday
        return new Date(nYear, nMonth, 20);
        case 4 : // Thursday
        return new Date(nYear, nMonth, 19);
        case 5 : // Friday
        return new Date(nYear, nMonth, 18);
        default : // Saturday
        return new Date(nYear, nMonth, 17);
        }
    }
	public static Date MemorialDayObserved (int nYear) {
		// Last Monday in May
	    int nX;
	    int nMonth = 4; //May
	    Date dtD;
	    dtD = new Date(nYear, nMonth, 31);
	    nX = dtD.getDay();
	    switch(nX)
	        {
	        case 0 : // Sunday
	        return new Date(nYear, nMonth, 25);
	        case 1 : // Monday
	        return new Date(nYear, nMonth, 31);
	        case 2 : // Tuesday
	        return new Date(nYear, nMonth, 30);
	        case 3 : // Wednesday
	        return new Date(nYear, nMonth, 29);
	        case 4 : // Thursday
	        return new Date(nYear, nMonth, 28);
	        case 5 : // Friday
	        return new Date(nYear, nMonth, 27);
	        default : // Saturday
	        return new Date(nYear, nMonth, 26);
	        }
	    }
	public static Date LaborDayObserved (int nYear)
    {
    // The first Monday in September
    int nX;
    int nMonth = 8; // September
    Date dtD;
    dtD = new Date(nYear, 9, 1);
    nX = dtD.getDay();
    switch(nX)
        {
        case 0 : // Sunday
        return new Date(nYear, nMonth, 2);
        case 1 : // Monday
        return new Date(nYear, nMonth, 7);
        case 2 : // Tuesday
        return new Date(nYear, nMonth, 6);
        case 3 : // Wednesday
        return new Date(nYear, nMonth, 5);
        case 4 : // Thursday
        return new Date(nYear, nMonth, 4);
        case 5 : // Friday
        return new Date(nYear, nMonth, 3);
        default : // Saturday
        return new Date(nYear, nMonth, 2);
        }
    }
	public static Date ColumbusDayObserved (int nYear)
    {
    // Second Monday in October
    int nX;
    int nMonth = 9; // October 
    Date dtD;
    dtD = new Date(nYear, nMonth, 1);
    nX = dtD.getDay();
    switch(nX)
        {
        case 0 : // Sunday
        return new Date(nYear, nMonth, 9);
        case 1 : // Monday
        return new Date(nYear, nMonth, 15);
        case 2 : // Tuesday
        return new Date(nYear, nMonth, 14);
        case 3 : // Wednesday
        return new Date(nYear, nMonth, 13);
        case 4 : // Thursday
        return new Date(nYear, nMonth, 12);
        case 5 : // Friday
        return new Date(nYear, nMonth, 11);
        default : // Saturday
        return new Date(nYear, nMonth, 10);
        }
    }
	public static Date ThanksgivingObserved(int nYear)
    {
    int nX;
    int nMonth = 10; // November
    Date dtD;
    dtD = new Date(nYear, nMonth, 1);
    nX = dtD.getDay();
    switch(nX)
        {
        case 0 : // Sunday
        return new Date(nYear, nMonth, 26);
        case 1 : // Monday
        return new Date(nYear, nMonth, 25);
        case 2 : // Tuesday
        return new Date(nYear, nMonth, 24);
        case 3 : // Wednesday
        return new Date(nYear, nMonth, 23);
        case 4 : // Thursday
        return new Date(nYear, nMonth, 22);
        case 5 : // Friday
        return new Date(nYear, nMonth, 28);
        default : // Saturday
        return new Date(nYear, nMonth, 27);
        }
    } 
	
	public void setSelectedUltrasound( String selectedUltrasound ) {
		this.selectedUltrasound = selectedUltrasound;
	}
	
	public String getSelectedUltrasound() {
		return selectedUltrasound;
	}
	public void deleteUltrasound() {
		controller.deleteUltrasound( visitID, selectedUltrasound );
	}
	
	public void download( String ultrasound ) {
		controller.download( ultrasound, visitID );
	}
	
}
