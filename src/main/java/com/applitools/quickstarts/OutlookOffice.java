package com.applitools.quickstarts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

public class OutlookOffice {

	public static void main(String[] args) {
		// Create object for eyes
		Eyes eyes = new Eyes();

		// Intialize a chrome browser
		System.setProperty("webdriver.chrome.driver", "D:\\Maveric\\Maveric Docs\\AppliTools\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		try {
			// Set API Key
			eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

			String url = "https://outlook.office.com/owa/?realm=maveric.com";

			// Set Batch Info
			BatchInfo batchInfo = new BatchInfo("OutlookOffice");
			eyes.setBatch(batchInfo);

			driver = eyes.open(driver, "Office 365", "OfficeOutlook", new RectangleSize(1200, 600));

			// Navigate to the url
			driver.get(url);
			
			
			eyes.checkWindow("Step1: Enter email ID:");
			
			//Enter the Value
			driver.findElement(By.name("loginfmt")).sendKeys("jayaseelia@maveric-systems.com");
			driver.findElement(By.id("idSIButton9")).click();
			driver.findElement(By.name("passwd")).sendKeys("");

			eyes.checkWindow("Step2: Enter email Password:");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eyes.close();
			driver.quit();
		}

	}

}
