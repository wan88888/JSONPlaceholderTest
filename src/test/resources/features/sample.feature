Feature: Sample JSONPlaceholder API Tests

  Background:
    Given I set base URL to "https://jsonplaceholder.typicode.com"

  Scenario: Get all posts - basic test
    When I send a GET request to "/posts"
    Then the response status code should be 200
    And the response should be a JSON array

  Scenario: Get a specific post - basic test
    When I send a GET request to "/posts/1"
    Then the response status code should be 200

  Scenario: Get all users - basic test
    When I send a GET request to "/users"
    Then the response status code should be 200
    And the response should be a JSON array

  Scenario: Get a specific user - basic test
    When I send a GET request to "/users/1"
    Then the response status code should be 200

  Scenario: Create a new post - basic test
    When I send a POST request to "/posts" with body:
      | title  | My Test Post        |
      | body   | This is a test post |
      | userId | 1                   |
    Then the response status code should be 201

  Scenario: Delete a post - basic test
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200