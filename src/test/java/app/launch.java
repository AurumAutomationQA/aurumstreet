package app;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		launchApp();
		logScreenshot();
		System.out.println("app launched successfully");
	}
	
	
	//@AfterClass
	public void tearDown() {
		closeDriver();
	}

}
