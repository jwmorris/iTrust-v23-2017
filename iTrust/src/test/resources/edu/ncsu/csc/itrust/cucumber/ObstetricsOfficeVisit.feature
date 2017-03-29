Feature: Obstetrics Office Visit
	As an OBGYN
	I want to be able to add office visits for Obstetric patients and view/edit prior office visits
	So that I can keep track of my visits with patients that are pregnant as well as keep track of upcoming obstetrics visits

Scenario: HCP Tries to create an obstetrics visit
	Given Kelly Doctor enters PID 3001
	And she correctly enters Princess Peach's PID
	When Dr. Doctor adds an obstetrics visit
	Then Dr. Doctor is prompted to created a regualr office visit.

Scenario: Full Office Visit
	Given Kathyrn Evans selects PID 52
	And she correctly changes to Princess Peach's PID
	And adds a documented visit
	And adds a ultrasound data
	When saves those records and sets Peach's next appointment to July 4th
	Then Princess Peach's next appointment is moved to July 5th

Scenario: Edit Office Visit
	Given Kathyrn Evans selects PID 5
	And she correctly changes to Princess Peach's PID
	When Dr. Evans edits the documented visit
	Then Princess Peach's visit info is changed

Scenario: S4????
	Given Kathyrn Evans selects PID 52
	And she correctly changes to Princess Peach's PID
	And adds a documented visit
	And adds a ultrasound data
	When saves those records and sets Peach's next appointment to July 4th
	Then Princess Peach's next appointment is moved to July 5th

Scenario: RH - Flag
	Given Kathyrn Evans selects Princess Peach's PID
	And Peach has her RH - Flag set
	When Peach has her 28+ week visit
	Then a notice is displayed that she needs an RH immune globulin shot

Scenario: Invalid Calendar
	Given Kathyrn Evans selects Princess Peach's PID
	When Dr. Evans tries to set Peach's next appointment
	Then Princess Peach's calendar cannot be accessed and her appointment is July 20th

Scenario: Invalid Calendar
	Given Kathyrn Evans selects Princess Peach's PID
	When Dr. Evans tries to set Peach's next appointment at 42 weeks
	Then Princess Peach's next appiontment is a Childbirth Hospital Visit