Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify place is successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"
    Examples:
      |name       |language |address            |
      |Aspenwoods |English  |1105 Duckwood trail|
      |StreetSmart|English  |2520 Pilot Knob Rd |

  @DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"