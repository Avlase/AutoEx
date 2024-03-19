Feature: Cart

  Scenario: Add product to cart as logged in User

    Given An product added to the cart for the User
    And Login page
    And I enter 'useremail001@mailinator.com' to the email field
    And I enter 'QAZ123wsx' to the password field
    And I click on login button
    When I open the cart
    Then I see the product in the cart
    When the quantity is updated for the product
    Then update ie reflected on the cart
    When product is deleted from the cart
    Then I see a message 'Cart is empty! Click here to buy products.'