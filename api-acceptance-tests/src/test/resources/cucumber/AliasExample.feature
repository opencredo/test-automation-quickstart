@ui-demi-alias
Feature: Demonstrate use of aliases to keep tests readable with complex data

  @ignore
  Scenario: (Without aliases) Send a message between two users
    Given I have registered as a new user
    And another registered user
    When I log in
    And I message the other user
    Then the other user should have received a message from me

  Scenario; (With aliases) Send a message between two users
    Given the following users:
      | alias |
      | Bob   |
      | Dave  |
    When I log in as "Bob"
    And I send a message to "Dave"
    Then "Dave should have received a message from "Bob"