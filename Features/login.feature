Feature: user login

  Scenario: Successful log in
    Given that the user is not logged in
    And the username is "marwa"
    And the password is "1110"
    And the userChoice is "1"
    Then the user login succeeds

  Scenario: User entered a wrong username
    Given that the user is not logged in
    And the username is "not exist"
    And the password is "truePass"
    And the userChoice is "1"
    Then the user will not login
    And show the reason why he can't logged in

  Scenario: User entered a wrong password
    Given that the user is not logged in
    And the username is "marwa"
    And the password is "not true"
    And the userChoice is "1"
    Then the user will not login
    And show the reason why he can't logged in

  Scenario: User entered a wrong userChoice
    Given that the user is not logged in
    And the username is "marwa"
    And the password is "1110"
    And the userChoice is "4"
    Then the user will not login
    And show the reason why he can't logged in
