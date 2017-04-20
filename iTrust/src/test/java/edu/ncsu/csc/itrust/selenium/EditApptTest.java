package edu.ncsu.csc.itrust.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import edu.ncsu.csc.itrust.model.old.enums.TransactionType;

public class EditApptTest extends iTrustSeleniumTest {
	private HtmlUnitDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		driver = new HtmlUnitDriver( BrowserVersion.INTERNET_EXPLORER_11 );
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void testSetPassedDate() throws Exception {
		gen.uc22();
		driver = (HtmlUnitDriver) login("9000000000", "pw");
		driver.setJavascriptEnabled(true);
		assertEquals("iTrust - HCP Home", driver.getTitle());
		driver.findElement(By.linkText("View My Appointments")).click();
		assertLogged(TransactionType.APPOINTMENT_ALL_VIEW, 9000000000L, 0L, "");
		
	}

	@Test
	public void testRemoveAppt() throws Exception {
		gen.uc22();
		driver = (HtmlUnitDriver) login("9000000000", "pw");
		driver.setJavascriptEnabled(true);
		assertEquals("iTrust - HCP Home", driver.getTitle());
		
	}

	@Test
	public void testEditAppt() throws Exception {
		driver = (HtmlUnitDriver) login("9000000000", "pw");
		driver.setJavascriptEnabled(true);
		assertEquals("iTrust - HCP Home", driver.getTitle());
		driver.findElement(By.linkText("View My Appointments")).click();
		assertLogged(TransactionType.APPOINTMENT_ALL_VIEW, 9000000000L, 0L, "");
		List<WebElement> rows = driver.findElements(By.tagName("td"));
		
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