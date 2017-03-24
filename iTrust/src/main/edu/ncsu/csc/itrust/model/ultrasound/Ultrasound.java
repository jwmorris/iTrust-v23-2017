/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultrasound;

import javax.faces.bean.ManagedBean;

/**
 * an object for holding ultrasound data
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="ultasound")
public class Ultrasound {
	/** patient mid */
	private long pid;
	
	/** date ultrasound record was created */
	private String dateCreated;
	
	/** crown rump length */
	private float crl;
	
	/** biparietal diameter */
	private float bpd;
	
	/** head circumference */
	private float hc;
	
	/** femur length */
	private float fl;
	
	/** occipiofrontal diameter */
	private float ofd;
	
	/** abdominal circumference */
	private float ac;
	
	/** humerus length */
	private float hl;
	
	/** estimated fetal weight */
	private float efw;

	/** path to ultrasound image */
	private String picturePath;
	
	/** used if there is more than 1 child expected */
	private int multiNum;
	
	public Ultrasound() {
		pid = 0;
		dateCreated = "";
		crl = 0;
		bpd = 0;
		hc = 0;
		fl = 0;
		ofd = 0;
		ac = 0;
		hl = 0;
		efw = 0;
		picturePath = "";
		multiNum = 0;
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
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the crl
	 */
	public float getCrl() {
		return crl;
	}

	/**
	 * @param crl the crl to set
	 */
	public void setCrl(float crl) {
		this.crl = crl;
	}

	/**
	 * @return the bpd
	 */
	public float getBpd() {
		return bpd;
	}

	/**
	 * @param bpd the bpd to set
	 */
	public void setBpd(float bpd) {
		this.bpd = bpd;
	}

	/**
	 * @return the hc
	 */
	public float getHc() {
		return hc;
	}

	/**
	 * @param hc the hc to set
	 */
	public void setHc(float hc) {
		this.hc = hc;
	}

	/**
	 * @return the fl
	 */
	public float getFl() {
		return fl;
	}

	/**
	 * @param fl the fl to set
	 */
	public void setFl(float fl) {
		this.fl = fl;
	}

	/**
	 * @return the ofd
	 */
	public float getOfd() {
		return ofd;
	}

	/**
	 * @param ofd the ofd to set
	 */
	public void setOfd(float ofd) {
		this.ofd = ofd;
	}

	/**
	 * @return the ac
	 */
	public float getAc() {
		return ac;
	}

	/**
	 * @param ac the ac to set
	 */
	public void setAc(float ac) {
		this.ac = ac;
	}

	/**
	 * @return the hl
	 */
	public float getHl() {
		return hl;
	}

	/**
	 * @param hl the hl to set
	 */
	public void setHl(float hl) {
		this.hl = hl;
	}

	/**
	 * @return the efw
	 */
	public float getEfw() {
		return efw;
	}

	/**
	 * @param efw the efw to set
	 */
	public void setEfw(float efw) {
		this.efw = efw;
	}

	/**
	 * @return the picturePath
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * @param picturePath the picturePath to set
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public int getMultiNum() {
		return multiNum;
	}
	
	public void setMultiNum( int multiNum ) {
		this.multiNum = multiNum;
	}
	
}
