#Author adwhite4
# Make sure that when a patient requests an appointment, their request is sent to the correct HCP

Feature: Appointment Request Sent To HCP
As an HCP
I want appointment requests from patients that I am assigned to the be sent to me
So I will be able to approve or deny requests from my patients

Scenario Outline: Test Request Sent
	Given patient <patient> logs in
	When patient makes an appointment request in the future
	And patient logs out
	And HCP <hcp> logs in
	Then the request appears

Examples:
	| patient   | hcp        |
	| 000000001 | 9000000003 |
	| 000000002 | 9000000003 |
