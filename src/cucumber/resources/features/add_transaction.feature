@transaction
Feature: Add Transaction

  Scenario: successfully add a new transaction
    When add a new transaction with the following information
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
    Then a new transaction will be created
      | Type    | Description         | Date        | Amount  |
      | Income  | Course Registration | 2016-08-14  | 4000    |
