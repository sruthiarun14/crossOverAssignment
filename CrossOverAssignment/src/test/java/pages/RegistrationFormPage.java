package pages;

import org.testng.AssertJUnit;
import org.json.simple.JSONObject;

import commonFunctions.CommonFunctions;


public class RegistrationFormPage {

	CommonFunctions commonFunctions = new CommonFunctions();
	
	//List of the page locators
	String firstNamePath = "//input[@id='firstName']";
	String lastNamePath = "//input[@id='lastName']";
	String emailPath = "//input[@id='userEmail']";
	String genderPath = "//input[contains(@id,'gender-radio') and @value='{0}']/following-sibling::label";
	String mobilePath = "//input[@id='userNumber']";
	String dobPath = "//input[@id='dateOfBirthInput']";
	String subjectPath = "//input[@id='subjectsInput']";
	String hobbyPath = "//label[contains(@for,'hobbies-checkbox') and text() ='{0}' ]/parent::div";
	String uploadPicPath = "//input[@id='uploadPicture']";
	String addressPath = "//textarea[@id='currentAddress']";
	String stateDrpDwnPath = "//div[@id='stateCity-wrapper']//div[contains(@class,'placeholder') and text() = 'Select State']/following-sibling::div//input";
	String cityDrpDwnPath = "//div[@id='stateCity-wrapper']//div[contains(@class,'placeholder') and text() = 'Select City']/following-sibling::div//input";
	String submitBtn = "//button[@id='submit']";
	String closeBtn = "//button[@id='closeLargeModal']";
	
	String nameValue = "//td[text()='Student Name']/following-sibling::td";
	String emailValue = "//td[text()='Student Email']/following-sibling::td";
	String genderValue = "//td[text()='Gender']/following-sibling::td";
	String mobileValue = "//td[text()='Mobile']/following-sibling::td";
	String dobValue = "//td[text()='Date of Birth']/following-sibling::td";
	String subjectValue = "//td[text()='Subjects']/following-sibling::td";
	String hobbyValue = "//td[text()='Hobbies']/following-sibling::td";
	String fileValue = "//td[text()='Picture']/following-sibling::td";
	String addressValue = "//td[text()='Address']/following-sibling::td";
	String stateValue = "//td[text()='State and City']/following-sibling::td";
	String popUpWindow = "//div[@class='modal-content']";
	
	/***
	 * Validate whether the page loaded is same as expected
	 */
	public void validateLoadedPage() {
		String expectedURL = commonFunctions.getAppURL();
		String actualURL = commonFunctions.getCurrentURL();
		AssertJUnit.assertEquals("Expected page is not loaded", expectedURL, actualURL);
	}
	
	/***
	 * Method to fill the form details
	 * @param formData
	 * @throws InterruptedException 
	 */
	public void fillFormData(String formData) throws InterruptedException {
		JSONObject data = commonFunctions.getData(formData);
		if(data.containsKey("firstName")){
			commonFunctions.type(firstNamePath, data.get("firstName").toString());
		}
		if(data.containsKey("lastName")){
			commonFunctions.type(lastNamePath, data.get("lastName").toString());
		}
		if(data.containsKey("email")){
			commonFunctions.type(emailPath, data.get("email").toString());
		}
		if(data.containsKey("gender")){
			commonFunctions.click(genderPath.replace("{0}", data.get("gender").toString().trim()));
		}
		if(data.containsKey("mobile")){
			commonFunctions.type(mobilePath, data.get("mobile").toString());
		}
		if(data.containsKey("dob")){
			commonFunctions.pickDate(dobPath, data.get("dob").toString().replace(",", " "));
		}
		if(data.containsKey("subjects")){
			String[] subjects = data.get("subjects").toString().split(",");
			commonFunctions.selectMultipleOptions(subjectPath, subjects);
		}
		if(data.containsKey("hobbies")){
			String[] hobbies = data.get("hobbies").toString().split(",");
			for (int i=0; i<hobbies.length; i++) {
				commonFunctions.click(hobbyPath.replace("{0}", hobbies[i].toString().trim()));
			}
		}
		if(data.containsKey("file")){
			commonFunctions.uploadFile(uploadPicPath, data.get("file").toString());
		}
		if(data.containsKey("address")){
			commonFunctions.type(addressPath, data.get("address").toString());
		}
		if(data.containsKey("state")){
			commonFunctions.enterSelect(stateDrpDwnPath, data.get("state").toString());
		}
		if(data.containsKey("city")){
			commonFunctions.enterSelect(cityDrpDwnPath, data.get("city").toString());
		}	
		
	}
	
	/***
	 * Submit the form
	 */
	public void clickOnSubmit() {
		commonFunctions.click(submitBtn);
	}
	
	/***
	 * Close the pop-up
	 */
	public void clickOnClose() {
		commonFunctions.click(closeBtn);
	}
	
	/***
	 * Method to validate the pop up data against form
	 * @param formData
	 */
	public void validatePopUpData(String formData) {
		JSONObject data = commonFunctions.getData(formData);
		String expectedValue = "";
		String actualValue = "";
		if(data.containsKey("firstName") && data.containsKey("lastName")){
			expectedValue = data.get("firstName").toString() + " " + data.get("lastName").toString(); 
			actualValue = commonFunctions.getWebElementText(nameValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("email")){
			expectedValue = data.get("email").toString(); 
			actualValue = commonFunctions.getWebElementText(emailValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("gender")){
			expectedValue = data.get("gender").toString(); 
			actualValue = commonFunctions.getWebElementText(genderValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("mobile")){
			expectedValue = data.get("mobile").toString(); 
			actualValue = commonFunctions.getWebElementText(mobileValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("dob")){
			expectedValue = data.get("dob").toString(); 
			actualValue = commonFunctions.getWebElementText(dobValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("subjects")){
			expectedValue = data.get("subjects").toString(); 
			actualValue = commonFunctions.getWebElementText(subjectValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("hobbies")){
			expectedValue = data.get("hobbies").toString(); 
			actualValue = commonFunctions.getWebElementText(hobbyValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("file")){
			expectedValue = data.get("fileName").toString(); 
			actualValue = commonFunctions.getWebElementText(fileValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("address")){
			expectedValue = data.get("address").toString(); 
			actualValue = commonFunctions.getWebElementText(addressValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
		if(data.containsKey("state") && data.containsKey("city")){
			expectedValue = data.get("state").toString() + " " + data.get("city").toString(); 
			actualValue = commonFunctions.getWebElementText(stateValue);
			AssertJUnit.assertEquals(expectedValue, actualValue);
		}
	}
	

	/***
	 * Method to assert whether the submission is failed
	 */
	public void assertFailedSubmission() {
		commonFunctions.waitForMinimumTimeOut();
		boolean success = commonFunctions.isElementExists(popUpWindow);
		AssertJUnit.assertFalse("Submission Not failed", success);
	}
	
	/***
	 * Method to clear the fields entered already
	 */
	public void clearMandatoryFields() {
		commonFunctions.clearField(firstNamePath);
		commonFunctions.clearField(lastNamePath);
		commonFunctions.clearField(mobilePath);
	}
	
	/***
	 * Method to refresh the web page
	 */
	public void refreshPage() {
		commonFunctions.refreshPage();
	}
	
	
}
