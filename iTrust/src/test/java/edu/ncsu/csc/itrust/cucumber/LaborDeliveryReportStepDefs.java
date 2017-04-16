package edu.ncsu.csc.itrust.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReport;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsReportController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.diagnosis.Diagnosis;
import edu.ncsu.csc.itrust.model.obstetrics.ObstetricsPregnancy;
import edu.ncsu.csc.itrust.model.old.beans.AllergyBean;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.selenium.Driver;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class LaborDeliveryReportStepDefs {
	private DataSource ds;
	private TestDataGenerator gen;
	private SessionUtils utils;
	private DAOFactory factory;
	private PatientDAO patientDAO;
	private PatientBean baby;
	private ObstetricsReportController andyControl;
	private long babyId;
	
	public LaborDeliveryReportStepDefs() {
		this.factory = TestDAOFactory.getTestInstance();
		utils = Mockito.mock( SessionUtils.class );
		utils.setSessionVariable( "pid", 2 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 2 );
		Mockito.doReturn( Long.parseLong( "2" ) ).when( utils ).getCurrentPatientMIDLong();
		this.gen = new TestDataGenerator();
		this.ds = ConverterDAO.getDataSource();
		this.patientDAO = factory.getPatientDAO();

	}
	
    @Given("^Kathyrn Evans types in Princess Leach$")
    public void kathyrn_evans_types_in_princess_leach() throws Throwable {
        gen.clearAllTables();
		gen.standardData();
		Assert.assertEquals( 0, patientDAO.searchForPatientsWithName( "Princess" , "Leach" ).size() );
    }

    @Given("^Kathyrn Evans searchs for Baby Programmer$")
    public void kathyrn_evans_searchs_for_baby_programmer() throws Throwable {
    	gen.clearAllTables();
 		gen.standardData();
 		babyId = 5;
    }

    @When("^Kathyrn Evans then enters Andy Programmer$")
    public void kathyrn_evans_then_enters_andy_programmer() throws Throwable {
		this.andyControl = new ObstetricsReportController( ds, factory, utils );		
    }

    @When("^Kathyrn Evans attempts to run a report for Baby Programmer$")
    public void kathyrn_evans_attempts_to_run_a_report_for_baby_programmer() throws Throwable {
    	baby = patientDAO.getPatient(babyId);
    }

    @Then("^Kathyrn Evans recieves a full report of pregnany info$")
    public void kathyrn_evans_recieves_a_full_report_of_pregnancy_info() throws Throwable {
    	List<ObstetricsPregnancy> priorpreg = andyControl.getPriorPregnancies();
    	Assert.assertEquals("2015",priorpreg.get(0).getConcepYear());
    	Assert.assertEquals("15", priorpreg.get(0).getTotalWeeksPregnant());
    	Assert.assertEquals("Caesarean Section",priorpreg.get(0).getDeliveryType());
    	ObstetricsPregnancy preg = andyControl.getCurrentPregnancy();
    	Assert.assertEquals("12/12/2017", preg.getEdd());
    	Assert.assertEquals("O-", andyControl.getCurrentPatientBloodType());
    	Assert.assertEquals("05/19/1984", andyControl.getDOB());
    }

    @Then("^the report is not generated as Baby Programmer is not an obstetrics patient$")
    public void the_report_is_not_generated_as_baby_programmer_is_not_an_obstetrics_patient() throws Throwable {
    	Assert.assertFalse(baby.isObstetricsPatient());
    }

}