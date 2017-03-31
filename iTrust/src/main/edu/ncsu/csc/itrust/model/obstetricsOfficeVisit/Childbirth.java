package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import javax.faces.bean.ManagedBean;

/**
 * data bean for a childbirth event
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="childbirth")
public class Childbirth {
	private long pid;
	
	private long childbirthId;
	
	private boolean er;
	
	private String deliveryType;
	
	private boolean pitocin;
	
	private String amtPitocin;
	
	private boolean nitrous;
	
	private String amtNitrous;
	
	private boolean pethidine;
	
	private String amtPethidine;
	
	private boolean epidural;
	
	private String amtEpidural;
	
	private boolean magnesium;
	
	private String amtMagnesium;
	
	public Childbirth() {
		pid = 0;
		childbirthId = 0;
		er = false;
		deliveryType = "";
		pitocin = false;
		amtPitocin = "";
		nitrous = false;
		amtNitrous = "";
		pethidine = false;
		amtPethidine = "";
		epidural = false;
		amtEpidural = "";
		magnesium = false;
		amtMagnesium = "";
	}

	/**
	 * @return the pid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid( long pid ) {
		this.pid = pid;
	}

	/**
	 * @return the childbirthId
	 */
	public long getchildbirthId() {
		return childbirthId;
	}

	/**
	 * @param childbirthId the childbirthId to set
	 */
	public void setchildbirthId( long childbirthId ) {
		this.childbirthId = childbirthId;
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
	public void setEr( boolean er ) {
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
	public void setDeliveryType( String deliveryType ) {
		this.deliveryType = deliveryType;
	}

	/**
	 * @return the pitocin
	 */
	public boolean isPitocin() {
		return pitocin;
	}

	/**
	 * @param pitocin the pitocin to set
	 */
	public void setPitocin( boolean pitocin ) {
		this.pitocin = pitocin;
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
	public void setAmtPitocin( String amtPitocin ) {
		this.amtPitocin = amtPitocin;
	}

	/**
	 * @return the nitrous
	 */
	public boolean isNitrous() {
		return nitrous;
	}

	/**
	 * @param nitrous the nitrous to set
	 */
	public void setNitrous( boolean nitrous ) {
		this.nitrous = nitrous;
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
	public void setAmtNitrous( String amtNitrous ) {
		this.amtNitrous = amtNitrous;
	}

	/**
	 * @return the pethidine
	 */
	public boolean isPethidine() {
		return pethidine;
	}

	/**
	 * @param pethidine the pethidine to set
	 */
	public void setPethidine( boolean pethidine ) {
		this.pethidine = pethidine;
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
	public void setAmtPethidine( String amtPethidine ) {
		this.amtPethidine = amtPethidine;
	}

	/**
	 * @return the epidural
	 */
	public boolean isEpidural() {
		return epidural;
	}

	/**
	 * @param epidural the epidural to set
	 */
	public void setEpidural( boolean epidural ) {
		this.epidural = epidural;
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
	public void setAmtEpidural( String amtEpidural ) {
		this.amtEpidural = amtEpidural;
	}

	/**
	 * @return the magnesium
	 */
	public boolean isMagnesium() {
		return magnesium;
	}

	/**
	 * @param magnesium the magnesium to set
	 */
	public void setMagnesium( boolean magnesium ) {
		this.magnesium = magnesium;
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
	public void setAmtMagnesium( String amtMagnesium ) {
		this.amtMagnesium = amtMagnesium;
	}
	
}
