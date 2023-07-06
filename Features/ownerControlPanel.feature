Feature: Owner want to see his housings

  Scenario: Find house By ID successfully
    Given that the owner logged in and select to see his housings
    And the owner enter the house ID =1124
    Then the number of tenants and floors and the id of floors are printed

  Scenario: Find floor By ID successfully
    Given that the owner logged in
    And the owner enter the floor ID =1
    Then the apartments of this floor printed

  Scenario: Find apartment By ID successfully
    Given that the owner logged in
    And the owner enter the apartments ID =1202
    Then the names of tenants and their communication info, and number of bathrooms, bedrooms, and if it has a balcony are printed




