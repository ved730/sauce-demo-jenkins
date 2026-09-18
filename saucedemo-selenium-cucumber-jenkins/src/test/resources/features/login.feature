Feature: Login to SauceDemo

  Scenario: Valid login redirects to the inventory page
    Given the user is on the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the inventory page

  Scenario: Locked out user cannot log in
    Given the user is on the SauceDemo login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then an error message containing "locked out" should be displayed

  Scenario: Login fails with an incorrect password
    Given the user is on the SauceDemo login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then an error message containing "do not match" should be displayed

  Scenario Outline: Login fails when required fields are empty
    Given the user is on the SauceDemo login page
    When the user logs in with username "<username>" and password "<password>"
    Then an error message containing "<expectedError>" should be displayed

    Examples:
      | username       | password     | expectedError            |
      |                | secret_sauce | Username is required     |
      | standard_user  |              | Password is required     |
      |                |              | Username is required     |
