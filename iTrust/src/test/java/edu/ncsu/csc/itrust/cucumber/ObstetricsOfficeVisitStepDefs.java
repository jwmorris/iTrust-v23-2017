package edu.ncsu.csc.itrust.cucumber;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;

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
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class ObstetricsOfficeVisitStepDefs {
	private DataSource ds;
	private PatientDataShared patientData;
	private PatientBean patient;
	private ObstetricsOfficeVisitMySQL oovData;
	private ObstetricsVisitController ovc;
	//private DAOFactory factory;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	private ObstetricsPregnancyData sql;
	private ObstetricsPregnancy newPreg;
	private SessionUtils utils;
	private TestDataGenerator gen;
	private ObstetricsOfficeVisit input;
	private ObstetricsPregnancy preg;
	private Long pid;
	
		public ObstetricsOfficeVisitStepDefs(){
			
			//this.factory = TestDAOFactory.getTestInstance();
			this.utils = Mockito.mock(SessionUtils.class);
			Mockito.doReturn( Long.parseLong("1") ).when( utils ).getCurrentPatientMIDLong();
			this.gen = new TestDataGenerator();
			this.ds = ConverterDAO.getDataSource();
			try {
				sql = new ObstetricsMySQL(ds);
			} catch (DBException e) {
				e.printStackTrace();
				
			}
			//this.patientData = currentPatient;
			this.patientDAO = new PatientDAO( TestDAOFactory.getTestInstance() );
			this.personnelDAO = new PersonnelDAO( TestDAOFactory.getTestInstance() );
			this.oovData = new ObstetricsOfficeVisitMySQL(ds);
			//this.oc = new ObstetricsController();
			this.ovc = new ObstetricsVisitController(ds, TestDAOFactory.getTestInstance(), utils);
			preg = new ObstetricsPregnancy();
			input = new ObstetricsOfficeVisit();
			
		}
		
		@Given("^Kelly Doctor enters PID 9000000021$")
		public void choose_invalid_patient() throws DBException, FileNotFoundException, IOException, SQLException{
			gen.clearAllTables();
			gen.standardData();
			Long id = 9000000021L;
			if((id <=0L) ||(id > 8999999999L) ){
				 Assert.assertFalse(patientDAO.checkPatientExists(id));
				//maybe test
			} else {
				Assert.fail("MID should be incorrect");
			}
			
		}
		@Given("^Kathyrn Evans selects Bad Horses PID$")
		public void kathyrn_Evans_selects_Bad_Horses_PID() throws DBException, FileNotFoundException, IOException, SQLException{
			gen.clearAllTables();
			gen.standardData();
			Long id = 0000000042L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				//patientData.patientID = id;
				patient = patientDAO.getPatient(id);
				preg.setPid( id );
			}
		}
		@Given("^Kathyrn Evans selects Baby Programmers PID$")
		public void kathyrn_Evans_selects_Baby_Programmers_PID() throws DBException, FileNotFoundException, IOException, SQLException{
			gen.clearAllTables();
			gen.standardData();
			Long id = 0000000005L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		}
		@Given("^Kathyrn Evans selects Princess Peachs PID$")
		public void kathyrn_Evans_selects_Princess_Peachs_PID() throws DBException, FileNotFoundException, IOException, SQLException{			
			gen.clearAllTables();
			gen.standardData();
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
		public void noCalendar_patient() throws DBException, FileNotFoundException, IOException, SQLException{
			gen.clearAllTables();
			gen.standardData();
			Long id = 0000000003L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
				preg.setPid( id );
			}
		
		}
		@Given("^Kathyrn Evans selects Random Persons PID$")
		public void choose_childbirth_patient() throws DBException, FileNotFoundException, IOException, SQLException{
			Long id = 0000000001L;
			gen.clearAllTables();
			gen.standardData();
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
			//input = new ObstetricsOfficeVisit();
			input.setBp("110/110");
			input.setFhr("110");
			//input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(patient.getMID());
			//input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("30");
			input.setWeight("150");
			try {
				//oovData.add(input);
				input.setId(oovData.addReturnsGeneratedId(input));
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
		public void change_to_person_pid() throws DBException {
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
		}
		
		@And("^Princess Peach had a prior pregnancy 20 weeks ago$")
		public void peach_prior_preg() {
			//initialize and make not current
			ObstetricsPregnancy op = new ObstetricsPregnancy();
			op.setCurrent(false);
		}
		
		@And("^Programmer has their RH Flag set$")
		public void set_programmer_rh() throws DBException, FormValidationException {
			//set rh
			//preg = sql.getCurrentObstetricsPregnancy(patient.getMID() );
			pid = patient.getMID();
			preg.setrhFlag(true);
			preg.setDateInit("02/01/2017");
			preg.setLmp("01/25/2017");
			preg.setWeeksPregnant();
			//preg.setBabyCount( "1" );
			sql.add(preg);
		}
		
		@When("^she correctly enters Random Persons PID$")
		public void choose_person_pid() throws DBException {
			Long id = 0000000001L;
			if((id <=0L) ||(id > 8999999999L) ){
				Assert.fail("MID should be real");
			} else {
				patient = patientDAO.getPatient(id);
			}
			
		}
		
		@When("^saves those records and sets Persons next appointment to Independence Day$")
		public void set_holiday_appointment() {
			
		}
		
		@When("^Dr Evans edits the documented visit$")
		public void edit_visit() throws FormValidationException, DBException {
			//edit and check to make sure of edit
			//input = new ObstetricsOfficeVisit();
			input.setBp("110/110");
			input.setFhr("110");
			//input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(patient.getMID());
			input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("30");
			input.setWeight("150");
			try {
				oovData.add(input);
			} catch (DBException e) {
				
			}
			input.setWeeksPregnant("40");
			try {
				oovData.update(input);
			} catch (DBException e) {
				
			}
			//oovData.getByID(input.getId()).setWeeksPregnant("40");
			
		}
		
		@When("^Dr Evans initilizes Princess Peach$")
		public void initilize_2nd_pregnancy() {
			//initialize and make sure is current
			newPreg = new ObstetricsPregnancy();
		}
		
		@When("Programmer has enough weeks for an rh pregnant visit$")
		public void add_28_week_visit() throws FormValidationException, DBException {
			//set weeks preg to 28 and see that rh flag boolean visit
			//ObstetricsOfficeVisit input = new ObstetricsOfficeVisit();
			//utils.setSessionVariable( "loggedInMID", 9000000012L );
			//Mockito.mock( SessionUtils.class ).setSessionVariable( "loggedInMID", 9000000012L );
			
			input.setBp("110/110");
			input.setFhr("110");
			input.setInitID( preg.getId() );
			//input.setId(9);
			input.setLowLying(false);
			input.setMultiplePregnancy(false);
			input.setNumBabies("1");
			input.setPid(3);
			//input.setVisitDate(new Date(05/04/17));
			input.setWeeksPregnant("29");
			input.setWeight("150");
			oovData.add( input );
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
			Assert.assertEquals("40", input.getWeeksPregnant());
		}
		
		@Then("^Princess Peachs displayed intiliazation is the current one$")
		public void check_initilization() {
			//current should be the newest initialization
			Assert.assertTrue(newPreg.getCurrent());
		}
		
		@Then("^a notice is displayed that they need an RH immune globulin shot$")
		public void rh_shot_needed() throws DBException {
			//rh shot needed
			utils.setSessionVariable("pid", 3 );
			Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 3 );
			Mockito.doReturn( Long.parseLong( "3" ) ).when( utils ).getCurrentPatientMIDLong();
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