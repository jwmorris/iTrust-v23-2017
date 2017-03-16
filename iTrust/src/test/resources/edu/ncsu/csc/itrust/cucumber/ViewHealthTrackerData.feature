#author wemaxey
Feature: Add Health Tracker Data [UC87]
As an HCP
I want to be able to view Health Tracker Data for a Patient
To be able to get a better overview of fitness trends for a Patient

Scenario Outline: View Step Count Graph
	Given standard data is loaded
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information
	When the HCP searches for patient with MID <patientmid>
	And selects that patient 
	And the patient has step counts <day1> <day2> <day3>
	#And selects view graphs
	And the HCP enters start date <startDate> and endDate <endDate>
	And selects a step count graph
	Then a step count graph is displayed for the time frame with steps 
Examples:
	| mid   	| pw         | patientmid	| day1	| day2	| day3	| startDate		| endDate		|
	| 900000000 | pw		 | 1			| 780	| 1270	| 820	| 02/03/2017	| 02/05/2017	|
	| 900000000 | pw		 | 5			| 870	| 1207	| 802	| 02/01/2017	| 02/03/2017	|
	
Scenario Outline: View Step Count Delta Graph
	Given standard data is loaded
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information
	When the HCP searches for patient with MID <patientmid>
	And selects that patient 
	And the patient has step counts <day1> <day2> <day3>
	#And selects view graphs
	And the HCP enters start date <startDate> and endDate <endDate>
	And selects a step count delta graph
	Then a step count delta graph is displayed for the time frame with step count deltas
Examples:
	| mid   	| pw         | patientmid	| day1	| day2	| day3	| startDate		| endDate		|
	| 900000000 | pw		 | 1			| 780	| 1270	| 820	| 02/03/2017	| 02/05/2017	|
	| 900000000 | pw		 | 5			| 870	| 1207	| 802	| 02/01/2017	| 02/03/2017	|
	
Scenario Outline: View Step Count Averages Chart
	Given standard data is loaded
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information
	When the HCP searches for patient with MID <patientmid>
	And selects that patient 
	And the patient has step counts <day1> <day2> <day3>
	#And selects view graphs
	And selects a step count averages chart
	Then a step count averages chart is displayed
Examples:
	| mid   	| pw         | patientmid	| day1	| day2	| day3	|
	| 900000000 | pw		 | 1			| 780	| 1270	| 820	|
	| 900000000 | pw		 | 5			| 870	| 1207	| 802	|