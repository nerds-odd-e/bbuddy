Feature: Login

  Scenario: Successful Login
    Given there is a user named "user" and password is "password"
    When login with user name "user" and password "password"
    Then login successfully