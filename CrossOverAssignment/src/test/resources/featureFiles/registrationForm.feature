@test_student_reg_form
Feature: Student Registration Form validation

  @test_student_reg_form_1 
  Scenario Outline: Veriy whether the student registration form is submitted successfully when all the fields in the form are filled with valid data.
  									Also veriy whether the data in the pop-up displayed after the submission is same as the entered values  
    Given a user land on the registration page
    When the user fills the form details '<fields>'
    And the user clicks on the submit button
    Then the popup data matches with the form details '<fields>'

    Examples:
    |fields|
    |allFields|

  @test_student_reg_form_2
  Scenario Outline: Veriy whether the student registration form is submitted successfully when only the mandatory fields in the form are filled with valid data.
  									Also veriy whether the data in the pop-up displayed after the submission is same as the entered values  
    Given a user land on the registration page
    When the user fills the form details '<fields>'
    And the user clicks on the submit button
    Then the popup data matches with the form details '<fields>'

    Examples:
    |fields|
    |mandatoryFields|
    
@test_student_reg_form_3
  Scenario Outline: Veriy whether the student registration form is not submitted successfully when any of the mandatory field in the form is left empty.
    Given a user land on the registration page
    When the user fills the form details '<noGenderfield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<noFirstNamefield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<noLastNamefield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<noMobilefield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  

    Examples:
    |noFirstNamefield|noLastNamefield|noGenderfield|noMobilefield|
    |noFirstNamefield|noLastNamefield|noGenderfield|noMobilefield|

@test_student_reg_form_4
  Scenario Outline: Veriy whether the student registration form is not submitted successfully when any of the field in the form is filled with invalid data format.
    Given a user land on the registration page
    When the user fills the form details '<invalidMobileField>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<invalidEmailField>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<invalidPicture>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<invalidLastNamefield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<invalidFirstNamefield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  
    And clear all the fields
    When the user fills the form details '<invalidDOBfield>'
    And the user clicks on the submit button
    Then submission fails and no pop-up is displayed  

    Examples:
    |invalidMobileField|invalidEmailField|invalidPicture|invalidLastNamefield|invalidFirstNamefield|invalidDOBfield|
    |invalidMobileField|invalidEmailField|invalidPicture|invalidLastNamefield|invalidFirstNamefield|invalidDOBfield|  

@test_student_reg_form_5
  Scenario Outline: Duplicate entry failure - Verify whether the student registration form is not submitted successfully when the same combination of first name, last name and mobile are registered.
    Given a user land on the registration page
    When the user fills the form details '<fields>'
    And the user clicks on the submit button
    Then the popup data matches with the form details '<fields>'
    And the user refresh the page
    When the user fills the form details '<repeatFields>'
    And the user clicks on the submit button 
    Then submission fails and no pop-up is displayed  

    Examples:
    |fields			 |repeatFields	|
    |uniqueFields|uniqueFields	|
   
    
   