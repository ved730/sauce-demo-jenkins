Feature: Cart operations on SauceDemo

  Background:
    Given the user is logged in as "standard_user"

  Scenario: Add a single item to the cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"

  Scenario: Add multiple items to the cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show "3"

  Scenario: Remove an item from the cart page
    When the user adds "Sauce Labs Backpack" to the cart
    And the user goes to the cart page
    And the user removes the first item from the cart
    Then the cart should contain 0 items
