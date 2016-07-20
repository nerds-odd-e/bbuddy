Feature: Add budget for month

  @wip
  Scenario: Add budget for month
    When add budget for "2016-06" with amount 100
    Then monthly budget 100 for "2016-06" is saved