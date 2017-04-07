Feature: Labor and Delivery Report
	As an OBGYN
	I want to be able to run a report on a patients prior pregnancy
	So that I can have a complete report detailing their pregnancy medical history

Scenario: Invalid Patient
	Given Kathyrn Evans searchs for Princess Leach
	When Kathyrn Evans correctly enters Princess Peach
	Then Kathyrn Evans fully documents a childbirth hospital visit

Scenario: Not an Obstetrics Patient
	Given Kathyrn Evans searchs for Baby Programmer
	When Kathyrn Evans attempts to run a report fot Baby Programmer
	Then the report is not generated as Baby Programmer is not an obstetrics patient