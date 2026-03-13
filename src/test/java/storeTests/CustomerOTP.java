package storeTests;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.ReusableMethods;

public class CustomerOTP extends ReusableMethods {

    String phoneNumber = randomPhoneNumber();
    String email = randomEmail();

    @BeforeClass
    public void prerequisites() {
        initialise();
    }

    @AfterMethod
    public void afterTest() {
        endTest();
    }

    @Test(priority = 1)
    public void launchTest() throws IOException, TimeoutException {

        startTest("Navigate to store");

        openBrowser("chrome", "https://deepa.aurumconnect.in/");

        verifyIsDisplayed("//p[text()='Deepa Jewellers']", "Deepa Jewellers header");
        
        verifyIsDisplayed("//a[text()='Login']", "Login link");

        logScreenshot();
    }

    @Test(priority = 2)
    public void openAddCustomerModal() {

        startTest("Open Add Customer modal");

        click("//a[text()='Login']", "Login link");

        verifyIsDisplayed("//h3[text()='Login']", "Login label");

        clearNenterText("//label[.//span[normalize-space()='Mobile Number']]/following-sibling::div//input","9392190045", "Mobile number field");

        click("//button[text()='Send OTP']", "Send OTP button");

        verifyIsDisplayed("//p[contains(text(),'OTP')]", "OTP label");

        String otp = "860821";

        for (int i = 0; i < otp.length(); i++) {
            clearNenterText("(//div[@role='group']//input)[" + (i + 1) + "]","" + otp.charAt(i), "OTP " + (i + 1));
        }

        click("//button[text()='Login']", "Login button");

        verifyIsDisplayed("//span[text()='Dashboard']/..", "Dashboard button");

        click("//span[text()='Dashboard']/..", "Dashboard");

        clickByCSS(".max-xl\\3Ahidden > .w-full", "Features expand");

        click("//span[text()='Customers']", "Customers");

        click("//button[normalize-space()='Add Customer']", "Add Customer");

        verifyIsDisplayed("//h2[normalize-space()='Add Customer']", "Add Customer modal");

        logScreenshot();
    }

    @Test(priority = 3)
    public void addCustomer() {

        startTest("Add Customer");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        verifyIsDisplayed("//h2[normalize-space()='Add Customer']", "Add Customer modal");

        clearNenterText("//input[@id='mobileNumber']", phoneNumber, "Mobile number");

        clearNenterText("//input[@id='email']", email, "Email");
        
        click("//button[normalize-space()='Send OTP']", "Send OTP button");

        try {

            String toastXpath = "//div[contains(@class,'Toastify')]//div[contains(@class,'toast-icon')]/..//span";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));

            String toastResponse = getResponse(toastXpath);

            if (toastResponse.contains("OTP sent successfully")) {

                getTest().log(LogStatus.PASS, toastResponse);

            } else {

                getTest().log(LogStatus.FAIL, "Unexpected toast message : " + toastResponse + addScreenShot());
                throw new AssertionError(toastResponse);
            }

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Toast not displayed " + addScreenShot());
            throw e;
        }

        String otp = "456456";

        for (int i = 0; i < otp.length(); i++) {

            clearNenterText("(//div[@role='group']//input)[" + (i + 1) + "]","" + otp.charAt(i), "OTP " + (i + 1));
        }

        try {

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(text())='Verify OTP']")));

            submitBtn.click();

            getTest().log(LogStatus.PASS, "Clicked Verify OTP button");

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Verify OTP button NOT clickable " + addScreenShot());
            throw e;
        }

        try {

            String toastXpath = "//div[contains(@class,'Toastify')]//span";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));

            String toastResponse = getResponse(toastXpath);

            if (toastResponse.contains("OTP verified successfully") || toastResponse.contains("OTP sent successfully")) {

                getTest().log(LogStatus.PASS, toastResponse);

            } else {

                getTest().log(LogStatus.FAIL, "Unexpected toast : " + toastResponse + addScreenShot());
                throw new AssertionError(toastResponse);
            }

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Toast not captured " + addScreenShot());
            throw e;
        }

        logScreenshot();
    }

    @Test(priority = 4)
    public void addCustomerWithDetails() {

        startTest("Add Customer with details");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        clearNenterText("//input[@id='firstName']", "TestFirst", "First Name");

        clearNenterText("//input[@id='lastName']", "TestLast", "Last Name");

        WebElement stateInput = driver.findElement(By.xpath("//input[@id='branch-state']"));
        stateInput.sendKeys("Karnataka");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and text()='Karnataka']"))).click();

        WebElement cityInput = driver.findElement(By.xpath("//input[@id='branch-city']"));
        cityInput.sendKeys("Bengaluru");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and text()='Bengaluru']"))).click();

        click("//input[@id='gender Male']", "Gender Male");

        try {

            WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Register & Create Customer')]")));

            registerBtn.click();

            getTest().log(LogStatus.PASS, "Clicked Register & Create Customer");

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Register button NOT clickable " + addScreenShot());
            throw e;
        }

        try {

            String toastXpath = "//div[contains(@class,'Toastify')]//span";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));

            String toastResponse = getResponse(toastXpath);

            if (toastResponse.toLowerCase().contains("Customer created successfully") || toastResponse.contains("OTP verified successfully") || toastResponse.contains("OTP sent successfully")) {

                getTest().log(LogStatus.PASS, toastResponse);

            } else {
                getTest().log(LogStatus.FAIL, toastResponse + addScreenShot());
                throw new AssertionError(toastResponse);
            }

        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, "Toast not displayed " + addScreenShot());
            throw e;
        }

        logScreenshot();
    }

    @Test(priority = 5)
    public void verifyAddedCustomerInList() {

        startTest("Verify added customer");

        clearNenterText("//input[@id='phoneNumber']", phoneNumber, "Search phone");

        click("//button[normalize-space(text())='Apply Filters' and not(@disabled)]","Apply Filters");

        verifyIsDisplayed("//td[normalize-space()='" + phoneNumber + "']", "Customer present");

        logScreenshot();
        
    }
    
    @Test(priority = 6)
    public void launchapp() {
		
		startTest("launch app");
		
		launchApp();

		logScreenshot();
    }
    
    @Test(priority = 7)
    public void verifyAddedCustomerInApp() {
    	startTest("Verify added customer in app");
    	
    	verifyIsDisplayed("//android.widget.Button[@text='LOGIN']", "Login button");

		logScreenshot();
		
    }
    
    @Test(priority = 8)
    public void demoWeb() throws IOException, TimeoutException {
    	startTest("Demo web test");
    	
    	switchToWebDriver();
    	
    	driver.navigate().back();

		logScreenshot();
    }
}