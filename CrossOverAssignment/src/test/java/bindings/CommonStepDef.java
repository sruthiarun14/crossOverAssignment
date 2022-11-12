package bindings;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import commonFunctions.CommonFunctions;

public class CommonStepDef {
	
CommonFunctions commonFunctions = new CommonFunctions();
	
    @Before
	public void launchBrowser() throws InterruptedException {
		commonFunctions.initiateBrowser();

		}
	
	@After
	public void closeBrowser() {
		commonFunctions.closeAllBrowsers();
	}
	

}
