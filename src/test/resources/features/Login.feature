@login
Feature: User login
  Scenario: User is able to login with valid credentials
    Given User enters valid username
    And User enters valid password
    When User clicks on login button
    Then User is logged in successfully

  Scenario Outline: User is not able to login with valid credentials
    Given User enters invalid username "<username>"
    And User enters invalid password "<password>"
    When User clicks on login button
    Then User is not logged in successfully
    Examples:
      | username | password |
      | johnybravo@gmail.com | johny@23 |