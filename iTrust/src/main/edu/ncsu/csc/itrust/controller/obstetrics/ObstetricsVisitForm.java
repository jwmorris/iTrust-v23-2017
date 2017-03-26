package edu.ncsu.csc.itrust.controller.obstetrics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.ultasound.Ultrasound;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "obstetrics_visit_form")
@ViewScoped
public class ObstetricsVisitForm {

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
		} catch (Exception e) {
			FacesMessage throwMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obsetrics Visit Controller Error",
					"Obstetrics Visit Controller Error");
			FacesContext.getCurrentInstance().addMessage(null, throwMsg);
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
	public void submitVisitInfo(){
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
		
		//fetus.setUltasoundId( controller.addUltrasound );
		controller.addFetus( fetus );
		
		// check to see if data is new or needs to be updated
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
		
		InputStream input = null;
		try {
			input = image.getInputStream();
			us.setPicPath( image.getSubmittedFileName() );
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

}
