package edu.ncsu.csc.itrust.controller.obstetrics;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.controller.iTrustController;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ViewScoped
@ManagedBean( name="obstetrics_report" )
public class ObstetricsReport extends iTrustController {
	
	
	private List<ObstetricsPregnancy> priors;
	private List<ObstetricsOfficeVisit> officeVisits;
	private String edd;
	private List<AllergyBean> allergies;
	private List<Diagnosis> diagnoses;
	private ObstetricsReportController obc;
	private ObstetricsPregnancy selected;
	private String bloodType;
	private Complications complications;

	
	
	
	public ObstetricsReport () {
		this.obc = new ObstetricsReportController();
		this.selected = obc.getSelectedVisit();
		this.priors = obc.getPriorPregnancies();
		this.officeVisits = obc.getOfficeVisitsForVisit( selected.getId() );
		this.allergies = obc.getRelevantAllergies();
		this.diagnoses = obc.getRelevantDiagnoses();
		this.bloodType = obc.getCurrentPatientBloodType();
		this.edd = selected.getEdd();
		this.complications = new Complications();
		complications.rh = selected.getrhFlag();
		complications.allergies = hasAllergies();
		complications.hyperemesis = checkHyperemesis();
		complications.hypothyroidism = checkHypothyroidism();
		complications.preExisting = hasDiagnoses();
		complications.miscarriage = selected.gethpMiscarriage();
		if( officeVisits != null ) {
			for( int i = 0; i < officeVisits.size(); i++ ) {
				if( i == 0 ) {
					getComplicationsForVisit( null, officeVisits.get( i ) );
				} else {
					getComplicationsForVisit( officeVisits.get( i - 1 ), officeVisits.get( i ) );
				}
			}
		}
		obc.logViewReport();
		
	}
	
	public ObstetricsReport( DataSource ds, DAOFactory factory, SessionUtils utils, ObstetricsPregnancy selected ) {
		super(utils, null, factory);
		this.obc = new ObstetricsReportController( ds, factory, utils );
		this.selected = selected;
		this.priors = obc.getPriorPregnancies();
		this.officeVisits = obc.getOfficeVisitsForVisit( selected.getId() );
		this.allergies = obc.getRelevantAllergies();
		this.diagnoses = obc.getRelevantDiagnoses();
		this.bloodType = obc.getCurrentPatientBloodType();
		this.edd = selected.getEdd();
		this.complications = new Complications();
		complications.rh = selected.getrhFlag();
		complications.allergies = hasAllergies();
		complications.hyperemesis = checkHyperemesis();
		complications.hypothyroidism = checkHypothyroidism();
		complications.preExisting = hasDiagnoses();
		complications.miscarriage = selected.gethpMiscarriage();
		if( officeVisits != null ) {
			for( int i = 0; i < officeVisits.size(); i++ ) {
				if( i == 0 ) {
					getComplicationsForVisit( null, officeVisits.get( i ) );
				} else {
					getComplicationsForVisit( officeVisits.get( i - 1 ), officeVisits.get( i ) );
				}
			}
		}
		obc.logViewReport();
	}


	/**
	 * @return the priors
	 */
	public List<ObstetricsPregnancy> getPriors() { return priors; }


	/**
	 * @return the officeVisits
	 */
	public List<ObstetricsOfficeVisit> getOfficeVisits() { return officeVisits; }


	/**
	 * @return the edd
	 */
	public String getEdd() { return edd; }


	/**
	 * @return the allergies
	 */
	public List<AllergyBean> getAllergies() { return allergies; }


	/**
	 * @return the diagnoses
	 */
	public List<Diagnosis> getDiagnoses() { return diagnoses; }


	/**
	 * @return the selected
	 */
	public ObstetricsPregnancy getSelected() { return selected; }


	/**
	 * @return the bloodType
	 */
	public String getBloodType() { return bloodType; }
	
	public boolean hasAllergies() {
		return allergies != null && allergies.size() != 0;
	}
	
	public boolean hasDiagnoses() {
		return diagnoses != null && diagnoses.size() != 0;
	}
	
	public boolean hasOfficeVisits() {
		return officeVisits != null && officeVisits.size() != 0;
	}
	
	public boolean hasPriorPregnancies() {
		return priors != null && priors.size() != 0;
	}
	
	public Complications getComplications() {
		return complications;
	}
	
	public void getComplicationsForVisit( ObstetricsOfficeVisit pastVisit, ObstetricsOfficeVisit visit ) {
		complications.abnormalFhr = abnormalFhr( visit ) || complications.abnormalFhr;
		complications.abnormalWeightChange = abnormalWeight( pastVisit, visit ) || complications.abnormalWeightChange;
		complications.bp = checkBloodPressure( visit ) || complications.bp;
		complications.multiple = visit.isMultiplePregnancy() || complications.multiple;
		complications.old = calculateAge( visit ) || complications.old;
		complications.placenta = visit.isLowLying() || complications.placenta;
		
	}
	
