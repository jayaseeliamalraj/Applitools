package com.applitools.quickstarts;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.BatchInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URI;
import java.net.URISyntaxException;

public  class HelloWorldBatch {

    public static void main(String[] args) {

        // Initialize the eyes SDK and set your private API key.
            URI serverURL = null;
        try {
            serverURL = new URI("https://eyesapi.applitools.com");
        } catch (URISyntaxException e) {
            System.out.println("URI Exception ");
            return;
        }

        Eyes eyes = new Eyes();

        String apiKey = System.getenv("APPLITOOLS_API_KEY");
        eyes.setApiKey(apiKey);



        BatchInfo batchInfo = new BatchInfo("Hello World Batch"); 
        eyes.setBatch(batchInfo);                                        

        
    	System.setProperty("webdriver.chrome.driver", "D:\\Maveric\\Maveric Docs\\AppliTools\\chromedriver.exe");
        WebDriver innerDriver = new ChromeDriver();  // Open a Chrome browser.
        RectangleSize viewportSizeLandscape = new RectangleSize(/*width*/ 1200, /*height*/ 600 );
        RectangleSize viewportSizePortrait = new RectangleSize(/*width*/ 900, /*height*/ 300 );

      //  runTest(eyes,innerDriver,viewportSizeLandscape);
        runTest(eyes,innerDriver,viewportSizePortrait);

        // Close the browser.
        if (innerDriver != null)
            innerDriver.quit();
        innerDriver.quit();
    }

    static void runTest(Eyes eyes,  WebDriver innerDriver, RectangleSize viewportSize) {

        WebDriver driver = eyes.open(innerDriver, "Hello World App v1", "Hello World Test", viewportSize);

        try {

            // Navigate the browser to the "hello world!" web-site.
            String website = "https://applitools.com/helloworld";
            driver.get(website);
            // Visual checkpoint #1

            eyes.checkWindow("Before mouse click");

            driver.findElement(By.tagName("button")).click(); // Click the "Click me!" button.
            
            driver.get("https://applitools.com/helloJaya");
            // Visual checkpoint #2.
            eyes.checkWindow("After mouse click"); 

            // End the test

            Boolean throwtTestCompleteException = false;
            TestResults result = eyes.close(throwtTestCompleteException);

            String url = result.getUrl();
            if (result.isNew()) {
                System.out.println("New Baseline Created: URL=" + url);
            } else if (result.isPassed()) {
                System.out.println("All steps passed:     URL=" + url);
            } else {
                System.out.println("Test Failed:          URL=" + url);
            }

        } finally {
            eyes.abortIfNotClosed();
        }

        
    }
}