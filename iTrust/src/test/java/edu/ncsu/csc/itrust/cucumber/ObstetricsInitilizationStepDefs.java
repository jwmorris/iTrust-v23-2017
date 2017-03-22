package edu.ncsu.csc.itrust.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ObstetricsInitilizationStepDefs {
	
	private HtmlUnitDriver driver;
	private final String ADDRESS = "http://localhost:8080/iTrust/";
	
	@Before public void loginOBGYN() {
		driver = new HtmlUnitDriver();

		TestHooks.testPrep();
		// Implicitly wait at most 2 seconds for each element to load
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
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
	}
	
	@Given("^Kathyrn Evans enters PID 52$")
	public void choose_invalid_patient() {
		driver.findElement(By.cssSelector("h2.panel-title")).click();
		driver.findElement(By.linkText("Patient Initialization")).click();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("52");
		driver.findElement(By.xpath("//input[@value='52']"));
		try {
			WebElement we = driver.findElement(By.className("searchResults"));
		} catch(Exception e) {
			Assert.assertEquals("iTrust - Please Select a Patient", driver.getTitle());
		}
		
	}
	
	@And("^she correctly enters Princess Peach's PID$")
	public void choose_peach_pid() {
		driver.findElement(By.name("UID_PATIENTID")).clear();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("21");
		driver.findElement(By.xpath("//input[@value='21']")).submit();		
	}
	
	@And("^she makes Princess Peach eligible for obstetric care$")
	public void make_eligible() {
		Assert.assertEquals("iTrust - Patient Initialization Record", driver.getTitle());
		driver.findElement(By.name("j_idt14:j_idt17")).click();
	}
	
	@When("^Dr. Evans initializes a current pregnancy$")
	public void initialize_peach() {
		driver.findElement(By.id("currentPregnancy:addNewPregnancy")).click();
		driver.findElement(By.name("j_idt22:j_idt24")).sendKeys("02/12/2017");
		driver.findElement(By.name("j_idt22:j_idt26")).sendKeys("02/11/2017");
		driver.findElement(By.name("j_idt22:j_idt28")).sendKeys("5");
		driver.findElement(By.name("j_idt22:j_idt30")).click();
	}
	
	@Then("^Princess Peach's current pregnancy is displayed$")
	public void check_initilization() {
		Assert.assertEquals("iTrust - Patient Initialization Record", driver.getTitle());
		WebElement baseTable = driver.findElement(By.name("j_idt23"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		String str = tableRows.get(1).getText();
		String[] s = str.split(" ");
		Assert.assertTrue(s[0].equals("02/12/2017"));
		Assert.assertTrue(s[1].equals("02/11/2017"));
		Assert.assertTrue(s[2].equals("11/18/2017"));
		Assert.assertTrue(s[3].equals("5"));		
	}
	
	@Given("^Kathyrn Evans selects PID 1$")
	public void choose_wrong_patient() {
		driver.findElement(By.cssSelector("h2.panel-title")).click();
		driver.findElement(By.linkText("Patient Initialization")).click();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("1");
		driver.findElement(By.xpath("//input[@value='1']")).submit();
		Assert.assertEquals("iTrust - Patient Initialization Record", driver.getTitle());
		//driver.findElement(By.xpath("//*[@id='j_idt81']/span/a")).click();
		//System.out.print(driver.getTitle());
		
	}
	
	
	@And("^reselects PID 2$")
	public void choose_obstetrics_pid() {
		//select pid with prior pregnancy
	}
	
	@And("^she edits a prior pregnancy by changing total weeks pregnant to forty$")
	public void edit_incorrect() {
		//edit prior pregnancy with incorrect input
	}
	
	@When("^Dr. Evans edits a prior pregnancy by changing total weeks pregnant to 40$")
	public void edit_correctly() {
		//edit prior pregnancy with correct input
	}
	
	@Then("^Princess Peach's prior pregnancy is updated$")
	public void pregnancy_updated() {
		//check pregnancy is updated
	}
	
	@After
	public void closeDriver() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
}