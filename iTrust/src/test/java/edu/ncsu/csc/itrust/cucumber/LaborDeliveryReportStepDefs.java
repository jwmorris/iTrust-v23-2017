package edu.ncsu.csc.itrust.cucumber;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class LaborDeliveryReportStepDefs {
	private DataSource ds;
	private TestDataGenerator gen;
	private SessionUtils utils;
	private DAOFactory factory;
	private PatientDAO patientDAO;
	private PatientBean patient;
	
	public LaborDeliveryReportStepDefs() {
		this.factory = TestDAOFactory.getTestInstance();
		utils = Mockito.mock( SessionUtils.class );
		this.gen = new TestDataGenerator();
		this.ds = ConverterDAO.getDataSource();
	
	}
    @Given("^Kathyrn Evans searchs for Princess Leach$")
    public void kathyrn_evans_searchs_for_princess_leach() throws Throwable {
        gen.clearAllTables();
		gen.standardData();
		Assert.assertEquals( 0, patientDAO.searchForPatientsWithName( "Princess" , "Leach" ).size() );
    }

    @Given("^Kathyrn Evans searchs for Baby Programmer$")
    public void kathyrn_evans_searchs_for_baby_programmer() throws Throwable {
    	gen.clearAllTables();
 		gen.standardData();
 		Assert.assertEquals( 1, patientDAO.searchForPatientsWithName( "Programmer" , "Baby" ).size() );
 		utils.setSessionVariable( "pid", 5 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 5 );
		Mockito.doReturn( Long.parseLong( "5" ) ).when( utils ).getCurrentPatientMIDLong();
        
    }

    @When("^Kathyrn Evans correctly enters Princess Peach$")
    public void kathyrn_evans_correctly_enters_princess_peach() throws Throwable {
    	utils.setSessionVariable( "pid", 21 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 21 );
		Mockito.doReturn( Long.parseLong( "21" ) ).when( utils ).getCurrentPatientMIDLong();
    }

    @When("^Kathyrn Evans attempts to run a report for Baby Programmer$")
    public void kathyrn_evans_attempts_to_run_a_report_for_baby_programmer() throws Throwable {
        //click report button
    }

    @Then("^Kathyrn Evans recieves a full report of pregnany info$")
    public void kathyrn_evans_recieves_a_full_report_of_pregnancy_info() throws Throwable {
        //check to make sure report is there
    }

    @Then("^the report is not generated as Baby Programmer is not an obstetrics patient$")
    public void the_report_is_not_generated_as_baby_programmer_is_not_an_obstetrics_patient() throws Throwable {
        //check to make sure on not obstetrics page
    }

}