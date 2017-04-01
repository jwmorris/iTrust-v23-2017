package edu.ncsu.csc.itrust.model.obstetrics;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;

/**
 * an obstetrics patient
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="obstetrics_pregnancy")
public class ObstetricsPregnancy {
	
	private long id;
	/** mid of the obstetrics patient */
	private long pid;

	/** date of patient initialization visit */
	private String dateInit;
	
	/** last menstrual period */
	private String lmp;
	
	/** estimated due date */
	private String edd;
	
	/** number of weeks pregnant */
	private String weeksPregnant;
	
	/** year of conception */
	private String concepYear;
	
	/** total weeks pregnant by delivery */
	private String totalWeeksPregnant;
	
	/** hours in labor */
	private String hoursLabor;
	
	/** weight gained during pregnancy */
	private String weightGain;
	
	/** delivery method */
	private String deliveryType;
	
	/** whether or not the pregnancy was a multiple */
	private boolean multiplePregnancy;
	
	/** number of children expected from pregnancy */
	private String babyCount;
	
	/** is this obstetrics patient currently pregnant */
	private boolean current;
	
	private boolean rhFlag;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public ObstetricsPregnancy() {
		pid = 0;
		dateInit = "";
		lmp = "";
		edd = "";
		weeksPregnant = "";
		concepYear = "";
		totalWeeksPregnant = "";
		hoursLabor = "0.0";
		weightGain = "";
		deliveryType = "";
		multiplePregnancy = false;
		babyCount = "";
		current = true;
		rhFlag = false;
		id = 0;
	}
	
	/**
	 * Return id
	 */
	public long getId() { return id; }
	public void setId( long id ) { this.id = id; }
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
	public String getDateInit() {
		return dateInit;
	}

	
	/**
	 * @param dateInitialization the dateInitialization to set
	 */
	public void setDateInit( String dateInitialization ) {
		this.dateInit = dateInitialization;
	}

	/**
	 * @return the lmp
	 */
	public String getLmp() {
		return lmp;
	}

	
	/**
	 * @param lmp the lmp to set
	 */
	public void setLmp( String lmp ) {
		try {
			DATE_FORMAT.parse(lmp);
		} catch (ParseException e) {
			lmp = "";
		}
		this.lmp = lmp;
		if(!lmp.equals("") && lmp != null) {
			System.out.println( "settingLMP" );
			this.edd = calculateEdd( lmp );
			this.weeksPregnant = calculateWeeksPreg( lmp );
		}
	}

	/**
	 * @return the edd
	 */
	public String getEdd() {
		return edd;
	}

	/**
	 * @param edd the edd to set
	 */
	public void setEdd() {
		if(!lmp.equals("") && lmp != null) {
			this.edd = calculateEdd( lmp );
		}
		
	}

	
	private String calculateEdd( String lmp ) {
		if ( lmp.equals("") || lmp == null)
			lmp = this.lmp;
		Date lmpDate = null;
		try {
			lmpDate = DATE_FORMAT.parse(lmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime( lmpDate );
		cal.add( Calendar.DAY_OF_YEAR, 280 );
		return DATE_FORMAT.format( new Date( cal.getTimeInMillis() ) );
		
		
	}
	
	private String calculateWeeksPreg( String lmp ) {
		Date lmpDate = null;
		if ( lmp.equals("") || lmp == null)
			lmp = this.lmp;
		try {
			lmpDate = DATE_FORMAT.parse( lmp );
		} catch( ParseException e ) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( lmpDate );
		Calendar cal2 = Calendar.getInstance();
		try {
			cal2.setTime( DATE_FORMAT.parse(this.dateInit) );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int w = cal2.get( Calendar.WEEK_OF_YEAR ) - cal.get( Calendar.WEEK_OF_YEAR );
		
		int days = ( cal2.get( Calendar.DAY_OF_YEAR ) - cal.get( Calendar.DAY_OF_YEAR) ) % w;
		StringBuilder sb = new StringBuilder();
		sb.append( Integer.toString( w ) );
		sb.append( "." );
		sb.append( Integer.toString( days ) );
		System.out.println("calc " + lmp);
		System.out.println("calc " + sb.toString() );
		return sb.toString();

	}

	/**
	 * @return the WeeksPregnant
	 */
	public String getWeeksPregnant() {
		return weeksPregnant;
	}

	/**
	 * @param weeksPregnant weeksPregnant to set
	 */
	public void setWeeksPregnant( ) {
		if(!lmp.equals("") && lmp != null) {
			this.weeksPregnant = calculateWeeksPreg( lmp );
		}
	}
	
	/**
	 * returns the year of conception
	 * 
	 * @return
	 * 		year of conception
	 */
	public String getConcepYear() {
		return concepYear;
	}
	
	/**
	 * sets the year of conception
	 * 
	 * @param concepYear
	 * 		year of conception
	 */
	public void setConcepYear( String concepYear ) {
		this.concepYear = concepYear;
	}
	
	/**
	 * return total weeks pregnant
	 * 
	 * @return
	 * 		total weeks pregnant
	 */
	public String getTotalWeeksPregnant() {
		return totalWeeksPregnant;
	}
	
	/**
	 * set the total weeks pregnant
	 * 
	 * @param totalWeeksPregnanat
	 * 		total weeks pregnant
	 */
	public void setTotalWeeksPregnant( String totalWeeksPregnanat ) {
		this.totalWeeksPregnant = totalWeeksPregnanat;
	}
	
	/**
	 * returns the hours in labor
	 * 
	 * @return
	 * 		hours in labor
	 */
	public String getHoursLabor() {
		return hoursLabor;
	}
	
	/**
	 * sets the hours in labor
	 * 
	 * @param hrsLabor
	 * 		hours in labor
	 */
	public void setHoursLabor( String hrsLabor ) {
		this.hoursLabor = hrsLabor;
	}
	
	/**
	 * return the weight gained during pregnancy
	 * 
	 * @return
	 * 		weight gained during pregnancy
	 */
	public String getWeightGain() {
		return weightGain;
	}
	
	/**
	 * set the weight gained during pregnancy
	 * 
	 * @param weightGain
	 * 		weight gained during pregnancy
	 */
	public void setWeightGain( String weightGain ) {
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
	public String getBabyCount() {
		return babyCount;
	}
	
	/**
	 * set expected chidlren form pregnancy
	 * 
	 * @param babyCount
	 * 		expected children from pregnancy
	 */
	public void setBabyCount( String babyCount ) {
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
	
	public boolean getrhFlag() {
		return rhFlag;
	}
	
	public void setrhFlag( boolean rhFlag ) {
		this.rhFlag = rhFlag;
	}
	
	@Override
	public boolean equals(Object o) {
		ObstetricsPregnancy op = (ObstetricsPregnancy) o;
		return this.babyCount.equals(op.getBabyCount())
				&& this.current == op.getCurrent()
				&& this.rhFlag == op.getrhFlag()
				&& this.multiplePregnancy == op.getMultiplePregnancy()
				&& this.concepYear.equals(op.getConcepYear())
				&& this.dateInit.equals(op.getDateInit())
				&& this.deliveryType.equals(op.getDeliveryType())
				&& this.edd.equals(op.getEdd())
				&& this.hoursLabor == op.getHoursLabor()
				&& this.lmp.equals(op.getLmp())
				&& this.pid == op.getPid()
				&& this.totalWeeksPregnant.equals(op.getTotalWeeksPregnant())
				&& this.weeksPregnant.equals(op.getWeeksPregnant())
				&& this.weightGain.equals(op.getWeightGain());
	}
}
