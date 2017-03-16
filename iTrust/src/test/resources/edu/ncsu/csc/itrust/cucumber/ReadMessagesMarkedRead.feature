#Author: adwhite4
# make sure that read messages are marked read when navigating back using the back button

Feature: Read Messages Marked Read
As a user
I want messages to be marked as read after reading without requring a page refresh
So I will be able to accurate see what messages are unread at any given point

Scenario: Test Message Read
	Given user logs in as an HCP
	When user navigates to their message inbox
	And user reads an unread message
	And user presses the navigate back button
	Then the read message is no longer bolded