package storeTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Events extends ReusableMethods {

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

    @Test(priority = 1)
    public void navigateAndValidateEvents() {
        startTest("Navigate to Events and validate fields");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Events']", "Events link");

        verifyIsDisplayed("//h3[text()='Events']", "Events heading");
        verifyIsDisplayed("//input[contains(@placeholder,'Search') or contains(@id,'search')]", "Search input");

        try {
            click("//button[contains(text(),'Add') or contains(text(),'Create') or contains(text(),'Add Exhibition')]", "Add/Create button");
            verifyIsDisplayed("//label", "Form label present");
            verifyIsDisplayed("//label//span[text()='*']", "Mandatory mark present");
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Add/Create not present: " + e.getMessage());
        }

        logScreenshot();
    }
}