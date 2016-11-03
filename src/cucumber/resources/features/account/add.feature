@user @account
Feature: Add Account

  Scenario: successfully add account
    When add account with the following information
      | name | balance brought forward |
      | CMB  | 1000                    |
    Then the following account will be created
      | name | balance brought forward |
      | CMB  | 1000                    |

  Scenario: account name can not duplicate
    Given existing an account with name "CMB"
    When add a new account with name "CMB"
    Then there is an error message for duplicate name

  Scenario: validation of adding account failed
    When add account with the following information
      | name | balance brought forward |
      |      | -1                      |
    Then there is an error message for blank name
    And there is an error message for negative balance brought forward

