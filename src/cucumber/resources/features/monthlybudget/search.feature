Feature: Get budget of a period

  Scenario: Get budget of a period
    Given budget planned for "2016-07" is 31
    And budget planned for "2016-08" is 31
    When get amount of period from "2016-07-10" to "2016-08-15"
    Then the amount is 37
