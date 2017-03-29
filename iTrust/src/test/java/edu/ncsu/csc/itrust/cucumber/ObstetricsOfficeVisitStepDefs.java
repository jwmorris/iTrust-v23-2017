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
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancyData;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PersonnelDAO;

public class ObstetricsOfficeVisitStepDefs {
	private DataSource ds;
	private PatientDataShared patientData;
	private ObstetricsOfficeVisitMySQL oovData;
	private ObstetricsController oc;
	private ObstetricsVisitForm ovf;
	private ObstetricsVisitController ovc;
	private DAOFactory factory;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	private ObstetricsPregnancyData sql;

		public ObstetricsOfficeVisitStepDefs(PatientDataShared currentPatient){
			try {
				sql = new ObstetricsMySQL();
			} catch (DBException e) {
				e.printStackTrace();
				
			}
			this.factory = DAOFactory.getProductionInstance();
			this.ds =ConverterDAO.getDataSource();
			this.patientData = currentPatient;
			this.patientDAO = factory.getPatientDAO();
			this.personnelDAO = factory.getPersonnelDAO();
			this.oovData = new ObstetricsOfficeVisitMySQL(ds);
			this.oc = new ObstetricsController();
			this.ovf = new ObstetricsVisitForm();
			this.ovc = new ObstetricsVisitController(ds);
		}
		
		@Given("^Kelly Doctor enters MID 9000000021$")
		public void choose_invalid_patient(){
			Long id = 9000000021L;
			if((id <=0L) ||(id > 8999999999L) ){
				patientData.patientID = id;
				//maybe test
			} else {
				Assert.fail("MID should be incorrect");
			}
			
		}
		@Given("^Kathyrn Evans selects MID 42$")
		public void choose_wrong_patient(){
			Long id = 0000000042L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		}
		@Given("^Kathyrn Evans selects MID 5$")
		public void choose_nonObstetrics_patient(){
			Long id = 0000000005L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		}
		@Given("^Kathyrn Evans selects Princess Peach's PID$")
		public void choose_initilization_patient(){			
			Long id = 0000000021L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		}
		@Given("^Kathyrn Evans selects Andy Programmer's PID$")
		public void choose_rh_patient(){
			Long id = 0000000002L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		}
		@Given("^Kathyrn Evans selects Care Needs' PID$")
		public void choose_nonCalendar_patient(){
			Long id = 0000000003L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
		
		}
		@Given("^Kathyrn Evans selects Random Person's PID$")
		public void choose_childbirth_patient(){
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
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
			//document ultrasound
			//make ultrsaound object
			//Ultrasound us = new Ultrasound();
		}
		
		@And("^she correctly changes to Random Person's PID$")
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
		}
		
		@And("^Programmer has their RH - Flag set$")
		public void set_programmer_rh() {
			//set rh
			//sql.getCurrentObstetricsPregnancy(patientData.patientID).setrhFlag();
		}
		
		@When("^she correctly enters Random Person's PID$")
		public void choose_person_pid() {
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patientData.patientID = id;
			}
			
		}
		
		@When("^saves those records and sets Person's next appointment to July 4th$")
		public void set_holiday_appointment() {
			
		}
		
		@When("^Dr. Evans edits the documented visit$")
		public void edit_visit() {
			//edit and check to make sure of edit
		}
		
		@When("^Dr. Evans initilizes Princess Peach$")
		public void initilize_2nd_pregnancy() {
			//initialize and make sure is current
		}
		
		@When("Programmer has their 29 weeks pregnant visit$")
		public void add_28_week_visit() {
			//set weeks preg to 28 and see that rh flag boolean visit
		}
		
		@When("Dr. Evans tries to set Needs' next appointment$")
		public void set_noCalendar_appointment() {
			//try to schedule next appointment, should not enter a legit account/fake should not work
		}
		
		@When("Dr. Evans tries to set Person's next appointment at 42 weeks$")
		public void set_late_preg_appointment() {
			//set next appointment defaults to childbirth visit
		}
		
		@Then("^Dr. Doctor is prompted to create a regualr office visit.$")
		public void catch_hcp_status() {
			try {	
				Assert.assertFalse(personnelDAO.getPersonnel(9000000001L).getSpecialty().equals("OB/GYN"));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Then("^Random Person's next appointment is moved to July 5th$")
		public void day_after_holiday() {
			//set date so next scheduled appointment will be July 4th, appointment should change to july 5th
		}
		
		@Then("^Random Person's visit info is changed$")
		public void visit_info_edited() {
			//info is edited
		}
		
		@Then("^Princess Peach's displayed intiliazation is the current one.$")
		public void check_initilization() {
			//current should be the newest initialization
		}
		
		@Then("^a notice is displayed that they need an RH immune globulin shot$")
		public void rh_shot_needed() {
			//rh shot needed
			Assert.assertTrue(ovc.isRHChecked());
		}
		
		@Then("^Care Needs' calendar cannot be accessed and their appointment is July 20th$")
		public void calendar_not_accessed() {
			//can't be found set for next week same date and time
		}
		
		@Then("^Random Person's next appiontment is a Childbirth Hospital Visit$")
		public void childbirth_time() {
			//appointment for childbirth
		}		
}