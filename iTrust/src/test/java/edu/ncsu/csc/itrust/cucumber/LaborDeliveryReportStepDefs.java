package edu.ncsu.csc.itrust.cucumber;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

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
	private HtmlUnitDriver driver;
	private final String ADDRESS = "http://localhost:8080/iTrust/";
	
	public LaborDeliveryReportStepDefs() {
		this.factory = TestDAOFactory.getTestInstance();
		utils = Mockito.mock( SessionUtils.class );
		this.gen = new TestDataGenerator();
		this.ds = ConverterDAO.getDataSource();
		driver = new HtmlUnitDriver();

		TestHooks.testPrep();
		// Implicitly wait at most 2 seconds for each element to load
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.get(ADDRESS);
		
		WebElement username = driver.findElement(By.name("j_username"));
		WebElement password = driver.findElement(By.name("j_password"));
		
		username.clear();
		password.clear();
		
		username.sendKeys("9000000012");
		password.sendKeys("pw");
		
		password.submit();
		
		Assert.assertFalse(driver.getTitle().equals("iTrust - Login"));
		Assert.assertEquals("iTrust - HCP Home", driver.getTitle());
		driver.findElement(By.cssSelector("h2.panel-title")).click();
		driver.findElement(By.linkText("tabname")).click();
	
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
 		driver.findElement(By.name("UID_PATIENTID")).clear();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("5");
		//driver.findElement(By.xpath("//input[@value='5']")).submit();		
 		/*Assert.assertEquals( 1, patientDAO.searchForPatientsWithName( "Programmer" , "Baby" ).size() );
 		utils.setSessionVariable( "pid", 5 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 5 );
		Mockito.doReturn( Long.parseLong( "5" ) ).when( utils ).getCurrentPatientMIDLong();*/
        
    }

    @When("^Kathyrn Evans correctly enters Princess Peach$")
    public void kathyrn_evans_correctly_enters_princess_peach() throws Throwable {
    	driver.findElement(By.name("UID_PATIENTID")).clear();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("2");
		driver.findElement(By.xpath("//input[@value='2']")).submit();
		Assert.assertEquals("iTrust - Select Obstetrics Report", driver.getTitle());
			
    	/*utils.setSessionVariable( "pid", 21 );
		Mockito.mock( SessionUtils.class ).setSessionVariable( "pid", 21 );
		Mockito.doReturn( Long.parseLong( "21" ) ).when( utils ).getCurrentPatientMIDLong();*/
    }

    @When("^Kathyrn Evans attempts to run a report for Baby Programmer$")
    public void kathyrn_evans_attempts_to_run_a_report_for_baby_programmer() throws Throwable {
        //click report button
    	driver.findElement(By.xpath("//input[@value='5']")).submit();	
    }

    @Then("^Kathyrn Evans recieves a full report of pregnany info$")
    public void kathyrn_evans_recieves_a_full_report_of_pregnancy_info() throws Throwable {
        //check to make sure report is there
    	driver.findElement(By.linkText("View Report")).click();
    	Assert.assertEquals("iTrust - Obstetrics Report", driver.getTitle());
    	
    }

    @Then("^the report is not generated as Baby Programmer is not an obstetrics patient$")
    public void the_report_is_not_generated_as_baby_programmer_is_not_an_obstetrics_patient() throws Throwable {
        //check to make sure on not obstetrics page
    	//Assert.assertFalse(driver.getTitle().equals("iTrust - Report"));
    	Assert.assertEquals("iTrust - Need Obstetrics Patient", driver.getTitle());
    }

}