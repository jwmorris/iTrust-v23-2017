
package edu.ncsu.csc.itrust.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import edu.ncsu.csc.itrust.model.old.enums.TransactionType;
import edu.ncsu.csc.itrust.selenium.iTrustSeleniumTest;

public class ViewAccessLogTest extends iTrustSeleniumTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Override
	protected void setUp() throws Exception {
		driver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		super.setUp();
		gen.clearAllTables();
		gen.uap1();
		gen.patient2();
		gen.patient1();
		gen.patient4();
		gen.hcp0();
		gen.hcp3();
		gen.er4();
	}

	@Test
	public void testViewaccesslog2() throws Exception {
		// clear operational profile
		gen.transactionLog();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("2", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());
		assertLogged(TransactionType.HOME_VIEW, 2L, 0L, "");

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());

		driver.findElement(By.name("startDate")).clear();
		driver.findElement(By.name("startDate")).sendKeys("06/22/2000");
		driver.findElement(By.name("endDate")).clear();
		driver.findElement(By.name("endDate")).sendKeys("06/23/2000");
		driver.findElement(By.name("submit")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertLogged(TransactionType.ACCESS_LOG_VIEW, 2L, 0L, "");
	}

	@Test
	public void testViewAccessLog3() throws Exception {
		// clear operational profile
		gen.transactionLog();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("1", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());
		assertLogged(TransactionType.HOME_VIEW, 1L, 0L, "");

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertLogged(TransactionType.ACCESS_LOG_VIEW, 1L, 0L, "");
	}

	@Test
	public void testViewAccessLogByDate() throws Exception {
		gen.transactionLog2();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("2", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());
		assertLogged(TransactionType.HOME_VIEW, 2L, 0L, "");

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());

		driver.findElement(By.name("startDate")).clear();
		driver.findElement(By.name("startDate")).sendKeys("03/01/2008");
		driver.findElement(By.name("endDate")).clear();
		driver.findElement(By.name("endDate")).sendKeys("12/01/2008");
		driver.findElement(By.name("submit")).click();
		// The rest of the original test was commented out
	}

	@Test
	public void testViewAccessLogByRole() throws Exception {
		gen.transactionLog3();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("1", "pw");
		driver.setJavascriptEnabled(true);
		assertEquals("iTrust - Patient Home", driver.getTitle());
		assertLogged(TransactionType.HOME_VIEW, 1L, 0L, "");

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		
	}

	@Test
	public void testViewAccessLogRepresentativeView() throws Exception {
		gen.clearAllTables();
		gen.standardData();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("1", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		// new
		// Select(driver.findElement(By.id("logMIDSelectMenu"))).selectByVisibleText("Dare
		// Devil");
		driver.findElement(By.name("submit")).click();
		// There is no way to read all text on the page through Selenium
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertEquals("Kelly Doctor",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[2]/td[2]")).getText());
		assertEquals("Justin Time",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[3]/td[2]")).getText());
		assertEquals("Andy Programmer",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[4]/td[2]")).getText());

		driver.findElement(By.linkText("Role")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertEquals("Kelly Doctor",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[2]/td[2]")).getText());
		assertEquals("Justin Time",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[3]/td[2]")).getText());
		assertEquals("Andy Programmer",
				driver.findElement(By.xpath("//div[@id='iTrustContent']/table/tbody/tr[4]/td[2]")).getText());
	}

	@Test
	public void testViewAccessLogNonRepresentativeView1() throws Exception {
		gen.clearAllTables();
		gen.standardData();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("1", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());

		// Check the text currently in the drop down box to ensure it is not
		// "Devil's Advocate"
		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertEquals("Random Person", driver.findElement(By.id("logMIDSelectMenu")).getText());
	}

	public void testViewAccessLogBadDateHandling() throws Exception {
		gen.clearAllTables();
		gen.standardData();

		// This logs us into iTrust and returns the HtmlUnitDriver for use in
		// this case
		HtmlUnitDriver driver = (HtmlUnitDriver) login("23", "pw");
		assertEquals("iTrust - Patient Home", driver.getTitle());

		driver.findElement(By.id("toggleMenu")).click();
		driver.findElement(By.xpath("//div[@id='iTrustMenu']/div/div[2]/div/h2")).click();
		driver.findElement(By.linkText("Access Log")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());

		driver.findElement(By.name("startDate")).clear();
		driver.findElement(By.name("startDate")).sendKeys("06/22/2007");
		driver.findElement(By.name("endDate")).clear();
		driver.findElement(By.name("endDate")).sendKeys("06/21/2007");
		driver.findElement(By.name("submit")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertEquals("Information not valid", driver.findElement(By.cssSelector("#iTrustContent > h2")).getText());
		assertEquals("Start date must be before end date!",
				driver.findElement(By.cssSelector("div.errorList")).getText());

		driver.findElement(By.name("startDate")).clear();
		driver.findElement(By.name("startDate")).sendKeys("June 22nd, 2007");
		driver.findElement(By.name("endDate")).clear();
		driver.findElement(By.name("endDate")).sendKeys("6/23/2007");
		driver.findElement(By.name("submit")).click();
		assertEquals("iTrust - View My Access Log", driver.getTitle());
		assertEquals("Information not valid", driver.findElement(By.cssSelector("#iTrustContent > h2")).getText());
		assertEquals("Enter dates in MM/dd/yyyy", driver.findElement(By.cssSelector("div.errorList")).getText());
	}

	@Override
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}