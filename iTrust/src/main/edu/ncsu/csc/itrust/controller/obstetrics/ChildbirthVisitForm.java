package edu.ncsu.csc.itrust.controller.obstetrics;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Baby;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.Childbirth;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

/**
 * We modeled some of the code off of the OfficeVisitForm.xhtml
 * @author David
 *
 */
@ManagedBean(name = "childbirth_visit_form")
@ViewScoped
public class ChildbirthVisitForm {

	private ChildbirthVisitController controller;
	private Childbirth cb;
	private Baby baby;
	
	/** Fields that can be reused*/
	// pid of patient
	private long pid;
	// id of visit, might not need
	private long childbirthID;
	// date of childbirth
	private Date date;
	
	/** Fields unique for childbirth */
	private boolean er;
	private String deliveryType;
	private String amtPitocin;
	private String amtNitrous;
	private String amtPethidine;
	private String amtEpidural;
	private String amtMagnesium;
	private String amtRH;
	
	/** Fields unique for baby*/
	private boolean estimateDate;
	private java.util.Date babyDate;
	private String time;
	private String timeHour;
	private String timeMinute;
	private String timeMerridean;
	private char sex;
	// id of the baby
	private int multiNum;
	
	/** */
	//The number of babies in the childbirth visit
	private int numBabies;
	//the id of the selected baby
	private int babyId;
	//are we editing a baby?
	private boolean editBaby;

	public ChildbirthVisitForm() {
		this(null);
	}
	
	public ChildbirthVisitForm(ChildbirthVisitController cvc) {
		try {
			controller = (cvc == null) ? new ChildbirthVisitController() : cvc;
			baby = new Baby();
			cb = controller.getSelectedVisit();// ? null : new ObstetricsOfficeVisit();
			if ( cb == null )
				cb = new Childbirth();
			childbirthID = cb.getChildbirthId();
			pid = cb.getPid();// ? null : 
			if ( pid == 0 )
				pid = SessionUtils.getInstance().getCurrentPatientMIDLong();
			date = cb.getDate();
			deliveryType = cb.getDeliveryType();
			amtPitocin = cb.getAmtPitocin();
			amtNitrous =cb.getAmtNitrous();
			amtPethidine = cb.getAmtPethidine();
			amtEpidural = cb.getAmtEpidural();
			amtMagnesium = cb.getAmtMagnesium();
			amtRH = cb.getAmtRH();
			List<Baby> b = getBabies();
			numBabies = (b == null) ? 0 : b.size();
			editBaby = false;
			timeHour = "12";
			timeMinute = "00";
			timeMerridean = "am";
		} catch (Exception e) {
//			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obsetrics Visit Controller Error",
//					"Obstetrics Visit Controller Error");
//			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_ERROR, "Obsetrics Visit Controller Error", "Obsetrics Visit Controller Error", null );
//					"Obstetrics Visit Controller Error" );
		}
	}
	
	/**
	 * Constructor used for testing
	 */
	public ChildbirthVisitForm(ChildbirthVisitController cvc, SessionUtils utils) {
		try {
			controller = (cvc == null) ? new ChildbirthVisitController() : cvc;
			baby = new Baby();
			cb = controller.getSelectedVisit();// ? null : new ObstetricsOfficeVisit();
			if ( cb == null )
				cb = new Childbirth();
			childbirthID = cb.getChildbirthId();
			pid = cb.getPid();// ? null : 
			if ( pid == 0 )
				pid = utils.getCurrentPatientMIDLong();
			date = cb.getDate();
			deliveryType = cb.getDeliveryType();
			amtPitocin = cb.getAmtPitocin();
			amtNitrous =cb.getAmtNitrous();
			amtPethidine = cb.getAmtPethidine();
			amtEpidural = cb.getAmtEpidural();
			amtMagnesium = cb.getAmtMagnesium();
			amtRH = cb.getAmtRH();
			timeHour = "12";
			timeMinute = "00";
			timeMerridean = "am";
		} catch ( Exception e ) {
			//do nothing
		}
	}
	
	/**
	 * return Whether we are editing\
	 */

	/**
	 * @return the pid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(long pid) {
		this.pid = pid;
	}

	/**
	 * @return the childbirthID
	 */
	public long getChildbirthID() {
		return childbirthID;
	}

