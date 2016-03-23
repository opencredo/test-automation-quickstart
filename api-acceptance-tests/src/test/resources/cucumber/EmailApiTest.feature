@email-demo
Feature: Demonstrate the Email API acceptance testing framework

  # Before running these tests, adjust the email settings in EmailAdaptor.java to your email account

  Scenario: Simple email interaction
    Given test emails have been deleted from my account
    When I send a test email
    Then there should be '1' test email in my inbox