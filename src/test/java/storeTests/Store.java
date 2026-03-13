package storeTests;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Store extends ReusableMethods
{

	@BeforeClass
	public void prerequisites() {
		initialise();
		
	}
	
    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

	@AfterMethod
	public void afterTest() {
		endTest();
	}
	
	@Test(priority=1)
	public void launchTest() throws IOException, TimeoutException {
		startTest("Navigate to store");
		openBrowser("chrome","https://deepa.aurumconnect.in/");
		verifyIsDisplayed("//p[text()='Deepa Jewellers']", "Deepa Jewellers header");
		verifyIsDisplayed("//a[text()='Login']", "Login link");
		logScreenshot();
	}
	
	@Test(priority=2)
	public void loginTest() {
		startTest("login to store");
		click("//a[text()='Login']", "Login link");
		verifyIsDisplayed("//h3[text()='Login']", "Login label");
		verifyIsDisplayed("//button[contains(@class,'text-gray-600')]//span[text()='Back to Website']", "label - Back to Website and back arrow");
		verifyIsDisplayed("//span[text()='Mobile Number']/..//span[text()='*']", "Mobile Number field with mandatory symbol");
		verifyIsDisplayed("//button[text()='+91']/..//input[@placeholder='9999988888']", "+91 and text field with place holder 9999988888");
		verifyIsDisplayed("//button[text()='Send OTP']", "send OTP button");
		verifyIsDisplayed("//p[text()=\"Don't have a Shop Account?\"]", "label- Don't have a Shop Account?");
		verifyIsDisplayed("//a[text()=\"Create a shop account in 30 seconds\"]", "link - Create a shop account in 30 seconds");
		clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']","9392190045","Mobile number field");
		logScreenshot();
		
		click("//button[text()='Send OTP']", "send OTP button");
		verifyIsDisplayed("//h3[text()='Login']", "Login label");
		verifyIsDisplayed("//button[contains(@class,'text-gray-600')]//span[text()='Back to Website']", "label - Back to Website and back arrow");
		verifyIsDisplayed("//p[contains(text(),'A OTP has sent to ')]", "label - A OTP has sent to ");
		verifyIsDisplayed("//label[text()='Enter OTP']/..//span[text()='*']", "label - Enter OTP and mandatory symbol");
		String otp="860821";
		clearNenterText("(//div[@role='group']//input)[1]", ""+otp.charAt(0),"otp 1st character");
		clearNenterText("(//div[@role='group']//input)[2]", ""+otp.charAt(1),"otp 2nd character");
		clearNenterText("(//div[@role='group']//input)[3]", ""+otp.charAt(2),"otp 3rd character");
		clearNenterText("(//div[@role='group']//input)[4]", ""+otp.charAt(3),"otp 4th character");
		clearNenterText("(//div[@role='group']//input)[5]", ""+otp.charAt(4),"otp 5th character");
		clearNenterText("(//div[@role='group']//input)[6]", ""+otp.charAt(5),"otp 6th character");
		verifyIsDisplayed("//p[text()=\"Didn't Received?\"]", "label - Didn't Received?");
		verifyIsDisplayed("//button[contains(text(),'Resend OTP in ')]", "link - Resend OTP in ");
		logScreenshot();
		
		click("//button[text()='Login']", "Login button");
		verifyIsDisplayed("//span[text()='Dashboard']/..", "dashboard button");
		logScreenshot();
	}
	

	@Test(priority=3)
	public void dashboardTest() {
		startTest("Navigate to Dashboard");
		click("//span[text()='Dashboard']/..", "dashboard button");
		verifyIsDisplayed("//span[text()='Redeem Request']", "Redeem Request button");
		verifyIsDisplayed("//span[text()='Redeem Request']/../..//button[1]", "QR scaner button");
		verifyIsDisplayed("//span[text()='Redeem Request']/../..//button[2]", "Share button");
		//verifyIsDisplayed("//span[text()='Redeem Request']/../../..//button[following::span[text()='Redeem Request']]", "features expand button");	
		logScreenshot();
		
		clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand button");
		verifyIsDisplayed("//span[text()='Dashboard']", "Dashboard link");
		verifyIsDisplayed("//span[text()='Categories']", "Categories link");
		verifyIsDisplayed("//span[text()='eOffer Card']", "eOffer Card link");
		verifyIsDisplayed("//span[text()='Roles and Staff']", "Roles and Staff link");
		verifyIsDisplayed("//span[text()='Customers']", "Customers link");
		verifyIsDisplayed("//span[text()='Customer Enrollments']", "Customer Enrollments link");
		verifyIsDisplayed("//span[text()='Redeems']", "Redeems link");
		verifyIsDisplayed("//span[text()='Stores']", "Stores link");
		verifyIsDisplayed("//span[text()='Metal Prices']", "Metal Prices link");
		verifyIsDisplayed("//span[text()='Audience']", "Audience link");
		verifyIsDisplayed("//span[text()='Ads']", "Ads link");
		verifyIsDisplayed("//span[text()='Guess Me']", "Guess Me link");
		verifyIsDisplayed("//span[text()='Events']", "Events link");
		verifyIsDisplayed("//span[text()='Offers']", "Offers link");
		verifyIsDisplayed("//span[text()='Reviews']", "Reviews link");
		verifyIsDisplayed("//span[text()='Gift Cards']", "Gift Cards link");
		verifyIsDisplayed("//span[text()='Price Protection']", "Price Protection link");
		verifyIsDisplayed("//span[text()='Terms Documents']", "Terms Documents link");
		logScreenshot();
		
		
	}
	
	@Test(priority=4)
	public void metalPriceTest() {
		startTest("Navigate to Metal Prices");
		click("//span[text()='Metal Prices']", "Metal Prices link");
		verifyIsDisplayed("//h3[text()='Metal Prices']", "Metal Prices heading");
		verifyIsDisplayed("//button[text()='Update Prices']", "Update Prices button");
		verifyIsDisplayed("//button[text()='Clear']", "clear button");
		verifyIsDisplayed("//button[text()='Apply Filters']", "Apply Filters button");
		
		
		logScreenshot();
	}
}
