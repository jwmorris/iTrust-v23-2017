/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

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
	
	private String visitDate;
	
	private String weeksPregnant;
	
	private int weight;
	
	private int bp;
	
	private int fhr;
	
	private boolean multiplePregnancy;
	
	private int numBabies;
	
	private boolean lowLying;
	
	public ObstetricsOfficeVisit() {
		id = 0;
		pid = 0;
		visitDate = "";
		weeksPregnant = "";
		weight = 0;
		bp = 0;
		fhr = 0;
		multiplePregnancy = false;
		numBabies = 0;
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
	public String getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(String visitDate) {
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
	public void setWeeksPregnant(String weeksPregnant) {
		this.weeksPregnant = weeksPregnant;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the bp
	 */
	public int getBp() {
		return bp;
	}

	/**
	 * @param bp the bp to set
	 */
	public void setBp(int bp) {
		this.bp = bp;
	}

	/**
	 * @return the fhr
	 */
	public int getFhr() {
		return fhr;
	}

	/**
	 * @param fhr the fhr to set
	 */
	public void setFhr(int fhr) {
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
	public void setMultiplePregnancy(boolean multiplePregnancy) {
		this.multiplePregnancy = multiplePregnancy;
	}
	
	public int getNumBabies() {
		return numBabies;
	}
	
	public void setNumBabies( int numBabies ) {
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
	public void setLowLying(boolean lowLying) {
		this.lowLying = lowLying;
	}
	
}
