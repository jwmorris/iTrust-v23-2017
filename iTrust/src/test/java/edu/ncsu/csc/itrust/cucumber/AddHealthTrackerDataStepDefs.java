package edu.ncsu.csc.itrust.cucumber;
/**
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.calendar.CalendarController;
import edu.ncsu.csc.itrust.controller.calendar.FileUploadController;
import edu.ncsu.csc.itrust.controller.calendar.FitnessDataEvent;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessData;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.model.user.patient.Patient;
import edu.ncsu.csc.itrust.selenium.Driver;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
/*
public class AddHealthTrackerDataStepDefs {
	private DataSource ds;
	private TestDataGenerator gen;
	private PatientDataShared sharedPatient;
	private FitnessDataShared fitnessShared;
	private PatientDAO patientController;
	private WebDriver driver;
	private long patientMID;
	private FitnessDataEvent fde;
	private CalendarController calControl;
	private FileUploadController fUp;
	private UploadedFile file;
	

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	private static final String DATA_FILE = "/iTrust/testing-files/fitness_data/FitBitUploadData.csv";
	
	public AddHealthTrackerDataStepDefs() {
		this.ds = ConverterDAO.getDataSource();
		this.gen = new TestDataGenerator();
		this.sharedPatient = new PatientDataShared();
		this.fitnessShared = new FitnessDataShared();
		this.patientController = new PatientDAO( TestDAOFactory.getTestInstance() );
		this.driver = new Driver();
		this.patientMID = 0;
		fUp = new FileUploadController();
		file = null;
	}
	
	@Given("^the standard data is populated$")
	public void standard_data_is_loaded() throws Throwable {
	    gen.clearAllTables();
	    gen.standardData();
	}
	
	@Given( "^an HCP with MID (\\d+) and password (.+) is logged in$" )
	public void hcp_logs_in_with_MID_and_Password( long mid, String password ) throws Throwable {
		
		driver.get("http://localhost:8080/iTrust/");
		try {
			WebElement user = driver.findElement(By.name("j_username"));
			WebElement pass = driver.findElement(By.name("j_password"));
			user.sendKeys(mid + "");
			pass.sendKeys(password);
			pass.submit();
		} catch (NoSuchElementException e) {
			fail("Login Failed");
		}
		
		Assert.assertFalse(driver.getTitle().equals("iTrust - Login"));
		
		
	}
	
	@Given("^selects health tracker information page$")
	public void select_health_tracker_info() {
		try {
			driver.findElement(By.linkText("Patient Fitness Data")).click();
		} catch (NoSuchElementException e) {
			fail("Link to health tracker information not found.");
		}
		Assert.assertTrue(driver.getTitle().equals("iTrust - Please Select a Patient"));
	}
	
	@When( "^the HCP searches for patient with MID (\\d+)$" )
	public void hcp_searches_for_patient( long patientMID ) throws DBException {
		PatientBean testPatient = patientController.getPatient( patientMID );
		driver.findElement( By.cssSelector( "input[name=\"FIRST_NAME\"]" ) ).sendKeys( testPatient.getFirstName() );
		driver.findElement( By.cssSelector( "input[name=\"LAST_NAME\"]" ) ).sendKeys( testPatient.getLastName() );
		driver.findElement( By.cssSelector( "input[value=\"User Search\"]" ) ).submit();
		assertTrue( patientController.checkPatientExists( patientMID ) );
		this.patientMID = patientMID;
	}
	
	@When( "^selects that patient$" )
	public void hcp_selects_patient() {
		//Assert.assertTrue( driver.getTitle().equals( "iTrust - View Fitness Data" ) );
		fitnessShared.pid = String.valueOf( patientMID );
		assertTrue( fitnessShared.pid != null );
	}
	
	@When( "^selects today's date on the calendar$" )
	public void select_date() {
		Date today = new Date();
		//Calendar dates with no information for the patient have an "Add" button
		//Calendar dates with information for the patient have an "Edit" button
		fitnessShared.date = today;
		
	}
	
	@Then( "^the HCP can enter health metrics: Calories Burned (\\d+); Steps (\\d+); Distance (\\d+); Floors (\\d+); Mins Sedentary (\\d+); Mins Lightly Active (\\d+); Mins Fairly Active (\\d+); Mins Very Active (\\d+); Calories (\\d+)$" )
	public void enter_data( int calsBurned, int steps, double distance, int floors, int minsSed, int minsLA, int minsFA, int minsVA, int cals ) {
		
		WebElement calorieBurnedBox = driver.findElement(By.id("calories_burned"));
		WebElement stepBox = driver.findElement(By.id("steps"));
		WebElement distanceBox = driver.findElement(By.id("distance"));
		WebElement floorBox = driver.findElement(By.id("floors"));
		WebElement minsSedBox = driver.findElement(By.id("mins_sedentary"));
		WebElement minsFABox = driver.findElement(By.id("mins_fairly_active"));
		WebElement minsVABox = driver.findElement(By.id("mins_very_active"));
		WebElement caloriesBox = driver.findElement(By.id("activity_calories"));
		
		calorieBurnedBox.sendKeys("" + calsBurned);
		stepBox.sendKeys("" + steps);
		distanceBox.sendKeys("" + distance);
		floorBox.sendKeys("" + floors);
		minsSedBox.sendKeys("" + minsSed);
		minsFABox.sendKeys("" + minsFA);
		minsVABox.sendKeys("" + minsVA);
		caloriesBox.sendKeys("" + cals);
		
		caloriesBox.submit();
		
		fitnessShared.cals = calsBurned;
		fitnessShared.steps = steps;
		fitnessShared.distance = distance;
		fitnessShared.floors = floors;
		fitnessShared.minsSed = minsSed;
		fitnessShared.minsLA = minsLA;
		fitnessShared.minsFA = minsFA;
		fitnessShared.minsVA = minsVA;
		fitnessShared.activeCals = cals;
		fitnessShared.pid = String.valueOf( sharedPatient.patientID );
		
		fde = new FitnessDataEvent();
		fde.setPatient( fitnessShared.pid );
		fde.setCalories( calsBurned );
		fde.setSteps( steps );
		fde.setDistance( distance );
		fde.setFloors( floors );
		fde.setMinsSed( minsSed );
		fde.setMinsLA( minsLA );
		fde.setMinsFA( minsFA );
		fde.setMinsVA( minsVA );
		fde.setActiveCals( cals );
		
		calControl = new CalendarController();
		calControl.setEvent( fde );
		try {
			calControl.addEvent();
		} catch ( DBException | FormValidationException e ) {
			Assert.fail();
		}
	}
	
	@Then( "^the patient's fitness metrics are updated$" )
	public void patient_updated() {
		Date today = new Date();
		
		DateFormat dateFormatter = new SimpleDateFormat("mm_dd_yyyy");
		Assert.assertTrue(driver.findElements(By.id("add_" + dateFormatter.format(today))).isEmpty());
		Assert.assertTrue(driver.findElements(By.id("view_" + dateFormatter.format(today))).size() == 1);
		WebElement message = driver.findElement(By.id("status_message"));
		Assert.assertTrue(message.getText().equals("The data has successfully been added."));
		
		Fitness testFit = fde.getFitnessData();
		Assert.assertEquals( fitnessShared.pid, testFit.getPid() );
		Assert.assertEquals( fitnessShared.cals, testFit.getCalories() );
		Assert.assertEquals( fitnessShared.steps, testFit.getSteps() );
		Assert.assertEquals( ( int )fitnessShared.distance, ( int )testFit.getDistance() );
		Assert.assertEquals( fitnessShared.minsSed, testFit.getMinsSed() );
		Assert.assertEquals( fitnessShared.minsLA, testFit.getMinsLA() );
		Assert.assertEquals( fitnessShared.minsFA, testFit.getMinsFA() );
		Assert.assertEquals( fitnessShared.minsVA, testFit.getMinsVA() );
		Assert.assertEquals( fitnessShared.activeCals, testFit.getActiveCals() );	
	}
	
	@Then("^the HCP can select View on today's date$")
	public void select_view() {
		try {
			driver.findElement( By.id( "datepicker" ) );
		} catch ( NoSuchElementException e ) {
			Assert.fail();
		}
		
	}
	
	@Then("^the information is populated in textboxes: Calories Burned (\\d+); Steps (\\d+); Distance (\\d+); Floors (\\d+); Mins Sedentary (\\d+); Mins Lightly Active (\\d+); Mins Fairly Active (\\d+); Mins Very Active (\\d+); Calories (\\d+)$")
	public void information_populated( int calsBurned, int steps, double distance, double floors, int minsSed, int minsLA, int minsFA, int minsVA, int cals ) {
		
		WebElement calorieBurnedBox = driver.findElement(By.id("calories_burned"));
		WebElement stepBox = driver.findElement(By.id("steps"));
		WebElement distanceBox = driver.findElement(By.id("distance"));
		WebElement floorBox = driver.findElement(By.id("floors"));
		WebElement minsSedBox = driver.findElement(By.id("mins_sedentary"));
		WebElement minsFABox = driver.findElement(By.id("mins_fairly_active"));
		WebElement minsVABox = driver.findElement(By.id("mins_very_active"));
		WebElement caloriesBox = driver.findElement(By.id("activity_calories"));
		
		Assert.assertTrue(calorieBurnedBox.getText().equals(calsBurned + ""));
		Assert.assertTrue(stepBox.getText().equals(steps + ""));
		Assert.assertTrue(distanceBox.getText().equals(distance + ""));
		Assert.assertTrue(floorBox.getText().equals(floors + ""));
		Assert.assertTrue(minsSedBox.getText().equals(minsSed + ""));
		Assert.assertTrue(minsFABox.getText().equals(minsFA + ""));
		Assert.assertTrue(minsVABox.getText().equals(minsVA + ""));
		Assert.assertTrue(caloriesBox.getText().equals(cals + ""));
		
		Date today = new Date();
		Fitness testFit = fde.getFitnessData();
		
		Assert.assertEquals( fitnessShared.pid, testFit.getPid() );
		Assert.assertEquals( fitnessShared.cals, testFit.getCalories() );
		Assert.assertEquals( fitnessShared.steps, testFit.getSteps() );
		Assert.assertEquals( ( int )fitnessShared.distance, ( int )testFit.getDistance() );
		Assert.assertEquals( fitnessShared.minsSed, testFit.getMinsSed() );
		Assert.assertEquals( fitnessShared.minsLA, testFit.getMinsLA() );
		Assert.assertEquals( fitnessShared.minsFA, testFit.getMinsFA() );
		Assert.assertEquals( fitnessShared.minsVA, testFit.getMinsVA() );
		Assert.assertEquals( fitnessShared.activeCals, testFit.getActiveCals() );
	}
	
	
	@Then("^the HCP can change the data and submit it: Calories Burned (\\d+); Steps (\\d+); Distance (\\d+); Floors (\\d+); Mins Sedentary (\\d+); Mins Lightly Active (\\d+); Mins Fairly Active (\\d+); Mins Very Active (\\d+); Calories (\\d+)$")
	public void change_data_submit(int calsBurned, int steps, double distance, int floors, int minsSed, int minsLA, int minsFA, int minsVA, int cals) {
		
		WebElement calorieBurnedBox = driver.findElement(By.id("calories_burned"));
		WebElement stepBox = driver.findElement(By.id("steps"));
		WebElement distanceBox = driver.findElement(By.id("distance"));
		WebElement floorBox = driver.findElement(By.id("floors"));
		WebElement minsSedBox = driver.findElement(By.id("mins_sedentary"));
		WebElement minsFABox = driver.findElement(By.id("mins_fairly_active"));
		WebElement minsVABox = driver.findElement(By.id("mins_very_active"));
		WebElement caloriesBox = driver.findElement(By.id("activity_calories"));
		
		calorieBurnedBox.sendKeys("" + calsBurned);
		stepBox.sendKeys("" + steps);
		distanceBox.sendKeys("" + distance);
		floorBox.sendKeys("" + floors);
		minsSedBox.sendKeys("" + minsSed);
		minsFABox.sendKeys("" + minsFA);
		minsVABox.sendKeys("" + minsVA);
		caloriesBox.sendKeys("" + cals);
		
		caloriesBox.submit();
		
		select_view();
		information_populated(calsBurned, steps, distance, floors, minsSed, minsFA, minsVA, cals);
	
		fitnessShared.cals = calsBurned;
		fitnessShared.steps = steps;
		fitnessShared.distance = distance;
		fitnessShared.floors = floors;
		fitnessShared.minsSed = minsSed;
		fitnessShared.minsLA = minsLA;
		fitnessShared.minsFA = minsFA;
		fitnessShared.minsVA = minsVA;
		fitnessShared.activeCals = cals;
		
		fde.setPatient( fitnessShared.pid );
		fde.setCalories( calsBurned );
		fde.setSteps( steps );
		fde.setDistance( distance );
		fde.setFloors( floors );
		fde.setMinsSed( minsSed );
		fde.setMinsLA( minsLA );
		fde.setMinsFA( minsFA );
		fde.setMinsVA( minsVA );
		fde.setActiveCals( cals );
		
		Date today = new Date();
		Fitness testFit = fde.getFitnessData();
		
		Assert.assertEquals( fitnessShared.pid, testFit.getPid() );
		Assert.assertEquals( fitnessShared.cals, testFit.getCalories() );
		Assert.assertEquals( fitnessShared.steps, testFit.getSteps() );
		Assert.assertEquals( ( int )fitnessShared.distance, ( int )testFit.getDistance() );
		Assert.assertEquals( fitnessShared.minsSed, testFit.getMinsSed() );
		Assert.assertEquals( fitnessShared.minsLA, testFit.getMinsLA() );
		Assert.assertEquals( fitnessShared.minsFA, testFit.getMinsFA() );
		Assert.assertEquals( fitnessShared.minsVA, testFit.getMinsVA() );
		Assert.assertEquals( fitnessShared.activeCals, testFit.getActiveCals() );
	}
	
	@Then("^the HCP can upload a file$")
	public void HCP_upload_file() {
		
		String [] line = null;
		FileReader fReader = null;
		BufferedReader bReader = null;
		StringBuilder sb = new StringBuilder();
		Date today = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("mm/dd/yyyy");
		
		//Used assistance from https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
		try {
			fReader = new FileReader(DATA_FILE);
			bReader = new BufferedReader(fReader);
			
			sb.append(bReader.readLine());
			sb.append(bReader.readLine());
			line = bReader.readLine().split(",");
			line[0] = dateFormatter.format(today);
			for(int i = 0; i < line.length; i++) {
				sb.append(line[i]);
			}
		} catch(FileNotFoundException e) {
			fail("Error opening file to read");
		} catch(IOException e) {
			fail("Error reading file");
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				fail("Failed to close reader");
			}
		}
		FileWriter fWriter = null;
		BufferedWriter bWriter = null;
		try {
			fWriter = new FileWriter(DATA_FILE);
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(sb.toString());
		} catch(IOException e) {
			fail("Error writing to file");
		} finally {
			try {
				bWriter.close();
			} catch (IOException e) {
				fail("Failed to close writer");
			}
		}
		
		WebElement upload = driver.findElement(By.name("fitnessFile"));
		WebElement sendFile = driver.findElement(By.id("sendFile"));
		upload.sendKeys(DATA_FILE);
		sendFile.click();
		
		//Help from http://stackoverflow.com/questions/18076454/how-to-test-junit-test-case-for-primefaces-uploadedfile-component
		File f = new File( "testing-files/fitness_data/FitBitUploadData.csv");
	    InputStream stream = null;
		try {
			stream = new FileInputStream( f );
		} catch (FileNotFoundException e2) {
			Assert.fail();
			e2.printStackTrace();
		}
	    FileUploadEvent e = Mockito.mock( FileUploadEvent.class );
	    UploadedFile file = Mockito.mock( UploadedFile.class );
	    Mockito.when( file.getFileName() ).thenReturn( f.getName() );
	    Mockito.when( file.getContentType() ).thenReturn( "application/octet-stream" );
	    try {
			Mockito.when( file.getInputstream() ).thenReturn( stream );
		} catch (IOException e1) {
			Assert.fail();
		}
	    Mockito.when( e.getFile() ).thenReturn( file );
	    this.file = file;
	    fUp.setFile( file );
	}
		
	
	@Then("^the information is shown in the calendar$")
	public void info_is_shown_in_calendar() {
		
		//Option to view the information on today's date should be present
		//There should not be any option to add data to the day
		Date today = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("mm_dd_yyyy");
		Assert.assertTrue(driver.findElements(By.id("add_" + dateFormatter.format(today))).isEmpty());
		Assert.assertTrue(driver.findElements(By.id("view_" + dateFormatter.format(today))).size() == 1);
		
		fUp.upload();
		Assert.assertTrue( fUp.getFile() != null );
		
		Fitness testFit = fde.getFitnessData();
		
		Assert.assertEquals( 2455, testFit.getCalories() );
		Assert.assertEquals( 11017, testFit.getSteps() );
		Assert.assertEquals( ( int )( 4.89 ), ( int )testFit.getDistance() );
		Assert.assertEquals( 970, testFit.getMinsSed() );
		Assert.assertEquals( 463, testFit.getMinsLA() );
		Assert.assertEquals( 2, testFit.getMinsFA() );
		Assert.assertEquals( 5, testFit.getMinsVA() );
		Assert.assertEquals( 1448, testFit.getActiveCals() );
		
	}
	
	@When("^the HCP enters negative health metrics: Calories Burned (\\d+); Steps (\\d+); Distance (\\d+); Floors (\\d+); Mins Sedentary (\\d+); Mins Lightly Active (\\d+); Mins Fairly Active (\\d+); Mins Very Active (\\d+); Calories (\\d+)$")
	public void hcp_enters_negative_metrics(String metric, int calsBurned, int steps, double distance, int floors, int minsSed, int minsLA, int minsFA, int minsVA, int cals ) {
		fitnessShared.cals = calsBurned;
		fitnessShared.steps = steps;
		fitnessShared.distance = distance;
		fitnessShared.floors = floors;
		fitnessShared.minsSed = minsSed;
		fitnessShared.minsLA = minsLA;
		fitnessShared.minsFA = minsFA;
		fitnessShared.minsVA = minsVA;
		fitnessShared.activeCals = cals;
	}
	
	@Then("^it will fail to be submitted: failed (.+)$")
	public void fail_to_be_submitted(String metric) {
		try {
			fde.setCalories( fitnessShared.cals );
			fde.setSteps( fitnessShared.steps );
			fde.setDistance( fitnessShared.distance );
			fde.setFloors( fitnessShared.floors );
			fde.setMinsSed( fitnessShared.minsSed );
			fde.setMinsLA( fitnessShared.minsLA );
			fde.setMinsFA( fitnessShared.minsFA );
			fde.setMinsVA( fitnessShared.minsVA );
			fde.setActiveCals( fitnessShared.activeCals );
			Assert.fail();
		} catch ( Exception e ) {
			Assert.assertTrue( true );
		}
	}
}

*/
