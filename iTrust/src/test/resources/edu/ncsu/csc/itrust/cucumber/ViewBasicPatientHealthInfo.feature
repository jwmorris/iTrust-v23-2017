#author: hmukhta
Feature: View Basic Patient Health Information
	As an HCP
	I want to view a patient's basic health information
	So I can get a better understanding on their health history
	
Background:
	Given an HCP user is logged in
	And an HCP user nagivates to the view basic health info page for a patient
	
#UC52 Viewing a Patient's Basic Health Info	
Scenario: HCP views a patient's health info - Web Interface
	When an HCP user enters a patient's mid or name
	And an HCP user clicks on the patient
	Then the HCP user is on the basic health information page of the patient selected
	
	