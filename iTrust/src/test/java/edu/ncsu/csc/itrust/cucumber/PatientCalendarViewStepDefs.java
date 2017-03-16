package edu.ncsu.csc.itrust.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.selenium.Driver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class PatientCalendarViewStepDefs {
	
	public WebDriver driver;

	
	@Given("^I am logged in as a patient$")
	public void loginAsPatient() throws Exception{
		driver = new Driver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/iTrust/");
		WebElement usernameBox = driver.findElement(By.name("j_username"));
		WebElement passwordBox = driver.findElement(By.name("j_password"));
		usernameBox.sendKeys("1");
		passwordBox.sendKeys("pw");
		
		passwordBox.submit();
		
		Assert.assertFalse(driver.getTitle().equals("iTrust - Login"));
	}
	
	
	@When("^I click Full Calendar$")
	public void clickFullCalendar() {
		driver.findElement(By.linkText("Appointment Calendar")).click();
	}
	
	@Then("^The calendar is loaded$")
	public void checkCalendarIsLoaded() {
		Assert.assertEquals("iTrust - Appointment Calendar", driver.getTitle());
		Assert.assertEquals(1, driver.findElements(By.id("calendarTable")).size());
	}
	
	
}
