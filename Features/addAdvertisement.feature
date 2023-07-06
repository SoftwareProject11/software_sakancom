Feature: Add advertisement

  Scenario: add advertisement successfully
    Given that the owner or admin is logged in
    And there is an id_house with id 1124, photos "house1.jpg", owner_name "Lemara Ali", contactInfo "lemaraali@gmail.com, 0599632333", location "Nablus,PalestineStreet", services "internet", monthly_rent 1000, noteRent "inclusive of electricity" and price 5550
    Then the advertisement will be saved in the database


  Scenario: add advertisement of the same house
    Given that the owner or admin is logged in
    And there is an id_house with id 1124, photos "house1.jpg", owner_name "Lemara Ali", contactInfo "lemaraali@gmail.com, 0599632333", location "Nablus,PalestineStreet", services "internet", monthly_rent 1000, noteRent "inclusive of electricity" and price 5550
    Then the advertisement will not be saved in the database
    And show the reason why it can't save


  Scenario: add advertisement of a house that does not exist
    Given that the owner or admin is logged in
    And there is an id_house with id -1, photos "house1.jpg", owner_name "Lemara Ali", contactInfo "lemaraali@gmail.com, 0599632333", location "Nablus,PalestineStreet", services "internet", monthly_rent 1000, noteRent "inclusive of electricity" and price 5550
    Then the advertisement will not be saved in the database
    And show the reason why it can't save2