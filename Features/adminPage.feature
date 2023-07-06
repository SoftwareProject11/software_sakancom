Feature: Admin logged in to his page

  Scenario: admin see a scenes of requests for advertisement of housing
    Given the admin logged in
    When he enter the id_house 1124, acceptance "yes"
    Then he can accept or reject it


  Scenario: admin watch reservations via the system
    Given the admin logged in
    When he select to see the reservations of houses
    Then a table of reservations data will printed



