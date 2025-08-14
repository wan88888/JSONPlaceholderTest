Feature: Advanced JSONPlaceholder API Tests with State Management

  Background:
    Given I set base URL to "https://jsonplaceholder.typicode.com"

  Scenario: Test state sharing between steps
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And Save attribute "id" as "userId"
    When I send a GET request to "/posts?userId={userId}"
    Then the response status code should be 200
    And the response should be a JSON array

  Scenario: Create post and verify creation response
    When I send a POST request to "/posts" with body:
      | title  | Test Post for User |
      | body   | This is a test     |
      | userId | 1                  |
    Then the response status code should be 201

  Scenario: Get existing post and save data for later use
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And Save attribute "userId" as "savedUserId"
    When I send a GET request to "/users/{savedUserId}"
    Then the response status code should be 200