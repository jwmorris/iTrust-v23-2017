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
	private Date dateInit;
	
	/** last menstrual period */
	private Date lmp;
	
	/** estimated due date */
	private Date edd;
	
	/** number of weeks pregnant */
	private int expectedWeeksPregnant;
	
	/** year of conception */
	private int concepYear;
	
	/** total weeks pregnant by delivery */
	private int totalWeeksPregnant;
	
	/** hours in labor */
	private double hrsLabor;
	
	/** weight gained during pregnancy */
	private int weightGain;
	
	/** delivery method */
	private String deliveryType;
	
	/** whether or not the pregnancy was a multiple */
	private boolean multiplePregnancy;
	
	/** number of children expected from pregnancy */
	private int babyCount;
	
	/** is this obstetrics patient currently pregnant */
	private boolean current;
	
	public ObstetricsPregnancy() {
		pid = 0;
		dateInit = null;
		lmp = null;
		edd = null;
		expectedWeeksPregnant = 0;
		concepYear = 0;
		totalWeeksPregnant = 0;
		hrsLabor = 0;
		weightGain = 0;
		deliveryType = null;
		multiplePregnancy = false;
		babyCount = 0;
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
		return dateInit;
	}

	/**
	 * @param dateInitialization the dateInitialization to set
	 */
	public void setDateInit( Date dateInitialization ) {
		this.dateInit = dateInitialization;
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
	 * returns the year of conception
	 * 
	 * @return
	 * 		year of conception
	 */
	public int getConcepYear() {
		return concepYear;
	}
	
	/**
	 * sets the year of conception
	 * 
	 * @param concepYear
	 * 		year of conception
	 */
	public void setConcepYear( int concepYear ) {
		this.concepYear = concepYear;
	}
	
	/**
	 * return total weeks pregnant
	 * 
	 * @return
	 * 		total weeks pregnant
	 */
	public int getTotalWeeksPregnant() {
		return totalWeeksPregnant;
	}
	
	/**
	 * set the total weeks pregnant
	 * 
	 * @param totalWeeksPregnanat
	 * 		total weeks pregnant
	 */
	public void setTotalWeeksPregnant( int totalWeeksPregnanat ) {
		this.totalWeeksPregnant = totalWeeksPregnanat;
	}
	
	/**
	 * returns the hours in labor
	 * 
	 * @return
	 * 		hours in labor
	 */
	public double getHrsLabor() {
		return hrsLabor;
	}
	
	/**
	 * sets the hours in labor
	 * 
	 * @param hrsLabor
	 * 		hours in labor
	 */
	public void setHrsLabor( double hrsLabor ) {
		this.hrsLabor = hrsLabor;
	}
	
	/**
	 * return the weight gained during pregnancy
	 * 
	 * @return
	 * 		weight gained during pregnancy
	 */
	public int getWeightGain() {
		return weightGain;
	}
	
	/**
	 * set the weight gained during pregnancy
	 * 
	 * @param weightGain
	 * 		weight gained during pregnancy
	 */
	public void setWeightGain( int weightGain ) {
		this.weightGain = weightGain;
	}
	
	/**
	 * return the delivery method used
	 * 
	 * @return
	 * 		delivery method used
	 */
	public String getDeliveryType() {
		return deliveryType;
	}
	
	/**
	 * sets the delivery method used
	 * 
	 * @param deliveryType
	 * 		delivery method used
	 */
	public void setDeliveryType( String deliveryType ) {
		this.deliveryType = deliveryType;
	}
	
	/**
	 * returns whether of not this is a multiple pregnancy
	 * 
	 * @return
	 * 		true if this is a multiple pregnancy
	 * 		false otherwise
	 */
	public boolean getMultiplePregnancy() {
		return multiplePregnancy;
	}
	
	/**
	 * sets the multiple pregnancies boolean values
	 * 
	 * @param multiplePregnancies
	 * 		whether or not this is a multiple pregnancy
	 */
	public void setMultiplePregnancy( boolean multiplePregnancy ) {
		this.multiplePregnancy = multiplePregnancy;
	}
	
	/**
	 * expected children from pregnancy
	 * 
	 * @return
	 * 		expected children from pregnancy
	 */
	public int getBabyCount() {
		return babyCount;
	}
	
	/**
	 * set expected chidlren form pregnancy
	 * 
	 * @param babyCount
	 * 		expected children from pregnancy
	 */
	public void setBabyCount( int babyCount ) {
		this.babyCount = babyCount;
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
