package edu.ncsu.csc.itrust.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ObstetricsInitilizationStepDefs {
	
	@Before public void loginOBGYN() {
		//set Mid to Kathyrn Evans
	}
	
	@Given("^Kathyrn Evans enters PID 52$")
	public void choose_invalid_patient() {
		//catch the invalid patient
	}
	
	@And("^she correctly enters Princess Peach's PID$")
	public void choose_peach_pid() {
		//select Peach Pid
	}
	
	@And("^she makes Princess Peach eligible for obstetric care$")
	public void make_eligible() {
		//make peach eligible
	}
	
	@When("^Dr. Evans initializes a current pregnancy$")
	public void initialize_peach() {
		//initialize peach
	}
	
	@Then("^Princess Peach's current pregnancy is displayed$")
	public void check_initilization() {
		//check peach's initilization
	}
	
	@Given("^Kathyrn Evans selects PID 1$")
	public void choose_wrong_patient() {
		//correct patient is not chosen reprompt
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
	
	
}