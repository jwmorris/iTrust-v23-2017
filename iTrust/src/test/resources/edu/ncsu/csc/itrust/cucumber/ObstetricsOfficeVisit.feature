Feature: Obstetrics Office Visit
	As an OBGYN
	I want to be able to add office visits for Obstetric patients and view/edit prior office visits
	So that I can keep track of my visits with patients that are pregnant as well as keep track of upcoming obstetrics visits

Scenario: HCP Tries to create an obstetrics visit
	Given Kelly Doctor enters PID 9000000021
	When she correctly enters Random Persons PID
	Then Dr Doctor is prompted to create a regualr office visit

Scenario: Full Office Visit
	Given Kathyrn Evans selects Bad Horses PID
	And she correctly changes to Random Persons PID
	And adds a documented visit
	And adds ultrasound data
	When saves those records and sets Persons next appointment to Independence Day
	Then Random Persons next appointment is moved to next day

Scenario: Edit Office Visit
	Given Kathyrn Evans selects Baby Programmers PID
	And she correctly changes to Random Persons PID
	When Dr Evans edits the documented visit
	Then Random Persons visit info is changed

Scenario: Pregnancies in 20 weeks
	Given Kathyrn Evans selects Princess Peachs PID
	And Princess Peach had a prior pregnancy 20 weeks ago
	When Dr Evans initilizes Princess Peach
	Then Princess Peachs displayed intiliazation is the current one

Scenario: RH - Flag
	Given Kathyrn Evans selects Andy Programmers PID
	And Programmer has their RH Flag set
	When Programmer has enough weeks for an rh pregnant visit
	Then a notice is displayed that they need an RH immune globulin shot

Scenario: Invalid Calendar
	Given Kathyrn Evans selects Random Persons PID
	When Dr Evans tries to set Programmers next appointment
	Then Programmers calendar cannot be accessed and their appointment is set for next week

Scenario: Max Weeks Pregnant
	Given Kathyrn Evans selects Random Persons PID
	When Dr Evans tries to set Persons next appointment at max weeks
	Then Random Persons next appiontment is a Childbirth Hospital Visit