package edu.ncsu.csc.itrust.cucumber;

import java.sql.Date;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Assert;
import org.mockito.Mockito;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitController;
import edu.ncsu.csc.itrust.controller.obstetrics.ChildbirthVisitForm;
import edu.ncsu.csc.itrust.controller.obstetrics.ObstetricsVisitController;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisit;
import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

public class ChildbirthHospitalVisitStepDefs {
	private DataSource ds;
	private TestDataGenerator gen;
	private SessionUtils utils;
	private DAOFactory factory;
	private PatientDAO patientDAO;
	private PatientBean patient;
	private ChildbirthVisitForm peachErForm;
	private ChildbirthVisitController peachErController;
	private ChildbirthVisitForm peachRegForm;
	private ChildbirthVisitController peachRegController;
	
	public ChildbirthHospitalVisitStepDefs() {
		this.factory = TestDAOFactory.getTestInstance();
		utils = Mockito.mock( SessionUtils.class );
		this.gen = new TestDataGenerator();
		this.ds = ConverterDAO.getDataSource();
		this.patientDAO = factory.getPatientDAO();
		this.peachRegController = new ChildbirthVisitController( ds, factory, utils );
	}
	

    @Given("^Princess Peach had a prior Obstetrics visit that scheduled a childbirth hospital visit$")
    public void princess_peach_had_a_prior_obstetrics_visit_that_scheduled_a_childbirth_hospital_visit() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		
    	peachRegForm = new ChildbirthVisitForm( peachRegController, utils );
    	Assert.assertFalse( peachRegForm.isEr() );

    }

    @Given("^Princess Peach delivers her baby before reaching the hospital$")
    public void princess_peach_delivers_her_baby_before_reaching_the_hospital() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		peachErController = new ChildbirthVisitController( ds, factory, utils );
    	peachErForm = new ChildbirthVisitForm( peachErController, utils );
        peachErForm.setEr( true );
        peachErForm.setPid( 21 );
        Assert.assertTrue( peachErForm.isEr() );
    }

    @Given("^Kathyrn Evans searchs for Princess Leach$")
    public void kathyrn_evans_searchs_for_princess_leach() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		Assert.assertEquals( 0, patientDAO.searchForPatientsWithName( "Princess" , "Leach" ).size() );
    }

    @Given("^Kathyrn Evans searchs for Amelia Davidson$")
    public void kathyrn_evans_searchs_for_amelia_davidson() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		patient = patientDAO.searchForPatientsWithName( "Amelia" , "Davidson" ).get( 0 );
		Assert.assertTrue( "Amelia".equals( patient.getFirstName() ) );
    }

    @When("^Princess Peach has her delivery$")
    public void princess_peach_has_her_delivery() throws Throwable {
    	
    	peachRegForm.setPid( 21 );
    	Assert.assertEquals( 21, peachRegForm.getPid() );
    }

    @When("^Kathryn Evans fully documents the emergency room visit$")
    public void kathryn_evans_fully_documents_the_emergency_room_visit() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		
    	peachErForm.setAmtEpidural( "0" );
    	peachErForm.setAmtMagnesium( "1" );
    	peachErForm.setAmtNitrous( "0" );
    	peachErForm.setAmtPethidine( "0" );
    	peachErForm.setAmtPitocin( "0" );
    	peachErForm.setDeliveryType( "vaginal delivery" );
    	peachErForm.submitChildbirthVisit();
    	Assert.assertTrue( peachErController.getChildbirthByID( "1" ).isEr() );
    }

    @When("^Kathyrn Evans correctly enters Princess Peach$")
    public void kathyrn_evans_correctly_enters_princess_peach() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		long id = 21;
		if((id <=0) ||(id > 8999999999L) ){
			Assert.fail("MID should be real");
		} else {
			Assert.assertEquals( id, patientDAO.getPatient( id ).getMID() );
		}
    }

    @When("^Kathyrn Evans does not confirm her selecction and correctly enters Princess Peach$")
    public void kathyrn_evans_does_not_confirm_her_selecction_and_correctly_enters_princess_peach() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		long id = 21;
		if((id <=0) ||(id > 8999999999L) ){
			Assert.fail("MID should be real");
		} else {
			patient = patientDAO.getPatient( id );
			Assert.assertEquals( id, patient.getMID() );
		}
    }

    @Then("^Kathryn Evans fully documents the childbirth$")
    public void kathryn_evans_fully_documents_and_edits_nitrous_oxide() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		
    	peachRegForm.setAmtEpidural( "0" );
    	peachRegForm.setAmtMagnesium( "1" );
    	peachRegForm.setAmtNitrous( "0" );
    	peachRegForm.setAmtPethidine( "0" );
    	peachRegForm.setAmtPitocin( "0" );
    	//peachRegForm.setChildbirthID( 1 );
    	peachRegForm.setDeliveryType( "caesarean section" );
    	
    	peachRegForm.setBabyId( 1 );
    	peachRegForm.setBabyDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
    	peachRegForm.setTimeHour( "06" );
    	peachRegForm.setTimeMinute( "00" );
    	peachRegForm.setTimeMerridean( "pm" );
    	peachRegForm.setTime();
    	peachRegForm.submitChildbirthVisit();
    	peachRegForm.submitBaby();
    	Assert.assertEquals( 1, peachRegForm.getBabies().size() );
       
    }
    
    @And( "^edits the amount of Nitrous Oxide administered$" )
    public void edits_the_amount_of_nitrous_oxide_administered() throws Throwable {
    	peachRegForm.setAmtNitrous( "50" );
    	peachRegForm.submitChildbirthVisit();
    	Assert.assertTrue( peachRegForm.getAmtNitrous().equals( "50" ) );
    }

    @Then("^the delivery method is vaginal delivery$")
    public void the_delivery_method_is_vaginal_delivery() throws Throwable {
    	Assert.assertTrue( peachErForm.getDeliveryType().equals( "vaginal delivery" ) );
    }
    
    @And( "^Kathyrn Evans deletes Nitrous Oxide$" )
    public void kathyrn_evans_deletes_nitrous_oxide() throws Throwable {
    	peachErForm.setAmtNitrous( "" );
    	Assert.assertTrue( peachErForm.getAmtNitrous().equals( "" ) );
    }

    @Then("^Kathyrn Evans fully documents a childbirth hospital visit$")
    public void kathyrn_evans_fully_documents_a_childbirth_hospital_visit() throws Throwable {
    	gen.clearAllTables();
		gen.standardData();
		
    	ChildbirthVisitForm form = new ChildbirthVisitForm( new ChildbirthVisitController( ds, factory, utils ), utils );
        
    	gen.clearAllTables();
		gen.standardData();
    	
    	form.setPid( 21 );
        form.setChildbirthID( 0 );
        form.setAmtEpidural( "30" );
        form.setAmtMagnesium( "50" );
        form.setAmtNitrous( "0" );
        form.setAmtPethidine( "0" );
        form.setAmtPitocin( "0" );
        form.setDeliveryType( "caesarean section" );
        form.setEr( false );
        form.submitChildbirthVisit();
        Assert.assertEquals( 1, form.getChildbirthID() );
    }

}