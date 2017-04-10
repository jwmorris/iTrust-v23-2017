package edu.ncsu.csc.itrust.cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * 
 * @author hmukhta
 * 
 *
 */
public class ViewBasicPatientHealthInfoWebStepDefs {

	private HtmlUnitDriver driver;
	private final String ADDRESS = "http://localhost:8080/iTrust/";
	
	
	public ViewBasicPatientHealthInfoWebStepDefs() {
	}
	
	@Given("^an HCP user is logged in$")
	public void hcp_user_logged_in() throws Throwable{
		driver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );

		// Implicitly wait at most 2 seconds for each element to load
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.get(ADDRESS);
		
		WebElement username = driver.findElement(By.name("j_username"));
		WebElement password = driver.findElement(By.name("j_password"));
		
		username.clear();
		password.clear();
		
		username.sendKeys("9000000000");
		password.sendKeys("pw");
		
		password.submit();
		
		Assert.assertFalse(driver.getTitle().equals("iTrust - Login"));
		Assert.assertEquals("iTrust - HCP Home", driver.getTitle());
	}
	
	@Given("^an HCP user nagivates to the view basic health info page for a patient$")
	public void navigate_to_basic_health_info_page() throws Throwable{
		driver.findElement(By.cssSelector("h2.panel-title")).click();
		driver.findElement(By.linkText("Basic Health Information")).click();
	}
	
	@When("^an HCP user enters a patient's mid or name$")
	public void hcp_user_enters_mid_or_name() throws Throwable{
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("1");
	}
	
	@When("^an HCP user clicks on the patient$")
	public void hcp_user_selects_patient() throws Throwable{
		driver.findElement(By.xpath("//input[@value='1']")).submit();
	}
	
	@Then("^the HCP user is on the basic health information page of the patient selected$")
	public void patient_info_shows_in_table() throws Throwable{
		Assert.assertFalse(driver.getTitle().equals("iTrust - Please Select a Patient"));
		Assert.assertTrue(driver.getTitle().equals("iTrust - View Patient Records"));
	}
}
