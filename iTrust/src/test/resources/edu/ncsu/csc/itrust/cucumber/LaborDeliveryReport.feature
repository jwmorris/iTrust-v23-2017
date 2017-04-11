Feature: Labor and Delivery Report
	As an OBGYN
	I want to be able to run a report on a patients prior pregnancy
	So that I can have a complete report detailing their pregnancy medical history

Scenario: Invalid Patient
	Given Kathyrn Evans types in Princess Leach
	When Kathyrn Evans then enters Princess Peach
	Then Kathyrn Evans recieves a full report of pregnany info

Scenario: Not an Obstetrics Patient
	Given Kathyrn Evans searchs for Baby Programmer
	When Kathyrn Evans attempts to run a report for Baby Programmer
	Then the report is not generated as Baby Programmer is not an obstetrics patient