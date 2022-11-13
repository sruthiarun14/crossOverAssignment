package commonFunctions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CommonFunctions {
	
	/***
	 * Method to read the data from config.properties file
	 * @return config.properties file data
	 */
	public static Properties readConfigProperty() {
		Properties config = new Properties();
		try {
		FileReader propertyFile = new FileReader(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		config.load(propertyFile);
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		
		return config;
	}
	
	public static WebDriver driver;
	public static Properties config = readConfigProperty();
	public static Wait<WebDriver> wait;
	public static WebElement element;
	public static Actions action;
	

	/***
	 * Method to read the data from json file
	 * @param key for the test input
	 * @return value for the key
	 */
	public JSONObject getData(String inputData) {
			
			JSONObject testData = null;
			JSONParser parser = new JSONParser();
			Properties config = readConfigProperty();
			String folderPath = config.getProperty("testDataFolderPath").toString();
			File folder = new File(System.getProperty("user.dir")+folderPath);

			for (File fileEntry: folder.listFiles()) {
				if(fileEntry.isFile()) {
					String fileName = fileEntry.getName();
					String filePath = System.getProperty("user.dir") + folderPath + fileName;
					try {
						JSONObject fullDataSheet = (JSONObject) parser.parse(new FileReader(filePath));
						 testData = (JSONObject) fullDataSheet.get(inputData);
						if(testData != null) {
							return testData;
						}
					} catch(Exception e) {
						throw new RuntimeException("Failed to read test data from "+filePath, e);
					}
				}
			}
			return testData;

	}
	
	/***
	 * Method to refresh the WebPage
	 */
	public void refreshPage() {
		try {
			driver.navigate().refresh();
		}catch (TimeoutException e) {
			Assert.fail("Page failed to load in "+config.getProperty("pageLoadTimeOut").toString() + " seconds.");
			e.getLocalizedMessage();
		}
	}
	
	/***
	 * Method to wait for the minimum time out specified in the properties file
	 * @param locator
	 */
	public void waitForMinimumTimeOut() {
		try {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("minimumTimeOut").toString()), TimeUnit.SECONDS);
		}catch (TimeoutException e) {
			Assert.fail("Page failed to load in "+config.getProperty("pageLoadTimeOut").toString() + " seconds.");
			e.getLocalizedMessage();
		}
	}
	
	/***
	 * Method to wait for the time out specified in the properties file
	 * @param locator
	 */
	public void waitForThePageToLoad() {
		try {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("pageLoadTimeOut").toString()), TimeUnit.SECONDS);
		}catch (TimeoutException e) {
			Assert.fail("Page failed to load in "+config.getProperty("pageLoadTimeOut").toString() + " seconds.");
			e.getLocalizedMessage();
		}
	}
	
	/**
	 * Method to initiate the browser and open the application page
	 * @throws InterruptedException 
	 */
	public void initiateBrowser() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationException",false);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("screen-resolution", "685x515");
		
		File driverFile = new File(config.getProperty("driverPath").toString());
		System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
		driver = new ChromeDriver(capabilities);
		
		driver.manage().window().setSize(new Dimension(685,515));
		waitForMinimumTimeOut();
		driver.get(config.getProperty("url"));
		waitForThePageToLoad();

	}
	
	/***
	 * Method to wait till the element mentioned in the xPath is located
	 * @param locator
	 */
	public void waitForElementToBeVisible(String locator) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Long.parseLong(config.getProperty("timeOut").toString()), TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		}catch (TimeoutException e) {
			e.getLocalizedMessage();
		}
	}
	
	/***
	 * Method to check if the element mentioned in the xPath exists
	 * @param locator
	 * @return true if element exists
	 */
	public boolean isElementExists(String locator) {
		Boolean value = false;
		try {
			if(driver.findElements(By.xpath(locator)).size() != 0 ){
				value = true;
			}	
		}catch (ElementNotVisibleException e) {
			Assert.fail("Element not visible " + locator);
			e.printStackTrace();
		}
		return value;
	}
	
	/***
	 * Method to click the element mentioned in the xPath 
	 * @param locator
	 */
	public void click(String locator) {
		try {
			element = driver.findElement(By.xpath(locator));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
		}catch (Exception e) {
			Assert.fail("Element not clickable " + locator);
			e.printStackTrace();
		}
	}
	
	/***
	 * Method to get the application URL
	 * @return app URL
	 */
	public String getAppURL() {
		String url = config.getProperty("url").toString();
		return url;
	}
	
	/***
	 * Method to get the current URL
	 * @return current URL
	 */
	public String getCurrentURL() {
		String url = driver.getCurrentUrl();
		return url;
	}
	
	/***
	 * Method to type the text in the given path in the UI
	 * @param locator, text
	 */
	public void type(String locator, String text) {
		if(isElementExists(locator)) {
			try {
				element = driver.findElement(By.xpath(locator));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.clear();
				element.sendKeys(text);
			}catch (Exception e) {
				Assert.fail("Unable to type/clear text" + locator);
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Method to clear the text in the given path in the UI
	 * @param locator, text
	 */
	public void clearField(String locator) {
		if(isElementExists(locator)) {
			try {
				element = driver.findElement(By.xpath(locator));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.clear();
			}catch (Exception e) {
				Assert.fail("Unable to clear text" + locator);
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Method to type the text in the select path in the UI
	 * @param locator, text
	 */
	public void enterSelect(String locator, String text) {
		if(isElementExists(locator)) {
			try {
				element = driver.findElement(By.xpath(locator));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.sendKeys(text);
				element.sendKeys(Keys.RETURN);
			}catch (Exception e) {
				Assert.fail("Unable to type/clear text" + locator);
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Method to type multiple texts in the given path in the UI
	 * @param locator, text
	 */
	public void selectMultipleOptions(String locator, String[] options) {
		if(isElementExists(locator)) {
			try {
				driver.findElement(By.xpath(locator)).clear();
				for(int i=0; i<options.length; i++) {
					element = driver.findElement(By.xpath(locator));
					element.sendKeys(options[i]);
					element.sendKeys(Keys.RETURN);
				}
			}catch (Exception e) {
				Assert.fail("Unable to type/clear text" + locator);
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Method to uploadFile
	 * @param locator, filePath
	 */
	public void uploadFile(String locator, String filePath) {
		if(isElementExists(locator)) {
			try {
				element = driver.findElement(By.xpath(locator));
				element.sendKeys(System.getProperty("user.dir") + filePath);
			}catch (Exception e) {
				Assert.fail("Unable to upload file" + locator);
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * Method to get the text value of the web element mentioned in the locator
	 * @param locator
	 * @return text of the web element
	 */
	public String getWebElementText(String locator) {
		String text = "";
		if(isElementExists(locator)) {
			try {
				text = driver.findElement(By.xpath(locator)).getText();
			}catch (Exception e) {
				Assert.fail("Unable to get the text" + locator);
				e.printStackTrace();
			}
		}
		return text;
	}
	
	/***
	 * Method to pick a date in the UI
	 * @param locator, text
	 * @throws InterruptedException 
	 */
	public void pickDate(String locator, String text) throws InterruptedException {
		if(isElementExists(locator)) {
			element = driver.findElement(By.xpath(locator));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
			String os = System.getProperty("os.name");
			if (os.toLowerCase().trim().contains("mac")){
				element.sendKeys(Keys.COMMAND+"a", Keys.BACK_SPACE);
			}else{
				element.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
			}
			Thread.sleep(300);
			element.sendKeys(text);
			element.sendKeys(Keys.RETURN);
			Thread.sleep(300);
		}
	}
	
	/***
	 * Method to close all the browsers opened during the tests
	 */
	public void closeAllBrowsers() {
		driver.quit();
	}
	
	public void tearDown() {
		closeAllBrowsers();
	}
	

}
