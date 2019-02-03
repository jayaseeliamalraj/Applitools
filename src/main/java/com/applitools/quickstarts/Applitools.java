package com.applitools.quickstarts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

public class Applitools {

	public static void main(String[] args) {
		Eyes eyes = new Eyes();
		
		
		//Initialize the driver
		System.setProperty("webdriver.chrome.driver", "D:\\Maveric\\Maveric Docs\\AppliTools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();


		// Set Applitools API key
		eyes.setApiKey("ELMVSQZgcmqw3gT97dnJDDTOqa0aIWZYtkO8uHw6kzKs110");
		
		
		// Start the test by setting AUT's name, window or the page name that's being tested, viewport width and height
		eyes.open(driver, "ACME app","Login Page", new RectangleSize(1300, 600));

		// Navigate the browser to the "ACME" demo app
		driver.get("https://demo.applitools.com");
		
		/** Generate screenshot.
			The following uploads the image data to Applitools for the AI to compare differences, generate baseline and so on.
		 	Visual checkpoint.
		 */
		eyes.checkWindow("Login window");
		
		// End the test
		eyes.close();

		// Close the browser.
		driver.quit();

		// If the test was aborted before eyes.close was called, ends the test as aborted.
		eyes.abortIfNotClosed();

	}

}
