@user @account
Feature: Show Accounts

  Scenario: show all accounts
    Given exists the following accounts
      | name | balance brought forward |
      | CMB  | 1000                    |
      | HSBC | 0                       |
    When show all accounts
    Then you will see all accounts as below
      | name | balance brought forward |
      | CMB  | 1000                    |
      | HSBC | 0                       |

