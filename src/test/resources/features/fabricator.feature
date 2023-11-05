Feature: Fabricator management of Outpost components

  Background:
    Given a fabricator without any components

  Scenario: Print new batch of of components
    Given I have a component
      | ID           | MESS_HALL |
      | VP           | 5         |
      | BIOMASS_COST | 2         |
    And another component
      | ID               | LAB |
      | VP               | 9   |
      | ELECTRICITY_COST | 2   |
      | SCIENCE_COST     | 2   |
    And another component
      | ID           | BUNKS |
      | VP           | 1     |
      | BIOMASS_COST | 1     |
      | WATER_COST   | 1     |
    When I fill the fabricator with these components
    Then it should have all of these components available for construction

    Scenario: Trying to print with empty collection of components causes an error
      When I fill the fabricator with components
      Then it should throw exception "No components passed to fabricator"