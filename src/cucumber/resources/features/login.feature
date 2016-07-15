Feature: Login

  Scenario: Successful Login
    Given there is a user named "Joseph" and password is "123456"
    When login with user name "Joseph" and password "123456"
    Then login successfully