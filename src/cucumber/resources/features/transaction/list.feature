@user @transaction
Feature: Show Transactions

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

  @restoreApplicationConfiguration
  Scenario: paginate of all transactions
    Given exists 15 transactions
    And every page will display 9 transactions
    When show page 1
    Then you will see 9 transactions in page 1
    When show page 2
    Then you will see 6 transactions in page 2
