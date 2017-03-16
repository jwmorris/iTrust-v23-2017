#author wemaxey and daseybol
Feature: Add Health Tracker Data [UC87]
As an HCP
I want to be able to add Health Tracker Data for a Patient
To have more health metrics to better understand our patients

Scenario Outline: Manually enter data
	Given the standard data is populated
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information page
	When the HCP searches for patient with MID <patientmid>
	And selects that patient
	And selects today's date on the calendar
	Then the HCP can enter health metrics: Calories Burned <cb>; Steps <steps>; Distance <distance>; Floors <floors>; Mins Sedentary <minsSed>; Mins Lightly Active <minsLA>; Mins Fairly Active <minsFA>; Mins Very Active <minsVA>; Calories <cals>
	And the patient's fitness metrics are updated

Examples:
	| mid   	 | pw        | patientmid	| cb	| distance	| floors	| minsSed	| minsLA	| minsFA	| minsVA	| cals	|
	| 9000000000 | pw		 | 1			| 900	| 1.6		| 1.2		| 750		| 85		| 35		| 30		| 2000	|
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| 78		| 40		| 45		| 1800	|

Scenario Outline: View data
	Given the standard data is populated
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information page
	When the HCP searches for patient with MID <patientmid>
	And selects that patient
	And selects today's date on the calendar
	Then the HCP can enter health metrics: Calories Burned <cb>; Steps <steps>; Distance <distance>; Floors <floors>; Mins Sedentary <minsSed>; Mins Lightly Active <minsLA>; Mins Fairly Active <minsFA>; Mins Very Active <minsVA>; Calories <cals>
	And the patient's fitness metrics are updated
	And the HCP can select View on today's date
	And the information is populated in textboxes: Calories Burned <cb>; Steps <steps>; Distance <distance>; Floors <floors>; Mins Sedentary <minsSed>; Mins Lightly Active <minsLA>; Mins Fairly Active <minsFA>; Mins Very Active <minsVA>; Calories <cals>
	
Examples:
	| mid   	 | pw        | patientmid	| cb	| distance	| floors	| minsSed	| minsLA	| minsFA	| minsVA	| cals	|
	| 9000000000 | pw		 | 1			| 900	| 1.6		| 1.2		| 750		| 85		| 35		| 30		| 2000	|
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| 78		| 40		| 45		| 1800	|	
	
	
Scenario Outline: Edit data
	Given the standard data is populated
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information page
	When the HCP searches for patient with MID <patientmid>
	And selects that patient
	And selects today's date on the calendar
	Then the HCP can enter health metrics: Calories Burned <cb>; Steps <steps>; Distance <distance>; Floors <floors>; Mins Sedentary <minsSed>; Mins Lightly Active <minsLA>; Mins Fairly Active <minsFA>; Mins Very Active <minsVA>; Calories <cals>
	And the patient's fitness metrics are updated
	And the HCP can change the data and submit it: Calories Burned <cb2>; Steps <steps2>; Distance <distance2>; Floors <floors2>; Mins Sedentary <minsSed2>; Mins Lightly Active <minsLA2>; Mins Fairly Active <minsFA2>; Mins Very Active <minsVA2>; Calories <cals2>
	
Examples:
	| mid   	 | pw         | patientmid	| cb	| distance	| floors	| minsSed	| minsLA	| minsFA	| minsVA	| cals	| cb2	| distance2	| floors2	| minsSed2	| minsLA2	| minsVA2	| cals2	|
	| 9000000000 | pw		 | 1			| 900	| 1.6		| 1.2		| 750		| 85		| 35		| 30		| 2000	| 400	| 2.0		| 1.6		| 250		| 25		| 90		| 5000	|
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| 78		| 30		| 45		| 1800	| 900	| 1.6		| 1.2		| 750		| 85		| 60		| 300	|	
	
Scenario Outline: Upload a file
	Given the standard data is populated
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information page
	When the HCP searches for patient with MID <patientmid>
	And selects that patient
	Then the HCP can upload a file
	And the information is shown in the calendar
	
Examples:

	| mid   	 | pw         | patientmid	|
	| 9000000000 | pw		 | 1			|
	| 9000000000 | pw		 | 5			|
	
Scenario Outline: Adding negative fitness data
	Given the standard data is populated
	And an HCP with MID <mid> and password <pw> is logged in
	And selects health tracker information page
	When the HCP searches for patient with MID <patientmid>
	And selects that patient
	And selects today's date on the calendar
	And the HCP enters negative health metrics: Calories Burned <cb>; Steps <steps>; Distance <distance>; Floors <floors>; Mins Sedentary <minsSed>; Mins Lightly Active <minsLA>; Mins Fairly Active <minsFA>; Mins Very Active <minsVA>; Calories <cals>
	Then it will fail to be submitted: failed <metric>

Examples:	
	| mid   	 | pw        | patientmid	| cb	| distance	| floors	| minsSed	| minsFA	| minsLA	| minsVA	| cals	| metric                 |
	| 9000000000 | pw		 | 1			| 900	| -1.6		| 1.2		| 750		| 80		| 85		| 30		| 2000	| distance               |
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| -5.4		| 680		| 85		| 78		| 45		| 1800	| floors                 |
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| -680		| 85		| 78		| 45		| 1800	| Minutes Lightly Active |
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| -85		| -78		| 45		| 1800	| Minutes Fairly Active  |
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| 85		| 78		| -45		| 1800	| Minutes Very Active    |
	| 9000000000 | pw		 | 5			| 1100	| 2.3		| 5.4		| 680		| 85		| 78		| 45		| -1800	| Calories               |

