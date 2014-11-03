@api-demo
Feature: Demonstrate the API acceptance testing framework

  Scenario: Simple API interaction
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for user "tristanmccarthy"
    Then there the repository "test-automation-quickstart" should be present in the repository list