@ui-demo-simple @smoke
Feature: Demonstrate the UI test framework

  Scenario: Simple interaction with a web page
    Given I am on the Google search page
    When I search for "OpenCredo"
    Then the site "www.opencredo.com" should be present in the results

  @ignore
  Scenario: Test which will not be run due to the tag
    Given I am on the Google search page
    When I search for "OpenCredo"
    Then the site "www.opencredo.com" should be present in the results