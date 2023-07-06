Feature: Add House

  Scenario: add new house successfully
    Given that the admin is logged in
    And admin enter the id_house 1126, photos "house2.png", location "Old City", services "Cleaning Service,Security" price 7200, id_owner 22, no_floors 1
    Then the new house will be add to the database in system

  Scenario: add new house with id_house has already exist
    Given that the admin is logged in
    And admin enter the id_house 1126, photos "house2.png", location "Old City", services "Cleaning Service,Security" price 7200, id_owner 22, no_floors 1
    Then the house will not added to system

  Scenario: add details for the new house successfully
    Given that the admin is logged in
    And admin enter the id_house 1126, id_floor 1, id_apart 1402, no_bathrooms 2, no_bedrooms 3, balcony "yes"
    Then the extra information about the house will be saved in the database