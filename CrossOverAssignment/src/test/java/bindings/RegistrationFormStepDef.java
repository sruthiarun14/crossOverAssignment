package bindings;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.RegistrationFormPage;

public class RegistrationFormStepDef {
	RegistrationFormPage form = new RegistrationFormPage();

	@Given("^a user land on the registration page$")
	public void validateLandingPage() {
	   form.validateLoadedPage();
	}
	
	@When("^the user fills the form details '(.*)'$")
	public void fillFormData(String input) throws InterruptedException {
	   form.fillFormData(input);
	}
	
	@And("^the user clicks on the submit button$")
	public void clickOnSubmit() {
	   form.clickOnSubmit();
	}
	
	@Then("^the popup data matches with the form details '(.*)'$")
	public void validatePopUpData(String input) {
	   form.validatePopUpData(input);
	}
	
	@Then("^submission fails and no pop-up is displayed$")
	public void assertFailedSubmission() {
	   form.assertFailedSubmission();
	}
    
	@And("^clear all the fields$")
	public void clearMandatoryFields() {
	   form.clearMandatoryFields();
	}
	
	@And("^the user refresh the page$")
	public void refreshPage() {
	   form.refreshPage();
	}
}
