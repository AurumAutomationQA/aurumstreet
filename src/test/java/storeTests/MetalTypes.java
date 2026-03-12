package storeTests;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.ReusableMethods;

public class MetalTypes extends ReusableMethods
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
        startTest("Navigate to store");
        openBrowser("chrome","https://deepa.aurumconnect.in/");//https://kinhub:KIPL_1122@bala.aurumsconnect.com/
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
        clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']","9392190045","Mobile number field");
        click("//button[text()='Send OTP']", "send OTP button");
        String otp="860821";
        for(int i=0;i<otp.length();i++) {
            clearNenterText("(//div[@role='group']//input)["+(i+1)+"]", ""+otp.charAt(i),"otp "+(i+1)+"th character");
        }
        click("//button[text()='Login']", "Login button");
        verifyIsDisplayed("//span[text()='Dashboard']/..", "dashboard button");
        logScreenshot();
    }
    
    @Test(priority=3)
    public void metalPricesTest() {
        startTest("Navigate to Metal Prices page");
        click("//span[text()='Dashboard']/..", "dashboard button");
        click("//a[@href=\"/admin/goldPricesList\"]", "Metal Prices link");
        verifyIsDisplayed("//h3[text()='Metal Prices']", "Metal Prices heading");
        verifyIsDisplayed("//button[contains(text(),'Update Prices') ]", "Update Prices button");
        logScreenshot();
    }
    
    @Test(priority=4)
    public void metalPricesGoldFields() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	startTest("Verify Gold metal fields in Metal Prices form"); 
    	// click any button whose text contains 'Add Plan'
    	click("//button[contains(text(),'Update Prices') ]", "Update Prices button");
    	// Verify fields present in the form for gold metal
    	verifyTextDisplayed("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//div[contains(@class,'react-select__single')]", "Gold", "Metal Type dropdown");
    	// List of gold karat fields
        String[] karatFields = {"22K","24K","18K","14K","10K","21K","9K"};

        for(String karat : karatFields) {

            // Verify label with mandatory star
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]")));

            WebElement star = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]/span[contains(text(),'*')]"));

            // Verify input field
            WebElement input = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]/following::input[1]"));

            if(label.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " label displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " label NOT displayed " + addScreenShot());
            }

            if(star.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " mandatory star displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " mandatory star NOT displayed " + addScreenShot());
            }

            if(input.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " input field displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " input field NOT displayed " + addScreenShot());
            }
        }
        getTest().log(LogStatus.INFO, "Gold metal form field verification completed");
        logScreenshot();
    }
    
    
    @Test(priority=5)
    public void metalPricesSilverFields() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	startTest("Verify Silver metal fields in Metal Prices form");
        WebElement metalInput = driver.findElement(By.xpath("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//input")); 
        metalInput.sendKeys("Silver"); 
     // Wait for dropdown option and click
        WebElement silverOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and normalize-space()='Silver']")));
        silverOption.click(); 
        getTest().log(LogStatus.PASS, "Selected Silver from the metal drop down");
        verifyTextDisplayed("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//div[contains(@class,'react-select__single')]", "Silver", "Metal Type dropdown");        
        // List of Silver karat fields
        String[] silverFields = {"Silver","99.9% Silver","92.5% Silver","80% Silver","95.8% Silver"};

        for(String purity : silverFields) {

            // Verify label with mandatory star
            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(),'" + purity + "')]")));

            WebElement star = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + purity + "')]/span[contains(text(),'*')]"));

            // Verify input field
            WebElement input = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + purity + "')]/following::input[1]"));

            if(label.isDisplayed()) {
                getTest().log(LogStatus.PASS, purity + " label displayed");
            } else {
                getTest().log(LogStatus.FAIL, purity + " label NOT displayed " + addScreenShot());
            }

            if(star.isDisplayed()) {
                getTest().log(LogStatus.PASS, purity + " mandatory star displayed");
            } else {
                getTest().log(LogStatus.FAIL, purity + " mandatory star NOT displayed " + addScreenShot());
            }

            if(input.isDisplayed()) {
                getTest().log(LogStatus.PASS, purity + " input field displayed");
            } else {
                getTest().log(LogStatus.FAIL, purity + " input field NOT displayed " + addScreenShot());
            }
        }
        getTest().log(LogStatus.INFO, "Silver metal form field verification completed");
        logScreenshot();        
    }
    
    @Test(priority=6)
    public void metalPricesGoldFormSubmission() {
    	startTest("Gold metal form submission in Metal Prices form");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement metalInput = driver.findElement(By.xpath("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//input"));
    	metalInput.sendKeys("Gold");
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and normalize-space()='Gold']"))).click();
    	getTest().log(LogStatus.PASS, "Selected Gold from Metal dropdown");
    	enterValue("24K", "16500");
    	enterValue("22K", "15900");
    	enterValue("21K", "15400");
    	enterValue("18K", "14500");
    	enterValue("14K", "13700");
    	enterValue("10K", "10000");
    	enterValue("9K", "9000");
    	
    	try {

    	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
    	            By.xpath("//button[normalize-space()='Submit']")));
    	    submitBtn.click();
    	    getTest().log(LogStatus.PASS, "Clicked Submit button");

    	} catch (Exception e) {
    	    getTest().log(LogStatus.FAIL, "Submit button NOT clickable " + addScreenShot());
    	}
    	try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            String toastXpath = "//div[contains(@class,'Toastify')]//div[contains(@class,'toast-icon')]/..//span";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));
            String toastResponse = getResponse(toastXpath);
            if (toastResponse.contains("Successfully Updated")) {
                getTest().log(LogStatus.PASS, "Gold Metal Type creation was submitted successful " + toastResponse);
            } 
            else {
                getTest().log(LogStatus.FAIL, "While submitting Gold Metal Type creation failed with unexpected toast message: " + toastResponse);
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, "While submitting Gold Metal Type creation, in the process of submitting the form, Toast message not displayed or not captured: " + e + addScreenShot());
        }
    	logScreenshot();
    }
    
    
    
    @Test(priority=7)
    public void metalPricesSilverFormSubmission() {
    	startTest("Silver metal form submission in Metal Prices form");
    	click("//button[contains(text(),'Update Prices') ]", "Update Prices button");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement metalInput = driver.findElement(By.xpath("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//input"));
    	metalInput.sendKeys("Silver");
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and normalize-space()='Silver']"))).click();
    	getTest().log(LogStatus.PASS, "Selected Silver from Metal dropdown");
    	enterValue("Silver", "300000");
    	enterValue("99.9% Silver", "295000");
    	enterValue("92.5% Silver", "290000");
    	enterValue("80% Silver", "275000");
    	enterValue("95.8% Silver", "280000");
    	
    	try {
    	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));
    	    submitBtn.click();
    	    getTest().log(LogStatus.PASS, "Clicked Submit button");
    	} catch (Exception e) {
    	    getTest().log(LogStatus.FAIL, "Submit button NOT clickable " + addScreenShot());
    	}
    	try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            String toastXpath = "//div[contains(@class,'Toastify')]//div[contains(@class,'toast-icon')]/..//span";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));
            String toastResponse = getResponse(toastXpath);
            if (toastResponse.contains("Successfully Updated")) {
                getTest().log(LogStatus.PASS, "Silver Metal Type creation was submitted successful " + toastResponse);
            } 
            else {
                getTest().log(LogStatus.FAIL, "While submitting Silver Metal Type creation failed with unexpected toast message: " + toastResponse);
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, "While submitting Silver Metal Type creation, in the process of submitting the form, Toast message not displayed or not captured: " + e + addScreenShot());
        }
    	logScreenshot();
    }
    
    
    
    public void enterValue(String karat, String value) {
        try {
            WebElement input = driver.findElement(By.xpath("//label[contains(normalize-space(),'" + karat + "')]/ancestor::div[contains(@class,'input')]//input"));
            input.clear();
            input.sendKeys(value);
            getTest().log(LogStatus.PASS, karat + " value entered : " + value);
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, karat + " input field NOT displayed " + addScreenShot());
        }
    }
    
    @AfterClass
    public void tearDown() {
        closeDriver();
    }
}
