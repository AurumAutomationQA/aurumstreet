
package storeTests;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class PriceProtection extends ReusableMethods
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
    public void priceProtectionTest() {
        startTest("Navigate to Price Protection");
        click("//span[text()='Dashboard']/..", "dashboard button");
        click("//a[@href=\"/admin/priceProtectionPlan\"]", "Price Protection link");
        verifyIsDisplayed("//h3[text()='Price Protection Plans']", "Price Protection Plans heading");
        verifyIsDisplayed("//button[contains(text(),'Add Plan')]", "Add Plan button");
        logScreenshot();
    }

    
    @Test(priority=4)
    public void priceProtectionForm() {
    	startTest("Navigate to Price Protection form");
        click("//button[contains(text(),'Add Plan')]", "Add Plan button");
        // Verify mandatory fields present
        verifyIsDisplayed("//input[@placeholder='Enter plan name']", "Plan Name input");
        verifyIsDisplayed("//input[@id='plan-branches']", "Select Branches input");
        verifyIsDisplayed("//input[@placeholder='Enter plan code']", "Plan Code input");
        verifyIsDisplayed("//input[contains(@id,'plan-tenure')]", "Tenure dropdown");
        verifyIsDisplayed("//input[@placeholder='Start date']", "Plan Validity Start Date");
        verifyIsDisplayed("//input[@placeholder='End date']", "Plan Validity End Date");
        
    }
    
    @AfterClass
    public void tearDown() {
        closeDriver();
    }
}
