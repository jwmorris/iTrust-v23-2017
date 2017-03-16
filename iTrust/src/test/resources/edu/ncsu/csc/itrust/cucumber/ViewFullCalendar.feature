#Author: David Seybold daseybol
Feature: View Full Calendar
As a patient
I want to be able to view the full calendar

Background:
Given I am logged in as a patient

Scenario: Open page
When I click Full Calendar
Then The calendar is loaded
