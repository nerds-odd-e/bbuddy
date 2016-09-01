@user @transaction
Feature: Transaction

  Scenario: successfully add transactions
    When add transactions with the following information
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |
    Then the following transactions will be created
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |

  Scenario: show all transactions
    Given exists the following transactions
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |
    When show all transactions
    Then you will see all transactions as belw
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |
