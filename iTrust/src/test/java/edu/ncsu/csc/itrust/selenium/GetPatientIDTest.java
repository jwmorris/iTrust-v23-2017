package edu.ncsu.csc.itrust.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * Selenium test conversion for HttpUnit GetPatientIDTest
 */
@SuppressWarnings("unused")
public class GetPatientIDTest extends iTrustSeleniumTest {

	private HtmlUnitDriver driver;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		driver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );
		driver.get("http://localhost:8080/iTrust/");
	}

	/*
	 * Tests if back-end "Select Patient" function is connected to front-end JSP
	 */
	public void testSelectPatientButton() throws Exception {

		gen.hcp4();
		gen.hcp5();

		WebElement element;

		driver.findElement(By.id("j_username")).sendKeys("9000000003");
		driver.findElement(By.id("j_password")).sendKeys("pw");

		driver.findElement(By.cssSelector("input[type='submit']")).click();
		assertEquals("iTrust - HCP Home", driver.getTitle());

		driver.findElement(By.cssSelector("a[href= '/iTrust/auth/hcp-uap/editPatient.jsp']")).click();
		assertEquals("iTrust - Please Select a Patient", driver.getTitle());

		// click on the "Select Patient" button
		driver.findElement(By.id("selectPatientButton")).click();
		assertEquals("iTrust - Please Select a Patient", driver.getTitle());

		assertFalse(driver.getPageSource().contains("HTTP Status 500"));
		assertFalse(driver.getPageSource().contains("java.lang.NumberFormatException"));

		// click on the "Select Patient" button
		driver.findElement(By.id("selectPatientButton")).click();
		assertEquals("iTrust - Please Select a Patient", driver.getTitle());

		assertFalse(driver.getPageSource().contains("HTTP Status 500"));
		assertFalse(driver.getPageSource().contains("java.lang.NumberFormatException"));
		assertFalse(driver.getPageSource().contains("Viewing information for <b>null</b>"));
	}

	@Override
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}