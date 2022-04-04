Feature: Calculate Factorial numbers

  @run
  Scenario: Calculate Factorial numbers
    Given user acceses factorial calculator webapp
    And user fills the input sections with following value
      | 5 |
    When user clicks calculate factorial button
    Then a result is displyed on the screen with following result
      | The factorial of 5 is: 120 |