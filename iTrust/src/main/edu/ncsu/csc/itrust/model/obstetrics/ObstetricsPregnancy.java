package edu.ncsu.csc.itrust.model.obstetrics;

import java.sql.Date;

import javax.faces.bean.ManagedBean;

/**
 * an obstetrics patient
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="obstetrics_pregnancy")
public class ObstetricsPregnancy {
	/** mid of the obstetrics patient */
	private long pid;

	/** date of patient initialization visit */
	private Date dateInitialization;
	
	/** last menstrual period */
	private Date lmp;
	
	/** estimated due date */
	private Date edd;
	
	/** number of weeks pregnant */
	private int expectedWeeksPregnant;
	
	/** is this obstetrics patient currently pregnant */
	private boolean current;
	
	public ObstetricsPregnancy() {
		pid = 0;
		dateInitialization = null;
		lmp = null;
		edd = null;
		expectedWeeksPregnant = 0;
		current = true;
	}
	
	/**
	 * @return the patientMid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * @param patientMid the patientMid to set
	 */
	public void setPid( long pid ) {
		this.pid = pid;
	}

	/**
	 * @return the dateInitialization
	 */
	public Date getDateInit() {
		return dateInitialization;
	}

	/**
	 * @param dateInitialization the dateInitialization to set
	 */
	public void setDateInit( Date dateInitialization ) {
		this.dateInitialization = dateInitialization;
	}

	/**
	 * @return the lmp
	 */
	public Date getLmp() {
		return lmp;
	}

	/**
	 * @param lmp the lmp to set
	 */
	public void setLmp( Date lmp ) {
		this.lmp = lmp;
	}

	/**
	 * @return the edd
	 */
	public Date getEdd() {
		return edd;
	}

	/**
	 * @param edd the edd to set
	 */
	public void setEdd( Date edd ) {
		this.edd = edd;
	}

	/**
	 * @return the expectedWeeksPregnant
	 */
	public int getExpectedWeeksPregnant() {
		return expectedWeeksPregnant;
	}

	/**
	 * @param expectedWeeksPregnant the expectedWeeksPregnant to set
	 */
	public void setExpectedWeeksPregnant( int expectedWeeksPregnant ) {
		this.expectedWeeksPregnant = expectedWeeksPregnant;
	}
	
	/**
	 * @return if patient is currently pregnant
	 */
	public boolean getCurrent() {
		return current;
	}
	
	/**
	 * @param current set if patient is currently pregnant
	 */
	public void setCurrent( boolean current ) {
		this.current = current;
	}
}
