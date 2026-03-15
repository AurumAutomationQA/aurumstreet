package storeTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Dashboard extends ReusableMethods {

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
    public void navigateAndValidateDashboard() {
        startTest("Navigate to Dashboard and validate fields");

        // ensure on dashboard
        click("//span[text()='Dashboard']/..", "dashboard button");

        // verify heading
        verifyIsDisplayed("//h3[contains(text(),'Dashboard')]", "Dashboard heading");

        // verify search and add/create if present
        verifyIsDisplayed("//input[contains(@placeholder,'Search') or contains(@id,'search')]", "Search input");
        // try to click Add/Create if present and validate labels + mandatory marks
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