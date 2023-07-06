Feature: update house information
  Scenario: change the services of the house
    Given that the admin is logged in and want to update house info
    When he select to update services and he enter the new services = "Cleaning Service,Security" for the entered houseId 1124
    Then the services will updated successfully

  Scenario: change the price of the house
    Given that the admin is logged in and want to update house info
    When he select to update price and he enter the new price = 6500 for the entered houseId 1124
    Then the price will updated successfully

  Scenario: change the ownerId of the house
    Given that the admin is logged in and want to update house info
    When he select to update ownerId and he enter the new id = 12 for the entered houseId 1124
    Then the ownerId will updated successfully

  Scenario: change a non existing house
    Given that the admin is logged in and want to update house info
    And he enter non existing houseId
    Then the message that the house is not exist will be print