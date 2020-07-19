Feature: The user can check for available flights

  Scenario Outline: The user can search for quickest flight
    Given the user is on the flight search page
    When the user enters  <departure city>
    And the user enters  <destination city>
    And the user selects the travel date
    And clicks search
    Then the user is able to navigate to the search result page
    And the user sorts quickest flight
    Then the user finds the quickest flight

    Examples: 
      | departure city | destination city |
      | Hyderabad      | Chennai          |
