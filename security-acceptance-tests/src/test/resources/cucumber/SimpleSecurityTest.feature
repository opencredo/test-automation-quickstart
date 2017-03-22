@security-demo
Feature: Demonstrate the Security test framework

  Scenario: Simple security test
    Given I am on the Google search page
    When I search for "OpenCredo"
    Then the site "www.opencredo.com" should be present in the results
    And the number of risks per category should not be greater than
      | low | medium | high |
      | 30  | 10     | 0    |