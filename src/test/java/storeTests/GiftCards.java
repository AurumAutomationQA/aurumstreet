package storeTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class GiftCards extends ReusableMethods {

    @BeforeClass
    public void prerequisites() throws Exception {
        initialise();
        startTest("Login flow - Store");
        loginToStore();
        endTest();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    @AfterMethod
    public void afterTest() {
        endTest();
    }

    @Test(priority = 1)
    public void navigateAndValidateGiftCards() {
        startTest("Navigate to Gift Cards and validate fields");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Gift Cards']", "Gift Cards link");

        verifyIsDisplayed("//h3[contains(text(),'Gift')]", "Gift Cards heading");
        verifyIsDisplayed("//input[contains(@placeholder,'Search') or contains(@id,'search')]", "Search input");

        try {
            click("//button[contains(text(),'Add') or contains(text(),'Create')]", "Add/Create button");
            verifyIsDisplayed("//label", "Form label present");
            verifyIsDisplayed("//label//span[text()='*']", "Mandatory mark present");
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Add/Create not present: " + e.getMessage());
        }

        logScreenshot();
    }
}