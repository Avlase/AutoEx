Feature: Create/Delete account

  Scenario Outline: Create an account via API call
    When I send an API call to create an account with required data: '<Name>', '<Email>', '<title>', '<birth_day>', '<birth_month>', '<birth_year>', '<Password>', '<FirstName>', '<LastName>', '<Address1>', '<Address2>','<Country>', '<State>', '<City>', '<Zipcode>', '<mobile_number>'
    Then Get details by '<Email>' response code is 200
    And Login page
    And I enter '<Email>' to the email field
    And I enter '<Password>' to the password field
    And I click on login button
    And Redirected to the Homepage
    Examples:
   | Name   | Email                  | title   | birth_day | birth_month | birth_year | Password  | FirstName | LastName | Address1   | Address2   | Country       | State | City | Zipcode | mobile_number |
   | User01 | 004user@mailinator.com |   Mr    |      1    |    3        | 1971       | QAZ123wsx | Oneuser   | Lone     | Address 1  | Address 11 | Unites States | NY    | NY   | 100001  | 3223224424    |
   | User02 | 005user@mailinator.com |   Ms    |      1    |    3        | 1972       | QAZ123wsx | Twouser   | Ltwo     | Address 3  | Address 22 | Unites States | NY    | NY   | 100001  | 3223224424    |

  Scenario Outline: Delete an account via API call
    When I send an Api call to delete the account with: '<Email>', '<Password>'
    Then response body contains 'Account deleted!' message
    And response code is '200'
    Examples:
   | Email                  | Password  |
   | 004user@mailinator.com | QAZ123wsx |
   | 005user@mailinator.com | QAZ123wsx |

  Scenario Outline: Delete an account via API call if password is incorrect
    When I send an Api call to delete the account with: '<Email>', '<Password>'
    Then response body contains 'Account not found!' message
    Examples:
      | Email                  | Password  |
      | 006user@mailinator.com | incorrect |

  Scenario Outline: Delete account via API if account doesn't exist
    When I send an Api call to delete the account with: '<Email>', '<Password>'
    Then response body contains 'Account not found!' message
    Examples:
      | Email                     | Password     |
      | accountNotExists@mail.com | somePassword |

  Scenario Outline: Check account details received by API call
     When I send an API call to get account detail by '<Email>'
     Then response body contains data which is equal to '<Name>', '<Email>', '<title>', '<FirstName>', '<LastName>'
     Examples:
      | Name   | Email                  | title    | FirstName | LastName |
      | User03 | 007user@mailinator.com |   Mr     | Threeuser | Lthree   |