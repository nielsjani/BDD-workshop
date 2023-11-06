Feature: Player actions that can be taken during a turn

  Background:
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
    And another component
      | ID           | TREASURY |
      | VP           | 1        |
      | BIOMASS_COST | 5        |
      | WATER_COST   | 1        |
    And another component
      | ID           | INDOOR_FOREST |
      | VP           | 1             |
      | BIOMASS_COST | 3             |
    And I fill the fabricator with these components
    And a player RED exists

  Scenario Outline: Gathering resources
    When player RED gathers the following resources
      | ELECTRICITY | <electricity> |
      | BIOMASS     | <biomass>     |
      | WATER       | <water>       |
      | SCIENCE     | <science>     |
    Then player RED should have the following resource totals
      | ELECTRICITY | <newElectricity> |
      | BIOMASS     | <newBiomass>     |
      | WATER       | <newWater>       |
      | SCIENCE     | <newScience>     |
    And player RED should have 2 actions remaining

    Examples:
      | electricity | biomass | water | science | newElectricity | newBiomass | newWater | newScience | explanation   |
      | 2           | 0       | 0     | 0       | 4              | 2          | 2        | 2          | 2 of the same |
      | 0           | 2       | 0     | 0       | 2              | 4          | 2        | 2          | 2 of the same |
      | 0           | 0       | 2     | 0       | 2              | 2          | 4        | 2          | 2 of the same |
      | 0           | 0       | 0     | 2       | 2              | 2          | 2        | 4          | 2 of the same |
      | 1           | 1       | 0     | 0       | 3              | 3          | 2        | 2          | 2 different   |
      | 1           | 0       | 1     | 0       | 3              | 2          | 3        | 2          | 2 different   |
      | 1           | 0       | 0     | 1       | 3              | 2          | 2        | 3          | 2 different   |


#    Note, you can still bypass the check by eg gathering -2 biomass and +2 science. I'll leave that fix up to you
  Scenario Outline: Gathering not exactly 2 resources causes error
    When player RED gathers the following resources
      | ELECTRICITY | <electricity> |
      | BIOMASS     | <biomass>     |
      | WATER       | <water>       |
      | SCIENCE     | <science>     |
    Then error "Should gather exactly 2 resources" should have been thrown
    And player RED should have 3 actions remaining
    And player RED should have the following resource totals
      | ELECTRICITY | 2 |
      | BIOMASS     | 2 |
      | WATER       | 2 |
      | SCIENCE     | 2 |

    Examples:
      | electricity | biomass | water | science | explanation        |
      | 3           | 0       | 0     | 0       | gather more than 2 |
      | 2           | 1       | 0     | 0       | gather more than 2 |

      | 1           | 0       | 0     | 0       | gather only 1      |
      | 0           | 1       | 0     | 0       | gather only 1      |
      | 0           | 0       | 1     | 0       | gather only 1      |
      | 0           | 0       | 0     | 1       | gather only 1      |

      | 0           | 0       | 0     | 0       | gather 0           |

#Should also check if component is no longer present in the fabricator
  Scenario: Add outpost room - no need for wild resource spending
    When player RED adds outpost BUNKS to their base
    Then player RED should have the following resource totals
      | ELECTRICITY | 2 |
      | BIOMASS     | 1 |
      | WATER       | 1 |
      | SCIENCE     | 2 |
    And player RED should have 2 actions remaining
    And player RED should have BUNKS in their base

  Scenario: Add outpost room - need to spend wild resource
    When player RED adds outpost INDOOR_FOREST to their base
    Then player RED should have the following resource totals
      | ELECTRICITY | 1 |
      | BIOMASS     | 0 |
      | WATER       | 2 |
      | SCIENCE     | 2 |
    And player RED should have 2 actions remaining
    And player RED should have INDOOR_FOREST in their base

  Scenario: Add outpost room - cannot afford component
    When player RED adds outpost TREASURY to their base
    Then error "Could not subtract costs using current resources" should have been thrown
    And player RED should have the following resource totals
      | ELECTRICITY | 2 |
      | BIOMASS     | 2 |
      | WATER       | 2 |
      | SCIENCE     | 2 |
    And player RED should have 3 actions remaining
    And player RED should not have any rooms in their base

  Scenario: Running out of player actions - Max 3 actions per player
    Given player RED gathers the following resources
      | ELECTRICITY | 1 |
      | BIOMASS     | 1 |
      | WATER       | 0 |
      | SCIENCE     | 0 |
    And player RED gathers the following resources
      | ELECTRICITY | 1 |
      | BIOMASS     | 1 |
      | WATER       | 0 |
      | SCIENCE     | 0 |
    And player RED gathers the following resources
      | ELECTRICITY | 1 |
      | BIOMASS     | 1 |
      | WATER       | 0 |
      | SCIENCE     | 0 |
    Then player RED should have the following resource totals
      | ELECTRICITY | 5 |
      | BIOMASS     | 5 |
      | WATER       | 2 |
      | SCIENCE     | 2 |
    When player RED gathers the following resources
      | ELECTRICITY | 1 |
      | BIOMASS     | 1 |
      | WATER       | 0 |
      | SCIENCE     | 0 |
    Then error "No actions remaining" should have been thrown