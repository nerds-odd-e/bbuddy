@user
Feature: FanLoBow

  Scenario: Game started
    Given there is a word "taiyuan"
    When this game started
    Then I can see "_ai_ua_"
    And used "aeiou"
    And length 7
    And tries 12
