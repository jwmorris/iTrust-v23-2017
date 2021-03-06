Feature: Obstetrics Initilization
	As an OBGYN
	I want to be able to initialize/edit pregnancies for Obstetric patients and view/edit prior pregnancies
	So that I can keep track of my patients that are pregnant as well as keep track of prior pregnancy history
	
Scenario: Initialize Obstetrics Patient
	Given Kathyrn Evans enters PID 52
	And she correctly enters Princess Peach's PID
	And she makes Princess Peach eligible for obstetric care
	When Dr. Evans initializes a current pregnancy
	Then Princess Peach's current pregnancy is displayed
	
	
Scenario: Editing Prior Pregnancies
	Given Kathyrn Evans selects PID 1
	And reselects PID 2
	And she edits a prior pregnancy by changing total weeks pregnant to forty
	When Dr. Evans edits a prior pregnancy by changing total weeks pregnant to 40
	Then Princess Peach's prior pregnancy is updated