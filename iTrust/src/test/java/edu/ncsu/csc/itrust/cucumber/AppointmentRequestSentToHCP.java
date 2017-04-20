package edu.ncsu.csc.itrust.cucumber;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.action.ViewApptRequestsAction;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.beans.ApptRequestBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.selenium.Driver;

public class AppointmentRequestSentToHCP {
	private Driver webDriver;
	private By requests = By.xpath("//div[@id='iTrustContent']/ul/li");
	private By message = By.xpath("//div[@id='iTrustContent']/span");
	
	@Given("^patient (.+) logs in$")
	public void patient_logs_in(String username){
		webDriver = new Driver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.get("http://localhost:8080/iTrust/");
		// log in using the given username and password
		WebElement user = webDriver.findElement(By.name("j_username"));
		WebElement pass = webDriver.findElement(By.name("j_password"));
		user.sendKeys(username);
		pass.sendKeys("pw");
		pass.submit();
		
		Assert.assertFalse(webDriver.getTitle().equals("iTrust - Login"));
	}
		
	@When("^patient makes an appointment request in the future$")
	public void patient_makes_appointment(){
		webDriver.get("http://localhost:8080/iTrust/auth/patient/appointmentRequests.jsp");
		Assert.assertEquals("", webDriver.findElement(message).getText());
		WebElement date = webDriver.findElement(By.name("startDate"));
		Date tomorrow = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(tomorrow); 
		cal.add(Calendar.DATE, 1);
		tomorrow = cal.getTime();
		DateFormat dateFormatter = new SimpleDateFormat("mm/dd/yyyy");
		date.sendKeys(dateFormatter.format(tomorrow));
		date.submit();
		webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Assert.assertEquals("Your appointment request has been saved and is pending.", webDriver.findElement(message).getText());
	}

	@When("^patient logs out$")
	public void patient_logs_out(){
		webDriver.get("http://localhost:8080/iTrust/logout.jsp");		
	}
		
	@When("^HCP (.+) logs in$")
	public void hcp_logs_in(String username){
		webDriver.get("http://localhost:8080/iTrust/");
		// log in using the given username and password
		WebElement user = webDriver.findElement(By.name("j_username"));
		WebElement pass = webDriver.findElement(By.name("j_password"));
		user.sendKeys(username);
		pass.sendKeys("pw");
		pass.submit();
		
		Assert.assertFalse(webDriver.getTitle().equals("iTrust - Login"));
	}
	
	@Then("^the request appears$")
	public void request_appears(){
		webDriver.get("http://localhost:8080/iTrust/auth/hcp/viewMyApptRequests.jsp");
		Assert.assertFalse(webDriver.getTitle().equals("iTrust - Login"));
	}
}
