Feature: Warming up with some cake

  Background:
    Given my name is "Niels"
    And I was born in FEBRUARY
    And other people sharing my birthday are
      | full name             | occupation                                                   |
      | Elizabeth Olsen       | Actress, being better than her sisters                       |
      | Kim Jong-Il           | World-class golfer, author of over 1.500 books, fashion icon |
      | Christopher Eccleston | The Doctor (from the North)                                  |
      | John Mcenroe          | Battled the Borg or something                                |

  Scenario: Birthdays cause your age to increase
    Given My age is 35
    When It's my birthday
    Then I should have an age of 36

  Scenario Outline: Variable ages
    Given I am <startingAge> years old
    When It's my birthday
    Then I should be <endingAge>

    Examples:
      | startingAge | endingAge |
      | 5           | 6         |
      | 99          | 100       |