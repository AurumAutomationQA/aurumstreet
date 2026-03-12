package app;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utility.ReusableMethods;

public class launch extends ReusableMethods
{

	@BeforeClass
	public void prerequisites() {
		initialise();
		
	}
	
	
	@AfterMethod
	public void afterTest() {
		endTest();
	}
	
	@Test(priority=1)
	public void launchTest() throws IOException, TimeoutException {
		startTest("launch app");
		
		try {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("emulator-5554");

            // Path to APK file
            options.setApp("C:\\\\Users\\\\mahes\\\\Downloads\\\\aurum_staging_11_Mar.apk");

            options.setAutoGrantPermissions(true);
            options.setNoReset(true);
            options.setNewCommandTimeout(Duration.ofMillis(300));

            AndroidDriver driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

            System.out.println("App launched successfully!");

            Thread.sleep(5000);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//launchApp();
		//logScreenshot();
		System.out.println("app launched successfully");
	}
	
	
	//@AfterClass
	public void tearDown() {
		closeDriver();
	}

}
