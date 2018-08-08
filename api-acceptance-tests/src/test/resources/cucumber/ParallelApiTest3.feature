@api-demo
Feature: Demonstrate the API acceptance testing framework

  Scenario: Check github service status
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for organisation "opencredo"
    Then the following repositories should be present in the repository list
      | test-automation-quickstart |

  Scenario: Check github service status
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for organisation "opencredo"
    Then the following repositories should be present in the repository list
      | test-automation-quickstart |

  Scenario: Check github service status
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for organisation "opencredo"
    Then the following repositories should be present in the repository list
      | test-automation-quickstart |

  Scenario: Check github service status
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for organisation "opencredo"
    Then the following repositories should be present in the repository list
      | test-automation-quickstart |

  Scenario: Check github service status
    Given that Github is up and running with a "good" status
    When I retrieve a list of repositories for organisation "opencredo"
    Then the following repositories should be present in the repository list
      | test-automation-quickstart |