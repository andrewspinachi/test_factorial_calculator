Feature: Calculate Factorial numbers

  Scenario Outline: Calculate Factorial numbers
    Given factorial calculator exists
    And user inputs in the checkbox the <number>
    When user clicks calculate
    Then a result is displyed on the screen with following message
      |  |
    Examples:
      | number |
      | 1      |
      | 2      |