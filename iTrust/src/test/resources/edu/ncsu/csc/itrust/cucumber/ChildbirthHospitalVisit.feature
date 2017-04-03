Feature: Childbirth Hospital Visit
	As an OBGYN
	I want to be able to add childbirth hospital visits for both office and ER visits and view an Obstetric patient's prior obstetrics care history 
	So that I can document baby deliveries as well as create records for the new child

Scenario: Delivering and editing a Childbirth Visit
	Given Princess Peach had a prior Obstetrics visit that scheduled a childbirth hospital visit
	When Princess Peach has her delivery
	Then Kathryn Evans fully documents the childbirth
	And edits the amount of Nitrous Oxide administered
	
Scenario: Emergency Room Delivery and Deleting fields
	Given Princess Peach delivers her baby before reaching the hospital
	When Kathryn Evans fully documents the emergency room visit
	Then the delivery method is vaginal delivery
	And Kathyrn Evans deletes Nitrous Oxide
	
Scenario: Invalid Patient Name
	Given Kathyrn Evans searchs for Princess Leach
	When Kathyrn Evans correctly enters Princess Peach
	Then Kathyrn Evans fully documents a childbirth hospital visit
	
Scenario: Wrong Selection
	Given Kathyrn Evans searchs for Amelia Davidson
	When Kathyrn Evans does not confirm her selecction and correctly enters Princess Peach
	Then Kathyrn Evans fully documents a childbirth hospital visit
	