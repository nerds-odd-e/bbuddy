@user @monthlyBudget
Feature: Get amount of a period based on monthly budgeting

  Scenario: Get amount with no budget planned for any month overlapping the period
    Given budget planned for "2016-06" is 30
    When get amount of period from "2016-07-01" to "2016-07-08"
    Then the amount is 0

  Scenario: Get amount with budget planned for the month overlapping the period
    Given budget planned for "2016-07" is 31
    When get amount of period from "2016-07-01" to "2016-07-11"
    Then the amount is 11

