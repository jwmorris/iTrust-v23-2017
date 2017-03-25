/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.sql.Date;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;

/**
 * an obstetrics office visit
 * 
 * @author wyattmaxey
 *
 */
@ManagedBean(name="obstetrics_office_visit")
public class ObstetricsOfficeVisit {
	private long id;
	
	private long pid;
	
	private Date visitDate;
	
	private String weeksPregnant;
	
	private String weight;
	
	private String bp;
	
	private String fhr;
	
	private boolean multiplePregnancy;
	
	private String numBabies;
	
	private boolean lowLying;
	
	public ObstetricsOfficeVisit() {
		id = 0;
		pid = 0;
		visitDate = new Date( Calendar.getInstance().getTimeInMillis() );
		weeksPregnant = "";
		weight = "'";
		bp = "";
		fhr = "";
		multiplePregnancy = false;
		numBabies = "";
		lowLying = false;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId( long id ) {
		this.id = id;
	}
	
	public long getPid() {
		return pid;
	}
	
	public void setPid( long pid ) {
		this.pid = pid;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate( Date visitDate ) {
		this.visitDate = visitDate;
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
	public void setWeeksPregnant( String weeksPregnant ) {
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
	public void setWeight( String weight ) {
		this.weight = weight;
	}

	/**
	 * @return the bp
	 */
	public String getBp() {
		return bp;
	}

	/**
	 * @param bp the bp to set
	 */
	public void setBp( String bp ) {
		this.bp = bp;
	}

	/**
	 * @return the fhr
	 */
	public String getFhr() {
		return fhr;
	}

	/**
	 * @param fhr the fhr to set
	 */
	public void setFhr( String fhr ) {
		this.fhr = fhr;
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
	public void setMultiplePregnancy( boolean multiplePregnancy ) {
		this.multiplePregnancy = multiplePregnancy;
	}
	
	public String getNumBabies() {
		return numBabies;
	}
	
	public void setNumBabies( String numBabies ) {
		this.numBabies = numBabies;
	}

	/**
	 * @return the lowLying
	 */
	public boolean isLowLying() {
		return lowLying;
	}

	/**
	 * @param lowLying the lowLying to set
	 */
	public void setLowLying( boolean lowLying ) {
		this.lowLying = lowLying;
	}
	
}
