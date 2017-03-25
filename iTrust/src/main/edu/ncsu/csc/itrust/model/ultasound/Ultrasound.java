/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

import java.sql.Date;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;

/**
 * an object for holding ultrasound data
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="ultrasound")
public class Ultrasound {
	private long id;
	
	private long ovId;
	
	private long pid;
	
	private Date dateCreated;
	
	private String picPath;
	
	public Ultrasound() {
		id = 0;
		ovId = 0;
		pid = 0;
		dateCreated = new Date( Calendar.getInstance().getTimeInMillis() );
		picPath = "";
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the ovId
	 */
	public long getOvId() {
		return ovId;
	}

	/**
	 * @param ovId the ovId to set
	 */
	public void setOvId(long ovId) {
		this.ovId = ovId;
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
	public void setPid(long pid) {
		this.pid = pid;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}
