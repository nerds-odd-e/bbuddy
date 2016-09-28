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

  Scenario: validation of adding transaction failed
    When add transactions with the following information
      | Type    | Description         | Date          | Amount  |
      | Income  |                     | wrong format  |         |
    Then there is an error message for blank description
    And there is an error message for invalid date date
    And there is an error message for null amount

  Scenario: show all transactions
    Given exists the following transactions
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |
    When show all transactions
    Then you will see all transactions as below
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
      | Outcome | Buy MacBook Pro     | 2015-11-01  | 100     |

  Scenario: total of all transactions
    Given exists the following transactions
      | Type    | Description           | Date       | Amount |
      | Income  | Course Registration   | 2016-08-14 | 4000   |
      | Income  | Coaching Fee          | 2016-08-15 | 10000  |
      | Outcome | Buy MacBook Pro       | 2015-11-01 | 15000  |
      | Outcome | Buy a new MacBook Pro | 2016-03-01 | 15000  |
    When show total of all transactions
    Then you will see the total as below
      | Total of Income  | 14000  |
      | Total of Outcome | 30000  |
      | Total of All     | -16000 |

