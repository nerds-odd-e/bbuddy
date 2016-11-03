@user @transaction
Feature: Add Transactions

  Scenario: successfully add transactions
    When add transactions with the following information
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |
    Then the following transactions will be created
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |

  Scenario: validation of adding transaction failed
    When add transactions with the following information
      | Type    | Description         | Date          | Amount  |
      | Income  |                     | wrong format  |         |
    Then there is an error message for blank description
    And there is an error message for invalid date date
    And there is an error message for null amount
