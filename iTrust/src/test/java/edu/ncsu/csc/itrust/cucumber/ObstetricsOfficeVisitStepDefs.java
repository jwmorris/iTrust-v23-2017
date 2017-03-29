package edu.ncsu.csc.itrust.cucumber;

import javax.sql.DataSource;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitForm;
import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitMySQL;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisitMySQL;

public class ObstetricsOfficeVisitStepDefs {
	private DataSource ds;
	private PatientDataShared patientData;
	private ObstetricsOfficeVisitMySQL oovData;
	private ObstetricsController oc;
	private ObstetricsVisitForm ovf;
	private ObstetricsVisitController ovc;

		public ObstetricsOfficeVisitStepDefs(PatientDataShared currentPatient){
			this.ds =ConverterDAO.getDataSource();
			this.patientData = currentPatient;
			this.oovData = new ObstetricsOfficeVisitMySQL(ds);
			this.oc = new ObstetricsController();
			this.ovf = new ObstetricsVisitForm();
			this.ovc = new ObstetricsVisitController(ds);
		}
		
		@Given("^Kelly Doctor enters PID 3001$")
		public void choose_invalid_patient(){
		
		}
		@Given("^Kathyrn Evans selects PID 52$")
		public void choose_wrong_patient(){
		
		}
		@Given("^Kathyrn Evans selects PID 5$")
		public void choose_nonObstetrics_patient(){
		
		}
		@Given("^Kathyrn Evans selects Princess Peach's PID$")
		public void choose_initilization_patient(){
		
		}
		@Given("^Kathyrn Evans selects Andy Programmer's PID$")
		public void choose_rh_patient(){
		
		}
		@Given("^Kathyrn Evans selects Care Needs' PID$")
		public void choose_nonCalendar_patient(){
		
		}
		@Given("^Kathyrn Evans selects Random Person's PID$")
		public void choose_childbirth_patient(){
		
		}
		
		@And("^she correctly changes to Random Person's PID$")
		public void choose_person_pid() {
			
		}
		
		@And("^adds a documented visit$")
		public void document_visit() {
			
		}
		
		@And("^adds a ultrasound data$")
		public void ultrasound_visit() {
			
		}
		
		@And("^she correctly changes to Random Person's PID$")
		public void change_to_person_pid() {
			
		}
		
		@And("^Princess Peach had a prior pregnancy 20 weeks ago$")
		public void peach_prior_preg() {
			
		}
		
		@And("^Programmer has their RH - Flag set$")
		public void set_programmer_rh() {
			
		}
		
		@When("^Dr. Doctor adds an obstetrics visit$")
		public void hcp_add_visit() {
			
		}
		
		@When("^saves those records and sets Person's next appointment to July 4th$")
		public void set_holiday_appointment() {
			
		}
		
		@When("^Dr. Evans edits the documented visit$")
		public void edit_visit() {
			
		}
		
		@When("^Dr. Evans initilizes Princess Peach$")
		public void initilize_2nd_pregnancy() {
			
		}
		
		@When("Programmer has her 28+ week visit$")
		public void add_28_week_visit() {
			
		}
		
		@When("Dr. Evans tries to set Needs' next appointment$")
		public void set_noCalendar_appointment() {
			
		}
		
		@When("Dr. Evans tries to set Person's next appointment at 42 weeks$")
		public void set_late_preg_appointment() {
			
		}
		
		@Then("^Dr. Doctor is prompted to created a regualr office visit.$")
		public void catch_hcp_status() {
			
		}
		
		@Then("^Random Person's next appointment is moved to July 5th$")
		public void day_after_holiday() {
			
		}
		
		@Then("^Random Person's visit info is changed$")
		public void visit_info_edited() {
			
		}
		
		@Then("^Princess Peach's displayed intilization is the current one.$")
		public void check_initilization() {
			
		}
		
		@Then("^a notice is displayed that they need an RH immune globulin shot$")
		public void rh_shot_needed() {
			
		}
		
		@Then("^Care Needs' calendar cannot be accessed and their appointment is July 20th$")
		public void calendar_not_accessed() {
			
		}
		
		@Then("^Random Person's next appiontment is a Childbirth Hospital Visit$")
		public void childbirth_time() {
			
		}
		
		
		
		
}