	private boolean checkBloodPressure( ObstetricsOfficeVisit visit ) {
		if( visit.getBp().equals( "" ) || visit.getBp() == null ) {
			return false;
		}
		String bp = visit.getBp();
		String [] parts = bp.split( "/" );
		int first = Integer.parseInt( parts[0] );
		int second = Integer.parseInt( parts[1] );
		return ( first >= 140 || second >= 90 );
	}
	
	private boolean calculateAge( ObstetricsOfficeVisit visit ) {
		String dob = obc.getDOB();
		if( dob == null ) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
		Date birth = null;
		Date visitDate = visit.getVisitDate();
		try {
			birth = sdf.parse( dob );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTime( birth );
		Calendar visitCal = Calendar.getInstance();
		visitCal.setTime( visitDate );
		
		LocalDate bd = LocalDate.of( birthCal.get( Calendar.YEAR ), birthCal.get( Calendar.MONTH ), birthCal.get( Calendar.DAY_OF_MONTH ) );
		LocalDate vd = LocalDate.of( visitCal.get( Calendar.YEAR ), visitCal.get( Calendar.MONTH ), visitCal.get( Calendar.DAY_OF_MONTH ) );
		Period age = Period.between( bd, vd );
		int intAge = age.getYears();
		return intAge >= 35;
	}
	
	private boolean abnormalFhr( ObstetricsOfficeVisit visit ) {
		if( visit.getFhr().equals( "" ) || visit.getFhr() == null ) {
			return false;
		}
		return Integer.parseInt( visit.getFhr() ) > 160 || Integer.parseInt( visit.getFhr() ) < 120;
	}
	
	private boolean abnormalWeight( ObstetricsOfficeVisit pastVisit, ObstetricsOfficeVisit visit ) {
		if( pastVisit == null ) {
			return false;
		}
		if( pastVisit.getWeight().equals( "" ) || pastVisit.getWeight() == null 
				|| visit.getWeight().equals( "" ) || visit.getWeight() == null ) {
			return false;	
		}
		double difference = Double.parseDouble( visit.getWeight() ) - Double.parseDouble( pastVisit.getWeight() );
		return difference < 15 || difference > 35;
		
	}
	
	private boolean checkHypothyroidism() {
		List<Diagnosis> diag = obc.getRelevantDiagnoses();
		boolean ret = false;
		for(int i = 0; i < diag.size(); i++) {
			if(diag.get(i).getCode().equals("E03")) {
				ret = true;
			}
		}
		return ret;
	}
	
	private boolean checkHyperemesis() {
		List<Diagnosis> diag = obc.getRelevantDiagnoses();
		boolean ret = false;
		for(int i = 0; i < diag.size(); i++) {
			if(diag.get(i).getCode().equals("O21")) {
				ret = true;
			}
		}
		return ret;
	}
	
	public class Complications {
		private boolean rh;
		private boolean bp;
		private boolean old;
		private boolean preExisting;
		private boolean allergies;
		private boolean placenta;
		private boolean miscarriage;
		private boolean abnormalFhr;
		private boolean multiple;
		private boolean abnormalWeightChange;
		private boolean hyperemesis;
		private boolean hypothyroidism;
		
		public Complications() {
			boolean rh = false;
			boolean bp = false;
			boolean old = false;
			boolean preExisting = false;
			boolean allergies = false;
			boolean placenta = false;;
			boolean miscarriage = false;;
			boolean abnormalFhr = false;;
			boolean multiple = false;;
			boolean abnormalWeightChange = false;;
			boolean hyperemesis = false;;
			boolean hypothyroidism = false;;
		}
		/**
		 * @return the rh
		 */
		public boolean getRh() {
			return rh;
		}

		/**
		 * @return the bp
		 */
		public boolean getBp() {
			return bp;
		}

		/**
		 * @return the old
		 */
		public boolean getOld() {
			return old;
		}

		/**
		 * @return the preExisting
		 */
		public boolean getPreExisting() {
			return preExisting;
		}

		/**
		 * @return the allergies
		 */
		public boolean getAllergies() {
			return allergies;
		}

		/**
		 * @return the placenta
		 */
		public boolean getPlacenta() {
			return placenta;
		}

		/**
		 * @return the miscarriage
		 */
		public boolean getMiscarriage() {
			return miscarriage;
		}

		/**
		 * @return the abnormalFhr
		 */
		public boolean getAbnormalFhr() {
			return abnormalFhr;
		}

		/**
		 * @return the multiple
		 */
		public boolean getMultiple() {
			return multiple;
		}

		/**
		 * @return the abnormalWeightChange
		 */
		public boolean getAbnormalWeightChange() {
			return abnormalWeightChange;
		}

		/**
		 * @return the hyperemesis
		 */
		public boolean getHyperemesis() {
			return hyperemesis;
		}

		/**
		 * @return the hypothyroidism
		 */
		public boolean getHypothyroidism() {
			return hypothyroidism;
		}

	
	}
}
