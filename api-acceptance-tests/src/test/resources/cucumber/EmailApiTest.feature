@api-demo
Feature: Demonstrate the Email API acceptance testing framework

  Scenario: Simple email interaction
    Given test emails have been deleted from my account
    When I send a test email
    Then there should be '1' test email in my inbox