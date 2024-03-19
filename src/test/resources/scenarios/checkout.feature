Feature: Checkout

  Scenario Outline: : Proceed with checkout flow

    Given An product added to the cart for the User
    And Login page
    And I enter 'useremail001@mailinator.com' to the email field
    And I enter 'QAZ123wsx' to the password field
    And I click on login button
    When I open the checkout screen
    Then I see the product on the checkout screen
    When I select Place Order button
    Then I see Payment screen
    When I enter payment info: '<CardName>', '<CardNumber>', '<CVC>', '<ExpMM>', '<ExpYYYY>'
    And I select a confirmation button
    Then Payment done screen is opened
    When I select continue button
    Then Redirected to the Homepage

    Examples:
  | CardName         | CardNumber      | CVC  | ExpMM | ExpYYYY |
  | American Express | 3700000000000002 | 7373 | 03    | 2030   |
