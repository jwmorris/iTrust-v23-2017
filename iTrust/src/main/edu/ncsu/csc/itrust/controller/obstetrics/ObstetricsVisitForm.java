package edu.ncsu.csc.itrust.controller.obstetrics;

import java.time.LocalDateTime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "obstetrics_visit_form")
@ViewScoped
public class ObstetricsVisitForm {

	private ObstetricsVisitController controller;
	// obstetrics office visit object here
	// pid of patient
	Long pid;
	// id of visit, might not need
	Long visitID;
	// date of visit
	private String date;
	// weeks pregnant at visit
	private String weeksPregnant;
	private String weight;
	private String bloodPressure;
	// fetal heart rate
	private String ftr;
	// multiple babies?
	private boolean multiplePregnancy;
	// low lyting placenta?
	private boolean placenta;
	
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
	
	// next appointment date
	private String next;
	
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
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
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
	public boolean isMultiplePregnancy() {
		return multiplePregnancy;
	}

	/**
	 * @param multiplePregnancy the multiplePregnancy to set
	 */
	public void setMultiplePregnancy(boolean multiplePregnancy) {
		this.multiplePregnancy = multiplePregnancy;
	}

	/**
	 * @return the placenta
	 */
	public boolean isPlacenta() {
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
	 * Called when user clicks on the submit button in obstetricsVisitInfo.xhtml. Takes data from form
	 * and sends to sql/loader/validator class
	 */
	public void submitVisitInfo(){
		
	}
	
	/**
	 * Called when user updates fetal data in obstetricsVisitInfo.xhtml. Takes data from form
	 * and sends to sql/loader/validator class
	 */
	public void submitFetusInfo(){
		
	}
}
