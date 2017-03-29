Feature: Obstetrics Office Visit
	As an OBGYN
	I want to be able to add office visits for Obstetric patients and view/edit prior office visits
	So that I can keep track of my visits with patients that are pregnant as well as keep track of upcoming obstetrics visits

Scenario: HCP Tries to create an obstetrics visit
	Given Kelly Doctor enters PID 3001
	When she correctly enters Random Person's PID
	Then Dr. Doctor is prompted to create a regualr office visit.

Scenario: Full Office Visit
	Given Kathyrn Evans selects PID 52
	And she correctly changes to Random Person's PID
	And adds a documented visit
	And adds ultrasound data
	When saves those records and sets Person's next appointment to July 4th
	Then Random Person's next appointment is moved to July 5th

Scenario: Edit Office Visit
	Given Kathyrn Evans selects PID 5
	And she correctly changes to Random Person's PID
	When Dr. Evans edits the documented visit
	Then Random Person's visit info is changed

Scenario: Pregnancies in 20 weeks
	Given Kathyrn Evans selects Princess Peach's PID
	And Princess Peach had a prior pregnancy 20 weeks ago
	When Dr. Evans initilizes Princess Peach
	Then Princess Peach's displayed intiliazation is the current one

Scenario: RH - Flag
	Given Kathyrn Evans selects Andy Programmer's PID
	And Programmer has their RH - Flag set
	When Programmer has their 29 weeks pregnant visit
	Then a notice is displayed that they need an RH immune globulin shot

Scenario: Invalid Calendar
	Given Kathyrn Evans selects Care Needs' PID
	When Dr. Evans tries to set Needs' next appointment
	Then Care Needs' calendar cannot be accessed and their appointment is July 20th

Scenario: Max Weeks Pregnant
	Given Kathyrn Evans selects Random Person's PID
	When Dr. Evans tries to set Person's next appointment at 42 weeks
	Then Random Person's next appiontment is a Childbirth Hospital Visit