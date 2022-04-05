Feature: Calculate Factorial numbers

  Scenario: Calculate Factorial numbers - Positive
    Given user accesses factorial calculator webapp
    And user fills the input sections with following value
      | 5 |
    When user clicks calculate factorial button
    Then a result is displayed on the screen with following result
      | The factorial of 5 is: 120 |

@run
  Scenario: Calculate Factorial numbers - Negative
    Given user accesses factorial calculator webapp
    And user fills the input sections with following value
      |"   "|
    When user clicks calculate factorial button
    Then a result is displayed on the screen with following error message
      | Please enter an integer |