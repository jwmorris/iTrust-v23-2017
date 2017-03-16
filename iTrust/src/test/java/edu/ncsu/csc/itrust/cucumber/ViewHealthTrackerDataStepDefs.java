package edu.ncsu.csc.itrust.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.selenium.Driver;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
/**
public class ViewHealthTrackerDataStepDefs {
	private AuthDAO authController;
	private DataSource ds;
	private TestDataGenerator gen;
	private UserDataShared sharedHCP;
	private PatientDataShared sharedPatient;
	private PatientDAO patientController;
	private WebDriver driver;
	private long patientMID;
	
	public ViewHealthTrackerDataStepDefs() {
		this.ds = ConverterDAO.getDataSource();
		this.gen = new TestDataGenerator();
		this.sharedHCP = new UserDataShared();
		this.sharedPatient = new PatientDataShared();
		this.patientController = new PatientDAO( TestDAOFactory.getTestInstance() );
		this.driver = new Driver();
		this.patientMID = 0;
	}
	
	@Given("^the standard data is loaded$")
	public void standard_data_is_loaded() throws Throwable {
	    gen.clearAllTables();
	    gen.standardData();
	}
	
	@Given( "^ Given an HCP with MID (\\d+) and password (\\S+) is logged in$" )
	public void hcp_logs_in_with_MID_and_Password( long mid, String password ) throws Throwable {
		try {
			if( authController.authenticatePassword( mid, password ) ){
				sharedHCP.loginID = mid;
			}
		} catch (DBException e) {
			fail( "Unable to authenticate Password" );
		}
		driver.get("localhost:8080/iTrust/");
		WebElement user = driver.findElement(By.name("j_username"));
		WebElement pass = driver.findElement(By.name("j_password"));
		user.sendKeys(mid +"");
		pass.sendKeys(password);
		pass.submit();
		
		Assert.assertFalse(driver.getTitle().equals("iTrust - Login"));
		
		
	}
	
	@Given("^selects health tracker information$")
	public void select_health_tracker_info() {
		driver.findElement(By.id("fitnessInfo")).click();
		
		Assert.assertTrue(driver.getTitle().equals("iTrust - Fitness Information"));
	}
	
	@When( "^When the HCP searches for patient with MID (\\d+)$" )
	public void hcp_searches_for_patient( long patientMID ) throws DBException {
		WebElement search = driver.findElement(By.id("search_box"));
		search.sendKeys(patientMID + "");
		assertTrue( patientController.checkPatientExists( patientMID ) );
		this.patientMID = patientMID;
	}
	
	@When( "^selects the patient$" )
	public void hcp_selects_patient() {
		driver.findElement(By.xpath("//input[@value="+patientMID+"]")).click();
		sharedPatient.patientID = patientMID;
	}
	
	@When( "^the patient has step counts (\\d+) (\\d+) (\\d+)$" )
	public void patient_step_counts( int day1, int day2, int day3 ) {
		//Will need to edit pateint's fitness data object here
		//sharedPatient.steps
	}
	
	@When( "^selects view graphs$" )
	public void select_view_graphs() {
		driver.findElement( By.id( "viewGraphs" ) ).click();
	}
	
	@When( "the HCP enters start date <startDate> and endDate <endDate>$" )
	public void set_chart_time_frame( String startDate, String endDate ) {
		WebElement startDateBox = driver.findElement( By.id( "startDateInput" ) );
		WebElement endDateBox = driver.findElement( By.id( "endDateInput" ) );
		startDateBox.sendKeys( startDate );
		endDateBox.sendKeys( endDate );
	}
	
	@When( "^selects a step count graph$" )
	public void select_step_count_graph() {
		driver.findElement( By.id( "stepCountGraphRadio" ) ).click();
	}
	
	@When( "^selects a step count delta graph$" )
	public void select_step_count_delta_graph() {
		driver.findElement( By.id( "stepCountGraphRadio" ) ).click();
	}
	
	@When( "^selects a step count averages chart$" )
	public void select_step_count_avergaes_chart() {
		driver.findElement( By.id( "stepCountGraphRadio" ) ).click();
	}
	
	@Then( "^a step count graph is displayed for the time frame$" )
	public void step_count_graph_is_shown() {
		assertTrue( driver.findElement( By.id( "stepGraph" ) ) != null );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-error" ).equals( "" ) );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-type" ).equals( "stepGraph" ) );
		//Test values of step chart against values given
	}
	
	@Then( "^a step count delta graph is displayed for the time frame with step count deltas$" )
	public void step_count_delta_graph_is_shown() {
		assertTrue( driver.findElement( By.id( "dataGraph" ) ) != null );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-error" ).equals( "" ) );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-type" ).equals( "stepDeltaGraph" ) );
		//Test values of step chart against values given
	}
	
	@Then( "^a step count averages chart is displayed$" )
	public void step_count_avgs_chart_is_shown() {
		assertTrue( driver.findElement( By.id( "dataGraph" ) ) != null );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-error" ).equals( "" ) );
		assertTrue( driver.findElement( By.id( "dataGraph" ) ).getAttribute( "data-type" ).equals( "stepAvgChart" ) );
		//Test values of step chart against values given
	}
}*/
