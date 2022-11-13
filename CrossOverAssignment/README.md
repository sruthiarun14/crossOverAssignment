## IgniteTech-QA-Automation
Implemented a basic cucumber framework integrated with Maven and TestNG using Java and Selenium WebDriver. 

###### **src/test/resources/featureFiles** - Contains the test scenarios and test steps in the Gherkin language.  
###### **src/test/resources/testData** - Contains the test input data for each test step.  
###### **src/test/java/bindings** - Contains the step definition for each test step in the feature file. 
###### **src/test/java/pages** - Contains the actions or assertions to be performed on the page.  
###### **src/test/java/commonFunctions** - Contains the WebDriver related functionalities.  
###### **src/test/java/runner** - Contains the testNG runner class.  

###### **src/test/resources/drivers** - Contains the chrome driver .exe file. (Tests has been implemented only for Chrome browser)  
###### **src/test/resources/config.properties** - Contains all the common properties.  

**Steps to run the test cases through terminal** 
######  1. Download the project folder to the local directory ex:assignment
######  2. Open terminal and go to the local project folder - "cd users/downloads/assignment"
######  3. Run the command "mvn test" 
######  4. If mvn command is not found, install maven - For Mac OS, "brew install maven". For other OS, "sudo apt-get install maven -y".
######  5. Once tests are completed, you can find the reports under the test-output folder

**Steps to run the test cases through eclipse** 
######  1. Download the project folder to the local directory
######  2. In Eclipse IDE, import as Maven project
######  3. Open the file src/test/java/runner/RunTest.java
######  4. Right click Run As > TestNG test
######  5. Once tests are completed, you can find the reports under the test-output folder

**Note** - Among the written 5 tests, 3 passed and 2 failed. Failures are valid. 
