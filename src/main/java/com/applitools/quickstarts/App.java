package com.applitools.quickstarts;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;

/**
 * 
 * This file runs four different tests for different parts of the tutorial based
 * on the arguments that's passed as arguments.
 *
 */

/**
 * --------------------- How to Execute ---------------------
 * 
 * From the Command line (Maven):
 * 
 * You can run different tests by setting the "args" value to one of "1", "2",
 * "3" or "4"
 * 
 * For test Part 1 mvn exec:java
 * -Dexec.mainClass="com.applitools.quickstarts.App" -Dexec.args="1"
 * 
 * 
 * 
 * From within Eclipse:
 * 
 * "Run app" menu --> Run Configurations.. --> Arguments (tab) --> Enter "1",
 * "2", "3" or "4" in the "Program arguments" section --> Click "Run".
 * 
 */

/*
 * --------------------------------------------------------------------- Note:
 * How to load Environment variables into Eclipse / IntelliJ:
 * ---------------------------------------------------------------------
 * 
 * If you are running the tests through an editor like Eclipse or IngelliJ. Open
 * the editor directly from the Terminal (and not by clicking on the editor
 * icon). To do that, run "open /path/to/Eclipse.app" from the Terminal.
 * 
 * This will load all the environment variables that's set in that Terminal into
 * Eclipse/IntelliJ! This will avoid you hard-coding paths and keys for things
 * as webdriver.chrome.driver, APPLITOOLS_API_KEY
 * 
 */

public class App {

	public static void main(String[] args) {

		/*
		 * Use Chrome browser
		 * 
		 * If you don't want to load chromedriver's path from the environment variable
		 * as suggested above, uncomment the following line and hard-code the ABSOLUTE
		 * path to the chromedriver
		 * 
		 * System.setProperty("webdriver.chrome.driver", "/Users/apps/chromedriver");
		 */

		System.out.println("Applitools API key: " + System.getenv("APPLITOOLS_API_KEY"));

		System.setProperty("webdriver.chrome.driver", "D:\\Maveric\\Maveric Docs\\AppliTools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		// Check if the Applitools API key is set in the environment
		if (System.getenv("APPLITOOLS_API_KEY") == null) {
			System.out.println("\n\n**** Please set APPLITOOLS_API_KEY in your environment ***");
			System.out.println("On Mac: export APPLITOOLS_API_KEY='YOUR_API_KEY'");
			System.out.println("On Windows: set APPLITOOLS_API_KEY='YOUR_API_KEY'");
			System.exit(0);
		}

		// Initialize the eyes SDK and set your private API key.
		Eyes eyes = new Eyes();

		// Set the API key from the env variable. Please read the "Important Note"
		// section above.
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

		try {

			// Call getTestInfoForPart to get the appropriate test information.
			HashMap<String, String> testInfo = App.getTestInfoForPart(args);

			System.out.println("Hashmap Value: " + testInfo);

			// Start the test by setting AUT's name, window or the page name that's being tested, viewport width and height
			eyes.open(driver, testInfo.get("appName"), testInfo.get("windowName"), new RectangleSize(
					Integer.parseInt(testInfo.get("viewportWidth")), Integer.parseInt(testInfo.get("viewportHeight"))));
			
			// Navigate the browser to the "ACME" demo app
			driver.get(testInfo.get("url"));

			// Visual checkpoint #1.
			eyes.checkWindow(testInfo.get("windowName"));
			
			eyes.setBaselineName("windowName");

			// End the test.
			eyes.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			// Close the browser.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortIfNotClosed();

			// End main test
			System.exit(0);
		}

	}

	/**
	 * getTestInfoForPart
	 * 
	 * This method returns details of the test for different parts of the tutorial.
	 * 
	 * This method receives tutorial part information from the command prompt such
	 * as "1", "2" etc., and then returns test app's URL, app-name, test-name,
	 * viewportHeight, viewportWidth and URL information for that part of the
	 * tutorial. This information is then used by the caller to run different tests.
	 * 
	 * @param args
	 *            String[] from the CLI such as "1", "2"
	 * 
	 * @return A hashmap of test app's URL, app-name, test-name, viewportHeight,
	 *         viewportWidth.
	 */
	public static HashMap<String, String> getTestInfoForPart(String[] args) {
		// There are 4 different parts of the tests. Default it to "1".
		String testPartNumber = "1";

		// If test part number is specified through CLI, use that
		if (args.length != 0) {
			testPartNumber = args[0];
		}

		System.out.println("Running tests for tutorial part: " + testPartNumber);

		int testNumber = Integer.parseInt(testPartNumber);
		if (testNumber == 0) {
			testNumber = 1;
		}
		HashMap<String, String> hmap = new HashMap<String, String>();

		String baseUrl = "https://demo.applitools.com/";
		// String baseUrl = "file:///Users/raja.rao/apps/acme-demo-app/";
		String viewportWidth = "1200";
		String viewportHeight = "600";
		String testName = "Login Page Java Quickstart";
		String appName = "ACME app";
		String loginPageName = "Login Page";
		String appPageName = "App Page";

		switch (testNumber) {
		default:
		case 1:
			System.out.println("Switch Statement: " + testNumber);
			hmap.put("url", baseUrl + "index.html");
			hmap.put("appName", appName);
			hmap.put("windowName", loginPageName);
			hmap.put("testName", testName);
			hmap.put("viewportWidth", viewportWidth);
			hmap.put("viewportHeight", viewportHeight);
			break;
		case 2:
			hmap.put("url", baseUrl + "index_v2.html");
			hmap.put("appName", appName);
			hmap.put("windowName", loginPageName);
			hmap.put("testName", testName);
			hmap.put("viewportWidth", viewportWidth);
			hmap.put("viewportHeight", viewportHeight);
			break;
		case 3:
			hmap.put("url", baseUrl + "app.html");
			hmap.put("appName", appName);
			hmap.put("windowName", appPageName);
			hmap.put("testName", testName);
			hmap.put("viewportWidth", viewportWidth);
			hmap.put("viewportHeight", viewportHeight);
			break;
		case 4:
			hmap.put("url", baseUrl + "app_v2.html");
			hmap.put("appName", appName);
			hmap.put("windowName", appPageName);
			hmap.put("testName", testName);
			hmap.put("viewportWidth", viewportWidth);
			hmap.put("viewportHeight", viewportHeight);
			break;

		}
		return hmap;
	}
}