	/**
	 * @param childbirthID the childbirthID to set
	 */
	public void setChildbirthID(long childbirthID) {
		this.childbirthID = childbirthID;
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
	 * @return the er
	 */
	public boolean isEr() {
		return er;
	}

	/**
	 * @param er the er to set
	 */
	public void setEr(boolean er) {
		this.er = er;
	}

	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	/**
	 * @return the amtPitocin
	 */
	public String getAmtPitocin() {
		return amtPitocin;
	}

	/**
	 * @param amtPitocin the amtPitocin to set
	 */
	public void setAmtPitocin(String amtPitocin) {
		this.amtPitocin = amtPitocin;
	}

	/**
	 * @return the amtNitrous
	 */
	public String getAmtNitrous() {
		return amtNitrous;
	}

	/**
	 * @param amtNitrous the amtNitrous to set
	 */
	public void setAmtNitrous(String amtNitrous) {
		this.amtNitrous = amtNitrous;
	}

	/**
	 * @return the amtPethidine
	 */
	public String getAmtPethidine() {
		return amtPethidine;
	}

	/**
	 * @param amtPethidine the amtPethidine to set
	 */
	public void setAmtPethidine(String amtPethidine) {
		this.amtPethidine = amtPethidine;
	}

	/**
	 * @return the amtEpidural
	 */
	public String getAmtEpidural() {
		return amtEpidural;
	}

	/**
	 * @param amtEpidural the amtEpidural to set
	 */
	public void setAmtEpidural(String amtEpidural) {
		this.amtEpidural = amtEpidural;
	}

	/**
	 * @return the amtMagnesium
	 */
	public String getAmtMagnesium() {
		return amtMagnesium;
	}

	/**
	 * @param amtMagnesium the amtMagnesium to set
	 */
	public void setAmtMagnesium(String amtMagnesium) {
		this.amtMagnesium = amtMagnesium;
	}
	

	/**
	 * @return the amtRH
	 */
	public String getAmtRH() {
		return amtRH;
	}

	/**
	 * @param amtRH the amtRH to set
	 */
	public void setAmtRH(String amtRH) {
		this.amtRH = amtRH;
	}

	/**
	 * @return the estimateDate
	 */
	public boolean isEstimateDate() {
		return estimateDate;
	}

	/**
	 * @param estimateDate the estimateDate to set
	 */
	public void setEstimateDate(boolean estimateDate) {
		this.estimateDate = estimateDate;
	}

	/**
	 * @return the baby date
	 */
	public java.util.Date getBabyDate() {
		return babyDate;
	}
	
	/**
	 * @param babyDate the date to set the baby to
	 */
	public void setBabyDate(java.util.Date babyDate) {
		this.babyDate = babyDate;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime() {
		this.time = timeHour + ":" + timeMinute + " " + timeMerridean;
	}
	
	/**
	 * @return the hours part of time
	 */
	public String getTimeHour() {
		return timeHour;
	}
	
	/**
	 * @param timeHour the hours part of time to set
	 */
	public void setTimeHour(String timeHour) {
		this.timeHour = timeHour;
		setTime();
	}
	
	/**
	 * @return the minutes part of time
	 */
	public String getTimeMinute() {
		return timeMinute;
	}
	
	/**
	 * @param timeMinute the minutes part of time to set
	 */
	public void setTimeMinute(String timeMinute) {
		this.timeMinute = timeMinute;
		setTime();
	}
	
	/**
	 * @return the merridean par of time
	 */
	public String getTimeMerridean() {
		return timeMerridean;
	}
	
	/**
	 * @param timeMerridean the merridean part of time to set
	 */
	public void setTimeMerridean(String timeMerridean) {
		this.timeMerridean = timeMerridean;
		setTime();
	}

	/**
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}

	/**
	 * @return the multiNum
	 */
	public int getMultiNum() {
		return multiNum;
	}

	/**
	 * @param multiNum the multiNum to set
	 */
	public void setMultiNum(int multiNum) {
		this.multiNum = multiNum;
	}

	/**
	 * @return the numBabies
	 */
	public int getNumBabies() {
		return numBabies;
	}

	/**
	 * @param numBabies the numBabies to set
	 */
	public void setNumBabies(int numBabies) {
		this.numBabies = numBabies;
	}

	/**
	 * @return the babyId
	 */
	public int getBabyId() {
		return babyId;
	}

	/**
	 * @param babyId the babyId to set
	 */
	public void setBabyId(int babyId) {
		this.babyId = babyId;
	}

	/**
	 * @return the editBaby
	 */
	public boolean isEditBaby() {
		return editBaby;
	}

	/**
	 * @param editBaby the editBaby to set
	 */
	public void setEditBaby(boolean editBaby) {
		this.editBaby = editBaby;
	}
	
	public List<Baby> getBabies() {
		return controller.getBabies( childbirthID );
	}
	
	public void editBabies() {
		Baby selected = controller.getBaby(childbirthID, babyId);
		
		estimateDate = selected.getEstimateDate();
		String strBabyDate = selected.getDate();
		DateFormat babyDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			babyDate = babyDateFormat.parse(strBabyDate);
			time = selected.getTime();
			DateFormat babyTimeFormat = new SimpleDateFormat("hh:mm a");
			java.util.Date tempTime = babyTimeFormat.parse(time);
			Calendar tempCalTime = Calendar.getInstance();
			tempCalTime.setTime(tempTime);
			String tempHour = String.valueOf(tempCalTime.get(Calendar.HOUR));
			if (tempHour.equals("0")) {
				tempHour = "12";
			}
			if (tempHour.length() < 2) {
				tempHour = "0" + tempHour;
			}
			timeHour = tempHour;
			String tempMinute = String.valueOf(tempCalTime.get(Calendar.MINUTE));
			if (tempMinute.length() < 2) {
				tempMinute = "0" + tempMinute;
			}
			timeMinute = tempMinute;
			if (tempCalTime.get(Calendar.AM_PM) == 0) {
				timeMerridean = "am";
			} else {
				timeMerridean = "pm";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			timeHour = "12";
			timeMinute = "00";
			timeMerridean = "am";
		}
		sex = selected.getSex();
		multiNum = selected.getMultiNum();
		editBaby = true;
	}
	
	public void logViewChildbirthVisit() {
		if(childbirthID != 0) {
//			controller.logViewChildbirthVisit( childbirthID );
		}
		
	}
	
	public void setErString( String strEr ) {
		setEr( Boolean.parseBoolean( strEr ) );
	}
	
	/**
	 * called when user submits childbirth visit information for both emergency and normal visits
	 */
	public void submitChildbirthVisit(){
		cb.setAmtEpidural(amtEpidural);
		cb.setAmtMagnesium(amtMagnesium);
		cb.setAmtNitrous(amtNitrous);
		cb.setAmtPethidine(amtPethidine);
		cb.setAmtPitocin(amtPitocin);
		cb.setDeliveryType(deliveryType);
		cb.setAmtRH( amtRH );

		cb.setEr( er );
		cb.setPid( pid );
		if ( cb.getDate() == null ){
			cb.setDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
		}
		if ( childbirthID == 0 ){
			childbirthID = controller.addReturnGeneratedId( cb );
			cb.setChildbirthId(childbirthID);
		}else{
			controller.editChildbirth( cb );
		}
	}
	
	/**
	 * called when user submits baby information for both emergency and normal visits
	 */
	public void submitBaby(){
		if ( childbirthID == 0 ) {
//			SessionUtils.getInstance().printFacesMessage( FacesMessage.SEVERITY_INFO, "Enter General Information first.","Enter General Information first.", null );
			return;
		}
		
		baby.setChildbirthId(childbirthID);
		baby.setSex(sex);
		baby.setTime(time);
		if ( er )
			estimateDate = true;
		else
			estimateDate = false;
		baby.setEstimateDate(estimateDate);
		DateFormat babyDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		baby.setDate(babyDateFormat.format(babyDate));
		
		if( editBaby ) {
			baby.setMultiNum( babyId );
			controller.editBaby( baby );
			editBaby = false;
		} else {
			baby.setMultiNum( numBabies + 1 );
			controller.addBaby( baby );
			numBabies++;
		}
		
		babyDate = null;
		sex = 'm';
		time = "12:00 am";
		timeHour = "12";
		timeMinute = "00";
		timeMerridean = "am";
	}
}
