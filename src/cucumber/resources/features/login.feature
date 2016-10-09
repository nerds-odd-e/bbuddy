@deleteUser
Feature: Login

  Scenario: Successful Login
    Given there is a user named "user" and password is "password"
    When login with user name "user" and password "password"
    Then login successfully

  Scenario: Login failed
    Given there is a user named "user" and password is "password"
    When login with user name "user" and password "wrong_password"
    Then login failed with some message

  @user
  Scenario: Successful Logout
    When logout
    Then logout with some message
