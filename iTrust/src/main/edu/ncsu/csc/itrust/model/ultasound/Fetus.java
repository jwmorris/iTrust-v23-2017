/**
 * 
 */
package edu.ncsu.csc.itrust.model.ultasound;

import javax.faces.bean.ManagedBean;

/**
 * an object for holding fetus data
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="fetus")
public class Fetus {
	/** patient mid */
	private long ovId;
	
	/** crown rump length */
	private String crl;
	
	/** biparietal diameter */
	private String bpd;
	
	/** head circumference */
	private String hc;
	
	/** femur length */
	private String fl;
	
	/** occipiofrontal diameter */
	private String ofd;
	
	/** abdominal circumference */
	private String ac;
	
	/** humerus length */
	private String hl;
	
	/** estimated fetal weight */
	private String efw;
	
	/** used if there is more than 1 child expected */
	private int multiNum;
	
	public Fetus() {
		ovId = 0;
		crl = "";
		bpd = "";
		hc = "";
		fl = "";
		ofd = "";
		ac = "";
		hl = "";
		efw = "";
		multiNum = 0;
	}

	/**
	 * @return the pid
	 */
	public long getOvId() {
		return ovId;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setOvId( long ovId ) {
		this.ovId = ovId;
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
	public void setCrl( String crl ) {
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
	public void setBpd( String bpd ) {
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
	public void setHc( String hc ) {
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
	public void setFl( String fl ) {
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
	public void setOfd( String ofd ) {
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
	public void setAc( String ac ) {
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
	public void setHl( String hl ) {
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
	public void setEfw( String efw ) {
		this.efw = efw;
	}

	public int getMultiNum() {
		return multiNum;
	}
	
	public void setMultiNum( int multiNum ) {
		this.multiNum = multiNum;
	}
	
}
