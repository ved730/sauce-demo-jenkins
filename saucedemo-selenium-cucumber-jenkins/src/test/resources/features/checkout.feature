Feature: Checkout flow on SauceDemo

  Background:
    Given the user is logged in as "standard_user"
    And the user has added "Sauce Labs Backpack" to the cart
    And the user goes to the cart page
    And the user proceeds to checkout

  Scenario: Complete checkout with valid information
    When the user enters checkout information "John" "Doe" "411001"
    And the user continues to the overview page
    And the user finishes the order
    Then the order confirmation should contain "Thank you"

  Scenario: Checkout fails when first name is missing
    When the user enters checkout information "" "Doe" "411001"
    And the user continues to the overview page
    Then a checkout error containing "First Name is required" should be displayed

  Scenario: Checkout fails when postal code is missing
    When the user enters checkout information "John" "Doe" ""
    And the user continues to the overview page
    Then a checkout error containing "Postal Code is required" should be displayed
