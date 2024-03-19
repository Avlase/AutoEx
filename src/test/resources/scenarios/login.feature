Feature: Login
  As the user I want to login to the automationexercise.com

 Background:
   Given Login page

  Scenario: Check the placeholders in the input fields
    When the field email is empty
    And the field password is empty
    Then the field email has Email Address placeholder
    And the field password has Password placeholder

  Scenario: Wrong password
    When I enter 'useremail001@mailinator.com' to the email field
    And I enter '1234' to the password field
    And I click on login button
    Then an error message is displayed 'Your email or password is incorrect!'

  Scenario: Login successfully
    When I enter 'useremail001@mailinator.com' to the email field
    And I enter 'QAZ123wsx' to the password field
    And I click on login button
    Then Redirected to the Homepage