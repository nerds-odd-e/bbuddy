@user @monthlyBudget
Feature: Add budget for month

  Scenario: Add budget for month
    When add budget for "2016-06" with amount 100
    Then monthly budget 100 for "2016-06" is saved

  Scenario: Add budget for month which was set a budget already
    Given budget 100 has been set for month "2016-06"
    When add budget for "2016-06" with a new amount 200
    Then the budget for "2016-06" is 200

  Scenario: Validation of adding monthly budget failed
    When add monthly budget with no month and invalid budget
    Then there is an error message for null month
    And there is an error message for invalid number budget