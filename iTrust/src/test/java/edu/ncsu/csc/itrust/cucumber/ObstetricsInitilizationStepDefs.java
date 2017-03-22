package edu.ncsu.csc.itrust.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

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
	}
	
	
	@And("^reselects PID 2$")
	public void choose_obstetrics_pid() {
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[1]/a")).click();
        driver.get("http://localhost:8080/iTrust/auth/getPatientID.jsp?forward=/iTrust/auth/hcp-obstetrics/initializePatient.xhtml");
        driver.findElement(By.name("UID_PATIENTID")).sendKeys("2");
		driver.findElement(By.xpath("//input[@value='2']")).submit();
		Assert.assertEquals("iTrust - Patient Initialization Record", driver.getTitle());
	}
	
	@And("^she edits a prior pregnancy by changing total weeks pregnant to forty$")
	public void edit_incorrect() {
		WebElement webElement = driver.findElement(By.name("editPriorForm:priorDates"));
		Select datePicker = new Select(webElement);
		datePicker.selectByValue("03/19/2013");
		driver.findElement(By.cssSelector("input[type=\"submit\"][value=\"Edit Prior Pregnancy\"]")).submit();
		Assert.assertTrue(driver.getTitle().equals("iTrust - Edit Prior Pregnancy"));
		
		// modify weeks pregnant to the word "forty"
		driver.findElement(By.name("j_idt23:j_idt33")).clear();
		driver.findElement(By.name("j_idt23:j_idt33")).sendKeys("forty");
		driver.findElement(By.cssSelector("input[type=\"submit\"][value=\"Save\"]")).submit();
		
		// see if the error message has appeared
		String errorMessage = driver.findElement(By.xpath("//*[@id=\"iTrustContent\"]/div")).getText();
		Assert.assertTrue(errorMessage.contains("This form has not been validated correctly. The following field are not properly filled in: [Total Weeks Pregnant must be numeric]"));
	}
	
	@When("^Dr. Evans edits a prior pregnancy by changing total weeks pregnant to 40$")
	public void edit_correctly() {
		driver.findElement(By.cssSelector("input[type=\"submit\"][value=\"Back\"]")).submit();
		WebElement webElement = driver.findElement(By.name("editPriorForm:priorDates"));
		Select datePicker = new Select(webElement);
		datePicker.selectByValue("03/19/2013");
		driver.findElement(By.cssSelector("input[type=\"submit\"][value=\"Edit Prior Pregnancy\"]")).submit();
		Assert.assertTrue(driver.getTitle().equals("iTrust - Edit Prior Pregnancy"));
		
		// modify weeks pregnant to the value 40
		driver.findElement(By.name("j_idt23:j_idt33")).clear();
		driver.findElement(By.name("j_idt23:j_idt33")).sendKeys("40");
		driver.findElement(By.cssSelector("input[type=\"submit\"][value=\"Save\"]")).submit();
	}
	
	@Then("^Princess Peach's prior pregnancy is updated$")
	public void pregnancy_updated() throws InterruptedException {
		WebElement babyTable = driver.findElement(By.xpath("//*[@id=\"iTrustContent\"]/table"));
		List<WebElement> tableRows = babyTable.findElements(By.tagName("tr"));
		String babyRow = tableRows.get(1).getText();
		String[] actual = babyRow.split(" ");
		String[] expected = {"03/19/2013","03/07/2013","12/12/2013","12","2013","40","13.5","100","Caesarean","Section","false","1"};
		for(int i = 0; i < expected.length; i++) {
			Assert.assertTrue(actual[i].equals(expected[i]));
		}
	}
	
	@After
	public void closeDriver() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
}