package edu.ncsu.csc.itrust.model.fitnessData;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;

import edu.ncsu.csc.itrust.exception.DBException;
/**
 * Java Bean class to access fitness 
 * data from UI
 * 
 * @author wyattmaxey
 */
@ManagedBean(name="fitness_data")
public class Fitness {
	/** patiet id */
	private String pid;
	
	/** Patient calories */
	private int calories;
	
	/** steps */
	private int steps;
	
	/** distance traveled */
	private double distance;
	
	/** floors climbed */
	private int floors;
	
	/** minutes sedentary */
	private int minsSed;
	
	/** minutes lightly active */
	private int minsLA;
	
	/** minutes fairly active */
	private int minsFA;
	
	/** minutes very active */
	private int minsVA;
	
	/** calories burned while active */
	private int activeCals;
	
	/** date of fitness data */
	private String date;
	
	private int hrLow;
	
	private int hrHigh;
	
	private int hrAvg;
	
	private int activeHours;
	
	private int uvExposure;
	
	public Fitness() {
		date = null;
		pid = null;
	}
	
	public int getHRLow() {
		return hrLow;
	}
	
	public void setHRLow(int hrLow) {
		this.hrLow = hrLow;
	}
	
	public int getHRHigh() {
		return this.hrHigh;
	}
	
	public void setHRHigh(int hrHigh) {
		this.hrHigh = hrHigh;
	}
	
	public int getHRAvg() {
		return this.hrAvg;
	}
	
	public void setHRAvg(int hrAvg) {
		this.hrAvg = hrAvg;
	}
	
	public void setActiveHours(int activeHours) {
		this.activeHours = activeHours;
	}
	
	public int getActiveHours() {
		return this.activeHours;
	}
	
	public void setUVExposure(int uvExposure) {
		this.uvExposure = uvExposure;
	}
	
	public int getUVExposure() {
		return this.uvExposure;
	}
	/**
	 * returns pid for fitness data
	 * 
	 * @return
	 * 		pid for fitness data
	 */
	public String getPid() {
		if ( pid == null )
			return "Not set";
		return pid;
	}
	
	public void setPid( String pid ) throws SQLException {
		try {
			Long.parseLong( pid );
			this.pid = pid;
			if ( pid == null ) {
				throw new SQLException( "Patient id must be numeric" );
			}
			
		} catch ( Exception e ) {
			throw new SQLException("Patient id must be numeric");
		}
	}

	/**
	 * returns Patient Calories
	 * 
	 * @return
	 * 		Patient calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * returns the patient's calories
	 * 
	 * @param calories
	 * 		Patient calories
	 * @throws SQLException 
	 */
	public void setCalories( int calories ) {
		this.calories = calories;
	}

	/**
	 * returns number of steps taken
	 * 
	 * @return
	 * 		steps taken
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * set value for steps taken
	 * 
	 * @param steps
	 * 		steps taken
	 * @throws SQLException 
	 */
	public void setSteps( int steps ) {
		this.steps = steps;
	}

	/**
	 * return distance traveled
	 * 
	 * @return
	 * 		distance traveled
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * set distance traveled
	 * 
	 * @param distance
	 * 		distance traveled
	 * @throws SQLException 
	 */
	public void setDistance( double distance ) {
		this.distance = distance;
	}

	/**
	 * return number of floors climbed
	 * 
	 * @return
	 * 		number of floors climbed
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * set number of floors climbed
	 * 
	 * @param floors
	 * 		number of floors climbed
	 * @throws SQLException 
	 */
	public void setFloors( int floors ) {
		this.floors = floors;
	}
	
	/**
	 * return minutes sedentary
	 * 
	 * @return
	 * 		minutes sedentary
	 */
	public int getMinsSed() {
		return minsSed;
	}

	/**
	 * set minutes sedentary
	 * 
	 * @param minsSed
	 * 		minutes sedentary
	 * @throws SQLException 
	 */
	public void setMinsSed( int minsSed ) {
		this.minsSed = minsSed;
	}

	/**
	 * return minutes lightly active
	 * 
	 * @return
	 * 		minutes lightly active
	 */
	public int getMinsLA() {
		return minsLA;
	}

	/**
	 * set minutes lightly active
	 * 
	 * @param minsLA
	 * 		minutes lightly active
	 * @throws SQLException 
	 */
	public void setMinsLA( int minsLA ) {
		this.minsLA = minsLA;
	}
	
	/**
	 * return minutes fairly active
	 * 
	 * @return
	 * 		minutes fairly active
	 */
	public int getMinsFA() {
		return minsFA;
	}

	/**
	 * set minutes fairly active
	 * 
	 * @param minsFA
	 * 		minutes fairly active
	 * @throws SQLException 
	 */
	public void setMinsFA( int minsFA ) {
		this.minsFA = minsFA;
	}

	/**
	 * return minutes very active
	 * 
	 * @return
	 * 		minutes very active
	 */
	public int getMinsVA() {
		return minsVA;
	}

	/**
	 * set minutes very active
	 * 
	 * @param minsVA
	 * 		minutes very active
	 * @throws SQLException 
	 */
	public void setMinsVA( int minsVA ) {
		this.minsVA = minsVA;
	}

	/**
	 * return active calories
	 * 
	 * @return
	 * 		active calories
	 */
	public int getActiveCals() {
		return activeCals;
	}

	/**
	 * set active calories
	 * 
	 * @param activeCals
	 * 		active calories
	 * @throws SQLException 
	 */
	public void setActiveCals( int activeCals ) {
		this.activeCals = activeCals;
	}

	/**
	 * return date of fitness data
	 * 
	 * @return
	 * 		date of fitness data
	 */
	public String getDate() {
		return date;
	}

	/**
	 * set date of fitness data
	 * 
	 * @param date
	 * 		date of fitness data
	 * @throws SQLException 
	 */
	public void setDate( String date ) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			sdf.parse(date);
		} catch(ParseException e) {
			throw new SQLException("Invalid Date");
		}
		this.date = date;
	}
}
