@Test
Feature: API Tests


  @Test
  Scenario Outline: View all pets from the stores with status pending
    Given Get the API Url
    And Set the Authorization token
    Then Make a Rest Call to "/pet/findByStatus?status=pending" with "GET" method
    And Verify if the status code is "<statuscode>"

    Examples:
      | statuscode |
      | 200        |

    @Agus
  Scenario Outline: View pet store inventory status
    Given Get the API Url
    And Set the Authorization token
    Then Make a Rest Call to "/store/inventory" with "GET" method
    And Verify if the status code is "<statuscode>"

    Examples:
      | statuscode |
      | 200        |

  Scenario Outline: Add a new order to for a pet and then Delete it
    Given Get the API Url
    Then Construct the "store" request body with the following data
      | <petId>  |
      | <status> |
    Then Make a Rest Call to "/store/order" with "POST" method
    And Verify if the status code is "<statuscode1>"
    And Verify if the response contains the following info
      | petId    |
      |<petId> |
    Then Make a Rest Call to "/store/order/" con id "2" with "DELETE" method
    And Verify if the status code is "<statuscode2>"

    Examples:
      | statuscode1 | statuscode2 | petId | status |
      | 200         | 200         | 6   | placed |

  Scenario Outline: Add a new users and verify the info
    Given Get the API Url
    Then Construct the "user" request body with the following data
      | <id>        |
      | <username>  |
      | <firstName> |
      | <lastName>  |
      | <email>     |
      | <password>  |
      | <phone>     |
    Then Make a Rest Call to "/user/createWithArray" with "POST" method
    And Verify if the status code is "200"
    And Verify if the response contains the following info
      | username   | lastName   |
      | <username> | <lastName> |

  Examples:
    | id | firstName | username | lastName | email       | password | phone   |
    | 0211 | nick      | nick01   | Cage     | nickCage    | 123456   | 6175448 |
    | 0222 | Kev       | Kev01    | Michigan | kevMichigan | 456      | 6546845 |
