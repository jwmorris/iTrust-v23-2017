package edu.ncsu.csc.itrust.cucumber;

import java.sql.Date;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitForm;
import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsMySQL;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.model.ultasound.Fetus;
import edu.ncsu.csc.itrust.model.user.patient.Patient;

public class ObstetricsOfficeVisitStepDefs {
	private DataSource ds;
	private PatientDataShared patientData;
	private PatientBean patient;
	private ObstetricsOfficeVisitMySQL oovData;
	private ObstetricsController oc;
	private ObstetricsVisitForm ovf;
	private ObstetricsVisitController ovc;
	private DAOFactory factory;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	private ObstetricsPregnancyData sql;
	private ObstetricsPregnancy newPreg;

		public ObstetricsOfficeVisitStepDefs(){
			try {
				sql = new ObstetricsMySQL();
			} catch (DBException e) {
				e.printStackTrace();
				
			}
			this.factory = DAOFactory.getProductionInstance();
			this.ds =ConverterDAO.getDataSource();
			//this.patientData = currentPatient;
			this.patientDAO = factory.getPatientDAO();
			this.personnelDAO = factory.getPersonnelDAO();
			this.oovData = new ObstetricsOfficeVisitMySQL(ds);
			//this.oc = new ObstetricsController();
			this.ovf = new ObstetricsVisitForm();
			this.ovc = new ObstetricsVisitController(ds);
		}
		
		@Given("^Kelly Doctor enters PID 9000000021$")
		public void choose_invalid_patient() throws DBException{
			Long id = 9000000021L;
			if((id <=0L) ||(id > 8999999999L) ){
				 Assert.assertTrue(patientDAO.checkPatientExists(id));
				//maybe test
			} else {
				Assert.fail("MID should be incorrect");
			}
			
		}
		@Given("^Kathyrn Evans selects Bad Horses PID$")
		public void kathyrn_Evans_selects_Bad_Horses_PID() throws DBException{
			Long id = 0000000042L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				//patientData.patientID = id;
				patient = patientDAO.getPatient(id);
			}
		}
		@Given("^Kathyrn Evans selects Baby Programmers PID$")
		public void kathyrn_Evans_selects_Baby_Programmers_PID() throws DBException{
			Long id = 0000000005L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		}
		@Given("^Kathyrn Evans selects Princess Peachs PID$")
		public void kathyrn_Evans_selects_Princess_Peachs_PID() throws DBException{			
			Long id = 0000000021L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		}
