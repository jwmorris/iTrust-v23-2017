package edu.ncsu.csc.itrust.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.Page;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.selenium.Driver;

public class ReadMessagesMarkedReadStepDefs {
	private Driver webDriver;
	private final String inboxUrl = "http://localhost:8080/iTrust/auth/hcp-patient/messageInbox.jsp";
	private By unreadMessages = By.xpath("//table[@id='mailbox']/tbody/tr[@style='font-weight: bold;']");
	private By firstUnreadMessageLink = By.xpath("//table[@id='mailbox']/tbody/tr[@style='font-weight: bold;']/td[last()]/a[1]");
	private int unreadMessageCount = 0;
	private Page cachedPage;

	public void ReadMessageMarkedReadStepDef() {
	}

	@Given("user logs in as an HCP")
	public void user_logs_in(){
		webDriver = new Driver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.get("http://localhost:8080/iTrust/");
		// log in using the given username and password
		WebElement user = webDriver.findElement(By.name("j_username"));
		WebElement pass = webDriver.findElement(By.name("j_password"));
		user.sendKeys("9000000000");
		pass.sendKeys("pw");
		pass.submit();

		Assert.assertFalse(webDriver.getTitle().equals("iTrust - Login"));

	}

	@When("user navigates to their message inbox")
	public void user_loads_inbox(){
		webDriver.get(inboxUrl);
//		cachedPage = webDriver.cachePage();
		List<WebElement> ums = webDriver.findElements(unreadMessages);
		unreadMessageCount = ums.size();
	}

	@When("user reads an unread message")
	public void user_reads_unread_message(){
		webDriver.findElement(firstUnreadMessageLink).click();
	}

	@When("user presses the navigate back button")
	public void user_navigates_back(){
		webDriver.navigate().back();
	}

	@Then("the read message is no longer bolded")
	public void read_message_not_bold(){
		List<WebElement> ums = webDriver.findElements(unreadMessages);
		Assert.assertEquals(unreadMessageCount-1, ums.size());
		try{
			Assert.assertTrue(webDriver.checkHeader(inboxUrl, "Cache-Control", "must-revalidate, no-store, no-cache, private"));
			Assert.assertTrue(webDriver.checkHeader(inboxUrl, "Pragma", "no-cache"));
			Assert.assertTrue(webDriver.checkHeader(inboxUrl, "Expires", "Thu, 01 Jan 1970 00:00:00 GMT"));
		}catch(Exception e){
			Assert.assertTrue(false);
		}
	}
}
