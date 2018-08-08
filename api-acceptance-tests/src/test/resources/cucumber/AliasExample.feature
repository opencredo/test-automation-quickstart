@demo-alias
Feature: Demonstrate use of aliases to keep tests readable with complex data

  Scenario: Send a message between two users
    Given the following users:
      | Bob  |
      | Dave |
    When I log in as "Bob"
    And I send a message to "Dave"
    Then "Dave" should have received a message from "Bob"