//		@Given("^Kathyrn Evans selects Andy Programmer's PID$")
//		public void rh_patient(){
//			Long id = 0000000002L;
//			if((id <=0L) ||(id > 8999999999L) ){
//				Assert.fail("MID should be real");
//			} else {
//				patientData.patientID = id;
//			}
//		}
		@Given("^Kathyrn Evans selects Andy Programmers PID$")
		public void noCalendar_patient() throws DBException{
			Long id = 0000000002L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		
		}
		@Given("^Kathyrn Evans selects Random Persons PID$")
		public void choose_childbirth_patient() throws DBException{
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		}
		
		@And("^adds a documented visit$")
		public void document_visit() throws FormValidationException {
			//document visit
			//make visit object
			ObstetricsOfficeVisit input = new ObstetricsOfficeVisit();
			input.setBp("110/82");
			input.setFhr("110");
			input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(patientData.patientID);
			//input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("30");
			input.setWeight("150");
			try {
				oovData.add(input);
			} catch (DBException e) {
				
			}
			
		}
		
		@And("^adds ultrasound data$")
		public void ultrasound_visit() {
//			document ultrasound
//			make ultrsaound object
//			Ultrasound us = new Ultrasound();
//			Fetus fetus = new Fetus();
//			fetus.setAc(ac);
//			fetus.setBpd(bpd);
//			fetus.setCrl(crl);
//			fetus.setEfw(efw);
//			fetus.setFl(fl);
//			fetus.setHc(hc);
//			fetus.setHl(hl);
//			fetus.setMultiNum(multiNum);
//			fetus.setOfd();
//			fetus.setUltrasoundId(ultrasoundId);
//			oovData.addFetus(f);
		}
		
		@And("^she correctly changes to Random Persons PID$")
		public void change_to_person_pid() {
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		}
		
		@And("^Princess Peach had a prior pregnancy 20 weeks ago$")
		public void peach_prior_preg() {
			//initialize and make not current
			ObstetricsPregnancy op = new ObstetricsPregnancy();
			op.setCurrent(false);
		}
		
		@And("^Programmer has their RH Flag set$")
		public void set_programmer_rh() {
			//set rh
			//sql.getCurrentObstetricsPregnancy(patientData.patientID).setrhFlag();
		}
		
		@When("^she correctly enters Random Persons PID$")
		public void choose_person_pid() {
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
			
		}
		
		@When("^saves those records and sets Persons next appointment to Independence Day$")
		public void set_holiday_appointment() {
			
		}
		
		@When("^Dr Evans edits the documented visit$")
		public void edit_visit() throws FormValidationException, DBException {
			//edit and check to make sure of edit
			ObstetricsOfficeVisit input = new ObstetricsOfficeVisit();
			input.setBp("110/82");
			input.setFhr("110");
			input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(patientData.patientID);
			//input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("30");
			input.setWeight("150");
			try {
				oovData.add(input);
			} catch (DBException e) {
				
			}
			
			oovData.getByID(9).setWeeksPregnant("40");
			
		}
		
		@When("^Dr Evans initilizes Princess Peach$")
		public void initilize_2nd_pregnancy() {
			//initialize and make sure is current
			newPreg = new ObstetricsPregnancy();
		}
		
		@When("Programmer has enough weeks for an rh pregnant visit$")
		public void add_28_week_visit() throws FormValidationException {
			//set weeks preg to 28 and see that rh flag boolean visit
			ObstetricsOfficeVisit input = new ObstetricsOfficeVisit();
			input.setBp("110/82");
			input.setFhr("110");
			input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(patientData.patientID);
			//input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("29");
			input.setWeight("150");
			try {
				oovData.add(input);
			} catch (DBException e) {
				
			}
		}
		
		@When("Dr Evans tries to set Programmers next appointment$")
		public void set_noCalendar_appointment() {
			//try to schedule next appointment, should not enter a legit account/fake should not work
		}
		
		@When("Dr Evans tries to set Persons next appointment at max weeks$")
		public void set_late_preg_appointment() throws FormValidationException {
//			//set next appointment defaults to childbirth visit
//			ObstetricsOfficeVisit input = new ObstetricsOfficeVisit();
//			input.setBp("110/82");
//			input.setFhr("110");
//			input.setId(9);
//			input.setLowLying(false);
//			input.setMultiplePregnancy(false);
//			input.setNumBabies("1");
//			input.setPid(patientData.patientID);
//			//input.setVisitDate(new Date(05/04/17));
//			input.setWeeksPregnant("42");
//			input.setWeight("150");
//			try {
//				oovData.add(input);
//			} catch (DBException e) {
//				
//			}
		}
		
		@Then("^Dr Doctor is prompted to create a regualr office visit$")
		public void catch_hcp_status() {
			try {	
				Assert.assertFalse(personnelDAO.getPersonnel(9000000001L).getSpecialty().equals("OB/GYN"));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Then("^Random Persons next appointment is moved to next day$")
		public void day_after_holiday() {
			//set date so next scheduled appointment will be July 4th, appointment should change to july 5th
		}
		
		@Then("^Random Persons visit info is changed$")
		public void visit_info_edited() throws DBException {
			//info is edited
			Assert.assertEquals(oovData.getByID(9).getWeeksPregnant(),"40");
		}
		
		@Then("^Princess Peachs displayed intiliazation is the current one$")
		public void check_initilization() {
			//current should be the newest initialization
			Assert.assertTrue(newPreg.getCurrent());
		}
		
		@Then("^a notice is displayed that they need an RH immune globulin shot$")
		public void rh_shot_needed() {
			//rh shot needed
			Assert.assertTrue(ovc.isRHChecked());
		}
		
		@Then("^Programmers calendar cannot be accessed and their appointment is set for next week$")
		public void calendar_not_accessed() {
			//can't be found set for next week same date and time
		}
		
		@Then("^Random Persons next appiontment is a Childbirth Hospital Visit$")
		public void childbirth_time() {
			//appointment for childbirth
		}		
}