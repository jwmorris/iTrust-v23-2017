/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

/**
 * baby data object, links with Childbirth
 * 
 * @author wyattmaxey
 */
public class Baby {
	private long childbirthId;
	
	private String date;
	
	private boolean estimateDate;
	
	private String time;
	
	private String deliveryType;
	
	private char sex;
	
	private int multiNum;
	
	private String name;
	
	public Baby() {
		childbirthId = 0;
		date = "";
		estimateDate = false;
		time = "";
		deliveryType = "";
		sex = 0;
		multiNum = 0;
		name = "";
	}

	/**
	 * @return the childbirthId
	 */
	public long getChildbirthId() {
		return childbirthId;
	}

	/**
	 * @param childbirthId the childbirthId to set
	 */
	public void setChildbirthId( long childbirthId ) {
		this.childbirthId = childbirthId;
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
	public void setDate( String date ) {
		this.date = date;
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
	public void setEstimateDate( boolean estimateDate ) {
		this.estimateDate = estimateDate;
	}

	/**
	 * @return returns estimate date
	 */
	public boolean getEstimateDate(){
		return this.estimateDate;
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
	public void setTime( String time ) {
		this.time = time;
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
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex( char sex ) {
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
