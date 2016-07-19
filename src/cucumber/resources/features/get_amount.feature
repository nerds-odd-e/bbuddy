Feature: Get amount of a period based on monthly budgeting

  Scenario: Get amount with no budget for any month
    Given no budget for any month
    When get amount of period from "2016-07-01" to "2016-07-10"
    Then the amount is 0